/*
 * ============================================================================
 *  PILAR 4 — POLIMORFISMO  (versão prática em Java)
 * ============================================================================
 *
 *  Resposta ao exercício da apresentação:
 *  "A mesma operação beneficios() deve responder diferente por tipo. Depois,
 *   acrescente um tipo NOVO (Podcaster) SEM alterar o código que já chama
 *   beneficios()."
 *
 *  Fundamentação:
 *  ---------------------------------------------------------------------------
 *  Cardelli & Wegner (1985): polimorfismo é a capacidade de uma MESMA MENSAGEM
 *  produzir COMPORTAMENTOS DIFERENTES conforme o TIPO REAL do objeto que a
 *  recebe. Em Java isso é a LIGAÇÃO DINÂMICA (dynamic dispatch): a JVM decide,
 *  em tempo de execução, qual `beneficios()` chamar, olhando o objeto real —
 *  não o tipo da variável.
 *
 *  R. C. Martin — Princípio Aberto/Fechado (OCP): o software deve ser ABERTO
 *  para EXTENSÃO e FECHADO para MODIFICAÇÃO. Com polimorfismo, adicionar um
 *  tipo novo é ESCREVER uma classe nova (extensão), não sair caçando `if`s
 *  para MODIFICAR (o que é frágil e propenso a erro).
 *
 *  Na UML: a operação abstrata da base (em itálico) REDEFINIDA em cada
 *  subclasse.
 */
public abstract class Usuario {

    protected final String nome;

    protected Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    /**
     * UM contrato, VÁRIAS implementações. Quem chama `usuario.beneficios()`
     * não sabe (nem precisa saber) qual é o tipo concreto.
     */
    public abstract String beneficios();
}
