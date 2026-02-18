"""
Collections Avançadas em Python

Demonstra as principais estruturas de dados em Python e suas operações.

@author luiscaparroz
@version 1.0
"""

from collections import defaultdict, Counter, deque


def main():
    """Demonstração de collections avançadas"""
    
    print("=== COLLECTIONS AVANÇADAS EM PYTHON ===\n")
    
    # ==================== LISTAS (list) ====================
    print("1. LISTAS (list) - Ordenadas e mutáveis:")
    lista = [1, 2, 3, 4, 5]
    print(f"   Lista: {lista}")
    print(f"   Métodos: append(), insert(), remove(), pop(), sort()")
    print(f"   Uso: quando precisa de ordem e modificação")
    print()
    
    # ==================== TUPLAS (tuple) ====================
    print("2. TUPLAS (tuple) - Ordenadas e imutáveis:")
    tupla = (1, 2, 3, 4, 5)
    print(f"   Tupla: {tupla}")
    print(f"   Imutável: não pode adicionar/remover elementos")
    print(f"   Uso: dados que não devem mudar, retorno múltiplo de funções")
    print()
    
    # ==================== DICIONÁRIOS (dict) ====================
    print("3. DICIONÁRIOS (dict) - Pares chave-valor:")
    dicionario = {"nome": "João", "idade": 25, "cidade": "SP"}
    print(f"   Dict: {dicionario}")
    print(f"   Acesso: dicionario['nome'] = '{dicionario['nome']}'")
    print(f"   Uso: mapear chaves para valores")
    print()
    
    # ==================== CONJUNTOS (set) ====================
    print("4. CONJUNTOS (set) - Elementos únicos, não ordenados:")
    conjunto = {1, 2, 3, 3, 2, 1}  # duplicatas removidas
    print(f"   Set: {conjunto}")
    print(f"   Operações: união, interseção, diferença")
    numeros_a = {1, 2, 3, 4}
    numeros_b = {3, 4, 5, 6}
    print(f"   {numeros_a} ∪ {numeros_b} = {numeros_a | numeros_b}")
    print(f"   {numeros_a} ∩ {numeros_b} = {numeros_a & numeros_b}")
    print()
    
    # ==================== DEFAULTDICT ====================
    print("5. DEFAULTDICT - Dict com valor padrão:")
    contagem = defaultdict(int)  # int() retorna 0
    palavras = ["python", "java", "python", "c++", "java", "python"]
    for palavra in palavras:
        contagem[palavra] += 1
    print(f"   Contagem de palavras: {dict(contagem)}")
    print()
    
    # ==================== COUNTER ====================
    print("6. COUNTER - Contador especializado:")
    contador = Counter(palavras)
    print(f"   Counter: {contador}")
    print(f"   Mais comum: {contador.most_common(2)}")
    print()
    
    # ==================== DEQUE ====================
    print("7. DEQUE - Fila de duas pontas (double-ended queue):")
    fila = deque([1, 2, 3])
    fila.append(4)      # Adiciona à direita
    fila.appendleft(0)  # Adiciona à esquerda
    print(f"   Deque: {fila}")
    fila.pop()          # Remove da direita
    fila.popleft()      # Remove da esquerda
    print(f"   Após pop: {fila}")
    print()
    
    # ==================== COMPREHENSIONS ====================
    print("8. COMPREHENSIONS - Criação eficiente de collections:")
    
    # List comprehension
    quadrados = [x**2 for x in range(5)]
    print(f"   List: {quadrados}")
    
    # Dict comprehension
    quadrados_dict = {x: x**2 for x in range(5)}
    print(f"   Dict: {quadrados_dict}")
    
    # Set comprehension
    pares = {x for x in range(10) if x % 2 == 0}
    print(f"   Set: {pares}")
    
    print()
    
    print("=== QUANDO USAR CADA UMA ===")
    print("• list: Dados ordenados que podem mudar")
    print("• tuple: Dados ordenados imutáveis")
    print("• dict: Mapear chaves para valores")
    print("• set: Elementos únicos, operações de conjunto")
    print("• defaultdict: Dict com valor padrão automático")
    print("• Counter: Contar ocorrências")
    print("• deque: Fila eficiente (adicionar/remover nas pontas)")


if __name__ == "__main__":
    main()
