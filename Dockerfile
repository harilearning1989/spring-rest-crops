FROM openjdk:11
LABEL maintainer="harilearning1989.net"

# Refer to Maven build -> finalName
ARG JAR_FILE=build/libs/spring-rest-crops.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} spring-rest-crops.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","spring-rest-crops.jar"]