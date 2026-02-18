SET SERVEROUTPUT ON;

/*
Author:		Tim Miles
Date:		3/19/2013
Purpose:	This script shows how to comparison operators.
*/
DECLARE

	--Declare variables
	vCharacters	VARCHAR2(10) := 'Doe';
	vMessageText	VARCHAR2(30);
	
BEGIN

	--Test the string
	IF vCharacters = 'Doe'
	OR vCharacters != 'Doe'
	OR vCharacters IS NULL
	OR vCharacters LIKE 'D%'
	OR vCharacters IN ('Doe', 'Smith', 'Jones')
	OR vCharacters BETWEEN 'Daa' AND 'Dzz' THEN
	
		--Set the message
		vMessageText := 'String properly evaluated';
		
		--Display the message to the user
		dbms_output.put_line(vMessageText);

	END IF;
	
END;
/