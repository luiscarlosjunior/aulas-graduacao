"""
Testes simples para validar os conceitos de POO
Este arquivo pode ser executado para verificar se as implementações estão corretas
"""

from conta import Conta
from conta_poupanca import ContaPoupanca
from cliente import Cliente


def teste_heranca():
    """Testa se ContaPoupanca herda corretamente de Conta"""
    print("=== Teste de Herança ===")
    
    # Instanciando ContaPoupanca
    cp = ContaPoupanca()
    
    # Testando se herda métodos de Conta
    cp.set_agencia("0001")
    cp.set_conta("123")
    cp.depositar(100)
    
    # Testando métodos específicos
    cp.set_dia_deposito(15)
    
    # Verificações
    assert cp.get_agencia() == "0001", "Erro: get_agencia() não funcionou"
    assert cp.get_conta() == "123", "Erro: get_conta() não funcionou"
    assert cp.get_saldo() == 100, "Erro: get_saldo() não funcionou"
    assert cp.get_dia_deposito() == 15, "Erro: get_dia_deposito() não funcionou"
    assert cp.ver_lucro() == 100, "Erro: ver_lucro() não funcionou"
    
    print("✓ Herança funcionando corretamente")
    print(f"  - ContaPoupanca herda de Conta: {isinstance(cp, Conta)}")
    print(f"  - ContaPoupanca é instância de ContaPoupanca: {isinstance(cp, ContaPoupanca)}")
    

def teste_associacao():
    """Testa se Cliente possui associação com ContaPoupanca"""
    print("\n=== Teste de Associação ===")
    
    # Criando cliente
    cliente = Cliente()
    cliente.nome = "João"
    cliente.cpf = "123.456.789-00"
    
    # Criando conta poupança (associação)
    conta = cliente.criar_conta_poupanca()
    conta.set_agencia("0001")
    conta.depositar(500)
    
    # Verificações
    assert cliente.conta_poupanca is not None, "Erro: conta_poupanca não foi criada"
    assert isinstance(cliente.conta_poupanca, ContaPoupanca), "Erro: tipo incorreto"
    assert cliente.ver_saldo(1234) == 500, "Erro: ver_saldo() não funcionou"
    
    print("✓ Associação funcionando corretamente")
    print(f"  - Cliente possui ContaPoupanca: {cliente.conta_poupanca is not None}")
    print(f"  - Tipo correto: {type(cliente.conta_poupanca).__name__}")


def teste_encapsulamento():
    """Testa se o encapsulamento está funcionando"""
    print("\n=== Teste de Encapsulamento ===")
    
    conta = Conta()
    
    # Testando acesso através de métodos
    conta.set_agencia("0001")
    conta.set_conta("123")
    
    # Verificando que não podemos acessar diretamente os atributos privados
    # (em Python, isto é convenção, não obrigatório)
    try:
        agencia_privada = conta._agencia  # Acesso direto (não recomendado)
        print(f"  - Atributo _agencia acessível (convenção Python): {agencia_privada}")
    except AttributeError:
        print("  - Atributo _agencia protegido")
    
    # Testando acesso através de métodos públicos
    assert conta.get_agencia() == "0001", "Erro: getter não funcionou"
    
    print("✓ Encapsulamento implementado (convenção Python)")
    print(f"  - Acesso via getter: {conta.get_agencia()}")


def teste_polimorfismo():
    """Testa polimorfismo básico"""
    print("\n=== Teste de Polimorfismo ===")
    
    # Criando lista com objetos de diferentes tipos
    contas = [Conta(), ContaPoupanca()]
    
    # Configurando ambas
    for i, conta in enumerate(contas):
        conta.set_agencia(f"000{i+1}")
        conta.depositar(100 * (i+1))
    
    # Testando método comum
    for conta in contas:
        saldo = conta.ver_saldo()
        tipo = type(conta).__name__
        print(f"  - {tipo}: saldo = {saldo}")
    
    print("✓ Polimorfismo funcionando")


def main():
    """Executa todos os testes"""
    print("Executando testes de POO...\n")
    
    try:
        teste_heranca()
        teste_associacao()
        teste_encapsulamento()
        teste_polimorfismo()
        
        print("\n🎉 Todos os testes passaram! POO implementado corretamente.")
        
    except AssertionError as e:
        print(f"\n❌ Teste falhou: {e}")
    except Exception as e:
        print(f"\n❌ Erro inesperado: {e}")


if __name__ == "__main__":
    main()