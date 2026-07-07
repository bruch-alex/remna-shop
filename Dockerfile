# ---- Build stage ----
# FROM bellsoft/liberica-runtime-container:jdk-25-musl AS build
FROM bellsoft/liberica-runtime-container:sha256:4ad1a75eb2b91358794196e418e9ea3cd3972de4a52108c5851b7d5c92a7fc70 AS build

WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ---- Runtime stage ----
# FROM bellsoft/liberica-runtime-container:jre-25-slim-musl
FROM bellsoft/liberica-runtime-container:sha256:0fa7f4b12f2e465556f6021a30cf2e5d922f818e2ad5d13cf9797f86d55dddaa
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]