FROM java:8-jdk-alpine
COPY ./target/prime-checker-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 5580
ENTRYPOINT ["java", "-jar", "prime-checker-0.0.1-SNAPSHOT.jar"]