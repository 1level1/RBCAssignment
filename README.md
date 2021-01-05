# RBCAssignment
Build an asynchronized data integration with Spring Boot, Kafka and a database

## Requirements:
1. docker
2. docker-compose

## Usage
### Producer
1. The producer has 2 API`s:
  1. localhost:8090/add-new-client
  2. localhost:8090/edit-else-new-client
  
  Where an example for sent body is the following json:
  ```json
  {
        "addressline" : "addressline",
        "city": "city",
        "state": "state",
        "postcode": "postcode",
        "id": "id",
        "firstname": "firstname",
        "networth": 100.0,
        "lastname": "lastname"
  }
  ```
  3. Since this is a simple task, there is no feedback from the consumer if such client already exists in the db, and it is the consumer responsibility to check if exists. If exists will update the records, otherwise will create a new Address and Client records.

### Consumer
