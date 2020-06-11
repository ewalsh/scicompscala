FROM ubuntu:bionic
MAINTAINER ewalsh200 <ewalsh@economicdatasciences.ai>

WORKDIR /opt

RUN apt-get clean
RUN apt-get update -y --fix-missing
RUN DEBIAN_FRONTEND="noninteractive" apt-get -y install tzdata
RUN apt-get install -y apt-utils iputils-ping nano zip unzip python3 curl wget r-base

RUN wget wget https://econdatasci.s3.eu-west-2.amazonaws.com/dev/jdk-8u231-linux-x64.tar.gz
RUN tar -xzf jdk-8u231-linux-x64.tar.gz
