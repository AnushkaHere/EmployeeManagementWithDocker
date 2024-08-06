FROM openjdk:17
COPY target/EmployeeManagementCrud-0.0.1-SNAPSHOT.jar EmployeeManagementCrud.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "EmployeeManagementCrud.jar"]