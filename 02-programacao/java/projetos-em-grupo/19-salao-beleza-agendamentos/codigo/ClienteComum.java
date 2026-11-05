/**
 * ClienteComum É-UM Cliente (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteComum extends Cliente {

    public ClienteComum(String nome, String telefone) {
        super(nome, telefone);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularDesconto() {
        return 0.00;   // não tem desconto
    }
}
