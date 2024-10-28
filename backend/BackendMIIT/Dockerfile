FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/BackendMIIT-0.0.1-SNAPSHOT.jar insideView.jar
ENTRYPOINT ["java", "-jar", "/insideView.jar"]