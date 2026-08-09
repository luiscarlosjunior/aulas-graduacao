# 🎧💳 Projeto-base em Java — Melodia (Streaming + Banco)

> **Este é o "dia de Java" do curso.** O mesmo sistema que você **modela** em UML na
> [pasta-pai](../) é **implementado e executado** aqui. Um dia desenhamos os diagramas;
> no outro, transformamos os diagramas em código Java que roda. **Modelo e código são
> dois lados da mesma moeda** — e esta pasta é a ponte entre eles.

📌 **Referência cruzada:** cada classe aqui aponta, nos comentários, para o diagrama
correspondente. E cada diagrama da pasta-pai aponta de volta para as classes daqui.

---

## O domínio em uma frase

**Melodia** é uma plataforma de streaming de música cujas assinaturas são pagas por uma
**conta bancária/carteira**. Assim, um único projeto exercita **dois domínios clássicos de
ensino de POO** — *streaming* (catálogo, playlists, reprodução, royalties) e *banco*
(saldo, saque, transferência, extrato) — e mostra como eles **colaboram** (assinar Premium
= debitar a conta).

A descrição completa e narrativa do domínio (a "especificação" que vira os diagramas) está
em **[../00-projeto-base/](../00-projeto-base/)**.

---

## ▶️ Como executar

Pré-requisito: **Java 17+** (`java -version`).

```bash
cd projeto-base-java
javac -d out $(find src -name "*.java")
java -cp out com.melodia.Principal
```

Você verá um cenário completo: contas criadas, catálogo publicado, Ana assinando Premium
(com débito na conta), Lucas sendo **suspenso** por falta de saldo e depois **reativado**,
reproduções creditando royalties e o extrato bancário no fim.

---

## 🗂️ Estrutura dos pacotes (mapa para os diagramas)

```
src/com/melodia/
├── banco/         → ContaBancaria, Transacao, TipoTransacao
├── usuario/       → Usuario (abstrata), Ouvinte, Artista
├── assinatura/    → Assinatura, Plano, StatusAssinatura
├── catalogo/      → Musica, Album (composição), Playlist (agregação)
├── plataforma/    → PlataformaStreaming (fachada; onde os domínios se encontram)
└── Principal.java → cenário executável (a "história" que os diagramas contam)
```

Essa divisão em pacotes é exatamente o **[Diagrama de Pacotes](../18-diagrama-de-pacotes/)**.

---

## 🧭 Onde cada pilar/conceito aparece no código

| Conceito | Onde ver | Diagrama relacionado |
|----------|----------|----------------------|
| **Abstração** | `Usuario` (abstrata) generaliza Ouvinte/Artista | [03](../03-abstracao/) |
| **Encapsulamento** | `ContaBancaria.saldo` privado + validações | [06-atributos](../06-atributos/) |
| **Herança** | `Ouvinte`/`Artista` `extends Usuario` | [09-classes](../09-diagrama-de-classes/) |
| **Polimorfismo** | `tipoDePerfil()` sobrescrito | [09-classes](../09-diagrama-de-classes/) |
| **Composição** | `Album` → faixas; `ContaBancaria` → `Transacao` | [05-associacao](../05-associacao/) |
| **Agregação** | `Playlist` → `Musica` (músicas sobrevivem à playlist) | [05-associacao](../05-associacao/) |
| **Máquina de estados** | `Assinatura` (ATIVA/SUSPENSA/CANCELADA) | [14-estados](../14-diagrama-de-maquina-de-estados/) |
| **Colaboração/mensagens** | `assinarPremium()` chama conta+assinatura | [11-sequencia](../11-diagrama-de-sequencia/) |

---

## 🏭 Como isso se parece com a indústria (e como não)

- **Parecido:** separação em pacotes por responsabilidade, objetos de valor imutáveis
  (`Transacao`), encapsulamento de invariantes, enums para conjuntos fechados, uma *fachada*
  (`PlataformaStreaming`) coordenando serviços.
- **Diferente (simplificado de propósito):** aqui os dados ficam em memória (listas), não há
  banco de dados, rede, concorrência, autenticação real nem tratamento de dinheiro com
  `BigDecimal` (usamos `double` por didática — **em produção, valores monetários usam
  `BigDecimal`**, pois `double` acumula erro de arredondamento). Esses pontos viram os
  diagramas de **[Componentes](../17-diagrama-de-componentes/)** e
  **[Implantação](../19-diagrama-de-implantacao/)**.

> 💡 **Exercício-ponte:** ao terminar cada diagrama na pasta-pai, volte aqui e localize a
> classe/método correspondente. Se o diagrama disser algo que o código não faz (ou
> vice-versa), um dos dois está desatualizado — mantê-los coerentes é o coração da
> engenharia de software orientada a modelos.
