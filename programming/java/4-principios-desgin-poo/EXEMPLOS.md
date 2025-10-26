# Exemplos de Princípios de Design em Java

Este diretório contém exemplos completos e executáveis em Java para todos os princípios de design fundamentais em Programação Orientada a Objetos mencionados no README principal.

## 📂 Estrutura dos Exemplos

Cada princípio possui dois arquivos:
1. **Violação** - Mostra o que NÃO fazer (código com problemas)
2. **Seguindo** - Mostra a implementação correta seguindo o princípio

## 🎯 Princípios Fundamentais

### 1. KISS - Keep It Simple, Stupid
📁 `01-kiss/`

- `CalculadoraComplexaDesnecessaria.java` - Exemplo de complexidade desnecessária
- `Calculadora.java` - Solução simples e direta

**Como executar:**
```bash
cd 01-kiss
javac Calculadora.java
java Calculadora
```

### 2. YAGNI - You Aren't Gonna Need It
📁 `02-yagni/`

- `UsuarioComFuncionalidadeEspeculativa.java` - Funcionalidades que ninguém pediu
- `Usuario.java` - Implementa apenas o necessário

**Como executar:**
```bash
cd 02-yagni
javac Usuario.java
java Usuario
```

### 3. DRY - Don't Repeat Yourself
📁 `03-dry/`

- `SistemaVendasComDuplicacao.java` - Código duplicado
- `CalculadoraDesconto.java` - Única fonte de verdade

**Como executar:**
```bash
cd 03-dry
javac CalculadoraDesconto.java
java CalculadoraDesconto
```

## 🏛️ Princípios SOLID

### 4. SRP - Single Responsibility Principle
📁 `04-solid/srp/`

- `UsuarioViolaSRP.java` - Múltiplas responsabilidades na mesma classe
- `UsuarioSegueSRP.java` - Cada classe tem uma responsabilidade

**Como executar:**
```bash
cd 04-solid/srp
javac UsuarioSegueSRP.java
java UsuarioSegueSRP
```

### 5. OCP - Open/Closed Principle
📁 `04-solid/ocp/`

- `CalculadoraDescontoViolaOCP.java` - Requer modificação para extensão
- `CalculadoraDescontoSegueOCP.java` - Aberto para extensão, fechado para modificação

**Como executar:**
```bash
cd 04-solid/ocp
javac CalculadoraDescontoSegueOCP.java
java CalculadoraDescontoSegueOCP
```

### 6. LSP - Liskov Substitution Principle
📁 `04-solid/lsp/`

- `FormaViolaLSP.java` - Quadrado não substitui Retângulo
- `FormaSegueLSP.java` - Hierarquia correta com substituibilidade

**Como executar:**
```bash
cd 04-solid/lsp
javac FormaSegueLSP.java
java FormaSegueLSP
```

### 7. ISP - Interface Segregation Principle
📁 `04-solid/isp/`

- `TrabalhadorViolaISP.java` - Interface "gorda" com muitos métodos
- `TrabalhadorSegueISP.java` - Interfaces pequenas e focadas

**Como executar:**
```bash
cd 04-solid/isp
javac TrabalhadorSegueISP.java
java TrabalhadorSegueISP
```

### 8. DIP - Dependency Inversion Principle
📁 `04-solid/dip/`

- `ProcessadorViolaDIP.java` - Dependência de concreções
- `ProcessadorSegueDIP.java` - Dependência de abstrações

**Como executar:**
```bash
cd 04-solid/dip
javac ProcessadorSegueDIP.java
java ProcessadorSegueDIP
```

## 🔗 Outros Princípios Fundamentais

### 9. Separation of Concerns
📁 `05-separation-of-concerns/`

- `SeparacaoConcerns.java` - Separação clara entre camadas

**Como executar:**
```bash
cd 05-separation-of-concerns
javac SeparacaoConcerns.java
java SeparacaoConcerns
```

### 10. Composition over Inheritance
📁 `06-composition-over-inheritance/`

- `ComposicaoSobreHeranca.java` - Usar composição para maior flexibilidade

**Como executar:**
```bash
cd 06-composition-over-inheritance
javac ComposicaoSobreHeranca.java
java ComposicaoSobreHeranca
```

### 11. Program to Interfaces
📁 `07-program-to-interfaces/`

- `ProgrameParaInterfaces.java` - Depender de interfaces, não implementações

**Como executar:**
```bash
cd 07-program-to-interfaces
javac ProgrameParaInterfaces.java
java ProgrameParaInterfaces
```

### 12. Encapsulate What Varies
📁 `08-encapsulate-what-varies/`

- `EncapsuleOQueVaria.java` - Isolar aspectos que variam

**Como executar:**
```bash
cd 08-encapsulate-what-varies
javac EncapsuleOQueVaria.java
java EncapsuleOQueVaria
```

## 💡 Como Usar Estes Exemplos em Aula

### Para cada princípio:

1. **Mostre o problema primeiro**
   - Execute o arquivo de violação
   - Explique os problemas do código
   - Discuta as consequências

2. **Apresente a solução**
   - Execute o arquivo que segue o princípio
   - Compare com a versão problemática
   - Destaque os benefícios

3. **Exercício prático**
   - Peça aos alunos para identificar violações em código novo
   - Solicite que refatorem código violando princípios
   - Discuta diferentes abordagens de solução

## 📊 Resumo dos Benefícios

| Princípio | Principal Benefício |
|-----------|-------------------|
| **KISS** | Código simples e fácil de entender |
| **YAGNI** | Não desperdiça tempo com código desnecessário |
| **DRY** | Mudanças em um único lugar |
| **SRP** | Cada classe tem uma razão para mudar |
| **OCP** | Extensível sem modificar código existente |
| **LSP** | Subtipos substituíveis sem quebrar comportamento |
| **ISP** | Clientes não dependem de métodos não usados |
| **DIP** | Baixo acoplamento via abstrações |
| **SoC** | Mudanças localizadas em camadas específicas |
| **Composition** | Flexibilidade sem explosão de classes |
| **Interfaces** | Facilita troca de implementações |
| **Encapsulate** | Isola impacto de mudanças |

## 🔍 Relações Entre Princípios

- **KISS + YAGNI + DRY** = Base fundamental para código limpo
- **SOLID** = Estrutura de classes bem projetadas
- **SoC + Composition + Interfaces + Encapsulate** = Arquitetura flexível

Todos os princípios se complementam e reforçam mutuamente!

## ✅ Checklist para Code Review

Ao revisar código, pergunte:

- [ ] O código é simples? (KISS)
- [ ] Implementa apenas o necessário? (YAGNI)
- [ ] Evita duplicação? (DRY)
- [ ] Cada classe tem uma responsabilidade? (SRP)
- [ ] Pode ser estendido sem modificação? (OCP)
- [ ] Subtipos são substituíveis? (LSP)
- [ ] Interfaces são focadas? (ISP)
- [ ] Depende de abstrações? (DIP)
- [ ] Responsabilidades estão separadas? (SoC)
- [ ] Usa composição quando apropriado? (Composition)
- [ ] Programa para interfaces? (Interfaces)
- [ ] Encapsula o que varia? (Encapsulate)

## 📚 Material Adicional

Para mais detalhes sobre cada princípio, incluindo:
- História e contexto acadêmico
- Fundamentação teórica
- Casos de uso avançados
- Referências bibliográficas

Consulte o **README.md** principal na raiz deste diretório.

## 🎓 Para os Alunos

Estes exemplos são projetados para serem:
- ✅ **Executáveis** - Todos os arquivos compilam e rodam
- ✅ **Didáticos** - Comentários explicam cada parte
- ✅ **Comparativos** - Violação vs. Solução lado a lado
- ✅ **Práticos** - Baseados em problemas reais

**Dica**: Execute os exemplos, modifique-os, quebre-os, conserte-os. A melhor forma de aprender é praticando!

---

**Desenvolvido para fins educacionais** - Curso de Graduação em Ciência da Computação 💻✨
