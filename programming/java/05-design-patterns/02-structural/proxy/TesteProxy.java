/**
 * Teste do Padrão Proxy
 * 
 * Demonstra como Proxy controla acesso e implementa lazy loading
 */
public class TesteProxy {
    
    public static void main(String[] args) {
        System.out.println("=== Padrão Proxy - Lazy Loading de Imagens ===\n");
        
        System.out.println("=== Cenário 1: SEM Proxy (carregamento imediato) ===\n");
        System.out.println("Criando objetos ImagemReal diretamente:");
        long inicio1 = System.currentTimeMillis();
        
        // SEM proxy - todas as imagens são carregadas imediatamente
        // Mesmo que nunca sejam exibidas!
        Imagem img1 = new ImagemReal("foto1.jpg");
        Imagem img2 = new ImagemReal("foto2.jpg");
        Imagem img3 = new ImagemReal("foto3.jpg");
        
        long fim1 = System.currentTimeMillis();
        System.out.println("\nTempo total: " + (fim1 - inicio1) + "ms");
        System.out.println("PROBLEMA: Todas as imagens foram carregadas,");
        System.out.println("mesmo que algumas nunca sejam exibidas!");
        
        // Separador
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        System.out.println("=== Cenário 2: COM Proxy (lazy loading) ===\n");
        System.out.println("Criando objetos ImagemProxy:");
        long inicio2 = System.currentTimeMillis();
        
        // COM proxy - nenhuma imagem é carregada ainda
        Imagem proxy1 = new ImagemProxy("paisagem1.jpg");
        Imagem proxy2 = new ImagemProxy("paisagem2.jpg");
        Imagem proxy3 = new ImagemProxy("paisagem3.jpg");
        
        long fim2 = System.currentTimeMillis();
        System.out.println("\nTempo de criação: " + (fim2 - inicio2) + "ms");
        System.out.println("VANTAGEM: Criação instantânea! Imagens ainda não carregadas.\n");
        
        // Obtendo informações sem carregar
        System.out.println("--- Obtendo informações (sem carregar imagens) ---");
        System.out.println("Proxy 1: " + proxy1.getInfo());
        System.out.println("Proxy 2: " + proxy2.getInfo());
        System.out.println("Proxy 3: " + proxy3.getInfo());
        
        // Agora vamos exibir apenas uma imagem
        System.out.println("\n--- Exibindo apenas primeira imagem ---");
        proxy1.exibir();
        
        System.out.println("\nVANTAGEM: Apenas imagem necessária foi carregada!");
        System.out.println("Proxy 2 e 3 ainda não consumiram recursos.\n");
        
        // Exibindo mesma imagem novamente
        System.out.println("--- Exibindo primeira imagem novamente ---");
        proxy1.exibir();
        
        System.out.println("\nVANTAGEM: Imagem reutilizada, não recarregada!\n");
        
        // Agora exibindo segunda imagem
        System.out.println("--- Exibindo segunda imagem ---");
        proxy2.exibir();
        
        // Estado final
        System.out.println("\n--- Estado Final ---");
        System.out.println("Proxy 1: " + proxy1.getInfo() + " [CARREGADA]");
        System.out.println("Proxy 2: " + proxy2.getInfo() + " [CARREGADA]");
        System.out.println("Proxy 3: " + proxy3.getInfo() + " [NÃO CARREGADA]");
        
        System.out.println("\nNota: Proxy 3 nunca foi carregado, economizando recursos!");
        
        // Resumo de vantagens
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n=== Vantagens do Proxy Demonstradas ===\n");
        
        System.out.println("1. LAZY LOADING:");
        System.out.println("   - Objetos caros criados apenas quando necessários");
        System.out.println("   - Economia de memória e tempo");
        
        System.out.println("\n2. CONTROLE DE ACESSO:");
        System.out.println("   - Proxy controla quando e como objeto real é acessado");
        System.out.println("   - Pode adicionar validações, logging, etc.");
        
        System.out.println("\n3. TRANSPARÊNCIA:");
        System.out.println("   - Cliente usa mesma interface");
        System.out.println("   - Não precisa saber se está usando proxy ou objeto real");
        
        System.out.println("\n4. CACHE/REUTILIZAÇÃO:");
        System.out.println("   - Objeto real carregado uma vez, reutilizado depois");
        System.out.println("   - Evita operações caras repetidas");
        
        // Casos de uso
        System.out.println("\n=== Tipos de Proxy ===\n");
        
        System.out.println("1. VIRTUAL PROXY (demonstrado aqui):");
        System.out.println("   - Lazy loading de objetos caros");
        System.out.println("   - Ex: Imagens, documentos grandes, conexões BD");
        
        System.out.println("\n2. PROTECTION PROXY:");
        System.out.println("   - Controle de acesso baseado em permissões");
        System.out.println("   - Ex: Verificar credenciais antes de permitir acesso");
        
        System.out.println("\n3. REMOTE PROXY:");
        System.out.println("   - Representa objeto em espaço de endereçamento diferente");
        System.out.println("   - Ex: RMI, Web Services, RPC");
        
        System.out.println("\n4. CACHE PROXY:");
        System.out.println("   - Armazena resultados de operações caras");
        System.out.println("   - Ex: Cache de queries de banco de dados");
        
        System.out.println("\n5. LOGGING/AUDIT PROXY:");
        System.out.println("   - Registra todas as operações");
        System.out.println("   - Ex: Audit trail, debugging");
        
        System.out.println("\n=== Casos de Uso Reais ===\n");
        System.out.println("• Hibernate/JPA: Lazy loading de entidades");
        System.out.println("• Spring Framework: AOP proxies para transações");
        System.out.println("• Java RMI: Remote proxies para objetos distribuídos");
        System.out.println("• Navegadores web: Image loading em páginas");
        System.out.println("• IDEs: Lazy loading de informações de projeto");
    }
}
