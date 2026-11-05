/**
 * ClientePremium É-UM Cliente (pilar: HERANÇA) — o tipo especial (inclui traslado e seguro-viagem).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClientePremium extends Cliente {

    public ClientePremium(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double valorComExtras() {
        return 4200.00;   // inclui traslado e seguro no valor
    }
}
