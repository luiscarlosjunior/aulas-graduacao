# 10 — Estacionamento: vagas e tickets

## 📞 O pedido do cliente
> "Administro um estacionamento. O carro entra, tiro um **ticket** com a hora; na saída,
> calculo quanto ele ficou e cobro. Preciso saber **quantas vagas estão livres** a qualquer
> momento. Mensalistas eu tenho também, mas isso vemos depois."

## 🧭 O que já sabemos
- Existem **vagas**, **veículos** e **tickets** (entradas/saídas).
- Um ticket registra a hora de entrada e, depois, a de saída.
- O valor depende do tempo de permanência.

## ❓ Perguntas em aberto (levantem com o "cliente")
- A vaga passa por estados (livre → ocupada → livre)?
- Como calcular o valor (por hora, fração, diária)?
- Mensalista e avulso se comportam igual?

## 🎚️ Complexidade sugerida
🟢🟡 MVP simples; ótimo para máquina de estados da vaga.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
