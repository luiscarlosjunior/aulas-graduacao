package recomendacao;

public class Recomendacao {
    private String criterio;

    public Recomendacao(String criterio) {
        this.criterio = criterio;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public void sugerir() {
        System.out.println("Sugestão baseada no critério: " + criterio);
    }
}