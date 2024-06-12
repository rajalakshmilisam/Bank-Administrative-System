FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/love-bank-api.jar app.jar

EXPOSE 9090

CMD ["java", "-jar", "app.jar"]
