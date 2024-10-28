FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/BackendMIIT-0.0.1-SNAPSHOT.jar inside-view.jar
ENTRYPOINT ["java", "-jar", "/inside-view.jar"]
