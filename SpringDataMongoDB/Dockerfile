FROM openjdk:8-jdk-alpine
VOLUME /tmp

ADD ./target/SpringDataMongoDB-0.0.1-SNAPSHOT.jar SpringDataMongoDB-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /SpringDataMongoDB-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/SpringDataMongoDB-0.0.1-SNAPSHOT.jar"]
