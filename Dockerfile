FROM openjdk:8
ADD target/address-stoom-test.jar address-stoom-test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "address-stoom-test.jar"]