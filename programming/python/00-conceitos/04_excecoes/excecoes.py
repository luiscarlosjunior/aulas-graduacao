"""
Demonstração de Tratamento de Exceções em Python

Este programa demonstra como usar try-except-finally para lidar com erros
e fazer o programa funcionar mesmo quando algo dá errado.

@author luiscaparroz
@version 1.0
"""

def main():
    """
    Função principal que demonstra o uso de tratamento de exceções
    """
    
    print("=== DEMONSTRAÇÃO DE TRATAMENTO DE EXCEÇÕES EM PYTHON ===\n")
    
    # ==================== TRY-EXCEPT BÁSICO ====================
    
    print("1. TRY-EXCEPT BÁSICO:")
    print("Tentando dividir 10 por 0...")
    
    try:
        resultado = 10 / 0
        print(f"Resultado: {resultado}")
    except ZeroDivisionError:
        print("✗ Erro: Não é possível dividir por zero!")
    
    print()
    
    # ==================== MÚLTIPLOS EXCEPT ====================
    
    print("2. MÚLTIPLOS EXCEPT (capturar diferentes tipos de erro):")
    
    def processar_numero(texto):
        try:
            numero = int(texto)
            resultado = 100 / numero
            print(f"  100 / {numero} = {resultado}")
        except ValueError:
            print(f"  ✗ Erro: '{texto}' não é um número válido!")
        except ZeroDivisionError:
            print(f"  ✗ Erro: Não é possível dividir por zero!")
    
    processar_numero("10")   # Funciona
    processar_numero("abc")  # ValueError
    processar_numero("0")    # ZeroDivisionError
    
    print()
    
    # ==================== EXCEPT GENÉRICO ====================
    
    print("3. EXCEPT GENÉRICO (captura qualquer erro):")
    
    try:
        lista = [1, 2, 3]
        print(f"  Acessando lista[10]...")
        print(lista[10])  # IndexError
    except Exception as e:
        print(f"  ✗ Erro capturado: {type(e).__name__}: {e}")
    
    print()
    
    # ==================== TRY-EXCEPT-ELSE ====================
    
    print("4. TRY-EXCEPT-ELSE (executado se não houver erro):")
    
    def tentar_abrir_arquivo(nome_arquivo):
        try:
            # Simula tentativa de abrir arquivo
            if nome_arquivo == "existente.txt":
                print(f"  ✓ Arquivo '{nome_arquivo}' aberto com sucesso")
            else:
                raise FileNotFoundError(f"Arquivo '{nome_arquivo}' não encontrado")
        except FileNotFoundError as e:
            print(f"  ✗ Erro: {e}")
        else:
            print(f"  ✓ Processando o arquivo...")
            # Este bloco só executa se NÃO houver exceção
    
    tentar_abrir_arquivo("existente.txt")
    tentar_abrir_arquivo("inexistente.txt")
    
    print()
    
    # ==================== TRY-EXCEPT-FINALLY ====================
    
    print("5. TRY-EXCEPT-FINALLY (sempre executado):")
    
    def processar_com_finally():
        try:
            print("  Tentando operação...")
            resultado = 10 / 2
            print(f"  Resultado: {resultado}")
        except ZeroDivisionError:
            print("  ✗ Erro na divisão!")
        finally:
            print("  ✓ Finally: Limpeza executada (sempre acontece)")
    
    processar_com_finally()
    
    print()
    
    # ==================== CAPTURANDO DETALHES DA EXCEÇÃO ====================
    
    print("6. CAPTURANDO DETALHES DA EXCEÇÃO:")
    
    try:
        numeros = [1, 2, 3]
        print(f"  Tentando acessar índice 5 em {numeros}...")
        print(numeros[5])
    except IndexError as erro:
        print(f"  ✗ Tipo do erro: {type(erro).__name__}")
        print(f"  ✗ Mensagem: {erro}")
        print(f"  ✗ Detalhes: Tentou acessar índice fora do range")
    
    print()
    
    # ==================== RAISE - LANÇANDO EXCEÇÕES ====================
    
    print("7. RAISE (lançar exceções manualmente):")
    
    def validar_idade(idade):
        if idade < 0:
            raise ValueError("Idade não pode ser negativa")
        if idade > 150:
            raise ValueError("Idade inválida: muito alta")
        return True
    
    try:
        print("  Validando idade 25...")
        validar_idade(25)
        print("  ✓ Idade válida!")
    except ValueError as e:
        print(f"  ✗ Erro: {e}")
    
    try:
        print("  Validando idade -5...")
        validar_idade(-5)
    except ValueError as e:
        print(f"  ✗ Erro: {e}")
    
    print()
    
    # ==================== EXCEÇÕES PERSONALIZADAS ====================
    
    print("8. EXCEÇÕES PERSONALIZADAS:")
    
    # Definindo uma exceção customizada
    class SaldoInsuficienteError(Exception):
        """Exceção lançada quando não há saldo suficiente"""
        def __init__(self, saldo, valor):
            self.saldo = saldo
            self.valor = valor
            mensagem = f"Saldo insuficiente: R${saldo:.2f} < R${valor:.2f}"
            super().__init__(mensagem)
    
    def sacar(saldo, valor):
        if valor > saldo:
            raise SaldoInsuficienteError(saldo, valor)
        return saldo - valor
    
    try:
        saldo_atual = 100.0
        valor_saque = 150.0
        print(f"  Tentando sacar R${valor_saque:.2f} de R${saldo_atual:.2f}...")
        novo_saldo = sacar(saldo_atual, valor_saque)
        print(f"  ✓ Saque realizado! Novo saldo: R${novo_saldo:.2f}")
    except SaldoInsuficienteError as e:
        print(f"  ✗ {e}")
    
    print()
    
    # ==================== EXCEÇÕES COMUNS ====================
    
    print("9. EXCEÇÕES COMUNS EM PYTHON:")
    
    # ZeroDivisionError
    try:
        x = 1 / 0
    except ZeroDivisionError:
        print("  • ZeroDivisionError: Divisão por zero")
    
    # ValueError
    try:
        numero = int("abc")
    except ValueError:
        print("  • ValueError: Conversão inválida de tipo")
    
    # TypeError
    try:
        resultado = "texto" + 5
    except TypeError:
        print("  • TypeError: Operação entre tipos incompatíveis")
    
    # IndexError
    try:
        lista = [1, 2, 3]
        elemento = lista[10]
    except IndexError:
        print("  • IndexError: Índice fora do range da lista")
    
    # KeyError
    try:
        dicionario = {"a": 1, "b": 2}
        valor = dicionario["c"]
    except KeyError:
        print("  • KeyError: Chave não existe no dicionário")
    
    # AttributeError
    try:
        texto = "teste"
        texto.metodo_inexistente()
    except AttributeError:
        print("  • AttributeError: Atributo ou método não existe")
    
    # FileNotFoundError
    try:
        with open("arquivo_inexistente.txt", "r") as f:
            conteudo = f.read()
    except FileNotFoundError:
        print("  • FileNotFoundError: Arquivo não encontrado")
    
    print()
    
    # ==================== ASSERT ====================
    
    print("10. ASSERT (verificação de condições):")
    
    def calcular_media(notas):
        assert len(notas) > 0, "Lista de notas não pode estar vazia"
        return sum(notas) / len(notas)
    
    try:
        notas_validas = [7, 8, 9]
        media = calcular_media(notas_validas)
        print(f"  ✓ Média de {notas_validas}: {media:.2f}")
    except AssertionError as e:
        print(f"  ✗ Erro: {e}")
    
    try:
        notas_vazias = []
        media = calcular_media(notas_vazias)
    except AssertionError as e:
        print(f"  ✗ Erro: {e}")
    
    print()
    
    # ==================== EXEMPLO PRÁTICO COMPLETO ====================
    
    print("11. EXEMPLO PRÁTICO - Sistema de Login:")
    
    class LoginError(Exception):
        """Erro de login personalizado"""
        pass
    
    def fazer_login(usuario, senha):
        """Simula um sistema de login"""
        usuarios_validos = {
            "admin": "12345",
            "user": "senha123"
        }
        
        if usuario not in usuarios_validos:
            raise LoginError(f"Usuário '{usuario}' não encontrado")
        
        if usuarios_validos[usuario] != senha:
            raise LoginError("Senha incorreta")
        
        return True
    
    # Tentativa 1: Sucesso
    try:
        print("  Tentativa 1: admin/12345")
        fazer_login("admin", "12345")
        print("  ✓ Login realizado com sucesso!")
    except LoginError as e:
        print(f"  ✗ Falha no login: {e}")
    
    # Tentativa 2: Usuário inexistente
    try:
        print("  Tentativa 2: inexistente/senha")
        fazer_login("inexistente", "senha")
        print("  ✓ Login realizado com sucesso!")
    except LoginError as e:
        print(f"  ✗ Falha no login: {e}")
    
    # Tentativa 3: Senha incorreta
    try:
        print("  Tentativa 3: admin/senha_errada")
        fazer_login("admin", "senha_errada")
        print("  ✓ Login realizado com sucesso!")
    except LoginError as e:
        print(f"  ✗ Falha no login: {e}")
    
    print()
    
    # ==================== DICAS IMPORTANTES ====================
    
    print("=== DICAS IMPORTANTES ===")
    print("1. Use try-except para capturar e tratar erros")
    print("2. Capture exceções específicas ao invés de Exception genérico")
    print("3. Use 'else' para código que só deve executar se não houver erro")
    print("4. Use 'finally' para código que sempre deve executar (limpeza)")
    print("5. Use 'raise' para lançar exceções manualmente")
    print("6. Crie exceções personalizadas herdando de Exception")
    print("7. Use 'as' para capturar detalhes da exceção")
    print("8. Não capture exceções que você não sabe tratar")
    print("9. Use 'assert' para verificações durante desenvolvimento")
    print("10. Exceções tornam o código mais robusto e confiável")


if __name__ == "__main__":
    main()
