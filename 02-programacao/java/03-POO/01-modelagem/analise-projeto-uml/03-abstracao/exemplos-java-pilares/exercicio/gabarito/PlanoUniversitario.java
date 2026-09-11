/* PlanoUniversitario É-UM Plano: preço reduzido, mesmos benefícios do Premium. */
public class PlanoUniversitario extends Plano {
    public PlanoUniversitario() { super("Universitario"); }

    @Override public double precoMensal()      { return 11.90; }
    @Override public boolean permiteDownload() { return true; }
    @Override public boolean temAnuncios()     { return false; }
}
