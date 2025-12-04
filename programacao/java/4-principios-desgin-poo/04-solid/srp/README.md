# SRP - Single Responsibility Principle
## Princípio da Responsabilidade Única

## 📖 Visão Geral

O **Single Responsibility Principle (SRP)** estabelece que uma classe deve ter apenas uma razão para mudar - ou seja, deve ter apenas uma responsabilidade. Este é o primeiro princípio dos SOLID e um dos mais fundamentais para design de software de qualidade.

## 🎯 Definição

> "A class should have only one reason to change."
>
> "Uma classe deve ter apenas uma razão para mudar."
>
> -- Robert C. Martin

**Refinamento posterior:**
> "Gather together the things that change for the same reasons. Separate those things that change for different reasons."
>
> "Agrupe as coisas que mudam pelas mesmas razões. Separe as coisas que mudam por razões diferentes."

## 📚 Origem e História

### Conceito Histórico

O conceito de coesão modular e responsabilidade única tem raízes em:
- **David Parnas** (1972): "On the Criteria to Be Used in Decomposing Systems into Modules" - decomposição de sistemas em módulos coesos
- **Tom DeMarco** (1978): "Structured Analysis" - conceitos de coesão funcional

### Formulação por Robert C. Martin

Robert C. Martin formalizou SRP como parte dos princípios SOLID nos anos 1990, publicando extensivamente sobre o tema em artigos e livros.

## 🔍 O Que é "Uma Responsabilidade"?

### Responsabilidade = Razão para Mudar

Uma responsabilidade é um eixo de mudança. Se você pode pensar em mais de um motivo para mudar uma classe, então ela tem mais de uma responsabilidade.

**Exemplos de razões para mudar:**
- Mudança em regras de negócio
- Mudança em formato de relatório
- Mudança em tecnologia de persistência
- Mudança em validação de dados
- Mudança em formato de comunicação

### Alta Coesão

SRP promove **alta coesão** - elementos que pertencem juntos estão agrupados, elementos que não pertencem estão separados.

## 🎯 Por Que SRP é Importante?

### 1. **Manutenibilidade**
Mudanças são localizadas. Se precisar modificar como relatórios são gerados, você sabe exatamente onde ir - na classe de relatórios.

### 2. **Testabilidade**
Classes com responsabilidade única são mais fáceis de testar. Você pode testar cada responsabilidade isoladamente.

### 3. **Compreensibilidade**
Propósito claro facilita entendimento. Quando você abre uma classe, sabe imediatamente o que ela faz.

### 4. **Reutilização**
Classes focadas são mais reutilizáveis em diferentes contextos.

### 5. **Redução de Acoplamento**
Menos responsabilidades = menos razões para depender da classe = menor acoplamento.

## ❌ Violações Comuns do SRP

### God Class (Classe Deus)
Classe que faz tudo - modelo, validação, persistência, apresentação, notificação...

```java
// ❌ Violação: Classe com MÚLTIPLAS responsabilidades
public class Usuario {
    private String nome;
    private String email;
    
    // Responsabilidade 1: Representar dados
    // Responsabilidade 2: Validar
    public boolean validar() {
        if (nome == null || email == null) return false;
        return email.contains("@");
    }
    
    // Responsabilidade 3: Persistir
    public void salvar() {
        String sql = "INSERT INTO usuarios...";
        // código SQL direto
    }
    
    // Responsabilidade 4: Enviar email
    public void enviarEmailBoasVindas() {
        // código de envio de email
    }
    
    // Responsabilidade 5: Gerar relatório
    public String gerarRelatorio() {
        return "Relatório do usuário...";
    }
}
```

**Problemas:**
- Mudança em banco de dados afeta classe de modelo
- Mudança em validação afeta classe de modelo
- Mudança em email afeta classe de modelo
- Classe tem 5 razões para mudar!

## ✅ Seguindo SRP: Responsabilidades Separadas

```java
// ✅ Responsabilidade 1: Apenas representar dados
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    // Apenas getters e setters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}

// ✅ Responsabilidade 2: Apenas validar
public class ValidadorUsuario {
    public void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}

// ✅ Responsabilidade 3: Apenas persistir
public class RepositorioUsuario {
    public void salvar(Usuario usuario) {
        // Lógica de persistência
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        // ...
    }
    
    public Usuario buscar(Long id) {
        // Lógica de busca
        return null; // implementação
    }
}

// ✅ Responsabilidade 4: Apenas notificar
public class NotificadorUsuario {
    public void enviarBoasVindas(Usuario usuario) {
        String assunto = "Bem-vindo!";
        String mensagem = "Olá " + usuario.getNome();
        // Lógica de envio de email
    }
}

// ✅ Responsabilidade 5: Apenas gerar relatórios
public class RelatorioUsuario {
    public String gerar(Usuario usuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("====== RELATÓRIO ======\n");
        sb.append("Nome: ").append(usuario.getNome()).append("\n");
        sb.append("Email: ").append(usuario.getEmail()).append("\n");
        return sb.toString();
    }
}

// ✅ Serviço orquestra operações (também tem apenas uma responsabilidade)
public class ServicoCadastroUsuario {
    private ValidadorUsuario validador;
    private RepositorioUsuario repositorio;
    private NotificadorUsuario notificador;
    
    public ServicoCadastroUsuario(
        ValidadorUsuario validador,
        RepositorioUsuario repositorio,
        NotificadorUsuario notificador
    ) {
        this.validador = validador;
        this.repositorio = repositorio;
        this.notificador = notificador;
    }
    
    public void cadastrar(Usuario usuario) {
        validador.validar(usuario);
        repositorio.salvar(usuario);
        notificador.enviarBoasVindas(usuario);
    }
}
```

**Benefícios:**
- Mudança em validação? Altere apenas `ValidadorUsuario`
- Mudança em persistência? Altere apenas `RepositorioUsuario`
- Mudança em notificação? Altere apenas `NotificadorUsuario`
- Cada classe tem UMA razão para mudar

## 📋 Como Identificar Violações de SRP

### Perguntas-Chave:

1. **Quantas razões para mudar?**
   - Se mais de uma, provavelmente viola SRP

2. **A classe pode ser descrita com "E"?**
   - "Esta classe gerencia usuários E envia emails E gera relatórios"
   - Se tem "E", provavelmente viola SRP

3. **Quantos grupos de métodos existem?**
   - Métodos de validação + métodos de persistência + métodos de UI
   - Se múltiplos grupos, provavelmente viola SRP

4. **Quem usaria esta classe?**
   - Se múltiplos stakeholders (DBA, UI designer, regras de negócio)
   - Provavelmente viola SRP

### Sinais de Violação:

- Classe com muitas dependências (imports)
- Classe muito grande (> 200-300 linhas)
- Métodos que não usam fields da classe
- Dificuldade em dar nome claro à classe
- Testes difíceis de escrever

## 📋 Diretrizes Práticas

### 1. **Nomeie Classes com Propósito Claro**
```java
// ❌ Nome vago
public class UserManager { }
public class DataProcessor { }

// ✅ Nome específico
public class UserValidator { }
public class UserRepository { }
public class UserNotifier { }
```

### 2. **Mantenha Classes Pequenas**
- Não há número mágico, mas se está com 500+ linhas, provavelmente faz demais
- Regra prática: 100-200 linhas por classe

### 3. **Use Composição**
Em vez de uma classe grande fazendo tudo, componha várias classes focadas:
```java
public class ServicoPedido {
    private ValidadorPedido validador;
    private CalculadoraPreco calculadora;
    private RepositorioPedidos repositorio;
    private NotificadorCliente notificador;
    
    // Orquestra componentes focados
}
```

### 4. **Separe Camadas**
- **Modelo**: Apenas dados
- **Validação**: Apenas regras de validação
- **Persistência**: Apenas acesso a dados
- **Apresentação**: Apenas formatação para exibição
- **Coordenação**: Apenas orquestração

## ⚖️ SRP e Pragmatismo

### Quando Combinar Responsabilidades?

Em sistemas muito simples ou protótipos, separação rigorosa pode ser over-engineering:

```java
// Para CRUD simples, pode ser aceitável:
public class UsuarioDAO {
    public void criar(Usuario u) { }
    public Usuario ler(Long id) { }
    public void atualizar(Usuario u) { }
    public void deletar(Long id) { }
    
    // Todas são operações de persistência - uma responsabilidade conceitual
}
```

### Evite Granularidade Excessiva

```java
// ❌ SRP levado ao extremo
public class GetterNome { String get(Usuario u) { return u.nome; } }
public class SetterNome { void set(Usuario u, String n) { u.nome = n; } }
public class GetterEmail { String get(Usuario u) { return u.email; } }
public class SetterEmail { void set(Usuario u, String e) { u.email = e; } }

// Classes muito pequenas sem benefício real
```

**Regra de Ouro:** Use bom senso. SRP visa facilitar manutenção, não criar burocracia.

## 🔗 Relação com Outros Princípios SOLID

- **OCP**: Classes com responsabilidade única são mais fáceis de estender
- **LSP**: Subclasses com responsabilidade clara são mais substituíveis
- **ISP**: Interfaces segregadas são aplicação de SRP a interfaces
- **DIP**: Facilita inversão quando responsabilidades estão bem definidas

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório:
- `UsuarioSegueSRP.java` - Implementação com responsabilidades bem separadas
- `UsuarioViolaSRP.java` - Exemplo de violação com múltiplas responsabilidades

## 🎯 Exercícios Práticos

1. **Identificação**: Encontre classes em seu código que violam SRP
2. **Contagem**: Para cada classe, liste todas as razões possíveis para mudá-la
3. **Refatoração**: Separe uma classe com múltiplas responsabilidades
4. **Teste**: Compare testabilidade antes e depois da separação

## 📖 Leituras Recomendadas

1. **"Clean Code"** - Robert C. Martin (2008) - Capítulos sobre classes e responsabilidades
2. **"Agile Software Development"** - Robert C. Martin (2002) - Explicação detalhada de SRP
3. **"The Single Responsibility Principle"** - Robert C. Martin (artigo original)

## 💭 Citações Inspiradoras

> "The single responsibility principle is about people." - Robert C. Martin

> "A class should have only one reason to change, and that reason should be the responsibility of a single actor." - Robert C. Martin

---

**Lembre-se:** SRP não é sobre ter apenas um método por classe. É sobre ter apenas uma razão para mudar - uma responsabilidade coesa. Classes pequenas e focadas são mais fáceis de entender, testar, manter e reutilizar.
