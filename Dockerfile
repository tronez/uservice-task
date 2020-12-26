FROM openjdk:11-jdk
ADD build/libs/uservice-1.0-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-SPRING.PROFILES.ACTIVE=local", "uservice-1.0-SNAPSHOT.jar"]