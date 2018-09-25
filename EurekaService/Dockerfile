FROM openjdk:8-jdk-alpine
VOLUME /tmp

ADD ./target/EurecaService-0.0.1-SNAPSHOT.jar EurecaService-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /EurecaService-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/EurecaService-0.0.1-SNAPSHOT.jar"]
