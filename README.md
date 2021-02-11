# nbpApi

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Usage](#usage)

## General info
Currency converter; enables converting certain currencies as well as checking exchange rates. Data is kept in MySQL Database.
\
Swagger UI was built to the project, so that it is easier to understand what endpoints are available.
  
## Technologies
Project is created with:
* Java 8
* Spring Boot
* Maven
* MySQL database
* NBP API
* Docker
* Swagger
	
## Installation
To run this project, install it locally:

```
$ git clone git@github.com:hofman-iga/nbpApi.git

```

## Usage

End points available in application: \ 

GET
/nbp/all \
Displays all currencies and exchange rates available in API.

GET
/nbp/available \
Displays all currencies codes available in API.

GET
/nbp/count/{code1}/{code2}/{amount} \
Convert chosen currency to another based on current exchange rate.\
       - code1 - currency code to be converted \
       - code2 - currency code to convert to\
       - amount - amount of currency to be exchanged\
