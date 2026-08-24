# 🚀 Plano de Evolução — 11 — Loja virtual: pedidos

> **Este é o roteiro do seu grupo.** O projeto **não nasce pronto**: a cada aula, alguém do
> projeto (o dono de uma loja online, gerente, dev sênior…) traz um **novo problema**, e resolvê-lo bem exige
> justamente o **conceito daquela aula**. O sistema começa como Java simples e vai ganhando
> POO, encapsulamento, herança, SOLID, KISS… **na hora em que cada um faz falta.**
>
> 📋 Briefing original do cliente: **[README deste projeto](README.md)**
> · 🗺️ Teoria do método (plano-mestre): **[../PLANO-DE-EVOLUCAO.md](../PLANO-DE-EVOLUCAO.md)**

As aulas **alternam**: um dia **☕ Java** (código), outro dia **📐 Modelagem** (UML), seguindo
a ordem dos diagramas do [curso de Análise e Projeto OO](../../03-POO/01-modelagem/analise-projeto-uml/).

> ### 📖 Como ler cada encontro
> - 🎙️ **O que mudou** — a "entrevista": quem pediu (cliente, gerente, dev sênior) e por que **agora**.
> - 💡 **Conceito da aula** — a ferramenta nova (a *teoria* vem na aula; aqui é só o nome dela).
> - 🎯 **O que fazer no seu projeto** — a **ação concreta**, no seu domínio: é isto que vocês entregam.
> - 📦 **Entregável** · 🔒 **Restrição nova** (o professor cobra; as restrições **acumulam**) · 🤔 **Por quê** (prepara o próximo passo).

---

## 🗺️ Mapa rápido (20 encontros)

| # | Trilha | Aula/tópico | O problema do encontro se resolve com… |
|---|--------|-------------|----------------------------------------|
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
- 🎙️ **O que mudou:** o dono de uma loja online chega com a ideia inicial: *"Por enquanto, só quero **cadastrar e listar os produtos e os clientes**. Deixa o resto pra depois — quero ver algo funcionando logo."* Ainda não há regras; é o primeiro esboço do sistema.
- 💡 **Conceito da aula:** variáveis, listas, laços, entrada/saída (ainda **sem classes**).
- 🎯 **O que fazer no seu projeto:** façam um programa de menu que permita **adicionar** e **listar** produtos, guardando tudo numa lista/array dentro do `main`. Só tipos básicos.
- 📦 **Entregável:** programa Java que roda e mostra a lista de produtos cadastrados.
- 🔒 **Restrição nova:** proibido criar classes próprias — só coleções e tipos básicos. (Vai ficar repetitivo e feio; é proposital.)
- 🤔 **Por quê:** vocês vão sentir a bagunça dos dados soltos — é exatamente isso que a POO vem resolver na próxima aula.

## Encontro 2 — 📐 Histórico + Conceitos de OO
**[Aula 01](../../03-POO/01-modelagem/analise-projeto-uml/01-historico-metodologias/) + [02](../../03-POO/01-modelagem/analise-projeto-uml/02-conceitos-orientacao-objetos/)**
- 🎙️ **O que mudou:** na segunda conversa, o dono de uma loja online completa: *"Ah, esqueci — também preciso registrar os pedidos, e cada pedido tem vários itens no carrinho."* De repente o programa do Encontro 1 fica confuso: os mesmos dados se repetem e se espalham por várias listas.
- 💡 **Conceito da aula:** objeto = estado + comportamento; como identificar entidades.
- 🎯 **O que fazer no seu projeto:** releiam o briefing + esse novo pedido e **grifem**: cada substantivo importante (produto, pedido…) vira candidato a **classe**; cada característica, a **atributo**; cada verbo, a **operação**. Montem a tabela "termo → classe / atributo / operação".
- 📦 **Entregável:** glossário do domínio + lista de entidades, atributos e operações candidatas.
- 🔒 **Restrição nova:** cada entidade candidata precisa de **uma frase** justificando por que existe (o que sabe e o que faz).
- 🤔 **Por quê:** um vocabulário claro agora evita retrabalho quando o código começar.

## Encontro 3 — 📐 Abstração + Classe e Objetos
**[Aula 03](../../03-POO/01-modelagem/analise-projeto-uml/03-abstracao/) + [04](../../03-POO/01-modelagem/analise-projeto-uml/04-classe-e-objetos/)**
- 🎙️ **O que mudou:** o **gerente do projeto** entra na conversa: *"O cliente falou um monte de coisa na reunião — até o time de futebol do produto. Nem tudo importa. Foquem no que o sistema precisa de verdade."*
- 💡 **Conceito da aula:** abstração (recorte) e classe (molde) × objeto (instância).
- 🎯 **O que fazer no seu projeto:** escolham de **3 a 6 classes** essenciais; para cada uma, escrevam nome, 2–4 **atributos** e 1–3 **operações**. Ao lado, anotem o que decidiram **deixar de fora** e por quê.
- 📦 **Entregável:** rascunho das primeiras classes (nome + atributos + operações) + a lista do que foi descartado.
- 🔒 **Restrição nova:** no máximo ~6 classes neste estágio; **justifiquem cada corte**.
- 🤔 **Por quê:** começar enxuto evita *over-engineering* — dá pra crescer com segurança depois.

## Encontro 4 — ☕ Classes e Objetos em Java
**[Módulo: Classes e Objetos](../../03-POO/02-classes-e-objetos/)**
- 🎙️ **O que mudou:** o **dev sênior** revisa o código do Encontro 1: *"Isso não escala. Cada pedido é uma linha solta numa lista, e a gente se perde. Vamos transformar cada coisa do domínio numa classe de verdade, com construtor."*
- 💡 **Conceito da aula:** `class`, atributos, construtor, `this`, `new`.
- 🎯 **O que fazer no seu projeto:** transformem as classes candidatas do Encontro 3 em **classes Java**, cada uma com **construtor** que exige os dados obrigatórios. No `main`, criem alguns objetos (ex.: alguns produtos) e usem.
- 📦 **Entregável:** classes Java compilando + `Principal` que cria e usa objetos (aposentando o código procedural).
- 🔒 **Restrição nova:** todo atributo nasce `private`; o `main` só **orquestra** objetos (nada de regra de negócio nele).
- 🤔 **Por quê:** objetos "donos de si" preparam o encapsulamento do próximo dia.

## Encontro 5 — 📐 Atributos + Operações
**[Aula 06](../../03-POO/01-modelagem/analise-projeto-uml/06-atributos/) + [07](../../03-POO/01-modelagem/analise-projeto-uml/07-operacoes/)**
- 🎙️ **O que mudou:** conversando de novo, o dono de uma loja online solta uma regra que ninguém tinha dito: *"Tem uma coisa que não pode acontecer de jeito nenhum: não pode finalizar um pedido com produto sem estoque."* Isso não é um dado — é um **comportamento** que o sistema precisa garantir.
- 💡 **Conceito da aula:** atributos (visibilidade) e operações (assinatura).
- 🎯 **O que fazer no seu projeto:** definam a **visibilidade** de cada atributo e a **assinatura** de cada operação (parâmetros e retorno). Decidam **qual classe** será dona da regra "não pode finalizar um pedido com produto sem estoque" e criem a operação que a implementa.
- 📦 **Entregável:** classes com atributos e operações completos; a regra vira uma operação de uma classe.
- 🔒 **Restrição nova:** **nenhuma classe anêmica** — cada uma com ≥1 operação de negócio (não só get/set).
- 🤔 **Por quê:** decidir onde a regra mora agora evita espalhá-la pelo código depois.

## Encontro 6 — ☕ Encapsulamento
**[Módulo: Encapsulamento](../../03-POO/03-encapsulamento/)**
- 🎙️ **O que mudou:** o **dev sênior** chega preocupado com um incidente: *"Tivemos um problema sério: o preço do produto ficou negativo. Alguém alterou o dado direto, sem passar por validação. Não pode se repetir."*
- 💡 **Conceito da aula:** `private` + validação; proteger o invariante.
- 🎯 **O que fazer no seu projeto:** deixem os atributos `private` e criem métodos que **validam antes de alterar** o estado. Implementem em código a regra "não pode finalizar um pedido com produto sem estoque": se a operação a violaria, ela **recusa** (lança exceção) em vez de gravar o valor errado.
- 📦 **Entregável:** classe(s) com atributos `private` e métodos que tornam o estado inválido **impossível**.
- 🔒 **Restrição nova:** estado inválido deve ser impossível — a validação **lança exceção**; proibido setter que aceita qualquer valor.
- 🤔 **Por quê:** um objeto que se protege sozinho elimina uma classe inteira de bugs.

## Encontro 7 — 📐 Associação
**[Aula 05](../../03-POO/01-modelagem/analise-projeto-uml/05-associacao/)**
- 🎙️ **O que mudou:** o **analista** aponta: *"Precisamos enxergar como as coisas se conectam — um cliente faz muitos pedidos; um pedido é feito de vários itens — e quantas de cada lado."* As classes deixam de ser ilhas.
- 💡 **Conceito da aula:** associação, multiplicidade, agregação × composição.
- 🎯 **O que fazer no seu projeto:** liguem suas classes com a **multiplicidade nas duas pontas** (um cliente faz muitos pedidos; um pedido é feito de vários itens). Para cada "tem-um", decidam: a parte **morre com o todo** (composição ◆) ou **sobrevive** (agregação ◇)?
- 📦 **Entregável:** diagrama de classes com relacionamentos e multiplicidades.
- 🔒 **Restrição nova:** toda associação com **multiplicidade explícita** e um nome/verbo; justifiquem cada composição.
- 🤔 **Por quê:** a multiplicidade correta vira integridade de dados (e futura chave estrangeira no banco).

## Encontro 8 — ☕ Herança
**[Módulo: Herança](../../03-POO/04-heranca/)**
- 🎙️ **O que mudou:** o dono de uma loja online traz uma novidade de negócio: *"Passamos a ter um **cliente VIP**. É quase igual aos outros clientes, mas com um detalhe: tem frete grátis e desconto progressivo."* Copiar e colar a classe inteira só pra mudar um detalhe seria desperdício (e fonte de bug).
- 💡 **Conceito da aula:** `extends`, `super`, reúso (o "é um").
- 🎯 **O que fazer no seu projeto:** vocês já têm a classe **`cliente`**. Criem uma **subclasse** para o **cliente VIP** que **herda** de `cliente` e **acrescenta só o que muda** (tem frete grátis e desconto progressivo) — não reescrevam o que já existe, reaproveitem com `super`. No `main`, criem um cliente VIP e usem-no onde um `cliente` é esperado.
- 📦 **Entregável:** subclasse de `cliente` funcionando, **sem duplicar** o código da base.
- 🔒 **Restrição nova:** só herdem se passar no teste **"é um"** (cliente VIP **é um** cliente?); se for "tem um", usem composição. A superclasse pode ser `abstract`.
- 🤔 **Por quê:** herança certa elimina duplicação; herança errada engessa o sistema.

## Encontro 9 — ☕ Polimorfismo
**[Módulo: Polimorfismo](../../03-POO/05-polimorfismo/)**
- 🎙️ **O que mudou:** o **dev sênior** sente um cheiro ruim no código: *"Vi vários `if` verificando o tipo do cliente pra tratar o cliente VIP de um jeito diferente. Isso vira um pesadelo quando aparecer mais um tipo."*
- 💡 **Conceito da aula:** sobrescrita (`@Override`), ligação dinâmica.
- 🎯 **O que fazer no seu projeto:** peguem o comportamento que muda entre os tipos (ex.: tem frete grátis e desconto progressivo) e **sobrescrevam** esse método na subclasse cliente VIP. No resto do sistema, chamem o método pela **referência de `cliente`** e deixem o objeto decidir o que fazer.
- 📦 **Entregável:** um método sobrescrito; o código que o chama **não pergunta** qual é o tipo concreto.
- 🔒 **Restrição nova:** proibido `if`/`instanceof` para escolher comportamento por tipo — quem decide é o objeto.
- 🤔 **Por quê:** adicionar um tipo novo no futuro não deve exigir tocar no código que já funciona (base do OCP, Encontro 16).

## Encontro 10 — 📐 Casos de Uso
**[Aula 08](../../03-POO/01-modelagem/analise-projeto-uml/08-diagrama-casos-de-uso/)**
- 🎙️ **O que mudou:** o **gerente do projeto** quer fechar o escopo: *"Antes de continuar, preciso da lista do que o sistema faz e **para quem**. Nada de inventar tela — quero os objetivos dos usuários."*
- 💡 **Conceito da aula:** atores + casos de uso; «include»/«extend».
- 🎯 **O que fazer no seu projeto:** identifiquem os **atores** (quem usa o sistema) e listem os **casos de uso** (verbo + objeto). Desenhem o diagrama e escrevam o **fluxo principal + 1 alternativo** do caso mais importante (fechar o carrinho (checkout)).
- 📦 **Entregável:** diagrama de casos de uso + descrição do caso principal.
- 🔒 **Restrição nova:** caso de uso = **objetivo do usuário** (não clique de tela); mínimo **2 atores**; usem «include»/«extend» ao menos uma vez.
- 🤔 **Por quê:** delimitar escopo evita o "sistema que faz tudo e não entrega nada".

## Encontro 11 — 📐 Diagrama de Classes (consolidação)
**[Aula 09](../../03-POO/01-modelagem/analise-projeto-uml/09-diagrama-de-classes/)**
- 🎙️ **O que mudou:** o **arquiteto** pede: *"Já temos bastante código. Me mostra a **planta completa** do sistema pra eu conferir se está tudo coerente antes de seguir."*
- 💡 **Conceito da aula:** visão estrutural completa (o diagrama mais usado da UML).
- 🎯 **O que fazer no seu projeto:** consolidem tudo num **único diagrama de classes**: classes, atributos, operações, herança, associações e multiplicidades. Comparem com o código dos Encontros 4, 6, 8 e 9.
- 📦 **Entregável:** diagrama de classes completo, batendo com o código.
- 🔒 **Restrição nova:** o diagrama tem que refletir o código **100%**. Divergiu? Corrijam **um dos dois**.
- 🤔 **Por quê:** modelo e código contando a mesma história é o que mantém o projeto sustentável.

## Encontro 12 — ☕ Abstração e Interfaces
**[Abstração](../../03-POO/06-abstracao/) + [Interfaces](../../03-POO/07-interfaces/)**
- 🎙️ **O que mudou:** o **dev sênior** antecipa uma mudança: *"Hoje só temos um jeito de lidar com a forma de pagamento (cartão, Pix, boleto), mas isso vai mudar em breve. Não podem amarrar o sistema a uma implementação só."*
- 💡 **Conceito da aula:** classe abstrata + interface (contrato).
- 🎯 **O que fazer no seu projeto:** criem a interface **`MeioDePagamento`** para a forma de pagamento (cartão, Pix, boleto), com pelo menos **duas implementações** diferentes. Façam o resto do sistema depender **só da interface** e escolher a implementação na hora de criar o objeto.
- 📦 **Entregável:** interface `MeioDePagamento` + 2 implementações intercambiáveis.
- 🔒 **Restrição nova:** nenhuma classe de negócio pode depender da **implementação concreta** — só do contrato.
- 🤔 **Por quê:** trocar de estratégia vira **escrever uma classe nova** (e facilita testes com um "dublê").

## Encontro 13 — 📐 Objetos + Sequência
**[Aula 10](../../03-POO/01-modelagem/analise-projeto-uml/10-diagrama-de-objetos/) + [11](../../03-POO/01-modelagem/analise-projeto-uml/11-diagrama-de-sequencia/)**
- 🎙️ **O que mudou:** o **analista de qualidade** quer validar o comportamento: *"Me mostra, passo a passo, o que acontece quando alguém vai fechar o carrinho (checkout). Quero ver **quem chama quem** e o que acontece se algo der errado."*
- 💡 **Conceito da aula:** objetos concretos + mensagens no tempo.
- 🎯 **O que fazer no seu projeto:** desenhem a **sequência** de "fechar o carrinho (checkout)", mostrando as mensagens entre os objetos na ordem em que acontecem, incluindo o **caminho alternativo** (por ex., quando a regra "não pode finalizar um pedido com produto sem estoque" é violada).
- 📦 **Entregável:** diagrama de objetos de um cenário real + diagrama de sequência do fluxo principal.
- 🔒 **Restrição nova:** a sequência precisa mostrar **≥1 caminho alternativo** (`alt`/`opt`) — não só o "tudo deu certo".
- 🤔 **Por quê:** pensar o fluxo antes de codar revela chamadas e erros que o código esconderia.

## Encontro 14 — 📐 Máquina de Estados
**[Aula 14](../../03-POO/01-modelagem/analise-projeto-uml/14-diagrama-de-maquina-de-estados/)**
- 🎙️ **O que mudou:** o **analista** percebe um problema recorrente: *"O pedido está bagunçado — teve caso de **pular etapa**. Precisa ter fases bem definidas e não deixar avançar fora de ordem."*
- 💡 **Conceito da aula:** estados, transições, guardas.
- 🎯 **O que fazer no seu projeto:** mapeiem os estados de o pedido (`criado → pago → enviado → entregue; ou cancelado`) e o evento que dispara cada transição. Depois, no código, garantam que só dá para ir de um estado ao **próximo válido**.
- 📦 **Entregável:** máquina de estados de o pedido + as transições implementadas como métodos.
- 🔒 **Restrição nova:** transição inválida é **barrada** — pular etapa lança exceção (nada de mudar o `status` na mão).
- 🤔 **Por quê:** estados impossíveis são fonte clássica de bug; o diagrama vira validação no código (como o `Assinatura` do [Melodia](../../03-POO/01-modelagem/analise-projeto-uml/projeto-base-java/)).

## Encontro 15 — 📐 Atividades
**[Aula 15](../../03-POO/01-modelagem/analise-projeto-uml/15-diagrama-de-atividades/)**
- 🎙️ **O que mudou:** o dono de uma loja online quer treinar a equipe nova: *"Preciso do **passo a passo do carrinho à entrega**, de um jeito que qualquer funcionário entenda o processo."*
- 💡 **Conceito da aula:** fluxo de processo, decisões, raias.
- 🎯 **O que fazer no seu projeto:** modelem o processo do carrinho à entrega com início, ações, **decisões** e fim; usem **raias** para deixar claro **quem** faz cada passo (cliente, atendente, sistema).
- 📦 **Entregável:** diagrama de atividades do processo.
- 🔒 **Restrição nova:** o fluxo precisa ter **≥1 decisão** (losango) e o responsável de cada ação explícito (raias).
- 🤔 **Por quê:** enxergar o processo inteiro alinha a equipe e expõe passos esquecidos.

## Encontro 16 — ☕ SOLID
**[Módulo: SOLID](../../03-POO/08-solid/)**
- 🎙️ **O que mudou:** o **tech lead** dá o alerta numa revisão: *"O sistema cresceu e virou uma bola de lama — toda mudancinha quebra outra coisa. Por exemplo: a classe Pedido calcula o total, cobra E dispara o e-mail. Precisamos organizar."*
- 💡 **Conceito da aula:** SRP (responsabilidade única), OCP (aberto/fechado)…
- 🎯 **O que fazer no seu projeto:** achem a classe que "faz demais" e **separem responsabilidades** — comecem por: a classe Pedido calcula o total, cobra E dispara o e-mail. Deixem a **regra de negócio** numa classe e a parte de **tela/arquivo/e-mail** em outra.
- 📦 **Entregável:** refatoração com responsabilidades separadas (mesmo comportamento, código mais limpo).
- 🔒 **Restrição nova:** nenhuma classe com **mais de um motivo para mudar**; regra de negócio separada de I/O.
- 🤔 **Por quê:** responsabilidades separadas deixam o sistema fácil de mudar sem quebrar o resto.

## Encontro 17 — ☕ KISS · DRY · YAGNI
**[Módulo: Princípios de Design](../../4-principios-desgin-poo/)**
- 🎙️ **O que mudou:** o **tech lead** revisa de novo: *"Agora exageraram pro outro lado — o cálculo de frete/desconto/cupom embolado — e ainda tem código que ninguém usa. **Simplifiquem.**"*
- 💡 **Conceito da aula:** KISS (simplicidade), DRY (não repita), YAGNI (não faça o que não pediram).
- 🎯 **O que fazer no seu projeto:** removam duplicação (extraiam método), simplifiquem o cálculo de frete/desconto/cupom embolado e **apaguem** todo código que nenhum requisito atual pede.
- 📦 **Entregável:** código mais simples e sem duplicação, com o mesmo comportamento.
- 🔒 **Restrição nova:** nenhum método gigante (regra prática: **cabe na tela**) e **zero** código "para o futuro" que não é usado hoje.
- 🤔 **Por quê:** código simples é mais barato de manter do que código "esperto".

## Encontro 18 — 📐 Componentes + Pacotes
**[Aula 17](../../03-POO/01-modelagem/analise-projeto-uml/17-diagrama-de-componentes/) + [18](../../03-POO/01-modelagem/analise-projeto-uml/18-diagrama-de-pacotes/)**
- 🎙️ **O que mudou:** o **arquiteto** planeja crescer o time: *"Outro time vai assumir a parte de pagamento e envio (logística). Precisamos separar isso em **módulos** com fronteiras claras."*
- 💡 **Conceito da aula:** componentes (interfaces) + pacotes (camadas, sem ciclos).
- 🎯 **O que fazer no seu projeto:** agrupem as classes em **pacotes por responsabilidade** e reorganizem o código em `com.loja.{dominio,aplicacao,infra}`. Desenhem as dependências entre os pacotes.
- 📦 **Entregável:** diagrama de pacotes + componentes com interfaces; código reorganizado.
- 🔒 **Restrição nova:** **sem dependência cíclica**; o pacote de **domínio não depende** de UI nem de infraestrutura.
- 🤔 **Por quê:** fronteiras claras permitem dividir o trabalho e trocar peças sem efeito dominó.

## Encontro 19 — 📐 Implantação
**[Aula 19](../../03-POO/01-modelagem/analise-projeto-uml/19-diagrama-de-implantacao/)**
- 🎙️ **O que mudou:** o pessoal de **infra (DevOps)** entra na conversa: *"Antes de publicar, preciso saber **onde** isso vai rodar. Pensei em o site, um servidor de aplicação, um banco e um gateway de pagamento. Como as partes se conectam?"*
- 💡 **Conceito da aula:** nós (hardware/ambiente), artefatos, conexões de rede.
- 🎯 **O que fazer no seu projeto:** desenhem onde cada parte roda (o site, um servidor de aplicação, um banco e um gateway de pagamento) e rotulem o **protocolo** de cada conexão (HTTP, JDBC…). Marquem o que é **público** e o que é **interno**.
- 📦 **Entregável:** diagrama de implantação da topologia pretendida (mesmo que futura).
- 🔒 **Restrição nova:** separar **público de privado** e **nomear os protocolos**.
- 🤔 **Por quê:** pensar a topologia cedo evita surpresas de segurança e desempenho no fim.

## Encontro 20 — ☕ Fechamento: integração + padrões
**[Módulo: Design Patterns](../../05-design-patterns/)**
- 🎙️ **O que mudou:** o dono de uma loja online e o **gerente** fecham o ciclo: *"Chegou a hora: quero ver **rodando de ponta a ponta** — fechar o carrinho (checkout) — do jeito que a gente combinou."*
- 💡 **Conceito da aula:** integração + um padrão simples (Factory, Strategy).
- 🎯 **O que fazer no seu projeto:** montem um `Principal` que executa "fechar o carrinho (checkout)" do começo ao fim, usando as classes de verdade. Se um padrão simples ajudar (ex.: *Factory* para criar objetos, *Strategy* para a forma de pagamento (cartão, Pix, boleto)), apliquem — **sem forçar**.
- 📦 **Entregável final:** sistema **Java rodando** + diagramas coerentes com o código.
- 🔒 **Restrição nova:** o sistema **roda sem erro** e os diagramas **refletem o código final**.
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
