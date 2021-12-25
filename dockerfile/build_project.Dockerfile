FROM openjdk:11-slim
MAINTAINER Elizaveta Zhemchuzhina, Elena Sunko, Evgeniia Kirillova
WORKDIR /home/gradle/src

ADD ./gradlew ./build.gradle ./settings.gradle ./
ADD ./gradle ./gradle

ADD ./src ./src
ADD ./config ./config

RUN ./gradlew build 
