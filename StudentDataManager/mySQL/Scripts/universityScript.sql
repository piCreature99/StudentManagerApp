DROP DATABASE IF EXISTS `University`;
CREATE DATABASE `University`;
USE `University`;

CREATE TABLE Students(
 Student_ID VARCHAR(50) PRIMARY KEY,
 Student_firstName VARCHAR(50),
 Student_middleName VARCHAR(50),
 Student_lastName VARCHAR(50),
 Student_dateOfBirth VARCHAR(20),
 Student_gender VARCHAR(6)
 );
 