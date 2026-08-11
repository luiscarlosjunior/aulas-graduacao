# 05 — Farmácia: estoque e vendas

## 📞 O pedido do cliente
> "Tenho uma farmácia de bairro. O que mais me preocupa é o **estoque**: saber quanto tem de
> cada remédio e o que está acabando. E toda **venda** deveria dar baixa no estoque
> automático. Alguns remédios só com receita — mas isso a gente detalha depois."

## 🧭 O que já sabemos
- Existem **produtos/medicamentos**, **estoque** e **vendas**.
- Cada produto tem nome, preço e quantidade em estoque.
- Uma venda tem vários itens e um total.

## ❓ Perguntas em aberto (levantem com o "cliente")
- A venda "tem" itens que morrem com ela (composição) ou aponta pra produtos?
- O que acontece ao vender um item sem estoque suficiente?
- Precisa controlar validade/lote e produtos que exigem receita?

## 🎚️ Complexidade sugerida
🟡 Bom para composição (Venda ◆ ItemVenda) e invariante de estoque não-negativo.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
