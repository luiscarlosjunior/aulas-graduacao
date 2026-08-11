# 06 — Restaurante: comandas e pedidos

## 📞 O pedido do cliente
> "Toco um restaurante. Cada **mesa** abre uma **comanda**, o garçom vai anotando os
> **pedidos** e no fim eu preciso **fechar a conta** com o total. Hoje é papelzinho e sempre
> some algum. Divisão de conta e taxa do garçom vemos mais pra frente."

## 🧭 O que já sabemos
- Existem **mesas**, **comandas**, **itens do cardápio** e **pedidos**.
- Uma comanda pertence a uma mesa e acumula vários pedidos.
- Cada item do cardápio tem nome e preço.

## ❓ Perguntas em aberto (levantem com o "cliente")
- Uma comanda passa por estados (aberta → fechada → paga)?
- O pedido guarda o preço no momento da venda ou consulta o cardápio?
- Como tratar cancelamento de um item já pedido?

## 🎚️ Complexidade sugerida
🟡 Bom para composição (Comanda ◆ Pedido) e máquina de estados da comanda.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
