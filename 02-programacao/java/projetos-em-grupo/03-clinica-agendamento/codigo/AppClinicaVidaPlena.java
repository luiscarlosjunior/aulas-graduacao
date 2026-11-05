/**
 * AppClinicaVidaPlena — o fluxo completo do sistema Clínica VidaPlena.
 * Rode e leia: cada bloco numerado mostra uma parte do sistema funcionando.
 */
public class AppClinicaVidaPlena {
    public static void main(String[] args) {
        System.out.println("=== Clínica VidaPlena ===");

        // DIP: o Gestor recebe o contrato Notificador (poderia ser e-mail, SMS...)
        Notificador notificador = new NotificadorConsole();
        GestorConsulta gestor = new GestorConsulta(notificador);

        // 1) HERANÇA + POLIMORFISMO: dois tipos, a mesma pergunta, respostas diferentes
        System.out.println("\n1) Polimorfismo — valorConsulta() (valor da consulta em R$)");
        Paciente comum = new PacienteParticular("Ana Souza", "123.456.789-00");
        Paciente especial = new PacienteConvenio("Bruno Lima", "987.654.321-00");
        System.out.println("   PacienteParticular -> " + comum.valorConsulta());
        System.out.println("   PacienteConvenio -> " + especial.valorConsulta());

        // 2) ASSOCIAÇÃO + ENCAPSULAMENTO: abre a Consulta pelo Gestor
        System.out.println("\n2) Abrindo um registro de Consulta");
        Consulta consulta = gestor.abrir(comum, 150.00);

        // 3) COMPOSIÇÃO: as partes nascem dentro do todo
        System.out.println("\n3) Composição — Consulta ◆ Prescricao");
        consulta.adicionarPrescricao("Prescricao 1", 50.00);
        consulta.adicionarPrescricao("Prescricao 2", 30.00);
        System.out.println("   Partes (Prescricao) criadas dentro de Consulta: " + consulta.getPrescricoes().size());

        // 4) AGREGAÇÃO: o grupo recebe algo que já existe
        System.out.println("\n4) Agregação — Convenio ◇ Paciente");
        Paciente item = new PacienteParticular("Carla Dias", "111.222.333-44");   // existe por conta própria
        Convenio convenio = new Convenio("Convenio principal");
        convenio.adicionarPaciente(item);   // recebe PRONTO (agregação)
        System.out.println("   Itens (Paciente) agrupados em Convenio: " + convenio.getPacientes().size());

        // 5) Avança etapa por etapa até o fim
        System.out.println("\n5) Avançando até a última etapa");
        while (!consulta.emEtapaFinal()) {
            gestor.avancar(consulta);
        }

        // 6) PRÉ-CONDIÇÕES / INVARIANTE sendo protegidos
        System.out.println("\n6) Tentativas inválidas");
        try {
            gestor.avancar(consulta);   // já está na última etapa
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }
        try {
            gestor.abrir(especial, -10.00);   // valor negativo fere o invariante
        } catch (IllegalArgumentException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        // 7) CAMINHO ALTERNATIVO ("cancelada")
        System.out.println("\n7) Caminho alternativo: cancelada");
        Consulta outra = gestor.abrir(especial, 99.90);
        gestor.cancelar(outra);
        try {
            gestor.avancar(outra);   // PRÉ-CONDIÇÃO falha: não se avança a partir de "cancelada"
        } catch (IllegalStateException e) {
            System.out.println("   Recusado: " + e.getMessage());
        }

        System.out.println("\nTotal de registros (Consulta): " + gestor.listar().size());
    }
}
