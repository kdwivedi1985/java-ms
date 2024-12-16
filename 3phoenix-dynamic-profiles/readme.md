
Add this in main controller->
    new SpringApplicationBuilder().profiles(args[0]).sources(PhoenixApplicationStarter.class).run(args);

Remove profile from local.properties

After this you won;t be able to run it directly by running main() class, you will need to pass
    - profile in maven
        OR
    - you need to create jar and pass the profile


Open terminal
    go to target-  cd target
    Run Jar with any profile - java -jar ./3phoenix-dynamic-profiles-1.0.jar stg
    "stg" is profile above

Shutdown Server - Control + c

If you want to run the same service on multiple ports, use following command from the console. It will help you to verify service discovery
    java -jar ./3phoenix-dynamic-profiles-1.0.jar --server.port=8081







