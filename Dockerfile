FROM ubuntu:bionic
MAINTAINER ewalsh200 <ewalsh@economicdatasciences.ai>

WORKDIR /opt

RUN apt-get clean
RUN apt-get update -y --fix-missing
RUN DEBIAN_FRONTEND="noninteractive" apt-get -y install tzdata
RUN apt-get install -y apt-utils iputils-ping nano zip unzip python3 curl wget r-base

RUN wget https://archive.apache.org/dist/spark/spark-2.2.1/spark-2.2.1-bin-hadoop2.7.tgz
RUN tar -xzf spark-2.2.1-bin-hadoop2.7.tgz
RUN wget wget https://econdatasci.s3.eu-west-2.amazonaws.com/dev/jdk-8u231-linux-x64.tar.gz
RUN tar -xzf jdk-8u231-linux-x64.tar.gz

ENV SPARK_HOME=/opt/spark-2.2.1-bin-hadoop2.7
ENV JAVA_HOME=/opt/jdk1.8.0_231
ENV PATH=$PATH:$SPARK_HOME/bin:$JAVA_HOME/bin
ENV SPARK_MASTER_ADDR=localhost
ENV SPARK_MASTER_PORT=7077

RUN addgroup hadoop
RUN useradd -ms /bin/bash hduser
RUN usermod -a -G hadoop hduser
RUN chown -R hduser:hadoop /opt/spark-2.2.1-bin-hadoop2.7

USER hduser

WORKDIR /opt/spark-2.2.1-bin-hadoop2.7

CMD ./bin/spark-class org.apache.spark.deploy.master.Master localhost:7077
