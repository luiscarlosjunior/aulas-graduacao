# Padrões Criacionais (Creational Patterns)

Os padrões criacionais abstraem o processo de criação de objetos, tornando o sistema independente de como seus objetos são criados, compostos e representados.

## 🎯 Objetivos

- Flexibilizar a criação de objetos
- Reduzir dependências entre classes
- Permitir diferentes formas de instanciação
- Controlar o processo de criação

## 📋 Padrões Implementados

### [Singleton](singleton/)
**Problema**: Garantir que uma classe tenha apenas uma instância e fornecer acesso global a ela.

**Quando usar**:
- Conexões com banco de dados
- Logs de sistema
- Configurações globais
- Cache de aplicação

### [Factory Method](factory-method/)
**Problema**: Criar objetos sem especificar suas classes exatas, delegando a criação para subclasses.

**Quando usar**:
- Quando não se sabe de antemão quais tipos de objetos criar
- Para localizar a criação de objetos em um lugar
- Para facilitar a adição de novos tipos

### [Builder](builder/)
**Problema**: Construir objetos complexos passo a passo, permitindo diferentes configurações.

**Quando usar**:
- Objetos com muitos parâmetros opcionais
- Processo de criação complexo
- Diferentes representações do mesmo objeto

### [Abstract Factory](abstract-factory/)
**Problema**: Criar famílias de objetos relacionados sem especificar suas classes concretas.

**Quando usar**:
- Sistema deve ser independente da criação de produtos
- Famílias de produtos relacionados devem ser usadas juntas
- Interface de produtos sem implementação específica

## 🚀 Como Executar os Exemplos

```bash
# Navegar para a pasta de um padrão
cd singleton/

# Compilar
javac *.java

# Executar o exemplo
java TesteSingleton
```

## 💡 Comparação dos Padrões

| Padrão | Complexidade | Flexibilidade | Uso Comum |
|--------|-------------|---------------|-----------|
| Singleton | Baixa | Baixa | Recursos únicos |
| Factory Method | Média | Média | Criação polimórfica |
| Builder | Alta | Alta | Objetos complexos |
| Abstract Factory | Alta | Alta | Famílias de produtos |

## 📝 Exercícios Práticos

1. **Singleton**: Implemente um gerenciador de configurações
2. **Factory**: Crie uma factory para diferentes tipos de veículos
3. **Builder**: Construa um builder para pizzas personalizadas
4. **Abstract Factory**: Desenvolva factories para diferentes temas de UI

## 🔗 Próxima Seção

Continue para [Padrões Estruturais](../02-structural/) para aprender sobre composição de objetos.