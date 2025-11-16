"""
Classes Abstratas em Python

Demonstra como usar classes abstratas com o módulo ABC (Abstract Base Classes).

@author luiscaparroz
@version 1.0
"""

from abc import ABC, abstractmethod


class Veiculo(ABC):
    """
    Classe abstrata - não pode ser instanciada diretamente.
    Define um contrato que as subclasses devem seguir.
    """
    
    def __init__(self, marca, modelo):
        self.marca = marca
        self.modelo = modelo
    
    @abstractmethod
    def acelerar(self):
        """Método abstrato - deve ser implementado nas subclasses"""
        pass
    
    @abstractmethod
    def frear(self):
        """Método abstrato - deve ser implementado nas subclasses"""
        pass
    
    def informacoes(self):
        """Método concreto - pode ser usado pelas subclasses"""
        return f"{self.marca} {self.modelo}"


class Carro(Veiculo):
    """Implementação concreta de Veiculo"""
    
    def acelerar(self):
        print(f"{self.informacoes()} acelerando... 🚗💨")
    
    def frear(self):
        print(f"{self.informacoes()} freando... 🚗🛑")


class Moto(Veiculo):
    """Outra implementação concreta de Veiculo"""
    
    def acelerar(self):
        print(f"{self.informacoes()} acelerando... 🏍️💨")
    
    def frear(self):
        print(f"{self.informacoes()} freando... 🏍️🛑")


def main():
    """Demonstração de classes abstratas"""
    
    print("=== CLASSES ABSTRATAS EM PYTHON ===\n")
    
    # Não podemos instanciar classe abstrata
    # veiculo = Veiculo("Marca", "Modelo")  # TypeError!
    
    # Criar instâncias das classes concretas
    carro = Carro("Toyota", "Corolla")
    moto = Moto("Honda", "CB500")
    
    # Usar métodos implementados
    print("Veículos em ação:\n")
    carro.acelerar()
    carro.frear()
    
    print()
    
    moto.acelerar()
    moto.frear()
    
    print()
    
    # Polimorfismo com classes abstratas
    veiculos = [carro, moto]
    print("Testando todos os veículos:\n")
    for veiculo in veiculos:
        print(f"Testando {veiculo.informacoes()}:")
        veiculo.acelerar()
        veiculo.frear()
        print()


if __name__ == "__main__":
    main()
