CREATE DATABASE IF NOT EXISTS jdbc_exp;
USE jdbc_exp;

CREATE TABLE Restaurant (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(150) NOT NULL
);

CREATE TABLE MenuItem (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    resId INT,
    FOREIGN KEY (resId) REFERENCES Restaurant(id)
);
