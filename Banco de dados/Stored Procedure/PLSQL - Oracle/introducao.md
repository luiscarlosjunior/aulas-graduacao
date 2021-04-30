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

* **Estrutura de blocos PL/SQL** – apresenta a estrutura do bloco PL/SQL e mostra como desenvolver o primeiro programa PL/SQL em execução.
* **Variáveis PL/SQL** – mostra como trabalhar com variáveis PL/SQL, incluindo declarar, nomear e atribuir variáveis.
* **Função PL/SQL** – explica quais são as funções de PL/SQL e mostra como criar funções PL/SQL.
* **Procedimento PL/SQL** – discute procedimentos PL/SQL e mostra como criar procedimentos PL/SQL.
* **PL/SQL Bloco Aninhado** – explica o que é um bloco aninhado PL/SQL e como aplicá-lo na programação PL/SQL.
* **Declaração PL/SQL IF** – apresenta-lhe várias formas da instrução PL/SQL IF, incluindo , e declaração.IF-THENIF-THEN-ELSEIF-THEN-ELSIF
* **Declaração de CASO PL/SQL** – mostra como usar a instrução PL/SQL e a instrução pesquisada pl/SQL.CASE CASE
* **Declaração DE LOOP PL/SQL** – orienta como usar a instrução PL/SQL para executar um bloco de código repetidamente.LOOP
* **PL/SQL WHILE Loop Statement** – executa uma sequência de instruções com uma condição é verificada no início de cada iteração com a instrução loop.WHILE
* **PL/SQL FOR Loop Statement** – mostra como executar uma sequência de instruções no número fixo de vezes com a instrução loop.FOR
* **Manipulação de exceções PL/SQL** – ensina como lidar com exceção corretamente em PL/SQL, bem como mostra como definir sua própria exceção e elevá-la em seu código.
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

----

# Introdução da estrutura do bloco PL/SQL e do bloco anônimo

As unidades do programa PL/SQL organizam o código em blocos. Um bloco sem nome é conhecido como um bloco anônimo. O bloco anônimo é a unidade mais simples em PL/SQL. Ele é chamado de bloco anônimo porque não é salvo no banco de dados Oracle.

Um bloco anônimo é um uso único e útil em certas situações, como a criação de unidades de teste. O seguinte ilustra a sintaxe de bloco anônimo:

```sql
[DECLARE]
   Declaration statements;
BEGIN
   Execution statements;
  [EXCEPTION]
      Exception handling statements;
END;
```
/

## Estrutura do bloco PL/SQL
O bloco anônimo tem três seções básicas que são a declaração, execução e tratamento de exceção. Apenas a seção de execução é obrigatória e as outras são opcionais.

* A **seção de declaração** permite definir tipos de dados, estruturas e variáveis. Muitas vezes, você declara variáveis na seção de declaração dando-lhes nomes, tipos de dados e valores iniciais.
* A **seção de execução** é necessária em uma estrutura de bloco e deve ter pelo menos uma declaração. A seção de execução é o lugar onde você coloca o código de execução ou código lógico de negócios. Você pode usar declarações processuais e SQL dentro da seção de execução.
* A **seção de tratamento de exceção** está começando com a palavra-chave EXCEÇÃO. A seção de exceção é o lugar que você coloca o código para lidar com exceções. Você pode pegar ou lidar com exceções na seção de exceção.

## Exemplo de estrutura de bloco PL/SQL
Vamos dar uma olhada no bloco PL/SQL mais simples que não faz nada.

```sql
BEGIN
   NULL;
END;
```

Se você executar o bloco anônimo acima, você verá que ele emite uma mensagem dizendo:

>PL/SQL procedure successfully completed.
Porque a declaração null não faz nada.

### Para exibir a saída do banco de dados na tela, você precisa:

Primeiro, use o comando **SET SERVEROUTPUT ON** para instruir o SQL Developer a ecoar a saída do banco de dados após a execução do bloco PL/SQL. O **SET SERVEROUTPUT ON** é o comando SQL\*Plus, que não está relacionado ao PL/SQL.

Em segundo lugar, use o procedimento para produzir uma string na tela. DBMS_OUTPUT.PUT_LINE

O exemplo a seguir exibe uma mensagem Hello PL/SQL em uma tela usando SQL*Plus:

```sql
SET SERVEROUTPUT ON SIZE 1000000
BEGIN
   DBMS_OUTPUT.PUT_LINE('Hello PL/SQL');
END;
/
```

Neste exemplo, basta usar a parte de execução para executar o código. Você aprenderá como declarar variáveis e lidar com exceções nos próximos tutoriais.

----

# Variáveis PL/SQL
Resumo: neste tutorial, você aprenderá sobre variáveis PL/SQL que ajudam você a manipular dados em programas PL/SQL.

Em PL/SQL, uma variável é um nome significativo de um local de armazenamento temporário que suporta um determinado tipo de dados em um programa. Antes de usar uma variável, você precisa declará-la primeiro na seção declaração de um bloco PL/SQL.

## Regras de nomeação de variáveis PL/SQL
Como outras linguagens de programação, uma variável em PL/SQL deve seguir as regras de nomeação da seguinte forma:

* O nome da variável deve ser inferior a 31 caracteres. Tente torná-lo o mais significativo possível dentro de 31 caracteres.
* O nome da variável deve começar com uma letra ASCII. Pode ser maiústo ou maiús no máximo. Observe que o PL/SQL é insensível a maiões, o que significa e consulte a mesma variável.v_dataV_DATA
* Seguido pelo primeiro caractere estão qualquer número, sublinhados ( ), e caracteres dollar sign () . Mais uma vez, não torne suas variáveis difíceis de ler e difíceis de entender._$

### Convenção de nomeação de variáveis PL/SQL
É altamente recomendável que você siga as convenções de nomeação listadas na tabela a seguir para tornar as variáveis óbvias nos programas PL/SQL:

|prefixo	| tipo de dado |
|----    |----
|v_	   |VARCHAR2
|n_	   |NUMBER
|t_	   |TABLE
|r_	   |ROW
|d_	   |DATE
|b_	   |BOOLEAN

## Declaração de Variáveis PL/SQL
   Para declarar uma variável, você usa um nome variável seguido do tipo de dados e rescindido por um ponto e vírgula ( ). Você também pode adicionar explicitamente uma restrição de comprimento ao tipo de dados entre parênteses. O seguinte ilustra alguns exemplos de declarações de variáveis em um bloco anônimoPL/SQL :;

```sql
DECLARE
   v_nome varchar2(20);
   v_sobre_name varchar2(20);
   n_id number;
   d_nascimento date;
BEGIN
   NULL;
END;
``` 
## Âncoras variáveis PL/SQL
No programa PL/SQL, uma das tarefas mais comuns é selecionar valores de colunas em uma tabela em um conjunto de variáveis. Caso os tipos de dados das colunas da tabela mudem, você tem que alterar o programa PL/SQL para tornar os tipos das variáveis compatíveis com as novas alterações.

O PL/SQL fornece um recurso muito útil chamado âncoras variáveis. Refere-se ao uso da palavra-chave para declarar que uma variável com o tipo de dados está associada ao tipo de dados de uma coluna de uma determinada coluna em uma tabela. %TYPE

```sql
DECLARE
  v_primeiro_nome    TABELA_EMPREGADO.PRIMEIRO_NOME%TYPE;
  v_sobre_name       TABELA_EMPREGADO.SOBRE_NOME%TYPE;
  n_id               TABELA_EMPREGADO.ID%TYPE;
  d_data_nascimento  TABELA_EMPREGADO.NASCIMENTO%TYPE;
BEGIN
  NULL;
END;
/
```

A variável tem um tipo de dados que é o mesmo que o tipo de dados da coluna na tabela. Caso o tipo de dados da coluna mude, o tipo da variável herda automaticamente o novo tipo de dados da coluna.

# Atribuição variável PL/SQL
Em PL/SQL, para atribuir um valor ou uma variável a outro, você usa o operador de atribuição ( ) que é um cólon( ) seguido pelo sinal igual( ).:=: =

Por favor, veja a lista de códigos abaixo para obter uma melhor compreensão:

```sql
DECLARE
  v_primeiro_nome    TABELA_EMPREGADO.PRIMEIRO_NOME%TYPE;
  v_sobre_name       TABELA_EMPREGADO.SOBRE_NOME%TYPE;
  n_id               TABELA_EMPREGADO.ID%TYPE;
  d_data_nascimento  TABELA_EMPREGADO.NASCIMENTO%TYPE;
BEGIN
   v_primeiro_nome := 'Mary';
   v_sobre_name := 'Junho';
   d_data_nascimento := to_date('19700101','YYYYMMDD');
END;
/
```

No exemplo acima, atribuímos a variável, à variável e resultado da função à variável.Maryv_first_nameJanev_last_nameto_dated_hire_date

Você pode usar a instrução para atribuir um valor a uma variável. A cláusula move os valores da lista de colunas da consulta para variáveis PL/SQL correspondentes. INTO SELECT INTO SELECT

```sql
SET SERVEROUTPUT ON SIZE 1000000;
DECLARE
  v_primeiro_nome    TABELA_EMPREGADO.PRIMEIRO_NOME%TYPE;
  v_sobre_name       TABELA_EMPREGADO.SOBRE_NOME%TYPE;
  n_id               TABELA_EMPREGADO.ID%TYPE;
  d_data_nascimento  TABELA_EMPREGADO.NASCIMENTO%TYPE;
BEGIN
   SELECT employee_id,
          primerio_nome,
          sobre_nome,
          nascimento
   INTO n_employee_id,
        v_primeiro_nome,
        v_sobre_nome,
        d_data_nascimento
   FROM TABELA_EMPREGADO
   WHERE id = 200;

   DBMS_OUTPUT.PUT_LINE(v_primeiro_nome);
   DBMS_OUTPUT.PUT_LINE(v_sobre_nome);
   DBMS_OUTPUT.PUT_LINE(d_data_nascimento);
END;
/
```

## Variáveis de inicialização
Quando você declara uma variável, seu valor é unnitializado e, portanto, é . Você pode inicializar uma variável de valor na seção de declaração usando atribuição variável.NULL

Veja o exemplo a seguir:

```sql
DECLARE
  n_id TABELA_EMPREGADO.ID%TYPE := 200;
  d_data_nascimento TABELA_EMPREGADO.NASCIMENTO%TYPE := to_date('19700101','YYYYMMDD');
BEGIN
   NULL;
END;
/
```

Em PL/SQL, significa um valor desconhecido para que tenha algumas características especiais da seguinte forma:NULL

* NULL não é igual a nada, mesmo a si mesmo.NULL
* NULL não é maior do que ou menos do que qualquer outra coisa, mesmo .NULL
* Você não pode usar operador lógico igual () ou ( ) com . Você deve usar o SQL IS NULL ou NÃO É NULO para testar os valores NULL \=\<\> \NULL

Nesta seção, vimos como declarar, atribuir e inicializar variáveis PL/SQL. Também o percorremos como declarar variáveis PL/SQL usando âncoras variáveis para tornar seu código mais flexível e adaptável às alterações nas colunas das tabelas de banco de dados.

----

# PL/SQL - Operadores
   O texto a seguir foi retirado do site: 
   Iremos discutiremos **operadores em PL/SQL**. Um operador é um símbolo que diz ao compilador para realizar manipulação matemática ou lógica específica. A linguagem PL/SQL é rica em operadores incorporados e fornece os seguintes tipos de operadores. 

* Operadores de aritmética
* Operadores relacionais
* Operadores de comparação
* Operadores lógicos
* Operadores de cordas

Aqui, entenderemos os operadores aritméticos, relacionais, comparativos e lógicos um a um. Os operadores string serão discutidos em um capítulo posterior − PL/SQL - Strings.

## Operadores de Aritmética
A tabela a seguir mostra todos os operadores de aritmética apoiados pelo PL/SQL. Vamos assumir que a variável A detém 10 e a variável B contém 5, então

|Operador   |	Descrição	                           |exemplo
|----       |----                                     |----
|+	         |Adiciona dois operandos	               |A + B dará 15
|-	         |Subtrai segundo operando do primeiro	   |A - B vai dar 5
|*	         |Multiplica as duas operações	            |A * B dará 50
|/	         |Divide o numerador por de-numerador	   |A / B dará 2
|**	      |Operador de exponenciação, eleva um operando ao poder de outro	|A ** B vai dar 100000

## Operadores Relacionais
Operadores relacionais comparam duas expressões ou valores e retornam um resultado booleano. A tabela a seguir mostra todos os operadores relacionais apoiados pelo PL/SQL. Vamos assumir que a variável A detém 10 e a variável B contém 20, então −

### Mostrar exemplos

|operador	|descrição	   |exemplo
|----       |----          |----
|=          |Verifica se os valores de dois operandos são iguais ou não, se sim, <br /> então a condição se torna verdadeira.|	(A = B) não é verdade.
|!= <br /> <> <br /> ~= | Verifica se os valores de dois operandos são iguais ou não, se os <br /> valores não são iguais, então a condição se torna verdadeira.|	(A != B) é verdade.
|>	|Verifica se o valor do operando esquerdo é maior do que o valor do operand direito, se sim, <br />então a condição se torna verdadeira.|	(A > B) não é verdade.
|<	|Verifica se o valor do operand esquerdo é menor do que o valor do operand direito, se sim, <br />então a condição se torna verdadeira.|	(A < B) é verdade.
|>=	|Verifica se o valor do operando esquerdo é maior ou igual ao valor do operand direito, se sim, <br />então a condição se torna verdadeira.|	(A >= B) não é verdade.
|<=	|Verifica se o valor do operando esquerdo é menor ou igual ao valor do operand direito, se sim, <br />então a condição se torna verdadeira.|	(A <= B) é verdade


## Operadores de Comparação
Os operadores de comparação são usados para comparar uma expressão com outra. O resultado é sempre VERDADEIRO, FALSO ou NULO.

### Exemplos

|operador	|descrição	|exemplo
|----       |----       |-----
|LIKE       |O operador LIKE compara um valor de caractere, string ou CLOB a um padrão e retorna TRUE se o valor corresponder <br /> ao padrão e FALSO se não o fizer.	  | Se 'Zara Ali' LIKE 'Z% A_i' retorna verdadeiro, enquanto, 'Nuha Ali' LIKE 'Z% A_i' devolve um falso.
|BETWEEN   |O operador BETWEEN testa se um valor está em uma faixa especificada. x ENTRE a E b significa que x >= a e x <= b.|Se x = 10 então, x entre 5 e 20 retorna verdadeiro, <br /> x entre 5 e 10 retorna verdadeiro, <br />mas x entre 11 e 20 retorna falso.
|IN         |Os testes do operador IN definem a adesão. x IN (conjunto) significa que x é igual a qualquer membro do conjunto.|Se x = 'm' então, x em ('a', 'b', 'c') retorna falso, mas x em ('m', 'n', 'o') retorna verdadeiro.
|IS NULL    |O operador **IS NULL** retorna o valor **BOOLEAN TRUE** se seu operando for NULO ou FALSO se não for NULO. <br />Comparações envolvendo valores NULL sempre rendem NULO.  |Se x = 'm', então 'x é nulo' retorna **falso**.

## Operadores Lógicos
A tabela a seguir mostra os operadores lógicos suportados pelo PL/SQL. Todos esses operadores trabalham em operações booleanas e produzem resultados booleanos. Vamos supor que a variável A é verdadeira e a variável B é falsa, então −

### Exemplos

|operador	|descrição	   |Exemplos
|----|----|----
|AND	|Chamado de operador lógico e. |Se ambos os operandos são verdadeiros, então a condição se torna verdadeira.	(A AND B) é falso.
|OR   |Chamado de operador lógico. |Se algum dos dois operando é verdade, então a condição se torna verdadeira.	(A OR B) é verdade.
|NOT  |Chamado de operador lógico NÃO. Usado para reverter o estado lógico de seu operando. |Se uma condição for verdadeira, então o operador Logical NOT o tornará falso.	NOT(A AND B) é verdade.

## Precedência do operador PL/SQL

A precedência do operador determina o agrupamento de termos em uma expressão. Isso afeta a forma como uma expressão é avaliada. Certos operadores têm maior precedência do que outros; por exemplo, o operador de multiplicação tem maior precedência do que o operador de adição.

Por exemplo, **x = 7 + 3 * 2**; aqui, x é atribuído **13**, não **20** porque operador * tem maior precedência do que +, por isso primeiro é multiplicado com **3*2** e depois adiciona em 7.

Aqui, operadores com maior precedência aparecem na parte superior da tabela, aqueles com os mais baixos aparecem na parte inferior. Dentro de uma expressão, operadores de maior precedência serão avaliados primeiro.

A precedência dos operadores é a seguinte:** =, <, >, <=, >=, <>, !=, ~=, ^=, É NULO, LIKE, BETWEEN, IN**.

### Exemplos

|operador	|operação
|----       |----
|**	      |Exponenciação
|+, -	      |identidade, negação
|*, /	      |multiplicação, divisão
|+, -, ||	|adição, subtração, concatenação, comparação	
|NOT	      |negação lógica
|AND	      |conjunção
|OR	      |inclusão
