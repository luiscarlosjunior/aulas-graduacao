"""
Hello World Detalhado

Versão mais detalhada que explica cada parte do programa Hello World.

@author luiscaparroz
@version 1.0
"""

def main():
    """
    Função principal do programa.
    
    Em Python, não é obrigatório ter uma função main,
    mas é uma boa prática de organização.
    """
    
    # 1. Print básico
    print("=== HELLO WORLD DETALHADO ===\n")
    
    # 2. Print com múltiplas linhas
    print("Primeira linha")
    print("Segunda linha")
    print("Terceira linha")
    
    # 3. Print sem quebra de linha
    print("\n--- Print sem quebra de linha ---")
    print("Texto 1 ", end="")
    print("Texto 2 ", end="")
    print("Texto 3")
    
    # 4. Print com separador customizado
    print("\n--- Print com separador ---")
    print("A", "B", "C", sep="-")
    print("Python", "é", "incrível", sep=" >>> ")
    
    # 5. Print formatado
    print("\n--- Print formatado ---")
    nome = "Python"
    versao = 3.12
    print(f"Linguagem: {nome}")
    print(f"Versão: {versao}")
    print(f"{nome} versão {versao}")
    
    # 6. Print com caracteres especiais
    print("\n--- Caracteres especiais ---")
    print("Linha 1\nLinha 2")  # \n = nova linha
    print("Coluna1\tColuna2")  # \t = tabulação
    print("Aspas: \"Python\"")  # \" = aspas dentro de string
    
    # 7. Print com formatação de números
    print("\n--- Formatação de números ---")
    pi = 3.14159265359
    print(f"Pi com 2 casas: {pi:.2f}")
    print(f"Pi com 4 casas: {pi:.4f}")
    
    numero = 42
    print(f"Decimal: {numero}")
    print(f"Binário: {bin(numero)}")
    print(f"Hexadecimal: {hex(numero)}")
    
    # 8. Comentários
    # Comentário de linha única em Python usa #
    
    """
    Comentário de múltiplas linhas
    usa três aspas (simples ou duplas)
    """
    
    print("\n✓ Programa executado com sucesso!")


# Ponto de entrada do programa
# Esta linha verifica se o script está sendo executado diretamente
if __name__ == "__main__":
    main()
