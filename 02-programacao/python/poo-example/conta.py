"""
Classe Conta - Demonstração de POO em Python
Representa uma conta bancária básica com operações de depósito e consulta de saldo.
"""


class Conta:
    """
    Classe que representa uma conta bancária.
    
    Atributos:
        agencia (str): Número da agência
        conta (str): Número da conta
        saldo (float): Saldo atual da conta
    """
    
    def __init__(self):
        """Construtor da classe Conta"""
        self._agencia = ""
        self._conta = ""
        self._saldo = 0.0
    
    # Getters
    def get_saldo(self):
        """Retorna o saldo atual da conta"""
        return self._saldo
    
    def get_agencia(self):
        """Retorna o número da agência"""
        return self._agencia
    
    def get_conta(self):
        """Retorna o número da conta"""
        return self._conta
    
    # Setters
    def set_agencia(self, agencia):
        """Define o número da agência"""
        self._agencia = agencia
    
    def set_conta(self, conta):
        """Define o número da conta"""
        self._conta = conta
    
    # Métodos de negócio
    def depositar(self, deposito):
        """
        Realiza um depósito na conta
        
        Args:
            deposito (float): Valor a ser depositado
        """
        self._saldo += deposito
        print(f"Seu saldo é {self._saldo}")
    
    def ver_saldo(self):
        """Retorna o saldo atual da conta"""
        return self._saldo