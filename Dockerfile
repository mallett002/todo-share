FROM openjdk:14-jdk-alpine as build
MAINTAINER mallett002@gmail.com
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/TodoShare.jar
ADD ${JAR_FILE} todoshare.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/todoshare.jar"]
