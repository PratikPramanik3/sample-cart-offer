# Prerequisities
JDK 17
Docker

# How bring the mockservice up
cd mockserver  
docker compose up  
the mocke server will start at port 1080

# How bring the service up
./mvnw clean install -DskipTests  
java -jar target/simple-springboot-app-0.0.1-SNAPSHOT.jar  
The server will start at port 9001

# How to run the tests
./mvnw test  

# Please find the Test case and possible edge cases in the Test file

# Project is ongoing
