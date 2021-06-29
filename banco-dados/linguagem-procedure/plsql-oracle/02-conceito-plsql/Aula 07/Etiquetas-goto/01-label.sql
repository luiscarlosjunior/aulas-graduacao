<<NOME_DO_LABEL>>
DECLARE
 ...
BEGIN
 RELAÇÃO DE COMANDOS
  <<NOME_DO_LABEL>>
  RELAÇÃO DE COMANDOS
END;
/
BEGIN
 <<LOOP_EXTERNO>>
 FOR V_EXTERNO IN 1..20 LOOP
  DBMS_OUTPUT.PUT_LINE('Contador externo: ' || V_EXTERNO);
  <<LOOP_INTERNO>>
   FOR V_INTERNO IN 1..5 LOOP
    DBMS_OUTPUT.PUT_LINE('Contador interno: ' || V_INTERNO);
    IF V_EXTERNO = 10 THEN
     EXIT LOOP_EXTERNO;
    END IF;
  END LOOP INTERNO;
 END LOOP EXTERNO;
END;
/

--Porém, há a seguinte instrução condicional que 
--fará com que ocorra uma interrupção e o desvio 
--para o label <> quando o valor da variável
-- V_CONTADOR seja igual a 10:

IF V_CONTADOR = 10 THEN
 GOTO FIM;
<<PRINCIPAL>>
BEGIN
 FOR V_CONTADOR IN 1..20 LOOP
  DBMS_OUTPUT.PUT_LINE('Contador: ' || V_CONTADOR);
 IF V_CONTADOR = 10 THEN
  GOTO FIM;
 END IF;
 END LOOP;
<<FIM>>
 DBMS_OUTPUT.PUT_LINE('Fim do programa');
END;
/