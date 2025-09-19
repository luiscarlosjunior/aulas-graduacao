import java.util.*;

/**
 * Demonstração de Collections Framework com Programação Orientada a Objetos
 * 
 * Este exemplo mostra como usar Collections com objetos personalizados,
 * aplicando conceitos fundamentais de POO:
 * - Encapsulamento (dados privados, métodos públicos)
 * - Herança (extensão da classe Pessoa)
 * - Polimorfismo (Collections de tipos diferentes)
 * - Abstração (uso de interfaces Collection, List, Set, Map)
 * 
 * @author Aulas Graduação
 */
public class ColecoesPessoas {
    
    public static void main(String[] args) {
        System.out.println("=== COLLECTIONS FRAMEWORK COM POO ===\n");
        
        demonstrarArrayListComObjetos();
        System.out.println();
        
        demonstrarHashSetComEquals();
        System.out.println();
        
        demonstrarHashMapComObjetos();
        System.out.println();
        
        demonstrarOrdenacaoPersonalizada();
        System.out.println();
        
        demonstrarPolimorfismoCollections();
        System.out.println();
        
        demonstrarComposicaoDeObjetos();
    }
    
    /**
     * Demonstra ArrayList com objetos personalizados
     * Conceitos POO: Encapsulamento, Instanciação de objetos
     */
    private static void demonstrarArrayListComObjetos() {
        System.out.println("🧑‍🤝‍🧑 ARRAYLIST COM OBJETOS PERSONALIZADOS");
        System.out.println("Conceitos POO: Encapsulamento, Instanciação");
        
        // ArrayList para armazenar objetos Pessoa
        List<Pessoa> pessoas = new ArrayList<>();
        
        // Criando objetos usando construtores (Encapsulamento)
        pessoas.add(new Pessoa("Ana Silva", 28, "ana@email.com"));
        pessoas.add(new Pessoa("Bruno Santos", 35, "bruno@email.com"));
        pessoas.add(new Pessoa("Carla Oliveira", 22, "carla@email.com"));
        pessoas.add(new Pessoa("Diego Costa", 31, "diego@email.com"));
        
        System.out.println("👥 Lista de pessoas criada:");
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa p = pessoas.get(i);
            System.out.println((i + 1) + ". " + p.getNome() + " (" + p.getIdade() + " anos)");
        }
        
        // Operações específicas com objetos
        System.out.println("\n🔍 Buscando pessoas maiores de 30 anos:");
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getIdade() > 30) {
                System.out.println("   • " + pessoa.getNome() + " - " + pessoa.getIdade() + " anos");
            }
        }
        
        // Modificando objetos na lista (usando métodos da classe)
        System.out.println("\n🎂 Fazendo aniversário de algumas pessoas:");
        pessoas.get(0).fazerAniversario(); // Ana
        pessoas.get(2).fazerAniversario(); // Carla
        
        // Estatísticas usando Stream API com objetos
        double idadeMedia = pessoas.stream()
                                 .mapToInt(Pessoa::getIdade)
                                 .average()
                                 .orElse(0.0);
        System.out.println("📊 Idade média do grupo: " + String.format("%.1f", idadeMedia) + " anos");
    }
    
    /**
     * Demonstra HashSet e a importância de equals() e hashCode()
     * Conceitos POO: Sobrescrita de métodos, Contrato de igualdade
     */
    private static void demonstrarHashSetComEquals() {
        System.out.println("🔄 HASHSET E IMPLEMENTAÇÃO DE EQUALS/HASHCODE");
        System.out.println("Conceitos POO: Sobrescrita de métodos, Contrato de igualdade");
        
        // HashSet elimina duplicatas usando equals() e hashCode()
        Set<Pessoa> pessoasUnicas = new HashSet<>();
        
        // Adicionando pessoas
        pessoasUnicas.add(new Pessoa("Maria", 25, "maria@email.com"));
        pessoasUnicas.add(new Pessoa("João", 30, "joao@email.com"));
        pessoasUnicas.add(new Pessoa("Maria", 25, "maria@email.com")); // Duplicata!
        pessoasUnicas.add(new Pessoa("Ana", 28, "ana@email.com"));
        
        System.out.println("👥 Pessoas no HashSet (duplicatas removidas automaticamente):");
        System.out.println("   Tamanho: " + pessoasUnicas.size() + " pessoas");
        
        for (Pessoa pessoa : pessoasUnicas) {
            System.out.println("   • " + pessoa);
        }
        
        // Testando busca com equals()
        Pessoa buscada = new Pessoa("Maria", 25, "maria@email.com");
        boolean encontrada = pessoasUnicas.contains(buscada);
        System.out.println("\n🔍 Busca por 'Maria, 25 anos': " + 
                          (encontrada ? "Encontrada! ✅" : "Não encontrada ❌"));
        
        // Demonstrando diferença com e sem equals() implementado
        System.out.println("\n📝 Importância do equals() e hashCode():");
        System.out.println("   - Sem implementação: objetos iguais são tratados como diferentes");
        System.out.println("   - Com implementação: duplicatas são eliminadas corretamente");
        System.out.println("   - Classe Pessoa implementa equals() baseado em nome + idade");
    }
    
    /**
     * Demonstra HashMap com objetos como chaves e valores
     * Conceitos POO: Objetos como chaves, Relacionamentos entre objetos
     */
    private static void demonstrarHashMapComObjetos() {
        System.out.println("🗺️ HASHMAP COM OBJETOS COMO CHAVES E VALORES");
        System.out.println("Conceitos POO: Objetos como chaves, Relacionamentos");
        
        // Map usando String como chave e Pessoa como valor
        Map<String, Pessoa> funcionarios = new HashMap<>();
        
        // Adicionando funcionários (relacionamento ID -> Pessoa)
        funcionarios.put("EMP001", new Pessoa("Alice Costa", 29, "alice@empresa.com"));
        funcionarios.put("EMP002", new Pessoa("Bruno Lima", 34, "bruno@empresa.com"));
        funcionarios.put("EMP003", new Pessoa("Carla Souza", 26, "carla@empresa.com"));
        
        System.out.println("💼 Sistema de funcionários:");
        for (Map.Entry<String, Pessoa> entry : funcionarios.entrySet()) {
            String id = entry.getKey();
            Pessoa funcionario = entry.getValue();
            System.out.println("   " + id + " -> " + funcionario.getNome() + 
                             " (" + funcionario.getEmail() + ")");
        }
        
        // Busca por ID
        String idBusca = "EMP002";
        Pessoa encontrado = funcionarios.get(idBusca);
        if (encontrado != null) {
            System.out.println("\n🔍 Funcionário " + idBusca + " encontrado:");
            System.out.println("   Nome: " + encontrado.getNome());
            System.out.println("   Idade: " + encontrado.getIdade());
            System.out.println("   Email: " + encontrado.getEmail());
        }
        
        // Map com objetos como chaves (usando Pessoa como chave)
        Map<Pessoa, String> cargosPorPessoa = new HashMap<>();
        
        for (Pessoa pessoa : funcionarios.values()) {
            String cargo = "Desenvolvedor " + (pessoa.getIdade() > 30 ? "Senior" : "Junior");
            cargosPorPessoa.put(pessoa, cargo);
        }
        
        System.out.println("\n📋 Cargos por pessoa:");
        cargosPorPessoa.forEach((pessoa, cargo) -> {
            System.out.println("   " + pessoa.getNome() + " -> " + cargo);
        });
    }
    
    /**
     * Demonstra ordenação personalizada usando Comparator
     * Conceitos POO: Interfaces funcionais, Strategy Pattern
     */
    private static void demonstrarOrdenacaoPersonalizada() {
        System.out.println("📊 ORDENAÇÃO PERSONALIZADA COM COMPARATORS");
        System.out.println("Conceitos POO: Interfaces funcionais, Strategy Pattern");
        
        List<Pessoa> equipe = Arrays.asList(
            new Pessoa("João Silva", 35, "joao@email.com"),
            new Pessoa("Ana Costa", 28, "ana@email.com"),
            new Pessoa("Bruno Santos", 31, "bruno@email.com"),
            new Pessoa("Carla Lima", 25, "carla@email.com")
        );
        
        System.out.println("👥 Equipe original:");
        equipe.forEach(p -> System.out.println("   " + p.getNome() + " (" + p.getIdade() + " anos)"));
        
        // Estratégia 1: Ordenar por nome (Strategy Pattern)
        List<Pessoa> porNome = new ArrayList<>(equipe);
        porNome.sort(Comparator.comparing(Pessoa::getNome));
        
        System.out.println("\n📝 Ordenado por nome:");
        porNome.forEach(p -> System.out.println("   " + p.getNome()));
        
        // Estratégia 2: Ordenar por idade
        List<Pessoa> porIdade = new ArrayList<>(equipe);
        porIdade.sort(Comparator.comparing(Pessoa::getIdade));
        
        System.out.println("\n🎂 Ordenado por idade:");
        porIdade.forEach(p -> System.out.println("   " + p.getNome() + " (" + p.getIdade() + " anos)"));
        
        // Estratégia 3: Ordenação composta (nome + idade)
        List<Pessoa> composta = new ArrayList<>(equipe);
        composta.sort(Comparator.comparing(Pessoa::getNome)
                               .thenComparing(Pessoa::getIdade));
        
        System.out.println("\n🔄 Ordenado por nome, depois por idade:");
        composta.forEach(p -> System.out.println("   " + p.getNome() + " (" + p.getIdade() + " anos)"));
        
        // Estratégia 4: Ordenação customizada com lambda
        List<Pessoa> customizada = new ArrayList<>(equipe);
        customizada.sort((p1, p2) -> {
            // Primeiro maiores de idade, depois por nome
            if (p1.ehMaiorIdade() && !p2.ehMaiorIdade()) return -1;
            if (!p1.ehMaiorIdade() && p2.ehMaiorIdade()) return 1;
            return p1.getNome().compareTo(p2.getNome());
        });
        
        System.out.println("\n🎯 Ordenação customizada (maiores de idade primeiro):");
        customizada.forEach(p -> System.out.println("   " + p.getNome() + 
                           " (" + p.getIdade() + " anos) - " + 
                           (p.ehMaiorIdade() ? "Maior" : "Menor") + " de idade"));
    }
    
    /**
     * Demonstra polimorfismo com Collections
     * Conceitos POO: Polimorfismo, Herança, Interfaces
     */
    private static void demonstrarPolimorfismoCollections() {
        System.out.println("🎭 POLIMORFISMO COM COLLECTIONS");
        System.out.println("Conceitos POO: Polimorfismo, Herança, Interfaces");
        
        // Polimorfismo: Referência de interface aponta para implementação específica
        Collection<Pessoa> colecao1 = new ArrayList<>(); // List
        Collection<Pessoa> colecao2 = new HashSet<>();   // Set
        Collection<Pessoa> colecao3 = new LinkedList<>(); // Queue
        
        // Adicionando pessoas em diferentes tipos de coleções
        Pessoa p1 = new Pessoa("Maria", 30, "maria@email.com");
        Pessoa p2 = new Pessoa("João", 25, "joao@email.com");
        
        colecao1.add(p1);
        colecao1.add(p2);
        colecao2.add(p1);
        colecao2.add(p2);
        colecao3.add(p1);
        colecao3.add(p2);
        
        System.out.println("📦 Diferentes implementações de Collection:");
        
        // Mesmo método funciona para todas as implementações (Polimorfismo)
        processarColecao("ArrayList", colecao1);
        processarColecao("HashSet", colecao2);
        processarColecao("LinkedList", colecao3);
        
        // Demonstrando hierarquia de herança
        System.out.println("\n🏗️ Hierarquia de herança:");
        System.out.println("   Collection (interface raiz)");
        System.out.println("   ├── List (interface) -> ArrayList, LinkedList");
        System.out.println("   ├── Set (interface) -> HashSet, TreeSet");
        System.out.println("   └── Queue (interface) -> LinkedList, ArrayDeque");
        
        // Aproveitando especialização de cada tipo
        if (colecao1 instanceof List) {
            List<Pessoa> lista = (List<Pessoa>) colecao1;
            System.out.println("\n🎯 Funcionalidade específica de List:");
            System.out.println("   Primeira pessoa: " + lista.get(0).getNome());
            System.out.println("   Acesso por índice disponível!");
        }
    }
    
    /**
     * Método polimórfico que funciona com qualquer Collection
     */
    private static void processarColecao(String tipo, Collection<Pessoa> colecao) {
        System.out.println("   " + tipo + " (tamanho: " + colecao.size() + "):");
        for (Pessoa pessoa : colecao) {
            System.out.println("     • " + pessoa.getNome());
        }
    }
    
    /**
     * Demonstra composição e agregação com Collections
     * Conceitos POO: Composição, Agregação, Relacionamentos
     */
    private static void demonstrarComposicaoDeObjetos() {
        System.out.println("🏗️ COMPOSIÇÃO E AGREGAÇÃO COM COLLECTIONS");
        System.out.println("Conceitos POO: Composição, Agregação, Relacionamentos");
        
        // Criando um Departamento que "tem" funcionários (Composição)
        Departamento ti = new Departamento("Tecnologia da Informação");
        
        // Adicionando funcionários ao departamento
        ti.adicionarFuncionario(new Pessoa("Alice", 29, "alice@empresa.com"));
        ti.adicionarFuncionario(new Pessoa("Bruno", 34, "bruno@empresa.com"));
        ti.adicionarFuncionario(new Pessoa("Carla", 26, "carla@empresa.com"));
        
        System.out.println("🏢 Departamento criado:");
        ti.exibirInformacoes();
        
        // Demonstrando operações do departamento
        System.out.println("\n📊 Estatísticas do departamento:");
        System.out.println("   Total de funcionários: " + ti.getTotalFuncionarios());
        System.out.println("   Idade média: " + String.format("%.1f", ti.getIdadeMedia()) + " anos");
        
        // Buscando funcionário específico
        String nomeBusca = "Bruno";
        Pessoa encontrado = ti.buscarFuncionario(nomeBusca);
        if (encontrado != null) {
            System.out.println("   Funcionário " + nomeBusca + " encontrado: " + encontrado.getEmail());
        }
        
        System.out.println("\n💡 Conceitos demonstrados:");
        System.out.println("   - Composição: Departamento 'possui' funcionários");
        System.out.println("   - Encapsulamento: Lista de funcionários é privada");
        System.out.println("   - Métodos públicos: Interface para acessar dados");
        System.out.println("   - Relacionamentos: Objetos relacionados via Collections");
    }
}

/**
 * Classe Pessoa para demonstrar POO com Collections
 * Implementa equals() e hashCode() para uso correto em HashSet/HashMap
 */
class Pessoa {
    private String nome;
    private int idade;
    private String email;
    
    public Pessoa(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }
    
    // Métodos de acesso (Encapsulamento)
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getEmail() { return email; }
    
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setEmail(String email) { this.email = email; }
    
    // Métodos de comportamento
    public void fazerAniversario() {
        this.idade++;
        System.out.println("   🎂 " + nome + " fez aniversário! Agora tem " + idade + " anos.");
    }
    
    public boolean ehMaiorIdade() {
        return idade >= 18;
    }
    
    // Implementação correta de equals() e hashCode() para uso em Collections
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Pessoa pessoa = (Pessoa) obj;
        return idade == pessoa.idade && 
               Objects.equals(nome, pessoa.nome);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nome, idade);
    }
    
    @Override
    public String toString() {
        return nome + " (" + idade + " anos, " + email + ")";
    }
}

/**
 * Classe Departamento demonstrando Composição com Collections
 * O departamento "possui" uma lista de funcionários
 */
class Departamento {
    private String nome;
    private List<Pessoa> funcionarios; // Composição: Departamento "tem" funcionários
    
    public Departamento(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>(); // Inicialização da composição
    }
    
    public void adicionarFuncionario(Pessoa funcionario) {
        funcionarios.add(funcionario);
    }
    
    public void removerFuncionario(Pessoa funcionario) {
        funcionarios.remove(funcionario);
    }
    
    public Pessoa buscarFuncionario(String nome) {
        return funcionarios.stream()
                          .filter(f -> f.getNome().equalsIgnoreCase(nome))
                          .findFirst()
                          .orElse(null);
    }
    
    public int getTotalFuncionarios() {
        return funcionarios.size();
    }
    
    public double getIdadeMedia() {
        return funcionarios.stream()
                          .mapToInt(Pessoa::getIdade)
                          .average()
                          .orElse(0.0);
    }
    
    public void exibirInformacoes() {
        System.out.println("   Departamento: " + nome);
        System.out.println("   Funcionários:");
        for (int i = 0; i < funcionarios.size(); i++) {
            Pessoa f = funcionarios.get(i);
            System.out.println("     " + (i + 1) + ". " + f.getNome() + " - " + f.getIdade() + " anos");
        }
    }
    
    // Getter defensivo - retorna cópia para manter encapsulamento
    public List<Pessoa> getFuncionarios() {
        return new ArrayList<>(funcionarios);
    }
}