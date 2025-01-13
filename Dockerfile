FROM 3.9.9-eclipse-temurin-21-jammy as BUILD_IMAGE
RUN mvn install

FROM tomcat:10-jdk21
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=BUILD_IMAGE bank-administrative-system-api/target/