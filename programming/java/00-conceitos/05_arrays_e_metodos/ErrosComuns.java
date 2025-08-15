/**
 * Exemplos de Erros Comuns em Java - Para Aprendizado
 * 
 * Este arquivo contém exemplos de erros comuns que iniciantes cometem
 * ao programar em Java, junto com suas correções e explicações.
 * 
 * IMPORTANTE: Este arquivo propositalmente contém código comentado
 * com erros para fins educacionais. Descomente uma seção por vez
 * para ver os erros e aprender como corrigi-los.
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
public class ErrosComuns {

    public static void main(String[] args) {
        
        System.out.println("=== EXEMPLOS DE ERROS COMUNS E SUAS CORREÇÕES ===\n");
        
        // Execute um exemplo por vez descomentando as linhas abaixo
        
        exemploErrosCorrigidos();
        // exemploErro1_NomeClasse();
        // exemploErro2_TiposVariaveis();
        // exemploErro3_DivisaoPorZero();
        // exemploErro4_ArrayIndexOutOfBounds();
        // exemploErro5_NullPointerException();
        
        System.out.println("\n=== DICAS PARA EVITAR ERROS ===");
        dicasParaEvitarErros();
    }
    
    /**
     * Exemplos de código correto vs incorreto (corrigido)
     */
    public static void exemploErrosCorrigidos() {
        System.out.println("CÓDIGO CORRETO - Exemplos funcionando:");
        
        // ✅ CORRETO: Declaração de variáveis
        int idade = 25;
        String nome = "João";
        boolean ativo = true;
        
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Ativo: " + ativo);
        
        // ✅ CORRETO: Operações matemáticas seguras
        int a = 10;
        int b = 3;
        double resultado = (double) a / b;  // Casting para double
        System.out.println("Divisão: " + a + " / " + b + " = " + String.format("%.2f", resultado));
        
        // ✅ CORRETO: Verificação antes de usar arrays
        int[] numeros = {1, 2, 3, 4, 5};
        if (numeros.length > 0) {
            System.out.println("Primeiro elemento: " + numeros[0]);
            System.out.println("Último elemento: " + numeros[numeros.length - 1]);
        }
        
        // ✅ CORRETO: Verificação de null
        String texto = "Hello World";
        if (texto != null && !texto.isEmpty()) {
            System.out.println("Texto em maiúsculas: " + texto.toUpperCase());
        }
    }
    
    /*
     * ERRO 1: Nome da classe diferente do arquivo
     * 
     * ❌ INCORRETO:
     * public class MinhaClasse {  // Arquivo se chama ErrosComuns.java
     * 
     * ✅ CORRETO:
     * public class ErrosComuns {  // Nome igual ao arquivo
     * 
     * EXPLICAÇÃO: Em Java, o nome da classe pública deve ser EXATAMENTE
     * igual ao nome do arquivo (sem o .java).
     */
    public static void exemploErro1_NomeClasse() {
        System.out.println("ERRO 1: Nome da classe deve ser igual ao nome do arquivo");
        System.out.println("❌ Se o arquivo é 'MinhaClasse.java', a classe deve ser 'MinhaClasse'");
        System.out.println("✅ Sempre mantenha nomes consistentes!");
    }
    
    /*
     * ERRO 2: Problemas com tipos de variáveis
     */
    public static void exemploErro2_TiposVariaveis() {
        System.out.println("ERRO 2: Problemas com tipos de variáveis");
        
        // ❌ INCORRETO: Tentar armazenar decimal em int
        // int valor = 3.14;  // ERRO: não compila
        
        // ✅ CORRETO: Usar tipo apropriado
        double valor = 3.14;
        System.out.println("Valor decimal: " + valor);
        
        // ❌ INCORRETO: Esquecer do 'f' para float
        // float altura = 1.75;  // ERRO: Java assume double
        
        // ✅ CORRETO: Adicionar 'f' para float
        float altura = 1.75f;
        System.out.println("Altura: " + altura);
        
        // ❌ INCORRETO: Usar aspas duplas para char
        // char letra = "A";  // ERRO: aspas duplas são para String
        
        // ✅ CORRETO: Usar aspas simples para char
        char letra = 'A';
        System.out.println("Letra: " + letra);
        
        System.out.println("💡 DICA: int→números inteiros, double→decimais, String→texto, char→um caractere");
    }
    
    /*
     * ERRO 3: Divisão por zero
     */
    public static void exemploErro3_DivisaoPorZero() {
        System.out.println("ERRO 3: Divisão por zero");
        
        int dividendo = 10;
        int divisor = 0;
        
        // ❌ INCORRETO: Dividir sem verificar
        // int resultado = dividendo / divisor;  // ERRO: ArithmeticException
        
        // ✅ CORRETO: Verificar antes de dividir
        if (divisor != 0) {
            double resultado = (double) dividendo / divisor;
            System.out.println("Resultado: " + resultado);
        } else {
            System.out.println("❌ ERRO: Não é possível dividir por zero!");
            System.out.println("💡 SOLUÇÃO: Sempre verificar se o divisor é diferente de zero");
        }
        
        // Exemplo com entrada do usuário
        System.out.println("\n📝 Exemplo prático: Sempre validar entrada do usuário");
        System.out.println("Scanner entrada = new Scanner(System.in);");
        System.out.println("int divisor = entrada.nextInt();");
        System.out.println("if (divisor != 0) { /* fazer divisão */ }");
        System.out.println("else { /* mostrar erro */ }");
    }
    
    /*
     * ERRO 4: Acessar índice inválido de array
     */
    public static void exemploErro4_ArrayIndexOutOfBounds() {
        System.out.println("ERRO 4: ArrayIndexOutOfBoundsException");
        
        int[] numeros = {10, 20, 30};  // Índices válidos: 0, 1, 2
        
        System.out.println("Array tem " + numeros.length + " elementos");
        System.out.println("Índices válidos: 0 até " + (numeros.length - 1));
        
        // ❌ INCORRETO: Acessar índice que não existe
        // int valor = numeros[3];  // ERRO: índice 3 não existe (só vai até 2)
        
        // ✅ CORRETO: Verificar se índice é válido
        int indice = 3;
        if (indice >= 0 && indice < numeros.length) {
            int valor = numeros[indice];
            System.out.println("Valor no índice " + indice + ": " + valor);
        } else {
            System.out.println("❌ ERRO: Índice " + indice + " é inválido!");
            System.out.println("💡 SOLUÇÃO: Índices vão de 0 até (tamanho - 1)");
        }
        
        // Exemplo seguro para percorrer array
        System.out.println("\n✅ Forma segura de percorrer array:");
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Posição " + i + ": " + numeros[i]);
        }
    }
    
    /*
     * ERRO 5: NullPointerException
     */
    public static void exemploErro5_NullPointerException() {
        System.out.println("ERRO 5: NullPointerException");
        
        String texto = null;  // Variável não inicializada
        
        // ❌ INCORRETO: Usar variável null sem verificar
        // int tamanho = texto.length();  // ERRO: NullPointerException
        
        // ✅ CORRETO: Verificar se não é null antes de usar
        if (texto != null) {
            int tamanho = texto.length();
            System.out.println("Tamanho do texto: " + tamanho);
        } else {
            System.out.println("❌ ERRO: Texto está null (vazio/não inicializado)");
            System.out.println("💡 SOLUÇÃO: Sempre verificar if (variavel != null)");
        }
        
        // Exemplo com inicialização
        System.out.println("\n✅ Exemplo corrigido:");
        String textoCorreto = "Hello World";
        if (textoCorreto != null && !textoCorreto.isEmpty()) {
            System.out.println("Texto: " + textoCorreto);
            System.out.println("Tamanho: " + textoCorreto.length());
            System.out.println("Maiúsculas: " + textoCorreto.toUpperCase());
        }
    }
    
    /**
     * Dicas gerais para evitar erros comuns
     */
    public static void dicasParaEvitarErros() {
        System.out.println("1. 📝 SEMPRE compile antes de executar:");
        System.out.println("   javac MinhaClasse.java");
        System.out.println("   java MinhaClasse");
        
        System.out.println("\n2. 🔍 LEIA as mensagens de erro com atenção:");
        System.out.println("   - Linha do erro");
        System.out.println("   - Tipo do erro");
        System.out.println("   - Descrição do problema");
        
        System.out.println("\n3. ✅ VERIFICAÇÕES importantes:");
        System.out.println("   - Divisor != 0 antes de dividir");
        System.out.println("   - Índice válido antes de acessar array");
        System.out.println("   - Variável != null antes de usar");
        
        System.out.println("\n4. 🏷️ NOMENCLATURA correta:");
        System.out.println("   - Classes: PascalCase (MinhaClasse)");
        System.out.println("   - Variáveis: camelCase (minhaVariavel)");
        System.out.println("   - Nome da classe = nome do arquivo");
        
        System.out.println("\n5. 🔧 TIPOS de dados corretos:");
        System.out.println("   - int para números inteiros");
        System.out.println("   - double para números decimais");
        System.out.println("   - String para texto (aspas duplas)");
        System.out.println("   - char para um caractere (aspas simples)");
        System.out.println("   - boolean para true/false");
        
        System.out.println("\n6. 🆘 QUANDO DER ERRO:");
        System.out.println("   - Não entre em pânico!");
        System.out.println("   - Leia a mensagem de erro");
        System.out.println("   - Verifique a linha indicada");
        System.out.println("   - Compare com exemplos que funcionam");
        System.out.println("   - Teste pequenas mudanças por vez");
        
        System.out.println("\n💡 LEMBRE-SE: Erros fazem parte do aprendizado!");
        System.out.println("Todo programador experiente já cometeu estes erros. 😊");
    }
}