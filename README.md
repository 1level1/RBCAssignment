# RBCAssignment
Build an asynchronized data integration with Spring Boot, Kafka and a database

## Requirements:
1. docker
2. docker-compose

## Asumptions:
  1. The Client to Address is a many to one relation. Meaning every client has only one address.
  2. When trying to edit a non existing client, will create a new client and address if does not exists.
  3. Editing an existing client with some change in the address fields will result in creating a new address and assigning it to user.
  4. Adding a new client, with address already exists (compared by all fields) will assign that address to client.

## Build:
  For convinience, there is a buildMe.sh script at the root dir
  
  Usage instructions are:
  * cd <rootdir>
  * source buildMe.sh
  
  This will compile, build dockers for consumer and producer and run docker-compose for the system:
  1. consumer - localhost:8090
  2. producer - localhost:8099
  3. kafka - localhost:29092
  4. zookeeper - localhost:2181
  5. schema-registry - localhost:8081
 
## Usage
### Producer
1. The producer has 2 API`s:
  1. POST on localhost:8090/add-new-client
  2. PUT on localhost:8090/edit-else-new-client
  
  Although both of them will do the same - create user and update used if a request with same id is called (As mentioned in the assumptions).
  
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
  3. For integration tests and playing with the system, postman (https://www.postman.com/) is very usefull (locally).
  4. There is no feedback from the consumer to the producer if such client already exists in the db, and it is the consumer responsibility to check if exists. If exists will update the records, otherwise will create a new Address and Client records.
  
  To implement such a feedback, using a framework for DDD with CQRS, such as Axon, and Kafka as the event store, would have provided a better stack.

### Consumer
1. Consumer has 2 tables, addresses and clients, as can be seen in the image bellow
2. Consumer database is h2, an easy connect method is to use h2-console, which will run on consumer side - http://localhost:8099/h2-console/

**Important** Change "JDBC URL" to: jdbc:h2:mem:testdb


![alt text](https://github.com/1level1/RBCAssignment/blob/main/consumerTables.png?raw=true)
