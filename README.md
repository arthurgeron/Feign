# HTTP Requests, Load Balance and RabbitMQ Examples

 Jersey
 ---
 Role: Http Server
 
 Classes: App, Voip
 
 -Runs an local API on the port 8080 (i.e localhost:8080) with the following directories:
 
 /api/call (GET) - Returns a JSON
 
 /api/users (POST)

 Feign (Http Client) + Ribbon ( Load Balance ) + Guice ( Dependency Injector)
 ---
 Roles: Feign as Http Client, Ribbon for Load Balancing and Guice for Dependency Injection
 
 Classes: IClient, Client
 
 -Runs a Http Client that will send requests to the two directions mentioned before.
 
 To Test: Run App's main method, then run the ClientTest;

 RabbitMQ (Message Broker)
 ---
 Roles: In a real scenario it would be used to integrate services, in this case we do a simple Hello World to
 test it's concept.
 
 Classes: RabbitMQ*
 
 To Test: Run main method in RabbitMQWorker, then run RabbitMQTest class

 Location of main methods
 ---

 Packages needed: `oracle-java8-installer, maven, rabbitmq-server`
