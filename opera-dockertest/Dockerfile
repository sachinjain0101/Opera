FROM openjdk:8-jdk-alpine
ARG JAR_FILE
ADD ${JAR_FILE} /target/opera-dockertest.jar
ADD target/app.properties /target/app.properties

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/target/opera-dockertest.jar"]