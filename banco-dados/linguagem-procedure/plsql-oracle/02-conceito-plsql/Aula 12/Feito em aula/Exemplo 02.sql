SET SERVEROUTPUT ON;

/*
  Cria a especifica��o do pacote
  Ou seja, define o que conter� no corpo do pacote
*/
CREATE OR REPLACE PACKAGE MANIPULAR_ALUNO IS
  PROCEDURE ADICIONA_ALUNO (
    V_RA IN ALUNO.RA%TYPE,
    V_NOME IN ALUNO.NOME%TYPE,
    V_SNOME IN ALUNO.SOBRENOME%TYPE);
  PROCEDURE ATUALIZA_NOME_ALUNO (
    V_RA IN ALUNO.RA%TYPE,
    V_NOME IN ALUNO.NOME%TYPE
  );
END MANIPULAR_ALUNO;
/

/*
  � definido o 'corpo' em si do pacote dado o que foi
  declarado na especifica��o do pacote
*/
CREATE OR REPLACE PACKAGE BODY MANIPULAR_ALUNO IS
   PROCEDURE ADICIONA_ALUNO (
    V_RA IN ALUNO.RA%TYPE,
    V_NOME IN ALUNO.NOME%TYPE,
    V_SNOME IN ALUNO.SOBRENOME%TYPE)
    IS
    BEGIN
      INSERT INTO ALUNO (RA, NOME, SOBRENOME)
        VALUES (V_RA, V_NOME, V_SNOME);
      
      IF SQL%NOTFOUND THEN
        DBMS_OUTPUT.PUT_LINE('N�o houve inser��o');
      ELSE
        DBMS_OUTPUT.PUT_LINE('Inserido com sucesso');
      END IF;
      
      EXCEPTION
      WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('ID j� existe');
      
    END ADICIONA_ALUNO;  
    
    PROCEDURE ATUALIZA_NOME_ALUNO (
      V_RA IN ALUNO.RA%TYPE,
      V_NOME IN ALUNO.NOME%TYPE
    ) IS
    BEGIN
      UPDATE ALUNO SET NOME = V_NOME WHERE RA = V_RA;
      
      IF SQL%NOTFOUND THEN
        DBMS_OUTPUT.PUT_LINE('N�o houve inser��o');
      ELSE
        DBMS_OUTPUT.PUT_LINE('Atualizado com sucesso');
      END IF;
      
      EXCEPTION
      WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('ID j� existe');
      
    END ATUALIZA_NOME_ALUNO;
    
END;

SELECT RA, NOME FROM ALUNO WHERE RA = '123654978';

EXEC MANIPULAR_ALUNO.ADICIONA_ALUNO('123654978', 'LUIS', 'SANTOS');
EXEC MANIPULAR_ALUNO.ATUALIZA_NOME_ALUNO('123654978', 'HUGO')

