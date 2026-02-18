
alter session set "_ORACLE_SCRIPT"=true;

CREATE USER cursoplsql IDENTIFIED BY cursoplsql DEFAULT tablespace users;

GRANT connect, resource TO cursoplsql;