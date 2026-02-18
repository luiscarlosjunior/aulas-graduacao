SET SERVEROUTPUT ON;

DECLARE
    R_ALUNO ALUNO%ROWTYPE;
BEGIN
    
    SELECT * INTO R_ALUNO
        FROM ALUNO WHERE ID = 2;
    
    DBMS_OUTPUT.PUT_LINE('Nome: ' || r_aluno.Nome);
    DBMS_OUTPUT.PUT_LINE('Nota: ' || r_aluno.Nota);
    DBMS_OUTPUT.PUT_LINE('Faltas: ' || r_aluno.Faltas);
    DBMS_OUTPUT.PUT_LINE('Curso: ' || r_aluno.Curso);    
END;

