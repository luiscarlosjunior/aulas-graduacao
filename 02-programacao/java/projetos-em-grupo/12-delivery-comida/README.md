# 12 — Delivery de comida

## 📞 O pedido do cliente
> "Quero um app tipo delivery. O cliente escolhe de um **restaurante**, monta o **pedido** e
> um **entregador** leva. Preciso saber onde o pedido está: preparando, saiu pra entrega,
> entregue. Avaliação e cálculo de rota vêm bem depois."

## 🧭 O que já sabemos
- Existem **clientes**, **restaurantes**, **pedidos** e **entregadores**.
- Um pedido pertence a um cliente e a um restaurante.
- O pedido passa por várias etapas até ser entregue.

## ❓ Perguntas em aberto (levantem com o "cliente")
- Quais são exatamente os estados do pedido/entrega?
- O entregador é associado ao pedido em que momento?
- Como calcular o total (itens + taxa de entrega)?

## 🎚️ Complexidade sugerida
🟡🔴 Bom para máquina de estados e várias associações (cliente, restaurante, entregador).

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
