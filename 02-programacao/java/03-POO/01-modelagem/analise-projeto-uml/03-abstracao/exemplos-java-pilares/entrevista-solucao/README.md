# 🎧 Solução da entrevista — aplicação MelodIA

Esta pasta é a **resolução completa** da [entrevista de modelagem](../entrevista-modelagem-melodia.md):
o **diagrama explicado parte por parte** + a **aplicação Java** inteira que o implementa.

## O que tem aqui

- **[EXPLICANDO-O-DIAGRAMA.md](EXPLICANDO-O-DIAGRAMA.md)** — a **aula de correção**: monta o
  diagrama em 9 partes e, para cada uma, responde *por que essa classe? por que herança? onde isso
  está na entrevista? como interpretar?* — com o trecho da fala, o diagrama do pedaço e o código.
- **Código Java** (o diagrama virou aplicação, roda em Java 17+):

| Arquivo | Papel |
|---------|-------|
| `Usuario.java` «abstract» | base das pessoas; senha protegida; `beneficios()` abstrato |
| `Ouvinte.java` / `Artista.java` | herdam `Usuario`; criam playlist / publicam música |
| `ConteudoDeAudio.java` «abstract» | base do que toca; `descricaoCurta()` abstrato |
| `Musica.java` / `Podcast.java` | herdam `ConteudoDeAudio` |
| `Playlist.java` | **agrega** conteúdos (`◇`) |
| `Assinatura.java` | **parte** do ouvinte (`◆` composição) |
| `AppMelodia.java` | `main` que roda o cenário da entrevista |

## ▶️ Como rodar (Java 17+)

```bash
javac *.java
java AppMelodia
```

---

[⬅️ Enunciado da entrevista](../entrevista-modelagem-melodia.md) · [📂 Voltar para os exemplos](../README.md)
