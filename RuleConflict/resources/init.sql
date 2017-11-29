DROP DATABASE IF EXISTS Rule;

CREATE DATABASE Rule;

USE Rule;

CREATE TABLE Customer (
  id			int(11) primary key auto_increment, 
  first_name	varchar(45) not null,
  middle_name	varchar(45),
  last_name		varchar(45) not null 	
);