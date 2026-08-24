# 13 — Imobiliária: locação de imóveis

## 📞 O pedido do cliente
> "Tenho uma imobiliária. Trabalho com **imóveis** para alugar, os **proprietários** e os
> **inquilinos**. Quando fecha negócio, gero um **contrato de aluguel** com valor mensal e
> prazo. Saber quais imóveis estão disponíveis já ajudaria muito. Reajuste e IPTU depois."

## 🧭 O que já sabemos
- Existem **imóveis**, **proprietários**, **inquilinos** e **contratos**.
- Um imóvel pertence a um proprietário e pode estar disponível ou alugado.
- Um contrato liga um imóvel a um inquilino, com valor e prazo.

## ❓ Perguntas em aberto (levantem com o "cliente")
- O imóvel tem estados (disponível → alugado → em manutenção)?
- Proprietário e inquilino compartilham dados? (pense em generalização: Pessoa)
- Como registrar os pagamentos mensais do aluguel?

## 🎚️ Complexidade sugerida
🟡 Bom para associações múltiplas e máquina de estados do imóvel/contrato.

## 🎤 Entrevistas com o cliente e a equipe (leiam conforme o curso avança)

> Estas **conversas** simulam o que acontece num projeto real: o cliente e a equipe vão
> revelando mais coisas com o tempo. **Cada entrevista é liberada num ponto do curso** e
> explica *o que mudou* e *por que* a próxima etapa é necessária. **Leiam a entrevista
> indicada antes dos encontros correspondentes** — a maioria das dúvidas de vocês está aqui.

| Leiam antes dos… | Entrevista |
|------------------|------------|
| Encontros 1–2 | 1 · Visão geral |
| Encontros 3–4 | 2 · O que realmente importa |
| Encontros 5–6 | 3 · A regra inegociável e o susto |
| Encontros 7–9 | 4 · Tudo se conecta (e um tipo especial) |
| Encontros 10–11 | 5 · Fechando o escopo |
| Encontros 12–13 | 6 · Preparando as mudanças |
| Encontros 14–15 | 7 · Ciclo de vida e processo |
| Encontros 16–17 | 8 · O sistema cresceu demais |
| Encontros 18–19 | 9 · Organizar e publicar |
| Encontro 20 | 10 · A entrega final |

### 🗣️ Entrevista 1 — Visão geral · *para os Encontros 1–2*
> **Cliente (dono de uma imobiliária):** *"Minha ideia é simples: preciso organizar os imóveis. Hoje faço
> tudo no caderno e me perco. Queria começar só cadastrando e vendo a lista. Depois a gente
> incrementa — já sei que vou precisar registrar os contratos de aluguel, mas uma coisa de cada vez."*
>
> **Gerente:** *"Combinado: primeiro algo simples funcionando, depois a gente evolui."*

### 🗣️ Entrevista 2 — O que realmente importa · *para os Encontros 3–4*
> **Gerente:** *"Na reunião o cliente falou MUITA coisa — inclusive um monte de detalhe que
> não interessa ao sistema. Nem tudo importa. Foquem no essencial: o que o sistema precisa
> guardar e fazer sobre imóveis e sobre cada contrato."*
>
> **Dev sênior:** *"E chega de dado solto numa lista — vamos transformar cada coisa do
> domínio numa classe de verdade, com construtor. Assim o código para de virar bagunça."*

### 🗣️ Entrevista 3 — A regra inegociável e o susto · *para os Encontros 5–6*
> **Cliente:** *"Tem uma regra que NÃO pode falhar de jeito nenhum: não pode alugar um imóvel que já está com contrato ativo. Se isso
> acontecer, é prejuízo e dor de cabeça na certa."*
>
> **Dev sênior:** *"E olha o que aconteceu semana passada: o valor do aluguel ficou negativo, porque alguém mexeu
> no dado direto, sem validação. **Não pode se repetir.** Cada objeto tem que **proteger os
> próprios dados**: o valor errado precisa ser recusado na entrada, não depois. É por isso
> que a próxima etapa é blindar as classes (encapsulamento) — não é frescura, é o que evita
> esse tipo de incidente."*

### 🗣️ Entrevista 4 — Tudo se conecta (e um tipo especial) · *para os Encontros 7–9*
> **Cliente:** *"Preciso enxergar como as coisas se ligam: um proprietário tem vários imóveis; cada contrato liga um imóvel a um inquilino. Ah, e agora temos um novo
> tipo: **imóvel comercial** — parecido com o resto, mas tem regras de reajuste e impostos diferentes."*
>
> **Dev sênior:** *"Perfeito. **imóvel comercial** é um **tipo de imóvel** (o teste do «é um»),
> então dá pra herdar o que já existe em vez de copiar a classe inteira. E um aviso: **nada
> de encher o código de `if`** pra tratar cada tipo — deixem cada objeto se comportar do seu
> jeito. Isso vai facilitar demais quando aparecer mais um tipo lá na frente."*

### 🗣️ Entrevista 5 — Fechando o escopo · *para os Encontros 10–11*
> **Gerente:** *"Antes de crescer mais, quero no papel **o que o sistema faz e para quem**
> (os atores e as funções). Sem inventar tela — quero os objetivos dos usuários."*
>
> **Dev sênior:** *"E eu quero ver a **planta completa** das classes, pra garantir que o
> desenho e o código estão contando a mesma história. Se divergiu, a gente corrige agora."*

### 🗣️ Entrevista 6 — Preparando as mudanças · *para os Encontros 12–13*
> **Dev sênior:** *"Hoje só temos um jeito de lidar com a forma de cobrança do aluguel (boleto, débito, Pix), mas isso VAI mudar.
> Não amarrem o sistema a uma opção só: criem um **contrato** e deixem a implementação
> trocável — assim mudar depois é escrever uma classe nova, não refazer tudo."*
>
> **Analista de qualidade:** *"E me mostrem, passo a passo, como acontece fechar um contrato de locação —
> inclusive o que ocorre **quando dá errado**. Preciso validar antes de virar código."*

### 🗣️ Entrevista 7 — Ciclo de vida e processo · *para os Encontros 14–15*
> **Analista:** *"O imóvel anda **pulando etapa** e vira confusão. Precisa de fases
> bem definidas (disponível → alugado → disponível; ou em manutenção) e não deixar avançar fora de ordem — nem no papel, nem no código."*
>
> **Cliente:** *"E documenta o processo da visita ao fechamento do contrato: quero treinar a equipe nova com esse
> passo a passo."*

### 🗣️ Entrevista 8 — O sistema cresceu demais · *para os Encontros 16–17*
> **Tech lead:** *"Tá difícil mexer: a classe Contrato calcula o reajuste, gera o boleto E arquiva o PDF — está tudo na mesma classe, e qualquer
> mudancinha quebra outra coisa. **Separem responsabilidades.**"*
>
> **Tech lead (depois):** *"E, quando terminarem, **simplifiquem**: o cálculo de reajuste anual com muitos índices, e joguem fora
> todo código que ninguém usa. Simples é melhor que 'esperto'."*

### 🗣️ Entrevista 9 — Organizar e publicar · *para os Encontros 18–19*
> **Arquiteto:** *"Vamos crescer o time: outro grupo vai assumir a parte de cobrança e contratos.
> Precisamos separar isso em **módulos** com fronteiras claras, sem tudo dependendo de tudo."*
>
> **DevOps:** *"E me digam **onde** isso vai rodar: pensei em um site de busca de imóveis, um servidor e um banco. Preciso planejar a
> infraestrutura e a segurança."*

### 🗣️ Entrevista 10 — A entrega final · *para o Encontro 20*
> **Cliente:** *"Chegou a hora: quero ver **rodando de ponta a ponta** — fechar um contrato de locação — do jeito
> que a gente combinou."*
>
> **Gerente:** *"E que os diagramas **batam com o que o sistema faz de verdade**. É isso que
> a gente entrega e apresenta."*

---
---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
