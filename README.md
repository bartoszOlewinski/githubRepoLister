# Github Repository Listing Tool

<i>This project uses Quarkus, the Supersonic Subatomic Java Framework.</i>

There are three endpoints present in this application.

<b>/github/users/{login}/repos/TEST</b> - testing endpoint for listing public repositories of given user

<b>/github/repos/{login}/{repository}/branches/TEST</b> - testing endpoint for listing branches given a repo 
name and an owner

<b>/github/repositories/{login}</b> - endpoint that responds with "Uni < T >" list that contains every public repository
of a user given a username, alongside a pair of every branch name and its latest commits sha.
Given non-existing login, user receives a response in format:

    “status”: ${responseCode}

    “message”: ${whyHasItHappened}





## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```



## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.