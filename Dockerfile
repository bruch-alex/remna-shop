# ---- Build stage ----
# FROM bellsoft/liberica-runtime-container:jdk-25-musl AS build
FROM bellsoft/liberica-runtime-container@sha256:befb4b0040b0c5e1c0f1b6fc7c6b7bd9f4e7b7e2ea41ff1e93348ac8335dfdb3 AS build

WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ---- Runtime stage ----
# FROM bellsoft/liberica-runtime-container:jre-25-slim-musl
FROM bellsoft/liberica-runtime-container@sha256:7206823d32fed057dbef030575862f6da2248b319fb60f687a931cbbd736b52b

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]