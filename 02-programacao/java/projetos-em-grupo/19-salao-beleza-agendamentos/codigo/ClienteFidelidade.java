/**
 * ClienteFidelidade É-UM Cliente (pilar: HERANÇA) — o tipo especial (acumula pontos e ganha descontos).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteFidelidade extends Cliente {

    public ClienteFidelidade(String nome, String telefone) {
        super(nome, telefone);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularDesconto() {
        return 0.10;   // ganha desconto por pontos
    }
}
