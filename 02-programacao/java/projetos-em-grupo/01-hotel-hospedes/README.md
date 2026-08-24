# 01 — Hotel: hóspedes e reservas

## 📞 O pedido do cliente
> "Tenho uma pousada com uns 20 quartos e ainda controlo tudo num caderno. Queria um
> sisteminha pra saber **quem está hospedado**, **em qual quarto** e **até quando**. Ah, e
> preciso saber quando o quarto vai ficar livre pra encaixar outra reserva. O resto a gente
> vê depois."

## 🧭 O que já sabemos
- Existem **quartos**, **hóspedes** e **reservas/estadias**.
- Um quarto tem algum tipo (casal, solteiro…) e uma diária.
- Uma reserva tem data de entrada e de saída.

## ❓ Perguntas em aberto (levantem com o "cliente")
- Um hóspede pode ter várias reservas ao longo do tempo? E acompanhantes na mesma reserva?
- O que acontece se tentarem reservar um quarto já ocupado nas mesmas datas?
- A diária muda por temporada? Tem check-in/check-out com horário?

## 🎚️ Complexidade sugerida
🟢🟡 MVP tranquilo; pode crescer com pagamento da estadia e relatório de ocupação.

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
> **Cliente (dono de uma pousada):** *"Minha ideia é simples: preciso organizar os hóspedes. Hoje faço
> tudo no caderno e me perco. Queria começar só cadastrando e vendo a lista. Depois a gente
> incrementa — já sei que vou precisar registrar as reservas de cada hóspede, mas uma coisa de cada vez."*
>
> **Gerente:** *"Combinado: primeiro algo simples funcionando, depois a gente evolui."*

### 🗣️ Entrevista 2 — O que realmente importa · *para os Encontros 3–4*
> **Gerente:** *"Na reunião o cliente falou MUITA coisa — inclusive um monte de detalhe que
> não interessa ao sistema. Nem tudo importa. Foquem no essencial: o que o sistema precisa
> guardar e fazer sobre hóspedes e sobre cada reserva."*
>
> **Dev sênior:** *"E chega de dado solto numa lista — vamos transformar cada coisa do
> domínio numa classe de verdade, com construtor. Assim o código para de virar bagunça."*

### 🗣️ Entrevista 3 — A regra inegociável e o susto · *para os Encontros 5–6*
> **Cliente:** *"Tem uma regra que NÃO pode falhar de jeito nenhum: não pode existir reserva de um quarto já ocupado nas mesmas datas. Se isso
> acontecer, é prejuízo e dor de cabeça na certa."*
>
> **Dev sênior:** *"E olha o que aconteceu semana passada: o valor da diária ficou negativo, porque alguém mexeu
> no dado direto, sem validação. **Não pode se repetir.** Cada objeto tem que **proteger os
> próprios dados**: o valor errado precisa ser recusado na entrada, não depois. É por isso
> que a próxima etapa é blindar as classes (encapsulamento) — não é frescura, é o que evita
> esse tipo de incidente."*

### 🗣️ Entrevista 4 — Tudo se conecta (e um tipo especial) · *para os Encontros 7–9*
> **Cliente:** *"Preciso enxergar como as coisas se ligam: um hóspede faz várias reservas; cada reserva é de um quarto. Ah, e agora temos um novo
> tipo: **hóspede VIP** — parecido com o resto, mas tem late check-out e isenção da taxa de serviço."*
>
> **Dev sênior:** *"Perfeito. **hóspede VIP** é um **tipo de hóspede** (o teste do «é um»),
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
> **Dev sênior:** *"Hoje só temos um jeito de lidar com a forma de pagamento da estadia (cartão, Pix, dinheiro), mas isso VAI mudar.
> Não amarrem o sistema a uma opção só: criem um **contrato** e deixem a implementação
> trocável — assim mudar depois é escrever uma classe nova, não refazer tudo."*
>
> **Analista de qualidade:** *"E me mostrem, passo a passo, como acontece fazer uma reserva e o check-in —
> inclusive o que ocorre **quando dá errado**. Preciso validar antes de virar código."*

### 🗣️ Entrevista 7 — Ciclo de vida e processo · *para os Encontros 14–15*
> **Analista:** *"A reserva anda **pulando etapa** e vira confusão. Precisa de fases
> bem definidas (reservada → check-in feito → check-out feito; ou cancelada) e não deixar avançar fora de ordem — nem no papel, nem no código."*
>
> **Cliente:** *"E documenta o processo da chegada do hóspede até o check-out: quero treinar a equipe nova com esse
> passo a passo."*

### 🗣️ Entrevista 8 — O sistema cresceu demais · *para os Encontros 16–17*
> **Tech lead:** *"Tá difícil mexer: a classe Reserva calcula o valor, emite a fatura E manda o e-mail — está tudo na mesma classe, e qualquer
> mudancinha quebra outra coisa. **Separem responsabilidades.**"*
>
> **Tech lead (depois):** *"E, quando terminarem, **simplifiquem**: o cálculo da diária com dezenas de ifs de temporada, e joguem fora
> todo código que ninguém usa. Simples é melhor que 'esperto'."*

### 🗣️ Entrevista 9 — Organizar e publicar · *para os Encontros 18–19*
> **Arquiteto:** *"Vamos crescer o time: outro grupo vai assumir a parte de pagamento e relatório de ocupação.
> Precisamos separar isso em **módulos** com fronteiras claras, sem tudo dependendo de tudo."*
>
> **DevOps:** *"E me digam **onde** isso vai rodar: pensei em um computador na recepção e um app para o hóspede ver a reserva. Preciso planejar a
> infraestrutura e a segurança."*

### 🗣️ Entrevista 10 — A entrega final · *para o Encontro 20*
> **Cliente:** *"Chegou a hora: quero ver **rodando de ponta a ponta** — fazer uma reserva e o check-in — do jeito
> que a gente combinou."*
>
> **Gerente:** *"E que os diagramas **batam com o que o sistema faz de verdade**. É isso que
> a gente entrega e apresenta."*

---
---
🗂️ Criem aqui `modelagem/` e `java/` conforme as aulas avançam.
[⬅️ Banco de projetos](../README.md) · [🗓️ **Plano de evolução deste projeto**](PLANO-DE-EVOLUCAO.md)
