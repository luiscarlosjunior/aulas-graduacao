# 10 — Diagrama de Objetos

**📌 Família:** estrutural · **Responde:** *como fica o sistema num instante concreto?*

---

## 1. Conceito

Se o diagrama de classes é o *molde*, o de **objetos** é uma **fotografia** do sistema
rodando: mostra **instâncias reais** (objetos) com **valores concretos** nos atributos e os
**links** entre elas. É um caso particular do diagrama de classes, usado para **ilustrar um
cenário** ou **validar o modelo** com um exemplo real.

---

## 2. Notação

- **Objeto:** retângulo com o nome **sublinhado** no formato `nomeObjeto : Classe`. O nome
  pode ser omitido → `: Classe` (objeto anônimo).
- **Valores dos atributos:** `atributo = valor`.
- **Link:** linha ligando objetos — uma *instância* de uma associação. **Sem multiplicidade**
  (aqui tudo é concreto e contável).

---

## 3. Aplicação e exemplo (Melodia — um instante real)

Retrato do sistema **logo após Ana assinar Premium** (o estado que o `Principal.java` produz):

```
 ┌───────────────────────────────┐        ┌────────────────────────────────┐
 │ ana : Ouvinte                  │ possui │ contaAna : ContaBancaria        │
 │ nome = "Ana Souza"             ├────────▶ numero = "0001-1"               │
 │ email = "ana@email.com"        │        │ saldo = 30.10                   │
 └───────────────┬───────────────┘        └────────────────────────────────┘
                 │ assina
                 ▼
 ┌───────────────────────────────┐
 │ assAna : Assinatura            │
 │ plano = PREMIUM                │
 │ status = ATIVA                 │
 │ proximaCobranca = 2026-09-09   │
 └───────────────────────────────┘

 ┌───────────────────────────────┐        ┌────────────────────────────────┐
 │ lucas : Ouvinte                │ assina │ assLucas : Assinatura           │
 │ nome = "Lucas Dias"            ├────────▶ plano = PREMIUM                 │
 └───────────────────────────────┘        │ status = SUSPENSA  (sem saldo)  │
                                           └────────────────────────────────┘
```

> 🧠 **Compare com o [diagrama de classes](../09-diagrama-de-classes/):** lá, `Ouvinte` é o
> tipo (todos os ouvintes); aqui, `ana` e `lucas` são **dois objetos** concretos — e note que
> a assinatura de Ana está **ATIVA** e a de Lucas **SUSPENSA**. É um retrato de um momento,
> não a regra geral.

---

## 4. Para que serve na prática

- **Validar o modelo:** se você não consegue montar um objeto válido para um cenário real, o
  diagrama de classes está errado.
- **Explicar um caso específico** para alguém (ex.: "veja como fica quando o saldo acaba").
- **Depurar estruturas complexas:** desenhar os objetos e links ajuda a achar referências
  erradas, ciclos, dados órfãos.

---

## 5. Vantagens e desvantagens

| ✅ Vantagens | ❌ Desvantagens |
|-------------|-----------------|
| Concreto: fala de dados reais, fácil de entender | Mostra **um** instante — não generaliza |
| Excelente para validar/depurar o modelo de classes | Vira bagunça com muitos objetos |
| Ótimo recurso didático | Pouco usado como documentação permanente |

---

## 6. Na indústria (como sim, como não)

- ✅ **Muito útil em depuração e ensino**, e para ilustrar um *bug* ("neste estado, o objeto
  fica assim"). Ferramentas de debug mostram, na prática, um "diagrama de objetos" ao vivo.
- ⚠️ **Raro como documentação oficial** — como retrata só um instante, envelhece rápido. Use
  para **comunicar um ponto específico**, não para documentar o sistema.
- 💡 É a ponte perfeita entre teoria e código: cada objeto do desenho corresponde a um `new` no
  `Principal.java`.

---

## ✅ O que levar desta pasta

- [ ] Diagrama de objetos = **fotografia** com instâncias e **valores reais**.
- [ ] Objeto é `nome : Classe` **sublinhado**; ligações são **links** (sem multiplicidade).
- [ ] Serve para **validar/depurar/explicar** — não para documentar tudo.
- [ ] Cada objeto do desenho = um `new` no código.

---

[⬅️ 09 - Diagrama de Classes](../09-diagrama-de-classes/) | [Índice](../README.md) | [11 - Diagrama de Sequência ➡️](../11-diagrama-de-sequencia/)
