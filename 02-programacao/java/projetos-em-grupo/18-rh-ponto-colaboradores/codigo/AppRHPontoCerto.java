/**
 * AppRHPontoCerto — o fluxo completo do sistema RH PontoCerto.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppRHPontoCerto {
    public static void main(String[] args) {
        System.out.println("=== RH PontoCerto ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorPonto gestor = new GestorPonto(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — calcularAdicional() (multiplicador da hora extra (0 = não faz hora extra))");
        Colaborador comum = new ColaboradorCLT("Ana Souza", "M-001");
        Colaborador especial = new ColaboradorEstagiario("Bruno Lima", "M-002");
        System.out.println("   ColaboradorCLT -> " + comum.calcularAdicional());
        System.out.println("   ColaboradorEstagiario -> " + especial.calcularAdicional());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Ponto pelo Gestor
        System.out.println("\n2) Abrindo um registro de Ponto");
        Ponto ponto = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Colaborador ◆ RegistroPonto");
        comum.adicionarRegistroPonto("RegistroPonto 1", 50.00);
        comum.adicionarRegistroPonto("RegistroPonto 2", 30.00);
        System.out.println("   Partes (RegistroPonto) criadas dentro de Colaborador: " + comum.getRegistrosPonto().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Departamento ◇ Colaborador");
        Colaborador item = new ColaboradorCLT("Carla Dias", "M-003");   // existe por conta própria
        Departamento departamento = new Departamento("Departamento principal");
        departamento.adicionarColaborador(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Colaborador) agrupados em Departamento: " + departamento.getColaboradores().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!ponto.emEtapaFinal()) {
            gestor.avancar(ponto);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(ponto);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO ("ajustado")
        System.out.println("\n7) Caminho alternativo: ajustado");
        Ponto outra = gestor.abrir(especial, 99.90);
        gestor.marcarAjustado(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "ajustado"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Ponto): " + gestor.listar().size());
    }
}
