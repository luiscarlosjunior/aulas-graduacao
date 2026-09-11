/* PlanoPremium É-UM Plano: pago, sem anúncios e com download. */
public class PlanoPremium extends Plano {
    public PlanoPremium() { super("Premium"); }

    @Override public double precoMensal()      { return 19.90; }
    @Override public boolean permiteDownload() { return true; }
    @Override public boolean temAnuncios()     { return false; }
}
