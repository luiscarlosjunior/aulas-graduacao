"""
Demonstração de Manipulação de Strings em Python

Este programa demonstra como trabalhar com strings (texto) em Python,
incluindo operações, métodos e técnicas de formatação.

@author luiscaparroz
@version 1.0
"""

def main():
    """Função principal que demonstra manipulação de strings"""
    
    print("=== DEMONSTRAÇÃO DE MANIPULAÇÃO DE STRINGS EM PYTHON ===\n")
    
    # ==================== CRIANDO STRINGS ====================
    
    print("1. CRIANDO STRINGS:")
    
    # Aspas simples ou duplas
    string1 = 'String com aspas simples'
    string2 = "String com aspas duplas"
    print(f"Aspas simples: {string1}")
    print(f"Aspas duplas: {string2}")
    
    # String multilinha
    string_multilinha = """
    Esta é uma string
    que ocupa várias
    linhas!
    """
    print(f"Multilinha: {string_multilinha}")
    
    # String com caracteres especiais
    string_especial = "Linha 1\nLinha 2\tTabulação"
    print(f"Caracteres especiais:\n{string_especial}")
    
    print()
    
    # ==================== CONCATENAÇÃO ====================
    
    print("2. CONCATENAÇÃO DE STRINGS:")
    
    nome = "Python"
    versao = "3.12"
    
    # Usando +
    mensagem1 = nome + " " + versao
    print(f"Com +: {mensagem1}")
    
    # Usando f-string (recomendado)
    mensagem2 = f"{nome} {versao}"
    print(f"Com f-string: {mensagem2}")
    
    # Usando format()
    mensagem3 = "{} {}".format(nome, versao)
    print(f"Com format(): {mensagem3}")
    
    # Repetição
    traco = "-" * 30
    print(f"Repetição: {traco}")
    
    print()
    
    # ==================== ACESSANDO CARACTERES ====================
    
    print("3. ACESSANDO CARACTERES:")
    texto = "Python"
    
    print(f"Texto: {texto}")
    print(f"Primeiro caractere [0]: {texto[0]}")
    print(f"Último caractere [-1]: {texto[-1]}")
    print(f"Terceiro caractere [2]: {texto[2]}")
    
    print()
    
    # ==================== SLICING ====================
    
    print("4. SLICING (FATIAMENTO):")
    texto = "Python Programming"
    
    print(f"Texto completo: '{texto}'")
    print(f"Primeiros 6 caracteres [:6]: '{texto[:6]}'")
    print(f"Últimos 11 caracteres [-11:]: '{texto[-11:]}'")
    print(f"Do 7 ao 18 [7:18]: '{texto[7:18]}'")
    print(f"A cada 2 caracteres [::2]: '{texto[::2]}'")
    print(f"Texto invertido [::-1]: '{texto[::-1]}'")
    
    print()
    
    # ==================== MÉTODOS DE TRANSFORMAÇÃO ====================
    
    print("5. MÉTODOS DE TRANSFORMAÇÃO:")
    texto = "Python Programming"
    
    print(f"Original: '{texto}'")
    print(f"upper(): '{texto.upper()}'")
    print(f"lower(): '{texto.lower()}'")
    print(f"capitalize(): '{texto.capitalize()}'")
    print(f"title(): '{texto.title()}'")
    print(f"swapcase(): '{texto.swapcase()}'")
    
    print()
    
    # ==================== MÉTODOS DE BUSCA ====================
    
    print("6. MÉTODOS DE BUSCA:")
    texto = "Python é uma linguagem Python"
    
    print(f"Texto: '{texto}'")
    print(f"'Python' in texto: {'Python' in texto}")
    print(f"find('Python'): {texto.find('Python')}")
    print(f"rfind('Python'): {texto.rfind('Python')}")
    print(f"count('Python'): {texto.count('Python')}")
    print(f"index('linguagem'): {texto.index('linguagem')}")
    print(f"startswith('Python'): {texto.startswith('Python')}")
    print(f"endswith('Python'): {texto.endswith('Python')}")
    
    print()
    
    # ==================== MÉTODOS DE LIMPEZA ====================
    
    print("7. MÉTODOS DE LIMPEZA:")
    
    texto_espacos = "   Python   "
    print(f"Original: '{texto_espacos}'")
    print(f"strip(): '{texto_espacos.strip()}'")
    print(f"lstrip(): '{texto_espacos.lstrip()}'")
    print(f"rstrip(): '{texto_espacos.rstrip()}'")
    
    print()
    
    # ==================== MÉTODOS DE SUBSTITUIÇÃO ====================
    
    print("8. MÉTODOS DE SUBSTITUIÇÃO:")
    texto = "Python é incrível. Python é poderoso."
    
    print(f"Original: '{texto}'")
    print(f"replace('Python', 'Java'): '{texto.replace('Python', 'Java')}'")
    print(f"replace('Python', 'Java', 1): '{texto.replace('Python', 'Java', 1)}'")
    
    print()
    
    # ==================== SPLIT E JOIN ====================
    
    print("9. SPLIT E JOIN:")
    
    # Split - dividir string em lista
    frase = "Python é uma linguagem incrível"
    palavras = frase.split()
    print(f"Frase: '{frase}'")
    print(f"split(): {palavras}")
    
    csv = "nome,idade,cidade"
    dados = csv.split(',')
    print(f"\nCSV: '{csv}'")
    print(f"split(','): {dados}")
    
    # Join - juntar lista em string
    palavras = ["Python", "é", "incrível"]
    frase = " ".join(palavras)
    print(f"\nLista: {palavras}")
    print(f"' '.join(): '{frase}'")
    
    caminho = ["usr", "local", "bin"]
    caminho_completo = "/".join(caminho)
    print(f"\nLista: {caminho}")
    print(f"'/'.join(): '{caminho_completo}'")
    
    print()
    
    # ==================== VERIFICAÇÕES ====================
    
    print("10. VERIFICAÇÕES:")
    
    print(f"'Python'.isalpha(): {'Python'.isalpha()}")
    print(f"'Python3'.isalnum(): {'Python3'.isalnum()}")
    print(f"'12345'.isdigit(): {'12345'.isdigit()}")
    print(f"'Python'.islower(): {'Python'.islower()}")
    print(f"'PYTHON'.isupper(): {'PYTHON'.isupper()}")
    print(f"'   '.isspace(): {'   '.isspace()}")
    print(f"'Python Programming'.istitle(): {'Python Programming'.istitle()}")
    
    print()
    
    # ==================== FORMATAÇÃO ====================
    
    print("11. FORMATAÇÃO DE STRINGS:")
    
    nome = "João"
    idade = 25
    altura = 1.75
    
    # f-string (Python 3.6+) - RECOMENDADO
    print(f"f-string: {nome} tem {idade} anos e {altura:.2f}m")
    
    # format()
    print("format(): {} tem {} anos e {:.2f}m".format(nome, idade, altura))
    
    # % (antigo, mas ainda usado)
    print("% operator: %s tem %d anos e %.2fm" % (nome, idade, altura))
    
    # Alinhamento
    print(f"\nAlinhamento:")
    print(f"Esquerda: |{nome:<10}|")
    print(f"Centro:   |{nome:^10}|")
    print(f"Direita:  |{nome:>10}|")
    
    # Números
    numero = 42
    print(f"\nFormatos numéricos:")
    print(f"Decimal: {numero:d}")
    print(f"Binário: {numero:b}")
    print(f"Octal: {numero:o}")
    print(f"Hexadecimal: {numero:x}")
    
    print()
    
    # ==================== EXEMPLOS PRÁTICOS ====================
    
    print("12. EXEMPLOS PRÁTICOS:")
    
    # Validar email simples
    email = "usuario@exemplo.com"
    eh_valido = "@" in email and "." in email.split("@")[1]
    print(f"Email '{email}' é válido? {eh_valido}")
    
    # Extrair extensão de arquivo
    arquivo = "documento.pdf"
    extensao = arquivo.split(".")[-1]
    print(f"Extensão de '{arquivo}': .{extensao}")
    
    # Formatar nome próprio
    nome_completo = "jOãO sILVA sANTOS"
    nome_formatado = nome_completo.title()
    print(f"Nome formatado: {nome_formatado}")
    
    # Remover acentos (básico)
    texto_acentuado = "São Paulo"
    # Para remover acentos completamente, use biblioteca unicodedata
    print(f"Texto com acentos: {texto_acentuado}")
    
    # Censurar palavras
    mensagem = "Python é incrível e Python é poderoso"
    censurado = mensagem.replace("Python", "****")
    print(f"Censurado: {censurado}")
    
    # Contar palavras
    frase = "Python é uma linguagem de programação incrível"
    num_palavras = len(frase.split())
    print(f"Número de palavras em '{frase}': {num_palavras}")
    
    print()
    
    # ==================== STRING MULTILINHA E DOCSTRING ====================
    
    print("13. STRING MULTILINHA E DOCSTRING:")
    
    relatorio = """
    RELATÓRIO DE VENDAS
    -------------------
    Produto: Notebook
    Quantidade: 10
    Valor Total: R$ 25.000,00
    """
    print(relatorio)
    
    print()
    
    # ==================== ESCAPE DE CARACTERES ====================
    
    print("14. ESCAPE DE CARACTERES:")
    
    print("Aspas dentro da string: \"Python\" é incrível")
    print('Aspas simples: \'Python\' é incrível')
    print("Barra invertida: C:\\Users\\Python")
    print("Nova linha:\nLinha 1\nLinha 2")
    print("Tabulação:\tColuna 1\tColuna 2")
    
    # Raw string (ignora escape)
    caminho = r"C:\Users\Python\novo_projeto"
    print(f"Raw string: {caminho}")
    
    print()
    
    # ==================== IMUTABILIDADE ====================
    
    print("15. IMUTABILIDADE DAS STRINGS:")
    texto = "Python"
    print(f"Texto original: {texto}")
    
    # Strings são imutáveis - não podemos alterar caracteres
    # texto[0] = 'J'  # Isso geraria erro!
    
    # Mas podemos criar novas strings
    novo_texto = 'J' + texto[1:]
    print(f"Novo texto: {novo_texto}")
    print(f"Texto original não mudou: {texto}")
    
    print()
    
    # ==================== DICAS IMPORTANTES ====================
    
    print("=== DICAS IMPORTANTES ===")
    print("1. Strings são imutáveis - toda operação cria nova string")
    print("2. Use f-strings para formatação (mais legível)")
    print("3. Use strip() para remover espaços em branco")
    print("4. Use split() e join() para processar texto")
    print("5. Use 'in' para verificar substring")
    print("6. Use lower() ou upper() para comparações case-insensitive")
    print("7. Use raw strings (r'') para caminhos de arquivo no Windows")
    print("8. Métodos de string não modificam a original")
    print("9. Índices começam em 0, negativos contam do final")
    print("10. Use slicing [início:fim:passo] para extrair partes")


if __name__ == "__main__":
    main()
