-- MySQL Script generated by MySQL Workbench
-- Sun Nov 17 14:05:12 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `codigo_cliente` INT NOT NULL AUTO_INCREMENT,
  `cif_cliente` VARCHAR(20) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `codigo_postal` VARCHAR(5) NOT NULL,
  `telefono` VARCHAR(12) NULL,
  PRIMARY KEY (`codigo_cliente`),
  UNIQUE INDEX `Codigo Cliente_UNIQUE` (`codigo_cliente` ASC) VISIBLE,
  UNIQUE INDEX `NIF NIE Cliente_UNIQUE` (`cif_cliente` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `mydb`.`Credenciales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Credenciales` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Credenciales` (
  `correo` VARCHAR(80) NOT NULL,
  `Contraseña` VARCHAR(15) NOT NULL,
  `Cliente_codigo_cliente` INT NOT NULL,
  PRIMARY KEY (`correo`),
  INDEX `fk_Credenciales_Cliente_idx` (`Cliente_codigo_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_Credenciales_Cliente`
    FOREIGN KEY (`Cliente_codigo_cliente`)
    REFERENCES `mydb`.`Cliente` (`codigo_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Carrito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Carrito` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Carrito` (
  `codigo_carrito` INT NOT NULL AUTO_INCREMENT,
  `fecha_creacion` DATETIME NULL,
  `Cliente_codigo_cliente` INT NOT NULL,
  PRIMARY KEY (`codigo_carrito`),
  UNIQUE INDEX `codigo_carrito_UNIQUE` (`codigo_carrito` ASC) VISIBLE,
  INDEX `fk_Carrito_Cliente1_idx` (`Cliente_codigo_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_Carrito_Cliente1`
    FOREIGN KEY (`Cliente_codigo_cliente`)
    REFERENCES `mydb`.`Cliente` (`codigo_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Pedido` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Pedido` (
  `codigo_pedido` INT NOT NULL AUTO_INCREMENT,
  `fecha_pedido` DATETIME NOT NULL,
  `estado_pedido` VARCHAR(20) NOT NULL,
  `metodo_pago` VARCHAR(15) NOT NULL,
  `Cliente_codigo_cliente` INT NOT NULL,
  PRIMARY KEY (`codigo_pedido`),
  UNIQUE INDEX `codigo_pedido_UNIQUE` (`codigo_pedido` ASC) VISIBLE,
  INDEX `fk_Pedido_Cliente1_idx` (`Cliente_codigo_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Cliente1`
    FOREIGN KEY (`Cliente_codigo_cliente`)
    REFERENCES `mydb`.`Cliente` (`codigo_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Categoria` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Categoria` (
  `codigo_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre_categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo_categoria`),
  UNIQUE INDEX `codigo_categoria_UNIQUE` (`codigo_categoria` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Producto` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Producto` (
  `codigo_producto` INT NOT NULL AUTO_INCREMENT,
  `nombre_producto` VARCHAR(45) NOT NULL,
  `descripcion_producto` VARCHAR(45) NULL,
  `precio_producto` DECIMAL NOT NULL,
  `stock` INT NOT NULL,
  `Categoria_codigo_categoria` INT NOT NULL,
  PRIMARY KEY (`codigo_producto`),
  UNIQUE INDEX `codigo_producto_UNIQUE` (`codigo_producto` ASC) VISIBLE,
  INDEX `fk_Producto_Categoria1_idx` (`Categoria_codigo_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_Producto_Categoria1`
    FOREIGN KEY (`Categoria_codigo_categoria`)
    REFERENCES `mydb`.`Categoria` (`codigo_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Detalle_Carrito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Detalle_Carrito` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Detalle_Carrito` (
  `cantidad_producto` INT NOT NULL,
  `Carrito_codigo_carrito` INT NOT NULL,
  `Producto_codigo_producto` INT NOT NULL,
  INDEX `fk_Detalle_Carrito_Carrito1_idx` (`Carrito_codigo_carrito` ASC) VISIBLE,
  INDEX `fk_Detalle_Carrito_Producto1_idx` (`Producto_codigo_producto` ASC) VISIBLE,
  CONSTRAINT `fk_Detalle_Carrito_Carrito1`
    FOREIGN KEY (`Carrito_codigo_carrito`)
    REFERENCES `mydb`.`Carrito` (`codigo_carrito`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Detalle_Carrito_Producto1`
    FOREIGN KEY (`Producto_codigo_producto`)
    REFERENCES `mydb`.`Producto` (`codigo_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;