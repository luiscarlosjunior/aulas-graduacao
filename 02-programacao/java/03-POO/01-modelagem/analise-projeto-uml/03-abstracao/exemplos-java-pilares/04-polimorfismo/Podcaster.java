/*
 *  Podcaster — o tipo NOVO que prova o Aberto/Fechado (OCP).
 *
 *  Para a Melodia passar a ter podcasters, bastou CRIAR esta classe. Repare no
 *  que NÃO foi preciso fazer: não tocamos em Usuario, nem em Ouvinte, nem em
 *  Artista, nem no DemoPolimorfismo. O método relatorio() lá continua idêntico
 *  e já funciona com Podcaster. Isso é "aberto para extensão, fechado para
 *  modificação" (R. C. Martin) acontecendo na prática.
 */
public class Podcaster extends Usuario {
    public Podcaster(String nome) {
        super(nome);
    }

    @Override
    public String beneficios() {
        return "publicar episódios e ver estatísticas de audiência";
    }
}
