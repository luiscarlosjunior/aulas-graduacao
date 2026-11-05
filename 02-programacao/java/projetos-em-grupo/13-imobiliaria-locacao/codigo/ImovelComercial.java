/**
 * ImovelComercial É-UM Imovel (pilar: HERANÇA) — o tipo especial (tem regras de reajuste e impostos diferentes).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ImovelComercial extends Imovel {

    public ImovelComercial(String endereco, double valorBase) {
        super(endereco, valorBase);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularReajuste() {
        return 0.06;   // reajuste comercial (com impostos próprios)
    }
}
