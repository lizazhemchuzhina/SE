FROM gradle:7.1.0-jdk11
MAINTAINER Elizaveta Zhemchuzhina, Elena Sunko, Evgeniia Kirillova
WORKDIR /home/gradle/src
RUN git clone -b docker-branch https://github.com/lizazhemchuzhina/SE.git .
RUN gradle build
