"""
Polimorfismo em Python

Demonstra como usar polimorfismo - mesma interface, comportamentos diferentes.

@author luiscaparroz
@version 1.0
"""


class Forma:
    """Classe base para formas geométricas"""
    
    def calcular_area(self):
        """Método que será sobrescrito"""
        pass
    
    def calcular_perimetro(self):
        """Método que será sobrescrito"""
        pass


class Retangulo(Forma):
    """Retângulo"""
    
    def __init__(self, largura, altura):
        self.largura = largura
        self.altura = altura
    
    def calcular_area(self):
        return self.largura * self.altura
    
    def calcular_perimetro(self):
        return 2 * (self.largura + self.altura)


class Circulo(Forma):
    """Círculo"""
    
    def __init__(self, raio):
        self.raio = raio
    
    def calcular_area(self):
        return 3.14159 * self.raio ** 2
    
    def calcular_perimetro(self):
        return 2 * 3.14159 * self.raio


def imprimir_info_forma(forma):
    """
    Função polimórfica - funciona com qualquer Forma.
    
    Args:
        forma: Qualquer objeto que herde de Forma
    """
    print(f"  Tipo: {type(forma).__name__}")
    print(f"  Área: {forma.calcular_area():.2f}")
    print(f"  Perímetro: {forma.calcular_perimetro():.2f}")


def main():
    """Demonstração de polimorfismo"""
    
    print("=== POLIMORFISMO EM PYTHON ===\n")
    
    # Criar diferentes formas
    retangulo = Retangulo(5, 3)
    circulo = Circulo(4)
    
    # Lista de formas diferentes
    formas = [retangulo, circulo]
    
    # Polimorfismo: mesma função funciona com diferentes tipos
    print("Informações das formas:\n")
    for i, forma in enumerate(formas, 1):
        print(f"Forma {i}:")
        imprimir_info_forma(forma)
        print()


if __name__ == "__main__":
    main()
