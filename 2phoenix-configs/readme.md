1. Use <mvn clean profile> to build a jar file ####
2. run <jar -jar jarname> to deploy the service from command prompt


3. GO to src/main/org.test.demo package 
4. Open DemoApplication.Java it has main method
4. Run the class, it will build and deploy the app in tomcat.

5. Connect this project to Config Server Using **Spring Cloud**

    Server and Client both will need some dependencies to connect to each other-

Client Dependency-
    <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>


It will give the following error if url to config server is not given
    Add a spring.config.import=configserver: property to your configuration.
    If configuration is not required add spring.config.import=optional:configserver: instead.
    To disable this check, set spring.cloud.config.enabled=false or
    spring.cloud.config.import-check.enabled=false.


Add following in application.properties. Don't add in profiles e.g. applications-stg.properties

    #used for activating one profile
    spring.profiles.active=dev
    
    #Used for activating group of profiles
    #spring.profiles.group.dev=dev,stg

Activate profiles at runtime- 
    <java -jar app.jar - -spring.profiles.active=dev,stage>






