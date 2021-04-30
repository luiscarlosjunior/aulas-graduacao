# PL/SQL Tutorial
O texto abaixo foi retirado do site [plsql tutorial](https://www.plsqltutorial.com/#:~:text=%20Basic%20PL%2FSQL%20Tutorial%20%201%20PL%2FSQL%20Block,you%20how%20to%20create%20PL%2FSQL%20functions.%20More%20)

> Antes de começar a praticar pl/sql há duas opções: Instalar dois componentes, uma banco de dado Oracle e o SGBD SQL Developer. Pode ser visto nesse [tutorial](https://www.plsqltutorial.com/plsql-setting-up-a-development-environment/)
> 
> E a opção, e menos trabalhasa, é acessar o site da Oracle que possui um simulador online [live scripts SQL](https://livesql.oracle.com/), antes é preciso fazer uma conta gratuita na oracle.

# O que é PL/SQL?
PL/SQL Tutorial
PL/SQL significa extensões de Linguagem Processual para a Linguagem de Consulta Estruturada (SQL). SQL é uma linguagem poderosa para consultar e atualizar dados em bancos de dados relacionais.

A Oracle criou o PL/SQL que amplia algumas limitações do SQL para fornecer uma solução mais abrangente para a construção de aplicativos de missão crítica em execução no banco de dados Oracle. Conhecendo mais informações sobre a linguagem PL/SQL.

Antes de começar, recomendamos a criação do banco de dados Oracle em seu sistema para ajudá-lo a praticar e aprender PL/SQL de forma eficaz.

# Tutorial básico PL/SQL
Assumimos que você tem o conhecimento fundamental de bancos de dados e SQL para iniciar nosso tutorial PL/SQL. Se este não for o caso, você precisa seguir o tutorial básico do SQL para ter um bom começo.

Esta seção é direcionada como um bom ponto de partida para aqueles que são novos no PL/SQL. No entanto, se você está muito familiarizado com o idioma e também quer olhar através desses tutoriais como uma atualização, você pode até encontrar algo útil que você nunca viu antes.

* Estrutura de blocos PL/SQL – apresenta a estrutura do bloco PL/SQL e mostra como desenvolver o primeiro programa PL/SQL em execução.
* Variáveis PL/SQL – mostra como trabalhar com variáveis PL/SQL, incluindo declarar, nomear e atribuir variáveis.
* Função PL/SQL – explica quais são as funções de PL/SQL e mostra como criar funções PL/SQL.
* Procedimento PL/SQL – discute procedimentos PL/SQL e mostra como criar procedimentos PL/SQL.
* PL/SQL Bloco Aninhado – explica o que é um bloco aninhado PL/SQL e como aplicá-lo na programação PL/SQL.
* Declaração PL/SQL IF – apresenta-lhe várias formas da instrução PL/SQL IF, incluindo , e declaração.IF-THENIF-THEN-ELSEIF-THEN-ELSIF
* Declaração de CASO PL/SQL – mostra como usar a instrução PL/SQL e a instrução pesquisada pl/SQL.CASE CASE
* Declaração DE LOOP PL/SQL – orienta como usar a instrução PL/SQL para executar um bloco de código repetidamente.LOOP
* PL/SQL WHILE Loop Statement – executa uma sequência de instruções com uma condição é verificada no início de cada iteração com a instrução loop.WHILE
* PL/SQL FOR Loop Statement – mostra como executar uma sequência de instruções no número fixo de vezes com a instrução loop.FOR
* Manipulação de exceções PL/SQL – ensina como lidar com exceção corretamente em PL/SQL, bem como mostra como definir sua própria exceção e elevá-la em seu código.
* PL/SQL Record – explica o registro PL/SQL e mostra como usar registros para gerenciar seus dados de forma mais eficaz.
* PL/SQL Cursor – abrange o conceito de cursor PL/SQL e o percorre como usar um cursor para fazer loop através de um conjunto de linhas e processar cada linha individualmente.
* Pacotes PL/SQL – mostra como criar um pacote PL/SQL que é um grupo de funções, procedimentos, tipos relacionados, etc.

----

# O que é PL/SQL

Resumo: neste tutorial, apresentaremos você à linguagem PL/SQL e discutiremos a história do PL/SQL, os elementos de linguagem e as vantagens que o PL/SQL traz para o desenvolvimento do Oracle.

PL/SQL é uma Linguagem Processual (PL) que amplia a Linguagem de Consulta Estruturada (SQL). Se você estiver programando Pascal ou Ada, encontrará muita sintaxe familiar em PL/SQL.

É difícil escrever aplicativos usando SQL apenas porque cada instrução SQL é executada de forma independente e tem pouco ou nenhum efeito uns sobre os outros. Para superar essa limitação, muitas vezes você tem que usar outras linguagens de programação, como C/C++, Perl, PHPe Java, usando interfaces de banco de dados padrão. A Oracle suporta essa abordagem quando você deseja desenvolver aplicativos que interagem com os bancos de dados Oracle.

Além disso, a Oracle também incentiva você a usar PL/SQL para trabalhar com bancos de dados Oracle porque o PL/SQL traz muitas vantagens.

## História pl/sql
A Oracle introduziu o PL/SQL (versão 1.0) em sua versão 6.0 do produto Oracle Database como a linguagem de script em SQL*Plus e linguagem de programação em SQL*Forms 3.

Desde a versão 7, a Oracle fez uma grande atualização para PL/SQL (versão 2.0) que fornece mais recursos como procedimentos, funções, pacotes, registros,coleções e algumas extensões de pacote.

Desde então, muitos recursos foram adicionados ao PL/SQL, como suporte a XML, pré-processador, I/O de arquivo, orientação de objetos, novas instruções, como e para torná-lo uma das linguagens de programação mais altamente estruturadas.CONTINUEGOTO

## Vantagens pl/SQL
PL/SQL é uma linguagem altamente estruturada
O PL/SQL fornece uma sintaxe muito expressiva que facilita para quem quer aprender PL/SQL. Se você está programando em outros idiomas, você pode se familiarizar com PL/SQL muito rapidamente e entender a intenção do código sem dificuldade.

Os recursos da linguagem PL/SQL incluem os seguintes elementos:

* Variáveis
* Estrutura dobloco, estrutura de bloco aninhada
* Declarações condicionais e sequenciais: SE, CASE, GOTO, CONTINUE e NULL
* Declarações de loop: ENQUANTO loop, FOR loop
* Exceção e manipulação de erros
* Tipos de dados: string, números, data e data e data, booleano e LOB
* registro
* coleção
* cursor
* Procedimentos, funções, pacotes
* Recursos de orientação de objetos
* SQL dinâmico e PL/SQL dinâmico
* PL/SQL é uma linguagem portátil e padrão para o desenvolvimento da Oracle
* Uma vez que você desenvolva um programa PL/SQL em um banco de dados Oracle, você pode movê-lo para os outros Bancos de Dados Oracle sem alterações, com a suposição de que as versões do banco de dados Oracle são compatíveis.

## PL/SQL é uma linguagem incorporada
Programas PL/SQL, como funções e procedimentos, são armazenados no banco de dados Oracle de forma compilada. Isso permite que aplicativos ou usuários compartilhem a mesma funcionalidade armazenada no banco de dados Oracle.

O PL/SQL também permite definir gatilhos que podem ser invocados automaticamente em resposta a eventos específicos em tabelas associadas.

PL/SQL é uma linguagem de alto desempenho dentro dos bancos de dados Oracle
A Oracle adiciona muitos aprimoramentos ao PL/SQL para torná-lo mais eficiente para interagir com os bancos de dados Oracle.
