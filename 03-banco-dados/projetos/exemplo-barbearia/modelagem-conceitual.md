# Modelagem Conceitual - Sistema Barbearia

## 📋 Visão Geral

O sistema de **Barbearia** é um exemplo educacional que demonstra os conceitos fundamentais de modelagem de dados através de um domínio simples e familiar. O sistema gerencia clientes, barbeiros, serviços e agendamentos de uma barbearia tradicional.

## 🎯 Objetivos do Sistema

- Cadastrar e gerenciar clientes da barbearia
- Controlar o quadro de barbeiros e suas especialidades
- Manter catálogo de serviços oferecidos
- Gerenciar agendamentos de clientes com barbeiros
- Permitir múltiplos serviços por agendamento
- Controlar status e histórico de atendimentos

## 📊 Modelo Conceitual

### Identificação das Entidades

#### 👤 CLIENTE
**Descrição**: Representa as pessoas que utilizam os serviços da barbearia

**Justificativa**: É essencial identificar quem são os clientes para:
- Manter histórico de atendimentos
- Facilitar agendamentos futuros
- Personalizar o atendimento
- Controlar preferências e observações

**Atributos Principais**:
- Nome completo
- Telefone para contato
- Email (único para cada cliente)
- Data de cadastro
- Status (ativo/inativo)

#### ✂️ BARBEIRO
**Descrição**: Representa os profissionais que prestam os serviços na barbearia

**Justificativa**: Necessário para:
- Distribuir agendamentos entre profissionais
- Controlar especialidades de cada barbeiro
- Gerenciar agenda individual
- Manter histórico de desempenho

**Atributos Principais**:
- Nome completo
- Telefone para contato
- Especialidade (corte, barba, tratamentos, etc.)
- Data de admissão
- Status (ativo/inativo)

#### 🎨 SERVIÇO
**Descrição**: Representa os tipos de serviços oferecidos pela barbearia

**Justificativa**: Essencial para:
- Padronizar ofertas da barbearia
- Controlar preços e durações
- Facilitar criação de agendamentos
- Gerar relatórios de serviços mais solicitados

**Atributos Principais**:
- Nome do serviço
- Descrição detalhada
- Duração em minutos
- Preço padrão
- Status (ativo/inativo)

#### 📅 AGENDAMENTO
**Descrição**: Representa os encontros marcados entre clientes e barbeiros

**Justificativa**: Núcleo do sistema para:
- Organizar agenda da barbearia
- Controlar horários disponíveis
- Manter histórico de atendimentos
- Calcular receita

**Atributos Principais**:
- Data e hora de início
- Data e hora de fim (quando concluído)
- Status do agendamento
- Observações específicas
- Valor total
- Data de criação do agendamento

### Relacionamentos Conceituais

#### 1. CLIENTE ←→ AGENDAMENTO
- **Tipo**: Um para Muitos (1:N)
- **Cardinalidade**: Um cliente pode ter vários agendamentos, mas cada agendamento pertence a apenas um cliente
- **Justificativa**: Clientes retornam à barbearia múltiplas vezes, criando histórico de atendimentos

#### 2. BARBEIRO ←→ AGENDAMENTO  
- **Tipo**: Um para Muitos (1:N)
- **Cardinalidade**: Um barbeiro pode ter vários agendamentos, mas cada agendamento é com apenas um barbeiro
- **Justificativa**: Barbeiros atendem múltiplos clientes, mas cada agendamento é individual

#### 3. AGENDAMENTO ←→ SERVIÇO
- **Tipo**: Muitos para Muitos (N:M)
- **Cardinalidade**: Um agendamento pode incluir vários serviços, e um serviço pode aparecer em vários agendamentos
- **Justificativa**: Flexibilidade para que clientes escolham múltiplos serviços (ex: corte + barba) no mesmo agendamento

### Diagrama Conceitual Textual

```
[CLIENTE] ──1:N── [AGENDAMENTO] ──N:1── [BARBEIRO]
                       │
                       │ N:M
                       │
                   [SERVIÇO]
```

### Regras de Negócio Identificadas

#### Regras de Integridade

1. **Cliente deve ter email único**: Evita duplicações e permite login futuro
2. **Agendamento deve ter cliente e barbeiro**: Não existe agendamento sem as partes envolvidas
3. **Serviço deve ter duração positiva**: Não existem serviços instantâneos
4. **Agendamento não pode ser no passado**: Apenas para agendamentos futuros
5. **Agendamento deve ter pelo menos um serviço**: Não faz sentido agendar sem especificar o serviço

#### Regras de Domínio

1. **Status do agendamento**: MARCADO → EM_ANDAMENTO → CONCLUÍDO ou CANCELADO
2. **Preço não pode ser negativo**: Valores devem ser zero ou positivos
3. **Duração de serviços**: Máximo 8 horas (480 minutos) para evitar erros
4. **Email deve ter formato válido**: Contém @ e . (validação básica)
5. **Nomes devem ter mínimo 2 caracteres**: Evita registros inválidos

### Atributos Derivados Identificados

#### No Agendamento:
- **Valor Total**: Soma dos preços dos serviços vinculados
- **Duração Total**: Soma das durações dos serviços vinculados
- **Data de Fim**: Calculada somando duração total ao início

#### Nos Relatórios:
- **Total de Agendamentos por Cliente**: Contagem de relacionamentos
- **Receita por Barbeiro**: Soma dos valores de agendamentos concluídos
- **Serviços Mais Solicitados**: Contagem de aparições na tabela de relacionamento

## 🎭 Análise dos Relacionamentos

### Relacionamento Cliente ↔ Agendamento

**Características**:
- **Obrigatoriedade**: Agendamento DEVE ter cliente (participação total)
- **Cardinalidade Mínima**: Cliente pode ter 0 agendamentos (cliente novo)
- **Cardinalidade Máxima**: Cliente pode ter ilimitados agendamentos

**Atributos do Relacionamento**: Não há atributos específicos do relacionamento

### Relacionamento Barbeiro ↔ Agendamento

**Características**:
- **Obrigatoriedade**: Agendamento DEVE ter barbeiro (participação total)
- **Cardinalidade Mínima**: Barbeiro pode ter 0 agendamentos (barbeiro novo)
- **Cardinalidade Máxima**: Barbeiro pode ter ilimitados agendamentos

**Atributos do Relacionamento**: Não há atributos específicos do relacionamento

### Relacionamento Agendamento ↔ Serviço (N:M)

**Características**:
- **Obrigatoriedade**: Agendamento DEVE ter pelo menos 1 serviço
- **Cardinalidade**: Agendamento pode ter múltiplos serviços; Serviço pode aparecer em múltiplos agendamentos

**Atributos do Relacionamento**:
- **Quantidade**: Quantas vezes o serviço é aplicado no agendamento
- **Preço Unitário**: Preço praticado na data do agendamento (pode diferir do preço padrão)
- **Observações**: Comentários específicos sobre o serviço no contexto do agendamento

## 💡 Decisões de Modelagem

### Por que Relacionamento N:M entre Agendamento e Serviço?

**Justificativa**: Na vida real, é comum que um cliente solicite múltiplos serviços em uma visita (exemplo: corte + barba + sobrancelha). Esta modelagem permite:

1. **Flexibilidade**: Cliente pode escolher quantos serviços quiser
2. **Precificação Diferenciada**: Preços promocionais ou descontos podem ser aplicados
3. **Controle de Quantidade**: Alguns serviços podem ser repetidos (exemplo: 2 reflexos)
4. **Histórico Detalhado**: Saber exatamente quais serviços foram prestados

### Por que Não Criar Entidade "Funcionário"?

**Justificativa**: Para este exemplo educacional, mantivemos apenas "Barbeiro" por simplicidade. Em um sistema real, poderíamos ter uma hierarquia:
- FUNCIONÁRIO (entidade pai): dados gerais
- BARBEIRO (especialização): dados específicos
- RECEPCIONISTA (especialização): outros dados específicos

### Por que Armazenar Preço no Relacionamento?

**Justificativa**: Os preços podem mudar ao longo do tempo. Armazenar o preço praticado no momento do agendamento:
1. Mantém histórico financeiro correto
2. Permite promoções específicas
3. Evita recalcular valores históricos
4. Facilita relatórios de receita

## 🔍 Validação do Modelo Conceitual

### Checklist de Completude
- ✅ Todas as entidades necessárias identificadas
- ✅ Relacionamentos mapeados corretamente  
- ✅ Atributos essenciais definidos
- ✅ Regras de negócio representadas
- ✅ Cardinalidades justificadas

### Checklist de Consistência
- ✅ Nomenclatura padronizada
- ✅ Relacionamentos coerentes com a realidade
- ✅ Não há redundâncias desnecessárias
- ✅ Modelo é compreensível

### Cenários de Teste Conceitual

1. **Cenário 1**: Cliente João agenda corte + barba com barbeiro Pedro
   - ✅ Modelo suporta: Relacionamento N:M permite múltiplos serviços

2. **Cenário 2**: Barbeiro Pedro tem agenda lotada na segunda-feira
   - ✅ Modelo suporta: 1:N permite múltiplos agendamentos por barbeiro

3. **Cenário 3**: Serviço "Corte Masculino" é solicitado por vários clientes
   - ✅ Modelo suporta: N:M permite serviço aparecer em múltiplos agendamentos

4. **Cenário 4**: Cliente cancela agendamento
   - ✅ Modelo suporta: Atributo "status" do agendamento

---

*Este modelo conceitual serve como base para o desenvolvimento dos modelos lógico e físico, garantindo que todas as necessidades de negócio sejam atendidas de forma consistente e escalável.*