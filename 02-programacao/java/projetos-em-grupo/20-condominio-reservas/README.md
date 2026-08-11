# 20 — Condomínio: moradores e reservas de áreas comuns

## 📞 O pedido do cliente
> "Sou síndico de um condomínio. Queria um sistema pros **moradores** reservarem as **áreas
> comuns** (salão de festas, churrasqueira, quadra) sem bagunça — hoje é grupo de WhatsApp e
> vira confusão. Preciso ver o que já está reservado e por quem. Cobrança de taxa depois."

## 🧭 O que já sabemos
- Existem **moradores**, **unidades (apartamentos)** e **áreas comuns**.
- Uma **reserva** liga um morador a uma área comum, numa data/horário.
- Uma área comum só pode ter uma reserva por período.

## ❓ Perguntas em aberto (levantem com o "cliente")
- O que impede duas reservas da mesma área no mesmo horário?
- A reserva tem estados (solicitada → confirmada → cancelada)?
- Morador está ligado a uma unidade? Uma unidade pode ter vários moradores?

## 🎚️ Complexidade sugerida
🟡 Bom para regra de disponibilidade, associações e máquina de estados da reserva.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
