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
    
    cd "$pattern" || exit 1
    
    # Clean previous compilation
    rm -f *.class 2>/dev/null
    
    # Compile
    if javac *.java 2>&1; then
        echo "✓ Compilação OK"
        
        # Find and run test class
        TEST_CLASS=$(ls Teste*.java 2>/dev/null | head -1 | sed 's/.java//')
        if [ -n "$TEST_CLASS" ]; then
            if java "$TEST_CLASS" > /dev/null 2>&1; then
                echo "✓ Execução OK"
                SUCCESS=$((SUCCESS + 1))
            else
                echo "✗ Erro na execução"
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
echo "║  Sucesso: $SUCCESS/${#PATTERNS[@]}                                           ║"
echo "║  Falhas:  $FAILED/${#PATTERNS[@]}                                            ║"
echo "╚════════════════════════════════════════════════════════════╝"

if [ $FAILED -eq 0 ]; then
    echo "✓ Todos os padrões funcionaram corretamente!"
    exit 0
else
    echo "✗ Alguns padrões falharam"
    exit 1
fi
