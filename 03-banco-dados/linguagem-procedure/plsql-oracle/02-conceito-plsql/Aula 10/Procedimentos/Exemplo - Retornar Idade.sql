create or replace 
PROCEDURE RETORNAR_IDADE (
  ano_nasc IN NUMBER,
  ano_corr IN NUMBER,
  idade    OUT NUMBER
)
AS 
BEGIN
  idade := ano_corr - ano_nasc;
END RETORNAR_IDADE;