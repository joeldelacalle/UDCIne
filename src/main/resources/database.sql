DROP SCHEMA IF EXISTS cine;
DROP USER IF EXISTS 'spq'@'localhost';
CREATE DATABASE cine;
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
GRANT ALL ON cine.* TO 'spq'@'localhost';