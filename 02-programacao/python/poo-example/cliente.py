"""
Classe Cliente - Demonstração de Associação em Python
Representa um cliente bancário que possui uma conta poupança.
"""

from conta_poupanca import ContaPoupanca


class Cliente:
    """
    Classe que representa um cliente do banco.
    Demonstra associação com a classe ContaPoupanca.
    
    Atributos:
        cpf (str): CPF do cliente
        nome (str): Nome do cliente  
        telefone (str): Telefone do cliente
        endereco (str): Endereço do cliente
        conta_poupanca (ContaPoupanca): Conta poupança do cliente
    """
    
    def __init__(self):
        """Construtor da classe Cliente"""
        self.cpf = ""
        self.nome = ""
        self.telefone = ""
        self.endereco = ""
        self.conta_poupanca = None
    
    def criar_conta_poupanca(self):
        """Cria uma nova conta poupança para o cliente"""
        self.conta_poupanca = ContaPoupanca()
        return self.conta_poupanca
    
    def mostrar_cpf(self):
        """Retorna o CPF do cliente"""
        return self.cpf
    
    def ver_saldo(self, senha):
        """
        Permite ao cliente ver o saldo da sua conta poupança
        
        Args:
            senha (int): Senha para acesso (simulação de segurança)
            
        Returns:
            float: Saldo da conta se a senha for válida
        """
        if senha == 1234:  # Senha padrão para demonstração
            if self.conta_poupanca:
                return self.conta_poupanca.ver_saldo()
            else:
                print("Cliente não possui conta poupança")
                return 0.0
        else:
            print("Senha incorreta")
            return None
    
    def get_conta_poupanca(self):
        """Retorna a conta poupança do cliente"""
        return self.conta_poupanca