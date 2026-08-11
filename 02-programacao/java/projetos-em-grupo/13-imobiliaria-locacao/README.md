# 13 — Imobiliária: locação de imóveis

## 📞 O pedido do cliente
> "Tenho uma imobiliária. Trabalho com **imóveis** para alugar, os **proprietários** e os
> **inquilinos**. Quando fecha negócio, gero um **contrato de aluguel** com valor mensal e
> prazo. Saber quais imóveis estão disponíveis já ajudaria muito. Reajuste e IPTU depois."

## 🧭 O que já sabemos
- Existem **imóveis**, **proprietários**, **inquilinos** e **contratos**.
- Um imóvel pertence a um proprietário e pode estar disponível ou alugado.
- Um contrato liga um imóvel a um inquilino, com valor e prazo.

## ❓ Perguntas em aberto (levantem com o "cliente")
- O imóvel tem estados (disponível → alugado → em manutenção)?
- Proprietário e inquilino compartilham dados? (pense em generalização: Pessoa)
- Como registrar os pagamentos mensais do aluguel?

## 🎚️ Complexidade sugerida
🟡 Bom para associações múltiplas e máquina de estados do imóvel/contrato.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
