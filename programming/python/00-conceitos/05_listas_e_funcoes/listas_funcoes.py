"""
Demonstração de Listas e Funções em Python

Este programa demonstra como trabalhar com listas (equivalente a arrays/ArrayLists do Java)
e como criar e usar funções para organizar o código.

@author luiscaparroz
@version 1.0
"""

# ==================== FUNÇÕES ====================

def saudacao_simples():
    """Função simples sem parâmetros e sem retorno"""
    print("Olá! Bem-vindo ao Python!")


def saudacao_com_nome(nome):
    """Função com parâmetro"""
    print(f"Olá, {nome}! Bem-vindo ao Python!")


def somar(a, b):
    """Função que retorna um valor"""
    resultado = a + b
    return resultado


def calcular_operacoes(x, y):
    """Função que retorna múltiplos valores (tuple)"""
    soma = x + y
    subtracao = x - y
    multiplicacao = x * y
    divisao = x / y if y != 0 else 0
    return soma, subtracao, multiplicacao, divisao


def saudacao_com_padrao(nome="Visitante", idioma="pt"):
    """Função com parâmetros com valores padrão"""
    if idioma == "pt":
        return f"Olá, {nome}!"
    elif idioma == "en":
        return f"Hello, {nome}!"
    else:
        return f"Hi, {nome}!"


def calcular_media(*notas):
    """Função com número variável de argumentos (*args)"""
    if len(notas) == 0:
        return 0
    return sum(notas) / len(notas)


def exibir_dados_pessoa(**dados):
    """Função com argumentos nomeados variáveis (**kwargs)"""
    print("Dados da pessoa:")
    for chave, valor in dados.items():
        print(f"  {chave}: {valor}")


def processar_lista(lista):
    """Função que processa uma lista"""
    if not lista:
        return "Lista vazia"
    
    tamanho = len(lista)
    primeiro = lista[0]
    ultimo = lista[-1]
    soma = sum(lista) if all(isinstance(x, (int, float)) for x in lista) else None
    
    return {
        "tamanho": tamanho,
        "primeiro": primeiro,
        "ultimo": ultimo,
        "soma": soma
    }


# ==================== FUNÇÃO PRINCIPAL ====================

def main():
    """Função principal que demonstra o uso de listas e funções"""
    
    print("=== DEMONSTRAÇÃO DE LISTAS E FUNÇÕES EM PYTHON ===\n")
    
    # ==================== FUNÇÕES BÁSICAS ====================
    
    print("1. FUNÇÕES BÁSICAS:")
    saudacao_simples()
    saudacao_com_nome("João")
    
    resultado_soma = somar(10, 5)
    print(f"10 + 5 = {resultado_soma}")
    
    print()
    
    # ==================== FUNÇÕES COM MÚLTIPLOS RETORNOS ====================
    
    print("2. FUNÇÕES COM MÚLTIPLOS RETORNOS:")
    s, sub, mult, div = calcular_operacoes(10, 3)
    print(f"Operações com 10 e 3:")
    print(f"  Soma: {s}")
    print(f"  Subtração: {sub}")
    print(f"  Multiplicação: {mult}")
    print(f"  Divisão: {div:.2f}")
    
    print()
    
    # ==================== FUNÇÕES COM VALORES PADRÃO ====================
    
    print("3. FUNÇÕES COM VALORES PADRÃO:")
    print(saudacao_com_padrao())  # Usa valores padrão
    print(saudacao_com_padrao("Maria"))  # Só altera o nome
    print(saudacao_com_padrao("John", "en"))  # Altera ambos
    print(saudacao_com_padrao(idioma="en", nome="Peter"))  # Argumentos nomeados
    
    print()
    
    # ==================== FUNÇÕES COM *ARGS ====================
    
    print("4. FUNÇÕES COM *ARGS (argumentos variáveis):")
    print(f"Média de 7, 8, 9: {calcular_media(7, 8, 9):.2f}")
    print(f"Média de 5, 6: {calcular_media(5, 6):.2f}")
    print(f"Média de 10, 9, 8, 7, 6: {calcular_media(10, 9, 8, 7, 6):.2f}")
    
    print()
    
    # ==================== FUNÇÕES COM **KWARGS ====================
    
    print("5. FUNÇÕES COM **KWARGS (argumentos nomeados variáveis):")
    exibir_dados_pessoa(nome="João", idade=25, cidade="São Paulo")
    print()
    exibir_dados_pessoa(nome="Maria", profissao="Engenheira", salario=5000)
    
    print()
    
    # ==================== LISTAS BÁSICAS ====================
    
    print("6. LISTAS BÁSICAS:")
    
    # Criando listas
    numeros = [1, 2, 3, 4, 5]
    frutas = ["maçã", "banana", "laranja"]
    misturado = [1, "texto", 3.14, True]
    
    print(f"Lista de números: {numeros}")
    print(f"Lista de frutas: {frutas}")
    print(f"Lista mista: {misturado}")
    
    print()
    
    # ==================== ACESSANDO ELEMENTOS ====================
    
    print("7. ACESSANDO ELEMENTOS DA LISTA:")
    print(f"Primeira fruta: {frutas[0]}")
    print(f"Última fruta: {frutas[-1]}")
    print(f"Segunda fruta: {frutas[1]}")
    
    print()
    
    # ==================== SLICING (FATIAMENTO) ====================
    
    print("8. SLICING (fatiamento de listas):")
    numeros = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    print(f"Lista completa: {numeros}")
    print(f"Primeiros 3 elementos: {numeros[:3]}")
    print(f"Últimos 3 elementos: {numeros[-3:]}")
    print(f"Do índice 2 ao 5: {numeros[2:6]}")
    print(f"A cada 2 elementos: {numeros[::2]}")
    print(f"Lista invertida: {numeros[::-1]}")
    
    print()
    
    # ==================== MODIFICANDO LISTAS ====================
    
    print("9. MODIFICANDO LISTAS:")
    frutas = ["maçã", "banana"]
    print(f"Lista inicial: {frutas}")
    
    # Adicionar elemento no final
    frutas.append("laranja")
    print(f"Após append('laranja'): {frutas}")
    
    # Inserir em posição específica
    frutas.insert(1, "uva")
    print(f"Após insert(1, 'uva'): {frutas}")
    
    # Remover elemento específico
    frutas.remove("banana")
    print(f"Após remove('banana'): {frutas}")
    
    # Remover por índice
    removido = frutas.pop(0)
    print(f"Após pop(0), removido '{removido}': {frutas}")
    
    # Limpar lista
    frutas_copia = frutas.copy()
    frutas.clear()
    print(f"Após clear(): {frutas}")
    print(f"Cópia preservada: {frutas_copia}")
    
    print()
    
    # ==================== OPERAÇÕES COM LISTAS ====================
    
    print("10. OPERAÇÕES COM LISTAS:")
    lista1 = [1, 2, 3]
    lista2 = [4, 5, 6]
    
    # Concatenação
    lista_concatenada = lista1 + lista2
    print(f"{lista1} + {lista2} = {lista_concatenada}")
    
    # Repetição
    lista_repetida = lista1 * 3
    print(f"{lista1} * 3 = {lista_repetida}")
    
    # Verificar se elemento está na lista
    print(f"2 está em {lista1}? {2 in lista1}")
    print(f"10 está em {lista1}? {10 in lista1}")
    
    # Tamanho da lista
    print(f"Tamanho de {lista1}: {len(lista1)}")
    
    print()
    
    # ==================== MÉTODOS ÚTEIS DE LISTAS ====================
    
    print("11. MÉTODOS ÚTEIS DE LISTAS:")
    numeros = [3, 1, 4, 1, 5, 9, 2, 6]
    print(f"Lista original: {numeros}")
    
    # Ordenar
    numeros_ordenados = sorted(numeros)  # Retorna nova lista
    print(f"sorted(): {numeros_ordenados}")
    
    numeros_copia = numeros.copy()
    numeros_copia.sort()  # Modifica a lista
    print(f"sort(): {numeros_copia}")
    
    # Reverter
    numeros_reversos = list(reversed(numeros))
    print(f"reversed(): {numeros_reversos}")
    
    # Contar ocorrências
    print(f"Quantas vezes 1 aparece: {numeros.count(1)}")
    
    # Encontrar índice
    print(f"Índice do número 5: {numeros.index(5)}")
    
    # Máximo e mínimo
    print(f"Máximo: {max(numeros)}")
    print(f"Mínimo: {min(numeros)}")
    print(f"Soma: {sum(numeros)}")
    
    print()
    
    # ==================== LISTAS ANINHADAS (MATRIZES) ====================
    
    print("12. LISTAS ANINHADAS (equivalente a arrays 2D):")
    matriz = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9]
    ]
    
    print("Matriz 3x3:")
    for linha in matriz:
        print(f"  {linha}")
    
    print(f"\nElemento [0][0]: {matriz[0][0]}")
    print(f"Elemento [1][2]: {matriz[1][2]}")
    print(f"Elemento [2][1]: {matriz[2][1]}")
    
    print()
    
    # ==================== USANDO FUNÇÕES COM LISTAS ====================
    
    print("13. USANDO FUNÇÕES COM LISTAS:")
    lista_teste = [10, 20, 30, 40, 50]
    info = processar_lista(lista_teste)
    print(f"Processando {lista_teste}:")
    for chave, valor in info.items():
        print(f"  {chave}: {valor}")
    
    print()
    
    # ==================== LIST COMPREHENSION ====================
    
    print("14. LIST COMPREHENSION (forma pythônica):")
    
    # Criar lista de quadrados
    quadrados = [x**2 for x in range(1, 6)]
    print(f"Quadrados de 1 a 5: {quadrados}")
    
    # Filtrar números pares
    numeros = range(1, 11)
    pares = [x for x in numeros if x % 2 == 0]
    print(f"Números pares de 1 a 10: {pares}")
    
    # Transformar strings
    nomes = ["joão", "maria", "pedro"]
    nomes_maiusculos = [nome.upper() for nome in nomes]
    print(f"Nomes em maiúsculas: {nomes_maiusculos}")
    
    print()
    
    # ==================== LAMBDA FUNCTIONS ====================
    
    print("15. LAMBDA FUNCTIONS (funções anônimas):")
    
    # Função lambda simples
    dobro = lambda x: x * 2
    print(f"Dobro de 5: {dobro(5)}")
    
    # Usar com map()
    numeros = [1, 2, 3, 4, 5]
    dobrados = list(map(lambda x: x * 2, numeros))
    print(f"Números dobrados: {dobrados}")
    
    # Usar com filter()
    numeros = range(1, 11)
    pares = list(filter(lambda x: x % 2 == 0, numeros))
    print(f"Números pares: {pares}")
    
    # Usar com sorted()
    palavras = ["banana", "maçã", "uva", "laranja"]
    ordenadas_por_tamanho = sorted(palavras, key=lambda x: len(x))
    print(f"Palavras ordenadas por tamanho: {ordenadas_por_tamanho}")
    
    print()
    
    # ==================== DICAS IMPORTANTES ====================
    
    print("=== DICAS IMPORTANTES ===")
    print("1. Listas são mutáveis (podem ser modificadas)")
    print("2. Use [] para criar listas vazias")
    print("3. Índices começam em 0")
    print("4. Índices negativos contam do final: -1 é o último")
    print("5. Use slicing para obter sublistas: lista[início:fim]")
    print("6. append() adiciona no final, insert() em posição específica")
    print("7. remove() remove por valor, pop() remove por índice")
    print("8. Use 'in' para verificar se elemento está na lista")
    print("9. List comprehension é mais pythônico e eficiente")
    print("10. Use funções para organizar e reutilizar código")
    print("11. *args permite número variável de argumentos")
    print("12. **kwargs permite argumentos nomeados variáveis")
    print("13. Funções podem retornar múltiplos valores (tuple)")
    print("14. Lambda é útil para funções pequenas e descartáveis")


if __name__ == "__main__":
    main()
