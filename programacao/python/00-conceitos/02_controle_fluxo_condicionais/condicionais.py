"""
Demonstração de Controle de Fluxo - Condicionais em Python

Este programa demonstra como usar estruturas condicionais (if, elif, else)
para tomar decisões no programa.

@author luiscaparroz
@version 1.0
"""

def main():
    """
    Função principal que demonstra o uso de estruturas condicionais
    """
    
    print("=== DEMONSTRAÇÃO DE CONDICIONAIS EM PYTHON ===\n")
    
    # ==================== IF SIMPLES ====================
    
    print("1. IF SIMPLES:")
    idade = 18
    print(f"Idade: {idade}")
    
    if idade >= 18:
        print("Você é maior de idade!")
    
    print()
    
    # ==================== IF-ELSE ====================
    
    print("2. IF-ELSE:")
    nota = 7.5
    print(f"Nota: {nota}")
    
    if nota >= 6.0:
        print("Aprovado!")
    else:
        print("Reprovado!")
    
    print()
    
    # ==================== IF-ELIF-ELSE ====================
    
    print("3. IF-ELIF-ELSE (equivalente a else if do Java):")
    nota_final = 8.5
    print(f"Nota final: {nota_final}")
    
    if nota_final >= 9.0:
        print("Conceito: A - Excelente!")
    elif nota_final >= 7.0:
        print("Conceito: B - Bom!")
    elif nota_final >= 5.0:
        print("Conceito: C - Regular")
    else:
        print("Conceito: D - Insuficiente")
    
    print()
    
    # ==================== OPERADORES DE COMPARAÇÃO ====================
    
    print("4. OPERADORES DE COMPARAÇÃO:")
    a = 10
    b = 5
    print(f"a = {a}, b = {b}")
    print(f"a > b: {a > b}")   # Maior que
    print(f"a < b: {a < b}")   # Menor que
    print(f"a >= b: {a >= b}")  # Maior ou igual
    print(f"a <= b: {a <= b}")  # Menor ou igual
    print(f"a == b: {a == b}")  # Igual
    print(f"a != b: {a != b}")  # Diferente
    
    print()
    
    # ==================== OPERADORES LÓGICOS ====================
    
    print("5. OPERADORES LÓGICOS:")
    tem_carteira = True
    tem_carro = False
    print(f"Tem carteira: {tem_carteira}, Tem carro: {tem_carro}")
    
    # Operador AND (e)
    if tem_carteira and tem_carro:
        print("Pode dirigir o próprio carro")
    else:
        print("Não pode dirigir o próprio carro")
    
    # Operador OR (ou)
    if tem_carteira or tem_carro:
        print("Tem pelo menos um: carteira ou carro")
    
    # Operador NOT (não)
    if not tem_carro:
        print("Não tem carro")
    
    print()
    
    # ==================== CONDIÇÕES COMPOSTAS ====================
    
    print("6. CONDIÇÕES COMPOSTAS:")
    idade_pessoa = 25
    tem_experiencia = True
    print(f"Idade: {idade_pessoa}, Tem experiência: {tem_experiencia}")
    
    if idade_pessoa >= 18 and tem_experiencia:
        print("Pode se candidatar à vaga sênior")
    elif idade_pessoa >= 18 and not tem_experiencia:
        print("Pode se candidatar à vaga júnior")
    else:
        print("Não pode se candidatar")
    
    print()
    
    # ==================== IF TERNÁRIO (INLINE) ====================
    
    print("7. IF TERNÁRIO (condicional inline):")
    numero = 10
    # Sintaxe: valor_se_true if condição else valor_se_false
    resultado = "par" if numero % 2 == 0 else "ímpar"
    print(f"O número {numero} é {resultado}")
    
    print()
    
    # ==================== COMPARAÇÃO DE STRINGS ====================
    
    print("8. COMPARAÇÃO DE STRINGS:")
    nome = "Python"
    
    if nome == "Python":
        print("A linguagem é Python!")
    
    # Python é case-sensitive
    if nome == "python":  # Isso é False
        print("Isso não será impresso")
    else:
        print("Python é case-sensitive: 'Python' != 'python'")
    
    # Comparação ignorando maiúsculas/minúsculas
    if nome.lower() == "python":
        print("Comparação ignorando case funciona!")
    
    print()
    
    # ==================== IN OPERATOR ====================
    
    print("9. OPERADOR IN (verificar se está contido):")
    frutas = ["maçã", "banana", "laranja"]
    print(f"Lista de frutas: {frutas}")
    
    if "banana" in frutas:
        print("Banana está na lista!")
    
    if "uva" not in frutas:
        print("Uva não está na lista!")
    
    # Funciona também com strings
    texto = "Python é incrível"
    if "Python" in texto:
        print(f"A palavra 'Python' está em '{texto}'")
    
    print()
    
    # ==================== VERIFICAÇÃO DE NONE ====================
    
    print("10. VERIFICAÇÃO DE NONE (equivalente a null do Java):")
    valor = None
    print(f"Valor: {valor}")
    
    if valor is None:
        print("O valor é None (nulo)")
    
    if valor is not None:
        print("Isso não será impresso")
    else:
        print("Confirmando: o valor é None")
    
    print()
    
    # ==================== TRUTHY E FALSY ====================
    
    print("11. VALORES TRUTHY E FALSY:")
    print("Em Python, alguns valores são considerados False em contextos booleanos:")
    
    # Valores considerados False (Falsy)
    if not 0:
        print("- 0 é considerado False")
    
    if not "":
        print("- String vazia '' é considerada False")
    
    if not []:
        print("- Lista vazia [] é considerada False")
    
    if not None:
        print("- None é considerado False")
    
    # Valores considerados True (Truthy)
    if 1:
        print("- Qualquer número diferente de 0 é considerado True")
    
    if "texto":
        print("- String não vazia é considerada True")
    
    if [1, 2, 3]:
        print("- Lista não vazia é considerada True")
    
    print()
    
    # ==================== EXEMPLO PRÁTICO ====================
    
    print("12. EXEMPLO PRÁTICO - Sistema de Login:")
    usuario_correto = "admin"
    senha_correta = "12345"
    
    usuario_digitado = "admin"
    senha_digitada = "12345"
    
    if usuario_digitado == usuario_correto and senha_digitada == senha_correta:
        print("✓ Login realizado com sucesso!")
    elif usuario_digitado != usuario_correto:
        print("✗ Usuário incorreto!")
    elif senha_digitada != senha_correta:
        print("✗ Senha incorreta!")
    else:
        print("✗ Erro no login!")
    
    print()
    
    # ==================== DICAS IMPORTANTES ====================
    
    print("=== DICAS IMPORTANTES ===")
    print("1. Python usa indentação (espaços/tabs) ao invés de chaves {}")
    print("2. Não use parênteses nas condições (opcional, mas não necessário)")
    print("3. Use 'elif' ao invés de 'else if'")
    print("4. Use 'and', 'or', 'not' ao invés de &&, ||, !")
    print("5. Use 'is' para comparar com None, não ==")
    print("6. Use 'in' para verificar se um elemento está em uma coleção")
    print("7. Strings vazias, listas vazias e 0 são False em contexto booleano")
    print("8. Comparações podem ser encadeadas: 0 < x < 10")


if __name__ == "__main__":
    main()
