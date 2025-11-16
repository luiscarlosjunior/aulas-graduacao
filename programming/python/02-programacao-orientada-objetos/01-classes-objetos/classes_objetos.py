"""
Classes e Objetos em Python

Demonstra como criar e usar classes e objetos em Python.

@author luiscaparroz
@version 1.0
"""


class Pessoa:
    """
    Classe que representa uma pessoa.
    
    Em Python, usamos 'class' para definir uma classe.
    """
    
    def __init__(self, nome, idade):
        """
        Construtor da classe (__init__ em Python).
        
        Args:
            nome: Nome da pessoa
            idade: Idade da pessoa
        """
        self.nome = nome  # Atributo público
        self.idade = idade
    
    def apresentar(self):
        """Método que apresenta a pessoa"""
        print(f"Olá! Meu nome é {self.nome} e tenho {self.idade} anos.")
    
    def fazer_aniversario(self):
        """Incrementa a idade"""
        self.idade += 1
        print(f"{self.nome} fez aniversário! Agora tem {self.idade} anos.")


def main():
    """Demonstração de classes e objetos"""
    
    print("=== CLASSES E OBJETOS EM PYTHON ===\n")
    
    # Criando objetos (instâncias da classe)
    pessoa1 = Pessoa("João", 25)
    pessoa2 = Pessoa("Maria", 30)
    
    # Acessando atributos
    print(f"Pessoa 1: {pessoa1.nome}, {pessoa1.idade} anos")
    print(f"Pessoa 2: {pessoa2.nome}, {pessoa2.idade} anos")
    
    print()
    
    # Chamando métodos
    pessoa1.apresentar()
    pessoa2.apresentar()
    
    print()
    
    # Modificando atributos
    pessoa1.fazer_aniversario()
    
    print()
    
    # Comparação de objetos
    print(f"pessoa1 == pessoa2: {pessoa1 == pessoa2}")
    print(f"pessoa1 is pessoa2: {pessoa1 is pessoa2}")


if __name__ == "__main__":
    main()
