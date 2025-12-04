/**
 * RealSubject - Implementação real de imagem
 * Operação cara: carregamento do disco
 */
public class ImagemReal implements Imagem {
    private String nomeArquivo;
    private int tamanhoMB;
    
    /**
     * Construtor carrega imagem do disco (operação cara)
     * Em sistema real, isso envolveria I/O de arquivo
     */
    public ImagemReal(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        carregarDoDisco();
    }
    
    /**
     * Simula carregamento pesado de imagem
     */
    private void carregarDoDisco() {
        System.out.println(">>> Carregando imagem do disco: " + nomeArquivo);
        System.out.println(">>> Lendo bytes...");
        
        // Simula tempo de carregamento
        try {
            Thread.sleep(1000); // 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Simula tamanho da imagem
        tamanhoMB = (int)(Math.random() * 10) + 1;
        
        System.out.println(">>> Imagem carregada: " + tamanhoMB + " MB");
    }
    
    @Override
    public void exibir() {
        System.out.println("Exibindo imagem: " + nomeArquivo);
    }
    
    @Override
    public String getInfo() {
        return nomeArquivo + " (" + tamanhoMB + " MB)";
    }
}
