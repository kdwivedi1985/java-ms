1.run <config-server> jar before running this
2. run 4price-service prior to that 
3. install redis befor this
4. running server from project has issue, create jar using <mvn clean package> 
5. deploy the jar using java -jar <jar name>

In AppConfig.java- Read host and port from API Gateway and give name of the microservice so it recognizes the name of the service
we want to hit . e.g. 4price-service.
config repo must have either yml or properties file name e.g. product-service.yml


This service will be invoked by client and will call price-service
Port is 8091

/getproduct-price service is working is you want to test

Mono-> single stream 
Flux-> Stream of Data

1. Hit http://localhost:8090/api/v1/async-pricehealth,
    it will return "price_status_cookie" and value in response
2. Make a subsequent request by adding "price_status_cookie" and it's value in header

3. If API Gateway is setup - we can also hit- http://localhost:8072/product-service/api/v1/async-pricehealth
    It means now all requests can be routed through API-Gateway. You don't need to hit separate host-port for each
    e.g. http://localhost:8072/price-service/api/v1/getprice

http://localhost:8072/product-service/api/v1/async-getprice

http://localhost:8090/api/v1/async-getprice







