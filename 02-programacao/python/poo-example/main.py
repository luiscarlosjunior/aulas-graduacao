"""
Demonstração de POO em Python
Script principal que demonstra os conceitos de:
- Herança (ContaPoupanca herda de Conta)
- Associação (Cliente possui ContaPoupanca)
- Encapsulamento (atributos privados com getters/setters)
"""

from cliente import Cliente
from conta_poupanca import ContaPoupanca


def main():
    """Função principal que demonstra o uso das classes"""
    
    print("=== Demonstração de POO em Python ===\n")
    
    # Criando clientes
    print("1. Criando clientes:")
    luis = Cliente()
    lais = Cliente()
    roberta = Cliente()
    
    # Configurando dados dos clientes
    luis.cpf = "111.111.111-11"
    luis.nome = "Luis"
    luis.telefone = "(11) 1111-1111"
    luis.endereco = "Rua A, 123"
    
    lais.cpf = "222.222.222-22"
    lais.nome = "Lais"
    lais.telefone = "(11) 2222-2222"
    lais.endereco = "Rua B, 456"
    
    roberta.cpf = "333.333.333-33"
    roberta.nome = "Roberta"
    roberta.telefone = "(11) 3333-3333"
    roberta.endereco = "Rua C, 789"
    
    print(f"Cliente 1: {luis.nome} - CPF: {luis.cpf}")
    print(f"Cliente 2: {lais.nome} - CPF: {lais.cpf}")
    print(f"Cliente 3: {roberta.nome} - CPF: {roberta.cpf}")
    
    # Demonstrando ASSOCIAÇÃO: Cliente possui ContaPoupanca
    print("\n2. Criando contas poupança (Associação):")
    
    # Luis
    conta_luis = luis.criar_conta_poupanca()
    conta_luis.set_agencia("0001")
    conta_luis.set_conta("123")
    conta_luis.set_dia_deposito(1)
    
    # Lais
    conta_lais = lais.criar_conta_poupanca()
    conta_lais.set_agencia("0001")
    conta_lais.set_conta("1234")
    conta_lais.set_dia_deposito(1)
    
    # Roberta
    conta_roberta = roberta.criar_conta_poupanca()
    conta_roberta.set_agencia("0001")
    conta_roberta.set_conta("1235")
    conta_roberta.set_dia_deposito(1)
    
    print(f"Conta de {luis.nome}: Agência {conta_luis.get_agencia()}, Conta {conta_luis.get_conta()}")
    print(f"Conta de {lais.nome}: Agência {conta_lais.get_agencia()}, Conta {conta_lais.get_conta()}")
    print(f"Conta de {roberta.nome}: Agência {conta_roberta.get_agencia()}, Conta {conta_roberta.get_conta()}")
    
    # Demonstrando HERANÇA: ContaPoupanca herda métodos de Conta
    print("\n3. Realizando depósitos (Herança - métodos herdados):")
    conta_luis.depositar(500.0)
    conta_lais.depositar(1500.0)
    conta_roberta.depositar(865.0)
    
    # Demonstrando ENCAPSULAMENTO: Acesso através de métodos
    print("\n4. Verificando saldos (Encapsulamento):")
    print(f"Saldo de {luis.nome}: R$ {luis.ver_saldo(1234)}")
    print(f"Saldo de {lais.nome}: R$ {lais.ver_saldo(1234)}")
    print(f"Saldo de {roberta.nome}: R$ {roberta.ver_saldo(1234)}")
    
    # Demonstrando método específico da ContaPoupanca
    print("\n5. Verificando lucros (Método específico de ContaPoupanca):")
    print(f"Lucro de {luis.nome}: R$ {conta_luis.ver_lucro()}")
    print(f"Lucro de {lais.nome}: R$ {conta_lais.ver_lucro()}")
    print(f"Lucro de {roberta.nome}: R$ {conta_roberta.ver_lucro()}")
    
    # Demonstrando segurança com senha incorreta
    print("\n6. Testando segurança (senha incorreta):")
    print(f"Tentativa de acesso com senha incorreta: {luis.ver_saldo(9999)}")
    
    print("\n=== Demonstração concluída ===")


if __name__ == "__main__":
    main()