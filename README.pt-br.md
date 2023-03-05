## Cinema Room REST Service

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/cinema-room-rest-service/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/cinema-room-rest-service/blob/master/README.pt-br.md)

### Descrição do projeto

Este projeto é um serviço RESTful que permite gerenciar uma sala de cinema. É parte do projeto [Cinema Room REST Service](https://hyperskill.org/projects/189) da [JetBrains Academy](https://hyperskill.org/).

### Resultados de aprendizagem

Ao fazer este projeto, aprendi a lidar com requisições HTTP em controladores, criar serviços e responder com objetos JSON — incluindo o emprego de DTOs.

### Tecnologias utilizadas

- Java 17
- Spring Boot 2.5.6
- Lombok
- Insomnia by Kong

### Endpoints da API

GET /seats — retorna todos os assentos da sala de cinema

#### Exemplo
```json 
    {
    "total_rows": 9,
    "total_columns": 9,
    "seats": [
        {
        "row": 1,
        "column": 1,
        "price": 10
        },
        {
        "row": 1,
        "column": 2,
        "price": 10
        },
        {
        "row": 1,
        "column": 3,
        "price": 10
        },
        {
        "row": 1,
        "column": 4,
        "price": 10
        },
        {
        "row": 1,
        "column": 5,
        "price": 10
        },
        {
        "row": 1,
        "column": 6,
        "price": 10
        },
        {
        "row": 1,
        "column": 7,
        "price": 10
        },
        {
        "row": 1,
        "column": 8,
        "price": 10
        },
        {
        "row": 1,
        "column": 9,
        "price": 10
        },
        {
        "row": 2,
        "column": 1,
        "price": 10
        },
        {
        "row": 2,
        "column": 2,
        "price": 10
        },
        {
        "row": 2,
        "column": 3,
        "price": 10
        },
        {
        "row": 2,
        "column": 4,
        "price": 10
        },
        {
        "row": 2,
        "column": 5,
        "price": 10
        },
        ...
        {
       "row": 9,
        "column": 9,
        "price": 8
        }
    ]   
    }
```

POST /purchases — compra um ingresso (se o assento solicitado estiver disponível)

#### Exemplo
```json 
request
{
  "row": 1,
  "column": 1
}
```

```json 
response
{
    "token": "UUID",
        {
            "row": 1,
            "column": 1,
            "price": 10
        }
}
```

POST /return — retorna um ingresso (se o token do ticket enviado for válido)

#### Exemplo
```json 
request
{
    "token": "UUID"
}
```

```json 
response
{
    "returned_ticket":
        {
            "row": 1,
            "column": 1,
            "price": 10
        }
}
```

POST /stats — retorna estatísticas sobre o valor arrecadado, o número de assentos disponíveis e o número de ingressos comprados (se a senha passada for válida)

#### Exemplo
```json 
request
123456
```

```json 
response
{
  "total_income": 0,
  "number_of_available_seats": 81,
  "number_of_purchased_tickets": 0
}
```

### Tratamento de exceções

O tratamento de exceções é feito na classe CinemaControllerAdvice, que está anotada com @RestControllerAdvice.

O programa pode lançar as seguintes exceções durante a execução:
- UnavailableSeatException — durante a compra de um ingresso, se o assento solicitado não estiver disponível ou se os parâmetros row e column forem inválidos
- InvalidTicketException — durante a devolução de um ingresso, se o token de ticket enviado for inválido
- WrongPasswordException — durante a solicitação de estatísticas, se a senha passada na query for inválida

Nesses casos, o programa retorna um objeto JSON com o código de status HTTP 400 e uma mensagem de erro seguindo o seguinte formato:
```json 
{
  "error": "mensagem de erro"
}
```


### Notas adicionais

- A sala de cinema possui 9 filas e 9 colunas de assentos, com um total de 81 assentos.
- As primeiras 4 filas possuem assentos com preço "10" e as últimas 5 filas possuem assentos com preço "8".
- A tokenização de tickets é feita com objetos UUID.
- A senha para acessar as estatísticas é "super_secret" e está visível na classe CinemaService. No entanto, poderia ser armazenada num arquivo de configuração ou numa variável de ambiente.
- Não foi utilizado nenhum banco de dados nesse projeto, mas poderíamos utilizar o Spring Data JPA para persistir os dados em um banco de dados relacional, como o MySQL.


### Contact

Se tiver dúvidas ou sugestões, sinta-se à vontade para entrar em contato comigo através do [LinkedIn](https://www.linkedin.com/in/douglasdotv) ou via email (douglas16722@gmail.com).