/*
 *  Ouvinte É-UM Usuario (o teste "is-a" passa).
 *
 *  Herda nome/email e a validação deles; acrescenta o que é só seu
 *  (o plano de assinatura). NÃO reescreve o que já funciona na base — isso é
 *  reúso por especialização.
 */
public class Ouvinte extends Usuario {

    private String plano; // "Free" ou "Premium"

    public Ouvinte(String nome, String email, String plano) {
        super(nome, email); // reaproveita a validação da superclasse
        this.plano = plano;
    }

    public String getPlano() {
        return plano;
    }

    /** Concretiza o contrato herdado. */
    @Override
    public String tipoDePerfil() {
        return "Ouvinte (" + plano + ")";
    }
}
