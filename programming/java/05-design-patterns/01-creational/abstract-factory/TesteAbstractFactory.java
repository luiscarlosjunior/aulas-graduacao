/**
 * Programa de teste para o padrão Abstract Factory
 * 
 * Demonstra como criar famílias de objetos relacionados
 * sem especificar suas classes concretas.
 * 
 * @author Aulas Graduação
 */
public class TesteAbstractFactory {
    
    public static void main(String[] args) {
        System.out.println("🏭 DEMONSTRAÇÃO DO PADRÃO ABSTRACT FACTORY\n");
        
        // Exemplo 1: Aplicação com tema claro
        System.out.println("=" .repeat(70));
        System.out.println("📱 EXEMPLO 1: Aplicação com Tema Claro");
        System.out.println("=".repeat(70));
        
        UIFactory factoryClaro = new UIFactoryClaro();
        Aplicacao app1 = new Aplicacao(factoryClaro);
        
        app1.criarInterface();
        app1.renderizar();
        app1.exibirInformacoes();
        app1.interagir();
        
        // Exemplo 2: Aplicação com tema escuro
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("📱 EXEMPLO 2: Aplicação com Tema Escuro");
        System.out.println("=".repeat(70));
        
        UIFactory factoryEscuro = new UIFactoryEscuro();
        Aplicacao app2 = new Aplicacao(factoryEscuro);
        
        app2.criarInterface();
        app2.renderizar();
        app2.exibirInformacoes();
        app2.interagir();
        
        // Exemplo 3: Alternando temas dinamicamente
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("🔄 EXEMPLO 3: Alternando Temas Dinamicamente");
        System.out.println("=".repeat(70));
        
        Aplicacao appDinamica = new Aplicacao(factoryClaro);
        appDinamica.criarInterface();
        appDinamica.renderizar();
        
        // Simula mudança de tema (ex: usuário ativa modo noturno)
        System.out.println("\n🌙 Usuário ativou o modo noturno...");
        appDinamica.alterarTema(factoryEscuro);
        appDinamica.renderizar();
        
        // Exemplo 4: Criando múltiplos componentes
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("🎨 EXEMPLO 4: Criando Múltiplos Componentes");
        System.out.println("=".repeat(70));
        
        System.out.println("\n📦 Criando formulário de login com tema escuro:");
        UIFactory factory = new UIFactoryEscuro();
        
        System.out.println("\nCriando componentes do formulário:");
        CampoTexto campoUsuario = factory.criarCampoTexto();
        CampoTexto campoSenha = factory.criarCampoTexto();
        Botao botaoEntrar = factory.criarBotao();
        Botao botaoCancelar = factory.criarBotao();
        
        System.out.println("\n📝 Configurando componentes:");
        campoUsuario.setPlaceholder("Digite seu e-mail");
        campoSenha.setPlaceholder("Digite sua senha");
        
        System.out.println("\n🖼️ Renderizando formulário completo:");
        System.out.println("\n--- Campo Usuário ---");
        campoUsuario.renderizar();
        System.out.println("\n--- Campo Senha ---");
        campoSenha.renderizar();
        System.out.println("\n--- Botão Entrar ---");
        botaoEntrar.renderizar();
        System.out.println("\n--- Botão Cancelar ---");
        botaoCancelar.renderizar();
        
        // Exemplo 5: Comparação lado a lado
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("⚖️ EXEMPLO 5: Comparação de Temas");
        System.out.println("=".repeat(70));
        
        System.out.println("\n🌞 TEMA CLARO:");
        Botao botaoClaro = new UIFactoryClaro().criarBotao();
        botaoClaro.renderizar();
        
        System.out.println("\n🌙 TEMA ESCURO:");
        Botao botaoEscuro = new UIFactoryEscuro().criarBotao();
        botaoEscuro.renderizar();
        
        // Conclusão
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("✅ VANTAGENS DO ABSTRACT FACTORY:");
        System.out.println("=".repeat(70));
        System.out.println("1. Garante que produtos de uma família sejam usados juntos");
        System.out.println("2. Isola o código cliente das classes concretas");
        System.out.println("3. Facilita a troca de famílias de produtos");
        System.out.println("4. Promove consistência entre produtos relacionados");
        System.out.println("5. Fácil adicionar novas famílias de produtos");
        
        System.out.println("\n📚 CASOS DE USO:");
        System.out.println("- Sistemas com múltiplos temas (claro/escuro)");
        System.out.println("- Aplicações multiplataforma (Windows/Mac/Linux)");
        System.out.println("- Sistemas com diferentes níveis de acesso");
        System.out.println("- Produtos que variam por região/idioma");
    }
}
