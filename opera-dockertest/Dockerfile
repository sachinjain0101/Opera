FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /target/opera-dockertest.jar
COPY /target/app.properties /target/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/target/opera-dockertest.jar"]