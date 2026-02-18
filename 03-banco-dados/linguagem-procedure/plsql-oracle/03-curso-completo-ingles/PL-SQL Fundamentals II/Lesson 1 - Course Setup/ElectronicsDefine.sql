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
CREATE TABLE customers ( CustomerID          NUMBER(4),
                         Name                VARCHAR2(20),
                         Address             VARCHAR2(30),
                         Telephone           CHAR(10),
                         Type                VARCHAR2(15)
                       );


DROP TABLE sales CASCADE CONSTRAINTS;
CREATE TABLE sales ( CustomerID       NUMBER(4),
                            ProductID        NUMBER(4),
                            SaleDate         DATE,
                            Qty              NUMBER(4),
                            UnitPrice        NUMBER(6,2)
                          );

DROP TABLE products CASCADE CONSTRAINTS;
CREATE TABLE products ( ProductID            NUMBER(4),
                        Name          VARCHAR2(20),
                        Comments             VARCHAR2(15),
                        ListPrice            NUMBER(6,2),
                        QtyOnHand            NUMBER(4),
                        QtyInProcess         NUMBER(4),
                        Location             VARCHAR2(10),
                        MemberID             NUMBER(4)
                      );


DROP TABLE teams CASCADE CONSTRAINTS;
CREATE TABLE teams ( TeamID                  NUMBER(4),
                     Name                    VARCHAR2(15),
                     StartDate               DATE,
                     LeaderID                NUMBER(4)
                   );


DROP TABLE members CASCADE CONSTRAINTS;
CREATE TABLE members ( MemberID              NUMBER(4),
                       FirstName             VARCHAR2(10),
                       LastName              VARCHAR2(10),
                       TaxID                 CHAR(9),
                       Salary                NUMBER(6),
                       Bonus                 NUMBER(6),
                       Gender                CHAR(1),
                       Married               CHAR(1),
                       HireDate              DATE,
                       FireDate              DATE,
                       MentorID              NUMBER(4),
                       TeamID                NUMBER(4)
                      );

PURGE RECYCLEBIN;
