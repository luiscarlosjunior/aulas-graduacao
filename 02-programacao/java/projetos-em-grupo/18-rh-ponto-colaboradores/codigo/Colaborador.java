import java.util.ArrayList;
import java.util.List;

/**
 * Colaborador — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ColaboradorCLT e ColaboradorEstagiario: guarda o que os dois têm em comum.
 * Não existe "um Colaborador genérico" — por isso não se faz `new Colaborador(...)`.
 */
public abstract class Colaborador {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String matricula;

    protected Colaborador(String nome, String matricula) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        if (matricula == null || matricula.isBlank()) throw new IllegalArgumentException("matricula é obrigatório");
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: multiplicador da hora extra (0 = não faz hora extra).
     */
    public abstract double calcularAdicional();

    // COMPOSIÇÃO (◆): cada RegistroPonto nasce dentro desta classe e morre com ela.
    private final List<RegistroPonto> registrosPonto = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public RegistroPonto adicionarRegistroPonto(String descricao, double valor) {
        RegistroPonto parte = new RegistroPonto(descricao, valor);
        registrosPonto.add(parte);
        return parte;
    }

    public List<RegistroPonto> getRegistrosPonto() { return List.copyOf(registrosPonto); }
}
