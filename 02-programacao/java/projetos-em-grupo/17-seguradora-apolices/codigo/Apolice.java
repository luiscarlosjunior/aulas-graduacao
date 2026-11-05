import java.util.ArrayList;
import java.util.List;

/**
 * Apolice — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ApoliceAuto e ApoliceVida: guarda o que os dois têm em comum.
 * Não existe "um Apolice genérico" — por isso não se faz `new Apolice(...)`.
 */
public abstract class Apolice {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String numero;
    private final double valorSegurado;

    protected Apolice(String numero, double valorSegurado) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (numero == null || numero.isBlank()) throw new IllegalArgumentException("numero é obrigatório");
        if (valorSegurado < 0) throw new IllegalArgumentException("valorSegurado não pode ser negativo");
        this.numero = numero;
        this.valorSegurado = valorSegurado;
    }

    public String getNumero() { return numero; }
    public double getValorSegurado() { return valorSegurado; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: prêmio anual em R$.
     */
    public abstract double calcularPremio();

    // COMPOSIÇÃO (◆): cada Cobertura nasce dentro desta classe e morre com ela.
    private final List<Cobertura> coberturas = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public Cobertura adicionarCobertura(String descricao, double valor) {
        Cobertura parte = new Cobertura(descricao, valor);
        coberturas.add(parte);
        return parte;
    }

    public List<Cobertura> getCoberturas() { return List.copyOf(coberturas); }
}
