#!/bin/bash

echo "╔════════════════════════════════════════════════════════════╗"
echo "║   TESTANDO TODOS OS PADRÕES COMPORTAMENTAIS              ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

PATTERNS=(
    "observer"
    "strategy"
    "command"
    "state"
    "template-method"
    "chain-of-responsibility"
    "iterator"
    "mediator"
    "memento"
    "visitor"
    "interpreter"
)

FAILED=0
SUCCESS=0

for pattern in "${PATTERNS[@]}"; do
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo "Testando: $pattern"
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    
    cd "$pattern" || {
        echo "✗ Erro: diretório não encontrado"
        FAILED=$((FAILED + 1))
        continue
    }
    
    # Clean previous compilation
    rm -f *.class 2>/dev/null
    
    # Compile
    if javac *.java 2>&1; then
        echo "✓ Compilação OK"
        
        # Find and run test class
        TEST_CLASS=$(ls Teste*.java 2>/dev/null | head -1 | sed 's/.java//')
        if [ -n "$TEST_CLASS" ]; then
            OUTPUT=$(java "$TEST_CLASS" 2>&1)
            if [ $? -eq 0 ]; then
                echo "✓ Execução OK"
                SUCCESS=$((SUCCESS + 1))
            else
                echo "✗ Erro na execução"
                echo "Saída do erro:"
                echo "$OUTPUT" | head -20
                FAILED=$((FAILED + 1))
            fi
        fi
    else
        echo "✗ Erro na compilação"
        FAILED=$((FAILED + 1))
    fi
    
    cd ..
    echo ""
done

echo "╔════════════════════════════════════════════════════════════╗"
echo "║                    RESULTADO FINAL                        ║"
echo "╠════════════════════════════════════════════════════════════╣"
printf "║  Sucesso: %-2d/%-2d                                          ║\n" "$SUCCESS" "${#PATTERNS[@]}"
printf "║  Falhas:  %-2d/%-2d                                          ║\n" "$FAILED" "${#PATTERNS[@]}"
echo "╚════════════════════════════════════════════════════════════╝"

if [ $FAILED -eq 0 ]; then
    echo "✓ Todos os padrões funcionaram corretamente!"
    exit 0
else
    echo "✗ Alguns padrões falharam"
    exit 1
fi
