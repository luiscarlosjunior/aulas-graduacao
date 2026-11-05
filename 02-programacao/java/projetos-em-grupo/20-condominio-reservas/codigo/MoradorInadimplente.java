/**
 * MoradorInadimplente É-UM Morador (pilar: HERANÇA) — o tipo especial (fica bloqueado de reservar até quitar).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class MoradorInadimplente extends Morador {

    public MoradorInadimplente(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public boolean podeReservar() {
        return false;   // fica bloqueado até quitar
    }
}
