FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY . .

RUN apk add --no-cache maven

RUN mvn clean package -DskipTests

COPY ./src/main/resources/application-prod.properties /app/application-prod.properties

CMD ["java", "-jar", "/app/target/api-0.0.1-SNAPSHOT.jar"]
