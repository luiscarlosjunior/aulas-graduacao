/**
 * ClienteComum É-UM Cliente (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteComum extends Cliente {

    public ClienteComum(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double precoAgendamento() {
        return 80.00;   // paga por serviço
    }
}
