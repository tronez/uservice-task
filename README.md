Link to original [repository](https://github.com/perseusEngineering/candidate-coding-challenges/tree/master/backend-challenges/user-service)

The objective is to implement a rest-service which is able to:

* [x] create new user with contact data
* [x] return user by id
* [x] return user by name
* [x] add additional mail/phone data
* [x] update existing mail/phone data
* [x] delete user

#### Build and run
Clone project into you workspace:

 ``` git clone https://github.com/tronez/uservice-task.git```

Run terminal, change to root directory of project and enter:
 
 ```gradlew bootRun --args='--spring.profiles.active=local'```
 
#### Build and run with docker
Clone project and enter:

 ```docker build . -t my-app```
 
 To run image
 
  ```docker run -p 8080:8080 my-app```
#### API Documentation

After starting application you can check out a documentation here: 

http://localhost:8080/swagger-ui.html

#### Constraints
* [x] you provide a straight forward documentation how to build and run the service
* [x] submitted data is stored in a database (free choice which one)

#### Bonus
* [x] you let your service run within a container based environment (Docker, Kubernetes)
* [x] you provide a documentation of your services API endpoints
* [x] your service is covered with tests
