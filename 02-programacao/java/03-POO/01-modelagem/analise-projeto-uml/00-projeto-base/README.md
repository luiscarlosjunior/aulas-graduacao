# 00 — Projeto-base: Melodia (Streaming de Música + Conta Bancária)

> **Leia esta pasta primeiro.** Aqui está a **especificação** do sistema que usaremos em
> *todos* os diagramas e no *projeto Java*. Modelar é traduzir uma descrição em modelos —
> então precisamos de uma boa descrição para traduzir.

---

## 1. A história do sistema (o "enunciado")

> A **Melodia** é uma plataforma de **streaming de música**. Nela, **ouvintes** escutam
> **músicas** organizadas em **álbuns** (publicados por **artistas**) e montam suas próprias
> **playlists**. Para ouvir sem anúncios e com qualidade alta, o ouvinte assina um **plano**
> (*Free*, *Premium* ou *Família*). A **assinatura** paga é cobrada **todo mês** de uma
> **conta bancária/carteira** do ouvinte: se houver saldo, a assinatura fica **ativa**; se
> faltar, é **suspensa** até o próximo pagamento. Cada **reprodução** gera um pequeno
> **royalty** ao artista, creditado depois na conta dele. O sistema mantém o **extrato** de
> todas as movimentações financeiras.

Esse parágrafo é a nossa fonte de verdade. Guarde-o: vamos "sublinhar as palavras" dele
para extrair classes, atributos e operações (a técnica do módulo
[01 - Modelagem](../../README.md)).

---

## 2. Por que este domínio? (justificativa didática)

Escolhemos **streaming + banco** de propósito, porque juntos cobrem todo o espectro da OO:

| O domínio oferece... | ...que exercita |
|----------------------|-----------------|
| Ouvinte e Artista são tipos de Usuário | **herança** e **abstração** |
| A mesma ação "descrever perfil" difere por tipo | **polimorfismo** |
| Conta protege o saldo com regras | **encapsulamento** |
| Álbum é dono de suas faixas | **composição** |
| Playlist só aponta para músicas existentes | **agregação** |
| Assinatura muda de estado (ativa/suspensa) | **máquina de estados** |
| Assinar dispara cobrança na conta | **colaboração entre objetos** |
| Streaming tem buffer que enche/esvazia no tempo | **temporização** |

> 🏭 **Na indústria:** streaming (Spotify, Deezar, YouTube Music) e sistemas financeiros
> (bancos, carteiras digitais, gateways) são dois dos domínios mais comuns em vagas de
> back-end. Modelar bem os dois é um treino diretamente aplicável.

---

## 3. Glossário do domínio (a "linguagem ubíqua")

Definir termos evita ambiguidade — na indústria isso se chama *ubiquitous language*
(DDD, *Domain-Driven Design*). Todo mundo, do cliente ao dev, usa as mesmas palavras.

| Termo | Significado no sistema |
|-------|------------------------|
| **Ouvinte** | Usuário que escuta músicas e monta playlists |
| **Artista** | Usuário que publica álbuns e recebe royalties |
| **Música** | Uma faixa reproduzível (título, artista, duração) |
| **Álbum** | Conjunto de faixas publicado por um artista |
| **Playlist** | Coleção pessoal de músicas montada por um ouvinte |
| **Plano** | Nível da assinatura: Free, Premium, Família |
| **Assinatura** | Vínculo do ouvinte com um plano, com um status |
| **Conta** | Carteira que guarda saldo e registra transações |
| **Transação** | Um lançamento no extrato (depósito, saque, pagamento…) |
| **Royalty** | Valor pago ao artista por reprodução |

---

## 4. Regras de negócio (invariantes)

Regras que o sistema **sempre** deve respeitar — elas guiam validações no código e guardas
nos diagramas:

- **R1.** O saldo de uma conta **nunca** fica negativo.
- **R2.** Um plano pago só fica **ativo** enquanto houver pagamento em dia.
- **R3.** Sem saldo na cobrança → a assinatura é **suspensa** (não cancelada).
- **R4.** Uma assinatura **cancelada** não pode voltar a ser cobrada (estado final).
- **R5.** Toda movimentação financeira gera **uma transação** no extrato.
- **R6.** Plano **Free** ouve o catálogo, mas **com anúncios**; planos pagos, sem anúncios.

---

## 5. Atores e funcionalidades (prévia)

- **Ouvinte:** buscar música, reproduzir, criar playlist, assinar/cancelar plano.
- **Artista:** publicar álbum, consultar/sacar royalties.
- **Sistema de Cobrança** (ator de tempo): dispara a cobrança mensal.

Isso vira, formalmente, o **[Diagrama de Casos de Uso](../08-diagrama-casos-de-uso/)**.

---

## 6. Do enunciado ao modelo (prévia da extração)

Aplicando "substantivo → classe/atributo, verbo → operação" ao parágrafo do item 1:

| No enunciado | Vira |
|--------------|------|
| ouvinte, artista, música, álbum, playlist, plano, assinatura, conta | **classes** |
| título, duração, saldo, status, preço | **atributos** |
| escutar, assinar, cobrar, suspender, publicar, creditar | **operações** |

O detalhamento de cada um está nas pastas [04-classe-e-objetos](../04-classe-e-objetos/),
[06-atributos](../06-atributos/) e [07-operacoes](../07-operacoes/).

---

## ✅ O que levar desta pasta

- [ ] Consigo **recontar a história** do sistema Melodia sem olhar.
- [ ] Sei por que o domínio junta **streaming + banco** (cobre todos os conceitos de OO).
- [ ] Entendo as **6 regras de negócio** — elas reaparecem como validações e guardas.
- [ ] Sei que este mesmo domínio está implementado em
      **[projeto-base-java/](../projeto-base-java/)**.

---

[⬅️ Índice do curso](../README.md) | [Próximo: 01 - Histórico das Metodologias ➡️](../01-historico-metodologias/)
