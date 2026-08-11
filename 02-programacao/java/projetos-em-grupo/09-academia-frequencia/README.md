# 09 — Academia: planos e frequência

## 📞 O pedido do cliente
> "Tenho uma academia. Cada **aluno** assina um **plano** (mensal, trimestral…) e faz
> **check-in** quando vem treinar. Quero saber quem está com o plano **em dia** e a
> frequência de cada um. Bloquear a catraca de quem está devendo seria o sonho, mas depois."

## 🧭 O que já sabemos
- Existem **alunos**, **planos** e **check-ins** (frequência).
- Um plano tem duração e valor.
- Um aluno tem um plano ativo e um histórico de visitas.

## ❓ Perguntas em aberto (levantem com o "cliente")
- O plano tem estados (ativo → vencido → cancelado)? Como isso libera/bloqueia o check-in?
- A frequência é um contador ou uma lista de datas?
- Diferentes planos dão acesso a coisas diferentes?

## 🎚️ Complexidade sugerida
🟡 Ótimo para máquina de estados do plano/assinatura (parecido com o Melodia).

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
