"""
Conversões de Tipos em Python

Demonstra como converter entre diferentes tipos de dados (type casting).

@author luiscaparroz
@version 1.0
"""

def main():
    """Demonstração de conversões de tipos"""
    
    print("=== CONVERSÕES DE TIPOS EM PYTHON ===\n")
    
    # ==================== STRING PARA NÚMERO ====================
    print("1. STRING PARA NÚMERO:")
    
    # String para int
    texto_numero = "42"
    numero = int(texto_numero)
    print(f"  String '42' -> int: {numero} (tipo: {type(numero).__name__})")
    
    # String para float
    texto_decimal = "3.14"
    decimal = float(texto_decimal)
    print(f"  String '3.14' -> float: {decimal} (tipo: {type(decimal).__name__})")
    
    # Erro ao converter string inválida
    print("\n  Tentando converter string inválida:")
    try:
        invalido = int("abc")
    except ValueError as e:
        print(f"  ✗ Erro: {e}")
    
    print()
    
    # ==================== NÚMERO PARA STRING ====================
    print("2. NÚMERO PARA STRING:")
    
    numero = 42
    texto = str(numero)
    print(f"  int 42 -> str: '{texto}' (tipo: {type(texto).__name__})")
    
    decimal = 3.14159
    texto_decimal = str(decimal)
    print(f"  float 3.14159 -> str: '{texto_decimal}' (tipo: {type(texto_decimal).__name__})")
    
    print()
    
    # ==================== INT PARA FLOAT ====================
    print("3. INT PARA FLOAT:")
    
    inteiro = 10
    flutuante = float(inteiro)
    print(f"  int 10 -> float: {flutuante} (tipo: {type(flutuante).__name__})")
    
    print()
    
    # ==================== FLOAT PARA INT ====================
    print("4. FLOAT PARA INT (trunca os decimais):")
    
    decimal = 3.9
    inteiro = int(decimal)
    print(f"  float 3.9 -> int: {inteiro} (tipo: {type(inteiro).__name__})")
    print("  ⚠️  Atenção: int() trunca, não arredonda!")
    
    # Para arredondar, use round()
    decimal = 3.9
    arredondado = round(decimal)
    print(f"  round(3.9) -> int: {arredondado}")
    
    decimal = 3.5
    arredondado = round(decimal)
    print(f"  round(3.5) -> int: {arredondado}")
    
    print()
    
    # ==================== BOOLEANO PARA NÚMERO ====================
    print("5. BOOLEANO PARA NÚMERO:")
    
    verdadeiro = True
    falso = False
    
    print(f"  int(True): {int(verdadeiro)}")
    print(f"  int(False): {int(falso)}")
    print(f"  float(True): {float(verdadeiro)}")
    
    print()
    
    # ==================== NÚMERO PARA BOOLEANO ====================
    print("6. NÚMERO PARA BOOLEANO:")
    
    # Qualquer número diferente de 0 é True
    print(f"  bool(0): {bool(0)}")
    print(f"  bool(1): {bool(1)}")
    print(f"  bool(-1): {bool(-1)}")
    print(f"  bool(42): {bool(42)}")
    print(f"  bool(0.0): {bool(0.0)}")
    print(f"  bool(3.14): {bool(3.14)}")
    
    print()
    
    # ==================== STRING PARA BOOLEANO ====================
    print("7. STRING PARA BOOLEANO:")
    
    # String vazia é False, qualquer outra é True
    print(f"  bool(''): {bool('')}")
    print(f"  bool('texto'): {bool('texto')}")
    print(f"  bool('False'): {bool('False')}")  # Atenção: string não vazia!
    print(f"  bool('0'): {bool('0')}")  # Atenção: string não vazia!
    
    print()
    
    # ==================== CONVERSÃO IMPLÍCITA ====================
    print("8. CONVERSÃO IMPLÍCITA (Coerção):")
    
    # int + float = float
    resultado1 = 10 + 3.14
    print(f"  int + float: 10 + 3.14 = {resultado1} (tipo: {type(resultado1).__name__})")
    
    # int * float = float
    resultado2 = 5 * 2.0
    print(f"  int * float: 5 * 2.0 = {resultado2} (tipo: {type(resultado2).__name__})")
    
    # Divisão sempre retorna float
    resultado3 = 10 / 2
    print(f"  int / int: 10 / 2 = {resultado3} (tipo: {type(resultado3).__name__})")
    
    print()
    
    # ==================== CONVERSÕES ESPECIAIS ====================
    print("9. CONVERSÕES ESPECIAIS:")
    
    # String binária para int
    binario = "1010"
    numero = int(binario, 2)
    print(f"  String binária '1010' -> int: {numero}")
    
    # String hexadecimal para int
    hexadecimal = "FF"
    numero = int(hexadecimal, 16)
    print(f"  String hex 'FF' -> int: {numero}")
    
    # Int para binário
    numero = 10
    print(f"  int 10 -> binário: {bin(numero)}")
    
    # Int para hexadecimal
    print(f"  int 10 -> hex: {hex(numero)}")
    
    # Int para octal
    print(f"  int 10 -> octal: {oct(numero)}")
    
    print()
    
    # ==================== VERIFICAÇÃO DE TIPOS ====================
    print("10. VERIFICAÇÃO DE TIPOS:")
    
    numero = 42
    print(f"  isinstance(42, int): {isinstance(numero, int)}")
    print(f"  isinstance(42, float): {isinstance(numero, float)}")
    print(f"  isinstance(42, str): {isinstance(numero, str)}")
    
    # isinstance aceita tupla de tipos
    valor = 3.14
    print(f"  isinstance(3.14, (int, float)): {isinstance(valor, (int, float))}")
    
    print()
    
    # ==================== EXEMPLO PRÁTICO ====================
    print("11. EXEMPLO PRÁTICO - Calculadora:")
    
    def calcular_media():
        """Exemplo de uso de conversões em programa real"""
        print("  Digite três números:")
        
        # Simular entrada (normalmente seria input())
        entradas = ["8", "7.5", "9"]
        
        numeros = []
        for i, entrada in enumerate(entradas, 1):
            print(f"    Número {i}: {entrada}")
            try:
                # Tenta converter para float
                numero = float(entrada)
                numeros.append(numero)
            except ValueError:
                print(f"    ✗ '{entrada}' não é um número válido!")
                return
        
        if numeros:
            media = sum(numeros) / len(numeros)
            print(f"\n  Média: {media:.2f}")
    
    calcular_media()
    
    print()
    
    # ==================== DICAS IMPORTANTES ====================
    print("=== DICAS IMPORTANTES ===")
    print("1. Use int() para converter para inteiro")
    print("2. Use float() para converter para decimal")
    print("3. Use str() para converter para string")
    print("4. Use bool() para converter para booleano")
    print("5. Conversões podem gerar ValueError se inválidas")
    print("6. int() trunca decimais, use round() para arredondar")
    print("7. Use isinstance() para verificar tipo de variável")
    print("8. Python faz conversão implícita em operações mistas")
    print("9. Sempre valide entradas do usuário antes de converter")
    print("10. Use try-except para tratar erros de conversão")


if __name__ == "__main__":
    main()
