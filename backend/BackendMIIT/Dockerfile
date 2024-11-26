FROM maven:3.9-eclipse-temurin-17 AS maven
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY --from=maven /app/target/*.jar inside-view.jar
ENTRYPOINT ["java", "-jar", "inside-view.jar"]
