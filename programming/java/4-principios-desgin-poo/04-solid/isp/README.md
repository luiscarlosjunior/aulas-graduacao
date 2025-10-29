# ISP - Interface Segregation Principle
## Princípio da Segregação de Interface

## 📖 Visão Geral

O **Interface Segregation Principle (ISP)** estabelece que clientes não devem ser forçados a depender de interfaces que não usam. Em outras palavras, interfaces grandes e "gordas" devem ser divididas em interfaces menores e mais específicas, de modo que os clientes conheçam apenas os métodos que lhes interessam.

## 🎯 Definição

> "Clients should not be forced to depend on interfaces they do not use."
>
> "Clientes não devem ser forçados a depender de interfaces que não usam."
>
> -- Robert C. Martin

**Corolário:**
> "Many client-specific interfaces are better than one general-purpose interface."
>
> "Muitas interfaces específicas para clientes são melhores que uma interface de propósito geral."

## 📚 Origem e História

### Robert C. Martin (1996)

Robert C. Martin formulou ISP em meados dos anos 1990, publicado em seu artigo "The Interface Segregation Principle" no C++ Report em 1996.

### Contexto Original

Martin observou problema em sistemas C++ onde mudanças em interfaces grandes forçavam recompilação de muitos módulos não relacionados. O princípio foi criado para resolver esse problema de acoplamento.

### Aplicação Moderna

Embora o problema de recompilação seja menos crítico em linguagens modernas como Java (com compilação incremental), ISP continua fundamental para design de software limpo e desacoplado.

## 🔍 Entendendo Interfaces "Gordas"

### O Que é Uma Interface "Gorda"?

Uma interface com muitos métodos que servem a múltiplos clientes com necessidades diferentes. Nem todos os implementadores precisam de todos os métodos.

```java
// ❌ Interface "gorda" - muitas responsabilidades
public interface Trabalhador {
    void trabalhar();
    void comer();
    void dormir();
    void receberSalario();
    void tirarFerias();
    void fazerHoraExtra();
    void participarReuniao();
    void preencherPontoEletronico();
}
```

### Problema das Interfaces Gordas:

1. **Dependências Desnecessárias**: Classes dependem de métodos que nunca usam
2. **Implementações Vazias**: Métodos implementados mas não usados
3. **Violação de SRP**: Interface tem múltiplas responsabilidades
4. **Dificulta Manutenção**: Mudanças afetam muitos clientes
5. **Dificulta Compreensão**: Interface complexa e confusa

## 🎯 Por Que ISP é Importante?

### 1. **Reduz Acoplamento**
Clientes dependem apenas do que realmente precisam, reduzindo dependências desnecessárias.

### 2. **Aumenta Coesão**
Interfaces focadas têm responsabilidades claras e coesas.

### 3. **Facilita Manutenção**
Mudanças em interface afetam apenas clientes relevantes.

### 4. **Melhora Testabilidade**
Mocks e stubs são menores e mais simples.

### 5. **Promove Flexibilidade**
Mais fácil implementar interfaces focadas que interfaces grandes.

## ❌ Violação de ISP

### Exemplo Clássico: Interface com Muitas Responsabilidades

```java
// ❌ Interface "gorda" violando ISP
public interface Trabalhador {
    void trabalhar();
    void comer();
    void dormirNoTrabalho();
    void receberSalario();
    void tirarFerias();
    void fazerHoraExtra();
}

// ❌ Robô forçado a implementar métodos que não fazem sentido
public class Robo implements Trabalhador {
    @Override
    public void trabalhar() {
        System.out.println("Robô trabalhando");
    }
    
    @Override
    public void comer() {
        // ❌ Robô não come! Implementação vazia ou exceção?
        throw new UnsupportedOperationException("Robô não come");
    }
    
    @Override
    public void dormirNoTrabalho() {
        // ❌ Robô não dorme!
        throw new UnsupportedOperationException("Robô não dorme");
    }
    
    @Override
    public void receberSalario() {
        // ❌ Robô não recebe salário!
        throw new UnsupportedOperationException("Robô não recebe salário");
    }
    
    @Override
    public void tirarFerias() {
        // ❌ Robô não tira férias!
        throw new UnsupportedOperationException("Robô não tira férias");
    }
    
    @Override
    public void fazerHoraExtra() {
        System.out.println("Robô fazendo hora extra");
    }
}

// ❌ Humano implementa tudo, mas ainda é interface confusa
public class TrabalhadorHumano implements Trabalhador {
    // Implementa todos os métodos...
    // Mas interface mistura muitas responsabilidades
}
```

**Problemas:**
- Robô forçado a "implementar" métodos irrelevantes
- Exceções em runtime indicam design incorreto
- Interface viola SRP (múltiplas responsabilidades)
- Cliente que usa `Trabalhador` pode chamar métodos inválidos para certos tipos

## ✅ Seguindo ISP: Interfaces Segregadas

```java
// ✅ ISP: Interfaces pequenas e focadas

public interface Trabalhavel {
    void trabalhar();
}

public interface Alimentavel {
    void comer();
}

public interface Descansavel {
    void dormirNoTrabalho();
}

public interface Remuneravel {
    void receberSalario();
}

public interface PodeTirarFerias {
    void tirarFerias();
}

public interface PodeFazerHoraExtra {
    void fazerHoraExtra();
}

// ✅ Humano implementa interfaces que fazem sentido
public class TrabalhadorHumano implements Trabalhavel, Alimentavel, 
                                          Descansavel, Remuneravel,
                                          PodeTirarFerias, PodeFazerHoraExtra {
    @Override
    public void trabalhar() {
        System.out.println("Humano trabalhando");
    }
    
    @Override
    public void comer() {
        System.out.println("Humano comendo");
    }
    
    @Override
    public void dormirNoTrabalho() {
        System.out.println("Humano tirando soneca");
    }
    
    @Override
    public void receberSalario() {
        System.out.println("Humano recebendo salário");
    }
    
    @Override
    public void tirarFerias() {
        System.out.println("Humano de férias");
    }
    
    @Override
    public void fazerHoraExtra() {
        System.out.println("Humano fazendo hora extra");
    }
}

// ✅ Robô implementa APENAS interfaces relevantes
public class Robo implements Trabalhavel, PodeFazerHoraExtra {
    @Override
    public void trabalhar() {
        System.out.println("Robô trabalhando 24/7");
    }
    
    @Override
    public void fazerHoraExtra() {
        System.out.println("Robô fazendo hora extra sem reclamar");
    }
    
    // ✅ Não precisa implementar comer, dormir, salário, férias!
}

// ✅ Clientes dependem apenas de interfaces necessárias
public class GerenciadorTrabalho {
    // Depende apenas da interface necessária
    public void atribuirTarefa(Trabalhavel trabalhador) {
        trabalhador.trabalhar();
        // Não tenta chamar comer() ou receberSalario()
    }
}

public class DepartamentoPessoal {
    // Depende apenas de interfaces de RH
    public void processarFolhaPagamento(Remuneravel funcionario) {
        funcionario.receberSalario();
    }
    
    public void gerenciarFerias(PodeTirarFerias funcionario) {
        funcionario.tirarFerias();
    }
}

public class Refeitorio {
    public void servirRefeicao(Alimentavel ser) {
        ser.comer();
        // Funciona com humanos, não aceita robôs
    }
}
```

**Benefícios:**
- ✅ Cada tipo implementa apenas o que faz sentido
- ✅ Sem métodos "vazios" ou exceções em runtime
- ✅ Clientes dependem apenas do necessário
- ✅ Interfaces focadas e coesas

## 📋 Como Identificar Violações de ISP

### Sinais de Violação:

1. **Implementações Vazias**
```java
@Override
public void metodoNaoUsado() {
    // vazio - não faz nada
}
```

2. **UnsupportedOperationException**
```java
@Override
public void metodoNaoSuportado() {
    throw new UnsupportedOperationException();
}
```

3. **Interface com Muitos Métodos**
- Interface com 10+ métodos pode ser candidata a segregação

4. **Clientes Usam Subset da Interface**
```java
// Cliente só usa 2 de 10 métodos disponíveis
public void processar(InterfaceGorda obj) {
    obj.metodo1(); // usa
    obj.metodo2(); // usa
    // Nunca usa metodo3-metodo10
}
```

5. **"Adapters" com Métodos Vazios**
```java
public abstract class AdapterQueImplementaTudo implements InterfaceGorda {
    public void metodo1() {}
    public void metodo2() {}
    // ... todos vazios
}
// Padrão Adapter assim indica interface muito grande
```

### Perguntas-Chave:

```
❓ Todos os implementadores usam todos os métodos?
   → Se NÃO, considere segregar

❓ Clientes dependem de métodos que não usam?
   → Se SIM, viola ISP

❓ Mudança em método afeta clientes que não o usam?
   → Se SIM, viola ISP
```

## 📋 Diretrizes Práticas

### 1. **Interfaces Focadas e Coesas**
```java
// ✅ Interface focada
public interface Persistivel {
    void salvar();
    void atualizar();
    void deletar();
}

// ✅ Interface focada
public interface Validavel {
    boolean validar();
}

// Melhor que interface gorda com ambos
```

### 2. **Role Interfaces**
Crie interfaces baseadas em papéis/roles que objetos desempenham:

```java
public interface Pagavel {
    void processarPagamento();
}

public interface Notificavel {
    void notificar(String mensagem);
}

public interface Auditavel {
    void registrarAuditoria();
}
```

### 3. **Interface Composition**
Quando necessário, componha interfaces pequenas:

```java
// Interface composta de interfaces menores
public interface PedidoCompleto extends Validavel, Persistivel, Notificavel {
    // Combina comportamentos quando todos são necessários
}
```

### 4. **Granularidade Apropriada**
```java
// ❌ Muito granular
public interface Getable { Object get(); }
public interface Setable { void set(Object o); }

// ✅ Granularidade apropriada
public interface Repositorio<T> {
    T buscar(Long id);
    void salvar(T entidade);
}
```

## ⚖️ ISP e Pragmatismo

### Evite Granularidade Excessiva

Não crie interface para cada método individual:

```java
// ❌ Excessivamente segregado
public interface PodeLer { }
public interface PodeEscrever { }
public interface PodeDeletar { }
public interface PodeAtualizar { }

// ✅ Balanceado - CRUD é uma responsabilidade coesa
public interface Repositorio {
    void criar(T obj);
    T ler(Long id);
    void atualizar(T obj);
    void deletar(Long id);
}
```

### Contexto Importa

Se todos os clientes sempre usam todos os métodos, interface não está "gorda" - está coesa.

### Refatoração Evolutiva

```
1. Comece com interface razoável
2. ↓
3. Identifique clientes usando subsets diferentes
4. ↓
5. Segregue interface quando padrão claro emergir
```

## 🔗 Relação com Outros Princípios SOLID

- **SRP**: ISP é aplicação de SRP a interfaces
- **LSP**: Interfaces segregadas facilitam substituibilidade
- **DIP**: Inversão de dependência funciona melhor com interfaces focadas
- **OCP**: Interfaces segregadas facilitam extensão

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório que demonstram interfaces gordas vs. segregadas.

## 🎯 Exercícios Práticos

1. **Análise**: Revise interfaces em seu código - quão grandes são?
2. **Uso**: Para cada interface, veja se todos os implementadores usam todos os métodos
3. **Clientes**: Identifique se clientes usam apenas subset da interface
4. **Refatoração**: Segregue interface grande em interfaces menores e focadas

## 📖 Leituras Recomendadas

1. **"Agile Software Development"** - Robert C. Martin (2002) - ISP explicado
2. **"The Interface Segregation Principle"** - Robert C. Martin (artigo original, 1996)
3. **"Clean Code"** - Robert C. Martin (2008) - Interfaces e abstrações

## 💭 Citações Inspiradoras

> "Fat interfaces lead to inadvertent coupling between classes that ought to be isolated." - Robert C. Martin

> "The interface segregation principle teaches us to keep our interfaces focused and cohesive." - Robert C. Martin

---

**Lembre-se:** ISP não é sobre ter interfaces com um único método. É sobre não forçar clientes a depender de métodos que não usam. Interfaces devem ser focadas e coesas, servindo necessidades específicas de seus clientes, não tentando servir a todos de uma vez.
