/**
 * Exemplo prático de Abstração - Interface
 * 
 * Esta interface demonstra conceitos fundamentais:
 * - Definição de contratos que classes devem seguir
 * - Métodos abstratos (implicitamente public abstract)
 * - Constantes (implicitamente public static final)
 * - Métodos default (Java 8+)
 * - Métodos static (Java 8+)
 * - Múltipla implementação
 * 
 * @author Curso POO Java
 */
public interface Dispositivo {
    
    // ===== CONSTANTES DA INTERFACE =====
    // Em interfaces, todas as variáveis são implicitamente:
    // public static final (constantes)
    
    String FABRICANTE_PADRAO = "TechCorp";
    int VOLTAGEM_PADRAO = 110;
    double VERSAO_MINIMA_SO = 1.0;
    int GARANTIA_MESES = 12;
    
    // Status possíveis do dispositivo
    String STATUS_LIGADO = "LIGADO";
    String STATUS_DESLIGADO = "DESLIGADO";
    String STATUS_STANDBY = "EM_STANDBY";
    String STATUS_ERRO = "ERRO";
    
    // ===== MÉTODOS ABSTRATOS =====
    // Em interfaces, todos os métodos são implicitamente:
    // public abstract (até Java 8)
    
    /**
     * Liga o dispositivo
     * Cada dispositivo implementa sua própria forma de ligar
     */
    void ligar();
    
    /**
     * Desliga o dispositivo
     * Cada dispositivo implementa sua própria forma de desligar
     */
    void desligar();
    
    /**
     * Reinicia o dispositivo
     * Processo pode variar conforme o tipo de dispositivo
     */
    void reiniciar();
    
    /**
     * Verifica o status atual do dispositivo
     * @return Status atual (LIGADO, DESLIGADO, etc.)
     */
    String obterStatus();
    
    /**
     * Verifica o nível de bateria
     * @return Percentual de bateria (0-100)
     */
    int obterNivelBateria();
    
    /**
     * Executa autodiagnóstico do dispositivo
     * @return true se o dispositivo está funcionando corretamente
     */
    boolean executarDiagnostico();
    
    /**
     * Atualiza o software/firmware do dispositivo
     * @param versao Nova versão do software
     * @return true se a atualização foi bem-sucedida
     */
    boolean atualizarSoftware(String versao);
    
    /**
     * Conecta a uma rede
     * @param nomeRede Nome da rede para conectar
     * @param senha Senha da rede (se necessária)
     * @return true se conectou com sucesso
     */
    boolean conectarRede(String nomeRede, String senha);
    
    /**
     * Desconecta da rede atual
     */
    void desconectarRede();
    
    /**
     * Verifica se está conectado à rede
     * @return true se conectado
     */
    boolean estaConectado();
    
    // ===== MÉTODOS DEFAULT (Java 8+) =====
    // Métodos com implementação padrão que podem ser sobrescritos
    
    /**
     * Método default para colocar dispositivo em modo standby
     * Implementação padrão que pode ser usada ou sobrescrita
     */
    default void entrarStandby() {
        System.out.println("📱 " + obterModelo() + " entrando em modo standby...");
        // Implementação básica - pode ser sobrescrita se necessário
    }
    
    /**
     * Método default para sair do modo standby
     */
    default void sairStandby() {
        System.out.println("📱 " + obterModelo() + " saindo do modo standby...");
    }
    
    /**
     * Método default para exibir informações básicas
     * Usa outros métodos da interface para compor informações
     */
    default void exibirInformacoes() {
        System.out.println("\n=== Informações do Dispositivo ===");
        System.out.println("Modelo: " + obterModelo());
        System.out.println("Status: " + obterStatus());
        System.out.println("Bateria: " + obterNivelBateria() + "%");
        System.out.println("Conectado: " + (estaConectado() ? "Sim" : "Não"));
        System.out.println("================================\n");
    }
    
    /**
     * Método default para carregar bateria
     * @param tempo Tempo de carregamento em minutos
     */
    default void carregarBateria(int tempo) {
        System.out.printf("🔋 Carregando %s por %d minutos...%n", obterModelo(), tempo);
        
        // Simulação básica de carregamento
        int nivelAtual = obterNivelBateria();
        int incremento = Math.min(tempo / 5, 100 - nivelAtual); // 1% por 5 minutos
        
        System.out.printf("   Bateria: %d%% → %d%%%n", nivelAtual, nivelAtual + incremento);
    }
    
    /**
     * Método default para verificar se precisa de atualização
     * @param versaoAtual Versão atual do software
     * @return true se precisa atualizar
     */
    default boolean precisaAtualizacao(String versaoAtual) {
        // Lógica básica - pode ser sobrescrita para algo mais específico
        try {
            double versaoNum = Double.parseDouble(versaoAtual);
            return versaoNum < VERSAO_MINIMA_SO;
        } catch (NumberFormatException e) {
            return true; // Se não conseguir interpretar, assume que precisa
        }
    }
    
    /**
     * Método default para resetar configurações de fábrica
     */
    default void resetarConfiguracoes() {
        System.out.println("🔄 Resetando " + obterModelo() + " para configurações de fábrica...");
        desconectarRede();
        // Outras operações de reset podem ser adicionadas
        System.out.println("✓ Reset concluído!");
    }
    
    // ===== MÉTODOS STATIC (Java 8+) =====
    // Métodos utilitários relacionados à interface
    
    /**
     * Método static para validar nome de rede
     * @param nomeRede Nome da rede a ser validado
     * @return true se o nome é válido
     */
    static boolean validarNomeRede(String nomeRede) {
        return nomeRede != null && 
               !nomeRede.trim().isEmpty() && 
               nomeRede.length() <= 32;
    }
    
    /**
     * Método static para verificar se senha é forte
     * @param senha Senha a ser verificada
     * @return true se a senha é considerada forte
     */
    static boolean senhaSegura(String senha) {
        if (senha == null || senha.length() < 8) {
            return false;
        }
        
        boolean temMaiuscula = senha.matches(".*[A-Z].*");
        boolean temMinuscula = senha.matches(".*[a-z].*");
        boolean temNumero = senha.matches(".*\\d.*");
        boolean temEspecial = senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        
        return temMaiuscula && temMinuscula && temNumero && temEspecial;
    }
    
    /**
     * Método static para calcular consumo de energia estimado
     * @param potenciaWatts Potência do dispositivo em watts
     * @param horasUso Horas de uso por dia
     * @return Consumo mensal estimado em kWh
     */
    static double calcularConsumoMensal(double potenciaWatts, double horasUso) {
        double consumoDiario = (potenciaWatts * horasUso) / 1000; // kWh por dia
        return consumoDiario * 30; // kWh por mês
    }
    
    /**
     * Método static para gerar ID único para dispositivo
     * @param fabricante Nome do fabricante
     * @param modelo Nome do modelo
     * @return ID único gerado
     */
    static String gerarIdDispositivo(String fabricante, String modelo) {
        long timestamp = System.currentTimeMillis();
        return String.format("%s-%s-%d", 
                           fabricante.toUpperCase().substring(0, Math.min(3, fabricante.length())),
                           modelo.toUpperCase().replaceAll("[^A-Z0-9]", "").substring(0, Math.min(5, modelo.length())),
                           timestamp % 100000);
    }
    
    /**
     * Método static para comparar versões de software
     * @param versao1 Primeira versão (ex: "2.1.3")
     * @param versao2 Segunda versão (ex: "2.1.4")
     * @return negativo se versao1 < versao2, 0 se igual, positivo se versao1 > versao2
     */
    static int compararVersoes(String versao1, String versao2) {
        String[] v1 = versao1.split("\\.");
        String[] v2 = versao2.split("\\.");
        
        int maxLength = Math.max(v1.length, v2.length);
        
        for (int i = 0; i < maxLength; i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            
            if (num1 != num2) {
                return Integer.compare(num1, num2);
            }
        }
        
        return 0; // Versões são iguais
    }
    
    // ===== MÉTODOS ABSTRATOS ADICIONAIS =====
    // Métodos que devem ser implementados para identificação
    
    /**
     * Obtém o modelo do dispositivo
     * @return Nome do modelo
     */
    String obterModelo();
    
    /**
     * Obtém o número de série do dispositivo
     * @return Número de série único
     */
    String obterNumeroSerie();
    
    /**
     * Obtém a versão do software/firmware
     * @return Versão atual do software
     */
    String obterVersaoSoftware();
}