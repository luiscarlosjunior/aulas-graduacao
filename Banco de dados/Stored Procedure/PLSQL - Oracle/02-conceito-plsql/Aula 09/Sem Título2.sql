SET SERVEROUTPUT ON
/*
DECLARE 
  -- Declarando um cursor
  CURSOR C_ALUNO IS 
    SELECT RA, NOME
      FROM aluno;
  -- Variável do tipo registro de um cursor
  V_ALUNO C_ALUNO%ROWTYPE;
BEGIN
  OPEN C_ALUNO;
  LOOP
    FETCH C_ALUNO INTO V_ALUNO;
    EXIT WHEN C_ALUNO%NOTFOUND;
    dbms_output.put_line('Aluno: ' || v_aluno.nome || 
    ' RA: ' || v_aluno.ra);    
  END LOOP;
  CLOSE C_ALUNO;
END;
/
*/
/*
DECLARE 
  -- Declarando um cursor
  CURSOR C_ALUNO IS 
    SELECT RA, NOME
      FROM ALUNO;
  -- Variável do tipo registro de um cursor
  V_ALUNO C_ALUNO%ROWTYPE;
BEGIN
  OPEN C_ALUNO;
  FETCH C_ALUNO INTO V_ALUNO;
  WHILE C_ALUNO%FOUND LOOP
    dbms_output.put_line('Aluno: ' || v_aluno.nome || 
    ' RA: ' || v_aluno.ra);
    FETCH C_ALUNO INTO V_ALUNO;
  END LOOP;
  CLOSE C_ALUNO;
END;
/
*/
/*
DECLARE 
  -- Declarando um cursor
  CURSOR C_ALUNO IS 
    SELECT RA, NOME
      FROM ALUNO;
BEGIN
  FOR I_ALUNO IN C_ALUNO LOOP
    dbms_output.put_line('Aluno: ' || i_aluno.nome || 
    ' RA: ' || i_aluno.ra);
  END LOOP;
END;
/
*/
DECLARE 

BEGIN
  FOR I_ALUNO IN (SELECT RA, NOME FROM ALUNO) LOOP
    dbms_output.put_line('Aluno: ' || i_aluno.nome || 
    ' RA: ' || i_aluno.ra);
  END LOOP;
END;
/
