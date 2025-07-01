
FROM maven:3.8.8-jdk-17 AS build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw \
  && ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw package -DskipTests -B


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
