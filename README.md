Link to original [repository](https://github.com/perseusEngineering/candidate-coding-challenges/tree/master/backend-challenges/user-service)

The objective is to implement a rest-service which is able to:

* [x] create new user with contact data
* [x] return user by id
* [x] return user by name
* [x] add additional mail/phone data
* [x] update existing mail/phone data
* [x] delete user

The data objects are defined as followed:
```
User:
    id: <int>
    lastName: <string>
    firstName: <string>
    emails: List<Email>
    phoneNumbers: List<PhoneNumber>

Email:
    id: <int>
    mail: <string>
    
PhoneNumber:
    id: <int>
    number: <string>
```

#### Build and run
Prerequisites: you ought to have git and java 11 installed

Clone project into you workspace:

 ``` git clone https://github.com/tronez/uservice-task.git```

Run terminal, change to root directory of project and write:
 
 ```gradlew build```
 
 Then write this into terminal
 
 ```gradlew bootRun --args='--spring.profiles.active=local'```
 

#### API Documentation

After starting application you can check out a documentation here: 

http://localhost:8080/swagger-ui.html

#### Constraints
* you provide a straight forward documentation how to build and run the service
* submitted data is stored in a database (free choice which one)
* free choice of following programming languages: Scala, Java, Python

#### Bonus
* you let your service run within a container based environment (Docker, Kubernetes)
* you provide a documentation of your services API endpoints
* your service is covered with tests
