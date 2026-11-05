/**
 * Paciente — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de PacienteParticular e PacienteConvenio: guarda o que os dois têm em comum.
 * Não existe "um Paciente genérico" — por isso não se faz `new Paciente(...)`.
 */
public abstract class Paciente {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String documento;

    protected Paciente(String nome, String documento) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        if (documento == null || documento.isBlank()) throw new IllegalArgumentException("documento é obrigatório");
        this.nome = nome;
        this.documento = documento;
    }

    public String getNome() { return nome; }
    public String getDocumento() { return documento; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: valor da consulta em R$.
     */
    public abstract double valorConsulta();
}
