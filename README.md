# Platform enablement technical test application

The Platform enablement technical test application (or PETT) is a small java application that provides a simple HTTP API that can be used to interrogate information about the application.

## Building

The PETT is built with gradle, but there is also a script provided to build using Jenkins.
A docker container is provided from https://hub.docker.com/r/clml/pett-build which provides all build dependencies, otherwise you just need a java 8 environment.

To build a single runnable jar file with gradle, from the root of the cloned repository, run
```
./gradlew jar
```
The output will be available in build/libs/.
You can also run unit tests with:
```
./gradlew test
```
And generate javadocs with:
```
./gradlew javadoc
```
To run any of these tasks within the docker container (requires docker to be installed first), run:
```
docker run --rm -v $(pwd):/workdir -w /workdir -u ${UID}:${GID} clml/pett-build bash -c "./gradlew <action>"
```
If you have a jenkins installation, you can build the application using the Jenkinsfile located in the root of the repository, which uses the docker container to build but has no other dependencies otherwise.
Note, the build version uses the environment variable \'BUILD_NUMBER\' as the build number in the version, otherwise the build number will be \'null\'. This is defined by the jenkins environment, or you will have to define this in your environment yourself.

## Usage

### Running the application

To run the application, you can also use the same environment as for building (either docker container or other java 8 environment).
The application just takes one argument which is the port number to serve on, or it will run on port 4567 as default:
```
java -jar pett-<version>.jar -p <port number>
```
Or to run in the docker container:
```
docker run --rm -p <port>:<port> -v $(pwd):/appdir -w /appdir -u ${UID}:${GID} clml/pett-build bash -c "java -jar pett-<version>.jar -p <port>"
```

### HTTP API

The application HTTP API can be easily used with most web browsers or you can programmatically access with your favourite HTTP request library. The API provides 3 functions: A simple root endpoint returning a welcome message; a metadata endpoint returning build info and descriptive data about the application; and a health endpoint returning a health status of the current application.
The requests are simple HTTP GET requests to the endpoint paths, which are detailed below:
* /
  - Returns a simple plain text message of 'Welcome to PETT!'
* /metadata
  - Returns JSON with the description of the build, the commit hash that the build was built from, and the version number
* /health
  - Returns Health JSON with a status describing the health. If the health status is not 'PASS', consider increasing the memory available to the JVM

If you are running the server locally on the default port, you can just follow the links below in your web browser:
* http://127.0.0.1:4567/
* http://127.0.0.1:4567/metadata
* http://127.0.0.1:4567/health

## Limitations and caveats

The main limitation of this application is that it does not implement any security, and as such should not be used on a publically available system directly. I would advise to at least use HTTPS and basic authentication which can be provided with an apache redirect or otherwise. You could extend the application to support this natively as well.

## Related third party libraries

All libraries are automatically imported and packaged into the jar by gradle
* JUnit:4.12
* Jcabi-manifests:1.1
* Slf4j-api:1.7.25
* Slf4j-simple:1.7.25
* Spark-core:2.7.2
* Gson:2.8.5
* Apache commons-cli:1.3.1
