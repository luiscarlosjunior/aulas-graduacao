# Programação Orientada a Objetos (POO) em Java

Um curso prático e progressivo de POO: você aprende a **pensar em objetos**, a **modelar**
problemas reais e a dominar os **4 pilares**, sempre com exemplos executáveis (do simples
ao complexo) e exercícios com gabarito.

> 🎯 **Se você está começando, comece pelo módulo [00 - Introdução](00-introducao/) e não
> pule o [01 - Modelagem](01-modelagem/):** é ali que mora a habilidade que realmente
> diferencia um programador — sair de um problema do mundo real para classes bem desenhadas.

---

## 🗺️ Trilha do curso

| # | Módulo | O que você aprende |
|---|--------|--------------------|
| 00 | **[Introdução](00-introducao/)** | O clique mental da POO: objeto = estado + comportamento; classe × objeto |
| 01 | **[Modelagem](01-modelagem/)** ⭐ | Do problema real às classes: entidades, atributos, responsabilidades e relacionamentos (com UML) |
| 02 | **[Classes e Objetos](02-classes-e-objetos/)** | Sintaxe: atributos, métodos, construtores, `this`, `new` |
| 03 | **[Encapsulamento](03-encapsulamento/)** | `private`/`public`, getters/setters com validação, proteção de dados |
| 04 | **[Herança](04-heranca/)** | `extends`, `super`, `@Override`, reaproveitamento de código |
| 05 | **[Polimorfismo](05-polimorfismo/)** | Sobrecarga, sobrescrita, ligação dinâmica |
| 06 | **[Abstração](06-abstracao/)** | Classes e métodos abstratos (com diagramas) |
| 07 | **[Interfaces](07-interfaces/)** | Contratos, implementação múltipla, métodos `default` |
| 08 | **[SOLID](08-solid/)** | Cinco princípios para código de qualidade e manutenível |
| 09 | **[Exercícios](09-exercicios/)** | Consolidação: biblioteca, universidade, personagens... |

**Ordem sugerida:** 00 → 01 → 02 → ... → 09. Cada módulo tem um `README.md` com teoria +
exemplos e termina com um checklist do que você deve levar.

---

## 🧭 Os 4 pilares num relance

| Pilar | Responde à pergunta | Analogia |
|-------|---------------------|----------|
| **Encapsulamento** | Como protejo os dados de uso errado? | Cápsula de remédio |
| **Herança** | Como reaproveito o que já escrevi? | Família (filho herda do pai) |
| **Polimorfismo** | Como o mesmo comando faz coisas diferentes? | Botão *play* |
| **Abstração** | Como escondo a complexidade? | Volante do carro |

---

## ▶️ Como executar os exemplos

Cada pasta é independente. Entre nela, compile e rode a classe `Principal` (ou a indicada
no README do módulo):

```bash
cd 01-modelagem/exemplo-simples
javac *.java
java Principal
```

**Pré-requisitos:** Java 8+ (`java -version` para conferir) e um editor (VS Code
recomendado). Conhecimento básico de variáveis, condicionais e loops.

---

## 💡 Por que POO? (a ideia em uma frase)

Antes da POO, **dados e funções viviam separados** — qualquer código podia corromper
qualquer dado. A POO **junta o dado com as regras que cuidam dele numa única caixa: o
objeto.** Isso torna o código mais **seguro**, **reaproveitável** e **próximo do jeito que
pensamos sobre o mundo real**. O módulo [00 - Introdução](00-introducao/) mostra isso com
código lado a lado.

---

## 📈 Próximos passos

Depois de concluir este curso:

- **[Princípios de Design de POO](../4-principios-desgin-poo/)** — KISS, DRY, YAGNI,
  composição sobre herança
- **[Design Patterns](../05-design-patterns/)** — soluções reutilizáveis para problemas comuns
- **[Conceitos Intermediários](../03-conceitos-intermediarios/)** — Collections, Generics, Streams

---

**Anterior:** [Fundamentos](../01-fundamentos/) | **Próximo:** [Conceitos Intermediários](../03-conceitos-intermediarios/)
