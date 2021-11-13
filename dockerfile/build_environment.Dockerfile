FROM gradle:7.1.0-jdk11 AS image
MAINTAINER Elizaveta Zhemchuzhina, Elena Sunko, Evgeniia Kirillova
WORKDIR /home/gradle/src
RUN git clone -b docker-branch https://github.com/lizazhemchuzhina/SE.git .
RUN gradle build

FROM openjdk:11-jre-slim
COPY --from=image /home/gradle/src/build/libs/*.jar logs_lib/lib.jar
ENTRYPOINT ["java", "-jar", "logs_lib/lib.jar"]
