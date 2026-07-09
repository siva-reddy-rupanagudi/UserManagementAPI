FROM eclipse-temurin:21-jdk
LABEL authors="Belen ITS"
WORKDIR /app
COPY target/UserManagementAPI-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]