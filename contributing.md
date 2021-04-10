# Guia de estilos para o colaborador

> “A commit message shows whether a developer is a good collaborator.”
― Peter Hutterer

## Definindo mensagem de um commit

### As mensagens dos commits devem servir para três importantes coisas:

* Para acelerar o processo de revisão.
* Para ajudar a escrever uma boa nota de lançamento.
* Para ajudar os futuros mantenedores (que pode ser você), ou ajudar a descobrir porque uma mudança foi feita no código ou porque uma funcionalidade foi adicionada.

### Sugestões Commit

* Escreva uma linha de resumo e faça a descrição de modo imperativo, como se estivesse comandando alguém. Escreva: "adiciona", "remove", "atualiza" ao invés de "adicionado", "removido", "atualizado".
* O resumo deve ser apenas uma frase e deve começar com uma letra maiúscula.

```
Exemplo de título de commit: Caso este commit seja aceito, será adicionada uma funcionalidade de cadastro cliente 
```
* Na descrição, use das tags da linguagem (markdown)[https://www.markdownguide.org/extended-syntax]
* Quebre as linhas da descrição para que não fiquem muito extensas. (Para tornar a mensagem legível sem ter que rolar horizontalmente).
* Faça a descrição das atividades em tópicos.
* Sempre informe o número da issue que você está tentando resolver. Por exemplo #33, automáticamente o github irá realizar a relação com a issue 33.
* Específique o tipo do commit através dos labels, caso precise, crie uma.
* Deixe o mais objetivo possível.

### NÃO FAÇA

* Não termine a linha de resumo com pontuação e não exceda 72 caracteres.
* Commit com muitas alterações em arquivos, isso fere um dos objetivos que é deixar o commit como algo compreensivel
* Não crie projetos inteiros em um commit somente
* Se a issue for muito grande, usa a máxima, dividir para conquistar, uma issue pode receber vários commits antes de ser fechada

### DICAS
* De prioridade as tarefas descritas no [board](https://github.com/luiscarlosjunior/aulas-graduacao/projects/1)
* Se parece difícil resumir o que o seu commit faz, talvez tenha diversas alterações lógicas ou correções de erros. Nesse caso, deve ser dividido em vários commits.

### Referências
* https://medium.com/devzera/pull-requests-f492ab93e7dd
* https://www.freecodecamp.org/news/writing-good-commit-messages-a-practical-guide/
* https://www.youtube.com/watch?v=w3jLJU7DT5E
* https://medium.com/swlh/how-to-write-good-commit-messages-f37b5492fade
