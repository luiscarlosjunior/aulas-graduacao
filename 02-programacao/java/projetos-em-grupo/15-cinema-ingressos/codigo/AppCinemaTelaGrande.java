/**
 * AppCinemaTelaGrande — o fluxo completo do sistema Cinema TelaGrande.
 * Leia na ordem: é exatamente este passo a passo que vira o DIAGRAMA DE SEQUÊNCIA.
 */
public class AppCinemaTelaGrande {
    public static void main(String[] args) {
        System.out.println("=== Cinema TelaGrande ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorVenda gestor = new GestorVenda(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — calcularPreco() (preço do ingresso em R$)");
        Ingresso comum = new IngressoInteira("A12", 12.90);
        Ingresso especial = new IngressoMeia("B07", 45.50);
        System.out.println("   IngressoInteira -> " + comum.calcularPreco());
        System.out.println("   IngressoMeia -> " + especial.calcularPreco());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Venda pelo Gestor
        System.out.println("\n2) Abrindo um registro de Venda");
        Venda venda = gestor.abrir(150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Venda ◆ Ingresso");
        venda.adicionarIngresso("C03", 8.90, false);
        venda.adicionarIngresso("B07", 8.90, true);
        System.out.println("   Partes (Ingresso) criadas dentro de Venda: " + venda.getIngressos().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Sessao ◇ Assento");
        Assento item = new Assento("Assento A");   // existe por conta própria
        Sessao sessao = new Sessao("Sessao principal");
        sessao.adicionarAssento(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Assento) agrupados em Sessao: " + sessao.getAssentos().size());

        // 5) MÁQUINA DE ESTADOS: avança etapa por etapa até o fim
        System.out.println("\n5) Estados — aberta → paga");
        while (!venda.emEtapaFinal()) {
            gestor.avancar(venda);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(venda);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(-10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO da máquina de estados ("cancelada")
        System.out.println("\n7) Caminho alternativo: cancelada");
        Venda outra = gestor.abrir(99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelada"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Venda): " + gestor.listar().size());
    }
}
