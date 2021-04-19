CREATE TABLE audit_entry (
  entry_date  DATE,
  entry_user  VARCHAR2(30),
  entry_text  VARCHAR2(2000),
  old_value   VARCHAR2(2000),
  new_value   VARCHAR2(2000));