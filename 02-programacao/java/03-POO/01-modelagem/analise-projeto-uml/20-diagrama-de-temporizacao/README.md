# 20 — Diagrama de Temporização (Timing)

**📌 Família:** comportamental (interação) · **Responde:** *como o estado dos objetos muda em
função do tempo (com precisão temporal)?*

---

## 1. Conceito

O diagrama de **temporização** foca na **mudança de estado ao longo de uma linha de tempo
explícita e medida**. Diferente da [sequência](../11-diagrama-de-sequencia/) (que ordena
mensagens **sem** escala de tempo), aqui o eixo horizontal é o **tempo com escala**, e vemos
como cada objeto muda de estado e **quando** — inclusive **restrições de duração**. É muito
usado em sistemas de **tempo real** e embarcados.

---

## 2. Notação

- **Eixo horizontal:** o tempo (com escala).
- **Eixo vertical:** os estados possíveis de um objeto (uma "faixa" por objeto).
- **Linha de vida em degraus:** sobe/desce entre estados conforme o tempo passa.
- **Restrições de tempo:** `{0..5s}` marca durações permitidas; setas entre linhas de vida
  mostram eventos que disparam mudanças.

---

## 3. Aplicação e exemplo (Melodia — o *buffer* de reprodução)

Streaming é o caso perfeito: o *player* enche um **buffer** e alterna entre **Tocando** e
**Rebuffering** conforme a rede. O tempo aqui é **medido**, não só ordenado.

```
Estado do
 Player
         │
Tocando  ├───────────┐              ┌──────────────────────┐
         │           │              │                      │
Buffer.  ├──┐         └──────────────┘                      │
         │  │                                               │
Parado   ├──┘                                               └──────────
         └──┴────┬────┬─────────┬────┬──────────────┬──────┬──────────▶ tempo (s)
            0   2s   3s        12s  14s            60s    62s
            │    │                │                  │
         play  buffer OK      rede cai           buffer OK
                {≤ 3s p/ iniciar}  {re-buffer ≤ 2s p/ não irritar o usuário}
```

> 🧠 **Leia da esquerda para a direita:** ao dar *play*, o player fica **Parado→Bufferizando**
> e deve começar a **Tocar** em no máximo 3s (`{≤ 3s}`). Aos 12s a rede cai → volta a
> **Bufferizar**, e a meta é religar em ≤ 2s. Aqui a métrica **tempo** é a estrela — é o que
> difere este diagrama do de sequência.

---

## 4. Vantagens e desvantagens

| ✅ Vantagens | ❌ Desvantagens |
|-------------|-----------------|
| Único diagrama que trata **tempo medido** e durações | Notação trabalhosa; ferramentas o suportam mal |
| Essencial para **tempo real**/hardware/protocolos | Inútil para a maioria dos sistemas de negócio |
| Torna explícitas **restrições temporais** (SLAs, timeouts) | Difícil de manter e de ler para não-especialistas |

---

## 5. Na indústria (como sim, como não)

- ✅ **Onde é valioso:** sistemas **embarcados e de tempo real** (automotivo, aeroespacial,
  telecom), **protocolos de comunicação** (handshakes, timeouts) e análise de **SLAs/latência**
  (ex.: "o buffer inicial deve ser < 3s"). Aqui, errar o tempo é errar o sistema.
- ❌ **Onde quase nunca aparece:** CRUDs, sistemas web de negócio, apps corporativos. Você pode
  passar anos sem desenhar um.
- 💡 **Dica de professor:** é o diagrama **mais nichado** da UML. Saiba **para que serve**
  (tempo medido / restrições temporais) e reconheça quando um problema é "de timing". Para a
  maioria dos sistemas, um diagrama de **estados** já basta.

---

## ✅ O que levar desta pasta

- [ ] Temporização = **estado × tempo medido**, com **restrições de duração**.
- [ ] Difere da sequência: aqui o **tempo tem escala** e importa quanto dura cada estado.
- [ ] Brilha em **tempo real, embarcados, protocolos e SLAs**.
- [ ] É o diagrama **mais especializado** — conheça o conceito, use com parcimônia.

---

## 🎓 Você terminou a Parte II — e o curso!

Você percorreu **os 13 diagramas da UML** e os fundamentos de OOAD, sempre sobre o mesmo
sistema **Melodia**. Agora feche o ciclo:

1. Reabra o **[projeto-base-java](../projeto-base-java/)**, compile e rode.
2. Para **cada diagrama**, localize a classe/método correspondente no código.
3. Faça os **exercícios** propostos no [índice do curso](../README.md) com um domínio novo.

> 🔗 **A grande lição:** modelo (UML) e código (Java) contam **a mesma história** em idiomas
> diferentes. O engenheiro sênior transita entre os dois sem esforço — e sabe **quando** cada
> um vale a pena.

---

[⬅️ 19 - Implantação](../19-diagrama-de-implantacao/) | [🏠 Índice do curso](../README.md) | [☕ Projeto Java ➡️](../projeto-base-java/)
