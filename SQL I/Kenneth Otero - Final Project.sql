-- --------------------------------------------------------------------------------
-- Kenneth Otero
-- Final Project
-- December 8, 2020 
-- --------------------------------------------------------------------------------

-- --------------------------------------------------------------------------------
-- Options
-- --------------------------------------------------------------------------------
USE dbSQL1;			-- Get out of the master database
SET NOCOUNT ON;		-- Report only errors

-- --------------------------------------------------------------------------------
-- Drop Tables
-- --------------------------------------------------------------------------------
IF OBJECT_ID('TJobMaterials')			IS NOT NULL DROP TABLE TJobMaterials
IF OBJECT_ID('TJobWorkers')				IS NOT NULL DROP TABLE TJobWorkers
IF OBJECT_ID('TWorkerSkills')			IS NOT NULL DROP TABLE TWorkerSkills
IF OBJECT_ID('TJobs')					IS NOT NULL DROP TABLE TJobs
IF OBJECT_ID('TMaterials')				IS NOT NULL DROP TABLE TMaterials
IF OBJECT_ID('TCustomers')				IS NOT NULL DROP TABLE TCustomers
IF OBJECT_ID('TStatuses')				IS NOT NULL DROP TABLE TStatuses
IF OBJECT_ID('TVendors')				IS NOT NULL DROP TABLE TVendors
IF OBJECT_ID('TWorkers')				IS NOT NULL DROP TABLE TWorkers
IF OBJECT_ID('TSkills')					IS NOT NULL DROP TABLE TSkills

-- --------------------------------------------------------------------------------
-- Create Tables
-- --------------------------------------------------------------------------------
CREATE TABLE TJobs
(
	 intJobID							INTEGER IDENTITY	NOT NULL
	,intCustomerID						INTEGER				NOT NULL
	,intStatusID						INTEGER				NOT NULL
	,dtmStartDate						DATETIME			NOT NULL
	,dtmEndDate							DATETIME			
	,strJobDesc							VARCHAR(2000)		NOT NULL
	,CONSTRAINT TJobs_PK				PRIMARY KEY ( intJobID )
)

CREATE TABLE TCustomers
(
	  intCustomerID						INTEGER	IDENTITY	NOT NULL
	 ,strFirstName						VARCHAR(50)			NOT NULL
	 ,strLastName						VARCHAR(50)			NOT NULL
	 ,strAddress						VARCHAR(50)			NOT NULL
	 ,strCity							VARCHAR(50)			NOT NULL
	 ,strState							VARCHAR(50)			NOT NULL
	 ,strZip							VARCHAR(50)			NOT NULL
	 ,strPhoneNumber					VARCHAR(50)			NOT NULL
	 ,CONSTRAINT TCustomer_PK			PRIMARY KEY ( intCustomerID )
)

CREATE TABLE TStatuses
(
	 intStatusID						INTEGER	IDENTITY	NOT NULL
	,strStatus							VARCHAR(50)			NOT NULL
	,CONSTRAINT TStatuses_PK			PRIMARY KEY ( intStatusID )
)

CREATE TABLE TJobMaterials
(
	 intJobMaterialID					INTEGER	IDENTITY	NOT NULL
	,intJobID							INTEGER				NOT NULL
	,intMaterialID						INTEGER				NOT NULL
	,intQuantity						INTEGER				NOT NULL
	,CONSTRAINT TCustomerJobMaterials_PK PRIMARY KEY ( intJobMaterialID )
)

CREATE TABLE TMaterials
(
	 intMaterialID						INTEGER	IDENTITY	NOT NULL
	,strDescription						VARCHAR(100)		NOT NULL
	,monCost							MONEY				NOT NULL
	,intVendorID						INTEGER				NOT NULL
	,CONSTRAINT TMaterials_PK			PRIMARY KEY ( intMaterialID )
)

CREATE TABLE TVendors
(
	 intVendorID						INTEGER	IDENTITY	NOT NULL
	,strVendorName						VARCHAR(50)			NOT NULL
	,strAddress							VARCHAR(50)			NOT NULL
	,strCity							VARCHAR(50)			NOT NULL
	,strState							VARCHAR(50)			NOT NULL
	,strZip								VARCHAR(50)			NOT NULL
	,strPhoneNumber						VARCHAR(50)			NOT NULL
	,CONSTRAINT TVendors_PK				PRIMARY KEY ( intVendorID )
)

CREATE TABLE TJobWorkers
(
	 intJobWorkerID						INTEGER	IDENTITY	NOT NULL
	,intJobID							INTEGER				NOT NULL
	,intWorkerID						INTEGER				NOT NULL
	,intHoursWorked						INTEGER				NOT NULL
	,CONSTRAINT TCustomerJobWorkers_PK	PRIMARY KEY ( intJobWorkerID )
)

CREATE TABLE TWorkers
(
	 intWorkerID						INTEGER	IDENTITY	NOT NULL
	 ,strFirstName						VARCHAR(50)			NOT NULL
	 ,strLastName						VARCHAR(50)			NOT NULL
	 ,strAddress						VARCHAR(50)			NOT NULL
	 ,strCity							VARCHAR(50)			NOT NULL
	 ,strState							VARCHAR(50)			NOT NULL
	 ,strZip							VARCHAR(50)			NOT NULL
	 ,strPhoneNumber					VARCHAR(50)			NOT NULL
	 ,dtmHireDate						DATETIME			NOT NULL
	 ,monHourlyRate						MONEY				NOT NULL
	 ,CONSTRAINT TWorkers_PK			PRIMARY KEY ( intWorkerID )
)

CREATE TABLE TWorkerSkills
(
	 intWorkerSkillID					INTEGER	IDENTITY	NOT NULL
	,intWorkerID						INTEGER				NOT NULL
	,intSkillID							INTEGER				NOT NULL
	,CONSTRAINT	TWorkerSkills_PK		PRIMARY KEY ( intWorkerSkillID )
)

CREATE TABLE TSkills
(
	 intSkillID							INTEGER	IDENTITY	NOT NULL
	,strSkill							VARCHAR(50)			NOT NULL
	,strDescription						VARCHAR(100)		NOT NULL
	,CONSTRAINT TSkills_PK				PRIMARY KEY ( intSkillID )
)

-- --------------------------------------------------------------------------------
-- Identify and Create Foreign Keys
-- --------------------------------------------------------------------------------
--
-- #	Child							Parent							Column(s)
-- -	-----							------							---------
-- 1	TJobs							TCustomers						intCustomerID
-- 2	TJobs							TStatuses						intStatusID
-- 3	TMaterials						TVendors						intVendorID
-- 4	TJobMaterials					TJobs							intJobID
-- 5	TJobMaterials					TMaterials						intMaterialID
-- 6	TJobWorkers						TJobs							intJobID
-- 7	TJobWorkers						TWorkers						intWorkerID
-- 8	TWorkerSkills					TWorkers						intWorkerID
-- 9	TWorkerSkills					TSkills							intSkillID

-- 1
ALTER TABLE TJobs ADD CONSTRAINT TJobs_TCustomers_FK
FOREIGN KEY ( intCustomerID ) REFERENCES TCustomers ( intCustomerID )

-- 2
ALTER TABLE TJobs ADD CONSTRAINT TJobs_TStatuses_FK
FOREIGN KEY ( intStatusID ) REFERENCES TStatuses ( intStatusID )

-- 3
ALTER TABLE TMaterials ADD CONSTRAINT TMaterials_TVendors_FK
FOREIGN KEY ( intVendorID ) REFERENCES TVendors ( intVendorID )

-- 4
ALTER TABLE TJobMaterials ADD CONSTRAINT TJobMaterials_TJobs_FK
FOREIGN KEY ( intJobID ) REFERENCES TJobs ( intJobID )

-- 5
ALTER TABLE TJobMaterials ADD CONSTRAINT TJobMaterials_TMaterials_FK
FOREIGN KEY ( intMaterialID ) REFERENCES TMaterials ( intMaterialID )

-- 6
ALTER TABLE TJobWorkers ADD CONSTRAINT TJobWorkers_TJobs_FK
FOREIGN KEY ( intJobID ) REFERENCES TJobs ( intJobID )

-- 7
ALTER TABLE TJobWorkers ADD CONSTRAINT TJobWorkers_TWorkers_FK
FOREIGN KEY ( intWorkerID ) REFERENCES TWorkers ( intWorkerID )

-- 8
ALTER TABLE TWorkerSkills ADD CONSTRAINT TWorkerSkills_TWorkers_FK
FOREIGN KEY ( intWorkerID ) REFERENCES TWorkers ( intWorkerID )

-- 9
ALTER TABLE TWorkerSkills ADD CONSTRAINT TWorkerSkills_TSkills_FK
FOREIGN KEY ( intSkillID ) REFERENCES TSkills ( intSkillID )

-- --------------------------------------------------------------------------------
-- Insert Data
-- --------------------------------------------------------------------------------
INSERT INTO TSkills (strSkill, strDescription)
VALUES		('Drywall', 'Pro Drywaller')
		   ,('Plumber', 'Handles Plumbing')
		   ,('Electrician', 'Good with wires')
		   ,('Heating', 'Pro at making your house warm')
		   ,('Cooling', 'Aircon Installer')

INSERT INTO TWorkers (strFirstName, strLastName, strAddress, strCity, strState, strZip, strPhoneNumber, dtmHireDate, monHourlyRate)
VALUES		('Ben', 'Jerry', '1111 Cool Rd', 'Cincinnati', 'OH', '45211', '111-111-1111', '01/01/2010', 15.00)
		   ,('Exxo', 'Kyle', '2222 Red Rd', 'Cincinnati', 'OH', '45211', '222-222-2222', '02/02/2011', 10.00)
		   ,('Weevi', 'Washington', '3333 Apple Rd', 'Cincinnati', 'OH', '45211', '333-333-3333', '05/25/2010', 15.00)
		   ,('Nolan', 'Brake', '4444 Banana Rd', 'Cincinnati', 'OH', '45211', '444-444-4444', '02/02/2020', 10.00)
		   ,('Jerry', 'Maya', '5555 Orange Rd', 'Cincinnati', 'OH', '45211', '555-555-5555', '01/01/1990', 25.00)

INSERT INTO TVendors (strVendorName, strAddress, strCity, strState, strZip, strPhoneNumber)
VALUES		('Mills', '1111 College Rd', 'Cincinnati', 'OH', '45238', '112-112-1122')
		   ,('Shokata', '2222 Hubert Rd', 'Mason', 'OH', '54549', '999-112-5252')
		   ,('Banananox', '3333 Peely Rd', 'Columbus', 'OH', '22354', '859-555-9696')
		   ,('Enders', '4444 Rage Rd', 'Cincinnati', 'OH', '45238', '513-112-1234')
		   ,('Lassy', '5555 Kind Ln', 'Mason', 'OH', '54599', '513-445-4455')

INSERT INTO TStatuses (strStatus)
VALUES		('Open')
		   ,('In Process')
		   ,('Complete')

INSERT INTO TCustomers (strFirstName, strLastName, strAddress, strCity, strState, strZip, strPhoneNumber)
VALUES		('Kenneth', 'Otero', '111 Main Street', 'Cincinnati', 'OH', '45211', '513-111-1112')
		   ,('Julie', 'Otero', '444 Main Street', 'Cincinnati', 'OH', '45211', '513-222-2222')
		   ,('Yvette', 'Otero', '123 Main Street', 'Cincinnati', 'OH', '45211', '513-333-3333')
		   ,('Kenneth', 'Otero Sr.', '123 Main Street', 'Cincinnati', 'OH', '45211', '513-444-4444')
		   ,('Amarice', 'Rivera', '752 Lone Oak Dr', 'Taylor Mill', 'KY', '41015', '859-555-5555')

INSERT INTO TMaterials (strDescription, monCost, intVendorID)
VALUES		('Stone', 100.00, 1)
		   ,('Wood', 25.00, 1)
		   ,('Marble', 150.00, 2)
		   ,('Granite', 175.00, 2)
		   ,('Brick', 50.00, 3)

INSERT INTO TJobs (intCustomerID, intStatusID, dtmStartDate, dtmEndDate, strJobDesc)
VALUES		(1, 3, '01/01/2011', NULL, 'Building Houses')
		   ,(1, 3, '02/02/2012', '01/01/2020', 'Installing Aircon')
		   ,(1, 3, '01/01/2001', '01/25/2001', 'Electrician Intern')
		   ,(1, 1, '01/01/2005', '01/01/2015', 'Lumberjack')
		   ,(1, 2, '09/11/2017', NULL, 'Real Estate')

INSERT INTO TWorkerSkills (intWorkerID, intSkillID)
VALUES		(1, 1)
		   ,(1, 2)
		   ,(3, 3)
		   ,(2, 4)
		   ,(2, 5)

INSERT INTO TJobWorkers (intJobID, intWorkerID, intHoursWorked)
VALUES		(1, 1, 40)
		   ,(2, 1, 40)
		   ,(3, 1, 20)
		   ,(4, 4, 60)
		   ,(5, 5, 0)

INSERT INTO TJobMaterials (intJobID, intMaterialID, intQuantity)
VALUES		(1, 1, 150)
		   ,(1, 2, 200)
		   ,(1, 3, 250)
		   ,(4, 4, 300)

-- --------------------------------------------------------------------------------
-- #3.1 - Create SQL to update the address for a specific customer. Include a select 
-- statement before and after the update. 
-- --------------------------------------------------------------------------------
SELECT * FROM TCustomers WHERE intCustomerID = 1

--UPDATE TCustomers
--SET strAddress = '3003 Britian Rd'
--WHERE intCustomerID = 1

SELECT * FROM TCustomers WHERE intCustomerID = 1

-- --------------------------------------------------------------------------------
-- #3.2 - Create SQL to increase the hourly rate by $2 for each worker that has been
-- an employee for at least 1 year. Include a select before and after the update. 
-- Make sure that you have data so that some rows are updated and others are not. 
-- --------------------------------------------------------------------------------
SELECT * FROM TWorkers

--UPDATE TWorkers
--SET monHourlyRate = monHourlyRate * 2
--WHERE dtmHireDate BETWEEN '01/01/1900' AND '01/01/2020'

SELECT * FROM TWorkers

-- --------------------------------------------------------------------------------
-- #3.3 - Create SQL to delete a specific job that has associated work hours and 
-- materials assigned to it. Include a select before and after the statement(s). 
-- --------------------------------------------------------------------------------
SELECT * FROM TJobs

--DELETE FROM TJobMaterials
--WHERE intJobID = 1

--DELETE FROM TJobWorkers
--WHERE intJobID = 1

--DELETE FROM TJobs
--WHERE intJobID = 1

SELECT * FROM TJobs

-- --------------------------------------------------------------------------------
-- #4.1 - Write a query to list all jobs that are in process. Include the Job ID and 
-- Description, Customer ID and name, and the start date. Order by the Job ID. 
-- --------------------------------------------------------------------------------
SELECT
	TJ.intJobID
   ,TJ.strJobDesc
   ,TC.intCustomerID
   ,TC.strFirstName + ' ' + TC.strLastName as CustomerName
   ,TJ.dtmStartDate
   ,TS.strStatus
FROM
	TJobs as TJ JOIN TCustomers as TC
		ON TJ.intCustomerID = TC.intCustomerID

	JOIN TStatuses as TS
		ON TS.intStatusID = TJ.intStatusID
WHERE
	TS.intStatusID = 2
ORDER BY
	intJobID

-- --------------------------------------------------------------------------------
-- #4.2 - Write a query to list all complete jobs for a specific customer and the materials 
-- used on each job. Include the quantity, unit cost, and total cost for each material 
-- on each job. Order by Job ID and material ID. Note: Select a customer that has at least 
-- 3 complete jobs and at least 1 open job and 1 in process job. At least one of the complete 
-- jobs should have multiple materials. If needed, go back to your inserts and add data. 
-- --------------------------------------------------------------------------------
SELECT
	TJ.intJobID
   ,TJM.intQuantity
   ,TC.strFirstName + ' ' + TC.strLastName as CustomerName
   ,SUM(TM.monCost) as TotalCost
   ,TS.strStatus
   ,TM.strDescription as Material
FROM
	TJobs as TJ JOIN TCustomers as TC
		ON TJ.intCustomerID = TC.intCustomerID

	JOIN TJobMaterials as TJM
		ON TJM.intJobID = TJ.intJobID

	JOIN TMaterials as TM
		ON TM.intMaterialID = TJM.intMaterialID

	JOIN TStatuses as TS
		ON TS.intStatusID = TJ.intStatusID
WHERE
	TC.intCustomerID = 1 --AND TS.intStatusID = 3
GROUP BY
	TJ.intJobID
   ,TJM.intQuantity
   ,TC.strFirstName
   ,TC.strLastName
   ,TM.intMaterialID
   ,TS.strStatus
   ,TM.strDescription
ORDER BY
	TJ.intJobID, TM.intMaterialID

-- --------------------------------------------------------------------------------
-- #4.3 - This step should use the same customer as in step 4.2. Write a query to list 
-- the total cost for all materials for each completed job for the customer. 
-- Use the data returned in step 4.2 to validate your results. 
-- --------------------------------------------------------------------------------
SELECT
	TC.strFirstName + ' ' + TC.strLastName as CustomerName
   ,TJ.intJobID
   ,SUM(TM.monCost) as TotalCost
   ,TS.strStatus
FROM
	TJobs as TJ JOIN TJobMaterials as TJM
		ON TJ.intJobID = TJM.intJobID

	JOIN TMaterials as TM
		ON TM.intMaterialID = TJM.intMaterialID

	JOIN TStatuses as TS
		ON TS.intStatusID = TJ.intStatusID

	JOIN TCustomers as TC
		ON TC.intCustomerID = TJ.intCustomerID
WHERE
	TS.intStatusID = 3 AND TC.intCustomerID = 1
GROUP BY
	TC.strFirstName
   ,TC.strLastName
   ,TJ.intJobID
   ,TS.strStatus

-- --------------------------------------------------------------------------------
-- #4.4 - Write a query to list all jobs that have work entered for them. Include the job ID, 
-- job description, and job status description. List the total hours worked for each 
-- job with the lowest, highest, and average hourly rate. Make sure that your data 
-- includes at least one job that does not have hours logged. This job should not be 
-- included in the query. Order by highest to lowest average hourly rate. 
-- --------------------------------------------------------------------------------
SELECT
	TJ.intJobID
   ,TJ.strJobDesc
   ,TS.strStatus
   ,ISNULL(SUM(TJW.intHoursWorked), 0) as TotalHours
   ,ISNULL(MIN(TW.monHourlyRate), 0) as MinRate
   ,ISNULL(MAX(TW.monHourlyRate), 0) as MaxRate
   ,ISNULL(AVG(TW.monHourlyRate), 0) as AvgRate
FROM
	TJobs as TJ JOIN TJobWorkers as TJW
		ON TJ.intJobID = TJW.intJobID

	JOIN TWorkers as TW
		ON TW.intWorkerID = TJW.intWorkerID

	JOIN TStatuses as TS
		ON TS.intStatusID = TJ.intStatusID
WHERE
	TJW.intHoursWorked > 0
GROUP BY
	TJ.intJobID
   ,TJ.strJobDesc
   ,TS.strStatus
   ,TW.monHourlyRate
ORDER BY
	AVG(TW.monHourlyRate) DESC

-- --------------------------------------------------------------------------------
-- #4.5 - Write a query that lists all materials that have not been used on any jobs. 
-- Include Material ID and Description. Order by Material ID. 
-- --------------------------------------------------------------------------------
SELECT
	TM.intMaterialID
   ,TM.strDescription
FROM
	TMaterials as TM LEFT JOIN TJobMaterials as TJM
		ON TM.intMaterialID = TJM.intMaterialID
WHERE
	TM.intMaterialID NOT IN (SELECT intMaterialID FROM TJobMaterials)
ORDER BY
	TM.intMaterialID

-- --------------------------------------------------------------------------------
-- #4.6 - Create a query that lists all workers that worked greater than 20 hours 
-- for all jobs that they worked on. Include the Worker ID and name, number of hours 
-- worked, and number of jobs that they worked on. Order by Worker ID. 
-- --------------------------------------------------------------------------------
SELECT
	TW.intWorkerID
   ,TW.strFirstName + ' ' + TW.strLastName as WorkerName
   ,TJW.intHoursWorked
   ,COUNT(TJW.intJobWorkerID) as Jobs
FROM
	TWorkers as TW JOIN TJobWorkers as TJW
		ON TW.intWorkerID = TJW.intWorkerID

	JOIN TJobs as TJ
		ON TJ.intJobID = TJW.intJobID
WHERE
	TJW.intHoursWorked > 20
GROUP BY
	TW.intWorkerID
   ,TW.strFirstName
   ,TW.strLastName
   ,TJW.intHoursWorked

-- --------------------------------------------------------------------------------
-- #4.7 - Write a query that lists all customers who are located on 'Main Street'. 
-- Include the customer Id and full address. Order by Customer ID. Make sure that 
-- you have at least three customers on 'Main Street' each with different house numbers. 
-- Make sure that you also have customers that are not on 'Main Street'
-- --------------------------------------------------------------------------------
SELECT
	TC.intCustomerID
   ,TC.strFirstName + ' ' + TC.strLastName as CustomerName
   ,TC.strAddress
   ,TC.strCity
   ,TC.strState
   ,TC.strZip
FROM
	TCustomers as TC
WHERE 
	TC.intCustomerID < 5
ORDER BY
	TC.intCustomerID

-- --------------------------------------------------------------------------------
-- #4.8 - Write a query to list completed jobs that started and ended in the same month. 
-- List Job, Job Status, Start Date and End Date. 
-- --------------------------------------------------------------------------------
SELECT
	TJ.intJobID
   ,TJ.strJobDesc
   ,TS.strStatus
   ,TJ.dtmStartDate
   ,TJ.dtmEndDate
FROM
	TJobs as TJ JOIN TStatuses as TS
		ON TJ.intStatusID = TS.intStatusID
WHERE
	TJ.dtmEndDate BETWEEN '01/01/2001' AND '01/31/2001'

-- --------------------------------------------------------------------------------
-- #4.9 - Create a query to list workers that worked on three or more jobs for the 
-- same customer. 
-- --------------------------------------------------------------------------------
SELECT
	TW.intWorkerID
   ,TW.strFirstName + ' ' + TW.strLastName as WorkerName
   ,TJ.intJobID
   ,TJ.strJobDesc
   ,TC.strFirstName + ' ' + TC.strLastName as CustomerName
FROM
	TJobs as TJ JOIN TJobWorkers as TJW
		ON TJ.intJobID = TJW.intJobID

	JOIN TWorkers as TW
		ON TW.intWorkerID = TJW.intWorkerID

	JOIN TCustomers as TC
		ON TC.intCustomerID = TJ.intCustomerID
WHERE 
	TC.intCustomerID = 1

-- --------------------------------------------------------------------------------
-- #4.10 - Create a query to list all workers and their total # of skills. 
-- Make sure that you have workers that have multiple skills and that you have at 
-- least 1 worker with no skills. The worker with no skills should be included with
-- a total number of skills = 0. Order by Worker ID. 
-- --------------------------------------------------------------------------------
SELECT
	TW.intWorkerID
   ,TW.strFirstName + ' ' + TW.strLastName as WorkerName
   ,ISNULL(COUNT(TWS.intWorkerSkillID), 0) as NumberOfSkills
FROM
	TWorkers as TW LEFT JOIN TWorkerSkills as TWS
		ON TW.intWorkerID = TWS.intWorkerID
GROUP BY
	TW.intWorkerID
   ,TW.strFirstName
   ,TW.strLastName
ORDER BY 
	TW.intWorkerID

-- --------------------------------------------------------------------------------
-- #4.11 - Write a query to list the total Charge to the customer for each job. 
-- Calculate the total charge to the customer as the total cost of materials + 
-- total Labor costs + 30% Profit. 
-- --------------------------------------------------------------------------------
SELECT
	SUM(TM.monCost + 300 * 0.30) as TotalCost
   ,TJ.intJobID
   ,TJ.strJobDesc
   ,TC.strFirstName + ' ' + TC.strLastName as CustomerName
FROM
	TJobs as TJ JOIN TCustomers as TC
		ON TJ.intCustomerID = TC.intCustomerID

	JOIN TJobMaterials as TJM
		ON TJM.intJobID = TJ.intJobID

	JOIN TMaterials as TM
		ON TM.intMaterialID = TJM.intMaterialID
GROUP BY
	TJ.intJobID
   ,TJ.strJobDesc
   ,TC.strFirstName
   ,TC.strLastName

-- --------------------------------------------------------------------------------
-- #4.12 - Write a query that totals what is owed to each vendor for a particular job. 
-- --------------------------------------------------------------------------------
SELECT 
	SUM(monCost) as TotalCost
   ,TV.strVendorName
   ,TJ.intJobID
   ,TJ.strJobDesc
FROM
	TMaterials as TM JOIN TVendors as TV
		ON TM.intVendorID = TV.intVendorID

	JOIN TJobMaterials as TJM
		ON TJM.intMaterialID = TM.intMaterialID

	JOIN TJobs as TJ
		ON TJ.intJobID = TJM.intJobID
GROUP BY
    TJ.intJobID
   ,TJ.strJobDesc
   ,TV.strVendorName