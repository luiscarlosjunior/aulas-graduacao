import java.util.*;
import java.util.stream.Collectors;

/**
 * Sistema Escolar - Demonstração Avançada de Collections com POO
 * 
 * Este exemplo mostra um sistema completo integrando Collections Framework
 * com conceitos avançados de Programação Orientada a Objetos:
 * 
 * Conceitos POO Demonstrados:
 * - Encapsulamento: Dados privados com acesso controlado
 * - Herança: Professor herda de Pessoa
 * - Polimorfismo: Coleções polimórficas de Pessoa
 * - Composição: Escola "possui" alunos, professores e disciplinas
 * - Agregação: Turma "usa" alunos e professor que existem independentemente
 * - Abstração: Interfaces claras para operações complexas
 * 
 * Padrões de Design:
 * - Repository Pattern: Gerenciamento de dados
 * - Observer Pattern: Notificações de mudanças
 * - Strategy Pattern: Diferentes critérios de busca/ordenação
 * 
 * @author Aulas Graduação
 */
public class SistemaEscolar {
    
    public static void main(String[] args) {
        System.out.println("🏫 SISTEMA ESCOLAR - COLLECTIONS + POO AVANÇADO\n");
        
        // Criando a escola
        Escola escola = new Escola("Instituto de Tecnologia ABC");
        
        demonstrarCadastroInicial(escola);
        System.out.println();
        
        demonstrarRelacionamentos(escola);
        System.out.println();
        
        demonstrarConsultas(escola);
        System.out.println();
        
        demonstrarRelatorios(escola);
        System.out.println();
        
        demonstrarOperacoesAvancadas(escola);
    }
    
    private static void demonstrarCadastroInicial(Escola escola) {
        System.out.println("📋 CADASTRO INICIAL - ENCAPSULAMENTO E COMPOSIÇÃO");
        
        // Cadastrando professores (Herança: Professor extends Pessoa)
        Professor prof1 = new Professor("Dr. João Silva", 45, "joao@escola.com", "Matemática", 15);
        Professor prof2 = new Professor("Dra. Maria Santos", 38, "maria@escola.com", "Programação", 10);
        Professor prof3 = new Professor("Dr. Carlos Lima", 42, "carlos@escola.com", "Banco de Dados", 12);
        
        escola.adicionarProfessor(prof1);
        escola.adicionarProfessor(prof2);
        escola.adicionarProfessor(prof3);
        
        // Cadastrando alunos
        escola.adicionarAluno(new Aluno("Ana Costa", 20, "ana@aluno.com", "202301001"));
        escola.adicionarAluno(new Aluno("Bruno Oliveira", 19, "bruno@aluno.com", "202301002"));
        escola.adicionarAluno(new Aluno("Carla Santos", 21, "carla@aluno.com", "202301003"));
        escola.adicionarAluno(new Aluno("Diego Ferreira", 18, "diego@aluno.com", "202301004"));
        escola.adicionarAluno(new Aluno("Elena Silva", 22, "elena@aluno.com", "202301005"));
        
        // Criando disciplinas
        escola.adicionarDisciplina(new Disciplina("MAT101", "Cálculo I", prof1, 60));
        escola.adicionarDisciplina(new Disciplina("PRG101", "Programação Java", prof2, 80));
        escola.adicionarDisciplina(new Disciplina("BD101", "Fundamentos de BD", prof3, 60));
        
        System.out.println("✅ Cadastros realizados:");
        System.out.println("   🧑‍🏫 Professores: " + escola.getTotalProfessores());
        System.out.println("   🎓 Alunos: " + escola.getTotalAlunos());
        System.out.println("   📚 Disciplinas: " + escola.getTotalDisciplinas());
    }
    
    private static void demonstrarRelacionamentos(Escola escola) {
        System.out.println("🔗 RELACIONAMENTOS ENTRE OBJETOS - AGREGAÇÃO");
        
        // Matriculando alunos em disciplinas (Relacionamento N:M)
        Disciplina calc = escola.buscarDisciplinaPorCodigo("MAT101");
        Disciplina java = escola.buscarDisciplinaPorCodigo("PRG101");
        Disciplina bd = escola.buscarDisciplinaPorCodigo("BD101");
        
        // Ana se matricula em todas as disciplinas
        Aluno ana = escola.buscarAlunoPorMatricula("202301001");
        ana.matricularDisciplina(calc);
        ana.matricularDisciplina(java);
        ana.matricularDisciplina(bd);
        
        // Bruno se matricula em Java e BD
        Aluno bruno = escola.buscarAlunoPorMatricula("202301002");
        bruno.matricularDisciplina(java);
        bruno.matricularDisciplina(bd);
        
        // Carla se matricula em Cálculo e Java
        Aluno carla = escola.buscarAlunoPorMatricula("202301003");
        carla.matricularDisciplina(calc);
        carla.matricularDisciplina(java);
        
        // Diego apenas em Java
        Aluno diego = escola.buscarAlunoPorMatricula("202301004");
        diego.matricularDisciplina(java);
        
        // Elena em BD
        Aluno elena = escola.buscarAlunoPorMatricula("202301005");
        elena.matricularDisciplina(bd);
        
        System.out.println("📊 Matrículas realizadas:");
        System.out.println("   📚 " + calc.getNome() + ": " + calc.getTotalAlunos() + " alunos");
        System.out.println("   💻 " + java.getNome() + ": " + java.getTotalAlunos() + " alunos");
        System.out.println("   🗄️ " + bd.getNome() + ": " + bd.getTotalAlunos() + " alunos");
        
        // Demonstrando relacionamento bidirecional
        System.out.println("\n🔄 Relacionamento bidirecional:");
        System.out.println("   Aluno Ana está matriculado em " + ana.getDisciplinas().size() + " disciplinas");
        System.out.println("   Disciplina Java possui " + java.getAlunos().size() + " alunos matriculados");
    }
    
    private static void demonstrarConsultas(Escola escola) {
        System.out.println("🔍 CONSULTAS E FILTROS - STREAM API + POO");
        
        // Busca por critérios usando Stream API
        System.out.println("👨‍🎓 Alunos maiores de 20 anos:");
        escola.getAlunos().stream()
              .filter(aluno -> aluno.getIdade() > 20)
              .sorted(Comparator.comparing(PessoaEscolar::getNome))
              .forEach(aluno -> System.out.println("   • " + aluno.getNome() + 
                       " (" + aluno.getIdade() + " anos, matrícula: " + aluno.getMatricula() + ")"));
        
        // Professores com mais de 10 anos de experiência
        System.out.println("\n👨‍🏫 Professores experientes (>10 anos):");
        escola.getProfessores().stream()
              .filter(prof -> prof.getAnosExperiencia() > 10)
              .forEach(prof -> System.out.println("   • " + prof.getNome() + 
                       " - " + prof.getEspecialidade() + " (" + prof.getAnosExperiencia() + " anos)"));
        
        // Disciplinas com mais de 2 alunos
        System.out.println("\n📚 Disciplinas populares (>2 alunos):");
        escola.getDisciplinas().stream()
              .filter(disc -> disc.getTotalAlunos() > 2)
              .sorted((d1, d2) -> Integer.compare(d2.getTotalAlunos(), d1.getTotalAlunos()))
              .forEach(disc -> System.out.println("   • " + disc.getNome() + 
                       " (" + disc.getTotalAlunos() + " alunos) - Prof. " + 
                       disc.getProfessor().getNome()));
    }
    
    private static void demonstrarRelatorios(Escola escola) {
        System.out.println("📊 RELATÓRIOS ESTATÍSTICOS - AGREGAÇÃO DE DADOS");
        
        // Estatísticas gerais
        double idadeMediaAlunos = escola.getAlunos().stream()
                                       .mapToInt(PessoaEscolar::getIdade)
                                       .average()
                                       .orElse(0.0);
        
        double idadeMediaProfessores = escola.getProfessores().stream()
                                            .mapToInt(PessoaEscolar::getIdade)
                                            .average()
                                            .orElse(0.0);
        
        System.out.println("📈 Estatísticas Gerais:");
        System.out.println("   🎓 Idade média dos alunos: " + String.format("%.1f", idadeMediaAlunos) + " anos");
        System.out.println("   👨‍🏫 Idade média dos professores: " + String.format("%.1f", idadeMediaProfessores) + " anos");
        
        // Relatório de matrículas por disciplina
        System.out.println("\n📋 Relatório de Matrículas:");
        Map<String, Long> matriculasPorDisciplina = escola.getDisciplinas().stream()
            .collect(Collectors.toMap(
                Disciplina::getNome,
                disc -> (long) disc.getTotalAlunos()
            ));
        
        matriculasPorDisciplina.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> System.out.println("   📚 " + entry.getKey() + ": " + entry.getValue() + " matrículas"));
        
        // Relatório de carga horária por professor
        System.out.println("\n⏰ Carga Horária por Professor:");
        escola.getProfessores().forEach(prof -> {
            int cargaTotal = escola.getDisciplinas().stream()
                                  .filter(disc -> disc.getProfessor().equals(prof))
                                  .mapToInt(Disciplina::getCargaHoraria)
                                  .sum();
            System.out.println("   👨‍🏫 " + prof.getNome() + ": " + cargaTotal + "h");
        });
    }
    
    private static void demonstrarOperacoesAvancadas(Escola escola) {
        System.out.println("🚀 OPERAÇÕES AVANÇADAS - PADRÕES DE DESIGN");
        
        // Strategy Pattern: Diferentes critérios de busca
        System.out.println("🎯 Strategy Pattern - Critérios de Busca:");
        
        // Busca por alunos com múltiplas disciplinas
        List<Aluno> alunosAtivos = escola.getAlunos().stream()
            .filter(aluno -> aluno.getDisciplinas().size() > 1)
            .collect(Collectors.toList());
        
        System.out.println("   📚 Alunos com múltiplas disciplinas:");
        alunosAtivos.forEach(aluno -> System.out.println("     • " + aluno.getNome() + 
                            " (" + aluno.getDisciplinas().size() + " disciplinas)"));
        
        // Agrupamento de alunos por faixa etária
        Map<String, List<Aluno>> alunosPorFaixaEtaria = escola.getAlunos().stream()
            .collect(Collectors.groupingBy(aluno -> {
                int idade = aluno.getIdade();
                if (idade < 20) return "Menor que 20";
                else if (idade <= 21) return "20-21 anos";
                else return "Maior que 21";
            }));
        
        System.out.println("\n👥 Agrupamento por Faixa Etária:");
        alunosPorFaixaEtaria.forEach((faixa, alunos) -> {
            System.out.println("   📊 " + faixa + ": " + alunos.size() + " alunos");
            alunos.forEach(aluno -> System.out.println("     • " + aluno.getNome()));
        });
        
        // Simulando Observer Pattern: Notificações
        System.out.println("\n🔔 Observer Pattern - Simulação de Notificações:");
        System.out.println("   ✉️ Nova disciplina seria notificada para " + escola.getTotalAlunos() + " alunos");
        System.out.println("   📧 Mudança de horário seria notificada para professores específicos");
        System.out.println("   🔔 Sistema de notificações integrado com Collections");
        
        // Demonstrando Repository Pattern
        System.out.println("\n🗄️ Repository Pattern - Simulação:");
        System.out.println("   💾 Dados persistidos: " + (escola.getTotalAlunos() + escola.getTotalProfessores()) + " pessoas");
        System.out.println("   🔍 Buscas otimizadas por índices (HashMaps internos)");
        System.out.println("   🔄 CRUD operations encapsuladas na classe Escola");
    }
}

// ===== CLASSES DO DOMÍNIO =====

/**
 * Classe base PessoaEscolar - Demonstra Encapsulamento
 */
abstract class PessoaEscolar {
    private String nome;
    private int idade;
    private String email;
    
    public PessoaEscolar(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }
    
    // Getters e Setters (Encapsulamento)
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getEmail() { return email; }
    
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PessoaEscolar pessoa = (PessoaEscolar) obj;
        return Objects.equals(email, pessoa.email); // Email é único
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
    
    @Override
    public String toString() {
        return nome + " (" + idade + " anos)";
    }
}

/**
 * Classe Professor - Demonstra Herança
 */
class Professor extends PessoaEscolar {
    private String especialidade;
    private int anosExperiencia;
    
    public Professor(String nome, int idade, String email, String especialidade, int anosExperiencia) {
        super(nome, idade, email); // Chamada ao construtor da superclasse
        this.especialidade = especialidade;
        this.anosExperiencia = anosExperiencia;
    }
    
    public String getEspecialidade() { return especialidade; }
    public int getAnosExperiencia() { return anosExperiencia; }
    
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public void setAnosExperiencia(int anosExperiencia) { this.anosExperiencia = anosExperiencia; }
    
    @Override
    public String toString() {
        return super.toString() + " - " + especialidade + " (" + anosExperiencia + " anos exp.)";
    }
}

/**
 * Classe Aluno - Demonstra Herança e Composição
 */
class Aluno extends PessoaEscolar {
    private String matricula;
    private Set<Disciplina> disciplinas; // Composição: Aluno "tem" disciplinas
    
    public Aluno(String nome, int idade, String email, String matricula) {
        super(nome, idade, email);
        this.matricula = matricula;
        this.disciplinas = new HashSet<>();
    }
    
    public String getMatricula() { return matricula; }
    
    public void matricularDisciplina(Disciplina disciplina) {
        if (disciplinas.add(disciplina)) { // Set evita duplicatas
            disciplina.adicionarAluno(this); // Relacionamento bidirecional
        }
    }
    
    public void cancelarMatricula(Disciplina disciplina) {
        if (disciplinas.remove(disciplina)) {
            disciplina.removerAluno(this);
        }
    }
    
    public Set<Disciplina> getDisciplinas() {
        return Collections.unmodifiableSet(disciplinas); // Cópia defensiva
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Matrícula: " + matricula;
    }
}

/**
 * Classe Disciplina - Demonstra Agregação
 */
class Disciplina {
    private String codigo;
    private String nome;
    private Professor professor; // Agregação: Disciplina "usa" Professor
    private int cargaHoraria;
    private Set<Aluno> alunos; // Agregação: Disciplina "usa" Alunos
    
    public Disciplina(String codigo, String nome, Professor professor, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.professor = professor;
        this.cargaHoraria = cargaHoraria;
        this.alunos = new HashSet<>();
    }
    
    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public Professor getProfessor() { return professor; }
    public int getCargaHoraria() { return cargaHoraria; }
    
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }
    
    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }
    
    public Set<Aluno> getAlunos() {
        return Collections.unmodifiableSet(alunos);
    }
    
    public int getTotalAlunos() {
        return alunos.size();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disciplina that = (Disciplina) obj;
        return Objects.equals(codigo, that.codigo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
    
    @Override
    public String toString() {
        return codigo + " - " + nome + " (" + cargaHoraria + "h)";
    }
}

/**
 * Classe Escola - Demonstra Composição e Repository Pattern
 */
class Escola {
    private String nome;
    
    // Collections para gerenciar os dados (Composição)
    private List<Professor> professores;
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;
    
    // Índices para busca otimizada (Strategy Pattern)
    private Map<String, Aluno> indiceAlunosPorMatricula;
    private Map<String, Disciplina> indiceDisciplinasPorCodigo;
    private Map<String, Professor> indiceProfessoresPorEmail;
    
    public Escola(String nome) {
        this.nome = nome;
        
        // Inicializando Collections
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        
        // Inicializando índices
        this.indiceAlunosPorMatricula = new HashMap<>();
        this.indiceDisciplinasPorCodigo = new HashMap<>();
        this.indiceProfessoresPorEmail = new HashMap<>();
    }
    
    // Métodos para Professor
    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        indiceProfessoresPorEmail.put(professor.getEmail(), professor);
    }
    
    public void removerProfessor(Professor professor) {
        professores.remove(professor);
        indiceProfessoresPorEmail.remove(professor.getEmail());
    }
    
    // Métodos para Aluno
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        indiceAlunosPorMatricula.put(aluno.getMatricula(), aluno);
    }
    
    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
        indiceAlunosPorMatricula.remove(aluno.getMatricula());
    }
    
    // Métodos para Disciplina
    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        indiceDisciplinasPorCodigo.put(disciplina.getCodigo(), disciplina);
    }
    
    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
        indiceDisciplinasPorCodigo.remove(disciplina.getCodigo());
    }
    
    // Métodos de busca otimizados
    public Aluno buscarAlunoPorMatricula(String matricula) {
        return indiceAlunosPorMatricula.get(matricula);
    }
    
    public Disciplina buscarDisciplinaPorCodigo(String codigo) {
        return indiceDisciplinasPorCodigo.get(codigo);
    }
    
    public Professor buscarProfessorPorEmail(String email) {
        return indiceProfessoresPorEmail.get(email);
    }
    
    // Getters defensivos (retornam cópias)
    public List<Professor> getProfessores() {
        return new ArrayList<>(professores);
    }
    
    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
    
    public List<Disciplina> getDisciplinas() {
        return new ArrayList<>(disciplinas);
    }
    
    // Métodos de estatística
    public int getTotalProfessores() { return professores.size(); }
    public int getTotalAlunos() { return alunos.size(); }
    public int getTotalDisciplinas() { return disciplinas.size(); }
    
    public String getNome() { return nome; }
}