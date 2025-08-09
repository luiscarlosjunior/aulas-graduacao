"""
Classe ContaPoupanca - Demonstração de Herança em Python
Representa uma conta poupança que herda de Conta.
"""

from conta import Conta


class ContaPoupanca(Conta):
    """
    Classe que representa uma conta poupança.
    Herda da classe Conta e adiciona funcionalidades específicas para poupança.
    
    Atributos:
        dia_deposito (float): Dia do depósito para cálculo de rendimento
    """
    
    def __init__(self):
        """Construtor da classe ContaPoupanca"""
        super().__init__()  # Chama o construtor da classe pai
        self._dia_deposito = 0.0
    
    # Getter e Setter para dia_deposito
    def get_dia_deposito(self):
        """Retorna o dia do depósito"""
        return self._dia_deposito
    
    def set_dia_deposito(self, dia_deposito):
        """Define o dia do depósito"""
        self._dia_deposito = dia_deposito
    
    # Método específico da conta poupança
    def ver_lucro(self):
        """
        Retorna o lucro atual da conta poupança
        (Por simplicidade, retorna o saldo atual)
        """
        return self.get_saldo()