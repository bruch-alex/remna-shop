1. First:
```dockerfile
# ---- Build stage ----
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ---- Runtime stage ----
FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

results
```
remnashop-app   0.20%     269MiB / 15.3GiB     1.72%     22.5kB / 18.9kB   64.2MB / 8.19kB   48
remnashop-db    0.02%     44.36MiB / 15.3GiB   0.28%     29.4kB / 16.6kB   51.1MB / 156MB    19
```