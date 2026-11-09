
CREATE DATABASE IF NOT EXISTS billing_db;
USE billing_db;

CREATE TABLE IF NOT EXISTS bill (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    area         DECIMAL(5,2) NOT NULL,
    customer_num CHAR(16)     NOT NULL,
    docket_num   CHAR(16)     NOT NULL UNIQUE,
    CHECK (area BETWEEN 1 AND 13)
);

CREATE TABLE IF NOT EXISTS publication (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    publication_id CHAR(16)     NOT NULL UNIQUE,
    frequency      VARCHAR(10)  NOT NULL,
    price          DECIMAL(6,2) NOT NULL,
    CHECK (frequency IN ('Daily', 'Weekly', 'Monthly')),
    CHECK (price >= 0.01)
);

CREATE TABLE IF NOT EXISTS order_book (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    order_num      CHAR(16) NOT NULL UNIQUE,
    customer_num   CHAR(16) NOT NULL,
    publication_id CHAR(16) NOT NULL,
    FOREIGN KEY (publication_id) REFERENCES publication(publication_id)
);

CREATE TABLE IF NOT EXISTS customer_book (
    customer_id CHAR(16) PRIMARY KEY,
    first_name VARCHAR(16) NOT NULL,
    surname VARCHAR(16) NOT NULL,
    address VARCHAR(16) NOT NULL,
    phone_number CHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS docket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    docket_num CHAR(16) NOT NULL UNIQUE,
    customer_num CHAR(16) NOT NULL,
    area DECIMAL(5,2) NOT NULL,
    stock BOOLEAN NOT NULL,
    CHECK (area BETWEEN 1 AND 13),
    FOREIGN KEY (customer_num)
        REFERENCES customer_book(customer_id)
);

SELECT * FROM billing_db.bill;
SELECT * FROM billing_db.publication;
SELECT * FROM billing_db.order_book;
SELECT * FROM billing_db.customer_book;
SELECT * FROM billing_db.docket;