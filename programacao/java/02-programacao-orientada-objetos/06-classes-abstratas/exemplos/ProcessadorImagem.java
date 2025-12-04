/**
 * Classe ProcessadorImagem - Processador de imagens
 * 
 * Características:
 * - Análise de dimensões e qualidade
 * - Detecção de formato
 * - Extração de metadados EXIF
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class ProcessadorImagem extends ProcessadorDocumento {
    private int largura;
    private int altura;
    private String formato;
    private int qualidade;
    private boolean possuiExif;
    
    public ProcessadorImagem(String nomeArquivo) {
        super(nomeArquivo);
        this.largura = 0;
        this.altura = 0;
        this.formato = "";
        this.qualidade = 0;
        this.possuiExif = false;
    }
    
    @Override
    protected boolean validarFormato() {
        String nome = nomeArquivo.toLowerCase();
        if (nome.endsWith(".jpg") || nome.endsWith(".jpeg")) {
            formato = "JPEG";
            return true;
        } else if (nome.endsWith(".png")) {
            formato = "PNG";
            return true;
        } else if (nome.endsWith(".gif")) {
            formato = "GIF";
            return true;
        } else if (nome.endsWith(".bmp")) {
            formato = "BMP";
            return true;
        } else {
            System.out.println("✗ Formato de imagem não suportado!");
            return false;
        }
    }
    
    @Override
    protected void abrir() {
        System.out.println("→ Inicializando processador de imagens...");
        System.out.println("→ Carregando decodificador " + formato + "...");
    }
    
    @Override
    protected void ler() {
        System.out.println("→ Lendo dimensões da imagem...");
        
        // Simulação de leitura
        largura = (int)(Math.random() * 3000) + 640;
        altura = (int)(Math.random() * 2000) + 480;
        qualidade = (int)(Math.random() * 40) + 60; // 60-100%
        tamanhoBytes = largura * altura * 3; // RGB aproximado
        
        System.out.println("→ Dimensões: " + largura + "x" + altura + " pixels");
        System.out.println("→ Formato: " + formato);
        
        // JPEG e alguns PNG têm EXIF
        if (formato.equals("JPEG") || nomeArquivo.contains("camera")) {
            possuiExif = true;
        }
    }
    
    @Override
    protected void processarConteudo() {
        System.out.println("→ Analisando pixels da imagem...");
        System.out.println("→ Calculando qualidade: " + qualidade + "%");
        
        if (possuiExif) {
            System.out.println("→ Extraindo dados EXIF...");
            System.out.println("  • Câmera: Canon EOS 5D / Nikon D850 (simulado)");
            System.out.println("  • Data: 2024-01-15");
            System.out.println("  • GPS: Incluído");
        }
        
        System.out.println("→ Detectando cores predominantes...");
        System.out.println("→ Analisando histograma...");
        
        // Montando conteúdo
        conteudo = "Imagem " + formato + "\n";
        conteudo += "Resolução: " + largura + "x" + altura + " (" + (largura*altura/1000000.0) + " MP)\n";
        conteudo += "Qualidade: " + qualidade + "%\n";
    }
    
    @Override
    public String getTipoDocumento() {
        return "Imagem (" + formato + ")";
    }
    
    @Override
    protected void exibirInformacoesEspecificas() {
        System.out.println("║ Formato: " + formato);
        System.out.println("║ Dimensões: " + largura + "x" + altura + " pixels");
        System.out.println("║ Megapixels: " + String.format("%.2f", (largura*altura/1000000.0)) + " MP");
        System.out.println("║ Qualidade: " + qualidade + "%");
        System.out.println("║ Dados EXIF: " + (possuiExif ? "Sim 📷" : "Não"));
    }
    
    @Override
    public boolean exportar(String formato) {
        System.out.println("\n📤 Exportando imagem para " + formato + "...");
        
        switch (formato.toLowerCase()) {
            case "jpg":
            case "jpeg":
                System.out.println("✓ Exportado como JPEG (comprimido)");
                return true;
            case "png":
                System.out.println("✓ Exportado como PNG (sem perda)");
                return true;
            case "webp":
                System.out.println("✓ Exportado como WebP (moderno)");
                return true;
            case "thumbnail":
                System.out.println("✓ Miniatura gerada (200x200)");
                return true;
            default:
                System.out.println("✗ Formato não suportado: " + formato);
                return false;
        }
    }
    
    /**
     * Método específico - redimensiona a imagem
     */
    public void redimensionar(int novaLargura, int novaAltura) {
        if (processado) {
            System.out.println("\n🔧 Redimensionando imagem...");
            System.out.println("  De: " + largura + "x" + altura);
            System.out.println("  Para: " + novaLargura + "x" + novaAltura);
            largura = novaLargura;
            altura = novaAltura;
            System.out.println("✓ Imagem redimensionada com sucesso!");
        }
    }
    
    /**
     * Método específico - aplica filtro na imagem
     */
    public void aplicarFiltro(String filtro) {
        if (processado) {
            System.out.println("\n🎨 Aplicando filtro: " + filtro);
            System.out.println("✓ Filtro aplicado com sucesso!");
        }
    }
}
