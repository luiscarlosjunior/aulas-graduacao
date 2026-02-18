"""
Encapsulamento em Python

Demonstra como usar encapsulamento (atributos privados e getters/setters).

@author luiscaparroz
@version 1.0
"""


class ContaBancaria:
    """
    Classe que demonstra encapsulamento.
    
    Em Python, usamos _ para indicar "protegido" e __ para "privado".
    """
    
    def __init__(self, titular, saldo_inicial=0):
        """
        Construtor
        
        Args:
            titular: Nome do titular
            saldo_inicial: Saldo inicial (padrão 0)
        """
        self.titular = titular  # Público
        self.__saldo = saldo_inicial  # Privado (com __)
    
    def depositar(self, valor):
        """Deposita um valor na conta"""
        if valor > 0:
            self.__saldo += valor
            print(f"Depósito de R${valor:.2f} realizado. Saldo: R${self.__saldo:.2f}")
        else:
            print("Valor inválido para depósito!")
    
    def sacar(self, valor):
        """Saca um valor da conta"""
        if valor <= 0:
            print("Valor inválido para saque!")
        elif valor > self.__saldo:
            print("Saldo insuficiente!")
        else:
            self.__saldo -= valor
            print(f"Saque de R${valor:.2f} realizado. Saldo: R${self.__saldo:.2f}")
    
    def get_saldo(self):
        """Getter para o saldo"""
        return self.__saldo
    
    def __str__(self):
        """Representação em string do objeto"""
        return f"Conta de {self.titular} - Saldo: R${self.__saldo:.2f}"


def main():
    """Demonstração de encapsulamento"""
    
    print("=== ENCAPSULAMENTO EM PYTHON ===\n")
    
    # Criar conta
    conta = ContaBancaria("João", 1000.0)
    print(f"Conta criada: {conta}")
    
    print()
    
    # Operações
    conta.depositar(500.0)
    conta.sacar(300.0)
    conta.sacar(2000.0)  # Saldo insuficiente
    
    print()
    
    # Acessar saldo através do getter
    print(f"Saldo atual: R${conta.get_saldo():.2f}")
    
    # Tentar acessar diretamente (não funcionará como esperado)
    # print(conta.__saldo)  # AttributeError
    
    print("\n✓ O saldo está encapsulado e protegido!")


if __name__ == "__main__":
    main()
