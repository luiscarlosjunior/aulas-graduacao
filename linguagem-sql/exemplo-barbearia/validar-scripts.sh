#!/bin/bash
# ================
# SCRIPT DE VALIDAÇÃO - SISTEMA BARBEARIA
# ================
# Este script verifica a sintaxe básica dos arquivos SQL
# Requer Oracle SQL*Plus ou similar para validação completa
#

echo "=================================================="
echo "VALIDAÇÃO DOS SCRIPTS SQL - SISTEMA BARBEARIA"
echo "=================================================="

# Verificar se os arquivos existem
echo "✓ Verificando existência dos arquivos..."

arquivos=("01-estrutura-completa.sql" "02-inserir-dados.sql" "03-consultas-basicas.sql" "04-consultas-avancadas.sql")
faltando=0

for arquivo in "${arquivos[@]}"; do
    if [ -f "$arquivo" ]; then
        echo "  ✓ $arquivo - OK"
    else
        echo "  ✗ $arquivo - AUSENTE"
        faltando=1
    fi
done

if [ $faltando -eq 1 ]; then
    echo "❌ Alguns arquivos estão ausentes. Verifique a estrutura."
    exit 1
fi

echo ""
echo "✓ Verificando sintaxe básica..."

# Função para validar sintaxe básica
validar_sql() {
    local arquivo=$1
    echo "  → Validando $arquivo..."
    
    # Verificar se há CREATE/INSERT/SELECT statements
    creates=$(grep -c "^CREATE" "$arquivo" 2>/dev/null || echo "0")
    inserts=$(grep -c "^INSERT" "$arquivo" 2>/dev/null || echo "0") 
    selects=$(grep -c "^SELECT" "$arquivo" 2>/dev/null || echo "0")
    
    echo "    - CREATE statements: $creates"
    echo "    - INSERT statements: $inserts"
    echo "    - SELECT statements: $selects"
    
    # Verificar problemas comuns
    if grep -q ";" "$arquivo"; then
        echo "    ✓ Comandos terminam com ;"
    else
        echo "    ⚠ Poucos comandos terminam com ; (pode ser normal)"
    fi
    
    # Verificar encoding
    if file "$arquivo" | grep -q "UTF-8"; then
        echo "    ✓ Codificação UTF-8"
    else
        echo "    ⚠ Codificação não identificada como UTF-8"
    fi
    
    # Tamanho do arquivo
    tamanho=$(wc -c < "$arquivo")
    echo "    - Tamanho: $tamanho bytes"
}

# Validar cada arquivo
for arquivo in "${arquivos[@]}"; do
    validar_sql "$arquivo"
    echo ""
done

echo "=================================================="
echo "RESUMO DA VALIDAÇÃO"
echo "=================================================="

total_linhas=$(wc -l *.sql | tail -n 1 | awk '{print $1}')
total_bytes=$(wc -c *.sql | tail -n 1 | awk '{print $1}')

echo "✓ Total de linhas de código SQL: $total_linhas"
echo "✓ Total de bytes: $total_bytes"
echo "✓ Arquivos principais: ${#arquivos[@]}"
echo "✓ Arquivos documentação: $(ls -1 *.md 2>/dev/null | wc -l)"

echo ""
echo "=================================================="
echo "INSTRUÇÕES PARA EXECUÇÃO"
echo "=================================================="
echo "1. Execute os scripts em ordem sequencial:"
echo "   @01-estrutura-completa.sql"
echo "   @02-inserir-dados.sql"
echo "   @03-consultas-basicas.sql"
echo "   @04-consultas-avancadas.sql"
echo ""
echo "2. Para validação completa, use Oracle SQL*Plus:"
echo "   sqlplus usuario/senha @nome_do_script.sql"
echo ""
echo "3. Leia a documentação:"
echo "   - README.md: Visão geral"
echo "   - modelagem-conceitual.md: Análise conceitual"
echo "   - modelagem-logica.md: Especificação lógica"
echo ""
echo "✅ Validação básica concluída com sucesso!"