/*
 * GABARITO — Assinaturas e Planos da Melodia
 * Plano é a ABSTRAÇÃO (classe abstrata): o essencial de todo plano de assinatura.
 */
public abstract class Plano {
    protected final String nome;                 // # protegido

    protected Plano(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do plano é obrigatório.");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // Contrato POLIMÓRFICO: cada plano responde à sua maneira.
    public abstract double precoMensal();
    public abstract boolean permiteDownload();
    public abstract boolean temAnuncios();

    // TEMPLATE: usa as operações abstratas acima (o mesmo texto para todos os planos).
    public String resumo() {
        return String.format("%s — R$ %.2f/mes%s%s",
                nome, precoMensal(),
                temAnuncios() ? " · com anuncios" : " · sem anuncios",
                permiteDownload() ? " · download offline" : "");
    }
}
