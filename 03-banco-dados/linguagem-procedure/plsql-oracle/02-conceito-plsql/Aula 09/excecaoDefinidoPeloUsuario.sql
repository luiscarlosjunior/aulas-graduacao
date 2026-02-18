DECLARE 
    V_CONTA NUMBER(3);
    TURMA_CHEIA EXCEPTION;
BEGIN

    SELECT COUNT(RA) INTO V_CONTA FROM ALUNO;

    IF V_CONTA > 60 THEN
        RAISE TURMA_CHEIA;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Turma está com ' || V_CONTA || ' alunos');
        DBMS_OUTPUT.PUT_LINE('Resta ' || (60 - V_CONTA) || ' vagas');
    END IF;

EXCEPTION
    WHEN TURMA_CHEIA THEN 
        DBMS_OUTPUT.PUT_LINE('Não foi possível incluir alunos');
        DBMS_OUTPUT.PUT_LINE('Motivo: Turma cheia');
END;