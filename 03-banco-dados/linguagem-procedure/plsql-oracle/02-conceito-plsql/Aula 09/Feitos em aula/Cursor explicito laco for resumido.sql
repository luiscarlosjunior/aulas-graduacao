BEGIN
 
  FOR I_ALUNO IN (SELECT RA, NOME FROM ALUNO) LOOP
    dbms_output.put_line('Aluno: ' || i_aluno.nome || 
    ' RA:' || i_aluno.ra);
  END LOOP;
 
END;