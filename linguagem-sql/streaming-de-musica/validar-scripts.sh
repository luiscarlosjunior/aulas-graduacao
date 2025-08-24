#!/bin/bash
# =====================================================
# VALIDAÇÃO DOS SCRIPTS SQL - MUSISTREAM
# =====================================================
# Script para validar sintaxe SQL básica

echo "================================================="
echo "VALIDAÇÃO DOS SCRIPTS SQL - SISTEMA MUSISTREAM"
echo "================================================="

# Função para contar elementos SQL
count_sql_elements() {
    file=$1
    echo "Analisando arquivo: $file"
    echo "─────────────────────────────────────────────────"
    
    if [ -f "$file" ]; then
        # Contar CREATE TABLE
        create_tables=$(grep -i "^CREATE TABLE" "$file" | wc -l)
        echo "   CREATE TABLE encontrados: $create_tables"
        
        # Contar PRIMARY KEY
        primary_keys=$(grep -i "PRIMARY KEY" "$file" | wc -l)
        echo "   PRIMARY KEY encontradas: $primary_keys"
        
        # Contar FOREIGN KEY
        foreign_keys=$(grep -i "FOREIGN KEY" "$file" | wc -l)
        echo "   FOREIGN KEY encontradas: $foreign_keys"
        
        # Contar CONSTRAINT
        constraints=$(grep -i "CONSTRAINT" "$file" | wc -l)
        echo "   CONSTRAINT encontradas: $constraints"
        
        # Contar CHECK
        checks=$(grep -i "CHECK" "$file" | wc -l)
        echo "   CHECK constraints: $checks"
        
        # Verificar sintaxe básica
        echo "   Verificações básicas:"
        
        # Parênteses balanceados
        open_parens=$(grep -o "(" "$file" | wc -l)
        close_parens=$(grep -o ")" "$file" | wc -l)
        if [ $open_parens -eq $close_parens ]; then
            echo "   ✓ Parênteses balanceados ($open_parens/$close_parens)"
        else
            echo "   ✗ Parênteses desbalanceados ($open_parens/$close_parens)"
        fi
        
        # Pontos e vírgulas
        semicolons=$(grep -o ";" "$file" | wc -l)
        echo "   ✓ Pontos e vírgulas encontrados: $semicolons"
        
        # Palavras-chave SQL essenciais
        if grep -qi "CREATE\|TABLE\|PRIMARY\|KEY" "$file"; then
            echo "   ✓ Palavras-chave SQL encontradas"
        else
            echo "   ✗ Palavras-chave SQL não encontradas"
        fi
        
        echo ""
    else
        echo "   ✗ Arquivo não encontrado!"
        echo ""
    fi
}

# Validar arquivos principais
echo "1. Validando arquivo principal de estrutura:"
count_sql_elements "01-estrutura-completa.sql"

echo "2. Validando script CREATE TABLE simplificado:"
count_sql_elements "create-tables.sql"

echo "3. Validando script de inserção de dados:"
count_sql_elements "02-inserir-dados.sql"

# Verificar completude do modelo
echo "================================================="
echo "VERIFICAÇÃO DE COMPLETUDE DO MODELO"
echo "================================================="

if [ -f "create-tables.sql" ]; then
    echo "Tabelas principais esperadas:"
    expected_tables=("usuario" "artista" "genero" "album" "musica" "playlist" "tipo_assinatura" "assinatura" "playlist_musica" "historico_reproducao")
    
    for table in "${expected_tables[@]}"; do
        if grep -qi "CREATE TABLE $table" "create-tables.sql"; then
            echo "   ✓ $table"
        else
            echo "   ✗ $table (não encontrada)"
        fi
    done
fi

echo ""
echo "================================================="
echo "RESUMO DA VALIDAÇÃO"
echo "================================================="

# Verificar se arquivos de documentação existem
echo "Documentação:"
if [ -f "modelagem-dados.md" ]; then
    echo "   ✓ Modelagem de dados documentada"
else
    echo "   ✗ Modelagem de dados não documentada"
fi

if [ -f "diagrama-er.md" ]; then
    echo "   ✓ Diagrama ER documentado"
else
    echo "   ✗ Diagrama ER não documentado"
fi

if [ -f "README.md" ]; then
    echo "   ✓ README presente"
else
    echo "   ✗ README não encontrado"
fi

echo ""
echo "Scripts SQL:"
for sql_file in *.sql; do
    if [ -f "$sql_file" ]; then
        size=$(stat -c%s "$sql_file")
        if [ $size -gt 1000 ]; then
            echo "   ✓ $sql_file (${size} bytes)"
        else
            echo "   ⚠ $sql_file (${size} bytes - muito pequeno)"
        fi
    fi
done

echo ""
echo "================================================="
echo "Validação concluída!"
echo "================================================="