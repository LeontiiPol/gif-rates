# Gif-rates project

Below you can see the short description of the project, stack and instructions to run project 
via Docker. To see detailed API documentation run the project and open swagger as instructed below.

## What is Gif-rates?

Gif-rates is a RESTful service 
that accepts a currency code, compares yesterday's and today's 
exchange rates of this currency against USD and returns a random 
GIF of a certain theme depending on the comparison result.
If currency exchange rate has risen then random "rich" GIF 
is returned from *https://giphy.com/search/rich*.
Otherwise, service returns "broke" GIF from here *https://giphy.com/search/broke*.

Two third-party APIs are used in this project:
+ [openexchangerate](https://docs.openexchangerates.org/) - used to get information about currencies 
  and their exchange rates.
+ [giphy](https://developers.giphy.com/docs/api#quick-start-guide) - used to get GIFs.

## Stack

+ SpringBoot 2.7.0
+ Spring Web
+ Spring Cloud (OpenFeign)
+ Gradle
+ Docker
+ JUnit 5
+ Mockito
+ Lombok
+ Swagger

## Run using Docker

1. clone this repo
2. open the terminal in the project root folder (gif-rates)
3. run `docker build -t gif-rates:latest .`
4. run `docker run --name gif-rates -p 8082:8082 -d gif-rates:latest`
5. open *http://localhost:8082/*
6. choose currency code from drop-down element
7. click `Get Rate` button
8. to see API documentation open *http://localhost:8082/swagger-ui/*
