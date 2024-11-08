CREATE DATABASE IF NOT EXISTS concessionaire;
USE concessionaire;

CREATE TABLE cars (
    ID INT(5) NOT NULL,
    Model VARCHAR(20) NOT NULL,
    Manufacturer INT(5) NOT NULL,
    Image LONGBLOB NOT NULL, -- BINARY DATA
    Year DATE NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE KEY (Model)
) ENGINE=InnoDB; 

CREATE TABLE manufacturer (
    ID INT(5) NOT NULL,
    Name VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE=InnoDB;

ALTER TABLE cars
    ADD CONSTRAINT cars_fk_1 FOREIGN KEY (Manufacturer) REFERENCES manufacturer(ID);
