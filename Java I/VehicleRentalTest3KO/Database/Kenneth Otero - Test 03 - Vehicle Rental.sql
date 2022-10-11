-- --------------------------------------------------------------------------------
-- Kenneth Otero
-- April 26, 2021
-- Final Project - Vehicle Rental
-- --------------------------------------------------------------------------------

-- --------------------------------------------------------------------------------
-- Options
-- --------------------------------------------------------------------------------
USE dbVehicleRental;    -- Get out of the master database
SET NOCOUNT ON;			-- Report only errors

-- --------------------------------------------------------------------------------
-- Drop Tables
-- --------------------------------------------------------------------------------
IF OBJECT_ID('TLocations') IS NOT NULL DROP TABLE TLocations

-- --------------------------------------------------------------------------------
-- Create Tables
-- --------------------------------------------------------------------------------
CREATE TABLE TLocations
(
	intLocationID			INTEGER IDENTITY	NOT NULL
   ,strName					VARCHAR(250)		NOT NULL
   ,strAddress				VARCHAR(250)		NOT NULL
   ,strCity					VARCHAR(250)		NOT NULL
   ,strZip					VARCHAR(250)		NOT NULL
   ,CONSTRAINT TLocations_PK PRIMARY KEY (intLocationID)
)

-- --------------------------------------------------------------------------------
-- Insert Data
-- --------------------------------------------------------------------------------
INSERT INTO TLocations (strName, strAddress, strCity, strZip)
VALUES	('Northwest', '10 Colerain', 'Cinti', '45241')
	   ,('Downtown', '2010 Vine', 'Cinti', '45201')
	   ,('Loveland', '202 Main St', 'Loveland', '45140')
	   ,('Hamilton', '9010 C Street', 'Hamilton', '45013')