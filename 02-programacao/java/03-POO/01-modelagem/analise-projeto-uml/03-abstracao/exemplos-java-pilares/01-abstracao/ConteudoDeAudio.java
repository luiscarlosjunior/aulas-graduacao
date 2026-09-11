/*
 * ============================================================================
 *  PILAR 1 — ABSTRAÇÃO  (versão prática em Java)
 * ============================================================================
 *
 *  Resposta ao exercício da apresentação:
 *  "Crie o tipo abstrato ConteudoDeAudio que GENERALIZA Musica, Podcast e
 *   Audiolivro. O que TODO áudio reproduzível precisa expor?"
 *
 *  Fundamentação (o "porquê", não só o "como"):
 *  ---------------------------------------------------------------------------
 *  Booch (1994) define abstração como o ato de destacar as CARACTERÍSTICAS
 *  ESSENCIAIS de uma entidade — as que a distinguem de todas as outras — e
 *  IGNORAR o que é irrelevante para o problema. Aqui, o essencial que todo
 *  conteúdo de áudio da Melodia compartilha é: ter um TÍTULO, uma DURAÇÃO e
 *  saber se REPRODUZIR. Cor da capa, formato do arquivo, bitrate... é ruído
 *  para este recorte.
 *
 *  Por que uma CLASSE ABSTRATA (e não uma interface)?
 *  ---------------------------------------------------------------------------
 *  Regra prática (Bloch, "Effective Java"): use classe abstrata quando há
 *  ESTADO e comportamento COMUNS a reaproveitar (aqui: os campos `titulo` e
 *  `duracaoSegundos` e o método concreto `fichaTecnica()`); use interface
 *  quando quer apenas declarar um CONTRATO sem estado. Como Musica, Podcast e
 *  Audiolivro realmente COMPARTILHAM dados e código, a classe abstrata evita
 *  a duplicação. (No módulo de interfaces, mais à frente, revemos essa
 *  decisão.)
 *
 *  Na UML, esta classe apareceria com o NOME EM ITÁLICO e o estereótipo
 *  «abstract», e a operação `descricaoCurta()` também em itálico (é abstrata).
 */
public abstract class ConteudoDeAudio {

    // --- ESTADO ESSENCIAL, comum a todo áudio (o "recorte" da abstração) -----
    // Note a visibilidade `protected` (#): as subclasses enxergam, o mundo
    // externo não. É a ponte entre ABSTRAÇÃO e ENCAPSULAMENTO.
    protected final String titulo;
    protected final int duracaoSegundos;

    protected ConteudoDeAudio(String titulo, int duracaoSegundos) {
        // Mesmo numa classe abstrata, protegemos os invariantes na entrada:
        // um conteúdo sem título ou com duração <= 0 não faz sentido no domínio.
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração deve ser positiva.");
        }
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
    }

    // --- COMPORTAMENTO COMUM já implementado aqui (reúso pela generalização) --
    public String getTitulo() {
        return titulo;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    /** Formata a duração como mm:ss — igual para todos os tipos, logo mora aqui. */
    public String duracaoFormatada() {
        return String.format("%02d:%02d", duracaoSegundos / 60, duracaoSegundos % 60);
    }

    /**
     * OPERAÇÃO ABSTRATA (o «contrato»): todo conteúdo SABE se descrever, mas
     * CADA TIPO faz isso à sua maneira. Não há implementação "genérica" que
     * sirva para todos — por isso é abstrata. Quem estende é OBRIGADO a
     * concretizá-la (o compilador cobra). Isto é também a semente do
     * POLIMORFISMO (pilar 4).
     */
    public abstract String descricaoCurta();

    /**
     * Ficha técnica: um TEMPLATE que combina a parte comum (título/duração,
     * definidos aqui) com a parte que varia (descricaoCurta(), definida nas
     * subclasses). Chamar um método abstrato de dentro de um concreto é o
     * padrão "Template Method" (GoF, 1994) em miniatura.
     */
    public String fichaTecnica() {
        return "[" + descricaoCurta() + "] " + titulo + " (" + duracaoFormatada() + ")";
    }
}
