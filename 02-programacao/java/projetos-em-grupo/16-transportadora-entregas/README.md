# 16 — Transportadora: entregas e rastreamento

## 📞 O pedido do cliente
> "Tenho uma transportadora. Recebo **encomendas** para levar de um lugar a outro e o cliente
> vive perguntando 'onde está minha entrega?'. Queria registrar cada **etapa** do trajeto
> (coletado, em trânsito, entregue). Motorista e rota otimizada ficam pra depois."

## 🧭 O que já sabemos
- Existem **encomendas**, **remetentes/destinatários** e **entregas**.
- Cada encomenda tem origem, destino e um código de rastreio.
- A entrega passa por etapas até ser concluída.

## ❓ Perguntas em aberto (levantem com o "cliente")
- Quais são as etapas (estados) do rastreamento?
- O histórico de rastreio é uma lista de eventos com data/hora?
- Como tratar uma entrega que falha (destinatário ausente)?

## 🎚️ Complexidade sugerida
🟡 Ótimo para máquina de estados e histórico de eventos (composição).

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
