# 19 — Salão de beleza: agendamentos

## 📞 O pedido do cliente
> "Tenho um salão com vários **profissionais** (cabeleireiro, manicure…). As **clientes**
> marcam **serviços** por horário com um profissional específico. Vivo com conflito de
> horário na agenda. Comissão do profissional a gente calcula depois, primeiro a agenda."

## 🧭 O que já sabemos
- Existem **clientes**, **profissionais**, **serviços** e **agendamentos**.
- Cada profissional faz certos serviços.
- Um agendamento tem data, hora, cliente, profissional e serviço.

## ❓ Perguntas em aberto (levantem com o "cliente")
- Como impedir dois agendamentos no mesmo horário do mesmo profissional?
- Um agendamento pode ter vários serviços seguidos?
- O agendamento tem estados (marcado → atendido → faltou/cancelado)?

## 🎚️ Complexidade sugerida
🟢🟡 MVP simples; cresce com comissão, pacotes e histórico da cliente.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
