# RBCAssignment
Build an asynchronized data integration with Spring Boot, Kafka and a database

## Requirements:
1. docker
2. docker-compose

## Asumptions:
  1. The Address to Client is a one to many relation. Meaning one address can have several clients, but every client has only one address.
  2. When editing a user, will create a new user and address if does not exists.

## Build:
  For convinience, there is a buildMe.sh script at the root dir
  
  Usage instructions are:
  * cd <rootdir>
  * source buildMe.sh
  This will compile, build dockers for consumer and producer and run docker-compose for the system:
  1. consumer
  2. producer
  3. kafka
  4. zookeeper
  5. schema-registry
 
## Usage
### Producer
1. The producer has 2 API`s:
  1. POST on localhost:8090/add-new-client
  2. PUT on localhost:8090/edit-else-new-client
  
  (Although both of them will do the same, as mentioned in the assumption section)
  
  A sent msg body must have the following json structure:
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
1. Consumer has 2 tables, addresses and clients, as can be seen in the image bellow
2. Consumer database is h2, an easy connect msg is 
![alt text](https://github.com/1level1/RBCAssignment/blob/main/consumerTables.png?raw=true)
