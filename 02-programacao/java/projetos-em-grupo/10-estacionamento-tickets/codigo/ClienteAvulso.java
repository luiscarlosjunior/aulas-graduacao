/**
 * ClienteAvulso É-UM Cliente (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteAvulso extends Cliente {

    public ClienteAvulso(String nome, String placa) {
        super(nome, placa);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularValor() {
        return 12.00;   // paga por hora/fração
    }
}
