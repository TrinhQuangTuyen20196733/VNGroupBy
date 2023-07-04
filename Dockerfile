FROM openjdk:18-alpine
EXPOSE 8082
ADD target/vngroupby.jar vngroupby.jar
ENTRYPOINT ["java","-jar","/vngroupby.jar"]