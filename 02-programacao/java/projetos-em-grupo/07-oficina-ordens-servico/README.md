# 07 — Oficina mecânica: ordens de serviço

## 📞 O pedido do cliente
> "Tenho uma oficina. O carro chega, abro uma **ordem de serviço**, anoto o que vai ser feito
> (troca de óleo, freio…) e as **peças** usadas. No fim somo mão de obra + peças e passo o
> valor pro cliente. Orçamento aprovado ou não também importa, mas depois."

## 🧭 O que já sabemos
- Existem **clientes**, **veículos**, **ordens de serviço**, **serviços** e **peças**.
- Uma OS pertence a um veículo/cliente e lista serviços e peças.
- O valor final soma mão de obra e peças.

## ❓ Perguntas em aberto (levantem com o "cliente")
- A OS tem estados (aberta → em execução → concluída → paga)?
- Peça e serviço são a mesma coisa ou entidades diferentes?
- Como registrar aprovação do orçamento antes de executar?

## 🎚️ Complexidade sugerida
🟡 Bom para máquina de estados da OS e composição de itens.

---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
