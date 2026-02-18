"""
Hello World Interativo

Programa interativo que pede o nome do usuário e exibe uma saudação.

@author luiscaparroz
@version 1.0
"""

def main():
    """
    Função principal - programa interativo
    """
    
    print("=== HELLO WORLD INTERATIVO ===\n")
    
    # Solicitar entrada do usuário
    nome = input("Digite seu nome: ")
    
    # Exibir saudação personalizada
    print(f"\nOlá, {nome}! Bem-vindo ao Python!")
    
    # Solicitar mais informações
    idade = input("Digite sua idade: ")
    cidade = input("Digite sua cidade: ")
    
    # Exibir informações
    print("\n--- Suas Informações ---")
    print(f"Nome: {nome}")
    print(f"Idade: {idade} anos")
    print(f"Cidade: {cidade}")
    
    # Mensagem final
    print(f"\nObrigado por usar nosso programa, {nome}!")


if __name__ == "__main__":
    main()
