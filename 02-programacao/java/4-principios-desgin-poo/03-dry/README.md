# DRY - Don't Repeat Yourself

## 📖 Visão Geral

**DRY** (Don't Repeat Yourself - Não Se Repita) é um princípio fundamental de desenvolvimento de software que estabelece que "cada pedaço de conhecimento deve ter uma representação única, não ambígua e autoritativa dentro de um sistema". O princípio vai além de simplesmente evitar código duplicado - trata-se de garantir que conhecimento e lógica de negócio tenham uma única fonte de verdade.

## 🎯 Definição

> "Every piece of knowledge must have a single, unambiguous, authoritative representation within a system."
>
> "Cada pedaço de conhecimento deve ter uma representação única, não ambígua e autoritativa dentro de um sistema."
>
> -- Andrew Hunt e David Thomas, The Pragmatic Programmer

## 📚 Origem e História

### Formulação Original

DRY foi formulado explicitamente por **Andrew Hunt** e **David Thomas** no livro **"The Pragmatic Programmer: From Journeyman to Master"** (1999).

### Contexto Histórico

Hunt e Thomas observaram que duplicação é uma das principais causas de problemas de manutenção em software. Eles formalizaram DRY como parte de um conjunto mais amplo de práticas pragmáticas de desenvolvimento.

### Influências Históricas
- **David Parnas** (1972): "Information Hiding" - ocultamento de informação para reduzir acoplamento
- **Structured Programming**: Eliminação de código duplicado através de sub-rotinas e funções
- **Code Reusability Movement**: Movimento dos anos 1980-1990 enfatizando reutilização de código

## 🔍 Por Que DRY é Importante?

### 1. **Facilita Manutenção**
Quando lógica está duplicada e precisa mudar, você deve encontrar e modificar **todos** os lugares onde ela aparece. Perder um lugar causa inconsistência e bugs.

### 2. **Reduz Bugs**
Bugs em código duplicado são multiplicados. Um defeito em lógica duplicada em 5 lugares = 5 bugs para corrigir.

### 3. **Melhora Consistência**
Uma única fonte de verdade garante que todos os pontos do sistema aplicam a mesma lógica da mesma forma.

### 4. **Economiza Tempo**
Mudanças são feitas em um único lugar, economizando tempo de desenvolvimento e testes.

### 5. **Facilita Testes**
Lógica centralizada pode ser testada uma vez de forma abrangente, em vez de múltiplos testes parciais.

## 💰 Custos da Duplicação

### Custos Diretos:
1. **Manutenção**: Mudança deve ser propagada em múltiplos lugares
2. **Inconsistência**: Versões divergentes criam bugs sutis
3. **Teste**: Mesma lógica precisa ser testada múltiplas vezes
4. **Debugging**: Bugs podem existir em algumas cópias mas não em outras

### Custos Indiretos:
1. **Carga Cognitiva**: Desenvolvedores precisam rastrear múltiplas versões
2. **Refatoração**: Mudanças arquiteturais são mais difíceis com código duplicado
3. **Onboarding**: Novos desenvolvedores levam mais tempo para entender sistema inconsistente

### Pesquisa Empírica:
- Duplicação de código correlaciona com maior densidade de defeitos (Juergens et al., 2009)
- Code clones são responsáveis por 10-20% dos bugs (estudo Microsoft)
- Refatoração para eliminar duplicação melhora manutenibilidade significativamente

## 📊 Tipos de Duplicação

### 1. **Duplicação Imposta**
Ambiente ou requisitos parecem forçar duplicação (mas geralmente há solução melhor).

**Exemplo:** Validação em frontend e backend
```java
// ❌ Duplicação: Regra "idade mínima 18" repetida
// Backend (Java)
if (usuario.getIdade() < 18) {
    throw new Exception("Idade mínima: 18 anos");
}

// Frontend (JavaScript)  
if (idade < 18) {
    alert("Idade mínima: 18 anos");
}

// ✅ Solução DRY: Expor regra via API
public static final int IDADE_MINIMA = 18;

@GetMapping("/api/config/idade-minima")
public int getIdadeMinima() {
    return IDADE_MINIMA;
}
```

### 2. **Duplicação Inadvertida**
Desenvolvedores não percebem que estão duplicando conhecimento existente.

**Exemplo:** Cálculo de desconto implementado em múltiplos lugares sem perceber.

### 3. **Duplicação por Impaciência**
Copiar e colar código porque parece "mais rápido" que refatorar.

**Exemplo:** Ctrl+C, Ctrl+V de método e modificar ligeiramente.

### 4. **Duplicação Inter-Desenvolvedores**
Múltiplos desenvolvedores implementam mesma funcionalidade independentemente.

**Exemplo:** Dois desenvolvedores criam validadores de email diferentes.

## ✅ Manifestações Práticas do DRY

### 1. **Extrair Métodos Comuns**
```java
// ❌ Lógica duplicada
public double calcularPrecoClienteRegular(double preco, int qtd) {
    double desconto = 0;
    if (qtd > 100) desconto = 0.15;
    else if (qtd > 50) desconto = 0.10;
    else if (qtd > 10) desconto = 0.05;
    return preco * qtd * (1 - desconto);
}

public double calcularPrecoClienteVIP(double preco, int qtd) {
    double desconto = 0;
    if (qtd > 100) desconto = 0.15; // DUPLICADO!
    else if (qtd > 50) desconto = 0.10;
    else if (qtd > 10) desconto = 0.05;
    desconto += 0.05; // Desconto adicional VIP
    return preco * qtd * (1 - desconto);
}

// ✅ Seguindo DRY
public double calcularDescontoPorQuantidade(int qtd) {
    if (qtd > 100) return 0.15;
    else if (qtd > 50) return 0.10;
    else if (qtd > 10) return 0.05;
    return 0.0;
}

public double calcularPrecoClienteRegular(double preco, int qtd) {
    double desconto = calcularDescontoPorQuantidade(qtd);
    return preco * qtd * (1 - desconto);
}

public double calcularPrecoClienteVIP(double preco, int qtd) {
    double desconto = calcularDescontoPorQuantidade(qtd) + 0.05;
    return preco * qtd * (1 - desconto);
}
```

### 2. **Centralizar Validações**
```java
// ❌ Validação duplicada em múltiplos lugares
public void cadastrarUsuario(String email, String senha) {
    if (email == null || !email.contains("@")) {
        throw new IllegalArgumentException("Email inválido");
    }
    if (senha == null || senha.length() < 8) {
        throw new IllegalArgumentException("Senha inválida");
    }
    // ...
}

public void atualizarEmail(Long userId, String novoEmail) {
    if (novoEmail == null || !novoEmail.contains("@")) { // DUPLICADO!
        throw new IllegalArgumentException("Email inválido");
    }
    // ...
}

// ✅ Seguindo DRY
public class ValidadorCredenciais {
    public void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
    
    public void validarSenha(String senha) {
        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException("Senha inválida");
        }
    }
}

// Uso reutilizável
private ValidadorCredenciais validador = new ValidadorCredenciais();

public void cadastrarUsuario(String email, String senha) {
    validador.validarEmail(email);
    validador.validarSenha(senha);
    // ...
}

public void atualizarEmail(Long userId, String novoEmail) {
    validador.validarEmail(novoEmail);
    // ...
}
```

### 3. **Single Source of Truth para Constantes**
```java
// ❌ Valores "mágicos" duplicados
public void validarIdade(int idade) {
    if (idade < 18) { // "18" aparece em múltiplos lugares
        throw new Exception("Idade mínima é 18");
    }
}

// ... em outro lugar
@Test
public void testeIdadeMinima() {
    assertThrows(Exception.class, () -> {
        validarIdade(17); // 17 porque 18 é o mínimo
    });
}

// ✅ Seguindo DRY
public class RegrasUsuario {
    public static final int IDADE_MINIMA = 18;
    
    public static String getMensagemIdadeMinima() {
        return "Idade mínima é " + IDADE_MINIMA;
    }
}

public void validarIdade(int idade) {
    if (idade < RegrasUsuario.IDADE_MINIMA) {
        throw new Exception(RegrasUsuario.getMensagemIdadeMinima());
    }
}

@Test
public void testeIdadeMinima() {
    assertThrows(Exception.class, () -> {
        validarIdade(RegrasUsuario.IDADE_MINIMA - 1);
    });
}
```

## ❌ Violações Comuns do DRY

### 1. **Copy-Paste Programming**
Copiar código e modificar ligeiramente para nova situação.

### 2. **Múltiplas Versões da Mesma Lógica**
Mesma regra de negócio implementada diferentemente em lugares diferentes.

### 3. **Magic Numbers**
Valores hard-coded repetidos sem constantes nomeadas.

### 4. **Validações Espalhadas**
Mesmas validações duplicadas em múltiplas camadas ou classes.

### 5. **Documentação Duplicada**
Comentários que repetem o que o código já expressa claramente.

## 📋 Regra de Três (Rule of Three)

**Guideline Prática:**
```
Primeira vez: Escreva código inline
Segunda vez: Note similaridade, mas tolere duplicação
Terceira vez: Refatore e elimine duplicação

Razão: Duas instâncias podem ser coincidência
Três instâncias indicam padrão real que merece abstração
```

## ⚠️ Quando NÃO Aplicar DRY

DRY tem limites. Há situações onde duplicação é aceitável ou até preferível:

### 1. **Duplicação Acidental vs Essencial**
```java
// Aparentemente duplicado, mas conceitos DIFERENTES
public class Pedido {
    public double calcularTotal() {
        // Total do pedido: itens + frete
        return somarItens() + calcularFrete();
    }
}

public class Orcamento {
    public double calcularTotal() {
        // Total do orçamento: apenas itens (sem frete)
        return somarItens();
    }
}

// ❌ NÃO extrair para método comum só porque nome é igual
// Conceitos são diferentes: total de pedido ≠ total de orçamento
// ✅ Manter separado: conceitos podem evoluir independentemente
```

### 2. **Duplicação entre Camadas/Módulos**
```java
// DTO (Data Transfer Object)
public class UsuarioDTO {
    private String nome;
    private String email;
}

// Entity (Domínio/Persistência)
public class UsuarioEntity {
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;
    private String senhaCriptografada;
}

// ✅ Duplicação aceitável: Camadas devem ser independentes
// DTO muda por razões diferentes de Entity
```

### 3. **Testes**
```java
// ✅ Duplicação em testes é muitas vezes aceitável
@Test
public void deveCalcularTotalCorretamente() {
    Pedido pedido = new Pedido();
    pedido.adicionarItem(new Item("Produto A", 100.0));
    pedido.adicionarItem(new Item("Produto B", 50.0));
    
    assertEquals(150.0, pedido.calcularTotal());
}

@Test
public void deveAplicarDesconto() {
    // Setup duplicado mas explícito - facilita entendimento
    Pedido pedido = new Pedido();
    pedido.adicionarItem(new Item("Produto A", 100.0));
    pedido.adicionarItem(new Item("Produto B", 50.0));
    pedido.setDesconto(0.10);
    
    assertEquals(135.0, pedido.calcularTotal());
}

// Testes devem ser auto-contidos e legíveis
// Abstrair setup pode dificultar compreensão
```

### 4. **Código Temporário ou Protótipo**
Durante prototipagem rápida, alguma duplicação pode ser tolerada temporariamente.

## 🎓 DRY em Diferentes Níveis

### Nível de Método
```java
private double aplicarImposto(double valor) {
    final double TAXA_IMPOSTO = 0.18;
    return valor * (1 + TAXA_IMPOSTO);
}
```

### Nível de Classe
```java
public abstract class ProcessadorBase {
    protected void validarDados(Dados dados) {
        // Validação comum reutilizada por subclasses
    }
}
```

### Nível de Sistema
```java
public class ServicoEmail {
    // Centraliza toda lógica de envio de email
    // Reutilizado por todo o sistema
}
```

## 🔗 Relação com Outros Princípios

- **Single Responsibility Principle (SRP)**: DRY + SRP = classes coesas com única fonte de verdade
- **Open/Closed Principle (OCP)**: DRY facilita extensão ao centralizar lógica
- **KISS**: Trabalham juntos - código simples sem duplicação

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório:
- `CalculadoraDesconto.java` - Implementação seguindo DRY
- `SistemaVendasComDuplicacao.java` - Exemplo de violação com código duplicado

## 🎯 Exercícios Práticos

1. **Identificação**: Procure código duplicado em seus projetos usando ferramentas de análise
2. **Refatoração**: Pratique extrair código duplicado em métodos/classes reutilizáveis
3. **Prevenção**: Antes de copiar código, pergunte-se: "Como posso reutilizar isto?"

## 📖 Leituras Recomendadas

1. **"The Pragmatic Programmer"** - Hunt & Thomas (1999) - Formulação original de DRY
2. **"Refactoring"** - Martin Fowler (1999) - Técnicas para eliminar duplicação
3. **"Clean Code"** - Robert C. Martin (2008) - Código limpo e sem duplicação
4. **"Code Complete"** - Steve McConnell (2004) - Práticas de código de qualidade

## 💭 Citações Inspiradoras

> "Duplication is the primary enemy of a well-designed system." - Robert C. Martin

> "Copy and paste is a design error." - David Parnas

> "Don't repeat yourself. It's not just about code, it's about knowledge and intent." - Andrew Hunt

---

**Lembre-se:** DRY não é apenas sobre evitar código duplicado - é sobre garantir que cada pedaço de conhecimento no sistema tenha uma única, clara e autoritativa representação. Quando você muda uma regra de negócio, deve haver apenas um lugar para mudar.
