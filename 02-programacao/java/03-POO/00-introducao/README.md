# 00 — Introdução à POO: aprendendo a *pensar em objetos*

> **Meta deste módulo:** você não vai decorar definições. Vai entender **por que** a
> Orientação a Objetos existe e **como** um programador OO enxerga um problema.
> Sem parede de estatística, sem história decorada — só a ideia central e a prática.

---

## 1. O problema que a POO resolve

Imagine um sistema de banco escrito do jeito "antigo" (programação **procedural**):
os **dados** ficam de um lado e as **funções** que mexem neles ficam do outro.

```java
// Dados soltos
double saldoDaContaDoJoao = 1000.0;

// Função solta, longe dos dados
void sacar(double valor) {
    saldoDaContaDoJoao -= valor;   // e se valor for maior que o saldo? ninguém protege
}
```

Qual o problema disso?

- **Qualquer parte do programa pode mexer no `saldo`** direto, sem regra nenhuma.
- A regra "não pode sacar mais do que tem" mora *fora* do dado. Se você esquecer de
  chamá-la, o saldo fica negativo.
- Com 2 contas até dá. Com 2 **milhões** de contas e 300 funções, vira o caos.

A ideia da POO é simples e poderosa:

> 🧩 **Junte o dado com as regras que cuidam dele numa única "caixa".**
> Essa caixa é o **objeto**.

```java
class Conta {
    private double saldo;              // o dado fica GUARDADO dentro

    void sacar(double valor) {         // a regra mora JUNTO do dado
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }
}
```

Agora é **impossível** mexer no saldo sem passar pela regra. O dado e o comportamento
viajam juntos. Esse é o coração de tudo.

---

## 2. Objeto = Estado + Comportamento

Todo objeto tem duas coisas:

| Parte | O que é | Em Java | Exemplo (um carro) |
|-------|---------|---------|--------------------|
| **Estado** | O que o objeto *sabe/tem* num dado momento | atributos (variáveis) | cor, velocidade atual, nível de combustível |
| **Comportamento** | O que o objeto *faz* | métodos (funções) | acelerar(), frear(), abastecer() |

**Analogia:** pense num controle remoto de TV.
- O **estado** é o canal e o volume em que ele está agora.
- O **comportamento** são os botões: `aumentarVolume()`, `trocarCanal()`.
- Você aperta o botão (chama o método) e **não precisa saber** como funciona por dentro.

---

## 3. Classe × Objeto — a confusão nº 1 de todo iniciante

Esta é a distinção mais importante do módulo. Leia devagar:

> 🏗️ A **classe** é a *planta da casa*. O **objeto** é a *casa construída*.

Com **uma** planta (classe) você constrói **muitas** casas (objetos), cada uma com
características próprias (uma azul, outra amarela), mas todas seguindo a mesma planta.

```java
// A CLASSE: a planta. Existe uma vez. Não "ocupa" nada ainda.
class Cachorro {
    String nome;
    String raca;

    void latir() {
        System.out.println(nome + ": Au au!");
    }
}

// Os OBJETOS: casas construídas a partir da planta. Cada 'new' cria um novo.
Cachorro rex = new Cachorro();      // objeto 1
rex.nome = "Rex";
rex.raca = "Labrador";

Cachorro bella = new Cachorro();    // objeto 2, independente do Rex
bella.nome = "Bella";
bella.raca = "Poodle";

rex.latir();    // Rex: Au au!
bella.latir();  // Bella: Au au!
```

| Classe | Objeto |
|--------|--------|
| Escrita **uma vez** no código | Criado **quantas vezes** você quiser (com `new`) |
| É um **conceito/molde** | É uma **coisa concreta** na memória |
| `class Cachorro { ... }` | `new Cachorro()` |
| Receita do bolo | O bolo assado |

---

## 4. Os 4 pilares (visão de mapa)

A POO se apoia em quatro ideias. Aqui é só o **mapa** — cada uma tem um módulo próprio
com exemplos e exercícios. Guarde a analogia de cada uma:

| Pilar | Pergunta que responde | Analogia | Módulo |
|-------|-----------------------|----------|--------|
| **Encapsulamento** | "Como protejo os dados de uso errado?" | Cápsula de remédio: protege o conteúdo | [03](../03-encapsulamento/) |
| **Herança** | "Como reaproveito o que já escrevi?" | Família: o filho herda traços do pai | [04](../04-heranca/) |
| **Polimorfismo** | "Como o mesmo comando faz coisas diferentes?" | Botão 'play': toca música, vídeo ou podcast | [05](../05-polimorfismo/) |
| **Abstração** | "Como escondo a complexidade e mostro só o essencial?" | Volante do carro: você dirige sem conhecer o motor | [06](../06-abstracao/) |

> Não tente dominar os quatro agora. Só entenda que eles existem para tornar o código
> **seguro** (encapsulamento), **reaproveitável** (herança), **flexível**
> (polimorfismo) e **simples de usar** (abstração).

---

## 5. Como um programador OO *pensa* (o clique mental)

Programação procedural pergunta: **"Quais passos o programa executa?"**
Programação orientada a objetos pergunta: **"Quais coisas existem no meu problema e o
que cada uma faz?"**

Exemplo — um sistema de pedidos de pizzaria. Em vez de pensar num roteiro gigante,
você identifica os **objetos** e distribui as responsabilidades:

- Um **Cliente** que *tem* nome e endereço e *faz* pedidos.
- Uma **Pizza** que *tem* sabor e tamanho e *sabe* calcular seu preço.
- Um **Pedido** que *junta* várias pizzas e *sabe* somar o total.

Cada objeto cuida do **seu próprio pedaço**. O programa vira uma conversa entre objetos:
`pedido.adicionar(pizza)`, `pedido.calcularTotal()`, `cliente.receber(pedido)`.

👉 **Aprender a fazer esse recorte — do problema para os objetos — é o assunto do
próximo módulo, [01 - Modelagem](../01-modelagem/), que é o coração deste curso.**

---

## 6. Quando (e quando NÃO) usar POO

POO é ótima, mas não é bala de prata. Um bom profissional sabe a hora de usar.

**✅ Use POO quando:**
- O sistema tem várias "coisas" que se relacionam (clientes, produtos, pedidos...).
- O código vai crescer e ser mantido por muito tempo / por várias pessoas.
- Proteger a integridade dos dados importa.

**⚠️ Talvez não precise quando:**
- É um script de 20 linhas que roda uma vez e morre.
- É um cálculo matemático puro, sem "entidades".

---

## 7. Mão na massa

Copie o exemplo abaixo para um arquivo `PrimeiroObjeto.java`, compile e rode.
Ele reúne tudo do módulo: classe, objeto, estado e comportamento.

```java
public class PrimeiroObjeto {
    public static void main(String[] args) {
        // Criando dois objetos a partir da MESMA classe
        Xicara minha = new Xicara("Café", 200);
        Xicara sua   = new Xicara("Chá", 150);

        minha.beberUmGole();   // Café: agora restam 170 ml
        minha.beberUmGole();   // Café: agora restam 140 ml
        sua.beberUmGole();     // Chá: agora restam 120 ml (independente da outra!)
    }
}

class Xicara {
    // ESTADO
    private String conteudo;
    private int quantidadeMl;

    // Construtor: como nasce um objeto Xicara
    public Xicara(String conteudo, int quantidadeMl) {
        this.conteudo = conteudo;
        this.quantidadeMl = quantidadeMl;
    }

    // COMPORTAMENTO
    public void beberUmGole() {
        if (quantidadeMl >= 30) {
            quantidadeMl -= 30;
            System.out.println(conteudo + ": agora restam " + quantidadeMl + " ml");
        } else {
            System.out.println(conteudo + ": acabou!");
        }
    }
}
```

```bash
javac PrimeiroObjeto.java
java PrimeiroObjeto
```

**Experimente:** crie uma terceira xícara, beba vários goles e veja que cada objeto
tem seu **próprio estado** — mexer numa não afeta as outras.

---

## ✅ O que você deve levar deste módulo

- [ ] POO existe para **juntar dados e regras** na mesma caixa (o objeto).
- [ ] Objeto = **estado** (o que tem) + **comportamento** (o que faz).
- [ ] **Classe** é a planta; **objeto** é a casa construída com `new`.
- [ ] Os 4 pilares existem para deixar o código seguro, reaproveitável, flexível e simples.
- [ ] Pensar em OO é perguntar **"que coisas existem e o que cada uma faz?"**.

---

[📚 Voltar ao Índice](../README.md) | [01 - Modelagem →](../01-modelagem/)
