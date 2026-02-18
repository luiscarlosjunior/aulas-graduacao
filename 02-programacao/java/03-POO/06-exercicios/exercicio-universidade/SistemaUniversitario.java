/**
 * Sistema Universitário Completo - Demonstração Integrada de TODOS os Conceitos de POO
 * 
 * Este exemplo demonstra a integração de TODOS os pilares da POO:
 * 1. CLASSES E OBJETOS: Pessoa, Estudante, Professor
 * 2. ENCAPSULAMENTO: Dados protegidos, validação, getters/setters
 * 3. HERANÇA: Estudante e Professor herdam de Pessoa
 * 4. POLIMORFISMO: Mesmo método, comportamentos totalmente diferentes
 * 5. ABSTRAÇÃO: Classe abstrata Pessoa com métodos abstratos
 * 
 * É um exemplo completo que integra todos os conceitos de forma prática e realista.
 * 
 * @author Curso POO Java - Sistema Integrado
 */
public class SistemaUniversitario {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA UNIVERSITÁRIO COMPLETO ===");
        System.out.println("🎓 Demonstração integrada de TODOS os conceitos de POO\n");
        
        // ===== 1. CLASSES E OBJETOS - CRIAÇÃO DE INSTÂNCIAS =====
        System.out.println("1️⃣ CRIANDO PESSOAS NO SISTEMA UNIVERSITÁRIO\n");
        
        // Criando estudantes
        Estudante est1 = new Estudante(
            "Maria Silva", "123.456.789-10", "maria.silva@universidade.edu.br", 20,
            "20240001", "Ciência da Computação", 3, 2022
        );
        
        Estudante est2 = new Estudante(
            "João Santos", "987.654.321-20", "joao.santos@universidade.edu.br", 19,
            "20240002", "Engenharia", 2, 2023
        );
        
        Estudante est3 = new Estudante(
            "Ana Costa", "456.789.123-30", "ana.costa@universidade.edu.br", 22,
            "20210003", "Administração", 6, 2021
        );
        
        // Criando professores
        Professor prof1 = new Professor(
            "Dr. Carlos Mendes", "111.222.333-40", "carlos.mendes@universidade.edu.br", 45,
            "100001", "Ciência da Computação", "Doutorado", "Dedicação Exclusiva", 2018
        );
        
        Professor prof2 = new Professor(
            "Dra. Laura Oliveira", "555.666.777-50", "laura.oliveira@universidade.edu.br", 38,
            "100002", "Matemática", "Mestrado", "40h", 2020
        );
        
        Professor prof3 = new Professor(
            "Dr. Roberto Lima", "999.888.777-60", "roberto.lima@universidade.edu.br", 52,
            "100003", "Administração", "Pós-Doutorado", "Dedicação Exclusiva", 2015
        );
        
        // ===== 2. POLIMORFISMO - ARRAYS HETEROGÊNEOS =====
        System.out.println("\n2️⃣ POLIMORFISMO - PROCESSAMENTO UNIFICADO\n");
        
        // Array polimórfico - todos são Pessoa, mas comportam-se diferentemente
        Pessoa[] comunidadeUniversitaria = {est1, est2, est3, prof1, prof2, prof3};
        
        System.out.println("--- Entrada no campus (comportamento comum) ---");
        for (Pessoa pessoa : comunidadeUniversitaria) {
            pessoa.entrarCampus(); // Mesmo método para todos
        }
        
        System.out.println("\n--- Exercendo funções específicas (POLIMORFISMO!) ---");
        for (Pessoa pessoa : comunidadeUniversitaria) {
            System.out.println("\n" + pessoa.getNome() + ":");
            pessoa.exercerFuncao(); // POLIMORFISMO: cada tipo faz coisa diferente
        }
        
        // ===== 3. ENCAPSULAMENTO - PROTEÇÃO E VALIDAÇÃO =====
        System.out.println("\n3️⃣ ENCAPSULAMENTO - PROTEÇÃO E VALIDAÇÃO\n");
        
        System.out.println("--- Testando validações de dados ---");
        try {
            // Tentativa de dados inválidos
            System.out.println("Tentando criar estudante com CPF inválido...");
            Estudante estudanteInvalido = new Estudante("Teste", "123456789", "teste", 20, "12345678", "Teste", 1, 2024);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Validação funcionou: " + e.getMessage());
        }
        
        System.out.println("\n--- Atualizando dados com segurança ---");
        est1.atualizarDados("(11) 99999-1111", "Rua Nova, 123");
        
        // ===== 4. HERANÇA - REUTILIZAÇÃO E ESPECIALIZAÇÃO =====
        System.out.println("\n4️⃣ HERANÇA - MÉTODOS HERDADOS E ESPECIALIZADOS\n");
        
        System.out.println("--- Usando métodos herdados (da classe Pessoa) ---");
        est1.usarBiblioteca(); // Método da classe pai
        prof1.usarBiblioteca(); // Mesmo método, comportamento especializado
        
        System.out.println("\n--- Usando restaurante (preços diferentes por tipo) ---");
        est1.usarRestaurante(); // Preço de estudante
        prof1.usarRestaurante(); // Preço de professor
        
        // ===== 5. MÉTODOS ESPECÍFICOS DE CADA CLASSE =====
        System.out.println("\n5️⃣ COMPORTAMENTOS ESPECÍFICOS POR TIPO\n");
        
        System.out.println("--- Atividades específicas de ESTUDANTES ---");
        est1.matricularDisciplina("Programação Orientada a Objetos", 4);
        est1.concluirDisciplina("Estruturas de Dados", 8.5, 4);
        est1.solicitarBolsa("merito");
        
        est2.participarAtividade("prova");
        est2.participarAtividade("monitoria");
        
        System.out.println("\n--- Atividades específicas de PROFESSORES ---");
        prof1.setAnosExperiencia(6);
        prof1.publicarTrabalho("Machine Learning in Education", "IEEE Transactions");
        prof1.publicarTrabalho("Deep Learning Applications", "Nature AI");
        prof1.ministrarDisciplina("Inteligência Artificial", 60);
        
        prof3.setAnosExperiencia(9);
        for (int i = 0; i < 8; i++) {
            prof3.publicarTrabalho("Artigo " + (i+1), "Revista Científica");
        }
        prof3.tornarCoordenador("Curso de Administração");
        
        // ===== 6. INTERAÇÕES ENTRE OBJETOS =====
        System.out.println("\n6️⃣ INTERAÇÕES ENTRE DIFERENTES TIPOS\n");
        
        System.out.println("--- Professores orientando estudantes ---");
        prof1.orientarEstudante(est1, "TCC");
        prof1.orientarEstudante(est2, "IC");
        prof3.orientarEstudante(est3, "mestrado");
        
        // ===== 7. ATIVIDADES ACADÊMICAS DIVERSAS =====
        System.out.println("\n7️⃣ ATIVIDADES ACADÊMICAS ESPECÍFICAS\n");
        
        System.out.println("--- Estudantes em diferentes atividades ---");
        est1.participarAtividade("seminario");
        est2.participarAtividade("iniciacao cientifica");
        est3.participarAtividade("aula");
        
        System.out.println("\n--- Professores em atividades docentes ---");
        prof1.participarAtividade("pesquisa");
        prof2.participarAtividade("orientacao");
        prof3.participarAtividade("reuniao"); // Como coordenador
        
        // ===== 8. PROGRESSÃO E EVOLUÇÃO =====
        System.out.println("\n8️⃣ PROGRESSÃO ACADÊMICA E PROFISSIONAL\n");
        
        System.out.println("--- Estudante evoluindo no curso ---");
        est1.concluirDisciplina("Banco de Dados", 9.0, 4);
        est1.concluirDisciplina("Engenharia de Software", 8.0, 6);
        est1.setSemestre(4);
        
        System.out.println("\n--- Professor evoluindo na carreira ---");
        prof2.setAnosExperiencia(5);
        for (int i = 0; i < 6; i++) {
            prof2.publicarTrabalho("Pesquisa " + (i+1), "Revista Matemática");
        }
        prof2.solicitarProgressao();
        
        // ===== 9. CASOS ESPECIAIS E VALIDAÇÕES =====
        System.out.println("\n9️⃣ CASOS ESPECIAIS E TRATAMENTO DE EXCEÇÕES\n");
        
        System.out.println("--- Testando bolsa para estudante com CRA baixo ---");
        Estudante estBaixoCRA = new Estudante(
            "Pedro Teste", "777.888.999-70", "pedro.teste@universidade.edu.br", 21,
            "20240004", "Direito", 1, 2024
        );
        estBaixoCRA.solicitarBolsa("merito"); // Deve falhar
        
        System.out.println("\n--- Tentativa de orientação inválida ---");
        prof2.orientarEstudante(est1, "doutorado"); // Prof mestrado tentando orientar doutorado
        
        // ===== 10. POLIMORFISMO AVANÇADO COM CASTING =====
        System.out.println("\n🔟 POLIMORFISMO AVANÇADO COM CASTING\n");
        
        for (Pessoa pessoa : comunidadeUniversitaria) {
            System.out.println("\nAnalisando: " + pessoa.getNome());
            
            if (pessoa instanceof Estudante) {
                System.out.println("✓ É um Estudante!");
                Estudante est = (Estudante) pessoa; // Cast seguro
                System.out.printf("   📚 Curso: %s (%dº semestre)\n", est.getCurso(), est.getSemestre());
                System.out.printf("   📊 CRA: %.2f, Créditos: %d/%d\n", 
                                est.getCra(), est.getCreditosCompletos(), est.getCreditosNecessarios());
                System.out.println("   🎓 Bolsista: " + (est.isBolsista() ? "Sim (" + est.getTipoBolsa() + ")" : "Não"));
                
                // Ação específica para estudantes
                if (est.getSemestre() >= 6) {
                    est.participarAtividade("TCC");
                }
                
            } else if (pessoa instanceof Professor) {
                System.out.println("✓ É um Professor!");
                Professor prof = (Professor) pessoa; // Cast seguro
                System.out.printf("   🏛️ Departamento: %s (%s)\n", prof.getDepartamento(), prof.getTitulacao());
                System.out.printf("   💰 Salário: R$ %.2f (%s)\n", prof.getSalario(), prof.getRegime());
                System.out.printf("   📄 Publicações: %d, Experiência: %d anos\n", 
                                prof.getPublicacoes(), prof.getAnosExperiencia());
                System.out.println("   👑 Coordenador: " + (prof.isCoordenador() ? "Sim" : "Não"));
                
                // Ação específica para professores
                if (prof.getPublicacoes() >= 5) {
                    prof.participarAtividade("pos-graduacao");
                }
            }
        }
        
        // ===== 11. SIMULAÇÃO DE SEMESTRE LETIVO =====
        System.out.println("\n1️⃣1️⃣ SIMULAÇÃO DE SEMESTRE LETIVO\n");
        
        System.out.println("--- Início do semestre ---");
        for (Pessoa pessoa : comunidadeUniversitaria) {
            if (pessoa instanceof Estudante) {
                Estudante est = (Estudante) pessoa;
                est.matricularDisciplina("Disciplina do Semestre", 4);
            } else if (pessoa instanceof Professor) {
                Professor prof = (Professor) pessoa;
                prof.ministrarDisciplina("Nova Disciplina", 60);
            }
        }
        
        System.out.println("\n--- Atividades do meio do semestre ---");
        for (Pessoa pessoa : comunidadeUniversitaria) {
            pessoa.participarAtividade("reuniao");
        }
        
        // ===== 12. RELATÓRIOS E ESTATÍSTICAS =====
        System.out.println("\n1️⃣2️⃣ RELATÓRIOS INSTITUCIONAIS\n");
        
        gerarRelatorioEstudantes(comunidadeUniversitaria);
        gerarRelatorioProfessores(comunidadeUniversitaria);
        gerarEstatisticasGerais(comunidadeUniversitaria);
        
        // ===== 13. INFORMAÇÕES DETALHADAS =====
        System.out.println("\n1️⃣3️⃣ INFORMAÇÕES DETALHADAS DE CADA PESSOA\n");
        
        for (Pessoa pessoa : comunidadeUniversitaria) {
            pessoa.exibirInformacoes(); // Polimorfismo: cada tipo exibe informações específicas
        }
        
        // ===== 14. SIMULAÇÃO DE FORMATURA =====
        System.out.println("\n1️⃣4️⃣ SIMULAÇÃO DE CONCLUSÃO DE CURSO\n");
        
        // Simula que est3 completou todos os créditos
        Estudante formando = est3;
        System.out.println("--- Preparando formatura de " + formando.getNome() + " ---");
        
        // Simula conclusão de créditos restantes
        int creditosFaltantes = formando.getCreditosNecessarios() - formando.getCreditosCompletos();
        for (int i = 0; i < creditosFaltantes / 4; i++) {
            formando.concluirDisciplina("Disciplina Final " + (i+1), 7.5, 4);
        }
        
        // Tenta formar
        formando.formar();
        
        // ===== RESUMO CONCEITUAL =====
        System.out.println("\n🎯 RESUMO DOS CONCEITOS DEMONSTRADOS\n");
        System.out.println("✅ Sistema Universitário - TODOS os conceitos de POO integrados:");
        System.out.println("   1. 📦 CLASSES E OBJETOS: Pessoa, Estudante, Professor com atributos e métodos");
        System.out.println("   2. 🔒 ENCAPSULAMENTO: Dados privados, validação, getters/setters seguros");
        System.out.println("   3. 🏗️ HERANÇA: Estudante e Professor herdam de Pessoa (reutilização)");
        System.out.println("   4. 🎭 POLIMORFISMO: exercerFuncao(), participarAtividade() - mesmo método, comportamentos diferentes");
        System.out.println("   5. 🎨 ABSTRAÇÃO: Classe abstrata Pessoa com métodos abstratos obrigatórios");
        System.out.println("   6. 🤝 INTERAÇÕES: Objetos de diferentes tipos interagindo (professor orienta estudante)");
        System.out.println("   7. 🧪 VALIDAÇÕES: Encapsulamento protege dados contra valores inválidos");
        System.out.println("   8. 🔄 SOBRESCRITA: Métodos especializados em cada classe filha");
        System.out.println("   9. 🎯 CASTING: instanceof e cast seguro para acessar métodos específicos");
        System.out.println("   10. 📊 RELATÓRIOS: Métodos polimórficos processando arrays heterogêneos");
        
        System.out.println("\n📚 Este é um exemplo COMPLETO e INTEGRADO de POO!");
        System.out.println("🏆 Demonstra como todos os conceitos trabalham juntos em um sistema real");
        System.out.println("🎓 Perfeito para entender a APLICAÇÃO PRÁTICA da orientação a objetos");
        
        System.out.println("\n✅ Demonstração do Sistema Universitário concluída!");
    }
    
    // ===== MÉTODOS UTILITÁRIOS PARA RELATÓRIOS =====
    
    /**
     * Gera relatório específico de estudantes
     */
    public static void gerarRelatorioEstudantes(Pessoa[] comunidade) {
        System.out.println("📊 RELATÓRIO DE ESTUDANTES");
        System.out.println("==========================");
        
        int totalEstudantes = 0;
        int bolsistas = 0;
        double somaIdades = 0;
        double somaCRA = 0;
        
        for (Pessoa pessoa : comunidade) {
            if (pessoa instanceof Estudante) {
                Estudante est = (Estudante) pessoa;
                totalEstudantes++;
                somaIdades += est.getIdade();
                somaCRA += est.getCra();
                
                if (est.isBolsista()) {
                    bolsistas++;
                }
                
                System.out.printf("👨‍🎓 %s - %s (%dº sem) - CRA: %.2f%s\n",
                                est.getNome(), est.getCurso(), est.getSemestre(), est.getCra(),
                                est.isBolsista() ? " [BOLSISTA]" : "");
            }
        }
        
        if (totalEstudantes > 0) {
            System.out.printf("\n📊 Estatísticas:\n");
            System.out.printf("   Total: %d estudantes\n", totalEstudantes);
            System.out.printf("   Bolsistas: %d (%.1f%%)\n", bolsistas, (double)bolsistas/totalEstudantes*100);
            System.out.printf("   Idade média: %.1f anos\n", somaIdades/totalEstudantes);
            System.out.printf("   CRA médio: %.2f\n", somaCRA/totalEstudantes);
        }
        System.out.println("==========================\n");
    }
    
    /**
     * Gera relatório específico de professores
     */
    public static void gerarRelatorioProfessores(Pessoa[] comunidade) {
        System.out.println("📊 RELATÓRIO DE PROFESSORES");
        System.out.println("============================");
        
        int totalProfessores = 0;
        int coordenadores = 0;
        double somaIdades = 0;
        double somaSalarios = 0;
        int somaPublicacoes = 0;
        
        for (Pessoa pessoa : comunidade) {
            if (pessoa instanceof Professor) {
                Professor prof = (Professor) pessoa;
                totalProfessores++;
                somaIdades += prof.getIdade();
                somaSalarios += prof.getSalario();
                somaPublicacoes += prof.getPublicacoes();
                
                if (prof.isCoordenador()) {
                    coordenadores++;
                }
                
                System.out.printf("👨‍🏫 %s - %s (%s) - R$ %.2f - %d pub.%s\n",
                                prof.getNome(), prof.getDepartamento(), prof.getTitulacao(),
                                prof.getSalario(), prof.getPublicacoes(),
                                prof.isCoordenador() ? " [COORDENADOR]" : "");
            }
        }
        
        if (totalProfessores > 0) {
            System.out.printf("\n📊 Estatísticas:\n");
            System.out.printf("   Total: %d professores\n", totalProfessores);
            System.out.printf("   Coordenadores: %d (%.1f%%)\n", coordenadores, (double)coordenadores/totalProfessores*100);
            System.out.printf("   Idade média: %.1f anos\n", somaIdades/totalProfessores);
            System.out.printf("   Salário médio: R$ %.2f\n", somaSalarios/totalProfessores);
            System.out.printf("   Publicações médias: %.1f por professor\n", (double)somaPublicacoes/totalProfessores);
        }
        System.out.println("============================\n");
    }
    
    /**
     * Gera estatísticas gerais da universidade
     */
    public static void gerarEstatisticasGerais(Pessoa[] comunidade) {
        System.out.println("📊 ESTATÍSTICAS GERAIS DA UNIVERSIDADE");
        System.out.println("======================================");
        
        int estudantes = 0, professores = 0, ativos = 0;
        
        for (Pessoa pessoa : comunidade) {
            if (pessoa.isAtivo()) {
                ativos++;
            }
            
            if (pessoa instanceof Estudante) {
                estudantes++;
            } else if (pessoa instanceof Professor) {
                professores++;
            }
        }
        
        System.out.printf("👥 Comunidade universitária: %d pessoas\n", comunidade.length);
        System.out.printf("   📚 Estudantes: %d (%.1f%%)\n", estudantes, (double)estudantes/comunidade.length*100);
        System.out.printf("   👨‍🏫 Professores: %d (%.1f%%)\n", professores, (double)professores/comunidade.length*100);
        System.out.printf("   ✅ Ativos: %d (%.1f%%)\n", ativos, (double)ativos/comunidade.length*100);
        System.out.printf("   📈 Relação estudante/professor: %.1f:1\n", (double)estudantes/professores);
        
        System.out.println("\n🎯 Sistema funcionando com todos os conceitos de POO integrados!");
        System.out.println("======================================\n");
    }
}