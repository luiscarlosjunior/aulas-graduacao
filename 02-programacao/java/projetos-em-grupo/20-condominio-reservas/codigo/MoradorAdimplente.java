/**
 * MoradorAdimplente É-UM Morador (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class MoradorAdimplente extends Morador {

    public MoradorAdimplente(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public boolean podeReservar() {
        return true;   // pode reservar normalmente
    }
}
