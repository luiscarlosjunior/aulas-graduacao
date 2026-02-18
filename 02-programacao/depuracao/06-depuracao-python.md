# 🐍 Depuração em Python - Guia Prático

## Exemplos Práticos do Dia a Dia

### Cenário 1: IndentationError e SyntaxError

**Problema comum (iniciantes):**
```python
def calcular_media(notas):
    soma = 0
    for nota in notas:
        soma += nota
      return soma / len(notas)  # IndentationError!
```

**Depuração:**
```
  File "script.py", line 5
    return soma / len(notas)
    ^
IndentationError: unindent does not match any outer indentation level
```

**Solução:**
```python
def calcular_media(notas):
    soma = 0
    for nota in notas:
        soma += nota
    return soma / len(notas)  # Indentação correta
```

**Dica:** Use um editor com visualização de espaços/tabs
```python
# VSCode: View → Render Whitespace
# Ou use: python -tt script.py (detecta mistura de tabs e espaços)
```

### Cenário 2: AttributeError e TypeError

**Problema:**
```python
class Usuario:
    def __init__(self, nome):
        self.nome = nome

usuario = Usuario("João")
print(usuario.idade)  # AttributeError: 'Usuario' object has no attribute 'idade'

resultado = "10" + 5  # TypeError: can only concatenate str to str
```

**Depuração com dir() e type():**

```python
usuario = Usuario("João")

# Debug: Quais atributos existem?
print(dir(usuario))
# ['__class__', '__delattr__', ..., 'nome']

# Debug: Verificar tipo
print(type(usuario))      # <class '__main__.Usuario'>
print(type(usuario.nome)) # <class 'str'>

# Debug: hasattr para verificar atributo
if hasattr(usuario, 'idade'):
    print(usuario.idade)
else:
    print("Usuario não tem atributo 'idade'")

# Debug: Verificar tipos antes de operar
valor = "10"
numero = 5
print(f"Tipos: {type(valor)}, {type(numero)}")  # <class 'str'>, <class 'int'>
resultado = int(valor) + numero  # Conversão explícita
```

**Solução preventiva com type hints (Python 3.5+):**

```python
from typing import List, Optional

class Usuario:
    def __init__(self, nome: str, idade: Optional[int] = None):
        self.nome = nome
        self.idade = idade
    
    def saudar(self) -> str:
        if self.idade:
            return f"Olá, {self.nome} ({self.idade} anos)"
        return f"Olá, {self.nome}"

# Ferramentas como mypy detectam erros de tipo:
# mypy script.py
```

### Cenário 3: IndexError e KeyError

**Problema:**
```python
lista = [1, 2, 3]
print(lista[5])  # IndexError: list index out of range

dicionario = {'nome': 'João'}
print(dicionario['idade'])  # KeyError: 'idade'
```

**Depuração e soluções:**

```python
# Debug: Verificar tamanho
lista = [1, 2, 3]
print(f"Tamanho da lista: {len(lista)}")  # 3
print(f"Tentando acessar índice: 5")
# Conclusão: índice fora do range

# Solução 1: Verificar antes
if indice < len(lista):
    valor = lista[indice]
else:
    valor = None  # ou valor padrão

# Solução 2: try/except
try:
    valor = lista[indice]
except IndexError:
    print(f"Índice {indice} não existe")
    valor = None

# Para dicionários:
dicionario = {'nome': 'João'}

# Debug: Ver chaves disponíveis
print(f"Chaves disponíveis: {dicionario.keys()}")

# Solução 1: get() com default
idade = dicionario.get('idade', 18)  # Retorna 18 se não existir

# Solução 2: in para verificar
if 'idade' in dicionario:
    idade = dicionario['idade']
else:
    idade = 18

# Solução 3: defaultdict
from collections import defaultdict

dados = defaultdict(int)  # int() retorna 0
print(dados['contador'])  # 0 (não dá erro)
```

### Cenário 4: Memory Leak em Loop

**Problema:**
```python
import sys

resultados = []

for i in range(10000000):
    # Bug: Lista cresce indefinidamente
    resultados.append(i * 2)
    
    # Debug a cada 1M de iterações
    if i % 1000000 == 0:
        tamanho_bytes = sys.getsizeof(resultados)
        print(f"Iteração {i}: {tamanho_bytes / 1024 / 1024:.2f} MB")
```

**Saída:**
```
Iteração 0: 0.06 MB
Iteração 1000000: 8.05 MB
Iteração 2000000: 16.05 MB
...
MemoryError: Unable to allocate array
```

**Solução com generator:**

```python
def processar_numeros(n):
    """Generator que produz valores sob demanda"""
    for i in range(n):
        yield i * 2

# Uso: memória constante
for valor in processar_numeros(10000000):
    processar(valor)
    # Cada valor é processado e descartado

# Ou usando generator expression
valores = (i * 2 for i in range(10000000))  # Não cria lista

# Debug de memória com memory_profiler
from memory_profiler import profile

@profile
def funcao_com_problemas():
    resultados = [i * 2 for i in range(10000000)]
    return sum(resultados)

# Executar: python -m memory_profiler script.py
```

### Cenário 5: Bug em Código Assíncrono

**Problema:**
```python
import asyncio

resultado = None

async def buscar_dados():
    global resultado
    await asyncio.sleep(1)  # Simula I/O
    resultado = "Dados carregados"

# Bug: resultado ainda é None!
asyncio.run(buscar_dados())
print(resultado)  # None (timing issue)
```

**Depuração:**

```python
import asyncio
import time

async def buscar_dados():
    print(f"[{time.time():.2f}] Iniciando busca...")
    await asyncio.sleep(1)
    print(f"[{time.time():.2f}] Dados recebidos")
    return "Dados carregados"

async def main():
    print(f"[{time.time():.2f}] Main iniciou")
    resultado = await buscar_dados()  # Aguarda completar
    print(f"[{time.time():.2f}] Resultado: {resultado}")

asyncio.run(main())
```

**Saída:**
```
[1234567890.00] Main iniciou
[1234567890.00] Iniciando busca...
[1234567891.00] Dados recebidos
[1234567891.00] Resultado: Dados carregados
```

## Depuração com PDB (Python Debugger)

### Uso Básico

```python
import pdb

def calcular_fatorial(n):
    if n < 0:
        pdb.set_trace()  # Breakpoint aqui
        raise ValueError("n deve ser não-negativo")
    
    if n == 0 or n == 1:
        return 1
    
    return n * calcular_fatorial(n - 1)

# Quando executar:
# $ python script.py
# > script.py(6)calcular_fatorial()
# -> raise ValueError("n deve ser não-negativo")
# (Pdb)
```

### Comandos PDB Essenciais

```python
# Navegação
(Pdb) n          # Next: próxima linha (não entra em funções)
(Pdb) s          # Step: próxima linha (entra em funções)
(Pdb) c          # Continue: continua até próximo breakpoint
(Pdb) r          # Return: continua até return da função atual
(Pdb) q          # Quit: sai do debugger

# Inspeção
(Pdb) p variavel     # Print variável
(Pdb) pp objeto      # Pretty print (formatado)
(Pdb) a              # Arguments da função atual
(Pdb) l              # List: mostra código ao redor
(Pdb) ll             # Long list: mostra função completa
(Pdb) w              # Where: stack trace

# Breakpoints
(Pdb) b 42                    # Breakpoint na linha 42
(Pdb) b funcao                # Breakpoint na função
(Pdb) b script.py:42          # Breakpoint em arquivo específico
(Pdb) b 42, x > 100           # Breakpoint condicional
(Pdb) condition 1 x > 100     # Adiciona condição ao breakpoint 1
(Pdb) disable 1               # Desabilita breakpoint 1
(Pdb) clear 1                 # Remove breakpoint 1

# Execução de código
(Pdb) !import math            # Executar código Python
(Pdb) !x = 42                 # Modificar variável
(Pdb) !print(locals())        # Ver todas variáveis locais
```

### Breakpoint() - Python 3.7+

```python
# Forma moderna (Python 3.7+)
def processar(dados):
    breakpoint()  # Equivalente a import pdb; pdb.set_trace()
    resultado = transformar(dados)
    return resultado

# Desabilitar todos breakpoints:
# PYTHONBREAKPOINT=0 python script.py

# Usar debugger alternativo:
# PYTHONBREAKPOINT=ipdb.set_trace python script.py
```

### PDB++ (PDB melhorado)

```bash
pip install pdbpp
```

```python
import pdb; pdb.set_trace()

# Recursos extras:
# - Syntax highlighting
# - Tab completion
# - Sticky mode (mostra código sempre)
# - ll (long list) melhorado
```

## Depuração com IPython/Jupyter

### IPython Debugger

```python
# Em IPython ou Jupyter
from IPython.core.debugger import set_trace

def funcao():
    x = 10
    set_trace()  # Breakpoint IPython
    return x * 2

# Ou usar magia %debug após exceção
try:
    resultado = funcao_problematica()
except:
    %debug  # Abre debugger no ponto da exceção
```

### Magic Commands para Debug

```python
# %run com debugger
%run -d script.py           # Inicia em modo debug
%run -d -b42 script.py      # Breakpoint na linha 42

# %debug - debugger pós-mortem
def funcao_com_erro():
    return 1 / 0

funcao_com_erro()  # Causa exceção

%debug  # Abre debugger na exceção
# > <ipython>(2)funcao_com_erro()
# -> return 1 / 0

# %pdb - ativa debugger automático
%pdb on  # Agora toda exceção abre o debugger

# %time e %timeit - medir performance
%time resultado = funcao_lenta()
%timeit funcao_rapida()  # Múltiplas execuções

# %prun - profiler
%prun funcao_complexa()  # Profile de chamadas de função
```

## Logging Eficaz em Python

### Configuração Básica

```python
import logging

# Configuração simples
logging.basicConfig(
    level=logging.DEBUG,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    filename='app.log',
    filemode='a'  # append
)

logger = logging.getLogger(__name__)

# Uso
logger.debug('Variável x = %d', x)
logger.info('Processamento iniciado')
logger.warning('Memória em %.2f%%', uso_memoria)
logger.error('Erro ao conectar: %s', erro)
logger.critical('Sistema fora do ar!')
```

### Configuração Avançada

```python
import logging
import logging.handlers

def setup_logger(name, log_file, level=logging.DEBUG):
    """Configura logger com arquivo rotativo"""
    
    # Formatter
    formatter = logging.Formatter(
        '%(asctime)s - %(name)s - %(levelname)s - '
        '%(filename)s:%(lineno)d - %(message)s'
    )
    
    # Handler para arquivo rotativo (10MB, 5 backups)
    file_handler = logging.handlers.RotatingFileHandler(
        log_file,
        maxBytes=10*1024*1024,
        backupCount=5
    )
    file_handler.setFormatter(formatter)
    
    # Handler para console
    console_handler = logging.StreamHandler()
    console_handler.setFormatter(formatter)
    console_handler.setLevel(logging.INFO)  # Console só INFO+
    
    # Logger
    logger = logging.getLogger(name)
    logger.setLevel(level)
    logger.addHandler(file_handler)
    logger.addHandler(console_handler)
    
    return logger

# Uso
logger = setup_logger('minha_app', 'logs/app.log')
```

### Logging Estruturado

```python
import logging
import json
from datetime import datetime

class JSONFormatter(logging.Formatter):
    def format(self, record):
        log_data = {
            'timestamp': datetime.utcnow().isoformat(),
            'level': record.levelname,
            'logger': record.name,
            'message': record.getMessage(),
            'module': record.module,
            'function': record.funcName,
            'line': record.lineno
        }
        
        if record.exc_info:
            log_data['exception'] = self.formatException(record.exc_info)
        
        return json.dumps(log_data)

# Configurar
handler = logging.StreamHandler()
handler.setFormatter(JSONFormatter())
logger = logging.getLogger('app')
logger.addHandler(handler)

# Logs são JSON parseable
logger.info('Usuário logado', extra={'user_id': 123, 'ip': '192.168.1.1'})
```

## Ferramentas de Profiling

### cProfile - Profiler Built-in

```python
import cProfile
import pstats

# Método 1: Decorador
def profile_funcao(funcao):
    def wrapper(*args, **kwargs):
        profiler = cProfile.Profile()
        profiler.enable()
        resultado = funcao(*args, **kwargs)
        profiler.disable()
        
        stats = pstats.Stats(profiler)
        stats.sort_stats('cumulative')
        stats.print_stats(20)  # Top 20 funções
        
        return resultado
    return wrapper

@profile_funcao
def funcao_lenta():
    # Código a ser analisado
    pass

# Método 2: Context manager
from contextlib import contextmanager

@contextmanager
def profiled():
    pr = cProfile.Profile()
    pr.enable()
    yield
    pr.disable()
    stats = pstats.Stats(pr)
    stats.sort_stats('cumulative')
    stats.print_stats()

with profiled():
    funcao_lenta()

# Método 3: Linha de comando
# python -m cProfile -s cumulative script.py
```

### line_profiler - Linha por Linha

```bash
pip install line_profiler
```

```python
# Adicione @profile ao código
@profile
def funcao_analisar():
    resultado = []
    for i in range(1000):
        resultado.append(i ** 2)
    return resultado

# Executar:
# kernprof -l -v script.py
```

**Saída:**
```
Line #      Hits         Time  Per Hit   % Time  Line Contents
==============================================================
     2                                           @profile
     3                                           def funcao_analisar():
     4         1          2.0      2.0      0.0      resultado = []
     5      1001        340.0      0.3     15.2      for i in range(1000):
     6      1000       1900.0      1.9     84.8          resultado.append(i ** 2)
     7         1          0.0      0.0      0.0      return resultado
```

### memory_profiler - Uso de Memória

```bash
pip install memory_profiler
```

```python
from memory_profiler import profile

@profile
def funcao_memoria():
    lista_grande = [i for i in range(1000000)]
    lista_processada = [x * 2 for x in lista_grande]
    return lista_processada

# Executar:
# python -m memory_profiler script.py
```

## Depuração com VSCode

**launch.json:**
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": "Python: Current File",
            "type": "python",
            "request": "launch",
            "program": "${file}",
            "console": "integratedTerminal",
            "justMyCode": false
        },
        {
            "name": "Python: Debug Tests",
            "type": "python",
            "request": "launch",
            "module": "pytest",
            "args": ["-v", "tests/"],
            "console": "integratedTerminal"
        }
    ]
}
```

## Checklist de Debug Python

- [ ] Ativei todos os warnings: `python -W all script.py`
- [ ] Verifiquei indentação (tabs vs espaços)
- [ ] Usei type hints e mypy para verificação de tipos
- [ ] Adicionei logging apropriado
- [ ] Testei com diferentes entradas (incluindo edge cases)
- [ ] Verifiquei memory leaks com memory_profiler
- [ ] Profiled código lento com cProfile
- [ ] Revisei stack traces completamente
- [ ] Testei com Python debugger (pdb/ipdb)

## Próximos Passos

- 🎯 [Boas Práticas e Prevenção](07-boas-praticas.md)
- 🔍 [Técnicas de Depuração](02-tecnicas-depuracao.md)

---

**📚 Recursos:**
- [Python Debugging With pdb](https://realpython.com/python-debugging-pdb/)
- [Python Logging HOWTO](https://docs.python.org/3/howto/logging.html)
- [Python Profiling](https://docs.python.org/3/library/profile.html)
