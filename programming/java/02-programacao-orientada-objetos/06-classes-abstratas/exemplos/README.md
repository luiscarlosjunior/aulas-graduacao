# Exemplos Completos - Classes Abstratas

Este diretório contém dois sistemas completos implementados com classes abstratas, demonstrando conceitos avançados de POO.

## 🎯 Sistema 1: Gerenciamento de Funcionários

### Arquivos:
- `Funcionario.java` - Classe abstrata base
- `Gerente.java` - Funcionário com bônus e níveis
- `Vendedor.java` - Funcionário com comissão sobre vendas
- `Desenvolvedor.java` - Funcionário com bônus por projeto
- `TesteSistemaFuncionarios.java` - Classe de teste

### Diagrama de Classes:

![Sistema de Funcionários](../img/sistema-funcionarios.png)

### Como executar:

```bash
# Compilar todos os arquivos
javac Funcionario.java Gerente.java Vendedor.java Desenvolvedor.java TesteSistemaFuncionarios.java

# Executar o teste
java TesteSistemaFuncionarios
```

### Conceitos Demonstrados:

1. **Classe Abstrata Base** (`Funcionario`)
   - Define estrutura comum para todos os funcionários
   - Atributos protegidos: `nome`, `salarioBase`, `departamento`
   - Métodos abstratos: `calcularSalario()`, `exibirDetalhes()`
   - Método concreto: `exibirInformacoes()` - Template Method

2. **Polimorfismo**
   - Array de `Funcionario[]` contém diferentes tipos
   - Cada tipo calcula salário de forma específica
   - Todos compartilham comportamento comum

3. **Encapsulamento**
   - Atributos protegidos na classe base
   - Atributos privados nas subclasses
   - Getters públicos para acesso controlado

4. **Template Method Pattern**
   - `exibirInformacoes()` define o fluxo geral
   - Chama métodos abstratos implementados pelas subclasses
   - Garante estrutura consistente na apresentação

### Saída Esperada:

```
╔══════════════════════════════════════════════════════════╗
║      SISTEMA DE GESTÃO DE FUNCIONÁRIOS - EMPRESA XYZ     ║
╚══════════════════════════════════════════════════════════╝

=== REGISTRANDO ATIVIDADES DO MÊS ===
[...]
=== FOLHA DE PAGAMENTO DO MÊS ===
[...]
╔══════════════════════════════════════════════════════════╗
║                    RESUMO FINANCEIRO                     ║
[...]
```

---

## 🎮 Sistema 2: Gerenciamento de Jogos

### Arquivos:
- `Jogo.java` - Classe abstrata base com Template Method
- `JogoCartas.java` - Jogo de cartas com baralho e rodadas
- `JogoTabuleiro.java` - Jogo de tabuleiro com dados e casas
- `JogoEletronico.java` - Jogo eletrônico com níveis e pontuação
- `TesteSistemaJogos.java` - Classe de teste

### Diagrama de Classes:

![Sistema de Jogos](../img/sistema-jogos.png)

### Como executar:

```bash
# Compilar todos os arquivos
javac Jogo.java JogoCartas.java JogoTabuleiro.java JogoEletronico.java TesteSistemaJogos.java

# Executar o teste
java TesteSistemaJogos
```

### Conceitos Demonstrados:

1. **Template Method Pattern (Avançado)**
   - Método `executarJogo()` é `final` - não pode ser sobrescrito
   - Define fluxo fixo: iniciar → jogar → terminar
   - Cada subclasse implementa as etapas específicas
   - Garante que todos os jogos sigam o mesmo processo

2. **Abstração de Comportamento**
   - Comportamentos comuns na classe base
   - Comportamentos específicos nas subclasses
   - Interface consistente para todos os tipos

3. **Estado Gerenciado**
   - Atributo `emAndamento` controlado pelo template method
   - Evita inconsistências no fluxo de execução

4. **Flexibilidade e Extensibilidade**
   - Fácil adicionar novos tipos de jogos
   - Basta implementar os 3 métodos abstratos
   - O fluxo de execução já está definido

### Saída Esperada:

```
╔════════════════════════════════════════════════════════════╗
║          🎮 PLATAFORMA DE JOGOS - GAME CENTER 🎮          ║
║                  Sistema de Gerenciamento                  ║
╚════════════════════════════════════════════════════════════╝
[...]
```

---

## 🔍 Análise Comparativa dos Exemplos

### Sistema de Funcionários
- **Foco**: Cálculos específicos por tipo
- **Pattern**: Template Method simples
- **Variação**: Lógica de cálculo de salário
- **Uso**: Sistemas de RH, folha de pagamento

### Sistema de Jogos
- **Foco**: Fluxo de execução consistente
- **Pattern**: Template Method com método final
- **Variação**: Implementação das etapas do jogo
- **Uso**: Engines de jogos, simuladores

---

## 💡 Lições Aprendidas

### Quando usar Classes Abstratas:

✅ **Use quando:**
- Há código comum a ser compartilhado
- Existe uma hierarquia natural "é-um"
- Precisa controlar o fluxo de execução
- Quer garantir que subclasses implementem certos métodos

❌ **Evite quando:**
- Não há código comum (use interface)
- Precisa de múltipla herança (use interfaces)
- A hierarquia não é natural
- Subclasses são muito diferentes entre si

### Boas Práticas Observadas:

1. **Método Template Final**
   ```java
   public final void executarJogo() {
       // Garante fluxo consistente
   }
   ```

2. **Atributos Protegidos**
   ```java
   protected String nome;
   protected int numeroJogadores;
   ```

3. **Métodos Abstratos Bem Definidos**
   ```java
   public abstract void iniciar();
   public abstract void jogar();
   public abstract void terminar();
   ```

4. **Documentação Clara**
   - JavaDoc em todas as classes
   - Comentários explicativos nos métodos
   - Exemplos de uso nas classes de teste

---

## 🚀 Próximos Passos

Após estudar estes exemplos:

1. Execute os programas e observe a saída
2. Modifique os valores e veja o impacto
3. Adicione novos tipos (ex: `Estagiario`, `JogoRPG`)
4. Implemente os exercícios propostos
5. Crie seus próprios sistemas com classes abstratas

---

## 📚 Referências

- [README principal](../README.md) - Conceitos teóricos
- [Exercícios](../exercicios/README.md) - Desafios práticos
- [Diagramas UML](../img/) - Visualização da arquitetura

---

**Desenvolvido para o Curso de Programação Orientada a Objetos**  
**Universidade Nove de Julho - 2024**
