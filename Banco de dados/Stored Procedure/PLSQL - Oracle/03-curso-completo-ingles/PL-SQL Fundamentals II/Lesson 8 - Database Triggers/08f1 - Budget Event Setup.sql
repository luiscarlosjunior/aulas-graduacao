CREATE TABLE budget_request (
  account_no    VARCHAR2(3),
  amount        NUMBER(6),
  description   VARCHAR2(2000),
  date_entered  DATE default SYSDATE);