# Covid Stats Application


### Endpoints
 - GET `/country/{COUNTRYCODE}` - list covid statistics for a particular country by providing 
 - a two-letter country code as a path parameter. (eg. https://localhost:8080/country/BG)

### Build the project using Maven:
 - build the project using Maven - `mvn clean install`
 - run the jar file - `java -jar target/commonProjects-0.0.1-SNAPSHOT.jar`

### Run with Docker

#### Build from Docker file

 - build the project using Maven - `mvn clean install`
 - build the image from the Docker file `docker build . -t covid-statistics-app`
 - run container from newly created image`docker run -d -p 8080:8080 covid-statistics-app`

 OR

#### Build from image in DockerHub

 - run image form docker hub `docker run -d -p 8080:8080 georgiilievvv/covid-statistics-app`