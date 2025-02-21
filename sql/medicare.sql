CREATE DATABASE medicare;
USE medicare;

CREATE TABLE patients (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NULL
);

INSERT INTO patients (name, username, email, phone) VALUES
('Ali Hassan', 'alihassan92', 'ali.hassan@example.com', '+971501234567'),
('Noura Khalid', 'nkhalid', 'noura.khalid@example.com', '+966550987654'),
('Omar Saeed', 'omarsaeed', 'omar.saeed@example.com', NULL);

SELECT * FROM patients WHERE id = 1;

SELECT * FROM patients;


CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NULL
);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,

);