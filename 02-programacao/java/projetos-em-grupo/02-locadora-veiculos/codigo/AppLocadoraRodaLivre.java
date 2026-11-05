/**
 * AppLocadoraRodaLivre — o fluxo completo do sistema Locadora RodaLivre.
 * Leia na ordem: é exatamente este passo a passo que vira o DIAGRAMA DE SEQUÊNCIA.
 */
public class AppLocadoraRodaLivre {
    public static void main(String[] args) {
        System.out.println("=== Locadora RodaLivre ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorLocacao gestor = new GestorLocacao(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — descontoDiaria() (desconto em % na diária)");
        Cliente comum = new ClienteComum("Ana Souza", "123.456.789-00");
        Cliente especial = new ClientePremium("Bruno Lima", "987.654.321-00");
        System.out.println("   ClienteComum -> " + comum.descontoDiaria());
        System.out.println("   ClientePremium -> " + especial.descontoDiaria());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Locacao pelo Gestor
        System.out.println("\n2) Abrindo um registro de Locacao");
        Locacao locacao = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Locacao ◆ ItemCobranca");
        locacao.adicionarItemCobranca("ItemCobranca 1", 50.00);
        locacao.adicionarItemCobranca("ItemCobranca 2", 30.00);
        System.out.println("   Partes (ItemCobranca) criadas dentro de Locacao: " + locacao.getItensCobranca().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Categoria ◇ Veiculo");
        Veiculo item = new Veiculo("Veiculo A");   // existe por conta própria
        Categoria categoria = new Categoria("Categoria principal");
        categoria.adicionarVeiculo(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Veiculo) agrupados em Categoria: " + categoria.getVeiculos().size());

        // 5) MÁQUINA DE ESTADOS: avança etapa por etapa até o fim
        System.out.println("\n5) Estados — aberta → em andamento → finalizada");
        while (!locacao.emEtapaFinal()) {
            gestor.avancar(locacao);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(locacao);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO da máquina de estados ("cancelada")
        System.out.println("\n7) Caminho alternativo: cancelada");
        Locacao outra = gestor.abrir(especial, 99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelada"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Locacao): " + gestor.listar().size());
    }
}
