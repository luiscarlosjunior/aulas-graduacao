"""
Interfaces em Python (Protocols)

Python não tem interfaces como Java, mas usa duck typing e Protocols (Python 3.8+).
Este exemplo demonstra diferentes formas de implementar "interfaces" em Python.

@author luiscaparroz
@version 1.0
"""

from typing import Protocol


# Forma 1: Duck Typing (jeito pythônico tradicional)
class Voador:
    """
    Qualquer classe que implemente os métodos de Voador
    pode ser considerada "Voador" (duck typing).
    """
    def decolar(self):
        raise NotImplementedError
    
    def pousar(self):
        raise NotImplementedError


class Aviao(Voador):
    """Implementa a 'interface' Voador"""
    
    def decolar(self):
        print("Avião decolando... ✈️")
    
    def pousar(self):
        print("Avião pousando... ✈️")


class Passaro(Voador):
    """Outra implementação de Voador"""
    
    def decolar(self):
        print("Pássaro decolando... 🦅")
    
    def pousar(self):
        print("Pássaro pousando... 🦅")


# Forma 2: Protocol (Python 3.8+) - tipo estático
class Nadador(Protocol):
    """
    Protocol define uma 'interface' que pode ser verificada estaticamente.
    Qualquer classe que implemente os métodos é considerada Nadador.
    """
    def nadar(self) -> None:
        ...


class Peixe:
    """Implementa o Protocol Nadador"""
    
    def nadar(self):
        print("Peixe nadando... 🐟")


class Pato:
    """Também implementa o Protocol Nadador"""
    
    def nadar(self):
        print("Pato nadando... 🦆")


def fazer_nadar(nadador: Nadador):
    """
    Função que aceita qualquer objeto que implemente o Protocol Nadador.
    
    Args:
        nadador: Qualquer objeto com método nadar()
    """
    nadador.nadar()


def fazer_voar(voador: Voador):
    """
    Função que trabalha com qualquer Voador.
    
    Args:
        voador: Qualquer objeto que implemente os métodos de Voador
    """
    voador.decolar()
    voador.pousar()


def main():
    """Demonstração de interfaces em Python"""
    
    print("=== INTERFACES EM PYTHON ===\n")
    
    print("1. DUCK TYPING (forma pythônica tradicional):")
    print("   'Se anda como pato e fala como pato, então é um pato'\n")
    
    aviao = Aviao()
    passaro = Passaro()
    
    fazer_voar(aviao)
    fazer_voar(passaro)
    
    print()
    
    print("2. PROTOCOL (Python 3.8+):")
    print("   Verifica estrutura em tempo de checagem de tipos\n")
    
    peixe = Peixe()
    pato = Pato()
    
    fazer_nadar(peixe)
    fazer_nadar(pato)
    
    print()
    
    print("3. DUCK TYPING NA PRÁTICA:")
    print("   Não importa o tipo, importa o comportamento\n")
    
    class Helicoptero:
        """Não herda de Voador, mas tem os métodos necessários"""
        def decolar(self):
            print("Helicóptero decolando... 🚁")
        
        def pousar(self):
            print("Helicóptero pousando... 🚁")
    
    helicoptero = Helicoptero()
    fazer_voar(helicoptero)  # Funciona mesmo sem herdar!
    
    print()
    
    print("=== DIFERENÇAS COM JAVA ===")
    print("1. Java: interface é palavra-chave obrigatória")
    print("   Python: usa duck typing ou Protocol")
    print("2. Java: classe deve declarar 'implements Interface'")
    print("   Python: basta implementar os métodos necessários")
    print("3. Java: verificação em tempo de compilação")
    print("   Python: verificação em tempo de execução (duck typing)")
    print("4. Python é mais flexível mas menos explícito")


if __name__ == "__main__":
    main()
