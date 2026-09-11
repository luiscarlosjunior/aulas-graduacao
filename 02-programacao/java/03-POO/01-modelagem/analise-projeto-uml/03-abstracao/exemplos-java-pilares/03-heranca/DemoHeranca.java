/*
 *  DemoHeranca — mostra o reúso e a substituição (LSP) na prática.
 */
public class DemoHeranca {
    public static void main(String[] args) {

        // Duas subclasses, tratadas pela superclasse comum.
        Usuario u1 = new Ouvinte("Ana Souza", "ana@melodia.com", "Premium");
        Usuario u2 = new Artista("João Lima", "joao@melodia.com", "JL Beats");

        // getNome()/getEmail() vêm da BASE (herdados, escritos uma só vez).
        // tipoDePerfil() é concretizado por cada subtipo.
        System.out.println(u1.getNome() + " -> " + u1.tipoDePerfil());
        System.out.println(u2.getNome() + " -> " + u2.tipoDePerfil());

        // LSP na prática: onde se espera Usuario, qualquer subtipo entra.
        exibirCartao(u1);
        exibirCartao(u2);
    }

    // Este método NÃO conhece Ouvinte nem Artista — só o tipo Usuario.
    // Graças ao LSP, funciona para qualquer subtipo, presente ou futuro.
    static void exibirCartao(Usuario usuario) {
        System.out.println("  [cartão] " + usuario.getNome() + " <" + usuario.getEmail() + ">");
    }
}
