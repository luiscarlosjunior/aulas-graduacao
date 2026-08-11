# ☕ Desafios em Java — o "dia de Java"

> Estes três desafios são o **par prático** das três primeiras aulas de modelagem. A ideia do
> curso é alternar: um dia você **modela** o conceito (no papel/UML), no outro você **sente na
> pele** o mesmo conceito **codando** em cima do projeto **Melodia**.
>
> | Aula de modelagem | Desafio em Java (aqui) |
> |-------------------|------------------------|
> | [01 - Histórico](../01-historico-metodologias/) | [Desafio 1 — Do procedural ao OO](#desafio-1--do-procedural-ao-oo-o-motivo-histórico-da-oo) |
> | [02 - Conceitos de OO](../02-conceitos-orientacao-objetos/) | [Desafio 2 — O Podcast](#desafio-2--conceitos-de-oo-em-ação-o-podcast) |
> | [03 - Abstração](../03-abstracao/) | [Desafio 3 — A FonteDeAudio](#desafio-3--abstração-a-fontedeaudio) |

> ⚠️ **Regra do jogo:** aqui **não tem gabarito**. Os desafios dão o *cenário*, as
> *assinaturas* e o *critério de pronto* — o código é você quem cria. Compile sempre com:
> ```bash
> javac -d out $(find src -name "*.java") && java -cp out com.melodia.Principal
> ```

---

## 💼 Exemplos do dia a dia (em Java)

Antes dos desafios, veja **em código** as dores que as aulas descrevem — são cenas reais de
empresa.

### a) Procedural × OO (o que a história conta, aula 01)

Como um time dos anos 2000 escreveria o cálculo da receita (estilo **procedural**: dados de
um lado, funções do outro):

```java
// ❌ Estilo procedural: "struct" burra + função solta que mexe nos dados dela.
class DadosAssinatura {           // só dados, público, sem proteção
    String plano;
    double preco;
    boolean ativa;
}
class CalculadoraReceita {        // função solta, longe dos dados
    static double receita(DadosAssinatura[] xs) {
        double t = 0;
        for (DadosAssinatura x : xs)
            if (x.ativa && x.preco > 0) t += x.preco;  // regra espalhada e sem dono
        return t;
    }
}
```

O problema: a regra "assinatura ativa e paga conta como receita" mora **fora** do objeto.
Qualquer um pode escrever `d.ativa = true` sem passar por regra nenhuma. É *exatamente* a dor
que a OO veio curar (aula 01, seção 3.4). No estilo **OO** do nosso projeto, a `Assinatura`
é dona do próprio estado e ninguém mexe nele por fora.

### b) O bug do dado exposto (encapsulamento, aula 02)

```java
// ❌ Se 'saldo' fosse público, este estorno deixaria a conta NEGATIVA sem ninguém perceber:
conta.saldo = conta.saldo - valor;      // não valida nada!

// ✅ Como o projeto realmente faz: o objeto protege o próprio invariante.
conta.sacar(valor);                     // lança exceção se faltar saldo (R1)
```

Veja `banco/ContaBancaria.java`: `saldo` é `private` e só muda por métodos que **validam**.

### c) A boa abstração (aula 03)

```java
// ✅ O resto do sistema depende do CONTRATO, não do fornecedor concreto:
interface MeioDePagamento { boolean cobrar(double valor); }
// Trocar de gateway = escrever uma classe nova, sem tocar em quem chama 'cobrar'.
```

---

## Desafio 1 — Do procedural ao OO (o motivo histórico da OO)

🟢 **Nível:** aquecimento · **Conecta com:** [aula 01 - Histórico](../01-historico-metodologias/)

**Cenário (dia a dia):** você "herdou" o código procedural da seção **a)** acima, num sistema
legado. Seu tech lead pediu para **refatorá-lo para OO** antes de adicionar qualquer coisa
nova — porque toda mudança nele hoje quebra três outras.

**O que fazer:**
1. Elimine a struct `DadosAssinatura` e a `CalculadoraReceita`. Use a classe **`Assinatura`**
   que já existe no projeto (`assinatura/Assinatura.java`).
2. Crie um objeto que seja **dono** do cálculo — por exemplo, um método
   `double receitaMensal()` na `PlataformaStreaming`, que percorre os ouvintes e soma o preço
   das assinaturas **ativas e pagas** (reaproveitando `assinatura.estaAtiva()` e
   `plano.isPago()`).
3. No `Principal.java`, imprima a receita mensal da plataforma depois que Ana e Lucas assinam.

**Critério de "pronto":**
- [ ] Não sobrou nenhum campo público de dados nem função "solta" mexendo neles.
- [ ] A regra de receita usa métodos dos objetos (não `if` com string mágica).
- [ ] Compila e o `Principal` mostra a receita correta.

> 💡 **Dica:** compare o "antes" (procedural) e o "depois" (OO) e escreva 2 linhas de
> comentário sobre **o que ficou mais fácil de mudar**. Essa reflexão é metade do aprendizado.

---

## Desafio 2 — Conceitos de OO em ação (o Podcast)

🟡 **Nível:** intermediário · **Conecta com:** [aula 02 - Conceitos de OO](../02-conceitos-orientacao-objetos/)

**Cenário (dia a dia):** o produto decidiu que o Melodia terá **podcasts**. Você recebeu a
*user story*: *"como ouvinte, quero ouvir episódios de podcast para além de música"*. É o seu
modelo da aula 02 virando código.

**O que fazer:**
1. No pacote `catalogo`, crie **`Podcast`** e, se quiser, **`Episodio`**. Sugestão de contrato:
   ```java
   public class Podcast {
       public Podcast(String titulo, String apresentador) { /* ... */ }
       public Episodio adicionarEpisodio(String titulo, int duracaoSegundos) { /* ... */ }
       // getters...
   }
   ```
   Faça-a **rica** (com comportamento), não anêmica — ex.: `duracaoTotalSegundos()`,
   `quantidadeEpisodios()`.
2. Garanta **encapsulamento**: nada de listas mutáveis vazando (devolva
   `Collections.unmodifiableList(...)`, como o resto do projeto faz).
3. Permita que uma **`Playlist`** receba um episódio de podcast **ou** ajuste seu cenário para
   reproduzir um episódio pela `PlataformaStreaming`, creditando royalty ao apresentador
   (reaproveite a lógica de `Artista.creditarRoyalty`).
4. No `Principal`, publique um podcast, reproduza um episódio e mostre no extrato/estado que
   funcionou.

**Critério de "pronto":**
- [ ] `Podcast`/`Episodio` têm **atributos + operações** (nenhuma classe anêmica).
- [ ] Você consegue apontar no seu código **onde** aparece cada pilar (nem que seja num
      comentário `// polimorfismo aqui`).
- [ ] Compila e roda; o cenário do `Principal` prova que o podcast toca.

> 💡 **Dica:** este desafio **prepara** o Desafio 3. Se você já sentir vontade de dizer
> "música e podcast são a mesma coisa reproduzível", segure essa ideia — ela é a próxima.

---

## Desafio 3 — Abstração (a FonteDeAudio)

🔴 **Nível:** integrador · **Conecta com:** [aula 03 - Abstração](../03-abstracao/)

**Cenário (dia a dia):** agora existem `Musica` e `Podcast`/`Episodio`, e o código começou a
ficar cheio de `if (é música) … else (é podcast) …`. É o sinal clássico de que falta uma
**abstração**. Seu objetivo é fazer a `Playlist` e a reprodução tratarem os dois de forma
**polimórfica** — sem saber qual é qual.

**O que fazer:**
1. Crie a interface (ou classe abstrata) **`FonteDeAudio`** com o mínimo que **todo** áudio
   reproduzível precisa expor:
   ```java
   public interface FonteDeAudio {
       String getTitulo();
       int getDuracaoSegundos();
       void registrarReproducao();
   }
   ```
2. Faça `Musica` **e** `Episodio` (ou `Podcast`) **implementarem** `FonteDeAudio`.
3. Altere `Playlist` para guardar `List<FonteDeAudio>` em vez de `List<Musica>` — assim ela
   aceita os dois **sem mudar mais nada**. Note que `duracaoTotalSegundos()` continua
   funcionando via polimorfismo.
4. Ajuste `PlataformaStreaming.reproduzir(...)` para receber `FonteDeAudio`.
5. No `Principal`, monte **uma** playlist misturando música e podcast e some a duração total.

**Critério de "pronto":**
- [ ] A `Playlist` mistura música e podcast e **não tem nenhum `if` perguntando o tipo**.
- [ ] Sumiram os `instanceof`/`cast` — quem decide o comportamento é o objeto (polimorfismo).
- [ ] Você consegue nomear **uma** mudança futura que ficou barata (ex.: adicionar
      "audiolivro" = só criar `Audiolivro implements FonteDeAudio`) — e **uma** coisa que você
      **deixou de fora** da interface de propósito.

> 💡 **Dica de professor:** se adicionar um terceiro tipo de áudio exigir mexer em `Playlist`
> ou em `reproduzir`, sua abstração vazou. Uma boa `FonteDeAudio` faz o novo tipo "só
> encaixar". Esse é o teste final da aula 03.

---

## 🏁 Fechamento

Ao terminar os três, você percorreu a mesma trilha das aulas 01→02→03, só que **do lado do
código**: sentiu a dor do procedural, deu **responsabilidade** aos objetos e criou uma
**abstração** que absorve mudança. Volte às aulas de modelagem e confirme: o seu código conta
a **mesma história** dos seus diagramas?

[⬅️ Voltar ao projeto Java](README.md) | [🏠 Índice do curso](../README.md)
