--Drop any sequence and table
DROP SEQUENCE UsrIDSeq;
DROP TABLE USERS;

--Create the table
CREATE TABLE SYSTEM.USERS 
  (	"FNAME" VARCHAR2(10 BYTE), 
    "MINIT" CHAR(1 BYTE), 
    "LNAME" VARCHAR2(10 BYTE), 
    "SSN" CHAR(9 BYTE), 
    "BDATE" DATE, 
    "ADDRESS" VARCHAR2(25 BYTE), 
    "SEX" CHAR(1 BYTE), 
    "SALARY" NUMBER(6,0), 
    "SUPERSSN" CHAR(9 BYTE), 
    "DNO" NUMBER(2,0)
  ) 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
COMMIT;

--Alter the table
ALTER TABLE USERS
  ADD UsrID NUMBER(4) CONSTRAINT usrPK PRIMARY KEY NOVALIDATE;
COMMIT;

--Create a new sequence
CREATE SEQUENCE UsrIDSeq;
COMMIT;

--Insert data into the tables
INSERT INTO USERS              
     VALUES ( 'John',             
              'B',                
              'Smith',            
              '123456789',        
              TO_DATE('09-JAN-1955','DD-MON-YYYY'),               
              '731 Fondren, Houston, TX',             
              'M',                 
              30000,              
              '333445555',         
              5,                   
              UsrIDSeq.NEXTVAL
              );                  

INSERT INTO USERS              
     VALUES ('Franklin',
              'T',
              'Wong',
              '333445555',
              TO_DATE('08-DEC-1945','DD-MON-YYYY'),
              '638 Voss, Houston, TX',
              'M',
              40000,
              '888665555',
              5,                   
              UsrIDSeq.NEXTVAL
              );

INSERT INTO USERS
     VALUES   ('Alicia',
              'T',
              'Zelaya',
              '999887777',
              TO_DATE('19-JUL-1958','DD-MON-YYYY'),
              '3321 Castle, Spring, TX',
              'F',
              25000,
              '987654321',
              4,                   
              UsrIDSeq.NEXTVAL
              );

INSERT INTO USERS
     VALUES   ('Jennifer',
              'S',
              'Wallace',
              '987654321',
              TO_DATE('20-JUL-1931','DD-MON-YYYY'),
              '291 Berry, Bellaire, TX',
              'F',
              43000,
              '888665555',
              4,                   
              UsrIDSeq.NEXTVAL
              );

INSERT INTO USERS
     VALUES   ('Ramesn',
              'K',
              'Naraan',
              '666884444',
              TO_DATE('15-SEP-1952','DD-MON-YYYY'),
              '975 Fire Oak, Humble, TX',
              'M',
              38000,
              '333445555',
              5,                   
              UsrIDSeq.NEXTVAL
              );

INSERT INTO USERS
     VALUES   ('Joyce',
              'A',
              'English',
              '453453453',
              TO_DATE('31-JUL-1962','DD-MON-YYYY'),
              '5631 Rice, Houston, TX',
              'F',
              25000,
              '333445555',
              5,                   
              UsrIDSeq.NEXTVAL
              );

INSERT INTO USERS
     VALUES   ('Ahmad',
              'V',
              'Jabour',
              '987987987',
              TO_DATE('29-MAR-1959','DD-MON-YYYY'),
              '980 Dallas, Houston, TX',
              'M',
              25000,
              '987654321',
              4,                   
              UsrIDSeq.NEXTVAL
              );

INSERT INTO USERS
     VALUES   ('James',
              'E',
              'Borg',
              '888665555',
              TO_DATE('10-NOV-1927','DD-MON-YYYY'),
              '450 Stone, Houston, TX',
              'M',
              55000,
              null,
              1,                   
              UsrIDSeq.NEXTVAL
              );
COMMIT;

--Retrieve the data
SELECT * FROM USERS;
