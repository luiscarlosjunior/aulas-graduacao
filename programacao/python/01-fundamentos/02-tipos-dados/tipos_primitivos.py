"""
Tipos Primitivos em Python

Demonstra os tipos de dados básicos em Python e suas características.
Diferente do Java, Python tem tipagem dinâmica - não precisa declarar tipos.

@author luiscaparroz
@version 1.0
"""

def main():
    """Demonstração dos tipos primitivos em Python"""
    
    print("=== TIPOS PRIMITIVOS EM PYTHON ===\n")
    
    # ==================== INTEIROS (int) ====================
    print("1. TIPO INT (Inteiro):")
    
    # Em Python, int pode ter tamanho arbitrário (não há limite como em Java)
    pequeno = 10
    grande = 999999999999999999999999999999
    negativo = -42
    
    print(f"  Número pequeno: {pequeno}")
    print(f"  Número grande: {grande}")
    print(f"  Número negativo: {negativo}")
    print(f"  Tipo: {type(pequeno)}")
    
    # Operações com inteiros
    print("\n  Operações:")
    print(f"  10 + 5 = {10 + 5}")
    print(f"  10 - 5 = {10 - 5}")
    print(f"  10 * 5 = {10 * 5}")
    print(f"  10 / 5 = {10 / 5}")  # Divisão sempre retorna float
    print(f"  10 // 3 = {10 // 3}")  # Divisão inteira
    print(f"  10 % 3 = {10 % 3}")  # Resto (módulo)
    print(f"  2 ** 8 = {2 ** 8}")  # Potenciação
    
    print()
    
    # ==================== PONTO FLUTUANTE (float) ====================
    print("2. TIPO FLOAT (Ponto Flutuante):")
    
    altura = 1.75
    pi = 3.14159265359
    cientifico = 3.0e8  # 3.0 × 10^8 (notação científica)
    
    print(f"  Altura: {altura}m")
    print(f"  Pi: {pi}")
    print(f"  Notação científica: {cientifico}")
    print(f"  Tipo: {type(altura)}")
    
    # Operações com floats
    print("\n  Operações:")
    print(f"  10.5 + 2.3 = {10.5 + 2.3}")
    print(f"  10.5 * 2 = {10.5 * 2}")
    print(f"  Pi arredondado (2 casas): {pi:.2f}")
    
    print()
    
    # ==================== BOOLEANO (bool) ====================
    print("3. TIPO BOOL (Booleano):")
    
    verdadeiro = True  # Primeira letra maiúscula!
    falso = False
    
    print(f"  Verdadeiro: {verdadeiro}")
    print(f"  Falso: {falso}")
    print(f"  Tipo: {type(verdadeiro)}")
    
    # Operações lógicas
    print("\n  Operações lógicas:")
    print(f"  True and False = {True and False}")
    print(f"  True or False = {True or False}")
    print(f"  not True = {not True}")
    
    # Comparações retornam bool
    print("\n  Comparações:")
    print(f"  10 > 5 = {10 > 5}")
    print(f"  10 == 10 = {10 == 10}")
    print(f"  10 != 5 = {10 != 5}")
    
    print()
    
    # ==================== STRING (str) ====================
    print("4. TIPO STR (String):")
    
    nome = "Python"
    frase = 'Strings podem usar aspas simples ou duplas'
    multilinha = """String
    com múltiplas
    linhas"""
    
    print(f"  Nome: {nome}")
    print(f"  Frase: {frase}")
    print(f"  Tipo: {type(nome)}")
    
    # Operações com strings
    print("\n  Operações:")
    print(f"  'Python' + ' 3' = {'Python' + ' 3'}")
    print(f"  'Py' * 3 = {'Py' * 3}")
    print(f"  Tamanho de 'Python': {len('Python')}")
    print(f"  'Python'[0] = {'Python'[0]}")
    print(f"  'Python'[-1] = {'Python'[-1]}")
    print(f"  'Python'[0:3] = {'Python'[0:3]}")
    
    print()
    
    # ==================== NONE (tipo especial) ====================
    print("5. NONE (equivalente a null do Java):")
    
    valor_nulo = None
    print(f"  Valor: {valor_nulo}")
    print(f"  Tipo: {type(valor_nulo)}")
    print(f"  valor_nulo is None: {valor_nulo is None}")
    
    print()
    
    # ==================== FUNÇÃO type() ====================
    print("6. FUNÇÃO type() - Verificar tipo de variável:")
    
    print(f"  type(42): {type(42)}")
    print(f"  type(3.14): {type(3.14)}")
    print(f"  type('texto'): {type('texto')}")
    print(f"  type(True): {type(True)}")
    print(f"  type(None): {type(None)}")
    
    print()
    
    # ==================== TIPAGEM DINÂMICA ====================
    print("7. TIPAGEM DINÂMICA:")
    print("  Python permite mudar o tipo de uma variável:")
    
    variavel = 10
    print(f"  variavel = {variavel} (tipo: {type(variavel).__name__})")
    
    variavel = "texto"
    print(f"  variavel = '{variavel}' (tipo: {type(variavel).__name__})")
    
    variavel = 3.14
    print(f"  variavel = {variavel} (tipo: {type(variavel).__name__})")
    
    print("\n  ⚠️  Isso é diferente de Java que tem tipagem estática!")
    
    print()
    
    # ==================== COMPARAÇÃO COM JAVA ====================
    print("=== DIFERENÇAS EM RELAÇÃO AO JAVA ===")
    print("1. Python não tem byte, short, long separados - só int")
    print("2. Python int pode ter tamanho arbitrário (sem limite)")
    print("3. Python não tem float e double separados - só float")
    print("4. Python usa True/False (não true/false como Java)")
    print("5. Python tem None ao invés de null")
    print("6. Python tem tipagem dinâmica - não precisa declarar tipos")
    print("7. Python usa // para divisão inteira (Java usa /)")
    print("8. Python tem ** para potenciação (Java usa Math.pow())")


if __name__ == "__main__":
    main()
