FROM amazoncorretto:11-alpine-jdk
ADD target/bidding-seller-service.jar bidding-seller-service.jar
ENTRYPOINT ["java", "-jar", "/bidding-seller-service.jar"]
EXPOSE 8082