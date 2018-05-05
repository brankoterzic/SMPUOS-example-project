FROM openjdk:8-jdk-alpine
VOLUME /tmp

ADD ./target/ftn-0.0.1-SNAPSHOT.jar ftn-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /ftn-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/ftn-0.0.1-SNAPSHOT.jar"]
