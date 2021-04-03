  SELECT aluno.primeiro_nome,
         aluno.ultimo_nome,
		 COUNT(aluno_curso.curso_id) numero_cursos
    FROM aluno
	JOIN aluno_curso ON aluno_curso.aluno_id = aluno.id
GROUP BY 1, 2
ORDER BY numero_cursos DESC
   LIMIT 1;
   
  SELECT curso.nome,
  		 COUNT(aluno_curso.aluno_id) numero_alunos
    FROM curso
	JOIN aluno_curso ON aluno_curso.curso_id = curso.id
GROUP BY 1
ORDER BY numero_alunos DESC
   LIMIT 1;
