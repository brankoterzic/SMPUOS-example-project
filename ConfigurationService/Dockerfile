FROM openjdk:8-jdk-alpine
VOLUME /tmp

ADD target/ConfigurationService-0.0.1-SNAPSHOT.jar ConfigurationService-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /ConfigurationService-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/ConfigurationService-0.0.1-SNAPSHOT.jar"]

