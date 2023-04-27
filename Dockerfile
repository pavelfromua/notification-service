FROM openjdk:11
WORKDIR /app
COPY target/notifier-0.0.1.jar .
ENTRYPOINT ["java", "-jar", "notifier-0.0.1.jar"]