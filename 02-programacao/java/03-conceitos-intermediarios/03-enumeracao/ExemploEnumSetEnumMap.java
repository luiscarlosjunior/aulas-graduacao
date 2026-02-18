import java.util.*;

/**
 * Exemplo de EnumSet e EnumMap
 * 
 * Demonstra coleções especializadas e otimizadas para trabalhar com enums.
 * EnumSet e EnumMap são implementações muito eficientes, usando bit vectors
 * e arrays internamente.
 * 
 * @author Aulas Graduação
 */
public class ExemploEnumSetEnumMap {
    
    /**
     * Enum representando dias da semana
     */
    enum DiaSemana {
        DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
    }
    
    /**
     * Enum representando permissões de sistema
     */
    enum Permissao {
        LER, ESCREVER, EXECUTAR, DELETAR, CRIAR, MODIFICAR, COMPARTILHAR
    }
    
    /**
     * Enum representando departamentos de uma empresa
     */
    enum Departamento {
        TI, RH, FINANCEIRO, VENDAS, MARKETING, OPERACOES
    }
    
    /**
     * Enum representando características de um produto
     */
    enum CaracteristicaProduto {
        NOVO, PROMOCAO, DESTAQUE, FRETE_GRATIS, EXCLUSIVO, LIMITADO
    }
    
    public static void main(String[] args) {
        System.out.println("=== ENUMSET E ENUMMAP ===\n");
        
        exemploEnumSetBasico();
        System.out.println();
        
        exemploEnumSetOperacoes();
        System.out.println();
        
        exemploEnumMapBasico();
        System.out.println();
        
        exemploEnumMapAvancado();
        System.out.println();
        
        exemploSistemaPermissoes();
        System.out.println();
        
        exemploComparacaoPerformance();
    }
    
    /**
     * Demonstra uso básico de EnumSet
     */
    private static void exemploEnumSetBasico() {
        System.out.println("--- ENUMSET BÁSICO ---");
        
        // Criar EnumSet vazio
        EnumSet<DiaSemana> diasUteis = EnumSet.noneOf(DiaSemana.class);
        
        // Adicionar elementos
        diasUteis.add(DiaSemana.SEGUNDA);
        diasUteis.add(DiaSemana.TERCA);
        diasUteis.add(DiaSemana.QUARTA);
        diasUteis.add(DiaSemana.QUINTA);
        diasUteis.add(DiaSemana.SEXTA);
        
        System.out.println("Dias úteis: " + diasUteis);
        
        // Criar EnumSet com todos os elementos
        EnumSet<DiaSemana> todosDias = EnumSet.allOf(DiaSemana.class);
        System.out.println("Todos os dias: " + todosDias);
        
        // Criar EnumSet com range
        EnumSet<DiaSemana> meioSemana = EnumSet.range(DiaSemana.TERCA, DiaSemana.QUINTA);
        System.out.println("Meio da semana: " + meioSemana);
        
        // Criar EnumSet complementar
        EnumSet<DiaSemana> fimSemana = EnumSet.complementOf(diasUteis);
        System.out.println("Fim de semana: " + fimSemana);
        
        // Criar EnumSet com elementos específicos
        EnumSet<DiaSemana> doisDias = EnumSet.of(DiaSemana.SABADO, DiaSemana.DOMINGO);
        System.out.println("Sábado e Domingo: " + doisDias);
    }
    
    /**
     * Demonstra operações com EnumSet
     */
    private static void exemploEnumSetOperacoes() {
        System.out.println("--- OPERAÇÕES COM ENUMSET ---");
        
        EnumSet<Permissao> permissoesUsuario = EnumSet.of(
            Permissao.LER, Permissao.ESCREVER
        );
        
        EnumSet<Permissao> permissoesAdmin = EnumSet.of(
            Permissao.LER, Permissao.ESCREVER, Permissao.EXECUTAR, 
            Permissao.DELETAR, Permissao.CRIAR, Permissao.MODIFICAR
        );
        
        System.out.println("Permissões Usuário: " + permissoesUsuario);
        System.out.println("Permissões Admin: " + permissoesAdmin);
        
        // União (addAll)
        EnumSet<Permissao> uniao = EnumSet.copyOf(permissoesUsuario);
        uniao.addAll(permissoesAdmin);
        System.out.println("União: " + uniao);
        
        // Interseção (retainAll)
        EnumSet<Permissao> intersecao = EnumSet.copyOf(permissoesUsuario);
        intersecao.retainAll(permissoesAdmin);
        System.out.println("Interseção: " + intersecao);
        
        // Diferença (removeAll)
        EnumSet<Permissao> diferenca = EnumSet.copyOf(permissoesAdmin);
        diferenca.removeAll(permissoesUsuario);
        System.out.println("Diferença (Admin - Usuário): " + diferenca);
        
        // Verificação de contenção
        System.out.println("\nVerificações:");
        System.out.println("Admin contém todas permissões de Usuário? " + 
                         permissoesAdmin.containsAll(permissoesUsuario));
        System.out.println("Usuário pode DELETAR? " + 
                         permissoesUsuario.contains(Permissao.DELETAR));
        
        // Iteração
        System.out.println("\nIterando sobre permissões de Admin:");
        for (Permissao p : permissoesAdmin) {
            System.out.println("  - " + p);
        }
    }
    
    /**
     * Demonstra uso básico de EnumMap
     */
    private static void exemploEnumMapBasico() {
        System.out.println("--- ENUMMAP BÁSICO ---");
        
        // Criar EnumMap
        EnumMap<DiaSemana, String> horarios = new EnumMap<>(DiaSemana.class);
        
        // Adicionar elementos
        horarios.put(DiaSemana.SEGUNDA, "8h - 18h");
        horarios.put(DiaSemana.TERCA, "8h - 18h");
        horarios.put(DiaSemana.QUARTA, "8h - 18h");
        horarios.put(DiaSemana.QUINTA, "8h - 18h");
        horarios.put(DiaSemana.SEXTA, "8h - 17h");
        horarios.put(DiaSemana.SABADO, "9h - 13h");
        horarios.put(DiaSemana.DOMINGO, "Fechado");
        
        System.out.println("Horários de funcionamento:");
        for (Map.Entry<DiaSemana, String> entry : horarios.entrySet()) {
            System.out.printf("  %s: %s\n", entry.getKey(), entry.getValue());
        }
        
        // Acessar valores
        System.out.println("\nHorário de sexta: " + horarios.get(DiaSemana.SEXTA));
        System.out.println("Horário de domingo: " + horarios.get(DiaSemana.DOMINGO));
    }
    
    /**
     * Demonstra uso avançado de EnumMap
     */
    private static void exemploEnumMapAvancado() {
        System.out.println("--- ENUMMAP AVANÇADO ---");
        
        // EnumMap com valores complexos (List)
        EnumMap<Departamento, List<String>> funcionarios = new EnumMap<>(Departamento.class);
        
        funcionarios.put(Departamento.TI, 
            Arrays.asList("João Silva", "Maria Santos", "Pedro Costa"));
        funcionarios.put(Departamento.RH, 
            Arrays.asList("Ana Paula", "Carlos Souza"));
        funcionarios.put(Departamento.FINANCEIRO, 
            Arrays.asList("Roberto Lima", "Juliana Oliveira", "Marcos Pereira"));
        funcionarios.put(Departamento.VENDAS, 
            Arrays.asList("Luciana Martins", "Fernando Alves", "Patricia Rocha", "Rafael Santos"));
        
        System.out.println("Funcionários por departamento:");
        for (Departamento dept : Departamento.values()) {
            List<String> lista = funcionarios.get(dept);
            if (lista != null) {
                System.out.printf("  %s (%d funcionários):\n", dept, lista.size());
                for (String func : lista) {
                    System.out.println("    - " + func);
                }
            } else {
                System.out.printf("  %s: Nenhum funcionário\n", dept);
            }
        }
        
        // EnumMap com valores numéricos
        EnumMap<Departamento, Integer> orcamento = new EnumMap<>(Departamento.class);
        orcamento.put(Departamento.TI, 500000);
        orcamento.put(Departamento.RH, 200000);
        orcamento.put(Departamento.FINANCEIRO, 300000);
        orcamento.put(Departamento.VENDAS, 800000);
        orcamento.put(Departamento.MARKETING, 600000);
        orcamento.put(Departamento.OPERACOES, 400000);
        
        System.out.println("\nOrçamento por departamento:");
        int total = 0;
        for (Map.Entry<Departamento, Integer> entry : orcamento.entrySet()) {
            System.out.printf("  %s: R$ %,d\n", entry.getKey(), entry.getValue());
            total += entry.getValue();
        }
        System.out.printf("Total: R$ %,d\n", total);
    }
    
    /**
     * Enum representando perfis de usuário
     */
    enum PerfilUsuario {
        VISITANTE, USUARIO, EDITOR, ADMIN
    }
    
    /**
     * Demonstra sistema de permissões usando EnumSet e EnumMap
     */
    private static void exemploSistemaPermissoes() {
        System.out.println("--- SISTEMA DE PERMISSÕES ---");
        
        // EnumMap com perfis e suas permissões
        EnumMap<PerfilUsuario, EnumSet<Permissao>> perfilPermissoes = new EnumMap<>(PerfilUsuario.class);
        perfilPermissoes.put(PerfilUsuario.VISITANTE, EnumSet.of(Permissao.LER));
        perfilPermissoes.put(PerfilUsuario.USUARIO, EnumSet.of(Permissao.LER, Permissao.CRIAR));
        perfilPermissoes.put(PerfilUsuario.EDITOR, EnumSet.of(Permissao.LER, Permissao.CRIAR, 
                                                               Permissao.ESCREVER, Permissao.MODIFICAR));
        perfilPermissoes.put(PerfilUsuario.ADMIN, EnumSet.allOf(Permissao.class));
        
        System.out.println("Permissões por perfil:");
        for (Map.Entry<PerfilUsuario, EnumSet<Permissao>> entry : perfilPermissoes.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        // Simula usuários e seus perfis
        Map<String, PerfilUsuario> usuarios = new HashMap<>();
        usuarios.put("usuario1", PerfilUsuario.VISITANTE);
        usuarios.put("usuario2", PerfilUsuario.EDITOR);
        usuarios.put("admin", PerfilUsuario.ADMIN);
        
        System.out.println("\nUsuários e permissões:");
        for (Map.Entry<String, PerfilUsuario> entry : usuarios.entrySet()) {
            String nomeUsuario = entry.getKey();
            PerfilUsuario perfil = entry.getValue();
            EnumSet<Permissao> permissoes = perfilPermissoes.get(perfil);
            System.out.println("  " + nomeUsuario + " (" + perfil + "): " + permissoes);
        }
        
        // Verifica permissão específica
        String usuario = "usuario2";
        PerfilUsuario perfil = usuarios.get(usuario);
        Permissao permissaoNecessaria = Permissao.ESCREVER;
        
        boolean temPermissao = perfilPermissoes.get(perfil).contains(permissaoNecessaria);
        System.out.println("\n" + usuario + " pode " + permissaoNecessaria + "? " + temPermissao);
    }
    
    /**
     * Demonstra comparação de performance entre EnumSet/EnumMap e outras coleções
     */
    private static void exemploComparacaoPerformance() {
        System.out.println("--- COMPARAÇÃO DE PERFORMANCE ---");
        
        // EnumSet vs HashSet
        System.out.println("EnumSet vs HashSet:");
        
        long inicio = System.nanoTime();
        EnumSet<Permissao> enumSet = EnumSet.allOf(Permissao.class);
        for (int i = 0; i < 100000; i++) {
            enumSet.contains(Permissao.LER);
        }
        long tempoEnumSet = System.nanoTime() - inicio;
        
        inicio = System.nanoTime();
        HashSet<Permissao> hashSet = new HashSet<>(Arrays.asList(Permissao.values()));
        for (int i = 0; i < 100000; i++) {
            hashSet.contains(Permissao.LER);
        }
        long tempoHashSet = System.nanoTime() - inicio;
        
        System.out.printf("  EnumSet: %.3f ms\n", tempoEnumSet / 1_000_000.0);
        System.out.printf("  HashSet: %.3f ms\n", tempoHashSet / 1_000_000.0);
        System.out.printf("  EnumSet é %.1fx mais rápido\n", 
                        (double) tempoHashSet / tempoEnumSet);
        
        // EnumMap vs HashMap
        System.out.println("\nEnumMap vs HashMap:");
        
        inicio = System.nanoTime();
        EnumMap<DiaSemana, String> enumMap = new EnumMap<>(DiaSemana.class);
        for (DiaSemana dia : DiaSemana.values()) {
            enumMap.put(dia, dia.name());
        }
        for (int i = 0; i < 100000; i++) {
            enumMap.get(DiaSemana.QUARTA);
        }
        long tempoEnumMap = System.nanoTime() - inicio;
        
        inicio = System.nanoTime();
        HashMap<DiaSemana, String> hashMap = new HashMap<>();
        for (DiaSemana dia : DiaSemana.values()) {
            hashMap.put(dia, dia.name());
        }
        for (int i = 0; i < 100000; i++) {
            hashMap.get(DiaSemana.QUARTA);
        }
        long tempoHashMap = System.nanoTime() - inicio;
        
        System.out.printf("  EnumMap: %.3f ms\n", tempoEnumMap / 1_000_000.0);
        System.out.printf("  HashMap: %.3f ms\n", tempoHashMap / 1_000_000.0);
        System.out.printf("  EnumMap é %.1fx mais rápido\n", 
                        (double) tempoHashMap / tempoEnumMap);
        
        // Uso de memória
        System.out.println("\nVantagens de EnumSet/EnumMap:");
        System.out.println("  • Performance superior (operações em O(1))");
        System.out.println("  • Menor uso de memória (bit vector ou array)");
        System.out.println("  • Type safety garantida em tempo de compilação");
        System.out.println("  • Ordem de iteração previsível (ordem de declaração)");
    }
}
