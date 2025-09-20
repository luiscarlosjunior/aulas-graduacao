/**
 * Demonstração de Estruturas Condicionais em Java
 * 
 * Este programa demonstra como usar estruturas condicionais (if, else if, else)
 * para criar programas que tomam decisões baseadas em diferentes condições.
 * 
 * Estruturas condicionais permitem que o programa execute diferentes códigos
 * dependendo de condições verdadeiras ou falsas.
 * 
 * @author luiscaparroz
 * @version 2.0
 * @since JDK 1.8
 */
public class TestaCondicional {

    /**
     * Método principal que demonstra várias formas de usar estruturas condicionais
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO DE ESTRUTURAS CONDICIONAIS ===\n");

        // ==================== EXEMPLO 1: Sistema de Controle de Acesso ====================
        
        System.out.println("EXEMPLO 1: Sistema de Controle de Acesso");
        
        // Variáveis para o exemplo
        int idade = 18;                    // Idade da pessoa
        int quantidadePessoas = 3;         // Quantas pessoas estão acompanhando
        boolean temAutorizacao = true;     // Se tem autorização especial
        
        System.out.println("Dados da pessoa:");
        System.out.println("- Idade: " + idade + " anos");
        System.out.println("- Quantidade de acompanhantes: " + quantidadePessoas);
        System.out.println("- Tem autorização especial: " + temAutorizacao);
        System.out.println();
        
        // Estrutura condicional principal
        if (idade >= 18) {
            // Se a pessoa tem 18 anos ou mais, pode entrar
            System.out.println("✓ Você tem " + idade + " anos (maior de idade)");
            System.out.println("✓ Seja bem-vindo!");
            
        } else {
            // Se a pessoa tem menos de 18 anos, verificamos outras condições
            System.out.println("✗ Você tem " + idade + " anos (menor de idade)");
            
            // Estrutura condicional aninhada (if dentro de else)
            if (quantidadePessoas >= 2) {
                // Menor de idade mas acompanhado por pelo menos 2 pessoas
                System.out.println("✓ Você não tem 18 anos, mas pode entrar");
                System.out.println("✓ Motivo: está acompanhado por " + quantidadePessoas + " pessoas");
                
            } else if (temAutorizacao) {
                // Menor de idade, pouco acompanhado, mas tem autorização
                System.out.println("✓ Você pode entrar com autorização especial");
                
            } else {
                // Menor de idade, pouco acompanhado e sem autorização
                System.out.println("✗ Infelizmente você não pode entrar");
                System.out.println("✗ Motivo: menor de idade e sem acompanhantes suficientes");
            }
        }
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // ==================== EXEMPLO 2: Sistema de Notas ====================
        
        System.out.println("EXEMPLO 2: Sistema de Avaliação de Notas");
        
        double nota = 8.5;  // Nota do aluno
        System.out.println("Nota do aluno: " + nota);
        
        // Múltiplas condições com else if
        if (nota >= 9.0) {
            System.out.println("🏆 Conceito: EXCELENTE (A)");
            System.out.println("Parabéns! Performance excepcional!");
            
        } else if (nota >= 8.0) {
            System.out.println("😊 Conceito: MUITO BOM (B)");
            System.out.println("Ótimo trabalho! Continue assim!");
            
        } else if (nota >= 7.0) {
            System.out.println("🙂 Conceito: BOM (C)");
            System.out.println("Bom trabalho! Há espaço para melhorar.");
            
        } else if (nota >= 6.0) {
            System.out.println("😐 Conceito: REGULAR (D)");
            System.out.println("Precisa estudar mais para melhorar.");
            
        } else {
            System.out.println("😟 Conceito: INSUFICIENTE (F)");
            System.out.println("É necessário se dedicar mais aos estudos.");
        }
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // ==================== EXEMPLO 3: Operadores Lógicos ====================
        
        System.out.println("EXEMPLO 3: Usando Operadores Lógicos");
        
        boolean temDinheiro = true;
        boolean temTempo = false;
        boolean temVontade = true;
        
        System.out.println("Condições para viajar:");
        System.out.println("- Tem dinheiro: " + temDinheiro);
        System.out.println("- Tem tempo: " + temTempo);
        System.out.println("- Tem vontade: " + temVontade);
        System.out.println();
        
        // Usando operador AND (&&) - todas as condições devem ser verdadeiras
        if (temDinheiro && temTempo && temVontade) {
            System.out.println("🌟 Pode viajar! Todas as condições foram atendidas.");
            
        // Usando operador OR (||) - pelo menos uma condição deve ser verdadeira  
        } else if (temDinheiro || temTempo) {
            System.out.println("🤔 Talvez possa viajar, mas precisa resolver algumas condições.");
            
            // Analisando cada condição que falta
            if (!temDinheiro) {
                System.out.println("  - Precisa juntar dinheiro");
            }
            if (!temTempo) {
                System.out.println("  - Precisa organizar o tempo");
            }
            if (!temVontade) {
                System.out.println("  - Precisa de motivação para viajar");
            }
            
        } else {
            System.out.println("😔 Não pode viajar no momento.");
        }
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // ==================== EXEMPLO 4: Condições com Cálculos ====================
        
        System.out.println("EXEMPLO 4: Calculadora de IMC com Condicionais");
        
        double peso = 70.5;      // kg
        double altura = 1.75;    // metros
        double imc = peso / (altura * altura);  // Cálculo do IMC
        
        System.out.println("Dados pessoais:");
        System.out.println("- Peso: " + peso + " kg");
        System.out.println("- Altura: " + altura + " m");
        System.out.println("- IMC calculado: " + String.format("%.2f", imc));
        System.out.println();
        
        // Classificação do IMC usando condicionais
        if (imc < 18.5) {
            System.out.println("📊 Classificação: ABAIXO DO PESO");
            System.out.println("💡 Recomendação: Consulte um nutricionista");
            
        } else if (imc < 25.0) {
            System.out.println("📊 Classificação: PESO NORMAL");
            System.out.println("💡 Recomendação: Mantenha hábitos saudáveis");
            
        } else if (imc < 30.0) {
            System.out.println("📊 Classificação: SOBREPESO");
            System.out.println("💡 Recomendação: Considere exercícios e dieta");
            
        } else {
            System.out.println("📊 Classificação: OBESIDADE");
            System.out.println("💡 Recomendação: Procure orientação médica");
        }
        
        System.out.println("\n=== RESUMO DOS OPERADORES CONDICIONAIS ===");
        System.out.println("• if: executa código SE a condição for verdadeira");
        System.out.println("• else if: testa uma nova condição SE a anterior for falsa");
        System.out.println("• else: executa código SE todas as condições anteriores forem falsas");
        System.out.println("• && (AND): todas as condições devem ser verdadeiras");
        System.out.println("• || (OR): pelo menos uma condição deve ser verdadeira");
        System.out.println("• ! (NOT): inverte o valor da condição (true vira false)");
    }
}