
CREATE DATABASE IF NOT EXISTS billing_db;
USE billing_db;

-- ---------- BILL ----------
CREATE TABLE IF NOT EXISTS bill (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    area         DECIMAL(5,2) NOT NULL,
    customer_num CHAR(16)     NOT NULL,
    docket_num   CHAR(16)     NOT NULL UNIQUE,
    CHECK (area BETWEEN 1 AND 13)
);

-- ---------- PUBLICATION ----------
CREATE TABLE IF NOT EXISTS publication (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    publication_id CHAR(16)     NOT NULL UNIQUE,
    frequency      VARCHAR(10)  NOT NULL,
    price          DECIMAL(6,2) NOT NULL,
    CHECK (frequency IN ('Daily', 'Weekly', 'Monthly')),
    CHECK (price >= 0.01)
);

-- ---------- ORDER BOOK ----------
CREATE TABLE IF NOT EXISTS order_book (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    order_num      CHAR(16) NOT NULL UNIQUE,
    customer_num   CHAR(16) NOT NULL,
    publication_id CHAR(16) NOT NULL,
    FOREIGN KEY (publication_id) REFERENCES publication(publication_id)
);

SELECT * FROM billing_db.bill;
SELECT * FROM billing_db.publication;
SELECT * FROM billing_db.order_book;