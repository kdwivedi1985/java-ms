package org.test.demo;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Product Service", description = "Product Service APIs")
@RestController
@RequestMapping("/productservice/api/v1")
public class MainRestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier("webClient_1")
    WebClient webClient_1;

    @Autowired
    @Qualifier("webClient_2")
    WebClient webClient_2;

   /* @Autowired
    @Qualifier("webClient_3")
    WebClient webClient_obs;*/

    //@Autowired
    //CredentialRepository credentialRepository;

    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);


    @GetMapping("/async-pricehealth")
    public ResponseEntity<String> getPriceServiceHealthStatus(HttpServletRequest request)
    {
        log.info("Method Accessed!!");

        Optional<String> priceHealthStatusCookie = Optional.ofNullable(request.getHeader("price_status_cookie"));
        log.info("Cookie Value::"+ priceHealthStatusCookie);

        if(priceHealthStatusCookie.isEmpty())
        {
            // ASYNC REQUEST TO Price-SERVICE
            //Mono support Synch and Aync both. use for single.
            //Flux is used for stream of events

            Mono<String> responseMono = webClient_2.get()
                    .retrieve()
                    .bodyToMono(String.class); // ASYNCHRONOUS

            final String[] finalResponse = {null};
            String cookie =  String.valueOf((int)(Math.random()*1000000));

            responseMono.subscribe(
                    response1 -> {
                        log.info(response1+" from the price service");
                        finalResponse[0] = response1;
                        redisTemplate.opsForValue().set(cookie, response1);
                    },
                    error1 ->
                    {
                        log.info("error processing the response "+error1);
                    });

            return  ResponseEntity.ok().header("price_status_cookie", cookie).body("Status Request Initiated");
        }
        else
        {
            String cookie = request.getHeader("price_status_cookie");
            String response = (String) redisTemplate.opsForValue().get(cookie);
            return ResponseEntity.ok().body(response);
        }

    }

    @PostMapping("/saveproduct")
    public String saveObj(@RequestParam("key") String key, @RequestParam("value") String value)
    {
       // redisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @GetMapping("/register")
    public ResponseEntity<User> register()
    {
        User user = new User();
        //Credential credential = new Credential();
        //credential.setId(UUID.randomUUID());
        //credential.setPassword(String.valueOf((int)(Math.random()*1000000)));
        //credentialRepository.save(credential);
        //redisTemplate.opsForValue().set(credential.getCitizenid().toString(), credential.getPassword());

        return  ResponseEntity.ok(user);
    }
/*

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("citizenid") UUID citizenid, @RequestParam("password") String password)
    {
        String passwordFromRedis = (String) redisTemplate.opsForValue().get(citizenid.toString());
        if(passwordFromRedis.equals(password))
        {
            return ResponseEntity.ok().header("Authorization","VGHJUHGJ543534534564554").body("AUTHENTICATED");
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
*/

    @GetMapping("/getproduct/{productid}")
    public ResponseEntity<String> getProduct(@PathVariable Integer productid)
    {

        Mono<Date> responseMono = webClient_1.post()
                .body(productid, Integer.class) // Set the request body as UUID
                .retrieve()
                .bodyToMono(Date.class); // ASYNCHRONOUS

        final Date[] finalResponse = {null};

        responseMono.subscribe(
                response -> {
                    log.info(response+" from the Product service");
                    finalResponse[0] = response;
                    //redisTemplate.opsForValue().set(credential.getCitizenid().toString(), credential.getPassword());

                },
                error ->
                {
                    log.info("error processing the response "+error);
                });

        return  ResponseEntity.ok("Id extraction initiated"); // INCORRECT APPROACH
    }

    @GetMapping("/getproduct-price")
    public ResponseEntity<String> getPriceForProduct(HttpServletRequest request)
    {
        Optional<String> healthStatusCookie = Optional.ofNullable(request.getHeader("product_price_status_cookie"));
        if(healthStatusCookie.isEmpty())
        {
            // ASYNC REQUEST TO Price-SERVICE

            Mono<String> responseMono = webClient_1.get()
                    .retrieve()
                    .bodyToMono(String.class); // ASYNCHRONOUS

            //final String[] finalResponse = {null};
            String cookie =  String.valueOf((int)(Math.random()*1000000));

            responseMono.subscribe(
                    response1 -> {
                        log.info(response1+" from the price service");
                        //finalResponse[0] = response;
                        //SredisTemplate.opsForValue().set(cookie, response1);
                    },
                    error1 ->
                    {
                        log.info("error processing the response "+error1);
                    });

            // SENDING ANOTHER PARALLEL REQUEST TO OBSERVABLE-SERVICE

           /* Mono<String> responseMono_2 = webClient_obs.get()
                    .retrieve()
                    .bodyToMono(String.class); // ASYNCHRONOUS*/

            /*responseMono_2.subscribe(
                    response2 -> {
                        log.info(response2+" from the price service");
                        //finalResponse[0] = response2;
                    },
                    error2 ->
                    {
                        log.info("error processing the response "+error2);
                    });
*/
            return  ResponseEntity.ok().header("health_status_cookie", cookie).body("Status Request Initiated");
        }
        else
        {
            String cookie = request.getHeader("health_status_cookie");
            //String response = (String)redisTemplate.opsForValue().get(cookie);
            String response ="success";
            if(response == null)
            {
                return ResponseEntity.notFound().build();
            }
            else
            {
                return ResponseEntity.ok().body(response);
            }
        }
    }



}
