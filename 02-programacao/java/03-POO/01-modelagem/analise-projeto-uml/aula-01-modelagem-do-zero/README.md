# Aula 01 — Modelagem do zero (deck em draw.io)

> **Comece o curso por aqui.** Esta aula mostra, **só com diagramas**, como um sistema tipo
> **Spotify** (o nosso *Melodia*) sai de **uma frase do cliente** e chega a um **modelo
> concreto**, um estágio de cada vez. É o "porquê" da modelagem, ao vivo, para você conduzir
> em sala.

---

## 📂 O arquivo da aula

**[`modelagem-do-zero-melodia.drawio`](modelagem-do-zero-melodia.drawio)** — um único arquivo
com **10 páginas (abas)**. Cada aba é um **estágio da evolução** do modelo, e traz uma
**caixa amarela de anotações** (o que falar) para você usar na lousa.

### Como abrir
- **Online:** acesse **[app.diagrams.net](https://app.diagrams.net)** → *Open Existing Diagram*
  → selecione o arquivo. (Ou arraste o arquivo para a janela.)
- **Desktop:** instale o **draw.io Desktop** e dê duplo-clique no arquivo.
- **VS Code:** instale a extensão *Draw.io Integration* (`hediet.vscode-drawio`) e abra o
  `.drawio` direto no editor.
- As **abas na parte de baixo** do draw.io são os 10 estágios — passe uma a uma durante a aula.

> 🎨 **Convenção de cores:** caixa **verde** = classe/elemento que **nasceu naquele estágio**;
> **azul** = o que já existia; **cinza** = classe abstrata/enumeração.

---

## 🗺️ As 10 páginas (e o que dizer em cada uma)

| Aba | Estágio | Ideia central para explicar |
|-----|---------|-----------------------------|
| **0 · Capa** | O requisito (1 frase) | Mostre o pedido do cliente e pergunte: *"como isso vira software?"* Peça para grifarem substantivos/verbos. |
| **1 · Do texto ao modelo** | Técnica | Substantivo → classe/atributo; verbo → operação. Nem todo substantivo vira classe. |
| **2 · v1 — Primeiras classes** | `Usuario`, `Musica` | Modelar é **recortar**: comece pelo essencial, sem prever tudo. |
| **3 · v2 — Atributos e operações** | Comportamento | Estado + comportamento juntos; dado **privado**, acesso por método (prepara encapsulamento). |
| **4 · v3 — Playlist** | Associação | Surge a playlist → **relacionamento** e **multiplicidade** (`1`, `0..*`). |
| **5 · v4 — Artista e Álbum** | Composição × Agregação | **Álbum ◆ Música** (parte morre com o todo) × **Playlist ◇ Música** (parte sobrevive). O ponto-chave da aula. |
| **6 · v5 — Assinatura e pagamento** | Integração de domínios | "Sem anúncios" puxa `Assinatura`, `Plano`, `ContaBancaria` — streaming encontra o banco. |
| **7 · v6 — Herança** | Generalização | `Ouvinte`/`Artista` **é um** `Usuario` (abstrato); mesma operação, respostas diferentes = polimorfismo. |
| **8 · v7 — Modelo concreto** | Consolidação | De **uma frase** a um diagrama completo — que já **é** a estrutura das classes Java. |
| **9 · Outra lente + fecho** | Máquina de estados | Modelar não é só classes: a `Assinatura` como ciclo de vida. Cada diagrama responde a uma pergunta. |

---

## 🎯 O recado que a aula deixa

1. **Modelar antes de codar** economiza tempo: erro no papel é barato, erro em produção é caro.
2. Um bom modelo **começa simples e cresce** conforme a necessidade aparece (v1 → v7).
3. O diagrama de classes final **não é burocracia** — ele **é** o esqueleto do código.
4. Existem **várias lentes** (classes, estados, casos de uso…): escolha a que responde à sua
   pergunta.

---

## 🔗 Para onde ir depois

- Aprofunde o **domínio** em **[00 - Projeto-base (Melodia)](../00-projeto-base/)**.
- Veja cada diagrama em detalhe a partir de **[08 - Casos de Uso](../08-diagrama-casos-de-uso/)**.
- No "dia de Java", o mesmo sistema roda em
  **[projeto-base-java/](../projeto-base-java/)**.

---

[🏠 Índice do curso](../README.md) | [Próximo: 00 - Projeto-base ➡️](../00-projeto-base/)
