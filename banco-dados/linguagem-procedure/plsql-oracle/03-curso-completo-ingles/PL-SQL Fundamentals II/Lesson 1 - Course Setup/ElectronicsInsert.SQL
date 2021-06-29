/* 
Copyright (c) 2005 Sideris Courseware Corporation. All Rights Reserved.
This file is licensed for use only by those instructors and students
who have received a valid license for the Sideris courseware title
associated with this file. All other use, duplication or distribution
is prohibited and illegal.
*/

INSERT INTO customers VALUES (100, 'The Camera Store', '300 Broad St., New York, NY', '2125552222', 'Retail');
INSERT INTO customers VALUES (101, 'Wilson Security', '50 Cactus Freeway, Phoenix, AZ', '6095550000', 'End User');
INSERT INTO customers VALUES (102, 'City Copy Shop', '777 5th Ave, New York, NY', '2125551111', 'End User');
INSERT INTO customers VALUES (103, 'Computer Planet', '100 Boylston St., Boston, MA', '6175553333', 'Retail');
INSERT INTO customers VALUES (104, 'Hi Tech Supply', '50 Market Blvd, Oakland, CA', '5103334444', 'Distributor');



INSERT INTO sales VALUES (100, 300, '01-DEC-01', 10, 300.00);
INSERT INTO sales VALUES (104, 300, '12-JAN-01', 5, 600.00);
INSERT INTO sales VALUES (100, 300, '01-NOV-01', 5, 350.00);
INSERT INTO sales VALUES (104, 301, '12-JAN-01', 5, 1000.00);
INSERT INTO sales VALUES (100, 304, '01-DEC-01', 10, 500.00);
INSERT INTO sales VALUES (101, 305, '03-MAR-00', 2, 349.99);
INSERT INTO sales VALUES (104, 302, '01-DEC-01', 5, 1000.00);
INSERT INTO sales VALUES (100, 304, '01-JAN-01', 7, 650.00);
INSERT INTO sales VALUES (103, 301, '08-APR-01', 1, 1000.00);
INSERT INTO sales VALUES (103, 301, '07-APR-01', 5, 900.00);
INSERT INTO sales VALUES (103, 302, '07-APR-01', 5, 1200.00);
INSERT INTO sales VALUES (104, 304, '11-OCT-01', 10, 550.50);
INSERT INTO sales VALUES (103, 301, '15-JUN-01', 5, 900.00);
INSERT INTO sales VALUES (103, 302, '15-JUN-01', 5, 1200.00);
INSERT INTO sales VALUES (104, 304, '18-FEB-01', 3, 750.00);
INSERT INTO sales VALUES (104, 303, '11-APR-01', 5, 2000.00);
INSERT INTO sales VALUES (102, 303, '12-JUN-00', 2, 2850.00);
INSERT INTO sales VALUES (103, 303, '29-NOV-01', 1, 2900.00);
INSERT INTO sales VALUES (102, 303, '01-JAN-01', 1, 2900.00);
INSERT INTO sales VALUES (102, 303, '07-NOV-01', 1, 2900.00);

INSERT INTO products VALUES (300, 'Digital camera', '3.7M pixels', 699.99, 745, 100, 'Boston', 504);
INSERT INTO products VALUES (301, 'Scanner', 'Black and White', 1100, 50, 10, 'Chicago', 501);
INSERT INTO products VALUES (302, 'Hi Res Scanner', 'Color', 1500, 50, 10, 'Chicago', 502);
INSERT INTO products VALUES (303, 'Photocopier', NULL, 2900, 25, 0, 'Chicago', 503);
INSERT INTO products VALUES (304, 'Video recorder', 'Stereo equipped', 999.5, 500, 100, 'Boston', 505);
INSERT INTO products VALUES (305, 'Badge reader', 'Bar code device', 349.99, 422, 100, 'Miami', 507);

INSERT INTO teams VALUES (400, 'Management', '01-JAN-00', 500);
INSERT INTO teams VALUES (401, 'Production', '01-JAN-00', 506);
INSERT INTO teams VALUES (402, 'Engineering', '01-JAN-01', 505);
INSERT INTO teams VALUES (403, 'Advertising', '10-JUN-02', 500);

INSERT INTO members VALUES (500, 'John', 'Jones', '000000000', 75000, 5000, 'M', 'Y', '01-FEB-90', NULL, NULL, 400);
INSERT INTO members VALUES (501, 'Mary', 'Jones', '111111111', 30000, NULL, 'F', 'N', '02-MAR-02', NULL, 506, 401);
INSERT INTO members VALUES (502, 'Antonios', 'Vamvakeros', '222222222', 30000, NULL, 'M', 'N', '15-MAR-02', NULL, 506, 401);
INSERT INTO members VALUES (503, 'Louisa', 'Lindboe', '333333333', 30000, 1000, 'F', 'Y', '01-JAN-02', NULL, 506, 401);
INSERT INTO members VALUES (504, 'Cara', 'Montopoli', '444444444', 35000, 2000, 'F', 'Y', '01-JAN-01', NULL, 506, 401);
INSERT INTO members VALUES (505, 'Michael', 'Dupre', '555555555', 50000, 4000, 'M', 'Y', '12-DEC-97', NULL, 500, 402);
INSERT INTO members VALUES (506, 'Migumi', 'Zona', '666666666', 75000, 5000, 'F', 'N', '01-MAR-90', NULL, 500, 400);
INSERT INTO members VALUES (507, 'Wey', 'Ho', '777777777', 30000, NULL, 'M', 'N', '01-FEB-90', NULL, 505, 402);
INSERT INTO members VALUES (508, 'Joseph', 'Smith', '888888888', 0, 0, 'M', 'N', '01-FEB-90', '15-JUN-02', NULL, NULL);

COMMIT;



