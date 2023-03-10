FROM maven:3.8.3-openjdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/redesocial-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT  ["java", "-jar", "/app/redesocial-0.0.1-SNAPSHOT.jar"]
