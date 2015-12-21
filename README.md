# Introduction

This Carpooling application was developed to, as its name implies, provide carpool service for teacher in TJU between old and new campus.

Actually it's a course exercise, but we are trying to make it more professional.

# Overview

Included with this application is an example of the optional DB API module. The examples provided illustrate a few of
the features available in [Hibernate](http://hibernate.org/), along with demonstrating how these are used from within
Dropwizard.

This database example is comprised of the following classes:

* The `PersonDAO` illustrates using the Data Access Object pattern with assisting of Hibernate.

* The `Person` illustrates mapping of Java classes to database tables with assisting of JPA annotations.

* All the JPQL statements for use in the `PersonDAO` are located in the `Person` class.

* `migrations.xml` illustrates the usage of `dropwizard-migrations` which can create your database prior to running
your application for the first time.

* The `PersonResource` and `PeopleResource` are the REST resource which use the PersonDAO to retrieve data from the database, note the injection
of the PersonDAO in their constructors.

As with all the modules the db example is wired up in the `initialize` function of the `HelloWorldApplication`.

# Running The Application

To test this application run the following commands.

* To package the example run.

        mvn package

* To run the server run.

        java -jar target/carpooling-1.0.jar server carpooling.yml

* To hit the Hello World example (hit refresh a few times).

	http://localhost:12306

