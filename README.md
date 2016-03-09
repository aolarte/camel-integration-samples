# Apache Camel Samples

This is a series of examples on using Apache Camel 

The examples use the Request-Reply pattern and the Claim Check pattern

You can run the examples  directly from Maven:

To run the application using direct wiring of the components, use:

    mvn exec:java -Dexec.mainClass="com.javaprocess.examples.integration.main.Main" -Dexec.args="direct"

To run the application using using a distribuited architecture (but running both the client and server in a single JVM) use:

    mvn exec:java -Dexec.mainClass="com.javaprocess.examples.integration.main.Main" -Dexec.args="client server"
    
To run the server component of the application use:

    mvn exec:java -Dexec.mainClass="com.javaprocess.examples.integration.main.Main" -Dexec.args="server"

To run the client component of the application use:

    mvn exec:java -Dexec.mainClass="com.javaprocess.examples.integration.main.Main" -Dexec.args="client"

The client requires a server instance running. You can have only a single server running (it start an embedded ActiveMQ instance).  You can have multiple clients running at once.
