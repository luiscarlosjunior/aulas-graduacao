/**
 * Proxy - Controla acesso à ImagemReal
 * Implementa Lazy Loading (Virtual Proxy)
 * 
 * Carrega imagem real apenas quando necessário (exibir)
 * Economiza recursos se imagem não for usada
 */
public class ImagemProxy implements Imagem {
    private String nomeArquivo;
    private ImagemReal imagemReal; // Referência para objeto real
    
    /**
     * Construtor do proxy - NÃO carrega imagem
     * Apenas armazena nome do arquivo
     */
    public ImagemProxy(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        System.out.println("Proxy criado para: " + nomeArquivo + " (imagem ainda não carregada)");
    }
    
    /**
     * Lazy Loading: carrega imagem apenas quando necessário
     */
    @Override
    public void exibir() {
        // Se imagem ainda não foi carregada, carrega agora
        if (imagemReal == null) {
            System.out.println("\n[Proxy] Primeira exibição - carregando imagem real...");
            imagemReal = new ImagemReal(nomeArquivo);
        } else {
            System.out.println("\n[Proxy] Imagem já carregada - reutilizando...");
        }
        
        // Delega para objeto real
        imagemReal.exibir();
    }
    
    /**
     * Retorna informações básicas sem carregar imagem
     * Demonstra que proxy pode responder sem acessar objeto real
     */
    @Override
    public String getInfo() {
        if (imagemReal == null) {
            return nomeArquivo + " (não carregada)";
        } else {
            return imagemReal.getInfo();
        }
    }
}
