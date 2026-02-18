-- Executando o procedimento verificar_nome
DECLARE
  V_NOME ALUNO.NOME%TYPE;
BEGIN 
  VERIFICAR_NOME('82100280473    ', V_NOME);
                                          -- Criar a função FUNC_VERIFICAR_NOME(RA IN CHAR)
  dbms_output.put_line('O nome associado ao RA é ' || FUNC_VERIFICAR_NOME('82100280473    '));
END;


BEGIN 
  dbms_output.put_line('O nome associado ao RA é ' || FUNC_NOME_ALUNO('82100280473    '));
END;