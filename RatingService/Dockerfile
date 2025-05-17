FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} ratingservice.jar

ENTRYPOINT [ "java","-jar","/ratingservice.jar"]

