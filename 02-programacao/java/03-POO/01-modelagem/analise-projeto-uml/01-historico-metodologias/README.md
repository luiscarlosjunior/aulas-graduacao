# 01 — Histórico das Metodologias de Desenvolvimento de Sistemas

> Para entender *por que* modelamos com objetos e UML, vale ver como chegamos aqui. A forma
> de construir software evoluiu junto com o tamanho e a complexidade dos sistemas — e cada
> metodologia nasceu para curar a dor da anterior.

---

## 1. Conceito: o que é uma "metodologia de desenvolvimento"?

Uma **metodologia** (ou *processo de software*) é um **conjunto organizado de etapas,
papéis e artefatos** para construir software de forma repetível. Ela responde: *em que ordem
fazer as coisas? o que produzir em cada fase? quem faz o quê?*

> ⚠️ **Não confunda:** **metodologia** (Cascata, Scrum) ≠ **notação** (UML) ≠ **linguagem**
> (Java). A UML é usada *dentro* de qualquer metodologia; é agnóstica.

---

## 2. A linha do tempo

```mermaid
timeline
    title Evolução das metodologias de desenvolvimento
    1950-60 : Codifica-e-corrige : Sem processo, direto no código
    1970 : Modelo Cascata (Royce) : Fases rígidas e sequenciais
    1970-80 : Análise Estruturada : Foco em funções e fluxo de dados (DFD)
    1980-90 : Análise e Programação OO : Foco em objetos; vários métodos
    1990-96 : "Guerra dos métodos" : Booch, OMT (Rumbaugh), OOSE (Jacobson)
    1997 : UML 1.x (OMG) : Unificação das notações OO
    2001 : Manifesto Ágil : Scrum, XP; entrega iterativa
    2005 : UML 2.x : 13 diagramas (base atual)
    Hoje : DevOps / entrega contínua : Modelagem enxuta a serviço do fluxo
```

---

## 3. As grandes fases (conceito + prós e contras)

### 3.1 Codifica-e-corrige (*code and fix*)
Programava-se sem planejar. **Vantagem:** rápido para algo minúsculo. **Desvantagem:** vira
caos quando o sistema cresce; sem documentação, ninguém entende o código do colega.

### 3.2 Modelo Cascata (Waterfall, 1970)
Fases **sequenciais**: requisitos → análise → projeto → implementação → testes → manutenção.

- ✅ **Vantagens:** ordem, previsibilidade, documentação forte, bom para requisitos
  **estáveis** (ex.: sistemas embarcados, contratos com escopo fechado).
- ❌ **Desvantagens:** rígido; um erro de requisito descoberto no fim custa caríssimo; o
  cliente só vê o produto no final.
- 🏭 **Hoje:** ainda usado em setores regulados (aviação, saúde, licitações), mas raro em
  produtos digitais.

### 3.3 Análise Estruturada (DeMarco, Yourdon — anos 70/80)
Organiza o sistema por **funções** e **fluxo de dados** (diagramas DFD), **separando** dados
de comportamento.

- ✅ **Vantagem:** ótima para sistemas centrados em processamento de dados (lote, relatórios).
- ❌ **Desvantagem:** dados e funções separados → mudança em uma estrutura de dados
  espalha alterações por muitas funções. É o problema que a OO veio resolver.

### 3.4 Orientação a Objetos (anos 80/90)
Junta **dados + comportamento** na mesma unidade (o objeto). Surgem métodos concorrentes:

| Método | Autor | Forte em |
|--------|-------|----------|
| **Booch** | Grady Booch | projeto e detalhe |
| **OMT** | James Rumbaugh | análise e modelagem de dados |
| **OOSE** | Ivar Jacobson | **casos de uso** |

### 3.5 A unificação — UML (1997)
Os **"três amigos"** (Booch, Rumbaugh, Jacobson) uniram seus métodos numa **notação única**,
padronizada pela **OMG** (*Object Management Group*). A **UML 2.x** (2005) definiu os **13
diagramas** que estudamos. Junto veio o **RUP** (*Rational Unified Process*): iterativo,
guiado por casos de uso e por arquitetura.

### 3.6 Métodos Ágeis (2001→)
O **Manifesto Ágil** priorizou *"software funcionando sobre documentação abrangente"* e
*"responder a mudanças sobre seguir um plano"*. Scrum e XP popularizaram entregas curtas.

- ✅ **Vantagens:** feedback rápido, adapta-se a requisitos incertos (o caso comum em produto).
- ❌ **Desvantagens:** documentação escassa pode virar dívida técnica; exige cliente presente
  e time maduro.
- 🏭 **Hoje:** domina o mercado de produto digital. A UML segue útil, usada **"just enough"**
  (o suficiente para comunicar uma decisão, não para documentar tudo).

---

## 4. Na indústria: como isso aparece no dia a dia

- Ninguém "faz cascata puro" nem "ágil puro": times misturam (ex.: *Water-Scrum-Fall*).
- **Você raramente desenha os 13 diagramas.** O uso real é: um **diagrama de sequência** para
  alinhar uma integração difícil; um **de classes** para combinar o modelo antes de codar; um
  **de implantação** para conversar com o time de infra. Depois, muitas vezes, o diagrama é
  descartado — serviu para **pensar e comunicar**.
- **Onde a UML resiste forte:** *onboarding* de novos devs (um diagrama vale mil linhas
  lidas), design reviews, arquitetura, documentação de sistemas críticos.
- **Onde a UML perde espaço:** substituída por *diagramas informais* (quadro branco, Excalidraw),
  *C4 model* para arquitetura, e código autoexplicativo com testes.

> 💡 **Dica de professor:** aprenda UML pela *capacidade de raciocínio* que ela treina —
> pensar em atores, estados, colaborações. Essa habilidade sobrevive a qualquer moda de
> ferramenta.

---

## 5. Vantagens e desvantagens de modelar (o debate honesto)

| Modelar antes de codar | |
|------------------------|--|
| ✅ Erros de design saem no papel (barato), não em produção (caro) | ❌ Pode virar "paralisia por análise" |
| ✅ Alinha o time e o cliente numa linguagem comum | ❌ Diagrama desatualizado engana mais que ajuda |
| ✅ Facilita manutenção e onboarding | ❌ Excesso de documentação vira burocracia |

**Equilíbrio (a resposta madura):** modele **o suficiente para reduzir o risco da decisão que
você tem agora**. Nem mais, nem menos.

---

## ✅ O que levar desta pasta

- [ ] Saímos de "software é código" para "software é um **modelo de objetos** que gera código".
- [ ] **Metodologia ≠ notação ≠ linguagem.**
- [ ] Cascata dá **ordem**; ágil dá **adaptação**; cada um serve a um contexto.
- [ ] A UML nasceu da **unificação** de Booch + OMT + OOSE (OMG, 1997) e é **agnóstica**.
- [ ] Na indústria, use UML **com parcimônia** — pelo raciocínio que ela treina.

---

[⬅️ 00 - Projeto-base](../00-projeto-base/) | [Índice](../README.md) | [02 - Conceitos de OO ➡️](../02-conceitos-orientacao-objetos/)
