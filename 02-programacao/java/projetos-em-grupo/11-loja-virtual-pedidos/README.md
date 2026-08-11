# 11 — Loja virtual: pedidos

## 📞 O pedido do cliente
> "Vou montar uma lojinha online. O cliente coloca **produtos no carrinho**, fecha o
> **pedido** e paga. Preciso acompanhar o pedido: foi pago? foi enviado? chegou? Frete e
> cupom de desconto entram depois, primeiro quero o básico funcionando."

## 🧭 O que já sabemos
- Existem **clientes**, **produtos**, **carrinho** e **pedidos**.
- Um pedido tem vários itens e um total.
- O pedido muda de situação ao longo do tempo.

## ❓ Perguntas em aberto (levantem com o "cliente")
- Quais são os estados do pedido? Pode ser cancelado em qual fase?
- O item do pedido guarda o preço no momento da compra?
- Existe cliente comum e cliente VIP com regras diferentes?

## 🎚️ Complexidade sugerida
🟡🔴 Projeto rico: composição (Pedido ◆ Item), estados e polimorfismo (tipos de cliente).

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
