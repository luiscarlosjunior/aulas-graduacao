/*
 * Usuario — a segunda abstração: superclasse abstrata de Ouvinte e Artista.
 * Concentra nome/email (o comum) e o contrato polimórfico beneficios().
 */
public abstract class Usuario {
    protected final String nome;
    protected final String email;

    protected Usuario(String nome, String email) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("E-mail inválido: " + email);
        this.nome = nome;
        this.email = email;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }

    /** Cada tipo de usuário tem benefícios diferentes (polimorfismo). */
    public abstract String beneficios();
}
