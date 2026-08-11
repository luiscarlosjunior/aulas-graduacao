# 03 — Clínica: agendamento de consultas

## 📞 O pedido do cliente
> "Tenho uma clínica com vários médicos. O telefone não para: gente ligando pra **marcar
> consulta**. Eu queria ver a **agenda de cada médico**, quem já marcou e em que horário, pra
> não marcar dois no mesmo horário. Encaixe e remarcação acontecem direto."

## 🧭 O que já sabemos
- Existem **médicos**, **pacientes** e **consultas** (agendamentos).
- Cada médico tem uma especialidade e horários de atendimento.
- Uma consulta tem data, hora, médico e paciente.

## ❓ Perguntas em aberto (levantem com o "cliente")
- O que impede marcar dois pacientes no mesmo horário do mesmo médico?
- Uma consulta pode ser cancelada/remarcada? O que muda no "estado" dela?
- Precisa registrar o resultado/prontuário da consulta?

## 🎚️ Complexidade sugerida
🟡 Bom para exercitar máquina de estados da consulta (marcada → realizada → cancelada).

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
