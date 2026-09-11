/*
 *  DemoEncapsulamento — mostra o estado PROTEGIDO na prática.
 *
 *  O objetivo pedagógico é fazer o aluno SENTIR a diferença: com o campo
 *  privado, a linha que corromperia o ranking sequer COMPILA. A proteção é
 *  garantida pelo compilador, não pela boa vontade de quem usa a classe.
 */
public class DemoEncapsulamento {
    public static void main(String[] args) {

        Musica m = new Musica("Aquarela do Brasil", 235);

        // Uso correto: passamos pela "porta da frente".
        m.registrarReproducao();
        m.registrarReproducao();
        m.registrarReproducao();
        System.out.println(m.getTitulo() + " -> " + m.getReproducoes() + " reproduções");

        // ----------------------------------------------------------------
        // TENTATIVA DE FRAUDE (descomente para ver o compilador RECUSAR):
        //
        //     m.reproducoes = 1_000_000;   // erro: reproducoes tem acesso private
        //
        // É exatamente esse erro de compilação que queremos: o encapsulamento
        // torna o estado inválido INALCANÇÁVEL de fora.
        // ----------------------------------------------------------------

        // Tentativa de criar objeto inválido: barrada NA ENTRADA.
        try {
            new Musica("Faixa quebrada", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Objeto inválido rejeitado: " + e.getMessage());
        }
    }
}
