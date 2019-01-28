# Platform enablement technical test application

Provides a simple HTTP REST API allowing users to interrogate the application status.

## Build

The PETT is built with gradle, but there is also a script provided to build using Jenkins.
A docker container is provided from https://hub.docker.com/r/clml/pett-build which provides all build dependencies, otherwise you just need a java 8 environment.

To build a single runnable jar file with gradle, from the root of the cloned repository, run
```
./gradlew jar
```
The output will be available in build/libs/

You can also run unit tests with:
```
./gradlew test
```

And generate javadocs with:
```
./gradlew javadoc
```

## Run

To run the built jar, you can also use the same environment as for building (either docker container or other java 8 environment).
The application just takes one argument which is the port number to serve on, or it will run on port 4567 as default:
```
java -jar pett-<version>.jar -p <port number>
```
