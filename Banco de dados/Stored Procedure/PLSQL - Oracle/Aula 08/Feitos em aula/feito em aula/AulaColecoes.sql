SET SERVEROUTPUT ON;

DECLARE
    type v_Idade IS VARRAY(10) OF NUMBER(2);
    n_idade v_Idade;
BEGIN

    n_idade := v_Idade(20, 25, 36, 87, 10);
    
    FOR indice IN 1..n_idade.count LOOP
        dbms_output.put_line('Idade ' || indice || ' : ' || n_idade(indice));
    END LOOP;
END;
/

DECLARE
    type v_Nota IS VARRAY(5) OF ALUNO.ID%type;
    n_nota v_Nota;
BEGIN
    n_nota := v_Nota(1, 2, 3, 4, 5);    
    FOR indice IN 1..n_nota.count LOOP
        dbms_output.put_line('Idade ' || indice || ' : ' || n_nota(indice));
    END LOOP;
END;
/

-- Na versão 11g, não funcionou
declare
    type v_idade is varray(10) of ALUNO.NOTA%type;
    n_idade v_idade;
    
begin
    
    n_idade := v_idade(20.5, 30.5, 40.9, 50.7, 60.5, 70.9, 81.5, 92.6, 95.0, 0.8);
    
    for i in 1..n_idade.count loop
        dbms_output.put_line(i);
        dbms_output.put_line('Idades: ' || n_idade(i));
    end loop;
end;    
--DROP TABLE ALUNO;


