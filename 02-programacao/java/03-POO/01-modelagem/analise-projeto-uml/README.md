# Análise e Projeto de Sistemas Orientados a Objetos (com UML)

> **Curso completo de modelagem OO.** Cada tópico é uma **pasta independente** com teoria
> academicamente correta, notação, exemplos, **vantagens, desvantagens, dicas** e uma seção
> **"na indústria"** (como aquilo é — ou não é — usado no dia a dia de uma empresa).
>
> Todo o curso gira em torno de **um único domínio-base**: a plataforma de streaming de
> música **Melodia**, cujas assinaturas são pagas por uma **conta bancária**. Assim você vê
> *o mesmo sistema* por todas as lentes da UML.

---

## 🔗 Duas trilhas, um sistema (modelagem × Java)

As aulas acontecem **intercaladas**: um dia **modelagem** (aqui), outro dia **Java**. O
mesmo sistema aparece dos dois lados, e eles se referenciam mutuamente:

| Dia de **Modelagem** (esta pasta) | Dia de **Java** (código que roda) |
|-----------------------------------|-----------------------------------|
| Diagramas UML do domínio Melodia | **[projeto-base-java/](projeto-base-java/)** — implementação executável |
| "*o quê* e *como* desenhar" | "*como* isso vira código que compila" |

> 🎯 Ao final de cada diagrama, abra o [projeto Java](projeto-base-java/) e ache a classe
> correspondente. Modelo e código devem contar **a mesma história**.

---

## 🗺️ Trilha do curso (cada item é uma pasta)

### Parte I — Fundamentos de Orientação a Objetos
| # | Tópico | O que você aprende |
|---|--------|--------------------|
| 00 | **[Projeto-base](00-projeto-base/)** | O domínio Melodia (streaming + banco): a especificação que vira tudo |
| 01 | **[Histórico das Metodologias](01-historico-metodologias/)** | De onde vêm a OO e a UML; cascata, estruturada, ágil |
| 02 | **[Conceitos de OO](02-conceitos-orientacao-objetos/)** | Objeto, classe, mensagem e os 4 pilares |
| 03 | **[Abstração](03-abstracao/)** | Focar no essencial; recorte e generalização |
| 04 | **[Classe e Objetos](04-classe-e-objetos/)** | Molde × instância |
| 05 | **[Associação](05-associacao/)** | Associação, agregação, composição, herança e multiplicidade |
| 06 | **[Atributos](06-atributos/)** | Propriedades, visibilidade, derivados |
| 07 | **[Operações](07-operacoes/)** | Comportamento, assinatura, operação × método |

### Parte II — Os 13 diagramas da UML 2.x
| # | Diagrama | Família | Responde |
|---|----------|---------|----------|
| 08 | **[Casos de Uso](08-diagrama-casos-de-uso/)** | Comportamental | O que o sistema faz e para quem |
| 09 | **[Classes](09-diagrama-de-classes/)** | Estrutural | Quais classes existem e como se ligam |
| 10 | **[Objetos](10-diagrama-de-objetos/)** | Estrutural | Um retrato do sistema com instâncias reais |
| 11 | **[Sequência](11-diagrama-de-sequencia/)** | Comportamental | A ordem das mensagens no tempo |
| 12 | **[Estrutura Composta](12-diagrama-de-estrutura-composta/)** | Estrutural | As partes internas de um elemento |
| 13 | **[Comunicação](13-diagrama-de-comunicacao/)** | Comportamental | Quem fala com quem |
| 14 | **[Máquina de Estados](14-diagrama-de-maquina-de-estados/)** | Comportamental | O ciclo de vida de um objeto |
| 15 | **[Atividades](15-diagrama-de-atividades/)** | Comportamental | O passo a passo de um processo |
| 16 | **[Interação Geral](16-diagrama-de-interacao-geral/)** | Comportamental | Como vários cenários se encadeiam |
| 17 | **[Componentes](17-diagrama-de-componentes/)** | Estrutural | As peças de software e suas interfaces |
| 18 | **[Pacotes](18-diagrama-de-pacotes/)** | Estrutural | A organização em módulos |
| 19 | **[Implantação](19-diagrama-de-implantacao/)** | Estrutural | Onde o software roda (hardware) |
| 20 | **[Temporização](20-diagrama-de-temporizacao/)** | Comportamental | Estado × tempo medido |

**Ordem sugerida:** 00 → 01 → … → 20. Cada pasta é autocontida e termina com um checklist.

---

## 🧠 Como estudar (método do professor)

1. **Leia o [00-projeto-base](00-projeto-base/)** até conseguir contar a história do sistema.
2. Nos fundamentos (01–07), fixe o **vocabulário**: sem ele, os diagramas não fazem sentido.
3. Em cada diagrama (08–20), pergunte: *"que pergunta este diagrama responde?"*. Guardar isso
   vale mais que decorar símbolos.
4. **Alterne com o Java:** implemente/rode o [projeto-base-java](projeto-base-java/) e
   confira se o código bate com o desenho.

> 📐 A UML é uma **caixa de ferramentas**, não um checklist. Num projeto real você usa 3 ou 4
> diagramas, não os 13. O curso ensina todos para você **saber escolher**.

---

[⬅️ Voltar para 01 - Modelagem](../README.md) | [📚 Índice do curso de POO](../../README.md) | [Começar pelo Projeto-base ➡️](00-projeto-base/)
