SET SERVEROUTPUT ON;

DECLARE 

    v_Aluno Aluno.RA%type := '555666777';
    v_Nome Aluno.Nome%type;

BEGIN

    SELECT NOME INTO V_NOME
        FROM ALUNO;

    DBMS_OUTPUT.putline('Achou o aluno(a) ' || v_nome);

EXCEPTION
    WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE('Há mais de um aluno encontrado')

END;
/

DECLARE 

    v_Ra Aluno.RA%type := '555666777';
    v_Nome Aluno.Nome%type;

BEGIN

    SELECT NOME INTO V_NOME
        FROM ALUNO WHERE RA = V_RA;

    DBMS_OUTPUT.putline("Achou o aluno(a) " || v_nome);

END;

