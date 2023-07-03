FROM openjdk:17
EXPOSE 9001
ADD target/vendor-service.jar vendor-service.jar
ENTRYPOINT ["java","-jar","/vendor-service.jar"]