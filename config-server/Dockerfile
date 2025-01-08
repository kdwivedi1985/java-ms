#INSTALLATION OF THE OPERATING SYSTEM
#FROM openjdk:17-jdk
FROM ubuntu:latest

LABEL authors="kdwivedi1985"

#PLACEMENT OF THE EXECUTABLE [MICROSERVICE] ON THE IMAGE
COPY target/config-server-1.0.jar config-server.jar

#EXPOSE PORTS FOR INCOMING TRAFFIC - HOST_PORT:CONTAINER_PORT
EXPOSE 8888:8888

#INSTALLING UTILITIES
#added FROM debian:latest for MAC
RUN apt-get update && apt-get install -y openjdk-17-jdk
RUN apt-get install -y gcc
RUN apt-get install -y curl


#ENTRYPOINT OF THE CONTAINER THROUGH THE MICROSERVICE
ENTRYPOINT ["java","-jar","config-server.jar"]