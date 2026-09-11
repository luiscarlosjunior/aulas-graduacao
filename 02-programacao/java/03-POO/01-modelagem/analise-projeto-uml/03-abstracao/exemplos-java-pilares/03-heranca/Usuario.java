/*
 * ============================================================================
 *  PILAR 3 — HERANÇA  (versão prática em Java)
 * ============================================================================
 *
 *  Resposta ao exercício da apresentação:
 *  "Modele Usuario (abstrata) como superclasse de Ouvinte e Artista. Herde
 *   nome/email uma única vez; cada subtipo acrescenta o que é seu."
 *
 *  Fundamentação:
 *  ---------------------------------------------------------------------------
 *  Booch (1994): herança permite que uma SUBCLASSE ESPECIALIZE uma
 *  SUPERCLASSE, reaproveitando atributos e operações. O teste que autoriza
 *  herança é o "É-UM" ("is-a"): Ouvinte É-UM Usuario? Sim. Artista É-UM
 *  Usuario? Sim. Então a generalização é legítima.
 *
 *  Liskov (1994 — Princípio da Substituição, LSP): onde se espera um Usuario,
 *  qualquer subtipo deve poder entrar SEM quebrar o programa. Nossas
 *  subclasses só ACRESCENTAM comportamento e concretizam o que faltava — não
 *  removem nem contradizem nada da base. Logo, respeitam o LSP.
 *
 *  Cuidado (Bloch, "Effective Java", item "Favor composition over
 *  inheritance"): herança é acoplamento forte. Só herde no "é-um" real; para
 *  "tem-um", use composição. Aqui o "é-um" é verdadeiro.
 *
 *  Na UML: linha com TRIÂNGULO VAZIO ──▷ apontando para a superclasse.
 */
public abstract class Usuario {

    // Estado COMUM, declarado UMA vez. `protected` (#): subclasses enxergam.
    protected final String nome;
    protected final String email;

    protected Usuario(String nome, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido: " + email);
        }
        this.nome = nome;
        this.email = email;
    }

    // Comportamento COMUM: implementado uma vez, herdado por todos.
    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    /**
     * Operação ABSTRATA: todo usuário TEM um tipo de perfil, mas o valor
     * depende do subtipo. A base declara o contrato; as subclasses concretizam.
     * (É também o gancho para o POLIMORFISMO, pilar 4.)
     */
    public abstract String tipoDePerfil();
}
