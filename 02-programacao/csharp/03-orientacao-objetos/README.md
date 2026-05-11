# 03 — Orientação a Objetos em C#

> Nesta etapa, o objetivo não é apenas “ver classes”. O foco é fazer o aluno entender **por que** modelamos problemas com objetos, **quando** usar cada conceito e **como** isso melhora a organização do sistema.

---

## O que o aluno deve entender primeiro

Antes de abrir o código, conduza a discussão com estas perguntas:

1. **Que problema do mundo real queremos representar?**
2. **Quais dados precisam ser protegidos?**
3. **Quais comportamentos mudam de um tipo para outro?**
4. **Quais classes “têm” outras classes?**
5. **Como adicionar novos comportamentos sem quebrar o que já existe?**

Essas perguntas levam naturalmente aos pilares de orientação a objetos:

- **Classes e objetos**
- **Encapsulamento**
- **Herança**
- **Polimorfismo**
- **Composição**

---

## Ordem didática recomendada

| Etapa | Pergunta para a turma | Conceito central | Exemplo |
|---|---|---|---|
| 1 | “Como representar um produto com regras de negócio?” | Encapsulamento | [EncapsulamentoEcommerce](./EncapsulamentoEcommerce/) |
| 2 | “O que funcionários diferentes têm em comum?” | Herança | [HerancaFuncionarios](./HerancaFuncionarios/) |
| 3 | “Como trocar o canal de envio sem mudar o sistema?” | Polimorfismo | [PolimorfismoNotificacoes](./PolimorfismoNotificacoes/) |
| 4 | “Como classes colaboram entre si em um sistema real?” | Composição + revisão geral | [BancoExemplo](./BancoExemplo/) |

---

## Material principal para a aula

- [Guia didático com problemas, passo a passo e diagramas de classes](./GUIA-DIDATICO.md)

Esse guia foi organizado para ser usado **antes** da leitura detalhada do código.

---

## Como explorar cada pasta

### 1. EncapsulamentoEcommerce

Use este exemplo para mostrar que:

- nem todo dado deve ficar livre para alteração;
- a classe protege suas próprias regras;
- propriedades podem validar entradas;
- métodos de negócio mantêm o objeto consistente.

### 2. HerancaFuncionarios

Use este exemplo para mostrar que:

- diferentes tipos compartilham uma base comum;
- a classe mãe define o que é comum;
- classes filhas especializam comportamentos;
- métodos abstratos e `override` existem para lidar com variações.

### 3. PolimorfismoNotificacoes

Use este exemplo para mostrar que:

- o sistema trabalha com um contrato;
- implementações diferentes respondem ao mesmo método;
- novas funcionalidades podem ser adicionadas sem reescrever o fluxo principal.

### 4. BancoExemplo

Use este exemplo para revisar:

- encapsulamento em `Conta`;
- herança em `ContaPoupanca`;
- composição em `Cliente`;
- polimorfismo ao usar referências do tipo base.

---

## Estratégia de ensino recomendada

1. **Comece pelo problema**, não pela sintaxe.
2. **Peça que a turma proponha classes e responsabilidades.**
3. **Mostre o diagrama de classes antes do código.**
4. **Só então abra os arquivos `.cs` para validar a modelagem.**
5. **Compare soluções ruins e boas**, destacando o motivo das decisões.
6. **Feche cada exemplo com uma regra prática** do tipo:
   - “encapsulamento protege estado”;
   - “herança reutiliza o que é comum”;
   - “polimorfismo reduz `if/else` por tipo”;
   - “composição modela relações de colaboração”.

---

## Execução dos exemplos

```bash
cd /home/runner/work/aulas-graduacao/aulas-graduacao/02-programacao/csharp/03-orientacao-objetos/EncapsulamentoEcommerce
dotnet run
```

Troque a pasta para executar os demais exemplos:

- `HerancaFuncionarios`
- `PolimorfismoNotificacoes`
- `BancoExemplo`

---

## Perguntas para fixação

- Qual problema o encapsulamento evita?
- Quando uma classe deve herdar de outra?
- Qual a diferença entre “ter algo” e “ser um tipo de algo”?
- O que muda no sistema quando adicionamos um novo canal de notificação?
- Em qual exemplo a composição aparece com mais clareza?

---

## Próximos passos

- Ler o [Guia Didático](./GUIA-DIDATICO.md)
- Executar os projetos
- Pedir que os alunos desenhem seus próprios diagramas antes de alterar o código

---

**Seção anterior:** [02 — Fundamentos do C#](../02-fundamentos-csharp/)  
**Próxima seção:** [04 — C# Avançado](../04-csharp-avancado/)
