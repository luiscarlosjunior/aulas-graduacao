import java.util.List;

/* DemoAssinaturas — mostra os 4 pilares + relacionamentos em ação. */
public class DemoAssinaturas {
    public static void main(String[] args) {
        // Polimorfismo: resumo() usa precoMensal()/temAnuncios()/permiteDownload() de cada plano
        List<Plano> planos = List.of(new PlanoGratuito(), new PlanoPremium(), new PlanoUniversitario());
        System.out.println("=== Planos disponíveis ===");
        for (Plano p : planos) System.out.println("  " + p.resumo());

        // Composição: a assinatura nasce dentro do assinante
        Assinante ana = new Assinante("Ana Souza", "ana@melodia.com");
        ana.assinar(new PlanoPremium(), 10);
        System.out.printf("%n%s assinou. Valor mensal: R$ %.2f%n", ana.getNome(), ana.valorMensal());

        // Encapsulamento: o estado só muda pela operação cancelar()
        ana.cancelarAssinatura();
        System.out.printf("Apos cancelar, valor mensal: R$ %.2f%n", ana.valorMensal());

        // Fail fast: estado inválido é barrado na entrada
        try {
            new Assinatura(new PlanoGratuito(), 31);
        } catch (IllegalArgumentException e) {
            System.out.println("\nRejeitado: " + e.getMessage());
        }
    }
}
