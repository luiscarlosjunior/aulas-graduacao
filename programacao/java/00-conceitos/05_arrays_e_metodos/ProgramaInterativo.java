import java.util.Scanner;

/**
 * Programa Interativo - Demonstração de Entrada de Dados com Scanner
 * 
 * Este programa demonstra como criar aplicações interativas em Java
 * que recebem entrada do usuário através do teclado usando a classe Scanner.
 * 
 * Conceitos abordados:
 * - Importação de classes (import)
 * - Uso da classe Scanner para entrada de dados
 * - Diferentes tipos de entrada (int, double, String)
 * - Validação básica de entrada
 * - Criação de menus interativos
 * - Combinação de todos os conceitos aprendidos
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
public class ProgramaInterativo {

    // Scanner global para ser usado em todos os métodos
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal - exibe menu e coordena as funcionalidades
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║         PROGRAMA INTERATIVO EM JAVA          ║");
        System.out.println("║      Demonstração de Entrada de Dados        ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        
        exibirMenuPrincipal();
        
        // Fecha o scanner no final do programa
        scanner.close();
        System.out.println("\nObrigado por usar o programa! 👋");
    }
    
    /**
     * Exibe o menu principal e processa as opções do usuário
     */
    public static void exibirMenuPrincipal() {
        int opcao;
        
        do {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("           MENU PRINCIPAL");
            System.out.println("=".repeat(50));
            System.out.println("1. 👤 Cadastro de Pessoa");
            System.out.println("2. 🧮 Calculadora Simples");
            System.out.println("3. 📊 Análise de Notas");
            System.out.println("4. 🎯 Jogo de Adivinhação");
            System.out.println("5. 📝 Lista de Tarefas");
            System.out.println("6. 🌡️  Conversor de Temperatura");
            System.out.println("0. 🚪 Sair");
            System.out.println("=".repeat(50));
            System.out.print("Escolha uma opção: ");
            
            opcao = lerInteiroValido();
            
            switch (opcao) {
                case 1:
                    cadastroPessoa();
                    break;
                case 2:
                    calculadoraSimples();
                    break;
                case 3:
                    analiseNotas();
                    break;
                case 4:
                    jogoAdivinhacao();
                    break;
                case 5:
                    listaTarefas();
                    break;
                case 6:
                    conversorTemperatura();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
            }
            
        } while (opcao != 0);
    }
    
    /**
     * Funcionalidade 1: Cadastro de pessoa com validações
     */
    public static void cadastroPessoa() {
        System.out.println("\n👤 CADASTRO DE PESSOA");
        System.out.println("-".repeat(30));
        
        // Entrada de nome (String)
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        
        // Validação básica do nome
        while (nome.trim().isEmpty()) {
            System.out.print("❌ Nome não pode estar vazio. Digite novamente: ");
            nome = scanner.nextLine();
        }
        
        // Entrada de idade (int) com validação
        System.out.print("Digite sua idade: ");
        int idade = lerInteiroValido();
        
        while (idade < 0 || idade > 120) {
            System.out.print("❌ Idade inválida (0-120). Digite novamente: ");
            idade = lerInteiroValido();
        }
        
        // Entrada de altura (double)
        System.out.print("Digite sua altura (ex: 1.75): ");
        double altura = lerDoubleValido();
        
        while (altura < 0.5 || altura > 3.0) {
            System.out.print("❌ Altura inválida (0.5-3.0). Digite novamente: ");
            altura = lerDoubleValido();
        }
        
        // Entrada de profissão
        System.out.print("Digite sua profissão: ");
        String profissao = scanner.nextLine();
        
        // Exibir dados cadastrados
        System.out.println("\n✅ CADASTRO REALIZADO COM SUCESSO!");
        System.out.println("━".repeat(40));
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Altura: " + String.format("%.2f", altura) + " metros");
        System.out.println("Profissão: " + profissao);
        
        // Classificação por idade
        if (idade < 18) {
            System.out.println("Categoria: Menor de idade");
        } else if (idade < 60) {
            System.out.println("Categoria: Adulto");
        } else {
            System.out.println("Categoria: Idoso");
        }
        
        // Cálculo do IMC
        double peso;
        System.out.print("\nDeseja calcular o IMC? (s/n): ");
        String calcularIMC = scanner.nextLine().toLowerCase();
        
        if (calcularIMC.equals("s") || calcularIMC.equals("sim")) {
            System.out.print("Digite seu peso (kg): ");
            peso = lerDoubleValido();
            
            double imc = peso / (altura * altura);
            System.out.printf("Seu IMC é: %.2f%n", imc);
            
            if (imc < 18.5) {
                System.out.println("Classificação: Abaixo do peso");
            } else if (imc < 25.0) {
                System.out.println("Classificação: Peso normal");
            } else if (imc < 30.0) {
                System.out.println("Classificação: Sobrepeso");
            } else {
                System.out.println("Classificação: Obesidade");
            }
        }
    }
    
    /**
     * Funcionalidade 2: Calculadora simples
     */
    public static void calculadoraSimples() {
        System.out.println("\n🧮 CALCULADORA SIMPLES");
        System.out.println("-".repeat(30));
        
        System.out.print("Digite o primeiro número: ");
        double num1 = lerDoubleValido();
        
        System.out.print("Digite o segundo número: ");
        double num2 = lerDoubleValido();
        
        System.out.println("\nOperações disponíveis:");
        System.out.println("1. Adição (+)");
        System.out.println("2. Subtração (-)");
        System.out.println("3. Multiplicação (*)");
        System.out.println("4. Divisão (/)");
        System.out.println("5. Potência (^)");
        System.out.print("Escolha a operação: ");
        
        int operacao = lerInteiroValido();
        double resultado = 0;
        String simbolo = "";
        boolean operacaoValida = true;
        
        switch (operacao) {
            case 1:
                resultado = num1 + num2;
                simbolo = "+";
                break;
            case 2:
                resultado = num1 - num2;
                simbolo = "-";
                break;
            case 3:
                resultado = num1 * num2;
                simbolo = "*";
                break;
            case 4:
                if (num2 != 0) {
                    resultado = num1 / num2;
                    simbolo = "/";
                } else {
                    System.out.println("❌ Erro: Divisão por zero!");
                    operacaoValida = false;
                }
                break;
            case 5:
                resultado = Math.pow(num1, num2);
                simbolo = "^";
                break;
            default:
                System.out.println("❌ Operação inválida!");
                operacaoValida = false;
        }
        
        if (operacaoValida) {
            System.out.printf("\n✅ Resultado: %.2f %s %.2f = %.2f%n", 
                            num1, simbolo, num2, resultado);
        }
    }
    
    /**
     * Funcionalidade 3: Análise de notas de alunos
     */
    public static void analiseNotas() {
        System.out.println("\n📊 ANÁLISE DE NOTAS");
        System.out.println("-".repeat(30));
        
        System.out.print("Quantos alunos deseja analisar? ");
        int quantidadeAlunos = lerInteiroValido();
        
        while (quantidadeAlunos <= 0) {
            System.out.print("❌ Quantidade inválida. Digite um número positivo: ");
            quantidadeAlunos = lerInteiroValido();
        }
        
        double somaNotas = 0;
        double maiorNota = -1;
        double menorNota = 11;
        int aprovados = 0;
        
        for (int i = 1; i <= quantidadeAlunos; i++) {
            System.out.printf("\nAluno %d:%n", i);
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Nota (0-10): ");
            double nota = lerDoubleValido();
            
            while (nota < 0 || nota > 10) {
                System.out.print("❌ Nota inválida (0-10). Digite novamente: ");
                nota = lerDoubleValido();
            }
            
            // Atualizar estatísticas
            somaNotas += nota;
            if (nota > maiorNota) maiorNota = nota;
            if (nota < menorNota) menorNota = nota;
            if (nota >= 7.0) aprovados++;
            
            // Classificar aluno
            String conceito;
            if (nota >= 9.0) conceito = "EXCELENTE";
            else if (nota >= 8.0) conceito = "MUITO BOM";
            else if (nota >= 7.0) conceito = "BOM";
            else if (nota >= 6.0) conceito = "REGULAR";
            else conceito = "INSUFICIENTE";
            
            String situacao = nota >= 7.0 ? "APROVADO" : "REPROVADO";
            
            System.out.printf("Aluno: %s | Nota: %.1f | Conceito: %s | %s%n", 
                            nome, nota, conceito, situacao);
        }
        
        // Relatório final
        double media = somaNotas / quantidadeAlunos;
        double percentualAprovacao = (double) aprovados / quantidadeAlunos * 100;
        
        System.out.println("\n📋 RELATÓRIO FINAL");
        System.out.println("━".repeat(30));
        System.out.printf("Total de alunos: %d%n", quantidadeAlunos);
        System.out.printf("Média da turma: %.2f%n", media);
        System.out.printf("Maior nota: %.1f%n", maiorNota);
        System.out.printf("Menor nota: %.1f%n", menorNota);
        System.out.printf("Aprovados: %d (%.1f%%)%n", aprovados, percentualAprovacao);
        System.out.printf("Reprovados: %d%n", quantidadeAlunos - aprovados);
    }
    
    /**
     * Funcionalidade 4: Jogo de adivinhação
     */
    public static void jogoAdivinhacao() {
        System.out.println("\n🎯 JOGO DE ADIVINHAÇÃO");
        System.out.println("-".repeat(30));
        
        int numeroSecreto = (int) (Math.random() * 100) + 1;  // 1 a 100
        int tentativas = 0;
        int maxTentativas = 7;
        boolean acertou = false;
        
        System.out.println("Pensei em um número de 1 a 100!");
        System.out.printf("Você tem %d tentativas para acertar.%n", maxTentativas);
        
        while (tentativas < maxTentativas && !acertou) {
            tentativas++;
            System.out.printf("\nTentativa %d/%d%n", tentativas, maxTentativas);
            System.out.print("Qual é o seu palpite? ");
            
            int palpite = lerInteiroValido();
            
            if (palpite == numeroSecreto) {
                acertou = true;
                System.out.println("🎉 PARABÉNS! Você acertou!");
                System.out.printf("O número era %d e você acertou em %d tentativas!%n", 
                                numeroSecreto, tentativas);
                
                // Classificar performance
                if (tentativas <= 3) {
                    System.out.println("🏆 Performance: EXCELENTE!");
                } else if (tentativas <= 5) {
                    System.out.println("👍 Performance: BOA!");
                } else {
                    System.out.println("😊 Performance: REGULAR!");
                }
                
            } else if (palpite < numeroSecreto) {
                System.out.println("📈 O número é MAIOR que " + palpite);
                
            } else {
                System.out.println("📉 O número é MENOR que " + palpite);
            }
            
            // Dar dicas conforme as tentativas
            if (!acertou && tentativas >= 3) {
                int diferenca = Math.abs(palpite - numeroSecreto);
                if (diferenca <= 5) {
                    System.out.println("🔥 Você está MUITO PERTO!");
                } else if (diferenca <= 15) {
                    System.out.println("👀 Você está perto!");
                }
            }
        }
        
        if (!acertou) {
            System.out.println("\n😔 Suas tentativas acabaram!");
            System.out.printf("O número era %d. Mais sorte na próxima vez!%n", numeroSecreto);
        }
        
        System.out.print("\nDeseja jogar novamente? (s/n): ");
        String jogarNovamente = scanner.nextLine().toLowerCase();
        if (jogarNovamente.equals("s") || jogarNovamente.equals("sim")) {
            jogoAdivinhacao();  // Recursão para jogar novamente
        }
    }
    
    /**
     * Funcionalidade 5: Lista de tarefas simples
     */
    public static void listaTarefas() {
        System.out.println("\n📝 LISTA DE TAREFAS");
        System.out.println("-".repeat(30));
        
        String[] tarefas = new String[10];  // Máximo 10 tarefas
        boolean[] concluidas = new boolean[10];
        int totalTarefas = 0;
        
        int opcao;
        do {
            System.out.println("\n--- GERENCIADOR DE TAREFAS ---");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Listar tarefas");
            System.out.println("3. Marcar como concluída");
            System.out.println("4. Remover tarefa");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = lerInteiroValido();
            
            switch (opcao) {
                case 1: // Adicionar tarefa
                    if (totalTarefas < tarefas.length) {
                        System.out.print("Digite a nova tarefa: ");
                        String novaTarefa = scanner.nextLine();
                        if (!novaTarefa.trim().isEmpty()) {
                            tarefas[totalTarefas] = novaTarefa;
                            concluidas[totalTarefas] = false;
                            totalTarefas++;
                            System.out.println("✅ Tarefa adicionada com sucesso!");
                        } else {
                            System.out.println("❌ Tarefa não pode estar vazia!");
                        }
                    } else {
                        System.out.println("❌ Lista cheia! Máximo 10 tarefas.");
                    }
                    break;
                    
                case 2: // Listar tarefas
                    if (totalTarefas == 0) {
                        System.out.println("📭 Nenhuma tarefa na lista.");
                    } else {
                        System.out.println("\n📋 SUAS TAREFAS:");
                        for (int i = 0; i < totalTarefas; i++) {
                            String status = concluidas[i] ? "✅" : "⏳";
                            System.out.printf("%d. %s %s%n", (i + 1), status, tarefas[i]);
                        }
                        
                        int concluidas_count = 0;
                        for (int i = 0; i < totalTarefas; i++) {
                            if (concluidas[i]) concluidas_count++;
                        }
                        
                        System.out.printf("\nProgresso: %d/%d tarefas concluídas%n", 
                                        concluidas_count, totalTarefas);
                    }
                    break;
                    
                case 3: // Marcar como concluída
                    if (totalTarefas == 0) {
                        System.out.println("📭 Nenhuma tarefa para marcar.");
                    } else {
                        System.out.printf("Digite o número da tarefa (1-%d): ", totalTarefas);
                        int numero = lerInteiroValido();
                        
                        if (numero >= 1 && numero <= totalTarefas) {
                            concluidas[numero - 1] = true;
                            System.out.println("✅ Tarefa marcada como concluída!");
                        } else {
                            System.out.println("❌ Número inválido!");
                        }
                    }
                    break;
                    
                case 4: // Remover tarefa
                    if (totalTarefas == 0) {
                        System.out.println("📭 Nenhuma tarefa para remover.");
                    } else {
                        System.out.printf("Digite o número da tarefa para remover (1-%d): ", totalTarefas);
                        int numero = lerInteiroValido();
                        
                        if (numero >= 1 && numero <= totalTarefas) {
                            // Mover todas as tarefas subsequentes uma posição para trás
                            for (int i = numero - 1; i < totalTarefas - 1; i++) {
                                tarefas[i] = tarefas[i + 1];
                                concluidas[i] = concluidas[i + 1];
                            }
                            totalTarefas--;
                            System.out.println("🗑️ Tarefa removida com sucesso!");
                        } else {
                            System.out.println("❌ Número inválido!");
                        }
                    }
                    break;
                    
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                    
                default:
                    System.out.println("❌ Opção inválida!");
            }
            
        } while (opcao != 0);
    }
    
    /**
     * Funcionalidade 6: Conversor de temperatura
     */
    public static void conversorTemperatura() {
        System.out.println("\n🌡️ CONVERSOR DE TEMPERATURA");
        System.out.println("-".repeat(30));
        
        System.out.print("Digite a temperatura: ");
        double temperatura = lerDoubleValido();
        
        System.out.println("\nEscala de origem:");
        System.out.println("1. Celsius (°C)");
        System.out.println("2. Fahrenheit (°F)");
        System.out.println("3. Kelvin (K)");
        System.out.print("Escolha a escala: ");
        
        int escalaOrigem = lerInteiroValido();
        
        while (escalaOrigem < 1 || escalaOrigem > 3) {
            System.out.print("❌ Escala inválida (1-3). Digite novamente: ");
            escalaOrigem = lerInteiroValido();
        }
        
        // Converter tudo para Celsius primeiro
        double celsius;
        String nomeOrigem;
        
        switch (escalaOrigem) {
            case 1: // Celsius
                celsius = temperatura;
                nomeOrigem = "Celsius";
                break;
            case 2: // Fahrenheit
                celsius = (temperatura - 32) * 5.0 / 9.0;
                nomeOrigem = "Fahrenheit";
                break;
            case 3: // Kelvin
                celsius = temperatura - 273.15;
                nomeOrigem = "Kelvin";
                break;
            default:
                celsius = temperatura;
                nomeOrigem = "Celsius";
        }
        
        // Calcular todas as conversões
        double fahrenheit = celsius * 9.0 / 5.0 + 32;
        double kelvin = celsius + 273.15;
        
        System.out.printf("\n🌡️ CONVERSÕES PARA %.2f° %s:%n", temperatura, nomeOrigem);
        System.out.println("━".repeat(40));
        System.out.printf("Celsius:    %.2f °C%n", celsius);
        System.out.printf("Fahrenheit: %.2f °F%n", fahrenheit);
        System.out.printf("Kelvin:     %.2f K%n", kelvin);
        
        // Informações extras sobre a temperatura
        System.out.println("\n📋 Informações interessantes:");
        if (celsius <= 0) {
            System.out.println("❄️ Temperatura de congelamento da água ou menor");
        } else if (celsius >= 100) {
            System.out.println("🔥 Temperatura de ebulição da água ou maior");
        } else if (celsius >= 37) {
            System.out.println("🌡️ Temperatura corporal ou maior");
        } else if (celsius >= 20 && celsius <= 25) {
            System.out.println("😊 Temperatura ambiente agradável");
        }
    }
    
    // ========================= MÉTODOS AUXILIARES =========================
    
    /**
     * Lê um número inteiro válido, tratando erros de entrada
     * @return número inteiro válido
     */
    public static int lerInteiroValido() {
        while (true) {
            try {
                String entrada = scanner.nextLine();
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida. Digite um número inteiro: ");
            }
        }
    }
    
    /**
     * Lê um número decimal válido, tratando erros de entrada
     * @return número decimal válido
     */
    public static double lerDoubleValido() {
        while (true) {
            try {
                String entrada = scanner.nextLine().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida. Digite um número decimal: ");
            }
        }
    }
}