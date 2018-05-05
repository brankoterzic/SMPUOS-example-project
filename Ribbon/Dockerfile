FROM openjdk:8-jdk-alpine
VOLUME /tmp

ADD ./target/RibbonService-0.0.1-SNAPSHOT.jar RibbonService-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /RibbonService-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/RibbonService-0.0.1-SNAPSHOT.jar"]
