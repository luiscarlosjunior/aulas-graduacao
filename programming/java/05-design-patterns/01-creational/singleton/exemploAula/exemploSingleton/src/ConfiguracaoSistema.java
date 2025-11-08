public class ConfiguracaoSistema {
    private static ConfiguracaoSistema instancia;

    // atributos de exemplo
    private String idioma;
    private String tema;

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    // metodo construtor privado
    private ConfiguracaoSistema() {
        // valores padrao
        this.idioma = "PT-BR";
        this.tema = "Claro";
    }

    public ConfiguracaoSistema criarInstancia(int dummy){
        if(instancia == null){
            instancia = new ConfiguracaoSistema();
        }
        return instancia;
    }

    // metodo para obter a instancia unica
    public static ConfiguracaoSistema getInstancia(){
        if(instancia == null){
            instancia = new ConfiguracaoSistema();
        }
        return instancia;
    }
}
