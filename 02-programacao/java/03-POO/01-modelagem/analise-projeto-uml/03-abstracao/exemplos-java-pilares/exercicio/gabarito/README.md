# ✅ Gabarito — Assinaturas e Planos da Melodia

> Resolução **comentada e executável** do [exercício](../README.md). Os arquivos `.java` desta
> pasta compilam e rodam em **Java 17+**.

## ▶️ Como rodar

```bash
javac *.java
java DemoAssinaturas
```

### Saída esperada

```
=== Planos disponíveis ===
  Gratuito — R$ 0,00/mes · com anuncios
  Premium — R$ 19,90/mes · sem anuncios · download offline
  Universitario — R$ 11,90/mes · sem anuncios · download offline

Ana Souza assinou. Valor mensal: R$ 19,90
Apos cancelar, valor mensal: R$ 0,00

Rejeitado: Dia de vencimento deve estar entre 1 e 28.
```

## 🗺️ Onde cada pilar está na resolução

| Pilar / relação | Onde | Como |
|-----------------|------|------|
| **Abstração** | `Plano` | `abstract class` + 3 métodos `abstract`; `resumo()` concreto (Template Method) |
| **Herança** | `PlanoGratuito/Premium/Universitario` | `extends Plano` + `@Override` |
| **Polimorfismo** | `precoMensal()`, `temAnuncios()`, `permiteDownload()` | redefinidos por plano; `resumo()` e `valorAPagar()` funcionam sem saber o tipo |
| **Encapsulamento** | `Assinatura`, `Assinante` | atributos `private` + validação; estado muda só por operações; invariante `dia ∈ 1..28` |
| **Composição** `◆` | `Assinante` → `Assinatura` | a assinatura é criada **dentro** de `assinar(...)` (`new Assinatura(...)`) |
| **Associação** `-->` | `Assinatura` → `Plano` | o plano é **recebido** pronto pelo construtor (existe por si só) |

## 🔎 Pontos de atenção (erros comuns dos alunos)

- **Repetir `resumo()` nas subclasses:** ele deve ficar **só** em `Plano` (senão, duplicação).
- **`setAtiva(boolean)` público:** não crie — o estado deve mudar só por `ativar()`/`cancelar()`.
- **Esquecer o invariante:** sem validar `diaDeVencimento`, entra `31`, `0`, `-1`… (estado inválido).
- **Composição × associação:** o `Plano` **não** deve ser criado dentro da `Assinatura` (ele é
  compartilhável e vem de fora); já a `Assinatura` **é** criada dentro do `Assinante`.
- **`double` para dinheiro:** aqui é **didático**. Em produção, use `BigDecimal` (evita erros de
  arredondamento em valores monetários).

## 📄 As classes

- [`Plano.java`](Plano.java) — abstração + template `resumo()`.
- [`PlanoGratuito.java`](PlanoGratuito.java) · [`PlanoPremium.java`](PlanoPremium.java) · [`PlanoUniversitario.java`](PlanoUniversitario.java) — herança + polimorfismo.
- [`Assinatura.java`](Assinatura.java) — encapsulamento + associação com `Plano`.
- [`Assinante.java`](Assinante.java) — composição com `Assinatura`.
- [`DemoAssinaturas.java`](DemoAssinaturas.java) — demonstração dos 4 pilares.

---

[⬅️ Enunciado do exercício](../README.md)
