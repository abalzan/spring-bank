This project is based on https://www.udemy.com/course/master-spring-boot-microservices-with-cqrs-event-sourcing course

If you want to run the project locally without docker.compose (docker/springbank/docker-compose.yml) you should start mysql-container,
axon-server and mongo-container, then execute a mvn clean install, then you can run the application 
(probably will need to comment the @Component from the bootstrap class on user-cmd-api, as it will create a user every time you start the server)

# Run application using docker

If you want to run the project using docker, first execute a 
```
mvn clean install -DskipTests 
```

(skipTests would be required here because it will try to connect into the databases that are not up yet)

Run your containers:

```$ docker-compose up```
OR
```$ docker-compose up --build  --force-recreate```

Check all containers are up and running:

```$ docker ps```

For stopping our stack:

```$ docker-compose stop```

if you try to execute "generate Oauth Token" postman call, and you face an error, go to user.cmd.api, and execute:
```$ mvn clean install```

this is required in case of the user-cmd-api starts before mongo-container is up.

#Architecture of the application

This is the flow of the user microservices
![user-microservice.png](user-microservice.png)

This is the flow of the bank account microservices
![bank-account-microservice.png](bank-account-microservice.png)




