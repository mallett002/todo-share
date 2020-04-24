FROM openjdk:14-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/TodoShare.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]