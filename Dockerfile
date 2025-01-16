# ---------- Build Stage ----------
FROM maven:3.9.9-amazoncorretto-17-alpine AS build
WORKDIR /app

# Copy project files
COPY . .

# Package the application as a WAR
RUN mvn clean install -DskipTests

# ---------- Deployment Stage ----------
FROM tomcat:10-jdk17

# Expose Tomcat's default port
EXPOSE 8080

# Clean the default webapps directory
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file to Tomcat
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Start Tomcat
CMD ["catalina.sh", "run"]
