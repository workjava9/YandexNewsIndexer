FROM eclipse-temurin:21-jdk
VOLUME /tmp
COPY build/libs/yandexnewsindexer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
