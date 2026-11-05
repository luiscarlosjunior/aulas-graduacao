/**
 * AppSeguradoraProtege — o fluxo completo do sistema Seguradora Protege+.
 * Leia na ordem: é exatamente este passo a passo que vira o DIAGRAMA DE SEQUÊNCIA.
 */
public class AppSeguradoraProtege {
    public static void main(String[] args) {
        System.out.println("=== Seguradora Protege+ ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorSinistro gestor = new GestorSinistro(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — calcularPremio() (prêmio anual em R$)");
        Apolice comum = new ApoliceAuto("AP-1001", 50000.00);
        Apolice especial = new ApoliceVida("AP-2002", 200000.00);
        System.out.println("   ApoliceAuto -> " + comum.calcularPremio());
        System.out.println("   ApoliceVida -> " + especial.calcularPremio());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Sinistro pelo Gestor
        System.out.println("\n2) Abrindo um registro de Sinistro");
        Sinistro sinistro = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Apolice ◆ Cobertura");
        comum.adicionarCobertura("Cobertura 1", 50.00);
        comum.adicionarCobertura("Cobertura 2", 30.00);
        System.out.println("   Partes (Cobertura) criadas dentro de Apolice: " + comum.getCoberturas().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Cliente ◇ Apolice");
        Apolice item = new ApoliceAuto("AP-3003", 80000.00);   // existe por conta própria
        Cliente cliente = new Cliente("Cliente principal");
        cliente.adicionarApolice(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Apolice) agrupados em Cliente: " + cliente.getApolices().size());

        // 5) MÁQUINA DE ESTADOS: avança etapa por etapa até o fim
        System.out.println("\n5) Estados — aberto → em análise → aprovado → pago");
        while (!sinistro.emEtapaFinal()) {
            gestor.avancar(sinistro);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(sinistro);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO da máquina de estados ("negado")
        System.out.println("\n7) Caminho alternativo: negado");
        Sinistro outra = gestor.abrir(especial, 99.90);
        gestor.marcarNegado(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "negado"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Sinistro): " + gestor.listar().size());
    }
}
