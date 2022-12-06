FROM maven:3.6-jdk-11 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM adoptopenjdk/openjdk11
COPY --from=build app/target/onlinestore-0.0.1-SNAPSHOT.jar /app/application.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/application.jar"]