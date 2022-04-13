FROM openjdk:11
COPY ./taco-cloud-0.0.1-SNAPSHOT.jar /taco-cloud.jar
EXPOSE 8080
CMD ["java", "-jar", "/taco-cloud.jar"]