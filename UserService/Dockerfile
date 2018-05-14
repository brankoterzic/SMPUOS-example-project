FROM openjdk:8-jdk-alpine
VOLUME /tmp

ADD target/UserService-0.0.1-SNAPSHOT.jar UserService-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /UserService-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/UserService-0.0.1-SNAPSHOT.jar"]

