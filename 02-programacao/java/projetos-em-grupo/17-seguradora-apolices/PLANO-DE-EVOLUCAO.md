# 🚀 Plano de Evolução — 17 — Seguradora: apólices e sinistros

> **Este é o roteiro do seu grupo.** O projeto **não nasce pronto**: a cada aula, o "cliente"
> (gerente de uma seguradora) manda um **novo feedback**, e esse pedido só se resolve bem com o **conceito
> daquela aula**. Sigam os 20 encontros na ordem — o sistema começa como Java simples e vai
> ganhando POO, encapsulamento, herança, SOLID, KISS… **na hora em que cada um faz falta.**
>
> 📋 Briefing original do cliente: **[README deste projeto](README.md)**
> · 🗺️ Teoria do método (plano-mestre): **[../PLANO-DE-EVOLUCAO.md](../PLANO-DE-EVOLUCAO.md)**

As aulas **alternam**: um dia **☕ Java** (código), outro dia **📐 Modelagem** (UML), seguindo
a ordem dos diagramas do [curso de Análise e Projeto OO](../../03-POO/01-modelagem/analise-projeto-uml/).

> ### 📖 Como ler cada encontro
> - 📨 **Cliente** — o novo pedido (a "dor").   💡 **Exercita** — o conceito da aula.
> - 📦 **Entregável** — o que entregar.   🎯 **Direcionamento** — por onde começar / o que fazer.
> - 🔒 **Restrição nova** — a regra que **sobe o nível** (o professor cobra isso).   🤔 **Por quê** — como isso prepara o próximo passo.
>
> As restrições **acumulam**: o que foi exigido antes continua valendo. Não precisam acertar
> tudo de primeira — o objetivo é **evoluir** o mesmo sistema, aula após aula.

---

## 🗺️ Mapa rápido (20 encontros)

| # | Trilha | Aula/tópico | O feedback do cliente resolve com… |
|---|--------|-------------|------------------------------------|
| 1 | ☕ Java | [Java básico](../../00-conceitos/) | listas e laços (sem classes ainda) |
| 2 | 📐 Model | [Histórico + Conceitos OO](../../03-POO/01-modelagem/analise-projeto-uml/02-conceitos-orientacao-objetos/) | enxergar entidades/objetos |
| 3 | 📐 Model | [Abstração + Classe/Objeto](../../03-POO/01-modelagem/analise-projeto-uml/03-abstracao/) | recortar o essencial |
| 4 | ☕ Java | [Classes e Objetos](../../03-POO/02-classes-e-objetos/) | virar classes com construtor |
| 5 | 📐 Model | [Atributos + Operações](../../03-POO/01-modelagem/analise-projeto-uml/06-atributos/) | onde mora a regra |
| 6 | ☕ Java | [Encapsulamento](../../03-POO/03-encapsulamento/) | `private` + validação |
| 7 | 📐 Model | [Associação](../../03-POO/01-modelagem/analise-projeto-uml/05-associacao/) | relacionamentos + multiplicidade |
| 8 | ☕ Java | [Herança](../../03-POO/04-heranca/) | um tipo especial (`extends`) |
| 9 | ☕ Java | [Polimorfismo](../../03-POO/05-polimorfismo/) | comportamento por tipo |
| 10 | 📐 Model | [Casos de Uso](../../03-POO/01-modelagem/analise-projeto-uml/08-diagrama-casos-de-uso/) | atores + funcionalidades |
| 11 | 📐 Model | [Diagrama de Classes](../../03-POO/01-modelagem/analise-projeto-uml/09-diagrama-de-classes/) | a planta completa |
| 12 | ☕ Java | [Abstração + Interfaces](../../03-POO/07-interfaces/) | contrato plugável |
| 13 | 📐 Model | [Objetos + Sequência](../../03-POO/01-modelagem/analise-projeto-uml/11-diagrama-de-sequencia/) | o passo a passo |
| 14 | 📐 Model | [Máquina de Estados](../../03-POO/01-modelagem/analise-projeto-uml/14-diagrama-de-maquina-de-estados/) | fases sem pular etapa |
| 15 | 📐 Model | [Atividades](../../03-POO/01-modelagem/analise-projeto-uml/15-diagrama-de-atividades/) | o processo de ponta a ponta |
| 16 | ☕ Java | [SOLID](../../03-POO/08-solid/) | separar responsabilidades |
| 17 | ☕ Java | [KISS · DRY · YAGNI](../../4-principios-desgin-poo/) | simplificar |
| 18 | 📐 Model | [Componentes + Pacotes](../../03-POO/01-modelagem/analise-projeto-uml/18-diagrama-de-pacotes/) | módulos e camadas |
| 19 | 📐 Model | [Implantação](../../03-POO/01-modelagem/analise-projeto-uml/19-diagrama-de-implantacao/) | onde vai rodar |
| 20 | ☕ Java | [Padrões + integração](../../05-design-patterns/) | juntar tudo rodando |

---

## Encontro 1 — ☕ Java básico
**[Módulo: Conceitos de Java](../../00-conceitos/)**
- 📨 **Cliente:** *"Por enquanto, só quero **cadastrar e listar as apólices**. Nada de mais."*
- 💡 **Exercita:** variáveis, listas, laços, entrada/saída — **ainda sem classes**, de propósito.
- 📦 **Entregável:** um programa Java que cadastra e lista apólices em memória (tudo no `main`).
- 🎯 **Direcionamento:** guardem os dados em listas/arrays no `main` e ofereçam só duas ações: *adicionar* e *listar*. Comecem pequeno.
- 🔒 **Restrição nova:** proibido criar classes próprias — só tipos básicos e coleções. (Vai ficar feio; é proposital.)
- 🤔 **Por quê:** sentir o limite do código sem objetos é o que dá sentido à POO nas próximas aulas.

## Encontro 2 — 📐 Histórico + Conceitos de OO
**[Aula 01](../../03-POO/01-modelagem/analise-projeto-uml/01-historico-metodologias/) + [02](../../03-POO/01-modelagem/analise-projeto-uml/02-conceitos-orientacao-objetos/)**
- 📨 **Cliente:** *"Ah, também preciso registrar os sinistros, e uma apólice pode ter vários ao longo do tempo."*
- 💡 **A dor aparece:** o código procedural começa a espalhar dados. Hora de **enxergar objetos**.
- 📦 **Entregável:** **glossário do domínio** + lista de **entidades, atributos e operações candidatas**.
- 🎯 **Direcionamento:** grifem o briefing — substantivos viram **classe/atributo**, verbos viram **operação**. Montem uma tabela "termo → é classe? atributo? operação?".
- 🔒 **Restrição nova:** cada entidade candidata precisa de **uma frase justificando** por que existe (o que ela sabe e o que faz).
- 🤔 **Por quê:** um vocabulário claro agora evita retrabalho quando o código começar.

## Encontro 3 — 📐 Abstração + Classe e Objetos
**[Aula 03](../../03-POO/01-modelagem/analise-projeto-uml/03-abstracao/) + [04](../../03-POO/01-modelagem/analise-projeto-uml/04-classe-e-objetos/)**
- 📨 **Cliente (falando demais):** *"o apólice torce pro tal time, faz aniversário em maio, indicou um amigo…"* — 90% irrelevante.
- 💡 **Exercita:** **abstração** (recortar só o que importa) e **classe (molde) × objeto (instância)**.
- 📦 **Entregável:** primeiras **classes candidatas** (nome + o que guarda + o que faz), **ignorando o ruído**.
- 🎯 **Direcionamento:** escolham de **3 a 6 classes** essenciais; para cada uma, 2–4 atributos e 1–3 operações. Descartem o resto.
- 🔒 **Restrição nova:** no máximo ~6 classes neste estágio; **justifiquem cada corte** (por que ficou de fora).
- 🤔 **Por quê:** começar enxuto evita *over-engineering* — dá para crescer depois com segurança.

## Encontro 4 — ☕ Classes e Objetos em Java
**[Módulo: Classes e Objetos](../../03-POO/02-classes-e-objetos/)**
- 📨 **Cliente:** *"Quero que cada sinistro seja uma 'coisa' de verdade no sistema, não uma linha solta."*
- 💡 **Exercita:** `class`, atributos, **construtor**, `this`, `new`.
- 📦 **Entregável:** as entidades viram **classes Java com construtor**; um `Principal` cria objetos reais — **aposentando** o código procedural do Encontro 1.
- 🎯 **Direcionamento:** uma classe por entidade, com **construtor que exige os dados obrigatórios**. No `main`, criem alguns objetos e usem.
- 🔒 **Restrição nova:** todo atributo nasce `private`; nada de lógica de negócio no `main` além de **orquestrar** objetos.
- 🤔 **Por quê:** objetos "donos de si" preparam o encapsulamento do próximo dia.

## Encontro 5 — 📐 Atributos + Operações
**[Aula 06](../../03-POO/01-modelagem/analise-projeto-uml/06-atributos/) + [07](../../03-POO/01-modelagem/analise-projeto-uml/07-operacoes/)**
- 📨 **Cliente:** *"Tem uma regra importante: não pode abrir sinistro em uma apólice vencida ou cancelada."*
- 💡 **Exercita:** atributos (visibilidade `+ - #`) e operações (assinatura) — e **onde a regra vai morar**.
- 📦 **Entregável:** classes com **atributos** e **operações** bem definidos no diagrama (a regra vira uma operação).
- 🎯 **Direcionamento:** escrevam a **assinatura** de cada operação (parâmetros e retorno) e a visibilidade de cada atributo. Transformem a regra "não pode abrir sinistro em uma apólice vencida ou cancelada" numa operação.
- 🔒 **Restrição nova:** **nenhuma classe anêmica** — cada uma precisa de ≥1 operação de negócio (não só get/set).
- 🤔 **Por quê:** decidir onde a regra mora agora evita espalhá-la pelo código depois.

## Encontro 6 — ☕ Encapsulamento
**[Módulo: Encapsulamento](../../03-POO/03-encapsulamento/)**
- 📨 **Cliente:** *"Deu problema! Alguém colocou um valor absurdo: o valor da cobertura ficou negativo. Isso não podia acontecer."*
- 💡 **Exercita:** `private` + getters/setters **com validação**; proteger o **invariante**.
- 📦 **Entregável:** campos `private` e métodos que **validam** a regra do Encontro 5 (estado inválido fica **impossível**).
- 🎯 **Direcionamento:** implementem no código a regra "não pode abrir sinistro em uma apólice vencida ou cancelada": o método valida **antes** de mudar o estado e recusa o inválido.
- 🔒 **Restrição nova:** estado inválido deve ser **impossível** — a validação **lança exceção**; proibido setter que aceita qualquer valor.
- 🤔 **Por quê:** um objeto que se protege sozinho elimina uma classe inteira de bugs.

## Encontro 7 — 📐 Associação
**[Aula 05](../../03-POO/01-modelagem/analise-projeto-uml/05-associacao/)**
- 📨 **Cliente:** *"Preciso enxergar como as coisas se ligam: um cliente tem várias apólices; cada apólice pode gerar vários sinistros — e quantas de cada lado."*
- 💡 **Exercita:** associação, **multiplicidade**, agregação × composição.
- 📦 **Entregável:** **diagrama de classes** com relacionamentos e multiplicidades (decidam o que é composição).
- 🎯 **Direcionamento:** desenhem a multiplicidade nas **duas pontas** (um cliente tem várias apólices; cada apólice pode gerar vários sinistros) e, para cada "tem-um", decidam **composição (◆)** ou **agregação (◇)**.
- 🔒 **Restrição nova:** toda associação precisa de **multiplicidade explícita** e um verbo/nome; **justifiquem** cada composição.
- 🤔 **Por quê:** a multiplicidade correta vira integridade de dados (e futura chave estrangeira no banco).

## Encontro 8 — ☕ Herança
**[Módulo: Herança](../../03-POO/04-heranca/)**
- 📨 **Cliente:** *"Surgiu um **tipo especial**: apólice de vida (vs de automóvel)."*
- 💡 **Exercita:** `extends`, `super`, reúso do que já existe.
- 📦 **Entregável:** uma **subclasse** que especializa a base — escrevendo **só o que muda**.
- 🎯 **Direcionamento:** criem a superclasse comum e façam **apólice de vida (vs de automóvel)** estendê-la, reaproveitando o que já existe com `super`.
- 🔒 **Restrição nova:** só herde se passar no teste **"é um"**; se for "tem um", use composição. A superclasse pode ser `abstract`.
- 🤔 **Por quê:** herança certa reduz duplicação; herança errada engessa o sistema.

## Encontro 9 — ☕ Polimorfismo
**[Módulo: Polimorfismo](../../03-POO/05-polimorfismo/)**
- 📨 **Cliente:** *"Esse apólice de vida (vs de automóvel) **se comporta diferente**: tem regras de cobertura e cálculo de prêmio próprios."*
- 💡 **Exercita:** sobrescrita (`@Override`), **ligação dinâmica**.
- 📦 **Entregável:** o **mesmo método** com comportamento diferente por tipo — **sem `if` perguntando o tipo**.
- 🎯 **Direcionamento:** sobrescrevam um método para que **apólice de vida (vs de automóvel)** faça o seu (tem regras de cobertura e cálculo de prêmio próprios) e chamem sempre pela **referência do tipo base**.
- 🔒 **Restrição nova:** proibido `if`/`instanceof` para decidir comportamento por tipo — quem decide é o objeto.
- 🤔 **Por quê:** amanhã, um tipo novo não deve exigir tocar no código que já funciona (é a base do OCP, no Encontro 16).

## Encontro 10 — 📐 Casos de Uso
**[Aula 08](../../03-POO/01-modelagem/analise-projeto-uml/08-diagrama-casos-de-uso/)**
- 📨 **Cliente:** *"Deixa eu te listar **tudo que o pessoal precisa fazer** no sistema…"*
- 💡 **Exercita:** atores + casos de uso; delimitar **escopo** (`«include»`/`«extend»`).
- 📦 **Entregável:** **diagrama de casos de uso** + descrição do caso principal (fluxo principal e alternativo).
- 🎯 **Direcionamento:** listem os **atores** e desenhem os casos (verbo + objeto). Escrevam o **fluxo principal e 1 alternativo** do caso mais importante.
- 🔒 **Restrição nova:** caso de uso = **objetivo do usuário**, não clique de tela; mínimo **2 atores**; usem `«include»`/`«extend»` ao menos uma vez.
- 🤔 **Por quê:** delimitar escopo agora evita o "sistema que faz tudo e não entrega nada".

## Encontro 11 — 📐 Diagrama de Classes (consolidação)
**[Aula 09](../../03-POO/01-modelagem/analise-projeto-uml/09-diagrama-de-classes/)**
- 📨 **Cliente:** *"Me mostra o **desenho completo** do sistema, quero ver tudo se ligando."*
- 💡 **Exercita:** juntar atributos + operações + relacionamentos numa visão só.
- 📦 **Entregável:** **diagrama de classes completo**, coerente com o código já escrito (Encontros 4, 6, 8, 9).
- 🎯 **Direcionamento:** consolidem tudo num diagrama só: classes, atributos, operações, relacionamentos e multiplicidades.
- 🔒 **Restrição nova:** o diagrama tem que **bater 100% com o código** já escrito. Divergiu? Corrijam **um dos dois**.
- 🤔 **Por quê:** modelo e código contando a mesma história é o que mantém o projeto sustentável.

## Encontro 12 — ☕ Abstração e Interfaces
**[Abstração](../../03-POO/06-abstracao/) + [Interfaces](../../03-POO/07-interfaces/)**
- 📨 **Cliente:** *"Quero poder **trocar** a forma de cálculo do prêmio (por idade, por risco) sem refazer o sistema."*
- 💡 **Exercita:** classe abstrata + **interface** = contrato plugável (baixo acoplamento).
- 📦 **Entregável:** uma **interface** `CalculadoraDePremio` com **2 implementações** intercambiáveis.
- 🎯 **Direcionamento:** criem a interface `CalculadoraDePremio` para a forma de cálculo do prêmio (por idade, por risco) e façam **≥2 implementações**; o resto do sistema usa **só a interface**.
- 🔒 **Restrição nova:** nenhuma classe de negócio pode depender da implementação concreta — só do **contrato**.
- 🤔 **Por quê:** trocar de estratégia/fornecedor vira **escrever uma classe nova** (e facilita testes com um "dublê").

## Encontro 13 — 📐 Objetos + Sequência
**[Aula 10](../../03-POO/01-modelagem/analise-projeto-uml/10-diagrama-de-objetos/) + [11](../../03-POO/01-modelagem/analise-projeto-uml/11-diagrama-de-sequencia/)**
- 📨 **Cliente:** *"Me explica, **passo a passo**, como acontece abrir e analisar um sinistro."*
- 💡 **Exercita:** objetos concretos (fotografia) + **mensagens ordenadas no tempo**.
- 📦 **Entregável:** um **diagrama de objetos** de um cenário real + um **diagrama de sequência** do fluxo principal.
- 🎯 **Direcionamento:** desenhem a sequência de "abrir e analisar um sinistro" mensagem a mensagem, nomeando **quem chama quem**.
- 🔒 **Restrição nova:** a sequência precisa mostrar **≥1 caminho alternativo** (`alt`/`opt`) — não só o "tudo deu certo".
- 🤔 **Por quê:** pensar o fluxo antes de codar revela chamadas e erros que o código esconderia.

## Encontro 14 — 📐 Máquina de Estados
**[Aula 14](../../03-POO/01-modelagem/analise-projeto-uml/14-diagrama-de-maquina-de-estados/)**
- 📨 **Cliente:** *"O sinistro passa por **fases** e **não pode pular etapa**."*
- 💡 **Exercita:** estados, transições, **guardas** (regra de negócio na transição).
- 📦 **Entregável:** **máquina de estados** de o sinistro: `aberto → em análise → aprovado/negado → pago`.
- 🎯 **Direcionamento:** desenhem os estados e marquem **evento + guarda** em cada transição. Depois, no Java, cada transição é um método.
- 🔒 **Restrição nova:** transição inválida deve ser **barrada** — tentar pular etapa **lança exceção** (nada de mudar `status` na mão).
- 🤔 **Por quê:** estados impossíveis são fonte clássica de bug; o diagrama vira validação no código (como o `Assinatura` do [Melodia](../../03-POO/01-modelagem/analise-projeto-uml/projeto-base-java/)).

## Encontro 15 — 📐 Atividades
**[Aula 15](../../03-POO/01-modelagem/analise-projeto-uml/15-diagrama-de-atividades/)**
- 📨 **Cliente:** *"Escreve o **processo da abertura do sinistro ao pagamento**, quero treinar minha equipe com isso."*
- 💡 **Exercita:** fluxo de processo, decisões, paralelismo, **raias** (quem faz o quê).
- 📦 **Entregável:** **diagrama de atividades** desse processo de negócio.
- 🎯 **Direcionamento:** modelem da abertura do sinistro ao pagamento com início, ações, decisões e fim; usem **raias** para mostrar o responsável de cada passo.
- 🔒 **Restrição nova:** o fluxo precisa ter **≥1 decisão** (losango) e deixar claro **quem** executa cada ação.
- 🤔 **Por quê:** enxergar o processo inteiro alinha a equipe e expõe passos esquecidos.

## Encontro 16 — ☕ SOLID
**[Módulo: SOLID](../../03-POO/08-solid/)**
- 📨 **Cliente:** *"O sistema cresceu e agora **toda mudancinha quebra outra coisa**. Tá difícil mexer."*
- 💡 **Exercita:** SRP (responsabilidade única), OCP (aberto/fechado)…
- 📦 **Entregável:** uma **refatoração** separando responsabilidades — por ex.: a classe Apolice calcula o prêmio, analisa o sinistro E emite o documento — separe.
- 🎯 **Direcionamento:** achem a classe que "faz demais" e quebrem em classes menores — comecem por: a classe Apolice calcula o prêmio, analisa o sinistro E emite o documento — separe.
- 🔒 **Restrição nova:** nenhuma classe com **mais de um motivo para mudar**; separem **regra de negócio** de **I/O** (tela, arquivo, e-mail).
- 🤔 **Por quê:** responsabilidades separadas deixam o sistema fácil de mudar sem quebrar o resto.

## Encontro 17 — ☕ KISS · DRY · YAGNI
**[Módulo: Princípios de Design](../../4-principios-desgin-poo/)**
- 📨 **Cliente:** *"Ficou **complicado demais** e cheio de função que ninguém usa. **Simplifica** isso."*
- 💡 **Exercita:** **KISS** (simplicidade), **DRY** (não repita), **YAGNI** (não faça o que não pediram).
- 📦 **Entregável:** uma **simplificação** — ex.: o cálculo de prêmio por perfil de risco cheio de ifs — removendo duplicação e complexidade sem perder função.
- 🎯 **Direcionamento:** extraiam método para tirar código repetido, simplifiquem o cálculo de prêmio por perfil de risco cheio de ifs e **apaguem** o que nenhum requisito pede.
- 🔒 **Restrição nova:** nenhum método gigante (regra prática: **cabe na tela**) e **zero** código "para o futuro" que não é usado hoje.
- 🤔 **Por quê:** código simples é mais barato de manter do que código "esperto".

## Encontro 18 — 📐 Componentes + Pacotes
**[Aula 17](../../03-POO/01-modelagem/analise-projeto-uml/17-diagrama-de-componentes/) + [18](../../03-POO/01-modelagem/analise-projeto-uml/18-diagrama-de-pacotes/)**
- 📨 **Cliente:** *"Talvez **outro time cuide** da parte de apólices e sinistros. Dá pra separar em módulos?"*
- 💡 **Exercita:** componentes (interfaces) + pacotes (camadas, **sem ciclos**).
- 📦 **Entregável:** **diagrama de pacotes** (camadas) + **componentes** com interfaces.
- 🎯 **Direcionamento:** agrupem as classes em pacotes por responsabilidade (apólices e sinistros) e reorganizem o código em `com.seguradora.{dominio,aplicacao,infra}`.
- 🔒 **Restrição nova:** **sem dependência cíclica**; o pacote de **domínio não pode depender** de UI nem de infraestrutura.
- 🤔 **Por quê:** fronteiras claras permitem dividir o trabalho e trocar peças sem efeito dominó.

## Encontro 19 — 📐 Implantação
**[Aula 19](../../03-POO/01-modelagem/analise-projeto-uml/19-diagrama-de-implantacao/)**
- 📨 **Cliente:** *"**Onde** isso vai rodar? Pensei em um portal do cliente, um servidor e um banco."*
- 💡 **Exercita:** nós (hardware/ambiente), artefatos, conexões de rede.
- 📦 **Entregável:** **diagrama de implantação** da topologia pretendida (mesmo que futura).
- 🎯 **Direcionamento:** desenhem onde cada parte roda (um portal do cliente, um servidor e um banco) e rotulem o **protocolo** de cada conexão.
- 🔒 **Restrição nova:** separem claramente o que é **público** do que é **privado** e **nomeiem os protocolos** (HTTP, JDBC…).
- 🤔 **Por quê:** pensar a topologia cedo evita surpresas de segurança e desempenho no fim.

## Encontro 20 — ☕ Fechamento: integração + padrões
**[Módulo: Design Patterns](../../05-design-patterns/)**
- 📨 **Cliente:** *"Quero ver **rodando de ponta a ponta**: abrir e analisar um sinistro, do jeito que a gente combinou."*
- 💡 **Exercita:** juntar tudo; enxergar onde **um padrão simples** ajuda (ex.: *Factory* para criar objetos, *Strategy* para regras que variam).
- 📦 **Entregável final:** sistema **Java rodando** com um `Principal` que demonstra o cenário completo **+** diagramas coerentes com o código.
- 🎯 **Direcionamento:** montem um `Principal` que executa "abrir e analisar um sinistro" do início ao fim e apliquem **um** padrão onde couber (não force).
- 🔒 **Restrição nova:** o sistema precisa **rodar sem erro** e os diagramas têm que **refletir o código final**.
- 🤔 **Por quê:** entregar rodando **e** coerente com o modelo é o que se espera de um projeto profissional.

---

## ✅ Como saber que o grupo está no caminho

- [ ] O código do Encontro 1 (procedural) foi **substituído** por objetos — e o grupo sabe **explicar por quê**.
- [ ] Cada regra de negócio vive **dentro** da classe dona do dado (nada de regra solta).
- [ ] Os **diagramas batem com o código** (mesma história em idiomas diferentes).
- [ ] O sistema evoluiu **sem reescrever do zero** a cada aula — cada conceito **somou**.
- [ ] Na entrega final, dá para apontar **em que Encontro** cada parte do sistema nasceu.

> 🧭 **Como o professor avalia:** cada 🔒 **Restrição nova** é um item de correção. Elas
> **acumulam** — uma restrição cobrada no Encontro 6 continua valendo no 16.

[⬅️ Banco de projetos](../README.md) · [📋 Briefing do cliente](README.md) · [📚 Curso de Análise e Projeto OO](../../03-POO/01-modelagem/analise-projeto-uml/)
