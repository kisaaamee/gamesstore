FROM adoptopenjdk:14-jdk-alpine
MAINTAINER Mikita Valadzko
COPY target/gamesstore-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
