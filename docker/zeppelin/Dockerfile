## This is taken almost entirely from the apache setup below
## https://hub.docker.com/r/apache/zeppelin/dockerfile
## I have only made some customization changes
## for example, ubuntu 18.04, python3, and no use of tini

FROM ubuntu:bionic
MAINTAINER ewalsh200 <ewalsh@economicdatasciences.ai>

WORKDIR /opt

RUN apt-get clean
RUN apt-get update -y --fix-missing
RUN DEBIAN_FRONTEND="noninteractive" apt-get -y install tzdata
RUN apt-get install -y apt-utils iputils-ping nano zip unzip python3 curl wget

RUN wget https://econdatasci.s3.eu-west-2.amazonaws.com/dev/jdk-8u231-linux-x64.tar.gz
RUN tar -xzf jdk-8u231-linux-x64.tar.gz

ENV JAVA_HOME=/opt/jdk1.8.0_231
ENV ZEPPELIN_HOME=/opt/zeppelin-0.9.0-preview1-bin-all
ENV ZEPPELIN_LOG_DIR=$ZEPPELIN_HOME/logs
ENV ZEPPELIN_NOTEBOOK_DIR=$ZEPPELIN_HOME/notebook
ENV ZEPPELIN_ADDR="0.0.0.0"
ENV PATH=$PATH:$JAVA_HOME/bin:$ZEPPELIN_HOME/bin:/opt/conda/bin

#ENV Z_VERSION="0.9.0-preview1"

#ENV LOG_TAG="[ZEPPELIN_${Z_VERSION}]:" \
#    Z_HOME="/opt/zeppelin-0.9.0-preview1-bin-all" \
#    LANG=en_US.UTF-8 \
#    LC_ALL=en_US.UTF-8 \
#    ZEPPELIN_ADDR="0.0.0.0"

RUN echo "$LOG_TAG update and install basic packages" && \
    apt-get -y update && \
    apt-get install -y locales && \
    locale-gen $LANG && \
    apt-get install -y software-properties-common && \
    apt -y autoclean && \
    apt -y dist-upgrade && \
    apt-get install -y build-essential

# should install conda first before numpy, matploylib since pip and python will be installed by conda
RUN echo "$LOG_TAG Install miniconda3 related packages" && \
    apt-get -y update && \
    apt-get install -y bzip2 ca-certificates \
    libglib2.0-0 libxext6 libsm6 libxrender1 \
    git mercurial subversion && \
    echo 'export PATH=/opt/conda/bin:$PATH' > /etc/profile.d/conda.sh && \
    wget --quiet https://repo.continuum.io/miniconda/Miniconda3-4.6.14-Linux-x86_64.sh -O ~/miniconda.sh && \
    /bin/bash ~/miniconda.sh -b -p /opt/conda && \
    rm ~/miniconda.sh

RUN echo "$LOG_TAG Install python related packages" && \
    apt-get -y update && \
    apt-get install -y python3-dev python3-pip && \
    apt-get install -y gfortran && \
    # numerical/algebra packages
    apt-get install -y libblas-dev libatlas-base-dev liblapack-dev && \
    # font, image
    apt-get install -y libpng-dev libfreetype6-dev libxft-dev && \
    # for tkinter
    apt-get install -y python3-tk libxml2-dev libxslt-dev zlib1g-dev && \
    hash -r && \
    conda config --set always_yes yes --set changeps1 no && \
    conda update -q conda && \
    conda info -a && \
    conda config --add channels conda-forge && \
    pip install -q pycodestyle==2.5.0 && \
    pip install -q numpy==1.17.3 pandas==0.25.0 scipy==1.3.1 grpcio==1.19.0 bkzep==0.6.1 hvplot==0.5.2 protobuf==3.10.0 pandasql==0.7.3 ipython==7.8.0 matplotlib==3.0.3 ipykernel==5.1.2 jupyter_client==5.3.4 bokeh==1.3.4 panel==0.6.0 holoviews==1.12.3 pycodestyle==2.5.0 apache_beam==2.15.0

RUN echo "$LOG_TAG Install R related packages" && \
    echo "PATH: $PATH" && \
    ls /opt/conda/bin && \
    echo "deb https://cloud.r-project.org/bin/linux/ubuntu bionic-cran40/" | tee -a /etc/apt/sources.list && \
    gpg --keyserver keyserver.ubuntu.com --recv-key E298A3A825C0D65DFD57CBB651716619E084DAB9 && \
    gpg -a --export E298A3A825C0D65DFD57CBB651716619E084DAB9 | apt-key add - && \
    apt-get -y update && \
    apt-get -y --allow-unauthenticated install r-base r-base-dev && \
    R -e "install.packages('evaluate', repos = 'https://cloud.r-project.org')" && \
    R -e "install.packages('knitr', repos='http://cran.us.r-project.org')" && \
    R -e "install.packages('ggplot2', repos='http://cran.us.r-project.org')" && \
    R -e "install.packages('googleVis', repos='http://cran.us.r-project.org')" && \
    R -e "install.packages('data.table', repos='http://cran.us.r-project.org')" && \
    R -e "install.packages('IRkernel', repos = 'https://cloud.r-project.org');IRkernel::installspec()" && \
    R -e "install.packages('shiny', repos = 'https://cloud.r-project.org')" && \
    # for devtools, Rcpp
    apt-get -y install libcurl4-gnutls-dev libssl-dev && \
    R -e "install.packages('devtools', repos='http://cran.us.r-project.org')" && \
    R -e "install.packages('Rcpp', repos='http://cran.us.r-project.org')" && \
    Rscript -e "library('devtools'); library('Rcpp'); install_github('ramnathv/rCharts')"

# Install kubectl
RUN apt-get install -y apt-transport-https && \
    curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add - && \
    echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | tee -a /etc/apt/sources.list.d/kubernetes.list && \
    apt-get update && \
    apt-get install -y kubectl

RUN echo "$LOG_TAG Cleanup" && \
    apt-get autoclean && \
    apt-get clean

RUN wget https://econdatasci.s3.eu-west-2.amazonaws.com/dev/zeppelin-0.9.0-preview1-bin-all.tgz
RUN tar -xzf zeppelin-0.9.0-preview1-bin-all.tgz
RUN mkdir -p /opt/zeppelin-0.9.0-preview1-bin-all/logs
RUN mkdir -p /opt/zeppelin-0.9.0-preview1-bin-all/run
RUN mkdir -p /opt/zeppelin-0.9.0-preview1-bin-all/webapps

## Need to sort out log4j properties
## COPY log4j.properties ${Z_HOME}/conf/

WORKDIR /opt/zeppelin-0.9.0-preview1-bin-all

RUN addgroup hadoop
RUN useradd -ms /bin/bash hduser
RUN usermod -a -G hadoop hduser
RUN chown -R hduser:hadoop /opt/zeppelin-0.9.0-preview1-bin-all

EXPOSE 8080

USER hduser

CMD ./bin/zeppelin-daemon.sh start && tail -f ./logs/*.log
