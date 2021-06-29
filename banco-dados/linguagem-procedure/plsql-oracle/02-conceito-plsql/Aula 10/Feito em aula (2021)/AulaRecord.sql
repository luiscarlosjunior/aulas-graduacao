SET SERVEROUTPUT ON;

DECLARE
    R_ALUNO ALUNO%ROWTYPE;
BEGIN    
    SELECT * INTO R_ALUNO
        FROM ALUNO WHERE ID = 5;   
    dbms_output.put_line('Nome do aluno(a): ' || r_aluno.nome);
    dbms_output.put_line('RA do aluno(a): ' || r_aluno.RA);
    dbms_output.put_line('Nota do aluno(a): ' || r_aluno.Nota);
    dbms_output.put_line('Faltas do aluno(a): ' || r_aluno.faLTAS);
END;
/


-- Record baseado em Cursor

DECLARE
    CURSOR c_aluno IS 
        SELECT NOME, RA
            FROM ALUNO WHERE ID <= 100;
BEGIN
    FOR i_aluno IN c_aluno LOOP
        dbms_output.put_line('Nome: ' || i_aluno.nome);
        dbms_output.put_line('Numero RA: ' || i_aluno.ra);
    END LOOP;
END;
/

-- Declarando um tipo registro baseado na definição do usuário/desenvolvedor

DECLARE
    
    TYPE r_livros IS RECORD
    (
        titulo varchar(100),
        autor varchar(100),
        assunto varchar(140),
        ano_publicacao number(4)
    );
    
    -- Declaração do tipo livro
    codigo_limpo r_livros;
    sql_2008 r_livros;
    
BEGIN
    
    -- Inicialização - Atribuir valores
    codigo_limpo.titulo := 'Codigo Limpo: Habilidades Praticas do Agile Software';
    codigo_limpo.autor := 'Robert C. Martin';
    codigo_limpo.assunto := 'Como escrever codigo limpo.';
    codigo_limpo.ano_publicacao := 2009;
    
    sql_2008.titulo := 'SQL Server 2008 - Prática';
    sql_2008.autor := 'Jose Manzano';
    sql_2008.assunto := 'Como escrever SQL no MS';
    sql_2008.ano_publicacao := 2011;
    
    -- 
    dbms_output.put_line('Titulo do livro 1 ' || codigo_limpo.titulo);
    dbms_output.put_line('Autor do livro 1 ' || codigo_limpo.autor);
    dbms_output.put_line('Assunto do livro 1 ' || codigo_limpo.assunto);
    dbms_output.put_line('Ano do livro 1 ' || codigo_limpo.ano_publicacao);

    dbms_output.put_line('Titulo do livro 2 ' || sql_2008.titulo);
    dbms_output.put_line('Autor do livro 2 ' || sql_2008.autor);
    dbms_output.put_line('Assunto do livro 2 ' || sql_2008.assunto);
    dbms_output.put_line('Ano do livro 2 ' || sql_2008.ano_publicacao);
    
END;
/

DECLARE
    TYPE r_aluno IS RECORD
    (
        RA CHAR(15),
        NOME VARCHAR2(100)
    );
    t_aluno r_aluno;
BEGIN
    SELECT RA, NOME INTO t_aluno.ra, t_aluno.nome
        FROM ALUNO WHERE ID = 64;
        
    dbms_output.put_line('RA :' || t_aluno.ra);
    dbms_output.put_line('Nome :' || t_aluno.nome);
END;
/
DECLARE
    TYPE r_aluno IS RECORD
    (
        RA ALUNO.RA%TYPE,
        NOME ALUNO.NOME%TYPE
    );
    t_aluno r_aluno;
BEGIN
    SELECT RA, NOME INTO t_aluno.ra, t_aluno.nome
        FROM ALUNO WHERE ID = 15;
    dbms_output.put_line('RA :' || t_aluno.ra);
    dbms_output.put_line('Nome :' || t_aluno.nome);
END;












