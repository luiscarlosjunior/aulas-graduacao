"""
Herança em Python

Demonstra como usar herança para reutilizar código.

@author luiscaparroz
@version 1.0
"""


class Animal:
    """Classe base (superclasse)"""
    
    def __init__(self, nome, idade):
        self.nome = nome
        self.idade = idade
    
    def fazer_som(self):
        """Método que será sobrescrito nas subclasses"""
        print(f"{self.nome} faz um som...")
    
    def dormir(self):
        """Método comum a todos os animais"""
        print(f"{self.nome} está dormindo... 😴")


class Cachorro(Animal):
    """Subclasse que herda de Animal"""
    
    def __init__(self, nome, idade, raca):
        super().__init__(nome, idade)  # Chama construtor da superclasse
        self.raca = raca
    
    def fazer_som(self):
        """Sobrescreve o método da superclasse"""
        print(f"{self.nome} faz: Au au! 🐕")
    
    def abanar_rabo(self):
        """Método específico de Cachorro"""
        print(f"{self.nome} está abanando o rabo!")


class Gato(Animal):
    """Outra subclasse que herda de Animal"""
    
    def fazer_som(self):
        """Sobrescreve o método da superclasse"""
        print(f"{self.nome} faz: Miau! 🐱")
    
    def arranhar(self):
        """Método específico de Gato"""
        print(f"{self.nome} está arranhando!")


def main():
    """Demonstração de herança"""
    
    print("=== HERANÇA EM PYTHON ===\n")
    
    # Criar objetos
    cachorro = Cachorro("Rex", 5, "Labrador")
    gato = Gato("Mimi", 3)
    
    # Métodos herdados
    print("Métodos da classe base:")
    cachorro.fazer_som()
    gato.fazer_som()
    
    print()
    
    # Método comum (herdado)
    cachorro.dormir()
    gato.dormir()
    
    print()
    
    # Métodos específicos
    print("Métodos específicos:")
    cachorro.abanar_rabo()
    gato.arranhar()
    
    print()
    
    # Verificar herança
    print(f"cachorro é instância de Cachorro? {isinstance(cachorro, Cachorro)}")
    print(f"cachorro é instância de Animal? {isinstance(cachorro, Animal)}")
    print(f"gato é instância de Cachorro? {isinstance(gato, Cachorro)}")


if __name__ == "__main__":
    main()
