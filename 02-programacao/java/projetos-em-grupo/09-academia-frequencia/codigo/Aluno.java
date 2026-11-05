import java.util.ArrayList;
import java.util.List;

/**
 * Aluno — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de AlunoComum e AlunoPlanoFamilia: guarda o que os dois têm em comum.
 * Não existe "um Aluno genérico" — por isso não se faz `new Aluno(...)`.
 */
public abstract class Aluno {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String documento;

    protected Aluno(String nome, String documento) {
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
     * Significado: mensalidade em R$ por pessoa.
     */
    public abstract double valorMensalidade();

    // COMPOSIÇÃO (◆): cada CheckIn nasce dentro desta classe e morre com ela.
    private final List<CheckIn> checkIns = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public CheckIn adicionarCheckIn(String descricao, double valor) {
        CheckIn parte = new CheckIn(descricao, valor);
        checkIns.add(parte);
        return parte;
    }

    public List<CheckIn> getCheckIns() { return List.copyOf(checkIns); }
}
