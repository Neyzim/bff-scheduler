FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTest
FROM bellsoft/liberica-runtime-container:jdk-17-musl
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8083

CMD ["java", "-jar", "app.jar"]
