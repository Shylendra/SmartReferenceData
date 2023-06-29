FROM openjdk:8-jdk-alpine
ARG JAR_FILE=smartreferencedata-web/target/*.jar
COPY ${JAR_FILE} /usr/app/
WORKDIR /usr/app/
RUN sh -c 'touch smartreferencedata-api.jar'
ENTRYPOINT ["java","-jar","smartreferencedata-api.jar","--spring.profiles.active=aws"]

