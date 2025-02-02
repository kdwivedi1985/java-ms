package org.test.demo;


import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class AppConfig {

    //@Value("${gateway-server.hostname}")
    //String gateway_hostname;

    //@Value("${gateway-server.portnumber}")
    //String gateway_portnumber;

    @Value("${gateway-server.hostname}")
    String gateway_hostname;

    @Value("${gateway-server.port}")
    String gateway_port;

    @Autowired
    private EurekaDiscoveryClient discoveryClient;

    @Bean
    public WebClient webClient_1(WebClient.Builder webClientBuilder)
    {
        return webClientBuilder
                .baseUrl("http://localhost:8091/api/v1/getprice")
                .filter(new LoggingWebClientFilter())
                .build();
    }

    //Connected through API Gateway

    @Bean
    public WebClient webClient_2(WebClient.Builder webClientBuilder)
    {
       /* return webClientBuilder
                .baseUrl("http://localhost:8091/api/v1/gethealthstatus")
                .filter(new LoggingWebClientFilter())
                .build();*/

        /*Read host and port from API Gateway and give name of the microservice so it recognizes the name of the service
        we want to hit . e.g. 4price-service.
        config repo must have either yml or properties file name e.g. product-service.yml
         */
        return webClientBuilder
                .baseUrl("http://"+gateway_hostname+ ":"+gateway_port+"/price-service/api/v1/gethealthstatus")
                .filter(new LoggingWebClientFilter())
                .build();
    }

    //Connecting through Eureka Service Discovery
    @Bean
    public WebClient webClient_3(WebClient.Builder webClientBuilder)
    {
        ServiceInstance instance = getServiceInstance("price-service");
        String hostname = instance.getHost();
        int port = instance.getPort();

        return webClientBuilder
                .baseUrl("http://"+hostname+":"+port+"/api/v1/getprice") //GET_RATION_DETAILS -> FETCH THE URI FROM HATEOS RESPONSE
                .filter(new LoggingWebClientFilter())
                .build();
    }

    /*
    @Bean
    public WebClient webClient_2(WebClient.Builder webClientBuilder)
    {

        //return webClientBuilder
                .baseUrl("http://"+gateway_hostname+":"+gateway_portnumber+"/health-service/api/v1/gethealthstatus")
                .filter(new LoggingWebClientFilter())
                .build();


        ServiceInstance instance = getServiceInstance("5price-service");
        String hostname = instance.getHost();
        int port = instance.getPort();

        return webClientBuilder
                .baseUrl("http://"+hostname+":"+port+"http://localhost:8090/getprice")
                .filter(new LoggingWebClientFilter())
                .build();

    }

    @Bean
    public WebClient webClient_3(WebClient.Builder webClientBuilder)
    {

        //return webClientBuilder
                .baseUrl("http://"+gateway_hostname+":"+gateway_portnumber+"/health-service/api/v1/gethealthstatus")
                .filter(new LoggingWebClientFilter())
                .build();

        ServiceInstance instance = getServiceInstance("5price-service");
        String hostname = instance.getHost();
        int port = instance.getPort();

        return webClientBuilder
                .baseUrl("http://"+hostname+":"+port+"http://localhost:8090/getprice")
                .filter(new LoggingWebClientFilter())
                .build();

    }
 */
    @Retryable(
            value = {RuntimeException.class}, // specify the exception types to retry on
            maxAttempts = 5,
            backoff = @Backoff(
                    delay = 1000, // initial delay in milliseconds
                    multiplier = 2, // multiplier for delay between attempts
                    maxDelay = 10000 // maximum delay in milliseconds
            )
    )
    public ServiceInstance getServiceInstance(String serviceName)
    {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances.isEmpty()) {
            throw new RuntimeException("No instances found for "+serviceName);
        }
        return instances.get(0);
    }

}