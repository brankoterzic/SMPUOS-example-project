FROM openjdk:8-jdk-alpine
VOLUME /tmp

ADD ./target/ZuulService-0.0.1-SNAPSHOT.jar ZuulService-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /ZuulService-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/ZuulService-0.0.1-SNAPSHOT.jar"]
