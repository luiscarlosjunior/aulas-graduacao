/* PlanoGratuito É-UM Plano: de graça, com anúncios e sem download. */
public class PlanoGratuito extends Plano {
    public PlanoGratuito() { super("Gratuito"); }

    @Override public double precoMensal()      { return 0.0; }
    @Override public boolean permiteDownload() { return false; }
    @Override public boolean temAnuncios()     { return true; }
}
