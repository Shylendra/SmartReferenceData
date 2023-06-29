FROM openjdk:8
EXPOSE 8083
ADD smartreferencedata-web/target/smartreferencedata-api.jar smartreferencedata-api.jar
ENTRYPOINT ["java","-jar","/smartreferencedata-api.jar"]

