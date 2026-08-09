# 02 — Conceitos de Orientação a Objetos

> A OO enxerga o sistema como uma **sociedade de objetos** que **trocam mensagens** para
> realizar tarefas. Antes dos diagramas, fixe o vocabulário — ele é a base de tudo que vem
> depois.

---

## 1. A ideia central

Na programação estruturada, dados e funções vivem separados. Na **orientação a objetos**,
cada **objeto** reúne **dados (estado)** + **comportamento (operações)** e é responsável por
si mesmo. O sistema funciona porque os objetos **colaboram**: um pede algo ao outro por meio
de **mensagens** (chamadas de método).

```mermaid
flowchart LR
    O["Ouvinte: ana"] -->|"assinarPremium()"| P["Plataforma"]
    P -->|"cobrar()"| A["Assinatura"]
    A -->|"debitarAssinatura(19.90)"| C["ContaBancaria"]
```
*No Melodia, "assinar Premium" é uma cadeia de mensagens entre objetos — cada um faz sua parte.*

---

## 2. O vocabulário essencial

| Conceito | O que é | Exemplo no Melodia |
|----------|---------|--------------------|
| **Objeto** | Uma coisa com **estado** + **comportamento** | A conta `0001-1` da Ana, com saldo R$ 30,10 |
| **Classe** | O *molde* que define os objetos de um tipo | `ContaBancaria`, `Musica`, `Assinatura` |
| **Atributo** | Um dado que o objeto guarda | `saldo`, `titulo`, `status` |
| **Operação/Método** | Uma ação que o objeto sabe fazer | `depositar()`, `reproduzir()` |
| **Mensagem** | Um objeto pedindo algo a outro | `assinatura.cobrar(conta)` |
| **Estado** | O conjunto de valores dos atributos num instante | assinatura *ativa*, saldo R$ 30,10 |

---

## 3. Os quatro pilares

| Pilar | Pergunta que responde | No Melodia | Aprofundamento |
|-------|-----------------------|------------|----------------|
| **Abstração** | Como mostro só o essencial? | `Usuario` genérico esconde detalhes | [03-abstracao](../03-abstracao/) |
| **Encapsulamento** | Como protejo os dados? | `saldo` privado, mexido só por métodos | [módulo 03](../../../03-encapsulamento/) |
| **Herança** | Como reaproveito código? | `Ouvinte`/`Artista` **é um** `Usuario` | [módulo 04](../../../04-heranca/) |
| **Polimorfismo** | Como a mesma ação varia? | `tipoDePerfil()` difere por tipo | [módulo 05](../../../05-polimorfismo/) |

---

## 4. Vantagens e desvantagens da OO

| ✅ Vantagens | ❌ Desvantagens / cuidados |
|-------------|---------------------------|
| Modela o mundo real de forma intuitiva | Curva de aprendizado maior que a estruturada |
| Encapsulamento reduz efeito-cascata de mudanças | Excesso de camadas/abstrações vira complexidade tola |
| Herança/polimorfismo favorecem reúso e extensão | Herança mal usada gera acoplamento rígido |
| Facilita testar partes isoladas | Overhead de memória/indireção (relevante em alto desempenho) |

> 🏭 **Na indústria:** a OO domina back-end corporativo (Java, C#), mobile (Kotlin, Swift) e
> muito do front-end. Mas paradigmas **funcionais** (imutabilidade, funções puras) voltaram
> forte e hoje se **misturam** com a OO — Java moderno tem lambdas, `record`, `streams`.
> Bons engenheiros usam **o pilar certo para o problema**, não OO por dogma.

---

## 5. Quando OO **não** é a melhor escolha

- **Processamento de dados massivo/estatístico:** pipelines funcionais/SQL costumam ser mais
  claros que hierarquias de objetos.
- **Scripts pequenos e utilitários:** OO pode ser peso morto (*over-engineering*).
- **Altíssimo desempenho/baixo nível:** indireção de objetos pode custar caro (games de
  motor, sistemas embarcados usam *data-oriented design*).

---

## 💡 Dicas de professor

- Pense **"quem é responsável por isto?"** antes de **"onde ponho esta função?"**. A resposta
  costuma indicar a classe certa.
- Fuja da **classe anêmica** (só dados, sem comportamento) — é OO "de fachada": os dados de um
  objeto e as regras que os manipulam devem morar **juntos**.
- **Composição costuma ser melhor que herança.** Herança é forte e rígida; use só no "é um" real.

---

## ✅ O que levar desta pasta

- [ ] Objeto = **estado + comportamento**; o sistema é objetos **colaborando por mensagens**.
- [ ] Domino o vocabulário: objeto, classe, atributo, operação, mensagem, estado.
- [ ] Sei nomear e explicar os **4 pilares** com um exemplo do Melodia cada.
- [ ] Sei que OO tem **limites** e convive com outros paradigmas.

---

[⬅️ 01 - Histórico](../01-historico-metodologias/) | [Índice](../README.md) | [03 - Abstração ➡️](../03-abstracao/)
