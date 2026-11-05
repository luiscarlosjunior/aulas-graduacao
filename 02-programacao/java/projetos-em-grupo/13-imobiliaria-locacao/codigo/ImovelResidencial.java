/**
 * ImovelResidencial É-UM Imovel (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ImovelResidencial extends Imovel {

    public ImovelResidencial(String endereco, double valorBase) {
        super(endereco, valorBase);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularReajuste() {
        return 0.04;   // reajuste residencial
    }
}
