FROM openjdk:14-alpine
COPY target/books-*.jar books.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "books.jar"]