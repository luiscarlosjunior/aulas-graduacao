import java.time.LocalDate;

/*
 * Assinatura — vínculo do ouvinte com o serviço. Vem da fala: "nasce junto com
 * o cadastro... se a conta for encerrada, a assinatura deixa de existir".
 * É PARTE do Ouvinte (composição): por isso o Ouvinte a cria no construtor dele.
 */
public class Assinatura {
    private boolean ativa;
    private final LocalDate dataInicio;

    public Assinatura() {
        this.ativa = true;                 // nasce ativa
        this.dataInicio = LocalDate.now();
    }

    public boolean estaAtiva() { return ativa; }
    public LocalDate getDataInicio() { return dataInicio; }

    public void ativar()   { ativa = true; }   // controlado (sem setAtiva)
    public void cancelar() { ativa = false; }
}
