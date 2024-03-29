### TRANSPORT MARKET #

Simple app that allows you to organize transportation of your loads.

App is written in Java language on back-end and with HTML and CSS on front-end. There is a possibility to register user and then login to the app. On the main page there is a list of load's details. You can contact load's owner via email to make business contract. App also contains list of all customers and Yours profile basic data. 

### Technologies : #

- Java 17
- Docker (last stable version)
- Spring Boot 2.7.16
- Maven 3.9.2


### Installation : #

- Run PostgreSQL database with docker by using the following command:

```
- docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=YOURPASSWORD postgres:15.1
```

#### Creating Postgres roles :

- Run SQL commands:

```
CREATE USER dev WITH PASSWORD 'dev';
CREATE USER test WITH PASSWORD 'test';
```

#### Creating databases
- Run SQL commands:

```
CREATE DATABASE transportmarket;
CREATE DATABASE transportmarket_test;
```
### Run : #

There are two profiles for app development and testing:
- dev
- test

To run application for development purpose use VM option:
```
-Dspring.profiles.active=dev
```

To run application for testing purpose use VM option:
```
-Dspring.profiles.active=test
```

MIT License
Copyright (c) 2023 Witold Brzeziński

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.




