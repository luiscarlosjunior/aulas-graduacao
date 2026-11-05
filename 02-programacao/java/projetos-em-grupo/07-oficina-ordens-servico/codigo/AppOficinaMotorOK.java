/**
 * AppOficinaMotorOK — o fluxo completo do sistema Oficina MotorOK.
 * Leia na ordem: é exatamente este passo a passo que vira o DIAGRAMA DE SEQUÊNCIA.
 */
public class AppOficinaMotorOK {
    public static void main(String[] args) {
        System.out.println("=== Oficina MotorOK ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorOrdemServico gestor = new GestorOrdemServico(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — precoMaoDeObra() (valor da hora de mão de obra em R$)");
        Cliente comum = new ClienteComum("Ana Souza", "123.456.789-00");
        Cliente especial = new ClienteFrota("Bruno Lima", "987.654.321-00");
        System.out.println("   ClienteComum -> " + comum.precoMaoDeObra());
        System.out.println("   ClienteFrota -> " + especial.precoMaoDeObra());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a OrdemServico pelo Gestor
        System.out.println("\n2) Abrindo um registro de OrdemServico");
        OrdemServico ordemServico = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — OrdemServico ◆ ItemOS");
        ordemServico.adicionarItemOS("ItemOS 1", 50.00);
        ordemServico.adicionarItemOS("ItemOS 2", 30.00);
        System.out.println("   Partes (ItemOS) criadas dentro de OrdemServico: " + ordemServico.getItensOS().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — CatalogoServicos ◇ Servico");
        Servico item = new Servico("Servico A");   // existe por conta própria
        CatalogoServicos catalogoServicos = new CatalogoServicos("CatalogoServicos principal");
        catalogoServicos.adicionarServico(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Servico) agrupados em CatalogoServicos: " + catalogoServicos.getServicos().size());

        // 5) MÁQUINA DE ESTADOS: avança etapa por etapa até o fim
        System.out.println("\n5) Estados — aberta → aprovada → em execução → concluída → paga");
        while (!ordemServico.emEtapaFinal()) {
            gestor.avancar(ordemServico);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(ordemServico);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (OrdemServico): " + gestor.listar().size());
    }
}
