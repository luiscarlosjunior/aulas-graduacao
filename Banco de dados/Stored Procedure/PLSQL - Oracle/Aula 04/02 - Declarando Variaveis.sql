-- Avisa o servidor para os resultados aparecerem na tela
SET SERVEROUTPUT ON

-- Corpo b�sico de um programa PL/SQL
DECLARE 
  
  -- idadeMinima CONSTANT NUMBER(3);
  idade  NUMBER(2) := 25;
  numero2 NUMBER(2) := 30;
  -- Declarando
  nome    VARCHAR(50);
  CPF     CHAR(11) := '11155566633';
  
BEGIN
    -- Inicializando a vari�vel nome 
    nome := 'Qualquer um';
    
    dbms_output.put_line('A minha idade � ' || idade);  
    dbms_output.put_line('Meu nome � ' || nome);  
    dbms_output.put_line('Meu CPF � ' || CPF);  
    --dbms_output.put_line(numero);  
END;
