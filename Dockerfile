FROM openjdk:11-jre-alpine3.9
COPY ./staging/taco-cloud-0.0.1-SNAPSHOT.jar /taco-cloud.jar
CMD ["java", "-jar", "/taco-cloud.jar"]