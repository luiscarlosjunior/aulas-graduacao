/**
 * Operadores Bitwise e Especiais em Java — com Exemplos da Indústria
 *
 * ============================================================
 * POR QUE OPERADORES BITWISE IMPORTAM NA INDÚSTRIA?
 * ============================================================
 * Operadores bit a bit (AND, OR, XOR, deslocamento) são usados em:
 *
 *  1. SISTEMAS DE PERMISSÃO: Linux usa bits para rwx (leitura, escrita, exec)
 *     Ex: chmod 755 = 0b111_101_101 (owner=rwx, group=r-x, others=r-x)
 *
 *  2. REDES/PROTOCOLOS: máscaras de sub-rede, flags em headers TCP/IP
 *     Ex: IP 192.168.1.0/24 → máscara 255.255.255.0 (0xFFFFFF00)
 *
 *  3. PERFORMANCE: multiplicar/dividir por 2 com shift é mais rápido
 *     Ex: n << 1 = n * 2,  n >> 1 = n / 2 (sem divisão de ponto flutuante)
 *
 *  4. CRIPTOGRAFIA: algoritmos AES, SHA usam XOR e shifts intensamente
 *
 *  5. COMPRESSÃO DE DADOS: pack/unpack de valores em poucos bytes
 *
 * ============================================================
 * POR QUE OPERADORES ESPECIAIS IMPORTAM?
 * ============================================================
 * - Operador ternário: código limpo e conciso (3 linhas → 1 linha)
 * - instanceof: polimorfismo e type-safe casting
 * - Cast: conversão explícita necessária em APIs legadas e generics
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */
public class OperadoresBitwise {

    // ============================================================
    // Sistema de permissões usando flags de bit (como Unix/Linux)
    // ============================================================
    // Cada permissão é um bit separado — permite combinar múltiplas permissões
    // em um único inteiro sem usar arrays ou listas!
    public static final int PERM_LEITURA    = 0b0001;  // bit 0 = 1  (decimal: 1)
    public static final int PERM_ESCRITA    = 0b0010;  // bit 1 = 2  (decimal: 2)
    public static final int PERM_EXECUCAO   = 0b0100;  // bit 2 = 4  (decimal: 4)
    public static final int PERM_ADMIN      = 0b1000;  // bit 3 = 8  (decimal: 8)

    public static void main(String[] args) {
        System.out.println("=== OPERADORES BITWISE E ESPECIAIS — EXEMPLOS DA INDÚSTRIA ===\n");

        sistemaPermissoesBit();
        operacoesBitwise();
        deslocamentoBits();
        operadorTernario();
        operadorInstanceof();
    }

    // ----------------------------------------------------------
    // 1. Sistema de Permissões com Flags de Bit
    // ----------------------------------------------------------
    static void sistemaPermissoesBit() {
        System.out.println("--- 1. SISTEMA DE PERMISSÕES COM BITS (Como Linux/Unix) ---");

        System.out.println("  Permissões definidas como bits:");
        System.out.printf("  LEITURA  = %4d = 0b%04d%n", PERM_LEITURA,  Integer.parseInt(Integer.toBinaryString(PERM_LEITURA)));
        System.out.printf("  ESCRITA  = %4d = 0b%04d%n", PERM_ESCRITA,  Integer.parseInt(Integer.toBinaryString(PERM_ESCRITA)));
        System.out.printf("  EXECUCAO = %4d = 0b%04d%n", PERM_EXECUCAO, Integer.parseInt(Integer.toBinaryString(PERM_EXECUCAO)));
        System.out.printf("  ADMIN    = %4d = 0b%04d%n", PERM_ADMIN,    Integer.parseInt(Integer.toBinaryString(PERM_ADMIN)));
        System.out.println();

        // Criar perfis de usuário combinando permissões com OR bit a bit
        int perfilLeitor = PERM_LEITURA;                            // = 0001 = 1
        int perfilEditor = PERM_LEITURA | PERM_ESCRITA;            // = 0011 = 3
        int perfilGerente = PERM_LEITURA | PERM_ESCRITA
                          | PERM_EXECUCAO;                          // = 0111 = 7
        int perfilAdmin   = PERM_LEITURA | PERM_ESCRITA
                          | PERM_EXECUCAO | PERM_ADMIN;            // = 1111 = 15

        System.out.println("  Perfis criados com OR (|) bit a bit:");
        exibirPermissoes("Leitor",  perfilLeitor);
        exibirPermissoes("Editor",  perfilEditor);
        exibirPermissoes("Gerente", perfilGerente);
        exibirPermissoes("Admin",   perfilAdmin);

        System.out.println();

        // Verificar permissão com AND bit a bit
        int usuarioAtual = perfilGerente;
        System.out.println("  Verificando permissões do Gerente:");
        System.out.println("  Tem leitura?  " + temPermissao(usuarioAtual, PERM_LEITURA));
        System.out.println("  Tem escrita?  " + temPermissao(usuarioAtual, PERM_ESCRITA));
        System.out.println("  Tem execução? " + temPermissao(usuarioAtual, PERM_EXECUCAO));
        System.out.println("  Tem admin?    " + temPermissao(usuarioAtual, PERM_ADMIN));

        System.out.println();
        System.out.println("  Removendo permissão de ESCRITA do gerente (com XOR ^):");
        int perfilSemEscrita = usuarioAtual ^ PERM_ESCRITA;  // toggle do bit
        exibirPermissoes("Gerente sem escrita", perfilSemEscrita);
        System.out.println();
    }

    // ----------------------------------------------------------
    // 2. Operações Bit a Bit
    // ----------------------------------------------------------
    static void operacoesBitwise() {
        System.out.println("--- 2. OPERAÇÕES BIT A BIT EXPLICADAS ---");

        int a = 0b1010;  // decimal: 10
        int b = 0b1100;  // decimal: 12

        System.out.printf("  a   = %4d = %s%n", a, Integer.toBinaryString(a));
        System.out.printf("  b   = %4d = %s%n", b, Integer.toBinaryString(b));
        System.out.println();

        // AND (&): resultado tem 1 apenas onde AMBOS têm 1
        int andResult = a & b;  // 0b1000 = 8
        System.out.printf("  a & b  = %4d = %s  (AND: 1 só onde ambos=1)%n",
            andResult, padLeft(Integer.toBinaryString(andResult), 4));

        // OR (|): resultado tem 1 onde pelo menos UM tem 1
        int orResult = a | b;   // 0b1110 = 14
        System.out.printf("  a | b  = %4d = %s  (OR:  1 onde qualquer um=1)%n",
            orResult, padLeft(Integer.toBinaryString(orResult), 4));

        // XOR (^): resultado tem 1 onde os bits são DIFERENTES
        int xorResult = a ^ b;  // 0b0110 = 6
        System.out.printf("  a ^ b  = %4d = %s  (XOR: 1 onde bits diferentes)%n",
            xorResult, padLeft(Integer.toBinaryString(xorResult), 4));

        // NOT (~): inverte todos os bits
        int notA = ~a;
        System.out.printf("  ~a     = %4d = ...%s (NOT: inverte todos os bits)%n",
            notA, padLeft(Integer.toBinaryString(a), 4));

        System.out.println();
        System.out.println("  >> XOR é muito usado em criptografia simples (one-time pad):");
        System.out.println("     original XOR chave = encriptado");
        System.out.println("     encriptado XOR chave = original (desfaz a operação!)");
        byte mensagem = 0b01001000;         // 'H' = 72
        byte chave    = (byte)0b10110011;   // chave secreta = -77 (signed byte)
        byte encriptado = (byte)(mensagem ^ chave);
        byte decriptado = (byte)(encriptado ^ chave);
        System.out.printf("     'H' (%d) XOR chave → %d → XOR chave de volta → %d ('H')%n",
            mensagem, encriptado, decriptado);
        System.out.println();
    }

    // ----------------------------------------------------------
    // 3. Deslocamento de Bits — otimização de performance
    // ----------------------------------------------------------
    static void deslocamentoBits() {
        System.out.println("--- 3. DESLOCAMENTO DE BITS (Shift) — OTIMIZAÇÃO ---");

        int n = 4;
        System.out.printf("  Número original: %d%n%n", n);

        // Left shift (<<): multiplica por 2^n (muito mais rápido que multiplicação)
        System.out.println("  Left shift << (multiplica por potência de 2):");
        System.out.printf("  %d << 1 = %d (equivale a %d * 2 = %d)%n", n, n<<1, n, n*2);
        System.out.printf("  %d << 2 = %d (equivale a %d * 4 = %d)%n", n, n<<2, n, n*4);
        System.out.printf("  %d << 3 = %d (equivale a %d * 8 = %d)%n", n, n<<3, n, n*8);

        System.out.println();

        // Right shift (>>): divide por 2^n (preserva sinal)
        int m = 32;
        System.out.println("  Right shift >> (divide por potência de 2):");
        System.out.printf("  %d >> 1 = %d (equivale a %d / 2 = %d)%n", m, m>>1, m, m/2);
        System.out.printf("  %d >> 2 = %d (equivale a %d / 4 = %d)%n", m, m>>2, m, m/4);
        System.out.printf("  %d >> 3 = %d (equivale a %d / 8 = %d)%n", m, m>>3, m, m/8);

        System.out.println();
        System.out.println("  >> Uso real: Em motores de jogos (Unity, Unreal), operações");
        System.out.println("     em loops de físicas usam shifts para ganhar microsegundos.");
        System.out.println("     Ex: posicao *= 2  →  posicao <<= 1  (mais rápido)");
        System.out.println();

        // Exemplo prático: Verificar se número é potência de 2
        System.out.println("  Verificar se é potência de 2 (útil para tamanhos de buffer):");
        for (int val : new int[]{1, 2, 3, 4, 5, 8, 10, 16, 17, 32}) {
            boolean isPotencia2 = val > 0 && (val & (val - 1)) == 0;
            System.out.printf("    %2d → %s%n", val, isPotencia2 ? "✅ Potência de 2" : "❌ Não é potência de 2");
        }
        System.out.println();
    }

    // ----------------------------------------------------------
    // 4. Operador Ternário — código mais limpo
    // ----------------------------------------------------------
    static void operadorTernario() {
        System.out.println("--- 4. OPERADOR TERNÁRIO (?:) — CÓDIGO MAIS LIMPO ---");

        System.out.println("  Sintaxe: condicao ? valorSeVerdadeiro : valorSeFalso");
        System.out.println();

        // Forma verbosa (sem ternário)
        double salario = 4500.0;
        String faixaSalarial;
        if (salario > 3000) {
            faixaSalarial = "Acima da média";
        } else {
            faixaSalarial = "Abaixo da média";
        }
        System.out.println("  Forma verbosa (sem ternário):   " + faixaSalarial);

        // Forma concisa (com ternário) — mesma lógica, 1 linha
        String faixaComTernario = salario > 3000 ? "Acima da média" : "Abaixo da média";
        System.out.println("  Forma concisa (com ternário):   " + faixaComTernario);

        System.out.println();

        // Usos comuns na indústria
        int quantidade = 1;
        System.out.println("  Usos comuns em sistemas reais:");

        // Pluralização
        String resultado = quantidade + " " + (quantidade == 1 ? "produto" : "produtos") + " encontrado(s)";
        System.out.println("    Pluralização: '" + resultado + "'");

        // Status de conexão
        boolean online = true;
        String status = online ? "🟢 Online" : "🔴 Offline";
        System.out.println("    Status conexão: " + status);

        // Frete
        double totalCarrinho = 280.0;
        double frete = totalCarrinho >= 250 ? 0.0 : 15.90;
        System.out.printf("    Frete (carrinho R$%.0f): R$%.2f%s%n",
            totalCarrinho, frete, frete == 0 ? " 🎉" : "");

        System.out.println();
        System.out.println("  ⚠️  Evite ternários aninhados — dificulta a leitura:");
        System.out.println("     RUIM: a ? b ? c : d : e ? f : g");
        System.out.println("     BOM:  Use if-else para lógica complexa");
        System.out.println();
    }

    // ----------------------------------------------------------
    // 5. Operador instanceof e Cast seguro
    // ----------------------------------------------------------
    static void operadorInstanceof() {
        System.out.println("--- 5. OPERADOR instanceof E CAST SEGURO ---");

        // Cria objetos de tipos diferentes
        Object[] objetos = {
            "Texto Java",
            42,
            3.14,
            true
        };

        System.out.println("  Processando array de Object (polimorfismo):");
        for (Object obj : objetos) {
            if (obj instanceof String) {
                String texto = (String) obj;
                System.out.println("    String: '" + texto + "' (length=" + texto.length() + ")");
            } else if (obj instanceof Integer) {
                int numero = (Integer) obj;
                System.out.println("    Integer: " + numero + " (dobro=" + (numero * 2) + ")");
            } else if (obj instanceof Double) {
                double decimal = (Double) obj;
                System.out.printf("    Double: %.2f (raiz=%.2f)%n", decimal, Math.sqrt(decimal));
            } else if (obj instanceof Boolean) {
                boolean bool = (Boolean) obj;
                System.out.println("    Boolean: " + bool + " (negado=" + !bool + ")");
            }
        }

        System.out.println();
        System.out.println("  >> instanceof é muito usado ao desserializar JSON,");
        System.out.println("     ao processar eventos de UI, e em frameworks como");
        System.out.println("     Spring (verificar tipo de Bean) e Hibernate (entidades).");
        System.out.println();

        // Java 16+: Pattern Matching para instanceof (código mais moderno)
        System.out.println("  Modern Java (16+): Pattern Matching com instanceof");
        System.out.println("  Em vez de: if(obj instanceof String) { String s=(String)obj; }");
        System.out.println("  Escreva:   if(obj instanceof String s) { /* usa s direto */ }");
        System.out.println("  Mais limpo, menos boilerplate, menos chance de ClassCastException");
        System.out.println();
    }

    // ----------------------------------------------------------
    // Métodos auxiliares
    // ----------------------------------------------------------

    static boolean temPermissao(int perfil, int permissao) {
        // AND bit a bit: se o bit da permissão estiver setado, resultado != 0
        return (perfil & permissao) != 0;
    }

    static void exibirPermissoes(String nomePerfil, int perfil) {
        String leitura  = temPermissao(perfil, PERM_LEITURA)  ? "✅" : "❌";
        String escrita  = temPermissao(perfil, PERM_ESCRITA)  ? "✅" : "❌";
        String execucao = temPermissao(perfil, PERM_EXECUCAO) ? "✅" : "❌";
        String admin    = temPermissao(perfil, PERM_ADMIN)    ? "✅" : "❌";
        System.out.printf("  %-20s (bits=%4d) | Leitura:%s Escrita:%s Exec:%s Admin:%s%n",
            nomePerfil, perfil, leitura, escrita, execucao, admin);
    }

    static String padLeft(String s, int length) {
        while (s.length() < length) s = "0" + s;
        return s;
    }
}
