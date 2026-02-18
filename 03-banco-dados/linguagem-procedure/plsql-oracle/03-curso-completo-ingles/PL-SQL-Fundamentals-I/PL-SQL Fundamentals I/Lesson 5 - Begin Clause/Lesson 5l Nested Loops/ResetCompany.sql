/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

SET ECHO ON;

--Delete existing data from the tables
DELETE FROM PROJECT;
DELETE FROM DEPENDENT;
DELETE FROM WORKS_ON;
DELETE FROM DEPARTMENT;
DELETE FROM LOCATIONS;
DELETE FROM EMPLOYEE;

--Insert data back into the tables
INSERT INTO EMPLOYEE              
     VALUES ( 'John',             
              'B',                
              'Doe',            
              '111223333',        
              TO_DATE('09-MAY-1997','DD-MON-YYYY'),               
              '212 Main St, Orlando, FL',             
              'M',                 
              25000,              
              '333445555',         
              5                   
              );                  

INSERT INTO EMPLOYEE              
     VALUES ( 'John',             
              'B',                
              'Smith',            
              '123456789',        
              TO_DATE('09-JAN-1955','DD-MON-YYYY'),               
              '731 Fondren, Houston, TX',             
              'M',                 
              30000,              
              '333445555',         
              5                   
              );          
              
INSERT INTO EMPLOYEE              
     VALUES ('Franklin',
              'T',
              'Wong',
              '333445555',
              TO_DATE('08-DEC-1945','DD-MON-YYYY'),
              '638 Voss, Houston, TX',
              'M',
              40000,
              '888665555',
              5
              );

INSERT INTO EMPLOYEE
     VALUES   ('Alicia',
              'T',
              'Zelaya',
              '999887777',
              TO_DATE('19-JUL-1958','DD-MON-YYYY'),
              '3321 Castle, Spring, TX',
              'F',
              25000,
              '987654321',
              4
              );

INSERT INTO EMPLOYEE
     VALUES   ('Jennifer',
              'S',
              'Wallace',
              '987654321',
              TO_DATE('20-JUL-1931','DD-MON-YYYY'),
              '291 Berry, Bellaire, TX',
              'F',
              43000,
              '888665555',
              4
              );

INSERT INTO EMPLOYEE
     VALUES   ('Ramesn',
              'K',
              'Naraan',
              '666884444',
              TO_DATE('15-SEP-1952','DD-MON-YYYY'),
              '975 Fire Oak, Humble, TX',
              'M',
              38000,
              '333445555',
              5
              );

INSERT INTO EMPLOYEE
     VALUES   ('Joyce',
              'A',
              'English',
              '453453453',
              TO_DATE('31-JUL-1962','DD-MON-YYYY'),
              '5631 Rice, Houston, TX',
              'F',
              25000,
              '333445555',
              5
              );

INSERT INTO EMPLOYEE
     VALUES   ('Ahmad',
              'V',
              'Jabour',
              '987987987',
              TO_DATE('29-MAR-1959','DD-MON-YYYY'),
              '980 Dallas, Houston, TX',
              'M',
              25000,
              '987654321',
              4
              );

INSERT INTO EMPLOYEE
     VALUES   ('James',
              'E',
              'Borg',
              '888665555',
              TO_DATE('10-NOV-1927','DD-MON-YYYY'),
              '450 Stone, Houston, TX',
              'M',
              55000,
              null,
              1
              );

INSERT INTO DEPT_LOCATIONS
     VALUES   (1,
              'Houston'
              );

INSERT INTO DEPT_LOCATIONS
     VALUES   (4,
              'Stafford'
              );

INSERT INTO DEPT_LOCATIONS
     VALUES   (5,
              'Bellaire'
              );

INSERT INTO DEPT_LOCATIONS
     VALUES   (5,
              'Sugarland'
              );

INSERT INTO DEPT_LOCATIONS
     VALUES   (5,
              'Houston'
              );

INSERT INTO DEPARTMENT
     VALUES   ('Research',
              5,
              '333445555',
              TO_DATE('22-MAY-1978','DD-MON-YYYY')
              );

INSERT INTO DEPARTMENT
     VALUES   ('Administration',
              4,
              '987654321',
              TO_DATE('01-JAN-1985','DD-MON-YYYY')
              );

INSERT INTO DEPARTMENT
     VALUES   ('Headquarters',
              1,
              '888665555',
              TO_DATE('19-JUN-1971','DD-MON-YYYY')
              );

INSERT INTO WORKS_ON
     VALUES   ('123456789',
              1,
              32.5
              );

INSERT INTO WORKS_ON
     VALUES   ('123456789',
              2,
              7.5
              );

INSERT INTO WORKS_ON
     VALUES   ('666884444',
              3,
              40.0
              );

INSERT INTO WORKS_ON
     VALUES   ('453453453',
              1,
              20.0
              );

INSERT INTO WORKS_ON
     VALUES   ('453453453',
              2,
              20.0
              );

INSERT INTO WORKS_ON
     VALUES   ('333445555',
              2,
              10.0
              );

INSERT INTO WORKS_ON
     VALUES   ('333445555',
              3,
              10.0
              );

INSERT INTO WORKS_ON
     VALUES   ('333445555',
              10,
              10.0
              );

INSERT INTO WORKS_ON
     VALUES   ('333445555',
              20,
              10.0
              );

INSERT INTO WORKS_ON
     VALUES   ('999887777',
              30,
              30.0
              );

INSERT INTO WORKS_ON
     VALUES   ('999887777',
              10,
              10.0
              );

INSERT INTO WORKS_ON
     VALUES   ('987987987',
              10,
              35.0
              );

INSERT INTO WORKS_ON
     VALUES   ('987987987',
              30,
              5.0
              );

INSERT INTO WORKS_ON
     VALUES   ('987654321',
              30,
              20.0
              );

INSERT INTO WORKS_ON
     VALUES   ('987654321',
              20,
              15.0
              );

INSERT INTO WORKS_ON
     VALUES   ('888665555',
              20,
              null
              );

INSERT INTO DEPENDENT
     VALUES   ('333445555',
              'Alice',
              'F',
               TO_DATE('05-APR-1976','DD-MON-YYYY'),
              'DAUGHTER'
              );

INSERT INTO DEPENDENT
     VALUES   ('333445555',
              'Theodore',
              'M',
               TO_DATE('25-OCT-1973','DD-MON-YYYY'),
              'SON'
              );

INSERT INTO DEPENDENT
     VALUES   ('333445555',
              'Joy',
              'F',
               TO_DATE('03-MAY-1948','DD-MON-YYYY'),
              'SPOUSE'
              );

INSERT INTO DEPENDENT
     VALUES   ('987654321',
              'Abner',
              'M',
               TO_DATE('29-FEB-1932','DD-MON-YYYY'),
              'SPOUSE'
              );

INSERT INTO DEPENDENT
     VALUES   ('123456789',
              'Michael',
              'M',
               TO_DATE('01-JAN-1978','DD-MON-YYYY'),
              'SON'
              );

INSERT INTO DEPENDENT
     VALUES   ('123456789',
              'Alice',
              'F',
               TO_DATE('31-DEC-1978','DD-MON-YYYY'),
              'DAUGHTER'
              );

INSERT INTO DEPENDENT
     VALUES   ('123456789',
              'Elizabeth',
              'F',
               TO_DATE('05-MAY-1957','DD-MON-YYYY'),
              'SPOUSE'
              );

INSERT INTO PROJECT
     VALUES   ('ProductX',
              1,
              'Bellaire',
              5
              );

INSERT INTO PROJECT
     VALUES   ('ProductY',
              2,
              'Sugarland',
              5
              );

INSERT INTO PROJECT
     VALUES   ('ProductZ',
              3,
              'Houston',
              5
              );

INSERT INTO PROJECT
     VALUES   ('Computerization',
              10,
              'Stafford',
              4
              );

INSERT INTO PROJECT
     VALUES   ('Reorganization',
              20,
              'Houston',
              1
              );

INSERT INTO PROJECT
     VALUES   ('Newbenefits',
              30,
              'Stafford',
              4
               );

COMMIT;

/*
If one wishes, one could add some further EMPLOYEE columns with preliminary
data values. Move the statements below outside the boundaries of these
comments in order to execute the statements.


ALTER TABLE employee
ADD (MaritalStatus CHAR(1),
     bonus NUMBER(6));

UPDATE employee
SET MaritalStatus = '1'
WHERE ssn IN (SELECT essn
              FROM dependent
              WHERE relationship = 'SPOUSE'
              AND essn = ssn);

UPDATE employee
set bonus = salary * .10
WHERE dno = 5;

COMMIT;

*/

SET ECHO OFF;

