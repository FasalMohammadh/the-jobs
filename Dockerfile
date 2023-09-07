FROM openjdk:17
EXPOSE 8080
ADD target/the-jobs.jar the-jobs.jar
ENTRYPOINT ["java", "-jar", "/the-jobs.jar"]
