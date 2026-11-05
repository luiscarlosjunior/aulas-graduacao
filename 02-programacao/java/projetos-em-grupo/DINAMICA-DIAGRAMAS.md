# 🧭 Dinâmica da aula — do código aos diagramas

> **Guia do professor.** Roteiro sugerido para a atividade em grupo com a pasta `codigo/` dos 20
> projetos. Os tempos consideram uma aula de **100 minutos**; ajuste à duração real da sua aula.

## A ideia em uma frase

Os alunos recebem um sistema **que já funciona** e fazem o caminho inverso do que vinham fazendo:
em vez de modelar para depois programar, eles **leem o código e extraem dele os diagramas**.

## O que os alunos praticam

- **Ler código alheio** e entender o que ele faz.
- **Escolher o diagrama certo** para cada parte do sistema.
- **Aplicar a notação** de casos de uso, sequência, estados e atividades.
- **Explicar com as próprias palavras** o que está acontecendo no sistema.

---

## Antes da aula

- [ ] Definir o tamanho dos grupos (sugestão: 3 ou 4 alunos).
- [ ] Decidir se dois grupos podem pegar o mesmo projeto.
- [ ] Confirmar que os computadores têm **Java 17 ou mais novo** e um editor.
- [ ] Decidir como será a entrega (draw.io exportado em PDF, por exemplo) e o prazo.
- [ ] Rodar um projeto na máquina da sala, para ter um exemplo pronto para mostrar.
- [ ] Decidir se os alunos terão acesso a `resposta/diagramas-atividade.md` (gabarito do diagrama
      de classes dos 20 projetos).

---

## Roteiro da aula

| Tempo | Etapa | O que acontece |
|-------|-------|----------------|
| 10 min | Abertura | Explicar a atividade e mostrar um projeto rodando |
| 10 min | Escolha e preparação | Grupos escolhem o projeto, abrem `codigo/` e rodam |
| 15 min | Leitura do código | Grupos entendem o sistema e fazem as escolhas |
| 45 min | Diagramas e resumos | Grupos desenham os 4 diagramas e escrevem os resumos |
| 15 min | Apresentação | Alguns grupos mostram um diagrama para a turma |
| 5 min | Fechamento | Retomar a pergunta que cada diagrama responde |

### 1. Abertura (10 min)

Explique o combinado:

1. Cada grupo escolhe um projeto.
2. Abre a pasta `codigo/` no editor e roda o sistema.
3. Escolhe uma classe ou funcionalidade para cada diagrama. **As escolhas podem ser diferentes.**
4. Faz os 4 diagramas.
5. Escreve um resumo de 3 a 5 linhas para cada um.

Vale reforçar três avisos:

- O código é **a fonte da verdade**: os nomes do diagrama precisam existir nos arquivos `.java`.
- O código é **simplificado**. Algumas regras citadas na entrevista não estão implementadas.
- Abram **só a pasta `codigo/`**, não o repositório inteiro. Os projetos têm classes com o mesmo
  nome e o editor se confunde.

### 2. Escolha e preparação (10 min)

Dentro de `codigo/`, cada projeto roda com dois comandos. O nome da classe `App...` muda por
projeto e está no README da pasta:

```bash
javac -encoding UTF-8 *.java
java AppNomeDoSistema
```

Só avance quando **todos os grupos tiverem o sistema rodando**. Se um computador der problema, o
grupo pode rodar em outra máquina e seguir lendo o código.

### 3. Leitura do código (15 min)

Oriente os grupos a começar pelo arquivo `App...java`, comparando o código com o que aparece na
saída. Depois disso, cada grupo decide o que vai desenhar em cada diagrama.

Uma boa divisão: cada integrante fica responsável por um diagrama, e o grupo revisa tudo junto no
final. Assim todos praticam, e a revisão faz o grupo discutir a notação.

### 4. Diagramas e resumos (45 min)

Circule pelos grupos. Quando alguém pedir a resposta, devolva uma pergunta:

| Se o grupo está… | Pergunte |
|------------------|----------|
| Sem saber o que escolher | "Qual parte vocês conseguem explicar melhor?" |
| Travado nos casos de uso | "Quem usaria isso no dia a dia? O que essa pessoa quer conseguir?" |
| Confundindo caso de uso com método | "Isso é um objetivo da pessoa ou um detalhe do código?" |
| Travado na sequência | "Quem chama este método? E o que ele chama depois?" |
| Travado nos estados | "Qual atributo muda? Que método muda esse atributo? Quando ele recusa?" |
| Travado nas atividades | "Onde esse processo começa e termina? Onde ele toma uma decisão?" |
| Confundindo estados com atividades | "Vocês estão olhando para um objeto ou para um processo?" |
| Com um diagrama que não bate com o código | "Mostrem no código onde isso acontece." |

Se um grupo tiver dúvida de notação, mande para a aula do diagrama em vez de responder direto:

- [Aula 08 — Casos de Uso](../03-POO/01-modelagem/analise-projeto-uml/08-diagrama-casos-de-uso/)
- [Aula 11 — Sequência](../03-POO/01-modelagem/analise-projeto-uml/11-diagrama-de-sequencia/)
- [Aula 14 — Máquina de Estados](../03-POO/01-modelagem/analise-projeto-uml/14-diagrama-de-maquina-de-estados/)
- [Aula 15 — Atividades](../03-POO/01-modelagem/analise-projeto-uml/15-diagrama-de-atividades/)

### 5. Apresentação (15 min)

Escolha de 3 a 4 grupos, de preferência um por tipo de diagrama, para mostrar **um** diagrama em
até 3 minutos. O grupo lê o resumo, mostra o desenho e aponta no código de onde ele saiu.

Peça para a turma avaliar: "Falta alguma coisa? O nome bate com o código? Esse era o melhor
diagrama para mostrar essa parte?"

### 6. Fechamento (5 min)

Retome a pergunta que cada diagrama responde:

| Diagrama | Pergunta |
|----------|----------|
| Casos de uso | Quem usa o sistema e para fazer o quê? |
| Sequência | Em que ordem os objetos conversam para fazer algo? |
| Estados | Por quais situações um objeto passa e o que o faz mudar? |
| Atividades | Quais passos e decisões formam um processo? |

A mensagem final: **cada diagrama é um ponto de vista diferente sobre o mesmo sistema**.

---

## Sugestão de avaliação

Cada diagrama pode ser avaliado com estes critérios:

| Critério | O que observar | Peso sugerido |
|----------|----------------|---------------|
| Fidelidade ao código | Nomes e comportamento batem com os arquivos `.java` | 30% |
| Notação | Elementos usados como na aula | 30% |
| Escolha | O diagrama combina com a parte escolhida | 15% |
| Resumo | Explica o que acontece no sistema, não só descreve o desenho | 25% |

Não existe uma resposta única: como cada grupo escolhe o que desenhar, avalie se o diagrama é
**coerente com a escolha e com o código**.

---

## Se algo sair do previsto

- **`javac` não encontrado:** o Java não está instalado ou não está no PATH. Use outra máquina.
- **Editor mostra erro de classe duplicada:** o grupo abriu o repositório inteiro. Abra só `codigo/`.
- **Acentos estranhos na saída:** costuma ser a configuração do terminal, e não atrapalha a atividade.
- **Grupo terminou antes:** peça para fazer um segundo diagrama de sequência mostrando um caminho
  que dá errado, ou para implementar a regra do cliente que o código ainda não verifica.
- **Faltou tempo:** deixe atividades ou casos de uso para casa e mantenha a apresentação.

---

[📂 Banco de projetos](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
