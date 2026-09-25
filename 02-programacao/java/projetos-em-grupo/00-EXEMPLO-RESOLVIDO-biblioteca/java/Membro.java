/*
 * Membro — a base ABSTRATA de quem usa a biblioteca (não se instancia direto).
 * Abstração + polimorfismo: prazo e limite MUDAM conforme o tipo de membro.
 */
public abstract class Membro {
    private final String nome;
    private final String email;
    private String senha;                 // dado sensível: nunca exposto

    protected Membro(String nome, String email, String senha) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("E-mail inválido: " + email);
        if (senha == null || senha.length() < 4)
            throw new IllegalArgumentException("Senha deve ter ao menos 4 caracteres.");
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    /** Confere a senha SEM expor a guardada (encapsulamento). */
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    /** Troca controlada: exige a senha atual (não existe setSenha). */
    public void alterarSenha(String atual, String nova) {
        if (!autenticar(atual))
            throw new IllegalArgumentException("Senha atual incorreta.");
        if (nova == null || nova.length() < 4)
            throw new IllegalArgumentException("Nova senha muito curta.");
        this.senha = nova;
    }

    // Operações ABSTRATAS: cada tipo de membro responde do seu jeito (polimorfismo).
    public abstract int prazoDeEmprestimoDias();
    public abstract int limiteDeLivros();
}
