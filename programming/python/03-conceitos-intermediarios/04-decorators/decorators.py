"""
Decorators em Python

Demonstra como criar e usar decorators (equivalente a annotations do Java).

@author luiscaparroz
@version 1.0
"""

import time
from functools import wraps


# ==================== DECORATOR SIMPLES ====================

def meu_decorator(func):
    """Decorator simples que envolve uma função"""
    def wrapper():
        print("Antes da função")
        func()
        print("Depois da função")
    return wrapper


@meu_decorator
def funcao_simples():
    """Função decorada"""
    print("Executando função")


# ==================== DECORATOR COM PARÂMETROS ====================

def repetir(vezes):
    """Decorator que repete a execução de uma função"""
    def decorator(func):
        def wrapper(*args, **kwargs):
            for _ in range(vezes):
                func(*args, **kwargs)
        return wrapper
    return decorator


@repetir(3)
def saudar(nome):
    """Função que será executada 3 vezes"""
    print(f"Olá, {nome}!")


# ==================== DECORATOR ÚTIL: MEDIR TEMPO ====================

def medir_tempo(func):
    """Decorator que mede o tempo de execução"""
    @wraps(func)  # Preserva metadados da função original
    def wrapper(*args, **kwargs):
        inicio = time.time()
        resultado = func(*args, **kwargs)
        fim = time.time()
        print(f"Função {func.__name__} levou {fim - inicio:.4f}s")
        return resultado
    return wrapper


@medir_tempo
def processar_dados():
    """Simula processamento pesado"""
    time.sleep(0.1)
    return "Dados processados"


# ==================== DECORATOR DE CLASSE ====================

def adicionar_metodo(cls):
    """Decorator que adiciona método a uma classe"""
    def novo_metodo(self):
        return f"Método adicionado a {self.__class__.__name__}"
    cls.metodo_extra = novo_metodo
    return cls


@adicionar_metodo
class MinhaClasse:
    """Classe decorada"""
    pass


def main():
    """Demonstração de decorators"""
    
    print("=== DECORATORS EM PYTHON ===\n")
    
    print("1. DECORATOR SIMPLES:")
    funcao_simples()
    print()
    
    print("2. DECORATOR COM PARÂMETROS:")
    saudar("Python")
    print()
    
    print("3. DECORATOR PARA MEDIR TEMPO:")
    resultado = processar_dados()
    print(f"Resultado: {resultado}")
    print()
    
    print("4. DECORATOR DE CLASSE:")
    obj = MinhaClasse()
    print(obj.metodo_extra())
    print()
    
    print("=== COMPARAÇÃO COM JAVA ===")
    print("Java: @Override, @Deprecated (annotations)")
    print("Python: @decorator (modifica comportamento)")
    print("Java: Metadados em tempo de compilação")
    print("Python: Envolve função/classe em tempo de execução")


if __name__ == "__main__":
    main()
