# 🚀 Plano de Evolução do Projeto — um feedback do cliente por aula

> **A ideia:** o projeto do grupo **não nasce pronto** — ele **cresce a cada aula**. A cada
> encontro, o "cliente" manda um **novo pedido/feedback**, e — não por acaso — esse pedido
> só se resolve bem com o **conceito daquela aula**. É assim que um sistema real evolui:
> começa simples (Java básico), aparecem necessidades, e cada tópico (POO, encapsulamento,
> herança, SOLID, KISS…) entra **quando faz falta**, não antes.

Este plano vale para **qualquer um dos [20 projetos](README.md)** — cada grupo aplica os
mesmos passos ao seu domínio. As aulas **alternam**: um dia **Java** (código), outro dia
**Modelagem** (UML), seguindo a ordem dos diagramas do
[curso de Análise e Projeto OO](../03-POO/01-modelagem/analise-projeto-uml/).

> 🎓 **Por que alternar e crescer aos poucos?** Porque o aluno *sente a dor antes do remédio*:
> escreve Java "ingênuo", tromba no limite, e aí o conceito novo aparece como **solução** — e
> não como regra decorada. Cada feedback do cliente é a "dor"; cada aula é o "remédio".

---

## 🗺️ Mapa rápido (20 encontros)

| # | Trilha | Aula/tópico | Feedback do cliente resolve com… |
|---|--------|-------------|----------------------------------|
| 1 | ☕ Java | [Java básico](../00-conceitos/) | listas e laços (sem classes ainda) |
| 2 | 📐 Model | [Histórico + Conceitos OO](../03-POO/01-modelagem/analise-projeto-uml/02-conceitos-orientacao-objetos/) | enxergar entidades/objetos |
| 3 | 📐 Model | [Abstração + Classe/Objeto](../03-POO/01-modelagem/analise-projeto-uml/03-abstracao/) | recortar o essencial |
| 4 | ☕ Java | [Classes e Objetos](../03-POO/02-classes-e-objetos/) | virar classes com construtor |
| 5 | 📐 Model | [Atributos + Operações](../03-POO/01-modelagem/analise-projeto-uml/06-atributos/) | onde mora a regra |
| 6 | ☕ Java | [Encapsulamento](../03-POO/03-encapsulamento/) | `private` + validação |
| 7 | 📐 Model | [Associação](../03-POO/01-modelagem/analise-projeto-uml/05-associacao/) | relacionamentos + multiplicidade |
| 8 | ☕ Java | [Herança](../03-POO/04-heranca/) | um tipo especial (`extends`) |
| 9 | ☕ Java | [Polimorfismo](../03-POO/05-polimorfismo/) | comportamento por tipo |
| 10 | 📐 Model | [Casos de Uso](../03-POO/01-modelagem/analise-projeto-uml/08-diagrama-casos-de-uso/) | atores + funcionalidades |
| 11 | 📐 Model | [Diagrama de Classes](../03-POO/01-modelagem/analise-projeto-uml/09-diagrama-de-classes/) | a planta completa |
| 12 | ☕ Java | [Abstração + Interfaces](../03-POO/07-interfaces/) | contrato plugável |
| 13 | 📐 Model | [Objetos + Sequência](../03-POO/01-modelagem/analise-projeto-uml/11-diagrama-de-sequencia/) | o passo a passo |
| 14 | 📐 Model | [Máquina de Estados](../03-POO/01-modelagem/analise-projeto-uml/14-diagrama-de-maquina-de-estados/) | fases sem pular etapa |
| 15 | 📐 Model | [Atividades](../03-POO/01-modelagem/analise-projeto-uml/15-diagrama-de-atividades/) | o processo de ponta a ponta |
| 16 | ☕ Java | [SOLID](../03-POO/08-solid/) | separar responsabilidades |
| 17 | ☕ Java | [KISS · DRY · YAGNI](../4-principios-desgin-poo/) | simplificar |
| 18 | 📐 Model | [Componentes + Pacotes](../03-POO/01-modelagem/analise-projeto-uml/18-diagrama-de-pacotes/) | módulos e camadas |
| 19 | 📐 Model | [Implantação](../03-POO/01-modelagem/analise-projeto-uml/19-diagrama-de-implantacao/) | onde vai rodar |
| 20 | ☕ Java | [Padrões + integração](../05-design-patterns/) | juntar tudo rodando |

Os **exemplos entre colchetes** usam vários domínios só para ilustrar — troquem pelo **seu**
projeto.

---

## Encontro 1 — ☕ Java básico
**[Módulo: Conceitos de Java](../00-conceitos/)**
- 📨 **Cliente:** *"Por enquanto, só quero **cadastrar e listar** [os hóspedes / os veículos / os pacientes]. Nada de mais."*
- 💡 **Exercita:** variáveis, tipos, listas, laços, entrada/saída — **ainda sem classes**, de propósito.
- 📦 **Entregável:** um programa Java que cadastra e lista em memória (pode ser tudo no `main`).
- 🧩 **Dica:** **guardem este código.** Na aula de POO vocês vão *sentir* por que ele não escala.

## Encontro 2 — 📐 Histórico + Conceitos de OO
**[Aula 01](../03-POO/01-modelagem/analise-projeto-uml/01-historico-metodologias/) + [02](../03-POO/01-modelagem/analise-projeto-uml/02-conceitos-orientacao-objetos/)**
- 📨 **Cliente:** *"Ah, também preciso registrar [as reservas de cada hóspede / as locações de cada carro] — e cada um pode ter vários."*
- 💡 **A dor aparece:** no código da aula 1 (procedural), dados começam a se espalhar. Hora de **enxergar objetos**.
- 📦 **Entregável:** **glossário do domínio** + lista de **entidades, atributos e operações candidatas** (sublinhem substantivos e verbos do pedido).
- 🧩 **Dica:** apontem no código da aula 1 **onde** já está ficando difícil mudar.

## Encontro 3 — 📐 Abstração + Classe e Objetos
**[Aula 03](../03-POO/01-modelagem/analise-projeto-uml/03-abstracao/) + [04](../03-POO/01-modelagem/analise-projeto-uml/04-classe-e-objetos/)**
- 📨 **Cliente (falando demais):** *"o cliente torce pro tal time, faz aniversário em maio, indicou um amigo…"* — 90% irrelevante.
- 💡 **Exercita:** **abstração** (recortar só o que importa) e a diferença **classe (molde) × objeto (instância)**.
- 📦 **Entregável:** primeiras **classes candidatas** (nome + o que guarda + o que faz), **ignorando o ruído**.

## Encontro 4 — ☕ Classes e Objetos em Java
**[Módulo: Classes e Objetos](../03-POO/02-classes-e-objetos/)**
- 📨 **Cliente:** *"Quero que cada [reserva/veículo/consulta] seja uma 'coisa' de verdade no sistema, não uma linha solta."*
- 💡 **Exercita:** `class`, atributos, **construtor**, `this`, `new`.
- 📦 **Entregável:** as entidades viram **classes Java com construtor**; um `Principal` cria objetos reais — **aposentando** o código procedural da aula 1.

## Encontro 5 — 📐 Atributos + Operações
**[Aula 06](../03-POO/01-modelagem/analise-projeto-uml/06-atributos/) + [07](../03-POO/01-modelagem/analise-projeto-uml/07-operacoes/)**
- 📨 **Cliente:** *"Tem uma regra importante: [não pode reservar quarto já ocupado / não pode vender sem estoque]."*
- 💡 **Exercita:** atributos (visibilidade `+ - #`) e operações (assinatura) — e **onde a regra vai morar**.
- 📦 **Entregável:** classes com **atributos** e **operações** bem definidos no diagrama (a regra vira uma operação).

## Encontro 6 — ☕ Encapsulamento
**[Módulo: Encapsulamento](../03-POO/03-encapsulamento/)**
- 📨 **Cliente:** *"Deu problema! Alguém colocou um valor absurdo em [preço / quantidade / saldo]. Isso não podia acontecer."*
- 💡 **Exercita:** `private` + getters/setters **com validação**; proteger o **invariante**.
- 📦 **Entregável:** campos `private` e métodos que **validam** a regra do encontro 5 (estado inválido fica **impossível**).

## Encontro 7 — 📐 Associação
**[Aula 05](../03-POO/01-modelagem/analise-projeto-uml/05-associacao/)**
- 📨 **Cliente:** *"Preciso saber **quais [X] pertencem a qual [Y]** e quantos — um cliente tem vários pedidos, um pedido tem vários itens…"*
- 💡 **Exercita:** associação, **multiplicidade**, agregação × composição.
- 📦 **Entregável:** **diagrama de classes** com relacionamentos e multiplicidades (e decidir o que é composição).

## Encontro 8 — ☕ Herança
**[Módulo: Herança](../03-POO/04-heranca/)**
- 📨 **Cliente:** *"Surgiu um **tipo especial**: [hóspede VIP / cliente premium / aluno bolsista]."*
- 💡 **Exercita:** `extends`, `super`, reúso do que já existe.
- 📦 **Entregável:** uma **subclasse** que especializa a base — escrevendo **só o que muda**.

## Encontro 9 — ☕ Polimorfismo
**[Módulo: Polimorfismo](../03-POO/05-polimorfismo/)**
- 📨 **Cliente:** *"Esse tipo especial **se comporta diferente**: [o VIP tem desconto / late checkout / prioridade]."*
- 💡 **Exercita:** sobrescrita (`@Override`), **ligação dinâmica**.
- 📦 **Entregável:** o **mesmo método** com comportamento diferente por tipo — **sem `if` perguntando o tipo**.

## Encontro 10 — 📐 Casos de Uso
**[Aula 08](../03-POO/01-modelagem/analise-projeto-uml/08-diagrama-casos-de-uso/)**
- 📨 **Cliente:** *"Deixa eu te listar **tudo que o pessoal precisa fazer** no sistema…"*
- 💡 **Exercita:** atores + casos de uso; delimitar **escopo** (`«include»`/`«extend»`).
- 📦 **Entregável:** **diagrama de casos de uso** + descrição do caso principal (fluxo principal e alternativo).

## Encontro 11 — 📐 Diagrama de Classes (consolidação)
**[Aula 09](../03-POO/01-modelagem/analise-projeto-uml/09-diagrama-de-classes/)**
- 📨 **Cliente:** *"Me mostra o **desenho completo** do sistema, quero ver tudo se ligando."*
- 💡 **Exercita:** juntar atributos + operações + relacionamentos numa visão só.
- 📦 **Entregável:** **diagrama de classes completo**, coerente com o código já escrito (encontros 4, 6, 8, 9).

## Encontro 12 — ☕ Abstração e Interfaces
**[Abstração](../03-POO/06-abstracao/) + [Interfaces](../03-POO/07-interfaces/)**
- 📨 **Cliente:** *"Quero poder **trocar** [a forma de pagamento / o meio de notificação] sem refazer o sistema."*
- 💡 **Exercita:** classe abstrata + **interface** = contrato plugável (baixo acoplamento).
- 📦 **Entregável:** uma **interface** (ex.: `MeioDePagamento`, `Notificador`) com **2 implementações** intercambiáveis.

## Encontro 13 — 📐 Objetos + Sequência
**[Aula 10](../03-POO/01-modelagem/analise-projeto-uml/10-diagrama-de-objetos/) + [11](../03-POO/01-modelagem/analise-projeto-uml/11-diagrama-de-sequencia/)**
- 📨 **Cliente:** *"Me explica, **passo a passo**, como acontece [fazer uma reserva / fechar um pedido]."*
- 💡 **Exercita:** objetos concretos (fotografia) + **mensagens ordenadas no tempo**.
- 📦 **Entregável:** um **diagrama de objetos** de um cenário real + um **diagrama de sequência** do fluxo principal.

## Encontro 14 — 📐 Máquina de Estados
**[Aula 14](../03-POO/01-modelagem/analise-projeto-uml/14-diagrama-de-maquina-de-estados/)**
- 📨 **Cliente:** *"Esse [reserva / pedido / OS / apólice] passa por **fases** e **não pode pular etapa**."*
- 💡 **Exercita:** estados, transições, **guardas** (regra de negócio na transição).
- 📦 **Entregável:** **máquina de estados** da entidade central.
- 🔗 **No próximo dia de Java**, implemente as transições como métodos que **barram** estados inválidos (igual ao `Assinatura` do [Melodia](../03-POO/01-modelagem/analise-projeto-uml/projeto-base-java/)).

## Encontro 15 — 📐 Atividades
**[Aula 15](../03-POO/01-modelagem/analise-projeto-uml/15-diagrama-de-atividades/)**
- 📨 **Cliente:** *"Escreve o **processo do começo ao fim**, quero treinar minha equipe com isso."*
- 💡 **Exercita:** fluxo de processo, decisões, paralelismo, **raias** (quem faz o quê).
- 📦 **Entregável:** **diagrama de atividades** de um processo de negócio do sistema.

## Encontro 16 — ☕ SOLID
**[Módulo: SOLID](../03-POO/08-solid/)**
- 📨 **Cliente:** *"O sistema cresceu e agora **toda mudancinha quebra outra coisa**. Tá difícil mexer."*
- 💡 **Exercita:** SRP (responsabilidade única), OCP (aberto/fechado)… separar o que virou bagunça.
- 📦 **Entregável:** uma **refatoração** que separa responsabilidades (ex.: tirar "gerar relatório" / "enviar e-mail" de dentro da entidade de domínio).

## Encontro 17 — ☕ KISS · DRY · YAGNI
**[Módulo: Princípios de Design](../4-principios-desgin-poo/)**
- 📨 **Cliente:** *"Ficou **complicado demais** e cheio de função que ninguém usa. **Simplifica** isso."*
- 💡 **Exercita:** **KISS** (simplicidade), **DRY** (não repita), **YAGNI** (não faça o que não pediram), composição sobre herança.
- 📦 **Entregável:** uma **simplificação** — remover duplicação, código especulativo e complexidade desnecessária, sem perder função.

## Encontro 18 — 📐 Componentes + Pacotes
**[Aula 17](../03-POO/01-modelagem/analise-projeto-uml/17-diagrama-de-componentes/) + [18](../03-POO/01-modelagem/analise-projeto-uml/18-diagrama-de-pacotes/)**
- 📨 **Cliente:** *"Talvez **outro time cuide** da parte de [pagamento / relatórios]. Dá pra separar em módulos?"*
- 💡 **Exercita:** componentes (interfaces) + pacotes (camadas, **sem ciclos**).
- 📦 **Entregável:** **diagrama de pacotes** (camadas) + **componentes** com interfaces.
- 🔗 **No Java**, reorganize o código em pacotes por responsabilidade (`com.seuprojeto.{dominio,aplicacao,infra}`).

## Encontro 19 — 📐 Implantação
**[Aula 19](../03-POO/01-modelagem/analise-projeto-uml/19-diagrama-de-implantacao/)**
- 📨 **Cliente:** *"**Onde** isso vai rodar? Vai ter site? Banco de dados? Roda no celular?"*
- 💡 **Exercita:** nós (hardware/ambiente), artefatos, conexões de rede.
- 📦 **Entregável:** **diagrama de implantação** da topologia pretendida (mesmo que futura).
- ➕ **Avançado (opcional):** grupos mais rápidos podem modelar também [Comunicação (13)](../03-POO/01-modelagem/analise-projeto-uml/13-diagrama-de-comunicacao/), [Estrutura Composta (12)](../03-POO/01-modelagem/analise-projeto-uml/12-diagrama-de-estrutura-composta/), [Interação Geral (16)](../03-POO/01-modelagem/analise-projeto-uml/16-diagrama-de-interacao-geral/) ou [Temporização (20)](../03-POO/01-modelagem/analise-projeto-uml/20-diagrama-de-temporizacao/).

## Encontro 20 — ☕ Fechamento: integração + padrões
**[Módulo: Design Patterns](../05-design-patterns/)**
- 📨 **Cliente:** *"Quero ver **rodando de ponta a ponta**, do jeito que a gente combinou."*
- 💡 **Exercita:** juntar tudo; enxergar onde **um padrão simples** ajuda (ex.: *Factory* para criar objetos, *Strategy* para regras que variam).
- 📦 **Entregável final:** sistema **Java rodando** com um `Principal` que demonstra o cenário completo **+** diagramas coerentes com o código.

---

## ✅ Como saber que o grupo está no caminho

- [ ] O código do encontro 1 (procedural) foi **substituído** por objetos — e o grupo sabe **explicar por quê**.
- [ ] Cada regra de negócio vive **dentro** da classe dona do dado (nada de regra solta).
- [ ] Os **diagramas batem com o código** (mesma história em idiomas diferentes).
- [ ] O sistema evoluiu **sem reescrever do zero** a cada aula — cada conceito **somou**.
- [ ] Na entrega final, dá para apontar **em que aula** cada parte do sistema nasceu.

> 🎯 **A grande lição do curso:** software bom **não é escrito de uma vez** — é **cultivado**.
> Cada aula é uma necessidade nova do mundo real, e cada conceito de POO/UML é a ferramenta
> que aparece **na hora certa** para atendê-la.

[⬅️ Banco de projetos](README.md) · [📚 Curso de Análise e Projeto OO](../03-POO/01-modelagem/analise-projeto-uml/)
