# Auzmor-assignment

Follow the below steps to setup/run the project.
1. This is spring-boot rest api project.
2. Install both mysql and redis and start servers on their default ports. Please don't change ports as I have hardcode urls.
   Database name : backend-server - Need to load data as per schema which was mentioned in assigment. 
   
3. Run spring-boot application from main class and bulid and run jar file.
4. The following 2 ends points are available for both inbound and outbound messages.
    (i). http://localhost:8080/api/inbound/sms    - POST
    (ii).http://localhost:8080/api/outbound/sms   - POST
  
Notes: 
1. Here I am passing input parameters in body insteady of either path or query parameters.
2. I have Hardcoded values in couple of places like 50 requests/24 hours from same from number,....

  
  
  
  
