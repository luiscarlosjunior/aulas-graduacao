/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

DECLARE 
  TextRecord   VARCHAR2(2000);
  TextFile     utl_file.FILE_TYPE;

BEGIN 
  TextFile := utl_file.fopen (UPPER('TextDirectory'),
                              'Employees.csv', 'r', 32767);

  IF NOT utl_file.is_open (TextFile) THEN
     dbms_output.put_line ('Unable to open file');
  ELSE
    LOOP
      utl_file.get_line (TextFile, TextRecord);
      dbms_output.put_line (TextRecord);
     END LOOP;
  END IF;

  utl_file.fclose (TextFile);

EXCEPTION
 WHEN no_data_found THEN
    dbms_output.put_line ('File read in its entirety');
    utl_file.fclose (TextFile);

 WHEN others THEN
    dbms_output.put_line (sqlcode);
    dbms_output.put_line (sqlerrm);
    utl_file.fclose_all;
END; 
/


