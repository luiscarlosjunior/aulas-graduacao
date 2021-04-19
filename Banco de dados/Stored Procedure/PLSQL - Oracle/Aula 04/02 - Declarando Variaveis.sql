-- Avisa o servidor para os resultados aparecerem na tela
SET SERVEROUTPUT ON

-- Corpo básico de um programa PL/SQL
DECLARE 
  
  -- idadeMinima CONSTANT NUMBER(3);
  idade  NUMBER(2) := 25;
  numero2 NUMBER(2) := 30;
  -- Declarando
  nome    VARCHAR(50);
  CPF     CHAR(11) := '11155566633';
  
BEGIN
    -- Inicializando a variável nome 
    nome := 'Qualquer um';
    
    dbms_output.put_line('A minha idade é ' || idade);  
    dbms_output.put_line('Meu nome é ' || nome);  
    dbms_output.put_line('Meu CPF é ' || CPF);  
    --dbms_output.put_line(numero);  
END;
