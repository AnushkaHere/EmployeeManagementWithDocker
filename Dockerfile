FROM openjdk:17-jdk-alpine
COPY target/EmployeeManagementCrud-0.0.1-SNAPSHOT.jar employee-management-system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "employee-management-system.jar"]