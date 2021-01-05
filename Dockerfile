FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --stacktrace
# PACKAGE STAGE
FROM openjdk:11-jdk
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/uservice.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-jar", "/app/uservice.jar"]