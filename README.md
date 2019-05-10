# Description:

Back-end application is based on SpringBood Api,recives HTTP request from Angular front-end and responds to them
by sending **JSON** objects or appropiate error message.

# Features:

**Through back-end app you can**

### As User

- Create new account - register
- Sign in - authetication.
- Add current date to database.
- Add product which is asigned to date and has specified amount.
- Add new product to database which has to be accepted by admin.
- Delete product which is asigned to specified date.
- Delete product from database which hasn`t been accpeted yet by admin.
- Fetch all accepted products.
- Count sum of macros and calories of each product which has been assigned to specific day and return them to client.

### As Admin

- Fetch not-accepted produt list.
- Transfer product from one table to other.
- Fetch user details.

# Data:

Communication with **MySql** database is handled by **Spring Data**. Below you can see diagram of tables.

![](src/main/resources/model_bazy.PNG)

# Security:

 This application is secured with JSON Web Token (JWT) authentication and Spring Security. Each request *(exept: /signin, /singup)* is checked by AuthTokenFilter which validate jwt token and control the roles in order to determine which resouces can be returned.
 
 When client sends */signup* request,aplication validate json data which are included in request then saves user in database.If data is not valid controller sends appropiate response.
 
 When client sends */signin* request,Spring security autheticates recived data then if result is positive, jwt token is created on the basis of recived data.If not,Api sends appropiate response.Previously created token is returned to client.
 



