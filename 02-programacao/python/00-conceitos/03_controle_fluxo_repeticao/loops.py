"""
Demonstração de Controle de Fluxo - Repetição (Loops) em Python

Este programa demonstra como usar estruturas de repetição (for, while)
para automatizar tarefas repetitivas.

@author luiscaparroz
@version 1.0
"""

def main():
    """
    Função principal que demonstra o uso de estruturas de repetição
    """
    
    print("=== DEMONSTRAÇÃO DE LOOPS EM PYTHON ===\n")
    
    # ==================== FOR LOOP COM RANGE ====================
    
    print("1. FOR LOOP COM RANGE (equivalente ao for do Java):")
    print("Contando de 0 a 4:")
    for i in range(5):  # range(5) gera: 0, 1, 2, 3, 4
        print(f"  i = {i}")
    
    print("\nContando de 1 a 5:")
    for i in range(1, 6):  # range(1, 6) gera: 1, 2, 3, 4, 5
        print(f"  i = {i}")
    
    print("\nContando de 0 a 10 de 2 em 2:")
    for i in range(0, 11, 2):  # range(início, fim, passo)
        print(f"  i = {i}")
    
    print()
    
    # ==================== FOR LOOP EM LISTAS ====================
    
    print("2. FOR LOOP EM LISTAS (for-each):")
    frutas = ["maçã", "banana", "laranja", "uva"]
    print(f"Lista de frutas: {frutas}\n")
    
    for fruta in frutas:
        print(f"  Fruta: {fruta}")
    
    print()
    
    # ==================== FOR COM ENUMERATE ====================
    
    print("3. FOR COM ENUMERATE (índice e valor):")
    linguagens = ["Python", "Java", "JavaScript", "C++"]
    
    for indice, linguagem in enumerate(linguagens):
        print(f"  {indice + 1}. {linguagem}")
    
    print()
    
    # ==================== WHILE LOOP ====================
    
    print("4. WHILE LOOP (repete enquanto condição for True):")
    contador = 0
    print("Contando até 5 com while:")
    
    while contador < 5:
        print(f"  contador = {contador}")
        contador += 1  # Incrementa o contador
    
    print()
    
    # ==================== BREAK ====================
    
    print("5. COMANDO BREAK (sair do loop):")
    print("Procurando o número 3:")
    
    for numero in range(10):
        print(f"  Verificando: {numero}")
        if numero == 3:
            print("  Número 3 encontrado! Saindo do loop...")
            break
    
    print()
    
    # ==================== CONTINUE ====================
    
    print("6. COMANDO CONTINUE (pular para próxima iteração):")
    print("Imprimindo apenas números ímpares de 0 a 9:")
    
    for numero in range(10):
        if numero % 2 == 0:  # Se for par
            continue  # Pula para próxima iteração
        print(f"  {numero}")
    
    print()
    
    # ==================== LOOP ANINHADO ====================
    
    print("7. LOOPS ANINHADOS (loop dentro de loop):")
    print("Tabuada de 1 a 3:")
    
    for i in range(1, 4):
        print(f"\nTabuada do {i}:")
        for j in range(1, 6):
            resultado = i * j
            print(f"  {i} x {j} = {resultado}")
    
    print()
    
    # ==================== FOR EM STRINGS ====================
    
    print("8. FOR EM STRINGS (percorrer caracteres):")
    palavra = "Python"
    print(f"Palavra: {palavra}")
    print("Caracteres:")
    
    for letra in palavra:
        print(f"  {letra}")
    
    print()
    
    # ==================== FOR EM DICIONÁRIOS ====================
    
    print("9. FOR EM DICIONÁRIOS:")
    pessoa = {
        "nome": "João",
        "idade": 25,
        "cidade": "São Paulo"
    }
    
    print("Percorrendo chaves:")
    for chave in pessoa.keys():
        print(f"  {chave}")
    
    print("\nPercorrendo valores:")
    for valor in pessoa.values():
        print(f"  {valor}")
    
    print("\nPercorrendo chaves e valores:")
    for chave, valor in pessoa.items():
        print(f"  {chave}: {valor}")
    
    print()
    
    # ==================== LIST COMPREHENSION ====================
    
    print("10. LIST COMPREHENSION (forma pythônica de criar listas):")
    
    # Forma tradicional
    quadrados_tradicional = []
    for i in range(5):
        quadrados_tradicional.append(i ** 2)
    print(f"Forma tradicional: {quadrados_tradicional}")
    
    # List comprehension (forma pythônica)
    quadrados_comprehension = [i ** 2 for i in range(5)]
    print(f"List comprehension: {quadrados_comprehension}")
    
    # Com condição
    pares = [i for i in range(10) if i % 2 == 0]
    print(f"Números pares de 0 a 9: {pares}")
    
    print()
    
    # ==================== ELSE EM LOOPS ====================
    
    print("11. ELSE EM LOOPS (recurso único do Python):")
    print("O else é executado se o loop completar normalmente (sem break)")
    
    print("\nLoop com break:")
    for i in range(5):
        if i == 3:
            print(f"  Encontrei o {i}, saindo com break")
            break
    else:
        print("  Este else NÃO será executado (teve break)")
    
    print("\nLoop sem break:")
    for i in range(3):
        print(f"  i = {i}")
    else:
        print("  Este else SERÁ executado (não teve break)")
    
    print()
    
    # ==================== LOOP INFINITO ====================
    
    print("12. LOOP INFINITO (cuidado!):")
    print("Exemplo controlado de loop infinito:")
    
    contador_infinito = 0
    while True:  # Loop infinito
        print(f"  Iteração {contador_infinito}")
        contador_infinito += 1
        
        if contador_infinito >= 3:
            print("  Saindo do loop infinito com break")
            break
    
    print()
    
    # ==================== EXEMPLOS PRÁTICOS ====================
    
    print("13. EXEMPLOS PRÁTICOS:")
    
    # Somar números de uma lista
    numeros = [1, 2, 3, 4, 5]
    soma = 0
    for numero in numeros:
        soma += numero
    print(f"Soma dos números {numeros}: {soma}")
    
    # Encontrar o maior número
    numeros = [23, 45, 12, 67, 34]
    maior = numeros[0]
    for numero in numeros:
        if numero > maior:
            maior = numero
    print(f"Maior número em {numeros}: {maior}")
    
    # Contar vogais em uma string
    texto = "Python é incrível"
    vogais = "aeiouAEIOU"
    contador_vogais = 0
    for letra in texto:
        if letra in vogais:
            contador_vogais += 1
    print(f"Número de vogais em '{texto}': {contador_vogais}")
    
    print()
    
    # ==================== DICAS IMPORTANTES ====================
    
    print("=== DICAS IMPORTANTES ===")
    print("1. Use 'for' quando souber quantas iterações fazer")
    print("2. Use 'while' quando não souber quantas iterações serão necessárias")
    print("3. range(n) gera números de 0 até n-1")
    print("4. range(a, b) gera números de a até b-1")
    print("5. range(a, b, step) gera números de a até b-1 com incremento step")
    print("6. Use 'break' para sair do loop prematuramente")
    print("7. Use 'continue' para pular para a próxima iteração")
    print("8. Use enumerate() para obter índice e valor ao mesmo tempo")
    print("9. List comprehension é mais eficiente e pythônico")
    print("10. Cuidado com loops infinitos! Sempre tenha uma condição de saída")
    print("11. O 'else' em loops é executado se não houver break")
    print("12. Use 'in' para iterar diretamente sobre elementos (mais pythônico)")


if __name__ == "__main__":
    main()
