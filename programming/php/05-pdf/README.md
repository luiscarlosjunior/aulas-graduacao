# Geração de PDF com PHP

Esta seção aborda a geração de documentos PDF usando PHP, incluindo diferentes bibliotecas e técnicas para criar relatórios, faturas, certificados e outros documentos.

## 📋 Pré-requisitos

- Conhecimento dos [conceitos básicos do PHP](../01-conceitos-php/)
- Familiaridade com [POO em PHP](../02-poo/)
- Composer instalado (para gerenciar dependências)
- Conhecimentos básicos de HTML/CSS (para alguns métodos)

## 🎯 Objetivos de Aprendizado

Ao final desta seção, você será capaz de:

- Gerar PDFs usando diferentes bibliotecas PHP
- Criar relatórios com dados do banco de dados
- Adicionar imagens, tabelas e formatação aos PDFs
- Implementar download automático de documentos
- Otimizar PDFs para diferentes finalidades

## 📚 Bibliotecas Disponíveis

### [mPDF](mpdf-8.0.11/) - Recomendada

A mPDF é uma das bibliotecas mais populares e completas para geração de PDF em PHP:

**Características:**
- Suporte completo a UTF-8 e caracteres especiais
- Conversão de HTML/CSS para PDF
- Suporte a imagens (JPEG, PNG, GIF)
- Headers e footers personalizados
- Marcas d'água
- Proteção por senha
- Formulários PDF

**Instalação:**
```bash
composer require mpdf/mpdf
```

### [PDF](pdf/) - Exemplos Básicos

Exemplos básicos usando diferentes abordagens para geração de PDF.

## 🚀 Configurando o Ambiente

### Opção 1: Composer (Recomendado)

```bash
# Na pasta do seu projeto
composer require mpdf/mpdf

# Ou para TCPDF
composer require tecnickcom/tcpdf

# Ou para FPDF
composer require setasign/fpdf
```

### Opção 2: Download Manual

1. Baixe a biblioteca desejada
2. Extraia na pasta `vendor/` ou `libs/`
3. Inclua os arquivos necessários

## 💻 Exemplos Práticos

### 1. **PDF Básico com mPDF**

```php
<?php
require_once 'vendor/autoload.php';

use Mpdf\Mpdf;

try {
    // Configurar mPDF
    $mpdf = new Mpdf([
        'mode' => 'utf-8',
        'format' => 'A4',
        'orientation' => 'P', // P = Portrait, L = Landscape
        'margin_left' => 15,
        'margin_right' => 15,
        'margin_top' => 20,
        'margin_bottom' => 20,
    ]);
    
    // Metadados do documento
    $mpdf->SetTitle('Meu Primeiro PDF');
    $mpdf->SetAuthor('João Silva');
    $mpdf->SetSubject('Exemplo de PDF');
    $mpdf->SetKeywords('PHP, PDF, mPDF');
    
    // Conteúdo HTML
    $html = '
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <style>
            body { font-family: Arial, sans-serif; }
            h1 { color: #333; text-align: center; }
            .highlight { background-color: #ffff99; }
        </style>
    </head>
    <body>
        <h1>Meu Primeiro PDF</h1>
        <p>Este é um exemplo de <span class="highlight">PDF gerado com mPDF</span>.</p>
        <p>Data de geração: ' . date('d/m/Y H:i:s') . '</p>
        
        <h2>Lista de Características:</h2>
        <ul>
            <li>Suporte a UTF-8</li>
            <li>HTML e CSS</li>
            <li>Imagens e tabelas</li>
            <li>Headers e footers</li>
        </ul>
    </body>
    </html>
    ';
    
    // Adicionar conteúdo
    $mpdf->WriteHTML($html);
    
    // Saída do PDF
    $mpdf->Output('meu_primeiro_pdf.pdf', 'I'); // I = Inline, D = Download, F = File
    
} catch (\Mpdf\MpdfException $e) {
    echo 'Erro ao gerar PDF: ' . $e->getMessage();
}
?>
```

### 2. **Relatório com Dados do Banco**

```php
<?php
require_once 'vendor/autoload.php';
require_once 'config/database.php';

use Mpdf\Mpdf;

try {
    // Conectar ao banco
    $pdo = Database::getInstance()->getConnection();
    
    // Buscar dados
    $sql = "SELECT id, nome, email, data_criacao FROM usuarios ORDER BY nome";
    $stmt = $pdo->query($sql);
    $usuarios = $stmt->fetchAll();
    
    // Configurar mPDF
    $mpdf = new Mpdf(['orientation' => 'L']); // Paisagem para tabelas
    
    // Cabeçalho e rodapé
    $mpdf->SetHTMLHeader('
        <div style="text-align: center; font-weight: bold; font-size: 14pt;">
            Relatório de Usuários
        </div>
    ');
    
    $mpdf->SetHTMLFooter('
        <div style="text-align: center; font-size: 8pt;">
            Página {PAGENO} de {nbpg} - Gerado em ' . date('d/m/Y H:i') . '
        </div>
    ');
    
    // CSS para o relatório
    $css = '
    <style>
        body { font-family: Arial, sans-serif; font-size: 10pt; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; font-weight: bold; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .total { font-weight: bold; background-color: #e6f3ff; }
    </style>
    ';
    
    // Conteúdo HTML
    $html = $css . '
    <h2>Relatório de Usuários Cadastrados</h2>
    <p><strong>Total de usuários:</strong> ' . count($usuarios) . '</p>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>E-mail</th>
                <th>Data de Cadastro</th>
            </tr>
        </thead>
        <tbody>';
    
    foreach ($usuarios as $usuario) {
        $dataFormatada = date('d/m/Y', strtotime($usuario['data_criacao']));
        $html .= '
            <tr>
                <td>' . $usuario['id'] . '</td>
                <td>' . htmlspecialchars($usuario['nome']) . '</td>
                <td>' . htmlspecialchars($usuario['email']) . '</td>
                <td>' . $dataFormatada . '</td>
            </tr>';
    }
    
    $html .= '
        </tbody>
    </table>
    
    <p style="margin-top: 30px; font-style: italic;">
        Relatório gerado automaticamente pelo sistema em ' . date('d/m/Y \à\s H:i:s') . '
    </p>
    ';
    
    $mpdf->WriteHTML($html);
    
    // Gerar nome do arquivo com timestamp
    $nomeArquivo = 'relatorio_usuarios_' . date('Y-m-d_H-i-s') . '.pdf';
    
    // Saída
    $mpdf->Output($nomeArquivo, 'D'); // Download automático
    
} catch (Exception $e) {
    echo 'Erro ao gerar relatório: ' . $e->getMessage();
}
?>
```

### 3. **Fatura/Invoice com Formatação Avançada**

```php
<?php
require_once 'vendor/autoload.php';

use Mpdf\Mpdf;

// Dados da fatura (normalmente viriam do banco)
$fatura = [
    'numero' => 'INV-2024-001',
    'data_emissao' => '2024-01-15',
    'data_vencimento' => '2024-02-15',
    'empresa' => [
        'nome' => 'Minha Empresa Ltda',
        'endereco' => 'Rua das Flores, 123',
        'cidade' => 'São Paulo - SP',
        'cnpj' => '12.345.678/0001-90',
        'telefone' => '(11) 9999-9999'
    ],
    'cliente' => [
        'nome' => 'Cliente Exemplo',
        'endereco' => 'Av. Principal, 456',
        'cidade' => 'Rio de Janeiro - RJ',
        'cpf' => '123.456.789-00'
    ],
    'itens' => [
        ['descricao' => 'Consultoria em PHP', 'quantidade' => 10, 'valor_unitario' => 150.00],
        ['descricao' => 'Desenvolvimento de Sistema', 'quantidade' => 1, 'valor_unitario' => 2500.00],
        ['descricao' => 'Treinamento Equipe', 'quantidade' => 8, 'valor_unitario' => 200.00]
    ]
];

// Calcular totais
$subtotal = 0;
foreach ($fatura['itens'] as &$item) {
    $item['total'] = $item['quantidade'] * $item['valor_unitario'];
    $subtotal += $item['total'];
}
$imposto = $subtotal * 0.10; // 10% de imposto
$total = $subtotal + $imposto;

try {
    $mpdf = new Mpdf(['format' => 'A4']);
    
    $css = '
    <style>
        body { font-family: Arial, sans-serif; font-size: 10pt; }
        .header { text-align: center; margin-bottom: 30px; }
        .empresa { font-size: 14pt; font-weight: bold; color: #2c3e50; }
        .info-boxes { width: 100%; margin-bottom: 20px; }
        .info-box { width: 48%; float: left; border: 1px solid #ddd; padding: 15px; }
        .info-box.right { float: right; }
        .clear { clear: both; }
        .invoice-details { margin: 20px 0; }
        .invoice-number { font-size: 18pt; font-weight: bold; color: #e74c3c; }
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #34495e; color: white; font-weight: bold; }
        .text-right { text-align: right; }
        .text-center { text-align: center; }
        .total-row { background-color: #ecf0f1; font-weight: bold; }
        .grand-total { background-color: #2c3e50; color: white; font-weight: bold; }
    </style>
    ';
    
    $html = $css . '
    <div class="header">
        <div class="empresa">' . $fatura['empresa']['nome'] . '</div>
        <div>' . $fatura['empresa']['endereco'] . '</div>
        <div>' . $fatura['empresa']['cidade'] . '</div>
        <div>CNPJ: ' . $fatura['empresa']['cnpj'] . ' | Tel: ' . $fatura['empresa']['telefone'] . '</div>
    </div>
    
    <div class="invoice-details text-center">
        <div class="invoice-number">FATURA Nº ' . $fatura['numero'] . '</div>
    </div>
    
    <div class="info-boxes">
        <div class="info-box">
            <strong>FATURAR PARA:</strong><br>
            ' . $fatura['cliente']['nome'] . '<br>
            ' . $fatura['cliente']['endereco'] . '<br>
            ' . $fatura['cliente']['cidade'] . '<br>
            CPF: ' . $fatura['cliente']['cpf'] . '
        </div>
        
        <div class="info-box right">
            <strong>DETALHES DA FATURA:</strong><br>
            <strong>Data de Emissão:</strong> ' . date('d/m/Y', strtotime($fatura['data_emissao'])) . '<br>
            <strong>Data de Vencimento:</strong> ' . date('d/m/Y', strtotime($fatura['data_vencimento'])) . '<br>
            <strong>Status:</strong> Pendente
        </div>
    </div>
    
    <div class="clear"></div>
    
    <table>
        <thead>
            <tr>
                <th>Descrição</th>
                <th class="text-center">Qtd</th>
                <th class="text-right">Valor Unit.</th>
                <th class="text-right">Total</th>
            </tr>
        </thead>
        <tbody>';
    
    foreach ($fatura['itens'] as $item) {
        $html .= '
            <tr>
                <td>' . $item['descricao'] . '</td>
                <td class="text-center">' . $item['quantidade'] . '</td>
                <td class="text-right">R$ ' . number_format($item['valor_unitario'], 2, ',', '.') . '</td>
                <td class="text-right">R$ ' . number_format($item['total'], 2, ',', '.') . '</td>
            </tr>';
    }
    
    $html .= '
            <tr class="total-row">
                <td colspan="3" class="text-right"><strong>Subtotal:</strong></td>
                <td class="text-right"><strong>R$ ' . number_format($subtotal, 2, ',', '.') . '</strong></td>
            </tr>
            <tr class="total-row">
                <td colspan="3" class="text-right"><strong>Impostos (10%):</strong></td>
                <td class="text-right"><strong>R$ ' . number_format($imposto, 2, ',', '.') . '</strong></td>
            </tr>
            <tr class="grand-total">
                <td colspan="3" class="text-right"><strong>TOTAL GERAL:</strong></td>
                <td class="text-right"><strong>R$ ' . number_format($total, 2, ',', '.') . '</strong></td>
            </tr>
        </tbody>
    </table>
    
    <div style="margin-top: 40px; padding: 15px; border: 1px solid #ddd; background-color: #f8f9fa;">
        <strong>Instruções de Pagamento:</strong><br>
        • Pagamento via transferência bancária<br>
        • Banco: Banco Exemplo - Agência: 1234 - Conta: 56789-0<br>
        • PIX: contato@minhaempresa.com.br<br>
        • Vencimento: ' . date('d/m/Y', strtotime($fatura['data_vencimento'])) . '
    </div>
    ';
    
    $mpdf->WriteHTML($html);
    
    $nomeArquivo = 'fatura_' . $fatura['numero'] . '.pdf';
    $mpdf->Output($nomeArquivo, 'I'); // Exibir no navegador
    
} catch (Exception $e) {
    echo 'Erro ao gerar fatura: ' . $e->getMessage();
}
?>
```

### 4. **PDF com Imagens e Marca D'água**

```php
<?php
require_once 'vendor/autoload.php';

use Mpdf\Mpdf;

try {
    $mpdf = new Mpdf();
    
    // Marca d'água
    $mpdf->SetWatermarkText('CONFIDENCIAL');
    $mpdf->showWatermarkText = true;
    $mpdf->watermarkTextAlpha = 0.1;
    
    // Definir senha para o PDF
    $mpdf->SetProtection(['print'], 'senha_usuario', 'senha_proprietario');
    
    $html = '
    <style>
        body { font-family: Arial, sans-serif; }
        .logo { text-align: center; margin-bottom: 30px; }
        .certificado { border: 3px solid #2c3e50; padding: 40px; margin: 20px; }
        .titulo { font-size: 24pt; font-weight: bold; text-align: center; color: #2c3e50; }
        .conteudo { font-size: 14pt; text-align: center; margin: 30px 0; }
        .assinatura { margin-top: 50px; text-align: center; }
    </style>
    
    <div class="certificado">
        <div class="logo">
            <img src="logo.png" width="150" alt="Logo da Empresa">
        </div>
        
        <div class="titulo">CERTIFICADO DE CONCLUSÃO</div>
        
        <div class="conteudo">
            Certificamos que <strong>João Silva</strong> concluiu com sucesso o curso de 
            <strong>"Desenvolvimento PHP Avançado"</strong> com carga horária de 
            <strong>40 horas</strong>, realizado no período de 
            <strong>01/01/2024 a 31/01/2024</strong>.
        </div>
        
        <div class="assinatura">
            <img src="assinatura.png" width="200" alt="Assinatura"><br>
            <strong>Prof. Maria Santos</strong><br>
            Coordenadora do Curso
        </div>
        
        <div style="text-align: center; margin-top: 30px; font-size: 10pt;">
            Certificado emitido em ' . date('d/m/Y') . ' | Código de verificação: ' . uniqid() . '
        </div>
    </div>
    ';
    
    $mpdf->WriteHTML($html);
    $mpdf->Output('certificado.pdf', 'D');
    
} catch (Exception $e) {
    echo 'Erro ao gerar certificado: ' . $e->getMessage();
}
?>
```

## 🔧 Recursos Avançados

### 1. **Headers e Footers Personalizados**

```php
// Header diferente para primeira página
$mpdf->SetHTMLHeaderByName('primeiro-header', '
    <div style="text-align: center; border-bottom: 1px solid #000;">
        <h1>Relatório Anual 2024</h1>
    </div>
');

// Footer com numeração
$mpdf->SetHTMLFooterByName('rodape-padrao', '
    <table width="100%">
        <tr>
            <td width="33%">Empresa XYZ</td>
            <td width="33%" align="center">{DATE d/m/Y}</td>
            <td width="33%" style="text-align: right;">Página {PAGENO} de {nbpg}</td>
        </tr>
    </table>
');
```

### 2. **Quebras de Página e Controle de Layout**

```php
// Quebra de página automática
$mpdf->WriteHTML('<pagebreak>');

// Quebra apenas se necessário
$mpdf->WriteHTML('<pagebreak type="next-odd">');

// Controlar quebra em tabelas
$html = '<table autosize="1" repeat_header="1">';
```

### 3. **Gráficos e Charts**

```php
// Integração com bibliotecas de gráficos
// Gerar imagem do gráfico e incluir no PDF
$chartImage = gerarGrafico($dados); // Sua função
$html = '<img src="' . $chartImage . '" width="400">';
```

## 🐛 Problemas Comuns

### 1. **Erro de memória**
```
Fatal error: Allowed memory size exhausted
```
```php
ini_set('memory_limit', '256M');
// ou configurar no php.ini
```

### 2. **Caracteres especiais não aparecem**
```php
// Configurar charset correto
$mpdf = new Mpdf(['mode' => 'utf-8']);
// Usar entidades HTML: &aacute; &ccedil; etc.
```

### 3. **Imagens não carregam**
```php
// Usar caminho absoluto
$caminhoImagem = __DIR__ . '/images/logo.png';
$html = '<img src="' . $caminhoImagem . '">';

// Ou converter para base64
$imagemBase64 = base64_encode(file_get_contents($caminhoImagem));
$html = '<img src="data:image/png;base64,' . $imagemBase64 . '">';
```

## 🌟 Boas Práticas

### 1. **Performance**
- Limite o tamanho dos PDFs
- Use cache para PDFs gerados frequentemente
- Otimize imagens antes de incluir

### 2. **Segurança**
- Valide dados antes de incluir no PDF
- Use escape para conteúdo HTML
- Configure proteção quando necessário

### 3. **Manutenibilidade**
- Separe HTML em templates
- Use classes para diferentes tipos de PDF
- Configure estilos CSS reutilizáveis

## 📖 Próximos Passos

1. **Integração com Sistemas** - APIs, Webhooks
2. **PDFs Interativos** - Formulários, botões
3. **Assinatura Digital** - Certificados digitais
4. **Automação** - Geração em massa, scheduling
5. **Cloud Storage** - AWS S3, Google Drive

## 📚 Recursos Adicionais

- [Documentação mPDF](https://mpdf.github.io/)
- [TCPDF Documentation](https://tcpdf.org/)
- [CSS Print Specifications](https://www.w3.org/TR/css-print/)

---

💡 **Dica**: Para PDFs complexos, considere criar templates HTML separados e usar um sistema de template como Twig para maior flexibilidade!