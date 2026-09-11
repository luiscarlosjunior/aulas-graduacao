/*
 * ============================================================================
 *  PILAR 2 — ENCAPSULAMENTO  (versão prática em Java)
 * ============================================================================
 *
 *  Resposta ao exercício da apresentação:
 *  "Complete a Musica protegendo o estado: `reproducoes` deve ser privado e só
 *   mudar por uma operação que valida. Escreva o invariante."
 *
 *  Fundamentação:
 *  ---------------------------------------------------------------------------
 *  Parnas (1972), no clássico "On the Criteria To Be Used in Decomposing
 *  Systems into Modules", introduz o OCULTAMENTO DE INFORMAÇÃO: cada módulo
 *  esconde uma decisão de projeto, para que mudanças internas não vazem para o
 *  resto do sistema. Meyer (1997, "Object-Oriented Software Construction")
 *  formaliza os INVARIANTES de classe: condições que devem ser SEMPRE
 *  verdadeiras para qualquer objeto observável de fora.
 *
 *  INVARIANTE desta classe: `reproducoes >= 0` e `duracaoSegundos > 0`.
 *  Encapsular é o que torna esse invariante IMPOSSÍVEL de violar de fora.
 *
 *  Na UML: atributos com visibilidade `-` (privado); acesso por operações `+`
 *  (públicas) que validam.
 */
public class Musica {

    // --- ESTADO PRIVADO (-): ninguém mexe direto. Este é o coração do pilar. -
    private final String titulo;
    private final int duracaoSegundos;
    private int reproducoes;   // alimenta ranking e royalties -> não pode ser adulterado

    public Musica(String titulo, int duracaoSegundos) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }
        if (duracaoSegundos <= 0) {
            // Rejeitamos o valor inválido NA ENTRADA (Meyer: "fail fast"),
            // não "depois que já corrompeu o ranking".
            throw new IllegalArgumentException("Duração deve ser positiva; veio " + duracaoSegundos);
        }
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
        this.reproducoes = 0;   // nasce coerente com o invariante
    }

    // --- OPERAÇÕES PÚBLICAS (+): a ÚNICA porta para mudar o estado -----------

    /**
     * A ÚNICA maneira de `reproducoes` crescer. Cresce de 1 em 1, sempre para
     * cima. Compare com `musica.reproducoes = 999999` — impossível aqui, porque
     * o campo é privado. O erro de uma pessoa não vira incidente de todos.
     */
    public void registrarReproducao() {
        reproducoes++;
    }

    // Getters expõem o estado para LEITURA, sem permitir ESCRITA arbitrária.
    // Repare: NÃO existe setReproducoes(int). Isso é intencional — um "setter"
    // para tudo destruiria o encapsulamento (a chamada "classe anêmica",
    // criticada por Fowler).
    public String getTitulo() {
        return titulo;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public int getReproducoes() {
        return reproducoes;
    }
}
