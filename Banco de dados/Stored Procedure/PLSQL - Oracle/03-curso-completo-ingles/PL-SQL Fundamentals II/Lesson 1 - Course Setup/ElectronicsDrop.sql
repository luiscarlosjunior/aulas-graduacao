/* 
Copyright (c) 2005 Sideris Courseware Corporation. All Rights Reserved.
This file is licensed for use only by those instructors and students
who have received a valid license for the Sideris courseware title
associated with this file. All other use, duplication or distribution
is prohibited and illegal.
*/

/*
Drop any previously existing tables of the same name and recreate the tables.
*/

DROP TABLE customers CASCADE CONSTRAINTS;
DROP TABLE sales CASCADE CONSTRAINTS;
DROP TABLE products CASCADE CONSTRAINTS;
DROP TABLE teams CASCADE CONSTRAINTS;
DROP TABLE members CASCADE CONSTRAINTS;

PURGE RECYCLEBIN;
