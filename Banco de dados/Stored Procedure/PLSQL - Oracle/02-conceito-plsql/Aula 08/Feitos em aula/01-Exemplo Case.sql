SET SERVEROUTPUT ON;

DECLARE 
    n_idade NUMBER(2) := 25;
BEGIN 
    IF n_idade = 10 THEN
        DBMS_OUTPUT.PUT_LINE('Iqual a 10');
    ELSIF n_idade = 15 THEN
        DBMS_OUTPUT.PUT_LINE('Iqual a 15');
    ELSIF n_idade = 20 THEN
        DBMS_OUTPUT.PUT_LINE('Iqual a 20');
    ELSIF n_idade = 30 THEN
        DBMS_OUTPUT.PUT_LINE('Iqual a 30');
    ELSIF n_idade = 35 THEN
        DBMS_OUTPUT.PUT_LINE('Iqual a 35');
    ELSIF n_idade = 40 THEN
        DBMS_OUTPUT.PUT_LINE('Iqual a 40');
    ELSE 
        DBMS_OUTPUT.PUT_LINE('Idade não listada');
    END IF;
END;
/
DECLARE 
    n_idade NUMBER(2) := 25;
BEGIN 
    
    CASE n_idade
        WHEN 10 THEN
            DBMS_OUTPUT.PUT_LINE('Iqual a 10');
        WHEN 20 THEN
            DBMS_OUTPUT.PUT_LINE('Iqual a 20');
        WHEN 30 THEN
            DBMS_OUTPUT.PUT_LINE('Iqual a 30');
        WHEN 40 THEN
            DBMS_OUTPUT.PUT_LINE('Iqual a 40');
        WHEN 50 THEN
            DBMS_OUTPUT.PUT_LINE('Iqual a 50');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Idade não listada');
    END CASE;
END;
/

-- Pergunta: Como eu sei que um valor númerico é par ou impar?

DECLARE 
    n_numero NUMBER(3) := -25;
    v_texto VARCHAR2(100);
BEGIN
    CASE 
        WHEN (MOD(n_numero, 2) = 0) THEN
            v_texto := 'Valor é par';
        WHEN (MOD(n_numero, 2) != 0) THEN
            v_texto := 'Valor é impar';
        ELSE
            v_texto := 'Valor é inválido';
    END CASE;
    DBMS_OUTPUT.PUT_LINE(v_texto);
END;






