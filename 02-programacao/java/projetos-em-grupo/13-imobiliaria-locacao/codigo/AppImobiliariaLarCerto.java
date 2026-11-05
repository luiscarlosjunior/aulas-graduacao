/**
 * AppImobiliariaLarCerto — o fluxo completo do sistema Imobiliária LarCerto.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppImobiliariaLarCerto {
    public static void main(String[] args) {
        System.out.println("=== Imobiliária LarCerto ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorContrato gestor = new GestorContrato(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — calcularReajuste() (reajuste anual (0.04 = 4%))");
        Imovel comum = new ImovelResidencial("Apto 101 - Centro", 1500.00);
        Imovel especial = new ImovelComercial("Loja 5 - Centro", 4000.00);
        System.out.println("   ImovelResidencial -> " + comum.calcularReajuste());
        System.out.println("   ImovelComercial -> " + especial.calcularReajuste());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Contrato pelo Gestor
        System.out.println("\n2) Abrindo um registro de Contrato");
        Contrato contrato = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Contrato ◆ Parcela");
        contrato.adicionarParcela("Parcela 1", 50.00);
        contrato.adicionarParcela("Parcela 2", 30.00);
        System.out.println("   Partes (Parcela) criadas dentro de Contrato: " + contrato.getParcelas().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Proprietario ◇ Imovel");
        Imovel item = new ImovelResidencial("Casa 7 - Jardim", 2200.00);   // existe por conta própria
        Proprietario proprietario = new Proprietario("Proprietario principal");
        proprietario.adicionarImovel(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Imovel) agrupados em Proprietario: " + proprietario.getImoveis().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!contrato.emEtapaFinal()) {
            gestor.avancar(contrato);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(contrato);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO ("cancelado")
        System.out.println("\n7) Caminho alternativo: cancelado");
        Contrato outra = gestor.abrir(especial, 99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelado"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Contrato): " + gestor.listar().size());
    }
}
