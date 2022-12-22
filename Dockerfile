# Create a docker image base from an alpine linux with openjdk11 installed
FROM azul/zulu-openjdk-alpine:11

# Change the working directory to /opt/app
WORKDIR /opt/app

# Copy jar file
COPY target/*.jar app.jar

# java -jar /opt/app/app.jar
CMD ["java","-jar","app.jar"]