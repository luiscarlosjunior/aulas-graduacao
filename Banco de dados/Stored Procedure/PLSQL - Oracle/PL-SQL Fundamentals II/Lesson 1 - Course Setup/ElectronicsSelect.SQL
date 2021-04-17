/* 
Copyright (c) 2005 Sideris Courseware Corporation. All Rights Reserved.
This file is licensed for use only by those instructors and students
who have received a valid license for the Sideris courseware title
associated with this file. All other use, duplication or distribution
is prohibited and illegal.
*/

/*
Formatting commands which enhance the readability of the output.
*/

COLUMN Address FORMAT A20 WORD_WRAP;
COLUMN Comments FORMAT A10 WORD_WRAP;
COLUMN QtyOnHand FORMAT 9999 HEADING 'Qty|On|Hand';
COLUMN QtyInProcess FORMAT 9999 HEADING 'Qty|In|Process';
COLUMN MemberID FORMAT B99999 HEADING 'Member|ID';
COLUMN MentorID FORMAT B99999 HEADING 'Mentor|ID';
COLUMN ProductID FORMAT B999999 HEADING 'Product|ID';
COLUMN CustomerID FORMAT B9999999 HEADING 'Customer|ID';
COLUMN TeamID FORMAT B999 HEADING 'Team|ID';

SET FEEDBACK 4;
SET ECHO ON;

SELECT * FROM customers;
SELECT * FROM products;
SELECT * FROM sales;
SELECT * FROM teams;
SELECT * FROM members;

SET ECHO OFF;
SET FEEDBACK 6

/*
If you wish to retain the effects of the formatting commands you may
move the following command inside the boundaries of this comment text.
*/

CLEAR COLUMNS;