Stack - JAVA com o framework Spring Boot

Versão JDK: 17

Versão Spring Boot: 3.2.2

http://localhost:8080/h2-console

http://localhost:8080/api/swagger-ui/index.html

Criar cliente

curl --location 'http://localhost:8080/api/customers' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Railson Martins",
    "document": "123456",
    "type": "PJ",
    "street": "Rua das Bobos",
    "streetNumber": "0",
    "zipCode": "000000000",
    "password": "1234456"
}'

Criar conta

curl --location 'http://localhost:8080/api/accounts' \
--header 'Content-Type: application/json' \
--data '{
    "agency": 123,
    "accountNumber": 123456,
    "customerId": 1
}'

Transação

curl --location 'http://localhost:8080/api/transactions' \
--header 'Content-Type: application/json' \
--data '{
    "sourceAccount": {
        "agency": 1,
        "accountNumber": 2
    },
    "destinationAccount": {
        "agency": 1,
        "accountNumber": 1
    },
    "type": "1",
    "value": 1000
}'