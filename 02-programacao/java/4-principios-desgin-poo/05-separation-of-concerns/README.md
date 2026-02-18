# Separation of Concerns (SoC)
## Separação de Responsabilidades

## 📖 Visão Geral

**Separation of Concerns (SoC)** é um princípio fundamental de design que estabelece que diferentes aspectos de um sistema devem ser separados em módulos distintos, onde cada módulo tem uma responsabilidade clara e bem definida. Este princípio promove a organização do código em camadas ou módulos que abordam preocupações específicas, facilitando manutenção, compreensão e evolução do software.

## 🎯 Definição

> "Separe diferentes aspectos do sistema em módulos distintos, onde cada módulo tem responsabilidade clara e bem definida."

O princípio estabelece que:
- **Aspectos diferentes** do sistema devem estar em **módulos separados**
- Cada **preocupação** (concern) deve ter sua própria **representação modular**
- **Mudanças** em um aspecto não devem afetar outros aspectos não relacionados

## 📚 Origem e História

### Edsger W. Dijkstra (1974)

O conceito foi introduzido por **Edsger W. Dijkstra** em seu artigo "On the role of scientific thought" (1974), onde ele argumentou pela necessidade de separar diferentes aspectos do pensamento computacional.

### David Parnas (1972)

**David Parnas**, em seu influente artigo "On the Criteria to Be Used in Decomposing Systems into Modules" (1972), já havia estabelecido fundamentos conceituais com seu trabalho sobre **Information Hiding** (ocultamento de informação).

### Evolução

- **Aspect-Oriented Programming (AOP)** (1990s): Separação de cross-cutting concerns
- **Arquitetura em Camadas**: Aplicação prática de SoC em nível de sistema
- **MVC, MVVM, Clean Architecture**: Padrões arquiteturais baseados em SoC

## 🔍 O Que São "Concerns" (Preocupações)?

### Exemplos de Concerns:

1. **Lógica de Negócio**: Regras e processamento do domínio
2. **Persistência de Dados**: Acesso a banco de dados
3. **Apresentação/UI**: Interface com usuário
4. **Validação**: Verificação de dados
5. **Logging**: Registro de eventos
6. **Segurança**: Autenticação e autorização
7. **Configuração**: Parâmetros do sistema
8. **Comunicação**: APIs, mensageria

### Concerns devem ser ortogonais
Mudança em um concern não deve afetar outros concerns.

## 🎯 Por Que SoC é Importante?

### 1. **Manutenibilidade**
Mudanças são localizadas em módulos específicos. Mudar formato de relatório não afeta persistência de dados.

### 2. **Compreensibilidade**
Código organizado por concerns é mais fácil de entender e navegar.

### 3. **Reutilização**
Módulos focados podem ser reutilizados em diferentes contextos.

### 4. **Testabilidade**
Cada concern pode ser testado isoladamente.

### 5. **Evolução Independente**
Diferentes concerns podem evoluir em ritmos diferentes sem interferência.

### 6. **Trabalho em Equipe**
Diferentes desenvolvedores podem trabalhar em concerns diferentes simultaneamente.

## ❌ Violação de SoC

### Exemplo: Todos os Concerns Misturados

```java
// ❌ Violação de SoC: UI, lógica de negócio, validação e dados misturados
public class TelaCadastroUsuario extends JFrame {
    private JTextField campoNome;
    private JTextField campoEmail;
    private JButton botaoSalvar;
    
    public TelaCadastroUsuario() {
        // ❌ Concern 1: Configuração de UI
        setTitle("Cadastro de Usuário");
        setSize(400, 300);
        setLayout(new FlowLayout());
        
        campoNome = new JTextField(20);
        campoEmail = new JTextField(20);
        botaoSalvar = new JButton("Salvar");
        
        add(new JLabel("Nome:"));
        add(campoNome);
        add(new JLabel("Email:"));
        add(campoEmail);
        add(botaoSalvar);
        
        // ❌ Concern 2-5: Evento + Validação + Lógica + Persistência misturados
        botaoSalvar.addActionListener(e -> {
            String nome = campoNome.getText();
            String email = campoEmail.getText();
            
            // ❌ Concern 3: Validação misturada com UI
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome obrigatório");
                return;
            }
            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(this, "Email inválido");
                return;
            }
            
            // ❌ Concern 4: Persistência misturada com UI
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://...");
                String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.executeUpdate();
                conn.close();
                
                // ❌ Concern 5: Feedback de UI misturado
                JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
                campoNome.setText("");
                campoEmail.setText("");
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });
    }
}
```

**Problemas:**
- UI, validação, persistência e lógica de negócio estão misturados
- Impossível testar validação sem UI
- Impossível reutilizar validação em outra tela
- Mudança no banco afeta classe de UI
- Mudança na UI pode quebrar lógica de negócio

## ✅ Seguindo SoC: Concerns Bem Separados

```java
// ✅ Concern 1: Modelo - Apenas dados
public class Usuario {
    private String nome;
    private String email;
    
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}

// ✅ Concern 2: Validação - Apenas valida
public class ValidadorUsuario {
    public void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
}

// ✅ Concern 3: Persistência - Apenas acessa dados
public class RepositorioUsuario {
    public void salvar(Usuario usuario) throws SQLException {
        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO usuarios (nome, email) VALUES (?, ?)")) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        }
    }
    
    private Connection obterConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://...");
    }
}

// ✅ Concern 4: Lógica de Negócio/Serviço - Orquestra operações
public class ServicoCadastroUsuario {
    private ValidadorUsuario validador;
    private RepositorioUsuario repositorio;
    
    public ServicoCadastroUsuario() {
        this.validador = new ValidadorUsuario();
        this.repositorio = new RepositorioUsuario();
    }
    
    public void cadastrar(Usuario usuario) throws Exception {
        validador.validar(usuario);
        repositorio.salvar(usuario);
    }
}

// ✅ Concern 5: Apresentação - Apenas UI
public class TelaCadastroUsuario extends JFrame {
    private JTextField campoNome;
    private JTextField campoEmail;
    private JButton botaoSalvar;
    private ServicoCadastroUsuario servico;
    
    public TelaCadastroUsuario() {
        this.servico = new ServicoCadastroUsuario();
        configurarUI();
        configurarEventos();
    }
    
    private void configurarUI() {
        setTitle("Cadastro de Usuário");
        setSize(400, 300);
        setLayout(new FlowLayout());
        
        campoNome = new JTextField(20);
        campoEmail = new JTextField(20);
        botaoSalvar = new JButton("Salvar");
        
        add(new JLabel("Nome:"));
        add(campoNome);
        add(new JLabel("Email:"));
        add(campoEmail);
        add(botaoSalvar);
    }
    
    private void configurarEventos() {
        botaoSalvar.addActionListener(e -> salvarUsuario());
    }
    
    private void salvarUsuario() {
        try {
            Usuario usuario = new Usuario(
                campoNome.getText(),
                campoEmail.getText()
            );
            
            servico.cadastrar(usuario);
            
            exibirSucesso("Usuário salvo com sucesso!");
            limparCampos();
            
        } catch (IllegalArgumentException ex) {
            exibirErro("Validação: " + ex.getMessage());
        } catch (Exception ex) {
            exibirErro("Erro ao salvar: " + ex.getMessage());
        }
    }
    
    private void exibirSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
    
    private void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    private void limparCampos() {
        campoNome.setText("");
        campoEmail.setText("");
    }
}
```

**Benefícios:**
- ✅ Validação testável sem UI ou banco de dados
- ✅ Pode trocar UI (Swing para Web) sem afetar lógica
- ✅ Pode trocar banco de dados sem afetar UI
- ✅ Lógica de negócio reutilizável em contextos diferentes
- ✅ Mudanças em um aspecto não afetam outros

## 📊 Padrões Arquiteturais Baseados em SoC

### 1. **Arquitetura em Camadas (Layered Architecture)**
```
┌─────────────────────────┐
│   Camada de Apresentação │ ← UI, Controllers
├─────────────────────────┤
│   Camada de Negócio     │ ← Lógica de domínio
├─────────────────────────┤
│   Camada de Dados       │ ← Persistência
└─────────────────────────┘
```

### 2. **MVC (Model-View-Controller)**
```
┌───────┐      ┌────────────┐      ┌──────┐
│ View  │ ←──→ │ Controller │ ←──→ │ Model│
└───────┘      └────────────┘      └──────┘
  (UI)         (Lógica)           (Dados)
```

### 3. **Clean Architecture (Arquitetura Limpa)**
```
┌─────────────────────────────────────┐
│     Frameworks & Drivers (UI, DB)  │ ← Externo
├─────────────────────────────────────┤
│     Interface Adapters              │ ← Adaptadores
├─────────────────────────────────────┤
│     Application Business Rules      │ ← Casos de Uso
├─────────────────────────────────────┤
│     Enterprise Business Rules       │ ← Entidades
└─────────────────────────────────────┘
```

### 4. **Hexagonal Architecture (Portas e Adaptadores)**
```
        ┌──────────────┐
        │   Domínio    │
        │  (Negócio)   │
        └──────────────┘
              ↑  ↑
        Portas│  │Portas
              │  │
    ┌─────────┘  └─────────┐
    │                      │
Adaptadores            Adaptadores
 (UI, API)            (BD, Serviços)
```

## 📋 Como Identificar Violações de SoC

### Sinais de Violação:

1. **Código SQL em Classe de UI**
```java
// ❌ UI conhece detalhes de persistência
public class TelaUsuarios extends JFrame {
    public void salvar() {
        String sql = "INSERT INTO..."; // Violação!
    }
}
```

2. **Lógica de Negócio em Controller/Servlet**
```java
// ❌ Controller com lógica de negócio
public class UsuarioController {
    public void cadastrar() {
        // Calcula desconto, valida regras complexas...
        // Deveria estar em camada de serviço!
    }
}
```

3. **HTML/CSS em Código Java**
```java
// ❌ Apresentação misturada com lógica
String html = "<html><body><h1>Relatório</h1>...";
```

4. **Múltiplas Responsabilidades em Uma Classe**
```java
// ❌ Classe faz tudo
public class Usuario {
    public void validar() { }
    public void salvar() { }
    public String renderizarHTML() { }
    public void enviarEmail() { }
}
```

## 📋 Diretrizes Práticas

### 1. **Organize por Camadas**
```
src/
  ├── apresentacao/   (UI, Controllers)
  ├── negocio/        (Serviços, Lógica)
  ├── dominio/        (Entidades, Regras)
  └── persistencia/   (Repositórios, DAOs)
```

### 2. **Cada Camada Comunica Apenas com Adjacentes**
```
Apresentação → Negócio → Persistência
(Não deve: Apresentação → Persistência direta)
```

### 3. **Use DTOs para Transferência entre Camadas**
```java
// DTO para UI
public class UsuarioDTO {
    private String nome;
    private String email;
}

// Entity para persistência
public class UsuarioEntity {
    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;
}
```

### 4. **Dependency Inversion entre Camadas**
```java
// Camada de Negócio define interface
public interface RepositorioUsuarios {
    void salvar(Usuario u);
}

// Camada de Persistência implementa
public class RepositorioUsuariosJDBC implements RepositorioUsuarios {
    // Implementação
}
```

## 🔗 Relação com Outros Princípios

- **SRP (Single Responsibility)**: SoC é SRP em nível de sistema/módulo
- **DIP (Dependency Inversion)**: Ferramenta para alcançar SoC entre camadas
- **OCP (Open/Closed)**: Concerns separados são mais fáceis de estender
- **ISP (Interface Segregation)**: Interfaces focadas por concern

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório:
- `SeparacaoConcerns.java` - Implementação com concerns bem separados

## 🎯 Exercícios Práticos

1. **Identificação**: Encontre código onde concerns estão misturados
2. **Análise**: Liste todos os concerns em uma classe complexa
3. **Refatoração**: Separe concerns em módulos distintos
4. **Arquitetura**: Organize projeto em camadas claras

## 📖 Leituras Recomendadas

1. **"Clean Architecture"** - Robert C. Martin (2017)
2. **"Domain-Driven Design"** - Eric Evans (2003)
3. **"Patterns of Enterprise Application Architecture"** - Martin Fowler (2002)

## 💭 Citações Inspiradoras

> "The separation of concerns is the most important principle in software development." - Edsger W. Dijkstra

> "Let each textual unit do one thing well." - David Parnas

---

**Lembre-se:** Separation of Concerns não é apenas sobre organizar código em pastas. É sobre garantir que cada módulo do sistema tenha uma responsabilidade clara e independente, facilitando compreensão, manutenção e evolução. Diferentes aspectos do sistema devem poder evoluir independentemente.
