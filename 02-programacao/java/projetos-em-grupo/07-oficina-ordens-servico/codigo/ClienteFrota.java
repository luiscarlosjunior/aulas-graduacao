/**
 * ClienteFrota É-UM Cliente (pilar: HERANÇA) — o tipo especial (tem tabela de preço própria e prazo diferenciado).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteFrota extends Cliente {

    public ClienteFrota(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double precoMaoDeObra() {
        return 90.00;   // paga a tabela da frota (contrato)
    }
}
