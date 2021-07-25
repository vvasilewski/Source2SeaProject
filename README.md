# Backend Coding Challenge
## _project for Source2Sea_
#
#



The project is a backend coding challange for Source2Sea, where it is needed 
to provide e-commerce API with endpoint that performs a checkout action. 



## Features

- Take a list of watches and return the total cost
- Show list of all available watches





## Tech

The project uses a number of technologies as:

- [Java12] - main programming language in the app 
- [Spring Boot] - Spring based Applications to "just run"
- [Spring Rest] - RESTful Web Service
- [MySQL] - Oracle DataBase
- [Hibernate] - Object Relational Mapping (ORM) framework
- [Maven] - software project management and comprehension tool 
- [Postman] - collaboration platform for API development 
- [Spring Security] - authentication request (ToDo) 
- [JUnit] - tests (ToDo) 
#
#



## Installation

Project requires Java12 and Maven.

Install the dependencies in main directory and start the SpringBoot application from target directory.

```sh
cd directory
mvn clean install
cd ..
cd target
java -jar products-0.0.1-SNAPSHOT.jar
```


## Database

Dillinger is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.




| id  | product_name  | unit_price  | discount  |
|---|---|---|---|
| 001 | Rolex  | 100.00  |  3,100 |
| 002 | Michael Kors  | 80.00  |  2,40 |
| 003 | Swatch  |  50.00 | null  |
| 004 | Casio | 30.00  | null  |


Sql script for creating the table:
```sh

CREATE DATABASE  IF NOT EXISTS `product_directory`;
USE `product_directory`;

--
-- Table structure for table `product_x`
--

DROP TABLE IF EXISTS `product_x`;

CREATE TABLE `product_x` (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `discount`  varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `product_x`
--

INSERT INTO `product_x` VALUES 
	(001,'Rolex',100 ,'3,100'),
	(002,'Michael Kors',80,'2,40'),
	(003,'Swatch',50 ,null),
	(004,'Casio',30 ,null);

```


#
#
#
#### Build and deploy
ToDo
#

#### Tests
ToDo
#

#
#### Security
ToDo
#
#

## Usage of the application

As it is web application it is necessary to use a web browser.
It was tested with Chrome, but should work with other ones as well.

##### There are two endpoints: 
#
#
#
```sh
http://localhost:8080/checkout
```
The first one displays all the watches available in database. 

#
#
```sh
http://localhost:8080/checkoutPrice
```

The second one displays price of items requested by RequestBody.
For this action it is needed to use additional tool as Postman, where it is available to create rest body.

Example:
```sh
["001",
"002",
"001",
"004",
"003",
"001",
"002"
]
```
The action should be send as POST method.

In the code there is implementation which calculate also discounts for the products.
Code gets the information from discount column in db.
Example: 3,100
The first one is a number of items when it is necessary to use discount. 
Second value is amount of price which should be subtracted.
In the case there are 6 items of Rolex the final value will be subtracted by 200.







#
#
## Michal Wasilewski

postbox.wasilewski@gmail.com



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [Spring Boot]: <https://spring.io/projects/spring-boot>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [Spring Rest]: <https://spring.io/guides/gs/rest-service/>
   [MySQL]: <https://www.mysql.com/>
   [Hibernate]: <https://hibernate.org/orm/>
   [Maven]: <http://maven.apache.org/>
   [Postman]: <www.postman.com>
   [Java12]: <https://www.oracle.com/pl/java/technologies/javase/jdk12-archive-downloads.html>


   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
