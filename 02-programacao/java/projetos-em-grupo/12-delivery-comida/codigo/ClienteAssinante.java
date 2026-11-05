/**
 * ClienteAssinante É-UM Cliente (pilar: HERANÇA) — o tipo especial (tem entrega grátis acima de um valor).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteAssinante extends Cliente {

    public ClienteAssinante(String nome, String endereco) {
        super(nome, endereco);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double taxaEntrega() {
        return 0.00;   // tem entrega grátis (acima do valor)
    }
}
