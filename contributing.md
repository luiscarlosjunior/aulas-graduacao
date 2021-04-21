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
1. Na descrição, use das tags da linguagem [markdown](https://www.markdownguide.org/extended-syntax)
2. Quebre as linhas da descrição para que não fiquem muito extensas. (Para tornar a mensagem legível sem ter que rolar horizontalmente).
3. Faça a descrição das atividades em tópicos.
4. Sempre informe o número da issue que você está tentando resolver. Por exemplo #33, automáticamente o github irá realizar a relação com a issue 33.
5. Específique o tipo do commit através dos labels, caso precise, crie uma.
6. Deixe o mais objetivo possível.

### NÃO FAÇA

* Não termine a linha de resumo com pontuação e não exceda 72 caracteres.
* Commit com muitas alterações em arquivos, isso fere um dos objetivos que é deixar o commit como algo compreensivel;
* Não crie projetos inteiros em um commit somente;
* Se a issue for muito grande, usa a máxima, dividir para conquistar, uma issue pode receber vários commits antes de ser fechada.

### DICAS
* De prioridade as tarefas descritas no [board](https://github.com/luiscarlosjunior/aulas-graduacao/projects/1);
* Se parece difícil resumir o que o seu commit faz, talvez tenha diversas alterações lógicas ou correções de erros. Nesse caso, deve ser dividido em vários commits.

## COMO FAZER UM PULL REQUEST (OBRIGATÓRIO)
- Defina um título e descrição seguindo as dicas acima;
- Coloque ao menos um revisor como obrigatório;
- Informe as etiqueta (labels), projeto (se houver), milestone (se houver) e qual issue está relacionada (se for o caso); 
- Envie para a branch a sua branch especifica ou em caso de duvídas envie para a branch **development**; 
- **Evite enviar para a branch master**;

> Observaçao: Ao realizar o PR utilize-se dos recursos de [markdown](https://guides.github.com/features/mastering-markdown/)

## Ambiente de desenvolvimento
Para manter o ensino padronizado e permitir que o conteúdo alcance mais pessoas descartando a necessidade de instalar software pesados ou de complexidade média/alta de configuração, segue uma lista de sugestões IDE e editores utilizados nesse projeto:

### Desenvolvimento Python
Nas disciplinas de **desenvolvimento em python, estatística e introdução a Data Science** é aconselhavél usar o [COLAB](https://colab.research.google.com/notebooks/intro.ipynb#recent=true)
Para mais detalhes sobre o COLAB acesse o site https://www.tutorialspoint.com/google_colab/what_is_google_colab.htm

### Desenvolvimento Java
Para as disciplinas relacinadas a linguagem **Java** é aconselhavel usar a IDE [eclipse](https://www.eclipse.org/downloads/)
Para saber mais detalhes sobre o eclipse acesse este site https://www.devmedia.com.br/conhecendo-o-eclipse-uma-apresentacao-detalhada-da-ide/25589

> obs: Para realizar a documentação java, utilize-se dos resursos de [markdown](https://guides.github.com/features/mastering-markdown/)
> Exemplo de como pode ser feito nesse [link](https://github.com/luiscarlosjunior/aulas-graduacao/blob/master/Programa%C3%A7%C3%A3o/Java/TiposDados.md)

## Configuração do ambiente de desenvolvimento
### Verifique o arquivo [.gitignore](https://git-scm.com/docs/gitignore)

Muitas vezes quando estamos usando alguma IDE automaticamente a própria ferramenta cirar diversos tipos de arquivos, seja na hora da compilação ou arquivos de configuração.
Para manter um repositório limpo e organizado e que os arquivos que iremos manter seja realmente aqueles que nos interessa. É importante e crucial ignorarmos alguns arquivos que não impacta, diretamente, no nosso desenvolvimento.

O git mantem um arquivo especial só para isso. O nome do arquivo é .gitignore e é um arquivo que especifica quais arquivos intencionalmente não rastreados que deve ser ignorar. Os arquivos já rastreados pelo Git não são afetados.

### Exemplos .gitignore
Para facilitar, alguns repositórios do projeto aula-graduação já possuem um arquivo .gitignore padronizado. 
- Java [Exemplo de arquivo .gitignore](https://github.com/luiscarlosjunior/aulas-graduacao/blob/master/Programa%C3%A7%C3%A3o/.gitignore)
- Python [Exemplo de arquivo .gitignore](https://github.com/luiscarlosjunior/aulas-graduacao/blob/master/Data%20science/python/.gitignore)
- Entre diversos outros exemplo. Tutorial de como adicionar [.gitignore](https://git-scm.com/docs/gitignore)

### Regra geral de ambiente de desenvolvimento
Para as demais disciplinas atente-se a utilizar sempre ambiente reconhecidos pela simplicidade e de fácil desenvolvimento, em regra gerais pode-se utilizar editores mais sofisticados. Abaixo uma lista de editores de código fonte:
1. [Visual Studio Code](https://code.visualstudio.com/)
2. [Atom](https://atom.io/)
3. [Notepad++](http://notepad-plus-plus.org/)
4. Entre diversos outros.


### Referências
* https://medium.com/devzera/pull-requests-f492ab93e7dd
* https://www.freecodecamp.org/news/writing-good-commit-messages-a-practical-guide/
* https://www.youtube.com/watch?v=w3jLJU7DT5E
* https://medium.com/swlh/how-to-write-good-commit-messages-f37b5492fade
