# 🏢 Banco de Projetos em Grupo — "Clientes de verdade"

> **20 projetos tradicionais da indústria**, um para cada grupo. Cada pasta traz o **pedido de
> um cliente** — do jeito que um cliente real fala: **incompleto, vago e cheio de pontas
> soltas**. A graça é essa: **os requisitos vão sendo descobertos e o sistema vai sendo
> construído conforme as aulas de modelagem e de Java avançam.**

Este banco de projetos é **separado das aulas** de propósito. As aulas ensinam *a técnica*
(no domínio-exemplo [Melodia](../03-POO/01-modelagem/analise-projeto-uml/)); aqui o seu grupo
aplica a técnica **no seu próprio cliente**, do zero, semana a semana.

---

## 🎯 Objetivo

- Cada **grupo escolhe (ou sorteia) um projeto** e fica com ele o curso inteiro.
- A cada aula, o grupo produz **o próximo pedaço** do seu sistema — primeiro o modelo (UML),
  depois o código (Java) — sempre sobre o **mesmo** projeto.
- No fim, cada grupo tem um sistema **modelado e implementado**, nascido de um briefing tão
  cru quanto os que se recebe na vida real.

> 💡 **Por que os textos são curtos?** Porque cliente **não entrega requisito pronto**. Parte
> essencial do trabalho de análise é *fazer as perguntas certas* e *preencher as lacunas*.
> Cada briefing tem uma seção **"Perguntas em aberto"** — comece por elas.

---

## 🧭 Como funciona (fluxo do grupo)

1. **Escolham um projeto** da lista abaixo (um por grupo).
2. Dentro da pasta do projeto, criem sua **área de trabalho** conforme o curso avança:
   ```
   NN-nome-do-projeto/
   ├── README.md              ← o briefing do cliente (já vem pronto; não apague)
   ├── PLANO-DE-EVOLUCAO.md   ← seu roteiro de 20 encontros (já vem pronto; sigam ele)
   ├── modelagem/             ← seus diagramas (um arquivo .md por diagrama, com Mermaid)
   └── java/                  ← seu código Java (o sistema de verdade)
   ```
3. **A cada aula**, entreguem o artefato correspondente (tabela abaixo).
4. **Levantem requisitos** de forma contínua: sempre que faltar informação, anotem a dúvida e
   decidam uma resposta razoável (documentem a decisão — é o que um analista faz).

---

## 🗓️ Roteiro de entregas (alinhado às aulas)

O grande diferencial deste banco de projetos: **o projeto não nasce pronto — ele cresce a
cada aula.** A cada encontro, o "cliente" manda um **novo feedback/pedido**, e esse pedido
só se resolve com o **conceito daquela aula**. O sistema começa como Java simples e vai
ganhando POO, encapsulamento, herança, SOLID, KISS… **na hora em que cada um faz falta.**

> ### 👉 Cada projeto tem o **seu próprio plano já contextualizado**
> Abra a pasta do seu projeto e siga o arquivo **`PLANO-DE-EVOLUCAO.md`** de lá: são
> **20 encontros** alternando **☕ Java** e **📐 Modelagem** (na ordem dos diagramas), e os
> feedbacks do cliente **já falam do seu domínio** (ex.: no hotel, o "tipo especial" é
> *hóspede VIP*; na loja, é *cliente VIP*; na farmácia, *medicamento controlado*).
>
> O **[plano-mestre (genérico)](PLANO-DE-EVOLUCAO.md)** fica aqui como referência do método e
> visão geral — mas o que cada grupo segue é o plano **da sua própria pasta**.

Resumo do arco (detalhe completo no [plano](PLANO-DE-EVOLUCAO.md)):

| Fase | Trilha | O grupo entrega, no seu projeto… |
|------|--------|----------------------------------|
| Início | ☕ Java | Cadastro simples (procedural) → depois **classes** com construtor |
| Fundamentos | 📐 Model | Glossário + entidades/atributos/operações; abstração e classe×objeto |
| Regras & dados | ☕ Java | **Encapsulamento** (private + validação) protegendo invariantes |
| Relacionamentos | 📐 Model | **Diagrama de classes** com associações e multiplicidades |
| Variação | ☕ Java | **Herança** e **polimorfismo** (tipos especiais do cliente) |
| Comportamento | 📐 Model | Casos de uso, **sequência**, **máquina de estados**, atividades |
| Contratos | ☕ Java | **Interfaces** plugáveis (trocar pagamento/notificação) |
| Qualidade | ☕ Java | **SOLID** e **KISS/DRY/YAGNI** (o sistema cresceu — refatorar) |
| Arquitetura | 📐 Model | **Pacotes**, **componentes**, **implantação** |
| Fechamento | ☕ Java | Sistema rodando de ponta a ponta (+ um padrão simples) |

> 🔗 Use o projeto [Melodia](../03-POO/01-modelagem/analise-projeto-uml/projeto-base-java/)
> como **referência de qualidade**: encapsulamento, pacotes por responsabilidade, um
> `Principal` que conta a história. O seu projeto deve ficar no mesmo nível.

---

## 📋 Os 20 projetos

| # | Projeto | Domínio |
|---|---------|---------|
| 01 | [Hotel — hóspedes e reservas](01-hotel-hospedes/) | Hotelaria |
| 02 | [Locadora de veículos](02-locadora-veiculos/) | Aluguel de carros |
| 03 | [Clínica — agendamento de consultas](03-clinica-agendamento/) | Saúde |
| 04 | [Escola — matrículas e notas](04-escola-matriculas/) | Educação |
| 05 | [Farmácia — estoque e vendas](05-farmacia-estoque-vendas/) | Varejo/Saúde |
| 06 | [Restaurante — comandas e pedidos](06-restaurante-comandas/) | Alimentação |
| 07 | [Oficina — ordens de serviço](07-oficina-ordens-servico/) | Automotivo |
| 08 | [Petshop — serviços e agenda](08-petshop-servicos/) | Serviços |
| 09 | [Academia — planos e frequência](09-academia-frequencia/) | Fitness |
| 10 | [Estacionamento — vagas e tickets](10-estacionamento-tickets/) | Mobilidade |
| 11 | [Loja virtual — pedidos](11-loja-virtual-pedidos/) | E-commerce |
| 12 | [Delivery de comida](12-delivery-comida/) | Logística/Alimentação |
| 13 | [Imobiliária — locação](13-imobiliaria-locacao/) | Imobiliário |
| 14 | [Agência de viagens — reservas](14-agencia-viagens-reservas/) | Turismo |
| 15 | [Cinema — sessões e ingressos](15-cinema-ingressos/) | Entretenimento |
| 16 | [Transportadora — entregas](16-transportadora-entregas/) | Logística |
| 17 | [Seguradora — apólices e sinistros](17-seguradora-apolices/) | Seguros |
| 18 | [RH — ponto e colaboradores](18-rh-ponto-colaboradores/) | Recursos Humanos |
| 19 | [Salão de beleza — agendamentos](19-salao-beleza-agendamentos/) | Serviços |
| 20 | [Condomínio — moradores e reservas](20-condominio-reservas/) | Administração |

---

## 👩‍🏫 Para o professor

- **Sorteio ou escolha:** 20 projetos cobrem 20 grupos sem repetir domínio.
- **Avaliação por fase:** cada linha do roteiro acima é um entregável avaliável — dá para
  acompanhar a evolução do grupo aula a aula.
- **Briefings propositalmente incompletos:** as lacunas são o gancho para exercitar
  **levantamento de requisitos**. Cada grupo pode chegar a um modelo *diferente e válido* —
  o que importa é a **justificativa**.
- **Escopo ajustável:** todos os briefings cabem num MVP simples (para focar em OO/UML) ou
  podem crescer (pagamento, relatórios, perfis de acesso) para grupos mais avançados.

> ⚠️ Cada `README.md` de projeto é o **briefing do cliente** — os grupos **não devem
> apagá-lo**; devem construir *ao redor* dele, criando `modelagem/` e `java/`.
