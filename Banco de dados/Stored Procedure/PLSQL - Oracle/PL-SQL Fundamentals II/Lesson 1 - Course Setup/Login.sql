/* 
Copyright (c) 2002 Sideris Consulting Group, Inc. All Rights Reserved.
This file is licensed for use only by those instructors and students
who have received a valid license for the Sideris courseware title
associated with this file. All other use, duplication or distribution
is prohibited and illegal.
*/

/*
General settings
*/

set echo on;
set linesize 132;
set pagesize 200;
set echo off;
set serveroutput on;

/*
Helpful settings for the EQUITIES database
*/

COLUMN price FORMAT B99,999.99;
COLUMN q FORMAT 9
COLUMN i FORMAT 9
COLUMN o FORMAT 9
COLUMN quarter FORMAT A15

