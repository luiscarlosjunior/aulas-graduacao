# 💻 Código pronto — Transportadora EntregaCerta

> Este é o sistema **Transportadora EntregaCerta** (dono de uma transportadora), **já funcionando em Java**. Ele usa o que vimos até a aula
> de SOLID. É um código **simples e comentado**: não é um sistema perfeito, é o material para o
> exercício de hoje. **Aqui não tem nenhum diagrama pronto** — quem descobre e desenha são vocês.

---

## 1. 🎯 A dinâmica de hoje

1. **Escolham** este projeto (ou outro dos 20) em grupo.
2. **Abram a pasta `codigo/`** no editor que preferirem e **rodem** o sistema (seção 3).
3. **Leiam o código** e escolham uma **classe** ou uma **funcionalidade**.
4. **Façam os 4 diagramas** da seção 5. As escolhas **não precisam ser as mesmas**: cada diagrama
   pode falar de uma parte diferente do sistema.
5. **Escrevam um pequeno resumo** de cada diagrama, contando com as palavras de vocês o que está
   acontecendo naquela parte do sistema.

> 💡 O diagrama sai **do código**: os nomes de classes, métodos e estados do desenho têm que
> existir nos arquivos `.java`.

---

## 2. O sistema em poucas palavras

- **Quem pediu:** dono de uma transportadora.
- **Peça central:** `Encomenda`, com dois tipos — `EncomendaComum` (comum) e `EncomendaExpressa` (tem prazo menor e prioridade no transporte).
- **Classe principal do negócio:** `Entrega`.
- **Ligações (descritas na entrevista):** Uma encomenda tem MUITOS eventos de rastreio; cada entrega é de UMA encomenda para UM destinatário.
- **Regra do cliente:** *não pode marcar como 'entregue' uma encomenda que nem saiu para entrega*. Ela aparece na entrevista, mas o código **ainda não verifica**
  essa regra; o sistema é simplificado.

Para conhecer melhor o negócio, leiam o [briefing do projeto](../README.md) e a
[Entrevista Técnica](../EntrevistaTecnica.md).

---

## 3. Como abrir e rodar

**Abram a pasta `codigo/` sozinha** no editor, e não o repositório inteiro. Todos os projetos têm
classes com o mesmo nome e o editor se confundiria.

No terminal, dentro de `codigo/`:

```bash
javac -encoding UTF-8 *.java
java AppTransportadoraEntregaCerta
```

No VS Code (com o *Extension Pack for Java*) ou no IntelliJ, também dá para abrir
`AppTransportadoraEntregaCerta.java` e clicar em **Run**. Precisa do **Java 17** ou mais novo.

---

## 4. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Encomenda.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `EncomendaComum.java` | tipo comum | Herança + polimorfismo |
| `EncomendaExpressa.java` | tipo especial (tem prazo menor e prioridade no transporte) | Herança + polimorfismo |
| `Entrega.java` | a transação central | Encapsulamento, invariante, pré/pós, associação com Encomenda |
| `EventoRastreio.java` | parte que nasce dentro de Encomenda | Composição ◆ |
| `Rota.java` | grupo da agregação de Encomenda | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorEntrega.java` | coordena abrir/avançar/marcarDevolvida | SRP + DIP |
| `AppTransportadoraEntregaCerta.java` | o `main`: o fluxo completo | Por onde começar a leitura |

---

## 5. Os 4 diagramas

Antes de desenhar, **revejam a aula** de cada diagrama: é lá que estão a notação e os exemplos.

| Diagrama | Pergunta que ele responde | Aula |
|----------|---------------------------|------|
| **Casos de uso** | Quem usa o sistema e para fazer o quê? | [Aula 08 — Casos de Uso](../../../03-POO/01-modelagem/analise-projeto-uml/08-diagrama-casos-de-uso/) |
| **Sequência** | Em que ordem os objetos conversam para fazer algo? | [Aula 11 — Sequência](../../../03-POO/01-modelagem/analise-projeto-uml/11-diagrama-de-sequencia/) |
| **Estados** | Por quais situações um objeto passa e o que o faz mudar? | [Aula 14 — Máquina de Estados](../../../03-POO/01-modelagem/analise-projeto-uml/14-diagrama-de-maquina-de-estados/) |
| **Atividades** | Quais passos e decisões formam um processo? | [Aula 15 — Atividades](../../../03-POO/01-modelagem/analise-projeto-uml/15-diagrama-de-atividades/) |

**Onde procurar** (são pontos de partida; a escolha é de vocês):

- **Casos de uso:** pensem em quem usaria este sistema no dia a dia e no que essa pessoa quer
  conseguir. Podem cobrir o sistema todo ou uma parte dele.
- **Sequência:** escolham um método e sigam as chamadas de uma classe para outra. O
  `AppTransportadoraEntregaCerta.java` e o `GestorEntrega.java` são bons lugares para começar.
- **Estados:** procurem uma classe com um atributo que **muda** durante a vida do objeto e vejam
  quais métodos o alteram e quando eles recusam a mudança.
- **Atividades:** escolham um processo do sistema, do começo ao fim, e procurem no código onde ele
  **decide** alguma coisa (os `if`).

---

## 6. O que entregar

Os 4 diagramas e, para cada um, este resumo:

| Diagrama | O que escolhemos (classe ou funcionalidade) | Resumo: o que está acontecendo (3 a 5 linhas) |
|----------|---------------------------------------------|-----------------------------------------------|
| Casos de uso | | |
| Sequência | | |
| Estados | | |
| Atividades | | |

**Confiram antes de entregar:**

- [ ] Os nomes usados nos diagramas existem no código.
- [ ] A notação segue a aula de cada diagrama.
- [ ] O resumo explica o que acontece no sistema, e não apenas descreve o desenho.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md)
