# Java JDBC introduction

# mysql -u kurzs -p

-- If database not yet exists:
-- CREATE DATABASE wild;
USE wild;

CREATE TABLE persons ( firstname VARCHAR(30), lastname VARCHAR(30), age int );
INSERT INTO persons ( firstname, lastname, age ) VALUES ( 'John', 'Smith', 31);
INSERT INTO persons ( firstname, lastname, age ) VALUES ( 'Sarah', 'Connor', 29 );

SELECT * FROM persons;
