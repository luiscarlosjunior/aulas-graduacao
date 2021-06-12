CREATE OR REPLACE FUNCTION checarPalindromo(entradaString VARCHAR2)
   RETURN VARCHAR2
   IS result VARCHAR2(75);
   reverterString VARCHAR2(50);
   BEGIN
      SELECT REVERSE(inputString) INTO reversedString FROM DUAL;

      -- Usando a fun��o UPPER para ignorar as diferen�as case sensitivity.
      IF UPPER(inputString) = UPPER(reversedString)
      THEN
      RETURN(inputString||' � um palindromo.');
      END IF;
      RETURN (inputString||' n�o � um palindromo.');

    END checarPalindromo;
/