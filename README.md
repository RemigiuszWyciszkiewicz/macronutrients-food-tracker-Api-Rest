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

# Data

Communication with **MySql** database is handled by **Spring Data**. Below you can see diagram of tables.






