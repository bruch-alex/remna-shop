# ---- Build stage ----
FROM bellsoft/liberica-runtime-container:jdk-25-musl AS build

WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ---- Runtime stage ----
FROM bellsoft/liberica-runtime-container:jre-25-slim-musl
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]