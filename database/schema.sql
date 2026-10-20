CREATE DATABASE IF NOT EXISTS billing_db;
USE billing_db;

CREATE TABLE IF NOT EXISTS bill (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    area         DECIMAL(5,2) NOT NULL,
    customer_num CHAR(16)     NOT NULL,
    docket_num   CHAR(16)     NOT NULL UNIQUE,
    CHECK (area BETWEEN 1 AND 13)
);