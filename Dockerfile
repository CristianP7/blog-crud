FROM eclipse-temurin:21

LABEL author=0303

COPY BackEnd/target/BackEnd-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]