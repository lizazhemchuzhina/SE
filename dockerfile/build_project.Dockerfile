FROM gradle:7.1.0-jdk11
WORKDIR /home/gradle/src
COPY . .
RUN gradle build
