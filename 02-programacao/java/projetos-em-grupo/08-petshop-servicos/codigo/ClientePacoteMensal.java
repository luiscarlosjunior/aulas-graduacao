/**
 * ClientePacoteMensal É-UM Cliente (pilar: HERANÇA) — o tipo especial (tem banhos inclusos e prioridade na agenda).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClientePacoteMensal extends Cliente {

    public ClientePacoteMensal(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double precoAgendamento() {
        return 0.00;   // tem banhos inclusos no pacote
    }
}
