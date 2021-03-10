FROM openjdk:11
WORKDIR /app
COPY target/Trabalho-SpringBoot-0.0.1-SNAPSHOT.jar /app/api-springboot.jar
ENTRYPOINT ["java", "-jar" , "api-springboot.jar"]