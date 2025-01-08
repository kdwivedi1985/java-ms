GO to src/main/org.test.demo package 
Open DemoApplication.Java it has main method
Run the class, it will build and deploy the app in tomcat.

##########Create Jar and run from command prompt########
1. Use <mvn clean profile> to build a jar file ####
2. run <jar -jar jarname> to deploy the service from command prompt


GET
http://localhost:8080/hello

POST
http://localhost:8080/api/v1/users/adduser
{
"userid": 1234,
"username": "kdwivedi"
}

GET
http://localhost:8080/api/v1/users/map/1234
http://localhost:8080/api/v1/users/map/1234


Added Swagger dependecy and config to enable Swagger. Try- http://localhost:8080/swagger-ui/index.html

Following line in logback-spring.xml -send log messages from a Java application to a Grafana Loki server running on the 
local machine at port 3100
    <appender name="LOKI" class="com.github.loki4j.logback.Loki4jAppender"> <url>http://localhost:3100/loki/api/v1/push</url>


