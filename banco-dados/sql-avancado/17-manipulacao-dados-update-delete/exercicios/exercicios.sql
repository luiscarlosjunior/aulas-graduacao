-- ========================================
-- EXERCÍCIOS PRÁTICOS: UPDATE e DELETE
-- Módulo 17 - Manipulação de Dados
-- ========================================
--
-- Instruções:
-- 1. Leia cada exercício com atenção
-- 2. Execute primeiro o script database-base.sql do módulo 09
-- 3. Sempre use transações e ROLLBACK para praticar
-- 4. Teste suas soluções com SELECT antes e depois
-- 5. Não esqueça: SEMPRE use WHERE!
--

-- ========================================
-- PARTE 1: EXERCÍCIOS DE UPDATE
-- ========================================

-- Exercício 1 (Básico)
-- Atualize o nome do usuário com id_usuario = 1 para "Carlos Silva Atualizado"
-- Sua resposta aqui:


-- Exercício 2 (Básico)
-- Atualize o email e o país do usuário com id_usuario = 2
-- Novo email: 'ana.souza.novo@email.com'
-- Novo país: 'Brasil'
-- Sua resposta aqui:


-- Exercício 3 (Intermediário)
-- Marque como inativo (ativo = 'N') todos os usuários da Espanha
-- Sua resposta aqui:


-- Exercício 4 (Intermediário)
-- Incremente em 5 o número de reproduções de todas as músicas do álbum 1
-- Sua resposta aqui:


-- Exercício 5 (Intermediário)
-- Aplique um aumento de 15% no preço mensal de todos os planos ativos
-- exceto o plano 'Free'
-- Sua resposta aqui:


-- Exercício 6 (Avançado)
-- Atualize o campo id_genero de todas as músicas para o mesmo id_genero
-- do álbum ao qual pertencem, mas apenas para músicas onde id_genero é NULL
-- Sua resposta aqui:


-- Exercício 7 (Avançado)
-- Atualize o campo total_musicas de todas as playlists
-- com a contagem real de músicas em playlist_musica
-- Sua resposta aqui:


-- Exercício 8 (Avançado)
-- Atualize o campo duracao_total de todos os álbuns
-- com a soma das durações de suas músicas
-- Sua resposta aqui:


-- Exercício 9 (Avançado com CASE)
-- Atualize o status de todas as assinaturas usando CASE:
-- - Se data_fim é NULL: 'ATIVA'
-- - Se data_fim < SYSDATE: 'EXPIRADA'
-- - Caso contrário: 'ATIVA'
-- Sua resposta aqui:


-- Exercício 10 (Desafio)
-- Aplique diferentes descontos nos planos usando CASE:
-- - Premium: 10% de desconto
-- - Família: 15% de desconto  
-- - Estudante: 5% de desconto
-- - Outros planos: sem desconto
-- Sua resposta aqui:


-- ========================================
-- PARTE 2: EXERCÍCIOS DE DELETE
-- ========================================

-- Exercício 11 (Básico)
-- Delete o usuário com id_usuario = 10
-- ATENÇÃO: Verifique dependências primeiro!
-- Sua resposta aqui:


-- Exercício 12 (Básico)
-- Delete todas as músicas com total_reproducoes = 0
-- Sua resposta aqui:


-- Exercício 13 (Intermediário)
-- Delete todos os gêneros que não têm músicas associadas
-- Dica: Use NOT IN ou NOT EXISTS
-- Sua resposta aqui:


-- Exercício 14 (Intermediário)
-- Delete todas as playlists que não têm músicas
-- (não estão em playlist_musica)
-- Sua resposta aqui:


-- Exercício 15 (Intermediário)
-- Delete o histórico de reprodução com mais de 1 ano
-- Use ADD_MONTHS(SYSDATE, -12)
-- Sua resposta aqui:


-- Exercício 16 (Avançado)
-- Delete todos os usuários inativos (ativo = 'N') do Brasil
-- que não têm assinaturas ativas
-- Sua resposta aqui:


-- Exercício 17 (Avançado)
-- Delete todas as músicas de artistas da Alemanha
-- Use subconsulta com JOIN
-- Sua resposta aqui:


-- Exercício 18 (Avançado)
-- Delete uma playlist específica (id_playlist = 5) e todas suas músicas
-- na tabela de relacionamento playlist_musica
-- Faça na ordem correta!
-- Sua resposta aqui:


-- Exercício 19 (Desafio - Integridade Referencial)
-- Delete completamente o usuário com id_usuario = 6
-- incluindo todos seus dados relacionados:
-- - historico_reproducao
-- - assinatura  
-- - playlist_musica (das playlists dele)
-- - playlist
-- - usuario
-- Faça na ordem correta respeitando FKs!
-- Sua resposta aqui:


-- Exercício 20 (Desafio - Limpeza de dados)
-- Crie uma sequência de DELETEs para remover:
-- 1. Histórico de reprodução com mais de 2 anos
-- 2. Assinaturas canceladas há mais de 1 ano
-- 3. Playlists sem músicas e não atualizadas há 1 ano
-- Sua resposta aqui:


-- ========================================
-- PARTE 3: EXERCÍCIOS COMBINADOS
-- ========================================

-- Exercício 21 (Combinado)
-- Faça o seguinte:
-- 1. Atualize todas as músicas do álbum 2 para explicita = 'N'
-- 2. Delete o histórico de reprodução dessas músicas
-- 3. Verifique os resultados
-- 4. Faça ROLLBACK
-- Sua resposta aqui:


-- Exercício 22 (Combinado - Soft Delete)
-- Em vez de deletar o usuário id_usuario = 8, implemente soft delete:
-- 1. Atualize ativo = 'N'
-- 2. Atualize ultimo_acesso = NULL
-- 3. Verifique o resultado
-- Sua resposta aqui:


-- Exercício 23 (Prático - Backup e Restore)
-- 1. Crie uma tabela de backup: usuario_backup
-- 2. Delete usuários inativos
-- 3. Verifique quantos foram deletados
-- 4. Se necessário, restaure do backup
-- 5. Limpe (drop) a tabela de backup
-- Sua resposta aqui:


-- ========================================
-- PARTE 4: EXERCÍCIOS DE SEGURANÇA
-- ========================================

-- Exercício 24 (Procedimento Seguro - UPDATE)
-- Execute o padrão de UPDATE seguro para atualizar o usuário id_usuario = 3:
-- 1. SELECT para ver dados atuais
-- 2. UPDATE para modificar nome_usuario e email
-- 3. SELECT para verificar mudanças
-- 4. COMMIT ou ROLLBACK
-- Sua resposta aqui:


-- Exercício 25 (Procedimento Seguro - DELETE)
-- Execute o padrão de DELETE seguro para remover a playlist id_playlist = 7:
-- 1. SELECT para ver dados atuais
-- 2. SELECT COUNT para contar dependências
-- 3. DELETE das dependências (playlist_musica)
-- 4. DELETE da playlist
-- 5. SELECT COUNT para verificar remoção
-- 6. COMMIT ou ROLLBACK
-- Sua resposta aqui:


-- ========================================
-- GABARITO - NÃO OLHE ANTES DE TENTAR!
-- ========================================
-- 
-- As soluções estão no arquivo solucoes_exercicios.sql
--
-- Dicas gerais:
-- - Sempre teste com SELECT primeiro
-- - Use COUNT(*) para saber quantos registros serão afetados
-- - Sempre use WHERE (exceto em casos muito específicos)
-- - Use transações: ROLLBACK para desfazer, COMMIT para confirmar
-- - Verifique dependências antes de DELETE
-- - Em operações críticas, faça backup primeiro
--
-- ========================================

-- TESTE FINAL: Avalie seu conhecimento
-- Antes de considerar que dominou o conteúdo:
-- 
-- [ ] Consigo fazer UPDATE de uma coluna?
-- [ ] Consigo fazer UPDATE de múltiplas colunas?
-- [ ] Consigo usar expressões e cálculos no UPDATE?
-- [ ] Consigo usar subconsultas no UPDATE?
-- [ ] Consigo usar CASE no UPDATE?
-- [ ] Consigo fazer DELETE com WHERE específico?
-- [ ] Consigo usar subconsultas no DELETE?
-- [ ] Entendo a ordem de DELETE respeitando FKs?
-- [ ] SEMPRE uso WHERE no UPDATE/DELETE?
-- [ ] SEMPRE testo com SELECT antes de executar?
-- [ ] SEMPRE uso transações e ROLLBACK em testes?
-- [ ] Sei verificar dependências antes de DELETE?
--
-- Se marcou todos, parabéns! Você dominou UPDATE e DELETE! 🎉
