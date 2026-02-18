/*
  Package funcionario body
*/
CREATE OR REPLACE PACKAGE BODY funcionario AS
  --// get funcionário nomeCompleto
  FUNCTION get_nomeCompleto(n_func_id NUMBER) RETURN VARCHAR2 IS
      v_nomeCompleto VARCHAR2(46);
  BEGIN
    SELECT primeiro_nome || ',' ||  ultimo_nome
    INTO v_nomeCompleto
    FROM funcionarios
    WHERE empresa_id = n_func_id;
  
    RETURN v_nomeCompleto;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN NULL;
  WHEN TOO_MANY_ROWS THEN
    RETURN NULL;
  END; --// end get_nomeCompleto
  
  -- get salario
  FUNCTION get_salario(n_func_id NUMBER) RETURN NUMBER IS
    n_salario NUMBER(8,2);
  BEGIN
    SELECT salario
    INTO n_salario
    FROM funcionarios
    WHERE empresa_id = n_func_id;
  
    RETURN n_salario;
  
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETURN NULL;
      WHEN TOO_MANY_ROWS THEN
        RETURN NULL;
  END;
END funcionario;