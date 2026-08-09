# 12 — Diagrama de Estrutura Composta

**📌 Família:** estrutural · **Responde:** *como um elemento (classe/componente) é montado por
dentro?*

---

## 1. Conceito

O diagrama de **estrutura composta** abre a "caixa" de um elemento e mostra sua **estrutura
interna** em tempo de execução: as **partes** que o compõem, as **portas** por onde ele se
comunica e os **conectores** entre as partes. Diferente do diagrama de classes (que mostra
*tipos* e associações genéricas), ele foca em **como as partes colaboram dentro de um
contexto específico**.

---

## 2. Notação

- **Parte (*part*):** retângulo interno `nome : Tipo [multiplicidade]`, desenhado **dentro** da
  classe que o contém.
- **Porta (*port*):** quadradinho na borda = ponto de interação com o exterior.
- **Conector:** linha ligando partes (ou parte↔porta) que colaboram.
- **Interface fornecida/requerida:** "pirulito" `─○` (fornece) e "soquete" `─(` (requer).

---

## 3. Aplicação e exemplo (Melodia — a Plataforma por dentro)

```
┌───────────────────── PlataformaStreaming ─────────────────────┐
│                                                                │
│   ┌────────────────────┐        ┌──────────────────────────┐  │
│  ▢│ catalogo :          │────────│ servicoAssinatura :       │▢─┼──○ (assinar)
│ (buscar)│ CatalogoMusicas│        │ ServicoAssinatura         │  │
│   └────────────────────┘        └───────────┬──────────────┘  │
│            │ contém                           │ usa            │
│            ▼                                   ▼               │
│   ┌────────────────────┐        ┌──────────────────────────┐  │
│   │ musicas : Musica[*] │        │ gateway : ServicoPagamento│▢─┼──( (requer conta)
│   └────────────────────┘        └──────────────────────────┘  │
│                                                                │
└────────────────────────────────────────────────────────────────┘
    ▢ = porta      ○ = interface fornecida      ( = interface requerida
```

> 🧠 Enquanto o [diagrama de classes](../09-diagrama-de-classes/) diz *"Plataforma tem um
> catálogo e um serviço de assinatura"*, o de estrutura composta mostra **as partes concretas
> montadas** dentro de uma `PlataformaStreaming` e **por quais portas** ela expõe (assinar) e
> requer (pagamento) serviços.

---

## 4. Estrutura composta × Classes × Componentes

| Diagrama | Mostra |
|----------|--------|
| **Classes** | os *tipos* e como se associam, em geral |
| **Estrutura composta** | as *partes internas* de **um** elemento e como colaboram naquele contexto |
| **Componentes** | as peças de software de alto nível e suas interfaces (visão macro) |

---

## 5. Vantagens e desvantagens

| ✅ Vantagens | ❌ Desvantagens |
|-------------|-----------------|
| Detalha a arquitetura interna de um elemento complexo | É o diagrama **menos usado** da UML |
| Mostra colaboração em **tempo de execução** (partes vivas) | Notação (portas/pirulitos) confunde iniciantes |
| Bom para peças "plugáveis" e arquiteturas por composição | Redundante com componentes na maioria dos projetos |

---

## 6. Na indústria (como sim, como não)

- ⚠️ **Raramente desenhado** em software de negócio comum. Você pode ter uma carreira inteira
  sem usá-lo — e tudo bem.
- ✅ **Onde brilha:** sistemas **baseados em componentes/partes plugáveis** — software
  embarcado, arquitetura de plugins, engenharia de sistemas (SysML, primo da UML), e
  frameworks que montam objetos por composição de partes configuráveis.
- 💡 **Dica honesta de professor:** conheça-o para reconhecer, mas priorize **classes,
  sequência, estados e componentes** — esses você usará de verdade.

---

## ✅ O que levar desta pasta

- [ ] Estrutura composta = **as partes internas** de um elemento e seus **conectores/portas**.
- [ ] **Porta** = ponto de interação; **pirulito** fornece, **soquete** requer interface.
- [ ] É o diagrama **menos usado**; útil em sistemas por **composição de partes**.
- [ ] Não confunda com **componentes** (visão macro) nem **classes** (tipos genéricos).

---

[⬅️ 11 - Diagrama de Sequência](../11-diagrama-de-sequencia/) | [Índice](../README.md) | [13 - Diagrama de Comunicação ➡️](../13-diagrama-de-comunicacao/)
