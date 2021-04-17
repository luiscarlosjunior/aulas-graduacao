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

DROP TABLE department CASCADE CONSTRAINTS;
DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE dependent CASCADE CONSTRAINTS;
DROP TABLE dept_locations CASCADE CONSTRAINTS;
DROP TABLE project CASCADE CONSTRAINTS;
DROP TABLE works_on CASCADE CONSTRAINTS;
PURGE RECYCLEBIN;
