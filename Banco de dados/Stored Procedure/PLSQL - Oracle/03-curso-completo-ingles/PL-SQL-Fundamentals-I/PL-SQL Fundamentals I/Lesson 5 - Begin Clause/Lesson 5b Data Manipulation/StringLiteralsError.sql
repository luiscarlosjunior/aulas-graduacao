SET SERVEROUTPUT ON;

/*
Author:		Tim Miles
Date:		3/19/2013
Purpose:	This script will generate an error due to the apostrophe 
		found in the message.
*/
DECLARE

	--Declare variables
	nValue		NUMBER(10) := &enter_value;
	vMessage	VARCHAR(30);

BEGIN
	--Test the value entered by the user
	IF nValue > 60000 THEN

		--Set and display the message
		vMessage := 'The value isn't valid.';
		dbms_output.put_line(vMessage);

	END IF;

END;
/