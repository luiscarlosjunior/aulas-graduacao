"""
Demonstração dos Tipos de Dados em Python

Este programa demonstra como declarar, inicializar e usar os diferentes
tipos de dados disponíveis em Python, além de algumas operações básicas.

Tipos principais em Python: int, float, str, bool, list, tuple, dict, set

@author luiscaparroz
@version 2.0
"""

def main():
    """
    Função principal que demonstra o uso de diferentes tipos de dados
    """
    
    print("=== DEMONSTRAÇÃO DOS TIPOS DE DADOS EM PYTHON ===\n")
    
    # ==================== TIPOS NUMÉRICOS INTEIROS ====================
    
    # int: números inteiros de tamanho arbitrário em Python
    # Python não tem limitação de tamanho como byte, short, int, long do Java
    dias_da_semana = 7
    print("TIPO INT (inteiro):")
    print(f"Dias da semana (int): {dias_da_semana}")
    print("Observação: Python suporta inteiros de tamanho arbitrário!\n")
    
    ano_atual = 2024
    print(f"Ano atual (int): {ano_atual}")
    
    populacao_brasil = 215000000  # aproximadamente 215 milhões
    print(f"População do Brasil (int): {populacao_brasil}")
    
    # Python suporta números muito grandes sem problemas
    distancia_lua_km = 384400
    print(f"Distância até a Lua em km (int): {distancia_lua_km}")
    print(f"Tipo da variável: {type(distancia_lua_km)}\n")
    
    # ==================== TIPOS NUMÉRICOS DECIMAIS ====================
    
    # float: números de ponto flutuante (equivalente a double do Java)
    altura = 1.85
    print("TIPO FLOAT (ponto flutuante):")
    print(f"Minha altura (float): {altura}m")
    
    numero_pi = 3.141592653589793
    print(f"Número PI (float): {numero_pi}")
    print(f"Tipo da variável: {type(numero_pi)}\n")
    
    # Python também suporta notação científica
    velocidade_luz = 3.0e8  # 3.0 x 10^8 m/s
    print(f"Velocidade da luz (notação científica): {velocidade_luz} m/s\n")
    
    # ==================== TIPO STRING ====================
    
    # str: sequência de caracteres (imutável)
    meu_nome = "Luis Carlos"
    saudacao = "Olá, mundo da programação!"
    print("TIPO STR (string):")
    print(f"Meu nome (str): {meu_nome}")
    print(f"Saudação (str): {saudacao}")
    
    # Strings podem usar aspas simples ou duplas
    mensagem1 = 'Aspas simples também funcionam'
    mensagem2 = "Aspas duplas são equivalentes"
    print(f"Mensagem 1: {mensagem1}")
    print(f"Mensagem 2: {mensagem2}")
    
    # String multilinha com aspas triplas
    texto_longo = """
    Esta é uma string
    que ocupa múltiplas
    linhas!
    """
    print(f"Texto multilinha:{texto_longo}")
    
    # Caractere individual é apenas uma string de tamanho 1
    primeira_letra = 'D'
    simbolo_especial = '@'
    print(f"Primeira letra do meu nome (str): {primeira_letra}")
    print(f"Símbolo especial (str): {simbolo_especial}")
    print(f"Tipo da variável: {type(primeira_letra)}\n")
    
    # ==================== TIPO LÓGICO ====================
    
    # bool: representa valores True ou False
    sou_pessoa = True
    tenho_carteira_motorista = False
    print("TIPO BOOL (booleano):")
    print(f"Eu sou uma pessoa? {sou_pessoa}")
    print(f"Tenho carteira de motorista? {tenho_carteira_motorista}")
    print(f"Tipo da variável: {type(sou_pessoa)}\n")
    
    # ==================== TIPOS DE COLEÇÕES ====================
    
    # list: lista ordenada e mutável (equivalente a ArrayList do Java)
    numeros = [1, 2, 3, 4, 5]
    frutas = ["maçã", "banana", "laranja"]
    print("TIPO LIST (lista):")
    print(f"Lista de números: {numeros}")
    print(f"Lista de frutas: {frutas}")
    print(f"Tipo da variável: {type(numeros)}\n")
    
    # tuple: tupla ordenada e imutável
    coordenadas = (10, 20)
    cores_primarias = ("vermelho", "azul", "amarelo")
    print("TIPO TUPLE (tupla):")
    print(f"Coordenadas (tuple): {coordenadas}")
    print(f"Cores primárias (tuple): {cores_primarias}")
    print(f"Tipo da variável: {type(coordenadas)}\n")
    
    # dict: dicionário de pares chave-valor (equivalente a HashMap do Java)
    pessoa = {
        "nome": "João",
        "idade": 25,
        "cidade": "São Paulo"
    }
    print("TIPO DICT (dicionário):")
    print(f"Dados da pessoa: {pessoa}")
    print(f"Nome: {pessoa['nome']}")
    print(f"Tipo da variável: {type(pessoa)}\n")
    
    # set: conjunto de elementos únicos não ordenados
    numeros_unicos = {1, 2, 3, 2, 1}  # Duplicatas são removidas
    print("TIPO SET (conjunto):")
    print(f"Conjunto de números (duplicatas removidas): {numeros_unicos}")
    print(f"Tipo da variável: {type(numeros_unicos)}\n")
    
    # ==================== OPERAÇÕES E CONVERSÕES ====================
    
    print("=== EXEMPLOS DE OPERAÇÕES ===\n")
    
    # Operações aritméticas
    numero1 = 10
    numero2 = 3
    print("Operações com inteiros:")
    print(f"{numero1} + {numero2} = {numero1 + numero2}")
    print(f"{numero1} - {numero2} = {numero1 - numero2}")
    print(f"{numero1} * {numero2} = {numero1 * numero2}")
    print(f"{numero1} / {numero2} = {numero1 / numero2}")  # Divisão decimal
    print(f"{numero1} // {numero2} = {numero1 // numero2}")  # Divisão inteira
    print(f"{numero1} % {numero2} = {numero1 % numero2}")  # Resto da divisão
    print(f"{numero1} ** {numero2} = {numero1 ** numero2}")  # Potenciação
    
    # Concatenação de strings
    nome = "Python"
    versao = 3.12
    print(f"\nConcatenação:")
    print(f"Linguagem: {nome} | Versão: {versao}")
    
    # Comparações
    print("\nComparações booleanas:")
    print(f"10 > 3: {10 > 3}")
    print(f"5 == 5: {5 == 5}")
    print(f"'A' == 'B': {'A' == 'B'}")
    
    # Conversões de tipo (type casting)
    print("\nConversões de tipo:")
    texto_numero = "42"
    numero_convertido = int(texto_numero)
    print(f"String '{texto_numero}' convertida para int: {numero_convertido}")
    
    numero_para_texto = str(100)
    print(f"Número 100 convertido para string: '{numero_para_texto}'")
    
    numero_para_float = float(5)
    print(f"Int 5 convertido para float: {numero_para_float}")
    
    # Função type() para verificar o tipo de uma variável
    print("\nVerificando tipos com type():")
    print(f"type(42) = {type(42)}")
    print(f"type(3.14) = {type(3.14)}")
    print(f"type('texto') = {type('texto')}")
    print(f"type(True) = {type(True)}")
    print(f"type([1,2,3]) = {type([1,2,3])}")
    
    # ==================== DICAS IMPORTANTES ====================
    
    print("\n=== DICAS IMPORTANTES ===")
    print("1. Python tem tipagem dinâmica - não precisa declarar tipos")
    print("2. Use 'int' para números inteiros (suporta qualquer tamanho)")
    print("3. Use 'float' para números decimais")
    print("4. Use 'str' para textos (aspas simples ou duplas)")
    print("5. Use 'bool' para valores True/False (primeira letra maiúscula!)")
    print("6. Use 'list' para listas ordenadas e mutáveis")
    print("7. Use 'tuple' para dados ordenados e imutáveis")
    print("8. Use 'dict' para pares chave-valor")
    print("9. Use 'set' para conjuntos de elementos únicos")
    print("10. Use type() para descobrir o tipo de uma variável")
    print("11. Python faz divisão decimal por padrão: 10/3 = 3.333...")
    print("12. Use // para divisão inteira: 10//3 = 3")


if __name__ == "__main__":
    main()
