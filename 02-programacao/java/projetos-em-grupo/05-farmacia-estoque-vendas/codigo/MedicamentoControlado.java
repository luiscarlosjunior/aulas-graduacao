/**
 * MedicamentoControlado É-UM Produto (pilar: HERANÇA) — o tipo especial (só pode ser vendido com receita registrada).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class MedicamentoControlado extends Produto {

    public MedicamentoControlado(String nome, double preco, int estoque) {
        super(nome, preco, estoque);   // a validação dos dados comuns é feita na base
    }

    @Override
    public boolean exigeReceita() {
        return true;   // exige receita
    }
}
