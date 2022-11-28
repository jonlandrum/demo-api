Example Web API
===============

This project serves as an example web API for retrieving data from multiple sources, aggregating the data within a database, and serving the resulting aggregate over a REST-ful API.

Dependencies
------------
* Java JDK 17
* Gradle
* A Java IDE that works with Gradle, such as IntelliJ IDEA or Eclipse
* Git CLI or GUI if you intend to clone the repository, otherwise you can download the project as a ZIP file and unzip it manually

Running The Code
----------------
* Clone the repository to your local computer, or alternatively download the ZIP archive and manually unzip it
* Open the project in IntelliJ IDEA, Eclipse, or your Java IDE of choice
* Click the "Run" button in the IDE interface to start the webserver

The site data can be accessed through a web browser or with an API testing application such as Postman by accessing the address `localhost:8080/<username>`.

Details about the API
---------------------

The data used in this example are provided by GitHub:
* https://api.github.com/users/<username>
* https://api.github.com/users/<username>/repos

Any account can be polled via these APIs if the username is known. This same username is all that is required to utilize this API, so in the address for the API, the placeholder `<username>` should be replaced with a GitHub username, such as the "octocat" demo account:
```
localhost:8080/octocat
```
The data provided by these two APIs are aggregated and the following information is provided as a JSON object:
* The username used for polling the API
* The display name for the account
* The address of the account's avatar
* The account's geolocation
* The account's email address
* The account's profile address
* The date/time the account's account was created
* A JSON array of the account's repositories, each containing the following information:
    * The repository Name
    * The repository URL

An example JSON object returned for the account "octocat" would be:
```json
{
  user_name: "octocat",
  display_name: "The Octocat" ,
  avatar: "https://avatars3.githubusercontent.com/u/583231?v=4 ",
  geo_location: "San Francisco",
  email: null,
  url: "https://github.com/octocat ",
  created_at: "2011-01-25 18:44:36",
  repos: [
    {
      name: "boysenberry-repo-1" ,
      url: "https://github.com/octocat/boysenberry-repo-1 "
    }, ...
  ]
}
```
If there is no account found for the given input data, an empty JSON object is returned: `{}`.

The API makes use of a database for caching data in case of excessive requests. By default, the data is cached for a period of one minute before a new request is made to the GitHub APIs to check for changes. This time period can be modified in the application config class.

Design Decisions
----------------
* The language used is Java 17
* The Spring Boot library was included in order to help with rapid prototyping of the API
* The Hibernate ORM framework was included in order to make persistence easier
* The initial project was created using [Spring Initializr](https://start.spring.io)
* Dependencies added:
    * *Spring Data JPA*: this was included in order to have the Java persistence API and Hibernate added to the build
    * *Spring Web*: this was included in order to have the Java MVC framework and Tomcat added to the build
    * *Spring Boot Dev Tools*: this was included in order to have fast restarts to make the prototyping process quicker
    * *Apache Camel*: this was added in order to make working with separate design domains easier
    * *Apache Derby Database*: this was included in order to have access to the Apache Derby database
* Derby was chosen because it can be distributed in its entirety using only Gradle, removing the requirement of downloading and setting up a database server before running the program, and because of its tight integration with the Spring framework

Resources Used
--------------
* https://start.spring.io
* https://spring.io/guides/gs/accessing-data-mysql/
* https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/
* https://springhow.com/derby-database-for-spring-boot/
* https://hevodata.com/learn/spring-boot-rest-api/