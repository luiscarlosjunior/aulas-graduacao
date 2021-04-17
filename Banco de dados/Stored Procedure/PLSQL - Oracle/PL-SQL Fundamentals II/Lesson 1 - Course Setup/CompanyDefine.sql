/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

/*
Drop any previously existing tables of the same name and recreate the tables.
*/

DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;
CREATE TABLE EMPLOYEE (
				  FNAME               VARCHAR2(10),
				  MINIT               CHAR(1),
				  LNAME               VARCHAR2(10),
				  SSN                 CHAR(9),
				  BDATE               DATE,
				  ADDRESS             VARCHAR2(25),
				  SEX                 CHAR(1),
				  SALARY              NUMBER(6),
				  SUPERSSN            CHAR(9),
				  DNO                 NUMBER(2)
			);


DROP TABLE DEPARTMENT CASCADE CONSTRAINTS;
CREATE TABLE DEPARTMENT ( DNAME               VARCHAR2(15),
				  DNUMBER             NUMBER(2),
				  MGRSSN              CHAR(9),
				  MGRSTARTDATE        DATE
			);

DROP TABLE DEPT_LOCATIONS CASCADE CONSTRAINTS;
CREATE TABLE DEPT_LOCATIONS (
				  DNUMBER             NUMBER(2),
				  DLOCATION           VARCHAR2(10)
			    );


DROP TABLE PROJECT CASCADE CONSTRAINTS;
CREATE TABLE PROJECT (    PNAME               VARCHAR2(15),
				  PNUMBER             NUMBER(2),
				  PLOCATION           VARCHAR2(10),
				  DNUM                NUMBER(2)
		      );



DROP TABLE WORKS_ON CASCADE CONSTRAINTS;
CREATE TABLE WORKS_ON (   ESSN                CHAR(9),
				  PNO                 NUMBER(2),
				  HOURS               NUMBER(3,1)
		      );


DROP TABLE DEPENDENT CASCADE CONSTRAINTS;
CREATE TABLE DEPENDENT (  ESSN                CHAR(9),
				  DEPENDENT_NAME      VARCHAR2(10),
				  SEX                 CHAR(1),
				  BDATE               DATE,
				  RELATIONSHIP        VARCHAR2(10)
		       );

PURGE RECYCLEBIN;