# RBCAssignment
Build an asynchronized data integration with Spring Boot, Kafka and a database

## Requirements:
1. docker
2. docker-compose

## Asumptions:
  1. The Address to Client is a one to many relation. Meaning one address can have several clients, but every client has only one address.
  2. When editing a user, will create a new user and address if does not exists.

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
  3. There is no feedback from the consumer to the producer if such client already exists in the db, and it is the consumer responsibility to check if exists. If exists will update the records, otherwise will create a new Address and Client records.
  
  To implement such a feedback, using a framework for DDD with CQRS, such as Axon, and Kafka as the event store, would have provided a better stack.

### Consumer
1. Consumer has 2 tables, addresses and clients, as seen in the following image:
