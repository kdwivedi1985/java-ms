1.run <config-server> jar before running this
2. run 4price-service prior to that 
3. install redis befor this
4. running server from project has issue, create jar using <mvn clean package> 
5. deploy the jar using java -jar <jar name>

*** This service is not working***

copy code from "social-service"

This service will be invoked by client and will call price-service
Port is 8091

/getproduct-price service is working is you want to test

Mono-> single stream 
Flux-> Stream of Data

1. Hit http://localhost:8090/productservice/api/v1/async-pricehealth,
    it will return "price_status_cookie" and value in response
2. Make a subsequent request by adding "price_status_cookie" and it's value in header








