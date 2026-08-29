# Guia de aula (notas do professor) — Conceitos de Orientação a Objetos

> **Para que serve este documento.** É o texto de apoio da apresentação
> [`apresentacao-conceitos-oo.pptx`](apresentacao-conceitos-oo.pptx). Explica **em
> profundidade** cada conceito de cada slide, com o exemplo do **Melodia** (nosso streaming
> de música) expandido, os **autores** que definem cada ideia, os **erros comuns** e um
> bloco **"Perguntas prováveis dos alunos"** com respostas. A meta é você conseguir
> responder a qualquer dúvida que surja em sala.

> **Como ler.** As seções seguem a ordem dos slides. Você pode ler tudo antes da aula ou
> consultar a seção específica quando um aluno perguntar. No fim há um
> [banco de perguntas frequentes](#banco-de-perguntas-frequentes-faq) consolidado.

**Domínio de referência (Melodia).** Um serviço de streaming de música. As classes principais:
`Usuario` (abstrata) → `Ouvinte` e `Artista`; `Musica`, `Album`, `Playlist`; `Assinatura`
com `Plano` (FREE, PREMIUM, FAMILIA) e `StatusAssinatura` (ATIVA, SUSPENSA, CANCELADA);
`PlataformaStreaming` (a fachada que coordena tudo). O código está em
[`../projeto-base-java/`](../projeto-base-java/).

---

## Índice

1. [Origens da OO e principais autores](#1-origens-da-oo-e-principais-autores)
2. [Motivação: estruturada × OO](#2-motivação-programação-estruturada--orientação-a-objetos)
3. [A ideia central: objetos e mensagens](#3-a-ideia-central-objetos-que-trocam-mensagens)
4. [Objeto](#4-objeto)
5. [Classe (e a diferença para o objeto)](#5-classe-e-a-diferença-para-o-objeto)
6. [Diagrama de objetos (retrato em memória)](#6-diagrama-de-objetos-um-retrato-em-memória)
7. [Atributo e estado](#7-atributo-e-estado)
8. [Operação, método e mensagem](#8-operação-método-e-mensagem)
9. [Exemplo integrado: a classe Assinatura](#9-exemplo-integrado-a-classe-assinatura)
10. [Os quatro pilares — visão geral](#10-os-quatro-pilares--visão-geral)
11. [Pilar 1 — Abstração](#11-pilar-1--abstração)
12. [Pilar 2 — Encapsulamento](#12-pilar-2--encapsulamento-e-ocultamento-de-informação)
13. [Pilar 3 — Herança](#13-pilar-3--herança)
14. [Pilar 4 — Polimorfismo](#14-pilar-4--polimorfismo)
15. [Composição sobre herança](#15-composição-sobre-herança)
16. [Modelo anêmico e responsabilidade](#16-modelo-anêmico-e-responsabilidade)
17. [Vantagens, desvantagens e limites da OO](#17-vantagens-desvantagens-e-limites-da-oo)
18. [Exercícios resolvidos](#18-exercícios-resolvidos-comentados)
19. [Exercício final no projeto](#19-exercício-final-no-projeto-do-grupo)
20. [Banco de perguntas frequentes (FAQ)](#banco-de-perguntas-frequentes-faq)
21. [Referências](#referências)

---

## 1. Origens da OO e principais autores

**O que está no slide.** Uma linha do tempo dos autores: Simula 67 (Dahl & Nygaard),
Smalltalk (Alan Kay), Booch, Rumbaugh (OMT), Meyer, e a unificação na UML (Booch, Rumbaugh e
Jacobson).

**Explicação aprofundada.** A OO não foi inventada por uma pessoa nem de uma vez:

- **Simula 67** (Ole-Johan Dahl e Kristen Nygaard, Noruega) foi a **primeira linguagem** a ter
  **classes e objetos**, criada para *simular* sistemas do mundo real (filas, navios, fábricas).
  A palavra "classe" nasce aqui. Rendeu o Prêmio Turing aos autores.
- **Smalltalk** (Alan Kay e equipe, Xerox PARC, anos 1970) tornou a OO um paradigma completo.
  Kay **cunhou o termo "object-oriented"** e defende que a essência da OO **não** são classes,
  e sim a **troca de mensagens** entre objetos autônomos (a metáfora é uma "sociedade de
  células que se comunicam").
- Nos anos 1980–90 surgiram **métodos** para *analisar e projetar* sistemas OO: o método de
  **Booch**, o **OMT** de **Rumbaugh** (forte em modelagem de dados) e os **casos de uso** de
  **Jacobson**. Eram concorrentes, cada um com sua notação.
- Em **1994–97**, os "três amigos" (Booch, Rumbaugh, Jacobson) uniram suas notações na **UML**
  (*Unified Modeling Language*), padronizada pela OMG. É a notação que usamos no curso.
- Em paralelo, **Bertrand Meyer** (linguagem Eiffel) formalizou o **projeto por contrato**
  (*design by contract*) e defendeu fortemente o **ocultamento de informação**.

**Por que isso importa para o aluno.** Saber a origem evita decorar: quando entendemos que
"classe" veio de *simular o mundo real* e que "mensagem" é a ideia central de Kay, os conceitos
param de ser arbitrários.

**❓ Perguntas prováveis dos alunos**

- *"Java foi a primeira linguagem OO?"* — Não. Java é de 1995. Antes vieram Simula (1967),
  Smalltalk (1970s), C++ (1983). Java popularizou a OO na indústria, mas não a inventou.
- *"UML é uma linguagem de programação?"* — Não. É uma **linguagem de modelagem** (desenhos
  padronizados). Não se "executa" UML; ela documenta o sistema. Veremos os 13 diagramas nas
  próximas aulas.
- *"Qual a diferença entre um método e uma metodologia?"* — Aqui "método" (Booch, OMT) é
  sinônimo de **metodologia/processo** de análise e projeto — não confundir com "método" no
  sentido de função de uma classe (isso vem na seção 8).

---

## 2. Motivação: programação estruturada × orientação a objetos

**O que está no slide.** Uma tabela comparando a programação estruturada com a OO.

**Explicação aprofundada.** Na **programação estruturada** (Pascal, C), o programa é organizado
em torno de **funções (procedimentos)**, e os **dados** ficam em estruturas separadas, muitas
vezes **globais**. O problema aparece quando o sistema cresce: se você muda a estrutura de um
dado (por exemplo, o formato de uma música), precisa caçar **todas** as funções que mexem
naquele dado e corrigi-las. É o **efeito-cascata**.

Na **OO**, o dado e as funções que o manipulam moram **juntos**, dentro do **objeto**. Mudar
como uma `Musica` guarda sua duração afeta só a classe `Musica` — o resto do sistema fala com
ela por operações (`duracaoFormatada()`), não com o campo cru. A pergunta central muda de
*"que passos executar?"* para *"quem é o responsável por este dado/comportamento?"*.

**Analogia.** Estruturada = uma cozinha onde os ingredientes (dados) ficam num depósito e as
receitas (funções) noutro prédio; qualquer mudança no ingrediente obriga a revisar receitas
espalhadas. OO = cada prato é um "chef" que guarda seus próprios ingredientes e sabe se
preparar; você só pede "faça-se".

**Erro comum.** Achar que "usar classes" já é fazer OO. Dá para escrever código estruturado
*dentro* de classes (uma classe gigante cheia de funções e dados públicos). OO de verdade é
**distribuir responsabilidades** entre objetos que protegem seus dados.

**❓ Perguntas prováveis dos alunos**

- *"Então a programação estruturada está errada/ultrapassada?"* — Não. Ela é excelente para
  problemas centrados em algoritmo/processamento (ver [seção 17](#17-vantagens-desvantagens-e-limites-da-oo)).
  O paradigma se escolhe pelo problema.
- *"OO é mais rápida que estruturada?"* — Em geral **não** em tempo de execução (há uma
  pequena indireção). O ganho da OO é em **manutenção e organização**, não em desempenho bruto.

---

## 3. A ideia central: objetos que trocam mensagens

**O que está no slide.** A frase de Alan Kay (a essência da OO é a troca de mensagens) e a
cadeia "reproduzir uma música": `ana → plataforma.reproduzir(...) → musica.registrarReproducao()
→ artista.creditarRoyalty(...)`.

**Explicação aprofundada.** Um sistema OO funciona como uma **rede de colaboração**: nenhum
objeto faz tudo; cada um tem uma responsabilidade e **delega** o resto enviando mensagens. No
exemplo:

1. A ouvinte `ana` pede à `plataforma` para reproduzir "Cometa".
2. A `plataforma` verifica se a assinatura de `ana` está ativa (regra de negócio) e manda a
   `Musica` **registrar mais uma reprodução**.
3. Registrada a reprodução, a `plataforma` credita um **royalty** ao `Artista` (Banda Nébula).

Repare que a `Musica` **não sabe** quem a mandou tocar, e o `Artista` **não sabe** de qual
música veio o royalty — cada um só cumpre sua parte. Isso é **baixo acoplamento**: as peças se
encaixam sem depender dos detalhes internos umas das outras.

**Vocabulário preciso.** Uma **mensagem** é composta de três coisas: **objeto emissor** +
**operação (método) invocada** + **argumentos**. Ex.: em `playlist.adicionar(cometa)`, o
emissor é `playlist`, a operação é `adicionar`, o argumento é `cometa`.

**Erro comum.** Confundir "mensagem" com "e-mail/notificação". Aqui "mensagem" = **chamada de
método**. É jargão de Smalltalk que sobreviveu no vocabulário de OO.

**❓ Perguntas prováveis dos alunos**

- *"Mensagem e chamada de método são a mesma coisa?"* — Na prática de Java, **sim**. "Enviar a
  mensagem `reproduzir` ao objeto `plataforma`" = "chamar o método `reproduzir()` de
  `plataforma`". Kay preferia "mensagem" para enfatizar a autonomia do objeto que recebe.
- *"Por que a plataforma não faz tudo sozinha?"* — Poderia, mas viraria uma **classe que faz
  tudo** (God class): difícil de testar e manter. Distribuir responsabilidades é o que mantém o
  sistema sustentável (ver [seção 16](#16-modelo-anêmico-e-responsabilidade)).

---

## 4. Objeto

**Definição formal (Booch).** Um **objeto** possui **estado**, **comportamento** e
**identidade**. A identidade o distingue de todos os outros, **mesmo que tenham o mesmo estado**.

**Explicação aprofundada.** As três propriedades:

- **Identidade** — o objeto *é ele mesmo*. Dois objetos podem ter exatamente os mesmos valores
  e ainda assim serem distintos. Em Java, identidade se compara com `==` (mesma referência);
  igualdade de conteúdo se compara com `equals()`.
- **Estado** — os valores atuais dos atributos naquele instante (ex.: a ouvinte `ana` está no
  plano PREMIUM e segue a Banda Nébula).
- **Comportamento** — as operações que o objeto sabe executar (`criarPlaylist()`,
  `seguir(artista)`, `reproduzir(musica)`).

**Exemplo no Melodia (expandido).** Pense em duas ouvintes, `ana` e `bia`, **ambas** no plano
PREMIUM, **ambas** seguindo a Banda Nébula. Elas têm o **mesmo estado** naquele instante — e
ainda assim são **objetos diferentes**: cancelar a conta de `ana` não afeta `bia`. É a
identidade que garante isso. (Analogia: dois copos idênticos na prateleira — iguais, mas não o
mesmo copo.)

**Erros comuns.** (1) Achar que "objetos iguais são o mesmo objeto" — não são. (2) Confundir o
objeto (que existe em execução, na memória) com a classe (que existe em projeto, no código).

**❓ Perguntas prováveis dos alunos**

- *"Objeto e instância são a mesma coisa?"* — **Sim.** "Instância da classe `Ouvinte`" é um
  jeito mais técnico de dizer "um objeto do tipo `Ouvinte`".
- *"Todo objeto tem que ter comportamento?"* — Idealmente sim (senão vira "saco de dados" — ver
  [modelo anêmico](#16-modelo-anêmico-e-responsabilidade)). Existem exceções legítimas: objetos
  que só transportam dados (DTOs, `record` em Java), desde que a regra de negócio viva em
  **alguma** classe.
- *"Como um objeto nasce?"* — Com o operador `new` chamando o **construtor**: `new
  Ouvinte("Ana", "ana@email.com")`. Cada `new` cria um objeto novo, com sua própria identidade.
- *"O que acontece quando o objeto 'morre'?"* — Em Java, quando ninguém mais o referencia, o
  **coletor de lixo** (*garbage collector*) libera a memória automaticamente.

---

## 5. Classe (e a diferença para o objeto)

**Definição formal (Rumbaugh et al.).** Uma **classe** descreve um conjunto de objetos que
compartilham os mesmos **atributos**, **operações** e **relacionamentos**. O objeto é uma
**instância** da classe.

**Explicação aprofundada.** A classe é o **molde (o tipo)**; o objeto é a **peça moldada (a
instância)**. A classe existe **uma vez**, em tempo de projeto (você a escreve no código). Os
objetos existem **aos milhares**, em tempo de execução, cada um com seus próprios valores.

**Exemplo no Melodia (expandido).** A classe `Musica` define que **toda** música tem `titulo`,
`duracaoSegundos`, `reproducoes` e sabe `registrarReproducao()` e `duracaoFormatada()`. A partir
desse molde, o catálogo cria vários objetos: `"Cometa"` (1.240 reproduções), `"Gravidade Zero"`
(980), `"Silêncio Sideral"` (512). Todas seguem o molde, mas cada uma tem seus próprios valores
— como cópias de uma mesma partitura, cada execução com seu público.

**Metáfora clássica.** Classe = planta da casa; objetos = casas construídas a partir da planta.
Uma planta, muitas casas; cada casa com seus moradores.

**Erro comum (o nº 1 do iniciante).** Confundir classe com objeto — dizer "o objeto `Musica`"
quando `Musica` é a **classe**; o objeto é `"Cometa"`.

**❓ Perguntas prováveis dos alunos**

- *"Classe é o mesmo que tipo?"* — Na prática, em Java, sim: cada classe define um **tipo**.
  (Interfaces e enums também definem tipos — veremos adiante.)
- *"Posso ter uma classe sem nenhum objeto?"* — Sim (ex.: uma classe utilitária só com métodos
  estáticos, ou uma classe abstrata que só serve de base). Mas o comum é a classe existir para
  gerar objetos.
- *"Quantos objetos uma classe pode ter?"* — Quantos você criar com `new` — zero, um ou
  milhões. A classe é o molde; a quantidade de peças é ilimitada.
- *"O que é uma classe abstrata?"* — Uma classe que serve **só de molde para outras** e **não
  pode ser instanciada** diretamente (ex.: `Usuario` — ver [seção 13](#13-pilar-3--herança)).

---

## 6. Diagrama de objetos (um retrato em memória)

**O que está no slide.** Um "instantâneo": `ana : Ouvinte` **possui** `assAna : Assinatura`
(PREMIUM, ATIVA); `ana` **monta** `favoritas : Playlist`, que **contém** `cometa : Musica`.

**Explicação aprofundada.** Enquanto o **diagrama de classes** mostra os *tipos* (a regra geral
do sistema), o **diagrama de objetos** mostra *instâncias concretas* em um **momento
específico** — uma fotografia. A notação: o nome do objeto vem **sublinhado** no formato
`nomeObjeto : Classe`, e as ligações entre objetos são **links** (instâncias das associações).

**Para que serve.** (1) **Ilustrar** um cenário para alguém ("veja como fica quando a Ana tem
uma playlist com uma música"); (2) **validar** o modelo de classes (se você não consegue montar
um objeto válido para um caso real, o modelo está errado); (3) **depurar** estruturas de dados
complexas.

**Erro comum.** Tratar o diagrama de objetos como documentação permanente do sistema. Ele
retrata **um instante** e envelhece rápido — use para comunicar um ponto específico.

**❓ Perguntas prováveis dos alunos**

- *"Qual a diferença para o diagrama de classes?"* — Classes = tipos (regra geral, com
  multiplicidades como `1`, `*`). Objetos = instâncias reais (valores concretos, sem
  multiplicidade, porque tudo é contável ali). Veremos ambos em detalhe nas aulas 09 e 10.
- *"Por que o nome vem sublinhado?"* — É a convenção da UML para indicar que aquilo é uma
  **instância** (um objeto), e não uma classe.

---

## 7. Atributo e estado

**Definições.** Um **atributo** é uma **propriedade nomeada** que cada objeto guarda (um dado).
O **estado** de um objeto é o **conjunto dos valores** dos seus atributos em um dado instante.

**Explicação aprofundada.** Se a operação é o *verbo* do objeto (o que ele faz), o atributo é o
*substantivo/adjetivo* (o que ele é/tem). O **estado** é a "fotografia" desses valores agora — e
**muda com o tempo**, enquanto a identidade permanece.

**Exemplo no Melodia (expandido).**

| Classe | Atributos | Um estado possível |
|--------|-----------|--------------------|
| `Musica` | `titulo`, `duracaoSegundos`, `reproducoes` | `reproducoes = 1240` |
| `Assinatura` | `plano`, `status`, `proximaCobranca` | `status = ATIVA` |
| `Playlist` | `nome`, `dono`, `musicas` | `nome = "Favoritas"` |

A cada *play* de "Cometa", o atributo `reproducoes` sobe (o **estado** muda), mas o objeto
continua sendo a mesma música (a **identidade** não muda).

**Tipos de atributo (para responder dúvidas):**

- **De instância** — cada objeto tem o seu (`reproducoes` — cada música conta as suas).
- **De classe (estático)** — um só, compartilhado por todos (ex.: `ROYALTY_POR_REPRODUCAO`,
  uma constante da plataforma). Na UML aparece **sublinhado**.
- **Derivado** — calculado a partir de outros, não armazenado. Ex.: a duração total de uma
  playlist é a **soma** das faixas; não guardamos, calculamos quando pedem
  (`duracaoTotalSegundos()`). Na UML vem com uma barra: `/ duracaoTotal`.

**Erro comum.** Guardar um dado **derivado** e esquecer de atualizá-lo, criando inconsistência
(ex.: guardar "duração total" e esquecer de somar quando adicionam uma música). Regra: se dá
para calcular, calcule.

**❓ Perguntas prováveis dos alunos**

- *"Atributo é o mesmo que variável?"* — Quase. Um **atributo** é uma variável que **pertence a
  um objeto** (ou à classe). Variáveis locais (dentro de um método) não são atributos.
- *"Por que `reproducoes` não pode ser público?"* — Porque então qualquer código poderia
  alterá-lo sem controle e corromper o valor (ver [encapsulamento](#12-pilar-2--encapsulamento-e-ocultamento-de-informação)).
- *"E se um atributo for outra classe (ex.: a assinatura do ouvinte)?"* — Aí é um
  **relacionamento** (associação/composição). O `Ouvinte` tem um atributo do tipo `Assinatura`.
  Isso é modelado como associação (aula 05).

---

## 8. Operação, método e mensagem

**Definições.** Uma **operação** é um **serviço** que a classe oferece (o que seus objetos sabem
fazer). Sua **assinatura** (nome + parâmetros + retorno) é um **contrato**, na visão de **Meyer**
(*design by contract*). Uma **mensagem** é o ato de um objeto **invocar** a operação de outro.

**Explicação aprofundada.** Distinção que costuma cair em prova:

- **Operação** = a **declaração**, o contrato: *o quê* o objeto oferece. Ex.: `boolean
  seguir(Artista a)`.
- **Método** = a **implementação** concreta: *o como* (o corpo, o código).

Por que separar? Porque uma **mesma operação pode ter vários métodos** — um por subclasse. É
exatamente o que torna o **polimorfismo** possível ([seção 14](#14-pilar-4--polimorfismo)).

**Projeto por contrato (Meyer).** Toda operação tem um **contrato**: o que ela **exige**
(pré-condição) e o que **garante** (pós-condição). Ex.: `Playlist.adicionar(Musica m)` exige que
`m` não seja nula e garante que, ao final, `m` está na playlist. No código, isso vira validações
e exceções.

**Exemplo no Melodia (mensagens):**

```
playlist.adicionar(cometa);        // pede à playlist para incluir a música
musica.registrarReproducao();      // pede à música para contar mais um play
ouvinte.seguir(bandaNebula);       // pede ao ouvinte para seguir o artista
```

**Comando × consulta (boa prática, CQS).** Um bom método ou **muda o estado** (comando, ex.:
`registrarReproducao()`) **ou** **responde uma pergunta** (consulta, ex.: `getReproducoes()`),
mas não os dois ao mesmo tempo — assim ninguém é pego de surpresa por um efeito colateral
escondido.

**Erros comuns.** (1) Nomes vagos (`processar()`, `fazer()`, `handle()`) em vez de verbos do
domínio (`reproduzir`, `seguir`, `cobrar`). (2) Métodos com muitos parâmetros (4+), sinal de que
falta um objeto agrupando-os.

**❓ Perguntas prováveis dos alunos**

- *"Método e função é a mesma coisa?"* — Um **método** é uma **função que pertence a uma
  classe** e opera sobre o objeto (`this`). Em Java tudo é método (não há funções soltas).
- *"O que é a assinatura de um método?"* — Nome + lista de parâmetros (+ retorno, em muitos
  contextos). É o que identifica a operação e permite a **sobrecarga** (mesmo nome, parâmetros
  diferentes).
- *"Diferença entre operação e método, na prática?"* — Operação = contrato (o quê); método =
  implementação (o como). No dia a dia, muita gente usa como sinônimos; a distinção importa ao
  falar de **polimorfismo**.
- *"O que é `void`?"* — Significa que a operação **não retorna** valor (só executa uma ação),
  como `registrarReproducao(): void`.

---

## 9. Exemplo integrado: a classe Assinatura

**O que está no slide.** O código real da `Assinatura`: atributo `status` privado + operação
`reativar()` que **valida** antes de mudar o estado + `cancelar()` (estado final).

```java
public class Assinatura {
    private StatusAssinatura status = ATIVA;   // ATRIBUTO (estado protegido)

    public void reativar() {                    // OPERAÇÃO com regra
        if (status != SUSPENSA)
            throw new IllegalStateException("só reativa se estiver suspensa");
        status = ATIVA;                         // muda o ESTADO
    }
    public void cancelar() { status = CANCELADA; } // estado final
}
```

**Explicação aprofundada (juntando tudo).** Este pedaço reúne o vocabulário inteiro:

- `status` é o **atributo** (o **estado**), e é **privado** — ninguém mexe nele por fora.
- `reativar()` e `cancelar()` são **operações** (o **comportamento**).
- `assinatura.reativar()` é uma **mensagem** que alguém envia a este objeto.
- A regra "só reativa se estiver suspensa" é um **invariante** protegido pela operação: uma
  assinatura **cancelada não volta**, e uma **ativa** não é "reativada" por acidente. Estado
  inválido é **impossível** de alcançar por fora.

Isso já é uma prévia de duas coisas que veremos: **encapsulamento** (o dado protegido) e a
**máquina de estados** (ATIVA → SUSPENSA → ATIVA; qualquer → CANCELADA), tema da aula 14.

**Por que lançar exceção em vez de só ignorar?** Porque **falhar visivelmente** é melhor que
corromper silenciosamente. Se alguém tenta reativar uma assinatura ativa/cancelada, o programa
avisa (exceção) em vez de fingir que deu certo.

**❓ Perguntas prováveis dos alunos**

- *"O que é `StatusAssinatura`?"* — Um **enum**: um tipo com um conjunto **fechado** de valores
  (ATIVA, SUSPENSA, CANCELADA). Usar enum em vez de texto ("ativa") evita erros de digitação e
  dá segurança de tipo.
- *"Por que não usar um `boolean ativa`?"* — Porque há **três** estados, não dois. E quando você
  vê vários `boolean` de status numa classe (`ativa`, `cancelada`, `suspensa`), quase sempre há
  uma **máquina de estados escondida** pedindo para nascer.
- *"O que é `IllegalStateException`?"* — Uma exceção padrão do Java para dizer "a operação foi
  chamada num momento/estado em que não faz sentido". É a forma de **proteger o invariante**.
- *"Onde está esse código?"* — Em [`../projeto-base-java/`](../projeto-base-java/), pacote
  `assinatura`. É o mesmo do projeto.

---

## 10. Os quatro pilares — visão geral

**O que está no slide.** Tabela: Abstração (mostrar o essencial), Encapsulamento (proteger os
dados), Herança (reaproveitar código), Polimorfismo (a mesma ação varia).

**Explicação aprofundada.** Booch trata **abstração, encapsulamento, hierarquia (herança) e
tipagem/polimorfismo** como os elementos que caracterizam um modelo orientado a objetos. Uma
forma de guardar, com uma pergunta para cada:

| Pilar | Pergunta | No Melodia |
|-------|----------|------------|
| Abstração | Como mostro só o essencial? | `Usuario` genérico esconde detalhes |
| Encapsulamento | Como protejo os dados? | `reproducoes`/`status` privados |
| Herança | Como reaproveito código? | `Ouvinte`/`Artista` **é um** `Usuario` |
| Polimorfismo | Como a mesma ação varia? | `tipoDePerfil()` difere por tipo |

**Erro comum.** Tratar os pilares como slogans decorados. Eles são **ferramentas**: cada um
resolve um problema concreto (esconder complexidade, proteger dados, reusar, variar
comportamento).

**❓ Perguntas prováveis dos alunos**

- *"São sempre exatamente quatro?"* — A tradição fala em quatro pilares. Alguns autores separam
  "abstração" e "encapsulamento"; outros incluem "composição". O importante é entender cada
  ideia, não brigar pela contagem.
- *"Preciso usar todos em todo programa?"* — Não. Você usa o pilar que o problema pede.
  Encapsulamento é quase sempre útil; herança, só quando o "é um" existe.

---

## 11. Pilar 1 — Abstração

**Definição formal (Booch).** Uma **abstração** destaca as **características essenciais** de uma
entidade — as que a distinguem de todas as outras — e **ignora o que é irrelevante** para o
problema. (Ver também Liskov & Guttag sobre abstração e especificação.)

**Explicação aprofundada.** Abstrair é **recortar** o mundo. Há dois sentidos, e vale distinguir:

1. **Recorte do essencial.** Um ouvinte real tem altura, cidade, time de futebol... O sistema
   guarda só o que **serve ao problema**: nome, e-mail, assinatura, playlists. O resto é ruído.
2. **Generalização.** Criar um tipo genérico (`Usuario`) que representa vários específicos
   (`Ouvinte`, `Artista`). É a base da herança e das classes abstratas.

**Exemplo no Melodia (expandido).** O botão "play" abstrai um processo enorme: buscar o arquivo,
decodificar o áudio, gerenciar o *buffer* de rede, enviar ao alto-falante. Você usa
`reproduzir()` sem conhecer nada disso — a abstração te dá **o que importa** (tocar) e esconde
**o como**. No modelo, `Usuario` abstrai o que `Ouvinte` e `Artista` têm em comum (nome, e-mail).

**Cuidado (erro comum).** **Abstrair cedo demais.** Criar `Usuario`, `UsuarioBase`,
`AbstractUsuarioFactory` "porque um dia pode ter outro tipo" quando hoje só existe um. Isso é
complexidade especulativa (viola o princípio YAGNI). Regra prática: abstraia **na terceira vez**
que a variação aparecer, não na primeira.

**❓ Perguntas prováveis dos alunos**

- *"Abstração e encapsulamento são a mesma coisa?"* — Não. **Abstração** decide *o que* mostrar
  (o essencial). **Encapsulamento** é o mecanismo que *esconde o resto* e protege. Uma é a
  intenção; o outro é a técnica que a realiza.
- *"Classe abstrata é obrigatória para ter abstração?"* — Não. Abstração é um **conceito**
  (recortar o essencial), presente em qualquer classe bem modelada. Classe abstrata é só **uma
  forma** de abstração (generalização).
- *"Dá para abstrair demais?"* — Sim, e é um problema real (camadas inúteis que ninguém
  entende). Abstração é para **simplificar**; se está complicando, está errada.

---

## 12. Pilar 2 — Encapsulamento (e ocultamento de informação)

**Definição formal (Parnas / Meyer).** Encapsular é **esconder o estado interno** de um objeto,
expondo-o **apenas por operações controladas**. **Parnas (1972)** mostrou que cada módulo deve
**ocultar uma decisão de projeto** (*information hiding*), de modo que mudanças internas não
vazem para o resto do sistema. Assim o objeto garante seus **invariantes** (regras que valem
sempre).

**Explicação aprofundada.** Encapsulamento tem duas faces:

- **Ocultar** os detalhes (o atributo `private`), para que o mundo externo não dependa de como o
  objeto guarda seus dados.
- **Proteger** os invariantes, oferecendo operações que **validam** antes de mudar o estado.

**Exemplo no Melodia — o incidente das reproduções (expandido).** As **reproduções** de cada
música são usadas para calcular os **royalties dos artistas** e montar o **ranking Top 50**.
Imagine que `reproducoes` fosse um campo **público**. Um *job* de importação de dados, mal
escrito, faz `musica.reproducoes = 0` para "resetar" antes de recarregar — e falha no meio. De
repente, músicas populares aparecem com zero reproduções: o ranking quebra e os artistas
recebem royalties errados. A causa-raiz **não** foi o programador distraído; foi o **dado
exposto**. Correção: `reproducoes` vira `private` e só muda por `registrarReproducao()` (que só
**incrementa**). O erro fica **impossível** de acontecer.

**Mensagem central.** Encapsulamento não é purismo acadêmico: é o que **impede que o erro de uma
pessoa vire incidente de todos**. É a diferença entre "um dev errou" e "o sistema não permitiu
o erro".

**Como se faz em Java.** Atributos `private` + métodos de acesso **com regra**. Cuidado: expor
uma lista interna mutável (`return musicas;`) **vaza** o encapsulamento — devolva uma cópia
somente-leitura (`Collections.unmodifiableList(...)`), como o projeto faz.

**❓ Perguntas prováveis dos alunos**

- *"Encapsulamento é só criar get e set para tudo?"* — **Não!** Gerar `get/set` para todos os
  campos, sem regra nenhuma, é o **modelo anêmico** ([seção 16](#16-modelo-anêmico-e-responsabilidade))
  — encapsulamento "de fachada". O ponto é **proteger invariantes**, não gerar getters
  automáticos. Nem todo atributo precisa de setter.
- *"Qual a diferença entre `private`, `protected` e `public`?"* — `private`: só a própria
  classe. `protected`: a classe e suas subclasses. `public`: qualquer um. `~` (padrão de
  pacote): classes do mesmo pacote. A regra: comece **o mais restrito possível**.
- *"O que é um invariante?"* — Uma regra que deve valer **sempre** para o objeto (ex.: "as
  reproduções nunca diminuem", "uma assinatura cancelada não volta"). Encapsulamento existe para
  garantir invariantes.
- *"Ocultar informação não deixa o código mais difícil de usar?"* — Ao contrário: quem usa o
  objeto lida com **menos** coisas (só as operações públicas) e não pode quebrá-lo. Esconder
  detalhes **reduz** a carga cognitiva.

---

## 13. Pilar 3 — Herança

**Definição formal (Booch).** Herança permite que uma **subclasse** (classe filha)
**especialize** uma **superclasse** (classe pai), **reaproveitando** seus atributos e operações.
Vale o teste **"é um"**.

**Explicação aprofundada.** Em vez de copiar código, a subclasse **herda** o que a superclasse
já tem e **acrescenta ou altera** só o que muda. No Melodia:

```java
abstract class Usuario {          // superclasse (abstrata: não instanciável)
    protected String nome;
    public abstract String tipoDePerfil();
}
class Ouvinte extends Usuario {   // Ouvinte É UM Usuario
    public String tipoDePerfil() { return "Ouvinte"; }
}
class Artista extends Usuario {   // Artista É UM Usuario
    public String tipoDePerfil() { return "Artista"; }
}
```

`Usuario` é **abstrata**: não existe "usuário genérico" no sistema — todo usuário é um `Ouvinte`
ou um `Artista`. `Ouvinte` e `Artista` reaproveitam `nome` e o contrato `tipoDePerfil()`, e cada
uma dá sua própria resposta.

**O teste "é um".** Só use herança quando a frase "X **é um** Y" for verdadeira: "Ouvinte é um
Usuário" ✓. Se for "X **tem um** Y" ("Playlist **tem** Músicas"), **não** é herança — é
composição/agregação ([seção 15](#15-composição-sobre-herança)).

**`super`.** A subclasse pode chamar o construtor/métodos do pai com `super(...)` — é assim que
ela reaproveita a inicialização da superclasse.

**Erro comum (grave).** **Herança por preguiça**: herdar de uma classe só para reusar um método,
mesmo sem o "é um" (ex.: `Playlist extends ArrayList` "porque já tem `add`"). Isso amarra a
`Playlist` a **toda** a interface de `ArrayList` (incluindo métodos que não fazem sentido) e é
fonte clássica de bugs. Prefira **composição** (a `Playlist` **tem** uma lista por dentro).

**❓ Perguntas prováveis dos alunos**

- *"Java tem herança múltipla?"* — **Não** para classes (uma classe só herda de uma). Para
  **interfaces**, sim: uma classe pode implementar várias. (Herança múltipla de classes causa o
  "problema do diamante", que Java evitou.)
- *"Qual a diferença entre `extends` e `implements`?"* — `extends`: herda de uma **classe** (ou
  estende uma interface). `implements`: uma classe **cumpre o contrato** de uma **interface**.
- *"Classe abstrata × interface?"* — Classe abstrata pode ter **estado** (atributos) e métodos já
  implementados; representa um "é um" com código compartilhado. Interface é um **contrato** puro
  (o que a classe promete fazer); uma classe pode implementar várias. Interfaces vêm no módulo 07.
- *"`protected` para que serve na herança?"* — Permite que a subclasse acesse o atributo/método
  do pai, sem abri-lo ao mundo todo.

---

## 14. Pilar 4 — Polimorfismo

**Definição formal (Cardelli & Wegner / Booch).** Polimorfismo é a capacidade de uma **mesma
mensagem** produzir **comportamentos diferentes** conforme o **tipo real** do objeto que a
recebe (**ligação dinâmica**).

**Explicação aprofundada.** "Poli-morfismo" = "muitas formas". No Melodia, a mesma chamada
`usuario.tipoDePerfil()` responde `"Ouvinte"` ou `"Artista"` dependendo de **qual objeto** está
por trás da variável `usuario` — e essa decisão acontece **em tempo de execução** (ligação
dinâmica), não em tempo de compilação.

```java
Usuario u = new Ouvinte(...);
u.tipoDePerfil();   // -> "Ouvinte"
u = new Artista(...);
u.tipoDePerfil();   // -> "Artista"   (mesma linha, resposta diferente)
```

**Por que é poderoso.** Quem chama `tipoDePerfil()` **não precisa saber** (nem perguntar com
`if`) qual é o tipo concreto. Se amanhã surgir um `Podcaster`, ele implementa `tipoDePerfil()` e
**o código que chama continua igual** — nada quebra. Essa é a base do **princípio Aberto/Fechado**
(aberto para extensão, fechado para modificação), do SOLID.

**Regra de ouro.** Se você vê `if (usuario is Ouvinte) ... else if (usuario is Artista) ...`
espalhado para tratar comportamento por tipo, isso é um **cheiro**: provavelmente falta
polimorfismo. Deixe cada objeto se comportar do seu jeito.

**Dois tipos de polimorfismo (para dúvidas):**

- **Sobrescrita** (*override*) — a subclasse redefine um método do pai. É o polimorfismo de
  subtipo (o dos exemplos acima).
- **Sobrecarga** (*overload*) — vários métodos com o **mesmo nome** e **parâmetros diferentes**
  na mesma classe (ex.: `reproduzir(Musica)` e `reproduzir(Playlist)`). É resolvida em tempo de
  compilação.

**❓ Perguntas prováveis dos alunos**

- *"O que é ligação dinâmica (late binding)?"* — É o mecanismo que decide, **em execução**, qual
  método rodar com base no **tipo real** do objeto (não no tipo da variável). É o que faz o
  polimorfismo funcionar.
- *"Sobrecarga é polimorfismo?"* — É considerado **polimorfismo ad hoc** (mesmo nome, assinaturas
  diferentes). O polimorfismo "central" da OO é o de **subtipo** (sobrescrita + ligação dinâmica).
- *"`@Override` é obrigatório?"* — Não é obrigatório, mas é **fortemente recomendado**: avisa o
  compilador de que você quer sobrescrever, evitando erros (ex.: um nome digitado errado que
  criaria um método novo em vez de sobrescrever).
- *"Polimorfismo precisa de herança?"* — Precisa de uma relação de **subtipo**: herança (`extends`)
  **ou** interface (`implements`). Sem subtipo não há para onde "variar".

---

## 15. Composição sobre herança

**Recomendação (GoF, 1994).** *"Prefira composição de objetos à herança de classes."* Composição
("tem um") costuma ser mais flexível que herança ("é um").

**Explicação aprofundada.** Três relações que os alunos confundem:

| Relação | No Melodia | Significado |
|---------|------------|-------------|
| **Herança** ("é um") | `Ouvinte` **é um** `Usuario` | especialização de tipo |
| **Composição** ("é feito de") | `Album` **é feito de** `Musica`s | a **parte morre** com o todo |
| **Agregação** ("tem um") | `Playlist` **tem** `Musica`s | a **parte sobrevive** ao todo |

O par que ensina tudo: **`Album` e `Playlist` apontam ambos para `Musica`**, mas:

- **`Album` (composição):** as faixas são **criadas dentro** do álbum e não fazem sentido soltas.
  Apagou o álbum, apagou as faixas.
- **`Playlist` (agregação):** apenas **aponta** para músicas que já existem no catálogo. Apagou a
  playlist, as músicas **continuam** lá.

A pergunta que decide: **"a parte sobrevive sem o todo?"**

**Por que "prefira composição".** Herança é forte e **rígida**: acopla a subclasse a toda a
implementação do pai, e a hierarquia é difícil de mudar depois. Composição monta o comportamento
juntando objetos pequenos — mais fácil de trocar e testar. Herança só quando o "é um" é
**realmente** verdadeiro.

**❓ Perguntas prováveis dos alunos**

- *"Como escolho entre composição e agregação na hora de modelar?"* — Pergunte: *se eu apagar o
  todo, a parte deixa de existir?* Se **sim**, composição (losango cheio ◆). Se **não**, agregação
  (losango vazio ◇).
- *"Composição sempre é melhor que herança?"* — É a **preferência** quando ambos serviriam. Mas
  quando existe um "é um" real com comportamento compartilhado, herança é apropriada. Não é dogma.
- *"Isso não é a mesma coisa que associação?"* — Composição e agregação são **tipos** de
  associação (associações mais "fortes"). Veremos a fundo na aula 05.

---

## 16. Modelo anêmico e responsabilidade

**Alerta (Fowler, 2003).** O **modelo de domínio anêmico** (*anemic domain model*) é a classe
que só **guarda dados** (com `get/set`), enquanto a **lógica** vive fora dela (em classes de
"serviço" ou "manager"). Fowler chama de anti-padrão: parece OO, mas é **estruturado disfarçado**
— os dados de um lado, as regras de outro.

**Explicação aprofundada.** Compare:

- **Anêmico:** `Assinatura` só tem `getStatus()/setStatus()`, e a regra "só reativa se suspensa"
  fica numa classe `AssinaturaService`. Qualquer um pode fazer `assinatura.setStatus(ATIVA)` numa
  cancelada — o invariante não está protegido.
- **Rico (o do Melodia):** `Assinatura` tem `reativar()`/`cancelar()` que **contêm** a regra.
  Dados e comportamento **moram juntos**; o objeto cuida de si.

**A pergunta central da OO.** *"Quem é o dono deste dado/comportamento?"* — a resposta indica a
classe certa. É uma frase que você vai ouvir (e falar) em **code review**: *"esse método não
deveria estar aqui; quem é o dono desse dado?"*

**Três boas práticas para o projeto do grupo:**

1. Pergunte "quem é o dono deste dado?" antes de "onde ponho esta função?".
2. Evite a classe anêmica: se uma classe só tem `get/set`, a regra dela ficou solta em outro lugar.
3. Prefira composição a herança — herança só no "é um" real.

**❓ Perguntas prováveis dos alunos**

- *"Mas todo mundo usa `get/set`. Está errado?"* — Getters/setters **não são proibidos**; o
  problema é gerá-los **para tudo, sem regra**, e deixar o comportamento fora. Exponha só o
  necessário e **coloque a regra na classe dona do dado**.
- *"E os DTOs / `record`, que só têm dados?"* — São legítimos como **transporte** de dados entre
  camadas. A diferença é: eles **não** representam o domínio com regras; a regra de negócio vive
  nas classes de domínio ricas.

---

## 17. Vantagens, desvantagens e limites da OO

**Vantagens.** Modela o mundo real de forma intuitiva; encapsulamento reduz o efeito-cascata de
mudanças; herança e polimorfismo favorecem reúso e extensão; facilita testar partes isoladas.

**Desvantagens / cuidados.** Curva de aprendizado maior que a estruturada; excesso de camadas e
abstrações vira complexidade tola; herança mal usada gera acoplamento rígido; a indireção de
objetos tem um custo (relevante em altíssimo desempenho).

**Quando a OO NÃO é a melhor escolha.**

- **Processamento de dados massivo/estatístico** — pipelines funcionais e SQL costumam ser mais
  claros que hierarquias de objetos.
- **Scripts pequenos e utilitários** — OO pode virar peso morto (*over-engineering*).
- **Altíssimo desempenho / baixo nível** — a indireção custa caro; jogos de motor e sistemas
  embarcados usam *data-oriented design*.

**Na indústria.** A OO domina back-end corporativo (Java, C#) e mobile (Kotlin, Swift), mas hoje
**se mistura** com o paradigma **funcional** (imutabilidade, funções puras). O Java moderno tem
`lambdas`, `record`, `streams`. Bons engenheiros usam **o pilar certo para o problema** — não OO
por dogma.

**Regra prática.** A OO se paga quando o domínio tem **regras e estados ricos** (como o Melodia:
assinaturas que mudam de estado, playlists, catálogo com royalties). Para transformar planilhas
em relatórios, um script simples pode ser melhor.

**❓ Perguntas prováveis dos alunos**

- *"Se OO tem desvantagens, por que aprendê-la?"* — Porque a maioria dos sistemas corporativos e
  mobile é OO, e porque os conceitos (responsabilidade, encapsulamento, polimorfismo) formam o
  **raciocínio** de projeto que você leva para qualquer paradigma.
- *"Programação funcional vai substituir a OO?"* — Não; elas **convivem**. Java, Kotlin, Scala e
  C# são multiparadigma. O futuro é combinar: objetos com regras + funções puras para
  transformações de dados.

---

## 18. Exercícios resolvidos (comentados)

> Use como diagnóstico rápido. Peça que os alunos justifiquem **antes** de revelar a resposta.

**1. Classifique cada termo em objeto, classe, atributo ou operação:** `Playlist`, `nome`, a
playlist `"Favoritas"`, `adicionar()`.
**Resposta:** `Playlist` = **classe**; `nome` = **atributo**; a playlist "Favoritas" = **objeto**
(instância); `adicionar()` = **operação**.
*Comentário:* a pegadinha é "Favoritas" — é um **objeto** específico, não a classe.

**2. Escreva a mensagem para o ouvinte `ana` seguir a Banda Nébula.**
**Resposta:** `ana.seguir(bandaNebula);` — emissor `ana`, operação `seguir`, argumento
`bandaNebula`.

**3. "O plano PREMIUM" e "a assinatura da ana" — qual é classe/tipo e qual é objeto?**
**Resposta:** `PREMIUM` é um **valor** do tipo `Plano` (um enum); "a assinatura da ana" é um
**objeto** da classe `Assinatura`.
*Comentário:* enum é um tipo com valores fixos; a assinatura é uma instância com estado próprio.

**4. Por que `reproducoes` deve ser `private`? Que invariante o encapsulamento protege?**
**Resposta:** para ninguém alterar/zerar o valor por fora. **Invariante:** as reproduções só
**aumentam** (via `registrarReproducao()`) e nunca ficam inconsistentes — garante royalties e
ranking corretos.

**5. No par `Ouvinte`/`Artista`, onde estão herança e polimorfismo?**
**Resposta:** **Herança** — `Ouvinte` e `Artista` estendem `Usuario`. **Polimorfismo** —
`tipoDePerfil()` responde "Ouvinte" ou "Artista" conforme o objeto (mesma mensagem, comportamento
diferente).

**6. `Album` e `Playlist` ambos contêm `Musica`. Qual é composição e qual é agregação?**
**Resposta:** `Album` ◆ `Musica` é **composição** (a faixa nasce e morre com o álbum); `Playlist`
◇ `Musica` é **agregação** (a música existe fora da playlist).

---

## 19. Exercício final no projeto do grupo

**Enunciado (para os alunos).** Cada grupo tem um projeto em
[`../../../../projetos-em-grupo/`](../../../../projetos-em-grupo/) (hotel, loja, farmácia,
clínica…). Apliquem o vocabulário desta aula ao **seu** domínio:

1. Liste **3 classes** do seu domínio, cada uma com 2–3 **atributos** e 1–2 **operações**.
2. Escreva **uma mensagem** entre dois objetos do seu sistema (ex.: `pedido.adicionar(item)`).
3. Escolha um atributo que deve ser `private` e diga qual **invariante** ele protege.
4. Aponte, no seu modelo, onde aparece **cada um dos quatro pilares**.

**Entrega.** Em `projetos-em-grupo/<seu-projeto>/modelagem/`. Corresponde ao **Encontro 2** do
`PLANO-DE-EVOLUCAO.md` do grupo.

**Como corrigir (para o professor).** Verifique: (a) nenhuma classe é **anêmica** (todas têm ao
menos uma operação de negócio); (b) o aluno consegue dizer **quem é o dono** de cada dado; (c) o
invariante escolhido é uma regra que deve valer **sempre** (ex.: "quantidade em estoque nunca
negativa", "reserva não sobrepõe outra no mesmo horário"); (d) os quatro pilares foram
localizados no **domínio deles**, não copiados do Melodia.

---

## Banco de perguntas frequentes (FAQ)

Perguntas soltas que costumam surgir e não se encaixam em uma seção só:

- **"Objeto, instância e ocorrência são a mesma coisa?"** — Sim, sinônimos. "Instância da classe
  X" = "objeto do tipo X".

- **"Qual a diferença entre `==` e `equals()` em Java?"** — `==` compara **identidade**
  (é o mesmo objeto na memória?); `equals()` compara **conteúdo/igualdade** (têm o mesmo valor?),
  se a classe o implementar. Duas músicas com o mesmo título podem ser `equals`, mas não `==`.

- **"O que é `this`?"** — Uma referência ao **próprio objeto** dentro de um método. Serve para
  desambiguar (`this.nome = nome`) e para um objeto passar a si mesmo a outro.

- **"O que é `static` (estático)?"** — Pertence à **classe**, não ao objeto. Um atributo estático
  é único e compartilhado (ex.: uma constante `ROYALTY_POR_REPRODUCAO`); um método estático é
  chamado pela classe (`Math.max(...)`), sem precisar de objeto.

- **"O que é um construtor?"** — Um "método especial" que **inicializa** o objeto no `new`.
  Garante que o objeto nasça em um estado válido (ex.: toda `Musica` nasce com título e duração).

- **"O que é `null`?"** — A ausência de objeto (uma referência que não aponta para nada). Chamar
  um método em `null` causa `NullPointerException`. Bom encapsulamento valida contra `null`.

- **"Enum é uma classe?"** — Em Java, sim — é um tipo especial com um conjunto **fixo** de
  valores (ex.: `Plano`, `StatusAssinatura`). Ótimo para representar estados/categorias fechadas.

- **"Interface é a mesma coisa que classe abstrata?"** — Não. Interface é um **contrato** (o que
  fazer), sem estado; uma classe pode implementar **várias**. Classe abstrata é uma base com
  **estado e código** compartilhados, herdada por **uma** hierarquia. Detalhes no módulo 07.

- **"Por que tanto cuidado com `private` se é só um trabalho de faculdade?"** — Porque o hábito
  se forma agora. Em sistemas reais, dado exposto é a origem de incidentes caros (como o do
  ranking/royalties). Modelar protegendo invariantes é o que separa código amador de profissional.

- **"Onde vejo tudo isso rodando?"** — No projeto [`../projeto-base-java/`](../projeto-base-java/):
  compile e rode o `Principal`. Cada conceito desta aula tem um trecho lá (classes `Musica`,
  `Usuario`, `Ouvinte`, `Artista`, `Assinatura`, `Playlist`, `Album`).

- **"Isso cai na prova?"** — O vocabulário (objeto, classe, atributo, operação, mensagem, estado)
  e os quatro pilares, sim — com exemplos. Saber **explicar com um caso do domínio** vale mais
  que a definição decorada.

---

## Referências

- BOOCH, G. *Object-Oriented Analysis and Design with Applications*. 2. ed. Benjamin/Cummings, 1994.
- RUMBAUGH, J. et al. *Object-Oriented Modeling and Design*. Prentice Hall, 1991.
- MEYER, B. *Object-Oriented Software Construction*. 2. ed. Prentice Hall, 1997.
- PARNAS, D. L. *On the Criteria To Be Used in Decomposing Systems into Modules*. Comm. of the ACM, 1972.
- GAMMA, E.; HELM, R.; JOHNSON, R.; VLISSIDES, J. *Design Patterns*. Addison-Wesley, 1994.
- CARDELLI, L.; WEGNER, P. *On Understanding Types, Data Abstraction, and Polymorphism*. ACM Computing Surveys, 1985.
- LISKOV, B.; GUTTAG, J. *Abstraction and Specification in Program Development*. MIT Press, 1986.
- BOOCH, G.; RUMBAUGH, J.; JACOBSON, I. *The Unified Modeling Language User Guide*. Addison-Wesley, 1999.
- KAY, A. *The Early History of Smalltalk*. ACM SIGPLAN Notices, 1993.
- FOWLER, M. *AnemicDomainModel*. martinfowler.com, 2003.

---

[⬅️ Aula 02 — Conceitos de OO](README.md) · [🎞️ Apresentação (.pptx)](apresentacao-conceitos-oo.pptx) · [☕ Projeto Melodia](../projeto-base-java/)
