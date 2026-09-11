/*
 * Assinante — COMPOSIÇÃO com Assinatura (losango cheio ◆): a assinatura nasce
 * dentro do assinante e não faz sentido fora dele. Pode ter 0..1 assinatura.
 */
public class Assinante {
    private final String nome;
    private final String email;
    private Assinatura assinatura;             // 0..1

    public Assinante(String nome, String email) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("E-mail inválido: " + email);
        this.nome = nome;
        this.email = email;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Assinatura getAssinatura() { return assinatura; }

    // cria a assinatura DENTRO do assinante (composição)
    public void assinar(Plano plano, int diaDeVencimento) {
        this.assinatura = new Assinatura(plano, diaDeVencimento);
    }

    public void cancelarAssinatura() {
        if (assinatura != null) assinatura.cancelar();
    }

    public double valorMensal() {
        return assinatura == null ? 0.0 : assinatura.valorAPagar();
    }
}
