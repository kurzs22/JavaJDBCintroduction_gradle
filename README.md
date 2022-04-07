# Java JDBC introduction

## 💪Challenge
Look up the actual driver classes for at least three databases. At least one of these databases should be MySQL or MariaDB. The other two databases are up to you. Specify the driver class name as a constant String in your Java code for this Quest.

Create a Java application that uses JDBC to insert new records into the persons table, updates at least one record in the persons table, deletes at least two records in the persons table and reads all rows from the persons database table.

Things to look for:
- Remember to close statements, resultsets and connections
- Avoid code that can be used for SQL injection attacks

When you were quick to code the CRUD operations, try to enhance the output for the records from the persons table and provide information about the data type of each column

## 🧐 Acceptance criteria
- ✔ You wrote the code to connect to a database with JDBC
- ✔ You wrote the code to insert new records in the persons table
- ✔ You wrote the code to retrieve all records from the persons table
- ✔ You wrote the code to update the lastname of one record in the persons table
- ✔ You wrote the code to delete two record from the persons table
- ✔ You attempted to write some bad SQL injection statements
- ✔ Your code is available on Github/Gitlab