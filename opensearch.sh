#!/bin/bash

npm install

docker-compose up --build 

echo "CURL for creating index in OpenSearch"

curl --location 'http://localhost:8080/api/index' \
     --header 'Content-Type: application/json' \
     --data '{
        "documentTitle": "Forrester",
        "documentContent": "Spring boot World",
        "documentAuthor": "Me",
        "documentCategory": "test"
     }'

