/**
 * AppTransportadoraEntregaCerta — o fluxo completo do sistema Transportadora EntregaCerta.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppTransportadoraEntregaCerta {
    public static void main(String[] args) {
        System.out.println("=== Transportadora EntregaCerta ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorEntrega gestor = new GestorEntrega(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — prazoEntregaDias() (prazo de entrega em dias)");
        Encomenda comum = new EncomendaComum("BR123", 2.5);
        Encomenda especial = new EncomendaExpressa("BR456", 0.8);
        System.out.println("   EncomendaComum -> " + comum.prazoEntregaDias());
        System.out.println("   EncomendaExpressa -> " + especial.prazoEntregaDias());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Entrega pelo Gestor
        System.out.println("\n2) Abrindo um registro de Entrega");
        Entrega entrega = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Encomenda ◆ EventoRastreio");
        comum.adicionarEventoRastreio("EventoRastreio 1", 50.00);
        comum.adicionarEventoRastreio("EventoRastreio 2", 30.00);
        System.out.println("   Partes (EventoRastreio) criadas dentro de Encomenda: " + comum.getEventosRastreio().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Rota ◇ Encomenda");
        Encomenda item = new EncomendaComum("BR789", 5.0);   // existe por conta própria
        Rota rota = new Rota("Rota principal");
        rota.adicionarEncomenda(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Encomenda) agrupados em Rota: " + rota.getEncomendas().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!entrega.emEtapaFinal()) {
            gestor.avancar(entrega);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(entrega);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO ("devolvida")
        System.out.println("\n7) Caminho alternativo: devolvida");
        Entrega outra = gestor.abrir(especial, 99.90);
        gestor.marcarDevolvida(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "devolvida"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Entrega): " + gestor.listar().size());
    }
}
