# Covid Stats Application
Spring Boot application providing covid statistics for each country. Data is collected from an 
external API (https://api.covid19api.com/summary) and persisted in a H2 in-memory database. 
Data is collected and populated when the application is started plus every hour the data is 
updated with a scheduled task.

### Run with Java:
 - build the project using Maven - `mvn clean install`
 - run the jar file - `java -jar target/commonProjects-0.0.1-SNAPSHOT.jar`

### Run with Docker

#### Run container from newly created image from Docker file

 - build the project using Maven - `mvn clean install`
 - build the image from the Docker file `docker build . -t covid-statistics-app`
 - run container from newly created image`docker run -d -p 8080:8080 covid-statistics-app`

 OR

#### Run container from existing image in DockerHub

 - run image form docker hub `docker run -d -p 8080:8080 georgiilievvv/covid-statistics-app`

### Endpoints
 - GET `/country/{COUNTRYCODE}` - list covid statistics for a particular country by providing
 - a two-letter country code as a path parameter. (eg. https://localhost:8080/country/BG)
