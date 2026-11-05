/**
 * ClienteMensalista É-UM Cliente (pilar: HERANÇA) — o tipo especial (tem vaga garantida e não paga por hora).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteMensalista extends Cliente {

    public ClienteMensalista(String nome, String placa) {
        super(nome, placa);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularValor() {
        return 0.00;   // não paga por hora (mensalidade fixa)
    }
}
