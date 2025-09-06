#!/bin/bash
# ============================================================================
# SISTEMA DE E-COMMERCE TECHSTORE
# Script de Validação e Testes
# ============================================================================
# Descrição: Script para validar a criação e funcionamento do banco de dados
# Versão: 1.0
# Data: 2024
# ============================================================================

# Configurações
DB_NAME="ecommerce_techstore"
MYSQL_USER="root"
MYSQL_HOST="localhost"
TEST_LOG="validacao_ecommerce.log"

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para logging
log_message() {
    echo -e "$1" | tee -a "$TEST_LOG"
}

# Função para executar query e verificar resultado
execute_query() {
    local query="$1"
    local description="$2"
    local expected_result="$3"
    
    echo -e "${BLUE}Testando: $description${NC}"
    
    result=$(mysql -h$MYSQL_HOST -u$MYSQL_USER -p$DB_NAME -e "$query" 2>/dev/null | tail -n +2)
    
    if [ $? -eq 0 ]; then
        if [ -n "$expected_result" ] && [ "$result" != "$expected_result" ]; then
            log_message "${RED}❌ FALHOU: $description${NC}"
            log_message "   Esperado: $expected_result"
            log_message "   Obtido: $result"
            return 1
        else
            log_message "${GREEN}✅ PASSOU: $description${NC}"
            if [ -n "$result" ]; then
                log_message "   Resultado: $result"
            fi
            return 0
        fi
    else
        log_message "${RED}❌ ERRO: $description${NC}"
        return 1
    fi
}

# Função principal de validação
main_validation() {
    log_message "============================================================================"
    log_message "INICIANDO VALIDAÇÃO DO SISTEMA E-COMMERCE TECHSTORE"
    log_message "Data: $(date)"
    log_message "============================================================================"
    
    # Contadores
    passed_tests=0
    failed_tests=0
    total_tests=0
    
    # Verificar se o banco existe
    echo -e "${YELLOW}=== VERIFICAÇÃO DE ESTRUTURA ===${NC}"
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = '$DB_NAME';" "Banco de dados existe" "1"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar tabelas criadas
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = '$DB_NAME';" "Número de tabelas criadas" "16"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar views criadas
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM information_schema.VIEWS WHERE TABLE_SCHEMA = '$DB_NAME';" "Número de views criadas" "3"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar triggers criados
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM information_schema.TRIGGERS WHERE TRIGGER_SCHEMA = '$DB_NAME';" "Número de triggers criados"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    echo -e "${YELLOW}=== VERIFICAÇÃO DE DADOS ===${NC}"
    
    # Verificar fornecedores
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM FORNECEDOR;" "Fornecedores inseridos" "5"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar categorias
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM CATEGORIA;" "Categorias inseridas"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar produtos
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM PRODUTO;" "Produtos inseridos" "20"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar clientes
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM CLIENTE;" "Clientes inseridos" "10"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar estoque criado automaticamente
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM ESTOQUE;" "Registros de estoque criados automaticamente" "20"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar carrinhos criados automaticamente
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM CARRINHO;" "Carrinhos criados automaticamente" "10"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    echo -e "${YELLOW}=== VERIFICAÇÃO DE INTEGRIDADE ===${NC}"
    
    # Verificar integridade referencial - produtos têm categoria
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM PRODUTO p LEFT JOIN CATEGORIA c ON p.id_categoria = c.id_categoria WHERE c.id_categoria IS NULL;" "Produtos sem categoria" "0"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar integridade referencial - produtos têm fornecedor
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM PRODUTO p LEFT JOIN FORNECEDOR f ON p.id_fornecedor = f.id_fornecedor WHERE f.id_fornecedor IS NULL;" "Produtos sem fornecedor" "0"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar integridade referencial - todos produtos têm estoque
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM PRODUTO p LEFT JOIN ESTOQUE e ON p.id_produto = e.id_produto WHERE e.id_produto IS NULL;" "Produtos sem estoque" "0"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar integridade referencial - todos clientes têm carrinho
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM CLIENTE c LEFT JOIN CARRINHO car ON c.id_cliente = car.id_cliente WHERE car.id_cliente IS NULL;" "Clientes sem carrinho" "0"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    echo -e "${YELLOW}=== VERIFICAÇÃO DE CONSTRAINTS ===${NC}"
    
    # Verificar constraint de preço positivo
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM PRODUTO WHERE preco_atual <= 0;" "Produtos com preço inválido" "0"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar constraint de estoque não negativo
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM ESTOQUE WHERE quantidade_disponivel < 0 OR quantidade_reservada < 0;" "Estoque com quantidade negativa" "0"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Verificar constraint de nota de avaliação
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM AVALIACAO WHERE nota < 1 OR nota > 5;" "Avaliações com nota inválida" "0"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    echo -e "${YELLOW}=== VERIFICAÇÃO DE FUNCIONALIDADES ===${NC}"
    
    # Testar consulta básica de produtos
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM vw_produto_completo WHERE produto_ativo = 1;" "View de produtos funcionando"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Testar consulta de carrinho
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM vw_carrinho_resumo;" "View de carrinho funcionando"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Testar consulta de pedidos
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM vw_pedido_completo;" "View de pedidos funcionando"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    echo -e "${YELLOW}=== VERIFICAÇÃO DE PERFORMANCE ===${NC}"
    
    # Verificar índices criados
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM information_schema.STATISTICS WHERE TABLE_SCHEMA = '$DB_NAME';" "Índices criados"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    # Testar consulta com JOIN complexo
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM PRODUTO p INNER JOIN CATEGORIA c ON p.id_categoria = c.id_categoria INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto WHERE p.ativo = TRUE;" "Consulta complexa com JOINs"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    echo -e "${YELLOW}=== VERIFICAÇÃO DE STORED PROCEDURES ===${NC}"
    
    # Verificar se procedures foram criadas
    ((total_tests++))
    if execute_query "SELECT COUNT(*) FROM information_schema.ROUTINES WHERE ROUTINE_SCHEMA = '$DB_NAME' AND ROUTINE_TYPE = 'PROCEDURE';" "Stored procedures criadas" "2"; then
        ((passed_tests++))
    else
        ((failed_tests++))
    fi
    
    echo -e "${YELLOW}=== TESTES DE SIMULAÇÃO ===${NC}"
    
    # Simular adição de item ao carrinho (teste de trigger)
    echo -e "${BLUE}Testando trigger de reserva de estoque...${NC}"
    
    # Obter estoque atual de um produto
    current_stock=$(mysql -h$MYSQL_HOST -u$MYSQL_USER -p$DB_NAME -e "SELECT quantidade_reservada FROM ESTOQUE WHERE id_produto = 1;" 2>/dev/null | tail -n +2)
    
    # Adicionar item ao carrinho
    mysql -h$MYSQL_HOST -u$MYSQL_USER -p$DB_NAME -e "INSERT INTO ITEM_CARRINHO (id_carrinho, id_produto, quantidade, preco_unitario) VALUES (4, 1, 2, 4999.99);" 2>/dev/null
    
    # Verificar se estoque foi reservado
    new_stock=$(mysql -h$MYSQL_HOST -u$MYSQL_USER -p$DB_NAME -e "SELECT quantidade_reservada FROM ESTOQUE WHERE id_produto = 1;" 2>/dev/null | tail -n +2)
    
    if [ $(($new_stock - $current_stock)) -eq 2 ]; then
        log_message "${GREEN}✅ PASSOU: Trigger de reserva de estoque${NC}"
        ((passed_tests++))
    else
        log_message "${RED}❌ FALHOU: Trigger de reserva de estoque${NC}"
        ((failed_tests++))
    fi
    ((total_tests++))
    
    # Limpar teste
    mysql -h$MYSQL_HOST -u$MYSQL_USER -p$DB_NAME -e "DELETE FROM ITEM_CARRINHO WHERE id_carrinho = 4 AND id_produto = 1;" 2>/dev/null
    
    echo -e "${YELLOW}=== RESULTADO FINAL ===${NC}"
    
    log_message "============================================================================"
    log_message "RESUMO DOS TESTES"
    log_message "============================================================================"
    log_message "Total de testes executados: $total_tests"
    log_message "Testes aprovados: ${GREEN}$passed_tests${NC}"
    log_message "Testes falharam: ${RED}$failed_tests${NC}"
    
    success_rate=$(echo "scale=2; $passed_tests * 100 / $total_tests" | bc)
    log_message "Taxa de sucesso: $success_rate%"
    
    if [ $failed_tests -eq 0 ]; then
        log_message "${GREEN}🎉 TODOS OS TESTES PASSARAM! O sistema está funcionando corretamente.${NC}"
        return 0
    else
        log_message "${RED}⚠️  ALGUNS TESTES FALHARAM. Verifique os detalhes acima.${NC}"
        return 1
    fi
}

# Função para mostrar help
show_help() {
    echo "============================================================================"
    echo "SCRIPT DE VALIDAÇÃO - SISTEMA E-COMMERCE TECHSTORE"
    echo "============================================================================"
    echo "Uso: $0 [opções]"
    echo ""
    echo "Opções:"
    echo "  -h, --help          Mostra esta ajuda"
    echo "  -f, --full          Executa validação completa (padrão)"
    echo "  -q, --quick         Executa validação rápida (apenas estrutura)"
    echo "  -c, --create        Cria o banco antes da validação"
    echo "  -d, --data          Insere dados antes da validação"
    echo "  -v, --verbose       Modo verboso"
    echo ""
    echo "Exemplos:"
    echo "  $0                  # Validação completa"
    echo "  $0 --quick          # Validação rápida"
    echo "  $0 --create         # Criar banco e validar"
    echo ""
}

# Função para criar o banco
create_database() {
    echo -e "${YELLOW}Criando estrutura do banco de dados...${NC}"
    if [ -f "01-estrutura-completa.sql" ]; then
        mysql -h$MYSQL_HOST -u$MYSQL_USER < 01-estrutura-completa.sql
        if [ $? -eq 0 ]; then
            log_message "${GREEN}✅ Estrutura criada com sucesso${NC}"
        else
            log_message "${RED}❌ Erro ao criar estrutura${NC}"
            return 1
        fi
    else
        log_message "${RED}❌ Arquivo 01-estrutura-completa.sql não encontrado${NC}"
        return 1
    fi
}

# Função para inserir dados
insert_data() {
    echo -e "${YELLOW}Inserindo dados de exemplo...${NC}"
    if [ -f "02-inserir-dados.sql" ]; then
        mysql -h$MYSQL_HOST -u$MYSQL_USER < 02-inserir-dados.sql
        if [ $? -eq 0 ]; then
            log_message "${GREEN}✅ Dados inseridos com sucesso${NC}"
        else
            log_message "${RED}❌ Erro ao inserir dados${NC}"
            return 1
        fi
    else
        log_message "${RED}❌ Arquivo 02-inserir-dados.sql não encontrado${NC}"
        return 1
    fi
}

# Verificar dependências
check_dependencies() {
    echo -e "${BLUE}Verificando dependências...${NC}"
    
    # Verificar MySQL
    if ! command -v mysql &> /dev/null; then
        log_message "${RED}❌ MySQL cliente não encontrado. Instale o MySQL.${NC}"
        return 1
    fi
    
    # Verificar bc para cálculos
    if ! command -v bc &> /dev/null; then
        log_message "${YELLOW}⚠️  Comando 'bc' não encontrado. Instale para cálculos de porcentagem.${NC}"
    fi
    
    # Testar conexão com MySQL
    if ! mysql -h$MYSQL_HOST -u$MYSQL_USER -e "SELECT 1;" &> /dev/null; then
        log_message "${RED}❌ Não foi possível conectar ao MySQL. Verifique as credenciais.${NC}"
        return 1
    fi
    
    log_message "${GREEN}✅ Todas as dependências estão OK${NC}"
    return 0
}

# Função principal
main() {
    # Limpar log anterior
    > "$TEST_LOG"
    
    # Verificar dependências
    if ! check_dependencies; then
        exit 1
    fi
    
    # Processar argumentos
    while [[ $# -gt 0 ]]; do
        case $1 in
            -h|--help)
                show_help
                exit 0
                ;;
            -c|--create)
                create_database
                shift
                ;;
            -d|--data)
                insert_data
                shift
                ;;
            -q|--quick)
                echo -e "${YELLOW}Modo rápido não implementado ainda. Executando validação completa.${NC}"
                shift
                ;;
            -v|--verbose)
                echo -e "${YELLOW}Modo verboso ativado.${NC}"
                shift
                ;;
            *)
                echo "Opção desconhecida: $1"
                show_help
                exit 1
                ;;
        esac
    done
    
    # Executar validação principal
    if main_validation; then
        exit 0
    else
        exit 1
    fi
}

# Executar script
main "$@"