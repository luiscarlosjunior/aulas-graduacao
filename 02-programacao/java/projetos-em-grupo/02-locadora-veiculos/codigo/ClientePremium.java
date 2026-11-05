/**
 * ClientePremium É-UM Cliente (pilar: HERANÇA) — o tipo especial (tem desconto na diária e prioridade na reserva).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClientePremium extends Cliente {

    public ClientePremium(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double descontoDiaria() {
        return 20.00;   // tem desconto na diária
    }
}
