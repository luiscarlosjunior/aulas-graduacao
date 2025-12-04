"""
Programa Hello World - Primeiro programa em Python

Este é o programa mais simples que podemos escrever em Python.
Ele demonstra a estrutura básica de uma aplicação Python.

@author luiscaparroz
@version 1.0
"""

def main():
    """
    Função principal - Ponto de entrada da aplicação
    
    Esta função é chamada quando executamos o programa.
    Em Python, não é obrigatório ter uma função main, mas é uma boa prática.
    """
    # print() imprime uma mensagem no console
    # Por padrão, print() adiciona uma quebra de linha no final
    print("Hello, world")
    
    # Demonstração: podemos imprimir múltiplas linhas
    print("Bem-vindo ao mundo da programação Python!")
    print("Este é meu primeiro programa.")
    
    # Por padrão, print() sempre quebra linha
    # Para não quebrar, use o parâmetro end
    print("Esta mensagem não quebra linha... ", end="")
    print("Esta continua na mesma linha!")
    print()  # Quebra de linha vazia
    
    # Python também pode receber argumentos da linha de comando
    import sys
    if len(sys.argv) > 1:
        print("Você passou os seguintes argumentos:")
        for i, arg in enumerate(sys.argv[1:], 1):
            print(f"Argumento {i}: {arg}")
    else:
        print("Nenhum argumento foi passado pela linha de comando.")
        print("Tente executar: python3 hello_world.py seu_nome")


# Verifica se o script está sendo executado diretamente
# Esta é a forma pythônica de definir o ponto de entrada
if __name__ == "__main__":
    main()
