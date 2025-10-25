import java.util.*;

/**
 * Exemplo de Enumerações com Construtores e Campos
 * 
 * Demonstra como enums podem ter campos privados e construtores,
 * permitindo associar dados específicos a cada constante.
 * 
 * @author Aulas Graduação
 */
public class ExemploEnumComConstrutores {
    
    /**
     * Enum representando tamanhos de vestuário
     */
    enum TamanhoRoupa {
        PP("Extra Pequeno", 36, 38),
        P("Pequeno", 38, 40),
        M("Médio", 40, 42),
        G("Grande", 42, 44),
        GG("Extra Grande", 44, 46),
        XGG("Extra Extra Grande", 46, 48);
        
        private final String descricao;
        private final int tamanhoMin;
        private final int tamanhoMax;
        
        // Construtor é sempre privado (implícito ou explícito)
        TamanhoRoupa(String descricao, int tamanhoMin, int tamanhoMax) {
            this.descricao = descricao;
            this.tamanhoMin = tamanhoMin;
            this.tamanhoMax = tamanhoMax;
        }
        
        public String getDescricao() {
            return descricao;
        }
        
        public int getTamanhoMin() {
            return tamanhoMin;
        }
        
        public int getTamanhoMax() {
            return tamanhoMax;
        }
        
        /**
         * Encontra tamanho apropriado para um número
         */
        public static TamanhoRoupa encontrarPorNumero(int numero) {
            for (TamanhoRoupa tamanho : values()) {
                if (numero >= tamanho.tamanhoMin && numero <= tamanho.tamanhoMax) {
                    return tamanho;
                }
            }
            return null;
        }
        
        @Override
        public String toString() {
            return name() + " - " + descricao + " (" + tamanhoMin + "-" + tamanhoMax + ")";
        }
    }
    
    /**
     * Enum representando formas de pagamento
     */
    enum FormaPagamento {
        DINHEIRO("Dinheiro", 0.0, true),
        DEBITO("Cartão de Débito", 0.0, true),
        CREDITO_VISTA("Crédito à Vista", 0.0, true),
        CREDITO_PARCELADO("Crédito Parcelado", 2.5, false),
        PIX("PIX", -5.0, true),
        BOLETO("Boleto", 0.0, true),
        CARTEIRA_DIGITAL("Carteira Digital", -3.0, true);
        
        private final String nome;
        private final double taxaPercentual;
        private final boolean instantaneo;
        
        FormaPagamento(String nome, double taxaPercentual, boolean instantaneo) {
            this.nome = nome;
            this.taxaPercentual = taxaPercentual;
            this.instantaneo = instantaneo;
        }
        
        public String getNome() {
            return nome;
        }
        
        public double getTaxaPercentual() {
            return taxaPercentual;
        }
        
        public boolean isInstantaneo() {
            return instantaneo;
        }
        
        /**
         * Calcula valor final com taxa
         */
        public double calcularValorFinal(double valorBase) {
            return valorBase * (1 + taxaPercentual / 100.0);
        }
        
        /**
         * Verifica se tem desconto
         */
        public boolean temDesconto() {
            return taxaPercentual < 0;
        }
        
        /**
         * Retorna formas de pagamento instantâneas
         */
        public static List<FormaPagamento> getFormasInstantaneas() {
            List<FormaPagamento> formas = new ArrayList<>();
            for (FormaPagamento forma : values()) {
                if (forma.isInstantaneo()) {
                    formas.add(forma);
                }
            }
            return formas;
        }
    }
    
    /**
     * Enum representando tipos de arquivo com extensões
     */
    enum TipoArquivo {
        IMAGEM_PNG("Imagem PNG", ".png", "image/png", 10 * 1024 * 1024),
        IMAGEM_JPG("Imagem JPEG", ".jpg", "image/jpeg", 10 * 1024 * 1024),
        DOCUMENTO_PDF("Documento PDF", ".pdf", "application/pdf", 50 * 1024 * 1024),
        DOCUMENTO_DOC("Documento Word", ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", 25 * 1024 * 1024),
        PLANILHA_XLS("Planilha Excel", ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", 25 * 1024 * 1024),
        VIDEO_MP4("Vídeo MP4", ".mp4", "video/mp4", 100 * 1024 * 1024),
        AUDIO_MP3("Áudio MP3", ".mp3", "audio/mpeg", 10 * 1024 * 1024),
        TEXTO("Arquivo de Texto", ".txt", "text/plain", 5 * 1024 * 1024);
        
        private final String descricao;
        private final String extensao;
        private final String mimeType;
        private final long tamanhoMaximoBytes;
        
        TipoArquivo(String descricao, String extensao, String mimeType, long tamanhoMaximoBytes) {
            this.descricao = descricao;
            this.extensao = extensao;
            this.mimeType = mimeType;
            this.tamanhoMaximoBytes = tamanhoMaximoBytes;
        }
        
        public String getDescricao() {
            return descricao;
        }
        
        public String getExtensao() {
            return extensao;
        }
        
        public String getMimeType() {
            return mimeType;
        }
        
        public long getTamanhoMaximoBytes() {
            return tamanhoMaximoBytes;
        }
        
        /**
         * Verifica se arquivo está dentro do tamanho permitido
         */
        public boolean validarTamanho(long tamanhoBytes) {
            return tamanhoBytes <= tamanhoMaximoBytes;
        }
        
        /**
         * Formata tamanho máximo em MB
         */
        public String getTamanhoMaximoFormatado() {
            return String.format("%.1f MB", tamanhoMaximoBytes / (1024.0 * 1024.0));
        }
        
        /**
         * Encontra tipo de arquivo pela extensão
         */
        public static TipoArquivo encontrarPorExtensao(String extensao) {
            for (TipoArquivo tipo : values()) {
                if (tipo.extensao.equalsIgnoreCase(extensao)) {
                    return tipo;
                }
            }
            return null;
        }
    }
    
    /**
     * Enum representando níveis de acesso em sistema
     */
    enum NivelAcesso {
        VISITANTE(0, "Acesso apenas leitura", false, false, false),
        USUARIO(1, "Acesso básico ao sistema", true, false, false),
        MODERADOR(2, "Pode moderar conteúdo", true, true, false),
        ADMINISTRADOR(3, "Controle total", true, true, true);
        
        private final int nivel;
        private final String descricao;
        private final boolean podeEditar;
        private final boolean podeModerar;
        private final boolean podeAdministrar;
        
        NivelAcesso(int nivel, String descricao, boolean podeEditar, 
                   boolean podeModerar, boolean podeAdministrar) {
            this.nivel = nivel;
            this.descricao = descricao;
            this.podeEditar = podeEditar;
            this.podeModerar = podeModerar;
            this.podeAdministrar = podeAdministrar;
        }
        
        public int getNivel() {
            return nivel;
        }
        
        public String getDescricao() {
            return descricao;
        }
        
        public boolean podeEditar() {
            return podeEditar;
        }
        
        public boolean podeModerar() {
            return podeModerar;
        }
        
        public boolean podeAdministrar() {
            return podeAdministrar;
        }
        
        /**
         * Verifica se tem permissão maior ou igual ao nível especificado
         */
        public boolean temPermissao(NivelAcesso nivelRequerido) {
            return this.nivel >= nivelRequerido.nivel;
        }
        
        /**
         * Lista todas as permissões do nível
         */
        public List<String> getPermissoes() {
            List<String> permissoes = new ArrayList<>();
            permissoes.add("Visualizar");
            if (podeEditar) permissoes.add("Editar");
            if (podeModerar) permissoes.add("Moderar");
            if (podeAdministrar) permissoes.add("Administrar");
            return permissoes;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== ENUMERAÇÕES COM CONSTRUTORES E CAMPOS ===\n");
        
        exemploTamanhoRoupa();
        System.out.println();
        
        exemploFormaPagamento();
        System.out.println();
        
        exemploTipoArquivo();
        System.out.println();
        
        exemploNivelAcesso();
    }
    
    /**
     * Demonstra enum TamanhoRoupa
     */
    private static void exemploTamanhoRoupa() {
        System.out.println("--- TAMANHOS DE ROUPA ---");
        
        // Lista todos os tamanhos
        System.out.println("Tamanhos disponíveis:");
        for (TamanhoRoupa tamanho : TamanhoRoupa.values()) {
            System.out.println("  " + tamanho);
        }
        
        // Encontra tamanho por número
        int numeroBusca = 41;
        TamanhoRoupa encontrado = TamanhoRoupa.encontrarPorNumero(numeroBusca);
        if (encontrado != null) {
            System.out.println("\nPara tamanho " + numeroBusca + ": " + encontrado.name() + 
                             " - " + encontrado.getDescricao());
        }
    }
    
    /**
     * Demonstra enum FormaPagamento
     */
    private static void exemploFormaPagamento() {
        System.out.println("--- FORMAS DE PAGAMENTO ---");
        
        double valorCompra = 1000.00;
        
        System.out.println("Valor da compra: R$ " + String.format("%.2f", valorCompra));
        System.out.println("\nFormas de pagamento:");
        
        for (FormaPagamento forma : FormaPagamento.values()) {
            double valorFinal = forma.calcularValorFinal(valorCompra);
            String status = forma.isInstantaneo() ? "Instantâneo" : "Processamento necessário";
            
            System.out.printf("  %s: R$ %.2f (%s)\n", 
                            forma.getNome(), valorFinal, status);
            
            if (forma.temDesconto()) {
                System.out.println("    → Desconto de " + Math.abs(forma.getTaxaPercentual()) + "%");
            } else if (forma.getTaxaPercentual() > 0) {
                System.out.println("    → Taxa de " + forma.getTaxaPercentual() + "%");
            }
        }
        
        // Lista formas instantâneas
        System.out.println("\nFormas de pagamento instantâneas:");
        for (FormaPagamento forma : FormaPagamento.getFormasInstantaneas()) {
            System.out.println("  " + forma.getNome());
        }
    }
    
    /**
     * Demonstra enum TipoArquivo
     */
    private static void exemploTipoArquivo() {
        System.out.println("--- TIPOS DE ARQUIVO ---");
        
        System.out.println("Tipos suportados:");
        for (TipoArquivo tipo : TipoArquivo.values()) {
            System.out.printf("  %s (%s) - Max: %s - MIME: %s\n",
                            tipo.getDescricao(),
                            tipo.getExtensao(),
                            tipo.getTamanhoMaximoFormatado(),
                            tipo.getMimeType());
        }
        
        // Valida tamanho de arquivo
        System.out.println("\nValidando tamanho de arquivo:");
        TipoArquivo tipo = TipoArquivo.IMAGEM_PNG;
        long tamanhoTeste = 8 * 1024 * 1024; // 8 MB
        
        System.out.printf("Arquivo: %s de %.1f MB\n", 
                        tipo.getExtensao(), 
                        tamanhoTeste / (1024.0 * 1024.0));
        System.out.println("Válido: " + tipo.validarTamanho(tamanhoTeste));
        
        // Busca por extensão
        String extensaoBusca = ".pdf";
        TipoArquivo encontrado = TipoArquivo.encontrarPorExtensao(extensaoBusca);
        if (encontrado != null) {
            System.out.println("\nTipo encontrado para " + extensaoBusca + ": " + 
                             encontrado.getDescricao());
        }
    }
    
    /**
     * Demonstra enum NivelAcesso
     */
    private static void exemploNivelAcesso() {
        System.out.println("--- NÍVEIS DE ACESSO ---");
        
        System.out.println("Hierarquia de acesso:");
        for (NivelAcesso nivel : NivelAcesso.values()) {
            System.out.printf("  [%d] %s - %s\n", 
                            nivel.getNivel(), 
                            nivel.name(), 
                            nivel.getDescricao());
            System.out.println("      Permissões: " + nivel.getPermissoes());
        }
        
        // Verifica permissões
        System.out.println("\nVerificação de permissões:");
        NivelAcesso usuarioAtual = NivelAcesso.MODERADOR;
        
        System.out.println("Usuário: " + usuarioAtual.name());
        System.out.println("Pode editar? " + usuarioAtual.podeEditar());
        System.out.println("Pode moderar? " + usuarioAtual.podeModerar());
        System.out.println("Pode administrar? " + usuarioAtual.podeAdministrar());
        
        // Verifica hierarquia
        System.out.println("\nHierarquia:");
        System.out.println("MODERADOR tem permissão de USUARIO? " + 
                         usuarioAtual.temPermissao(NivelAcesso.USUARIO));
        System.out.println("MODERADOR tem permissão de ADMINISTRADOR? " + 
                         usuarioAtual.temPermissao(NivelAcesso.ADMINISTRADOR));
    }
}
