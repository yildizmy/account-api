

## How to test?

### API Endpoints

Swagger page for the endpoints: [Account Rest APIs](http://localhost:8080/swagger-ui/index.html)

> **Note** All URIs are relative to *http://localhost:8080/api/v1*



| Class             | Method                                                   | HTTP request             | Description                                                                                       |
|-------------------|----------------------------------------------------------|--------------------------|---------------------------------------------------------------------------------------------------|
| *AccountController* | [**findAll**](http://localhost:8080/api/v1/accounts) | **GET** /accounts    | Retrieves account by the given id                                                                 |
| *AccountController* | [**create**](http://localhost:8080/api/v1/accounts)       | **POST** /accounts        | Opens a new current account for the given customer                                                                                |



<br/>



| Class                | Method                                                               | HTTP request                       | Description                        |
|----------------------|----------------------------------------------------------------------|------------------------------------|------------------------------------|
| *CustomerController* | [**findById**](http://localhost:8080/api/v1/customers/{id}) | **GET** /customers/{id}    | Retrieves customer by the given id |
| *CustomerController* | [**findAll**](http://localhost:8080/api/v1/customers)    | **GET** /customers    | Retrieves all customers           |
| *CustomerController* | [**create**](http://localhost:8080/api/v1/customers) | **POST** /customers   | Creates a customer                 |


<br/>


| Class                | Method                                                          | HTTP request                 | Description                                         |
|----------------------|-----------------------------------------------------------------|------------------------------|-----------------------------------------------------|
| *TransactionController* | [**findById**](http://localhost:8080/api/v1/transactions/{id})   | **GET** /transactions/{id}    | Retrieves all transactions by the given customer id |


<br/>



### Postman Requests

The following request examples can be modified and used for testing the endpoints. [Postman Collection](postman/account_api.postman_collection.json) is also shared in the resources and can be used.


### account-controller


* create

```
http://localhost:8080/api/v1/accounts/
```

```
{
    "customerId": 101,
    "balance": 1000
}
```

* findAll

```
http://localhost:8080/api/v1/accounts/
```

<br/>







### customer-controller


* create

```
http://localhost:8080/api/v1/customers/
```

```
{
    "name": "Niels",
    "surname": "Westerbeek",
    "email": "niels.westerbeek@gmail.com"
}
```

* findAll

```
http://localhost:8080/api/v1/customers/
```

<br/>





### transaction-controller


* findAllByCustomerId

```
http://localhost:8080/api/v1/transactions/:101
```

<br/>


<br/>
<br/>