FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8090
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
