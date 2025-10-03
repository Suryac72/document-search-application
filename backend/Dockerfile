FROM eclipse-temurin:21-jdk AS build
WORKDIR /document-search-application-backend
COPY . .
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /document-search-application-backend
COPY --from=build /document-search-application-backend/target/*.jar document-search-application-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "document-search-application-backend.jar"]
