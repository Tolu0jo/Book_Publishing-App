FROM openjdk:17-jdk-alpine
MAINTAINER tolu.com
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]