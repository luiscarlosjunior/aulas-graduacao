/**
 * ProdutoComum É-UM Produto (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ProdutoComum extends Produto {

    public ProdutoComum(String nome, double preco, int estoque) {
        super(nome, preco, estoque);   // a validação dos dados comuns é feita na base
    }

    @Override
    public boolean exigeReceita() {
        return false;   // não exige receita
    }
}
