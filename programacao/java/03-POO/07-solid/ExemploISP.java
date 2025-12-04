/**
 * Demonstração do Princípio da Segregação de Interface (ISP)
 * Interface Segregation Principle
 * 
 * Nenhum cliente deve ser forçado a depender de métodos que não utiliza
 * 
 * @author Sistema de Ensino - Princípios SOLID
 */

import java.util.ArrayList;
import java.util.List;

// ==========================================
// EXEMPLO RUIM - Violando ISP
// ==========================================

// Interface "gorda" que tenta fazer tudo
interface DispositivoMultifuncionalRuim {
    void imprimir(String documento);
    void escanear();
    void enviarFax(String numero);
    void copiar();
    void enviarEmail(String email, String mensagem);
}

// Impressora moderna - OK, tem todas as funcionalidades
class ImpressoraModernaRuim implements DispositivoMultifuncionalRuim {
    @Override
    public void imprimir(String documento) {
        System.out.println("🖨️  Imprimindo: " + documento);
    }
    
    @Override
    public void escanear() {
        System.out.println("📄 Escaneando documento");
    }
    
    @Override
    public void enviarFax(String numero) {
        System.out.println("📠 Enviando fax para: " + numero);
    }
    
    @Override
    public void copiar() {
        System.out.println("📋 Copiando documento");
    }
    
    @Override
    public void enviarEmail(String email, String mensagem) {
        System.out.println("📧 Enviando email para: " + email);
    }
}

// PROBLEMA: Impressora simples não tem scanner nem fax!
class ImpressoraSimplesRuim implements DispositivoMultifuncionalRuim {
    @Override
    public void imprimir(String documento) {
        System.out.println("🖨️  Imprimindo: " + documento);
    }
    
    // Forçada a implementar métodos que não suporta!
    @Override
    public void escanear() {
        throw new UnsupportedOperationException("❌ Sem suporte a scanner");
    }
    
    @Override
    public void enviarFax(String numero) {
        throw new UnsupportedOperationException("❌ Sem suporte a fax");
    }
    
    @Override
    public void copiar() {
        throw new UnsupportedOperationException("❌ Sem suporte a cópia");
    }
    
    @Override
    public void enviarEmail(String email, String mensagem) {
        throw new UnsupportedOperationException("❌ Sem suporte a email");
    }
}

// ==========================================
// EXEMPLO BOM - Seguindo ISP
// ==========================================

// Interfaces segregadas e específicas
interface Impressora {
    void imprimir(String documento);
}

interface Scanner {
    void escanear();
}

interface Fax {
    void enviarFax(String numero);
}

interface Copiadora {
    void copiar();
}

interface EnviadorEmail {
    void enviarEmail(String email, String mensagem);
}

// Impressora simples - implementa APENAS o que pode fazer
class ImpressoraSimples implements Impressora {
    private String modelo;
    
    public ImpressoraSimples(String modelo) {
        this.modelo = modelo;
    }
    
    @Override
    public void imprimir(String documento) {
        System.out.println("🖨️  [" + modelo + "] Imprimindo: " + documento);
    }
}

// Impressora moderna - implementa múltiplas interfaces
class ImpressoraModerna implements Impressora, Scanner, Fax, Copiadora, EnviadorEmail {
    private String modelo;
    
    public ImpressoraModerna(String modelo) {
        this.modelo = modelo;
    }
    
    @Override
    public void imprimir(String documento) {
        System.out.println("🖨️  [" + modelo + "] Imprimindo: " + documento);
    }
    
    @Override
    public void escanear() {
        System.out.println("📄 [" + modelo + "] Escaneando documento");
    }
    
    @Override
    public void enviarFax(String numero) {
        System.out.println("📠 [" + modelo + "] Enviando fax para: " + numero);
    }
    
    @Override
    public void copiar() {
        System.out.println("📋 [" + modelo + "] Copiando documento");
    }
    
    @Override
    public void enviarEmail(String email, String mensagem) {
        System.out.println("📧 [" + modelo + "] Enviando email para: " + email);
    }
}

// Scanner dedicado - implementa apenas Scanner
class ScannerDedicado implements Scanner {
    private String modelo;
    
    public ScannerDedicado(String modelo) {
        this.modelo = modelo;
    }
    
    @Override
    public void escanear() {
        System.out.println("📄 [" + modelo + "] Escaneando em alta resolução");
    }
}

// Impressora com scanner (não tem fax nem email)
class ImpressoraComScanner implements Impressora, Scanner, Copiadora {
    private String modelo;
    
    public ImpressoraComScanner(String modelo) {
        this.modelo = modelo;
    }
    
    @Override
    public void imprimir(String documento) {
        System.out.println("🖨️  [" + modelo + "] Imprimindo: " + documento);
    }
    
    @Override
    public void escanear() {
        System.out.println("📄 [" + modelo + "] Escaneando documento");
    }
    
    @Override
    public void copiar() {
        System.out.println("📋 [" + modelo + "] Copiando documento");
    }
}

// Serviços que usam interfaces específicas
class ServicoImpressao {
    public void imprimirDocumentos(Impressora impressora, List<String> documentos) {
        System.out.println("\n📝 Serviço de Impressão:");
        for (String doc : documentos) {
            impressora.imprimir(doc);
        }
    }
}

class ServicoDigitalizacao {
    public void digitalizarDocumentos(Scanner scanner, int quantidade) {
        System.out.println("\n📸 Serviço de Digitalização:");
        for (int i = 1; i <= quantidade; i++) {
            System.out.print("  Documento " + i + ": ");
            scanner.escanear();
        }
    }
}

// ==========================================
// OUTRO EXEMPLO: Trabalhadores
// ==========================================

// Interface geral
interface Trabalhador {
    void trabalhar();
    String getNome();
}

// Interface para quem pode comer
interface Comedor {
    void comer();
}

// Interface para quem pode descansar
interface Descansador {
    void descansar();
}

// Humano - implementa tudo
class TrabalhadorHumano implements Trabalhador, Comedor, Descansador {
    private String nome;
    
    public TrabalhadorHumano(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void trabalhar() {
        System.out.println("👷 " + nome + " trabalhando");
    }
    
    @Override
    public void comer() {
        System.out.println("🍽️  " + nome + " comendo");
    }
    
    @Override
    public void descansar() {
        System.out.println("😴 " + nome + " descansando");
    }
    
    @Override
    public String getNome() {
        return nome;
    }
}

// Robô - só trabalha (não come nem descansa)
class TrabalhadorRobo implements Trabalhador {
    private String nome;
    
    public TrabalhadorRobo(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void trabalhar() {
        System.out.println("🤖 " + nome + " trabalhando 24/7");
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    public void recarregar() {
        System.out.println("🔋 " + nome + " recarregando bateria");
    }
}

// Gerenciador que trabalha com trabalhadores
class GerenciadorTrabalhadores {
    public void executarTrabalho(List<Trabalhador> trabalhadores) {
        System.out.println("\n💼 Executando trabalho:");
        for (Trabalhador t : trabalhadores) {
            t.trabalhar();
        }
    }
    
    public void pausaAlmoco(List<Comedor> comedores) {
        System.out.println("\n🍽️  Pausa para almoço:");
        for (Comedor c : comedores) {
            c.comer();
        }
    }
    
    public void pausaDescanso(List<Descansador> descansadores) {
        System.out.println("\n😴 Pausa para descanso:");
        for (Descansador d : descansadores) {
            d.descansar();
        }
    }
}

// ==========================================
// DEMONSTRAÇÃO E TESTES
// ==========================================

public class ExemploISP {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  PRINCÍPIO DA SEGREGAÇÃO DE INTERFACE (ISP)             ║");
        System.out.println("║  Interface Segregation Principle                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Exemplo RUIM
        System.out.println("❌ EXEMPLO RUIM - Interface gorda:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploRuim();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Dispositivos
        System.out.println("✅ EXEMPLO BOM - Interfaces segregadas:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploBom();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Trabalhadores
        System.out.println("✅ EXEMPLO BOM - Trabalhadores:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploTrabalhadores();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Análise
        imprimirAnalise();
    }
    
    private static void demonstrarExemploRuim() {
        System.out.println("ImpressoraModernaRuim (tem tudo):");
        ImpressoraModernaRuim moderna = new ImpressoraModernaRuim();
        moderna.imprimir("Documento1.pdf");
        moderna.escanear();
        moderna.copiar();
        
        System.out.println("\nImpressoraSimplesRuim (não tem scanner/fax):");
        ImpressoraSimplesRuim simples = new ImpressoraSimplesRuim();
        simples.imprimir("Documento2.pdf");
        
        System.out.println("\nTentando escanear com impressora simples:");
        try {
            simples.escanear(); // VAI LANÇAR EXCEÇÃO!
        } catch (UnsupportedOperationException e) {
            System.out.println("  ERRO: " + e.getMessage());
        }
        
        System.out.println("\n⚠️  PROBLEMAS:");
        System.out.println("   • ImpressoraSimples forçada a implementar métodos que não usa");
        System.out.println("   • Cliente pode chamar métodos não suportados");
        System.out.println("   • Exceções em runtime (não em compile-time)");
        System.out.println("   • Interface não expressa capacidades reais");
    }
    
    private static void demonstrarExemploBom() {
        // Criar dispositivos
        Impressora impSimples = new ImpressoraSimples("HP DeskJet");
        Impressora impModerna = new ImpressoraModerna("Canon Multifunction");
        Scanner scanModerna = (Scanner) impModerna; // É também Scanner
        Scanner scanDedicado = new ScannerDedicado("Epson Perfection");
        
        // Serviço de impressão usa APENAS Impressora
        ServicoImpressao servicoImp = new ServicoImpressao();
        List<String> docs = new ArrayList<>();
        docs.add("Contrato.pdf");
        docs.add("Relatório.docx");
        
        servicoImp.imprimirDocumentos(impSimples, docs);
        servicoImp.imprimirDocumentos(impModerna, docs);
        
        // Serviço de digitalização usa APENAS Scanner
        ServicoDigitalizacao servicoScan = new ServicoDigitalizacao();
        servicoScan.digitalizarDocumentos(scanModerna, 2);
        servicoScan.digitalizarDocumentos(scanDedicado, 2);
        
        // Dispositivo com múltiplas capacidades
        System.out.println("\n🎯 Dispositivo multifuncional:");
        ImpressoraModerna multifuncional = new ImpressoraModerna("Brother MFC");
        multifuncional.imprimir("Foto.jpg");
        multifuncional.escanear();
        multifuncional.copiar();
        multifuncional.enviarFax("+5511999999999");
        multifuncional.enviarEmail("cliente@email.com", "Documento enviado");
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Cada classe implementa APENAS as interfaces que suporta");
        System.out.println("   ✓ Clientes dependem apenas das capacidades que usam");
        System.out.println("   ✓ Sem métodos não implementados");
        System.out.println("   ✓ Sem exceções inesperadas");
        System.out.println("   ✓ Código mais expressivo e autodocumentado");
    }
    
    private static void demonstrarExemploTrabalhadores() {
        // Criar trabalhadores
        TrabalhadorHumano joao = new TrabalhadorHumano("João");
        TrabalhadorHumano maria = new TrabalhadorHumano("Maria");
        TrabalhadorRobo robo1 = new TrabalhadorRobo("R2D2");
        TrabalhadorRobo robo2 = new TrabalhadorRobo("C3PO");
        
        GerenciadorTrabalhadores gerenciador = new GerenciadorTrabalhadores();
        
        // Todos podem trabalhar
        List<Trabalhador> todosTrabalhadores = new ArrayList<>();
        todosTrabalhadores.add(joao);
        todosTrabalhadores.add(maria);
        todosTrabalhadores.add(robo1);
        todosTrabalhadores.add(robo2);
        gerenciador.executarTrabalho(todosTrabalhadores);
        
        // Apenas humanos comem
        List<Comedor> comedores = new ArrayList<>();
        comedores.add(joao);
        comedores.add(maria);
        gerenciador.pausaAlmoco(comedores);
        
        // Apenas humanos descansam
        List<Descansador> descansadores = new ArrayList<>();
        descansadores.add(joao);
        descansadores.add(maria);
        gerenciador.pausaDescanso(descansadores);
        
        // Robôs recarregam
        System.out.println("\n🔋 Manutenção de robôs:");
        robo1.recarregar();
        robo2.recarregar();
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Robôs NÃO são forçados a implementar comer() e descansar()");
        System.out.println("   ✓ Interfaces específicas para capacidades específicas");
        System.out.println("   ✓ Tipo-seguro em compile-time");
        System.out.println("   ✓ Fácil adicionar novos tipos de trabalhadores");
    }
    
    private static void imprimirAnalise() {
        System.out.println("📊 ANÁLISE COMPARATIVA\n");
        
        System.out.println("┌─────────────────────────┬──────────────┬──────────────┐");
        System.out.println("│ Métrica                 │ Sem ISP      │ Com ISP      │");
        System.out.println("├─────────────────────────┼──────────────┼──────────────┤");
        System.out.println("│ Métodos não usados      │ Muitos       │ Nenhum       │");
        System.out.println("│ Implementações vazias   │ Sim          │ Não          │");
        System.out.println("│ Exceções em runtime     │ Comum        │ Raro         │");
        System.out.println("│ Acoplamento             │ Alto         │ Baixo        │");
        System.out.println("│ Clareza de propósito    │ Baixa        │ Alta         │");
        System.out.println("│ Manutenibilidade        │ Difícil      │ Fácil        │");
        System.out.println("└─────────────────────────┴──────────────┴──────────────┘");
        
        System.out.println("\n💡 PRINCÍPIO CHAVE:");
        System.out.println("   \"Nenhum cliente deve depender de métodos que não utiliza\"");
        System.out.println("\n🎯 SINAIS DE VIOLAÇÃO:");
        System.out.println("   • Interface com > 5-7 métodos não relacionados");
        System.out.println("   • Métodos que lançam UnsupportedOperationException");
        System.out.println("   • Implementações vazias ou com apenas return null");
        System.out.println("   • Comentários como 'não implementado' ou 'não suportado'");
        System.out.println("\n✅ SOLUÇÃO:");
        System.out.println("   • Divida interfaces grandes em interfaces menores");
        System.out.println("   • Cada interface deve ter um propósito coeso");
        System.out.println("   • Classes implementam apenas o que realmente fazem");
        System.out.println("   • Use composição de interfaces quando necessário");
        System.out.println("\n✅ BENEFÍCIO PRINCIPAL:");
        System.out.println("   Interfaces focadas e classes que implementam apenas o necessário");
    }
}
