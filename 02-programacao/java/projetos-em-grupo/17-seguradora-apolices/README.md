# 17 — Seguradora: apólices e sinistros

## 📞 O pedido do cliente
> "Trabalho com seguros. Cada **cliente** contrata uma **apólice** (de carro, de vida…) com
> uma cobertura e um valor. Quando dá problema, ele abre um **sinistro** e eu analiso se
> cobre ou não. Quero pelo menos controlar as apólices ativas e os sinistros abertos."

## 🧭 O que já sabemos
- Existem **clientes**, **apólices** e **sinistros**.
- Uma apólice tem tipo, cobertura, valor e vigência.
- Um sinistro é aberto sobre uma apólice e precisa ser analisado.

## ❓ Perguntas em aberto (levantem com o "cliente")
- A apólice tem estados (ativa → vencida → cancelada)?
- O sinistro tem fluxo (aberto → em análise → aprovado/negado → pago)?
- Tipos de seguro diferentes se comportam diferente? (herança/polimorfismo)

## 🎚️ Complexidade sugerida
🔴 Projeto rico: duas máquinas de estados (apólice e sinistro) e polimorfismo por tipo.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
