FROM openjdk:latest

COPY target/inventory_app.jar inventory_app.jar

EXPOSE 2004
ENTRYPOINT ["java", "-jar", "inventory_app.jar"]