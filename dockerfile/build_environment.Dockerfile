FROM openjdk:11-slim as image
MAINTAINER Elizaveta Zhemchuzhina, Elena Sunko, Evgeniia Kirillova
WORKDIR /home/gradle/src

ADD ./gradlew ./build.gradle ./settings.gradle ./
ADD ./gradle ./gradle

ADD ./src ./src
ADD ./config ./config

RUN ./gradlew assemble

FROM openjdk:11-jre-slim
COPY --from=image /home/gradle/src/build/libs/*.jar logs_lib/lib.jar
ENTRYPOINT ["java", "-jar", "logs_lib/lib.jar"]
