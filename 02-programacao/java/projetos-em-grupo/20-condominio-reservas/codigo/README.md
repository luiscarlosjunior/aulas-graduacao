# 💻 Código pronto — Condomínio BemMorar

> Este é o sistema **Condomínio BemMorar** (síndico de um condomínio), **já funcionando em Java**. Ele usa o que vimos até a aula
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

- **Quem pediu:** síndico de um condomínio.
- **Peça central:** `Morador`, com dois tipos — `MoradorAdimplente` (comum) e `MoradorInadimplente` (fica bloqueado de reservar até quitar).
- **Classe principal do negócio:** `Reserva`.
- **Ligações (descritas na entrevista):** Um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns.
- **Regra do cliente:** *não pode reservar uma área comum já reservada no mesmo período*. Ela aparece na entrevista, mas o código **ainda não verifica**
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
java AppCondominioBemMorar
```

No VS Code (com o *Extension Pack for Java*) ou no IntelliJ, também dá para abrir
`AppCondominioBemMorar.java` e clicar em **Run**. Precisa do **Java 17** ou mais novo.

---

## 4. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Morador.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `MoradorAdimplente.java` | tipo comum | Herança + polimorfismo |
| `MoradorInadimplente.java` | tipo especial (fica bloqueado de reservar até quitar) | Herança + polimorfismo |
| `Reserva.java` | a transação central | Encapsulamento, invariante, pré/pós, associação com Morador |
| `Cobranca.java` | parte que nasce dentro de Reserva | Composição ◆ |
| `Bloco.java` | grupo da agregação de Unidade | Agregação ◇ |
| `Unidade.java` | item que é agrupado por Bloco | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorReserva.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppCondominioBemMorar.java` | o `main`: o fluxo completo | Por onde começar a leitura |

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
  `AppCondominioBemMorar.java` e o `GestorReserva.java` são bons lugares para começar.
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
