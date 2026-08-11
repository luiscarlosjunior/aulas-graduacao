# 14 — Agência de viagens: reservas de pacotes

## 📞 O pedido do cliente
> "Tenho uma agência de viagens. Vendo **pacotes** (destino, datas, hotel, passeios) para os
> **clientes**, que fazem uma **reserva**. Preciso saber quantas vagas ainda tem em cada
> pacote e quem já reservou. Parcelamento e emissão de voucher a gente vê depois."

## 🧭 O que já sabemos
- Existem **pacotes**, **clientes** e **reservas**.
- Um pacote tem destino, período, preço e um número de vagas.
- Uma reserva ocupa vaga(s) de um pacote para um cliente.

## ❓ Perguntas em aberto (levantem com o "cliente")
- O que impede reservar mais vagas do que o pacote tem?
- A reserva passa por estados (pendente → confirmada → cancelada)?
- Um pacote é "feito de" itens (hotel, voo, passeio)? Composição?

## 🎚️ Complexidade sugerida
🟡 Bom para multiplicidade/limite de vagas e composição do pacote.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
