"""
Operadores em Python

Demonstra os diferentes tipos de operadores disponíveis em Python:
aritméticos, comparação, lógicos, atribuição, identidade e pertencimento.

@author luiscaparroz
@version 1.0
"""

def main():
    """Demonstração dos operadores em Python"""
    
    print("=== OPERADORES EM PYTHON ===\n")
    
    # ==================== OPERADORES ARITMÉTICOS ====================
    print("1. OPERADORES ARITMÉTICOS:")
    
    a = 10
    b = 3
    
    print(f"  a = {a}, b = {b}")
    print(f"  a + b = {a + b}  (adição)")
    print(f"  a - b = {a - b}  (subtração)")
    print(f"  a * b = {a * b}  (multiplicação)")
    print(f"  a / b = {a / b:.2f}  (divisão)")
    print(f"  a // b = {a // b}  (divisão inteira)")
    print(f"  a % b = {a % b}  (resto/módulo)")
    print(f"  a ** b = {a ** b}  (potenciação)")
    
    # Divisão sempre retorna float
    print(f"\n  ⚠️  10 / 5 = {10 / 5} (float, não int!)")
    print(f"  Use // para divisão inteira: 10 // 5 = {10 // 5}")
    
    print()
    
    # ==================== OPERADORES DE COMPARAÇÃO ====================
    print("2. OPERADORES DE COMPARAÇÃO:")
    
    x = 10
    y = 5
    
    print(f"  x = {x}, y = {y}")
    print(f"  x == y: {x == y}  (igual a)")
    print(f"  x != y: {x != y}  (diferente de)")
    print(f"  x > y: {x > y}  (maior que)")
    print(f"  x < y: {x < y}  (menor que)")
    print(f"  x >= y: {x >= y}  (maior ou igual)")
    print(f"  x <= y: {x <= y}  (menor ou igual)")
    
    # Comparações encadeadas (único do Python!)
    print("\n  Comparações encadeadas (recurso único do Python):")
    valor = 15
    print(f"  10 < {valor} < 20: {10 < valor < 20}")
    print(f"  5 < {valor} < 10: {5 < valor < 10}")
    
    print()
    
    # ==================== OPERADORES LÓGICOS ====================
    print("3. OPERADORES LÓGICOS:")
    
    verdadeiro = True
    falso = False
    
    print(f"  verdadeiro = {verdadeiro}, falso = {falso}")
    print(f"  verdadeiro and falso: {verdadeiro and falso}  (E lógico)")
    print(f"  verdadeiro or falso: {verdadeiro or falso}  (OU lógico)")
    print(f"  not verdadeiro: {not verdadeiro}  (NÃO lógico)")
    
    # Tabela verdade AND
    print("\n  Tabela verdade AND:")
    print(f"    True and True: {True and True}")
    print(f"    True and False: {True and False}")
    print(f"    False and False: {False and False}")
    
    # Tabela verdade OR
    print("\n  Tabela verdade OR:")
    print(f"    True or True: {True or True}")
    print(f"    True or False: {True or False}")
    print(f"    False or False: {False or False}")
    
    # Avaliação de curto-circuito
    print("\n  Python usa avaliação de curto-circuito:")
    print("  (False and qualquer_coisa) sempre é False")
    print("  (True or qualquer_coisa) sempre é True")
    
    print()
    
    # ==================== OPERADORES DE ATRIBUIÇÃO ====================
    print("4. OPERADORES DE ATRIBUIÇÃO:")
    
    num = 10
    print(f"  num = {num}")
    
    num += 5  # num = num + 5
    print(f"  num += 5  → {num}")
    
    num -= 3  # num = num - 3
    print(f"  num -= 3  → {num}")
    
    num *= 2  # num = num * 2
    print(f"  num *= 2  → {num}")
    
    num /= 4  # num = num / 4
    print(f"  num /= 4  → {num}")
    
    num //= 2  # num = num // 2
    print(f"  num //= 2  → {num}")
    
    num %= 3  # num = num % 3
    print(f"  num %= 3  → {num}")
    
    num **= 3  # num = num ** 3
    print(f"  num **= 3  → {num}")
    
    print()
    
    # ==================== OPERADORES DE IDENTIDADE ====================
    print("5. OPERADORES DE IDENTIDADE:")
    
    a = [1, 2, 3]
    b = [1, 2, 3]
    c = a
    
    print(f"  a = {a}, b = {b}, c = a")
    print(f"  a == b: {a == b}  (mesmo conteúdo)")
    print(f"  a is b: {a is b}  (mesmo objeto na memória)")
    print(f"  a is c: {a is c}  (c aponta para o mesmo objeto que a)")
    print(f"  a is not b: {a is not b}")
    
    # Caso especial: None
    print("\n  Use 'is' para comparar com None:")
    valor = None
    print(f"  valor is None: {valor is None}  ✓ Correto")
    print(f"  valor == None: {valor == None}  ⚠️  Funciona mas não é recomendado")
    
    print()
    
    # ==================== OPERADORES DE PERTENCIMENTO ====================
    print("6. OPERADORES DE PERTENCIMENTO:")
    
    lista = [1, 2, 3, 4, 5]
    print(f"  lista = {lista}")
    print(f"  3 in lista: {3 in lista}")
    print(f"  10 in lista: {10 in lista}")
    print(f"  10 not in lista: {10 not in lista}")
    
    # Funciona com strings também
    texto = "Python é incrível"
    print(f"\n  texto = '{texto}'")
    print(f"  'Python' in texto: {'Python' in texto}")
    print(f"  'Java' in texto: {'Java' in texto}")
    
    # Funciona com dicionários (verifica chaves)
    pessoa = {"nome": "João", "idade": 25}
    print(f"\n  pessoa = {pessoa}")
    print(f"  'nome' in pessoa: {'nome' in pessoa}")
    print(f"  'João' in pessoa: {'João' in pessoa}  (verifica chaves, não valores)")
    
    print()
    
    # ==================== OPERADORES BIT A BIT ====================
    print("7. OPERADORES BIT A BIT (Bitwise):")
    
    a = 12  # 1100 em binário
    b = 10  # 1010 em binário
    
    print(f"  a = {a} ({bin(a)}), b = {b} ({bin(b)})")
    print(f"  a & b = {a & b}  (AND bit a bit)")
    print(f"  a | b = {a | b}  (OR bit a bit)")
    print(f"  a ^ b = {a ^ b}  (XOR bit a bit)")
    print(f"  ~a = {~a}  (NOT bit a bit)")
    print(f"  a << 2 = {a << 2}  (deslocamento à esquerda)")
    print(f"  a >> 2 = {a >> 2}  (deslocamento à direita)")
    
    print()
    
    # ==================== PRECEDÊNCIA DE OPERADORES ====================
    print("8. PRECEDÊNCIA DE OPERADORES:")
    
    resultado = 2 + 3 * 4
    print(f"  2 + 3 * 4 = {resultado}  (* tem precedência)")
    
    resultado = (2 + 3) * 4
    print(f"  (2 + 3) * 4 = {resultado}  (parênteses têm maior precedência)")
    
    resultado = 2 ** 3 ** 2
    print(f"  2 ** 3 ** 2 = {resultado}  (** é associativo à direita)")
    
    resultado = (2 ** 3) ** 2
    print(f"  (2 ** 3) ** 2 = {resultado}")
    
    print("\n  Ordem de precedência (maior para menor):")
    print("  1. ()")
    print("  2. **")
    print("  3. *, /, //, %")
    print("  4. +, -")
    print("  5. <, <=, >, >=, ==, !=")
    print("  6. not")
    print("  7. and")
    print("  8. or")
    
    print()
    
    # ==================== OPERADOR WALRUS (Python 3.8+) ====================
    print("9. OPERADOR WALRUS := (Python 3.8+):")
    
    # Atribuição e uso em uma expressão
    if (n := 10) > 5:
        print(f"  n := 10 atribui e verifica: n = {n}, n > 5")
    
    # Útil em list comprehension
    numeros = [1, 2, 3, 4, 5]
    quadrados = [(x, x**2) for x in numeros if (y := x**2) > 10]
    print(f"  Números e seus quadrados (se quadrado > 10): {quadrados}")
    
    print()
    
    # ==================== EXEMPLOS PRÁTICOS ====================
    print("10. EXEMPLOS PRÁTICOS:")
    
    # Verificar se número é par
    numero = 10
    eh_par = numero % 2 == 0
    print(f"  {numero} é par? {eh_par}")
    
    # Verificar se está em um range
    idade = 25
    eh_adulto = 18 <= idade < 60
    print(f"  Idade {idade} é adulto (18-59)? {eh_adulto}")
    
    # Calcular desconto
    preco = 100
    tem_cupom = True
    desconto = preco * 0.1 if tem_cupom else 0
    preco_final = preco - desconto
    print(f"  Preço: R${preco}, Desconto: R${desconto}, Final: R${preco_final}")
    
    # Validar entrada
    nome = "João"
    eh_valido = len(nome) > 0 and nome.isalpha()
    print(f"  Nome '{nome}' é válido? {eh_valido}")
    
    print()
    
    # ==================== DICAS IMPORTANTES ====================
    print("=== DICAS IMPORTANTES ===")
    print("1. / sempre retorna float, use // para divisão inteira")
    print("2. ** é o operador de potenciação (não Math.pow() como Java)")
    print("3. Use 'and', 'or', 'not' (não &&, ||, ! como Java)")
    print("4. Python permite comparações encadeadas: 0 < x < 10")
    print("5. Use 'is' para comparar com None, não ==")
    print("6. Use 'in' para verificar pertencimento em coleções")
    print("7. Operadores lógicos fazem avaliação de curto-circuito")
    print("8. Use parênteses para deixar a precedência explícita")
    print("9. Operador walrus := permite atribuição em expressões")
    print("10. Cuidado: True e False têm primeira letra maiúscula!")


if __name__ == "__main__":
    main()
