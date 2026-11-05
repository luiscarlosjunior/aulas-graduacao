import java.util.ArrayList;
import java.util.List;

/**
 * Cliente — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ClienteComum e ClientePacoteMensal: guarda o que os dois têm em comum.
 * Não existe "um Cliente genérico" — por isso não se faz `new Cliente(...)`.
 */
public abstract class Cliente {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final String documento;

    protected Cliente(String nome, String documento) {
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
     * Significado: preço do banho em R$ (0 = incluso no pacote).
     */
    public abstract double precoAgendamento();

    // AGREGAÇÃO (◇): só AGRUPA pets que já existem por conta própria.
    private final List<Pet> pets = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarPet(Pet item) {
        if (item == null) throw new IllegalArgumentException("Pet é obrigatório");
        if (!pets.contains(item)) pets.add(item);
    }

    public List<Pet> getPets() { return List.copyOf(pets); }
}
