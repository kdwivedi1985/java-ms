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

