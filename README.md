# Technical Assessment - Email Service
### Overview
The application should provide an abstraction between two different email service providers. If one of the services goes down, your service can quickly failover to a different provider without affecting your customers.

### Email Providers
- [Mailgun](https://www.mailgun.com/)
- [Pepipost](https://www.pepipost.com/)

### Requirements
- Java 11 or higher - [Installation Guide](https://docs.oracle.com/en/java/javase/11/install/preface.html#GUID-A1E4DD95-DA5E-4441-BFCD-2E8AE63C573D)
- Maven -  [Installation Guide](https://maven.apache.org/install.html)

### Configuration
- Populate the following fields in application.properties file 
	- email.mailgun.domain
	- email.mailgun.api
	- email.pepipost.api

### Build
1. Build Spring Boot Project with Maven
<pre>mvn install</pre>
2. Run Spring Boot app using Maven
<pre>mvn spring-boot:run</pre>

### Swagger UI
`http://localhost:8080/swagger-ui/index.html`

### Fields
| Field  | Required  | Constraint |
| :------------: |:---------------:|:---------------:|
| from|yes| email|
| subject| yes| string|
| to | yes|email|
| cc | no|email|
| bcc |no|email|
| message |no|string|

### TODO
- Unit Testing
- SendGrid integration
	- Having issue on login
	`You are not authorized to access SendGrid, please contact Support`
