# SpringBootMultiDBApplication
Simple SpringBoot Application Integration with MongoDatabase and MysqlDatabase

## Requirements
Make sure to have the followings installed:

* To run locally
    - Mysql official website ([Mysql](https://www.mysql.com/))
    - MysqlDatabase engine (the version depends on the spring-jpa dependency used within the project see: [Compatibility Matrix](https://spring.io/guides/gs/accessing-data-mysql/))
    - Mongodb official website ([Mongodb](https://www.mongodb.com/))
    - Mongodb engine with spring ([Compatibility Matrix](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/))


* To run with Docker
    - Docker (Ubuntu 22.04 [Installation guide](https://docs.docker.com/engine/install/ubuntu/))
    - Docker-compose (Ubuntu 22.04 [Installation guide](https://docs.docker.com/compose/install/))

## Build and run
* Locally
    - Create a database in MySql `elasticsearch`
    - Run `mvn clean package spring-boot:run` to build the artifact and run the application
    - Run `./elasticsearch` script inside the bin directory of the elastic search package

* Docker
    - Run `docker-compose up --build -d` to run the docker services
 
## Application contains 3 modules:

    1) customer         (mysql)
    2) order            (mysql)
        1) orderDetails (mysql)
    3) product          (mongo)

## Documentation (Swagger)
Visit it [Swagger Application Document](http://localhost:8080/swagger-ui.html) to visualize the exposed API endpoints.

![MultipleDB.drawio.png](src%2Fmain%2Fresources%2Fstatic%2FMultipleDB.drawio.png)


![RestApi.png](src%2Fmain%2Fresources%2Fstatic%2FRestApi.png)
