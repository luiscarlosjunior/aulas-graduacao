# 15 — Cinema: sessões e ingressos

## 📞 O pedido do cliente
> "Tenho um cineminha com algumas salas. Passo **filmes** em **sessões** (horários) e vendo
> **ingressos** por assento. Preciso não vender o mesmo assento duas vezes na mesma sessão.
> Meia-entrada e combo de pipoca vêm depois, primeiro a venda do ingresso."

## 🧭 O que já sabemos
- Existem **filmes**, **salas**, **sessões**, **assentos** e **ingressos**.
- Uma sessão é um filme numa sala, num horário.
- Um ingresso reserva um assento de uma sessão.

## ❓ Perguntas em aberto (levantem com o "cliente")
- Como garantir que um assento não seja vendido duas vezes na mesma sessão?
- O assento tem estado (livre → reservado → vendido)?
- Existem tipos de ingresso com preços diferentes (inteira, meia)?

## 🎚️ Complexidade sugerida
🟡 Bom para associações (sessão-assento-ingresso) e regra de unicidade.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
