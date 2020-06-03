CREATE OR REPLACE FUNCTION checarPalindromo(entradaString VARCHAR2)
   RETURN VARCHAR2
   IS result VARCHAR2(75);
   reverterString VARCHAR2(50);
   BEGIN
      SELECT REVERSE(inputString) INTO reversedString FROM DUAL;

      -- Usando a função UPPER para ignorar as diferenças case sensitivity.
      IF UPPER(inputString) = UPPER(reversedString)
      THEN
      RETURN(inputString||' é um palindromo.');
      END IF;
      RETURN (inputString||' não é um palindromo.');

    END checarPalindromo;
/