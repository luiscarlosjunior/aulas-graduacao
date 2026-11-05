/**
 * ============================================================================
 *  QUIZ UML — História da modelagem, Casos de Uso, Classes, Objetos, Sequência
 * ============================================================================
 *
 *  COMO USAR:
 *   1. Acesse https://script.google.com  →  Novo projeto.
 *   2. Apague o conteúdo padrão e cole ESTE arquivo inteiro.
 *   3. Clique em "Executar" na função `criarFormularioUML`.
 *      (Na primeira vez o Google pede autorização — aceite com sua conta.)
 *   4. No menu "Execução" / "Logs" (Ctrl+Enter) aparecem os links do formulário:
 *         • Editar:    para abrir e revisar o quiz
 *         • Responder: para enviar aos alunos
 *
 *  CARACTERÍSTICAS:
 *   • 30 questões, 6 por tema, cada uma com um CONTEXTO em forma de diálogo
 *     (professor-aluno, aluno-aluno, sênior-júnior, gerente-cliente...).
 *   • 5 alternativas, apenas 1 correta.
 *   • A posição da correta é CONTROLADA (padrão balanceado, 6 de cada A–E,
 *     nunca duas iguais seguidas) → sem "vários A em sequência".
 *   • 1 ponto por questão (modo quiz).
 *   • Cada questão leva um feedback com a EXPLICAÇÃO da resposta.
 * ============================================================================
 */

function criarFormularioUML() {
  var form = FormApp.create('Quiz UML — História, Casos de Uso, Classes, Objetos e Sequência');

  form.setIsQuiz(true)
      .setDescription(
        'Responda pensando em SITUAÇÕES REAIS de projeto. Cada questão traz um ' +
        'diálogo de contexto. São 30 questões (1 ponto cada), uma só alternativa correta. ' +
        'Boa prova! 🎧')
      .setProgressBar(true)
      .setAllowResponseEdits(false)
      .setCollectEmail(true);

  // --------------------------------------------------------------------------
  // Posições CONTROLADAS da alternativa correta (0 = A, 1 = B, ... 4 = E).
  // Padrão balanceado: 6 de cada letra, SEM duas iguais consecutivas.
  // --------------------------------------------------------------------------
  var posicoes = [
    0, 1, 2, 3, 4,
    1, 2, 3, 4, 0,
    2, 3, 4, 0, 1,
    3, 4, 0, 1, 2,
    4, 0, 1, 2, 3,
    0, 1, 2, 3, 4
  ];

  if (posicoes.length !== PERGUNTAS.length) {
    throw new Error('posicoes (' + posicoes.length + ') e PERGUNTAS (' +
                    PERGUNTAS.length + ') precisam ter o mesmo tamanho.');
  }

  PERGUNTAS.forEach(function (q, i) {
    if (q.distratores.length !== 4) {
      throw new Error('A questão ' + (i + 1) + ' precisa ter exatamente 4 distratores.');
    }

    var item = form.addMultipleChoiceItem();

    // Insere a alternativa correta na posição controlada, entre os distratores.
    var opcoes = q.distratores.slice();
    opcoes.splice(posicoes[i], 0, q.correta);

    var choices = opcoes.map(function (texto) {
      return item.createChoice(texto, texto === q.correta);
    });

    item.setTitle((i + 1) + ') ' + q.enunciado)
        .setChoices(choices)
        .setPoints(1)
        .setRequired(true);

    if (q.explicacao) {
      var fb = FormApp.createFeedback().setText(q.explicacao).build();
      item.setFeedbackForCorrect(fb);
      item.setFeedbackForIncorrect(fb);
    }
  });

  Logger.log('✅ Formulário criado com ' + PERGUNTAS.length + ' questões.');
  Logger.log('✏️  Editar:    ' + form.getEditUrl());
  Logger.log('📨 Responder:  ' + form.getPublishedUrl());
}

/* ===========================================================================
 *  BANCO DE QUESTÕES
 *  - enunciado: contexto (diálogo) + a pergunta
 *  - correta:   a ÚNICA alternativa correta
 *  - distratores: 4 alternativas erradas (mas plausíveis)
 *  - explicacao: feedback mostrado após responder
 * =========================================================================== */
var PERGUNTAS = [

  /* ---------------------- HISTÓRIA DA MODELAGEM (1–6) ---------------------- */

  {
    enunciado:
      '🎓 Professor → turma:\n' +
      '“Antes de 1997, cada autor tinha sua própria notação — Booch, OMT (Rumbaugh) e ' +
      'OOSE (Jacobson). Os times perdiam tempo só decidindo COMO desenhar.”\n\n' +
      'Qual foi a principal contribuição que a UML trouxe nesse cenário?',
    correta: 'Unificar as notações concorrentes numa única linguagem-padrão de modelagem.',
    distratores: [
      'Criar uma nova linguagem de programação para substituir C++ e Java.',
      'Definir um processo de desenvolvimento obrigatório para todas as empresas.',
      'Gerar código automaticamente a partir de qualquer desenho feito à mão.',
      'Obrigar todo projeto a usar apenas o diagrama de classes.'
    ],
    explicacao:
      'UML (Unified Modeling Language) nasceu justamente para UNIFICAR as notações rivais ' +
      'dos anos 90 numa linguagem comum. Ela é notação, não linguagem de programação nem processo.'
  },

  {
    enunciado:
      '👥 Aluno → aluno:\n' +
      '“Ouvi que a UML foi criada pelos ‘três amigos’. Quem são eles?”\n\n' +
      'Assinale a alternativa correta.',
    correta: 'Grady Booch, James Rumbaugh e Ivar Jacobson.',
    distratores: [
      'Dennis Ritchie, Ken Thompson e Brian Kernighan.',
      'James Gosling, Guido van Rossum e Bjarne Stroustrup.',
      'Martin Fowler, Kent Beck e Robert C. Martin.',
      'Alan Kay, Edsger Dijkstra e Donald Knuth.'
    ],
    explicacao:
      'Os “três amigos” são Booch, Rumbaugh e Jacobson — cada um trazia um método próprio ' +
      '(Booch, OMT e OOSE) que convergiu na UML.'
  },

  {
    enunciado:
      '🧑‍💼 Júnior → sênior:\n' +
      '“Quem é o ‘dono’ da UML hoje? Quem decide o que entra numa nova versão?”\n\n' +
      'Qual a resposta correta?',
    correta: 'A OMG (Object Management Group), que mantém e padroniza a especificação.',
    distratores: [
      'A Oracle, dona do Java.',
      'A Microsoft, junto com o Visual Studio.',
      'A W3C, a mesma do HTML e CSS.',
      'Ninguém: a UML não tem mantenedor oficial.'
    ],
    explicacao:
      'A OMG mantém a especificação da UML (posteriormente também adotada como padrão ISO/IEC). ' +
      'Não é propriedade de uma fabricante de linguagem ou IDE.'
  },

  {
    enunciado:
      '🧑‍🏫 Sênior → júnior:\n' +
      '“Você disse que vai ‘seguir a UML’ para organizar as etapas do projeto. Cuidado com essa frase.”\n\n' +
      'Por que o sênior corrigiu?',
    correta: 'Porque a UML é uma linguagem de modelagem (notação), não um processo/metodologia.',
    distratores: [
      'Porque a UML só pode ser usada com a linguagem Java.',
      'Porque a UML foi descontinuada e ninguém mais usa.',
      'Porque a UML substitui a necessidade de escrever código.',
      'Porque a UML é um framework ágil que proíbe documentação.'
    ],
    explicacao:
      'UML descreve O QUE desenhar (notação). O PROCESSO (como conduzir o projeto) vem de ' +
      'metodologias como RUP, Scrum, XP — que podem usar UML, mas não se confundem com ela.'
  },

  {
    enunciado:
      '👩‍🏫 Professora → turma:\n' +
      '“Posso modelar em UML um sistema que será escrito em Python, Java OU C#?”\n\n' +
      'Qual afirmação está correta?',
    correta: 'Sim — a UML é independente de linguagem; os conceitos valem para qualquer linguagem OO.',
    distratores: [
      'Não, a UML só funciona para Java.',
      'Sim, mas só se o código for gerado automaticamente pela ferramenta.',
      'Não, cada linguagem tem sua própria UML incompatível.',
      'Sim, mas apenas para linguagens funcionais como Haskell.'
    ],
    explicacao:
      'A UML modela conceitos de orientação a objetos (classes, associações, mensagens) de forma ' +
      'independente da linguagem de implementação.'
  },

  {
    enunciado:
      '🎓 Professor:\n' +
      '“O conceito de ‘caso de uso’ não nasceu com a UML — veio de um dos três amigos, no método OOSE.”\n\n' +
      'De quem foi essa contribuição?',
    correta: 'De Ivar Jacobson.',
    distratores: [
      'De Grady Booch.',
      'De James Rumbaugh.',
      'De Edsger Dijkstra.',
      'De Alan Turing.'
    ],
    explicacao:
      'Jacobson introduziu os casos de uso no método OOSE, conceito depois incorporado à UML.'
  },

  /* ----------------------- DIAGRAMA DE CASOS DE USO (7–12) ----------------- */

  {
    enunciado:
      '💼 Gerente → cliente (reunião inicial do app de streaming “Melodia”):\n' +
      '“Preciso te mostrar, numa página, TUDO que o sistema vai fazer e quem usa cada coisa, sem jargão técnico.”\n\n' +
      'Qual diagrama ele deve levar?',
    correta: 'Diagrama de casos de uso.',
    distratores: [
      'Diagrama de sequência.',
      'Diagrama de classes.',
      'Diagrama de objetos.',
      'Diagrama de implantação.'
    ],
    explicacao:
      'Casos de uso são a visão mais externa e de alto nível — ideal para alinhar ESCOPO com o ' +
      'cliente sem jargão técnico.'
  },

  {
    enunciado:
      '🧑‍🏫 Sênior → júnior:\n' +
      '“No Melodia, tanto ‘Reproduzir música’ quanto ‘Assinar plano’ exigem SEMPRE ‘Autenticar’ antes.”\n\n' +
      'Que relacionamento representa isso?',
    correta: '«include» (inclusão obrigatória, sempre acontece).',
    distratores: [
      '«extend» (extensão condicional, só às vezes).',
      'Generalização de ator.',
      'Composição.',
      'Dependência entre pacotes.'
    ],
    explicacao:
      '«include» é para comportamento SEMPRE executado e reaproveitado por vários casos — exatamente ' +
      'o caso de “Autenticar”.'
  },

  {
    enunciado:
      '👥 Aluno → aluno:\n' +
      '“No Melodia a cobrança mensal dispara sozinha todo dia 1º, sem ninguém clicar. Então esse caso de uso não tem ator?”\n\n' +
      'Qual a resposta correta?',
    correta: 'Tem sim: um ator de tempo (um agendador/sistema externo) que dispara o caso de uso.',
    distratores: [
      'Não, casos de uso sem pessoa não têm ator.',
      'O ator é obrigatoriamente o programador do sistema.',
      'Nesse caso não é um caso de uso, é uma classe.',
      'O ator é o banco de dados que guarda as cobranças.'
    ],
    explicacao:
      'Ator é um PAPEL que interage com o sistema — pode ser pessoa, outro sistema ou o TEMPO ' +
      '(um agendador que dispara sozinho).'
  },

  {
    enunciado:
      '🧑‍🏫 Professor corrige um aluno que listou como “casos de uso”:\n' +
      '“Clicar no botão Login”, “Abrir menu”, “Digitar senha”.\n\n' +
      'Qual item é um caso de uso de verdade?',
    correta: '“Autenticar” — representa um objetivo do usuário, não um passo de interface.',
    distratores: [
      '“Clicar no botão Login”.',
      '“Abrir o menu lateral”.',
      '“Digitar a senha no campo”.',
      '“Rolar a página para baixo”.'
    ],
    explicacao:
      'Caso de uso é um OBJETIVO do usuário (Autenticar), não um clique/passo de tela. Confundir ' +
      'os dois é o erro mais comum.'
  },

  {
    enunciado:
      '🧑‍💼 Júnior → sênior:\n' +
      '“Quando a cobrança falha por falta de saldo, às vezes a assinatura é suspensa — mas só às vezes.”\n\n' +
      'Como modelar “Suspender assinatura” em relação a “Cobrar assinatura”?',
    correta: 'Com «extend», pois é um comportamento condicional/opcional sobre o caso base.',
    distratores: [
      'Com «include», pois acontece sempre.',
      'Como um ator novo do sistema.',
      'Como herança entre classes.',
      'Como uma mensagem assíncrona.'
    ],
    explicacao:
      '«extend» modela comportamento que ocorre SÓ SOB CONDIÇÃO (saldo insuficiente), sem poluir ' +
      'o fluxo principal de “Cobrar assinatura”.'
  },

  {
    enunciado:
      '👥 Aluno A → Aluno B:\n' +
      '“Vou detalhar no caso de uso cada laço e cada if do algoritmo de busca.” Aluno B discorda.\n\n' +
      'Quem está certo e por quê?',
    correta: 'Aluno B: casos de uso dizem O QUÊ o sistema faz (objetivo), não COMO (lógica interna).',
    distratores: [
      'Aluno A: o caso de uso deve conter o algoritmo completo.',
      'Aluno A: cada if do algoritmo vira um ator.',
      'Aluno B: porque casos de uso não podem ter texto descritivo.',
      'Os dois erram: casos de uso mostram a ordem das chamadas de método.'
    ],
    explicacao:
      'Casos de uso ficam no nível do O QUÊ. Laços e ifs (o COMO) vão para diagramas de atividades ' +
      'ou de sequência.'
  },

  /* ------------------------- DIAGRAMA DE CLASSES (13–18) ------------------- */

  {
    enunciado:
      '👩‍🏫 Professora:\n' +
      '“Cada retângulo do diagrama de classes tem três faixas. O que representam, de cima para baixo?”\n\n' +
      'Assinale a correta.',
    correta: 'Nome da classe, atributos e operações (métodos).',
    distratores: [
      'Nome, construtor e destrutor.',
      'Entrada, processamento e saída.',
      'Classe, objeto e instância.',
      'Público, privado e protegido.'
    ],
    explicacao:
      'Os três compartimentos são: NOME / ATRIBUTOS / OPERAÇÕES — a tradução quase direta para ' +
      'uma classe em código.'
  },

  {
    enunciado:
      '🧑‍🏫 Sênior → júnior (modelando o Melodia):\n' +
      '“As faixas de um Álbum deixam de existir se o álbum é apagado. Mas uma música numa Playlist ' +
      'continua existindo se a playlist some.”\n\n' +
      'Como modelar cada caso?',
    correta: 'Álbum–Música: composição (losango cheio ◆); Playlist–Música: agregação (losango vazio ◇).',
    distratores: [
      'Os dois casos como herança.',
      'Álbum–Música: agregação; Playlist–Música: composição.',
      'Os dois como dependência (- ->).',
      'Os dois como associação simples, sem distinção.'
    ],
    explicacao:
      'Composição = a parte MORRE com o todo (faixas do álbum). Agregação = a parte SOBREVIVE ' +
      '(música continua existindo fora da playlist).'
  },

  {
    enunciado:
      '👥 Aluno → aluno:\n' +
      '“No Melodia, Ouvinte e Artista compartilham nome e e-mail de Usuario, que é abstrata.”\n\n' +
      'Qual relacionamento liga Ouvinte a Usuario?',
    correta: 'Generalização/herança (Ouvinte É-UM Usuario).',
    distratores: [
      'Composição.',
      'Agregação.',
      'Associação muitos-para-muitos.',
      'Dependência temporária.'
    ],
    explicacao:
      'Quando o filho É-UM tipo do pai e herda seus membros, o relacionamento é generalização ' +
      '(herança), desenhada com o triângulo vazado.'
  },

  {
    enunciado:
      '🧑‍🏫 Professor:\n' +
      '“No diagrama aparece Ouvinte \"1\" --> \"0..*\" Playlist.”\n\n' +
      'Como se lê essa multiplicidade?',
    correta: 'Um ouvinte pode ter zero ou muitas playlists.',
    distratores: [
      'Toda playlist tem exatamente um ouvinte e vice-versa.',
      'Um ouvinte tem obrigatoriamente muitas playlists (no mínimo uma).',
      'Playlists sempre existem sem nenhum ouvinte.',
      'Um ouvinte tem no máximo uma playlist.'
    ],
    explicacao:
      '“0..*” do lado da Playlist significa de zero a muitas. O “1” do lado do Ouvinte indica ' +
      'que cada playlist pertence a um ouvinte.'
  },

  {
    enunciado:
      '🧑‍💼 Júnior → sênior:\n' +
      '“Vi uma classe com o nome em itálico e um método marcado com «abstract». O que significa?”\n\n' +
      'Assinale a correta.',
    correta: 'É uma classe abstrata com um método abstrato, que as subclasses devem implementar.',
    distratores: [
      'É uma classe depreciada que será removida.',
      'É uma classe final que não pode ter filhas.',
      'É apenas uma convenção de cor, sem significado.',
      'Indica que aquilo é um objeto, não uma classe.'
    ],
    explicacao:
      'Itálico (ou «abstract») marca classe/método abstrato — sem implementação própria, ' +
      'concretizado nas subclasses (base do polimorfismo).'
  },

  {
    enunciado:
      '🧑‍🏫 Sênior → júnior:\n' +
      '“No diagrama de classes, qual relacionamento vira a palavra-chave extends no Java?”\n\n' +
      'Assinale a correta.',
    correta: 'A generalização (seta de triângulo vazado ▷).',
    distratores: [
      'A composição (losango cheio ◆).',
      'A associação simples (linha).',
      'A dependência (linha tracejada - ->).',
      'A multiplicidade 0..*.'
    ],
    explicacao:
      'Generalização ↔ extends. Composição/agregação viram ATRIBUTOS (campos), e dependência ' +
      'vira um uso pontual (parâmetro/variável local).'
  },

  /* ------------------------- DIAGRAMA DE OBJETOS (19–24) ------------------- */

  {
    enunciado:
      '👩‍🏫 Professora:\n' +
      '“Se o diagrama de classes é a planta da casa, o diagrama de objetos é o quê?”\n\n' +
      'Assinale a melhor analogia.',
    correta: 'Uma fotografia/instantâneo do sistema num momento específico, com objetos concretos.',
    distratores: [
      'A linha do tempo das mensagens trocadas entre objetos.',
      'A lista de funcionalidades apresentada ao cliente.',
      'O código-fonte já compilado.',
      'O histórico de versões (commits) do projeto.'
    ],
    explicacao:
      'O diagrama de objetos é um SNAPSHOT: mostra instâncias concretas e seus valores num ' +
      'instante — não a estrutura genérica nem o tempo.'
  },

  {
    enunciado:
      '👥 Aluno A → Aluno B:\n' +
      '“Qual a diferença, na prática, entre o diagrama de CLASSES e o de OBJETOS?”\n\n' +
      'Assinale a correta.',
    correta: 'Classes mostram tipos e relações genéricas; objetos mostram instâncias concretas com valores (ex.: ana:Ouvinte, nome="Ana").',
    distratores: [
      'Nenhuma: são sinônimos.',
      'O de objetos mostra o tempo; o de classes, o espaço físico.',
      'O de classes é para o cliente; o de objetos, para o gerente.',
      'O de objetos substitui o de classes em projetos grandes.'
    ],
    explicacao:
      'Classe = o molde (tipo). Objeto = uma instância concreta com valores. O diagrama de objetos ' +
      'é um exemplo vivo do diagrama de classes.'
  },

  {
    enunciado:
      '🧑‍🏫 Sênior → júnior:\n' +
      '“No diagrama de objetos, por que escrevemos ana : Ouvinte com o nome sublinhado?”\n\n' +
      'Assinale a correta.',
    correta: 'Porque representa uma instância (objeto) “ana” do tipo Ouvinte — o sublinhado é a convenção para objetos.',
    distratores: [
      'Porque “ana” é uma classe abstrata.',
      'Porque o sublinhado indica atributo privado.',
      'Porque é um método estático.',
      'Porque marca um erro a ser corrigido no modelo.'
    ],
    explicacao:
      'A notação objeto:Classe sublinhada distingue INSTÂNCIAS (diagrama de objetos) de CLASSES ' +
      '(diagrama de classes).'
  },

  {
    enunciado:
      '🧑‍💼 Júnior → sênior:\n' +
      '“O diagrama de classes parece certo, mas não consigo imaginar se as multiplicidades fazem sentido.”\n\n' +
      'Que diagrama ajuda a VALIDAR isso com um caso concreto?',
    correta: 'O diagrama de objetos — monta um cenário real e confere se bate com o de classes.',
    distratores: [
      'O diagrama de implantação.',
      'O diagrama de casos de uso.',
      'O diagrama de componentes.',
      'Nenhum: multiplicidade não se valida com exemplos.'
    ],
    explicacao:
      'Instanciar um cenário concreto (o diagrama de objetos) é a forma clássica de TESTAR se o ' +
      'diagrama de classes e suas multiplicidades fazem sentido.'
  },

  {
    enunciado:
      '👥 Aluno → aluno:\n' +
      '“No diagrama de classes, Playlist tem 0..* músicas. E a playlist ‘Treino’ da Ana, com exatamente 3 músicas, no diagrama de objetos?”\n\n' +
      'Como fica?',
    correta: 'Aparece o objeto treino:Playlist ligado a três objetos :Musica — um exemplo válido do 0..*.',
    distratores: [
      'Não pode: objeto tem que ter 0 músicas.',
      'Vira uma nova classe chamada PlaylistTreino.',
      'O 0..* obrigatoriamente vira 3..3 no diagrama de classes.',
      'Playlists não aparecem em diagramas de objetos.'
    ],
    explicacao:
      'Um objeto concreto apenas MATERIALIZA a multiplicidade: 3 músicas é um valor válido dentro ' +
      'do intervalo 0..* definido nas classes.'
  },

  {
    enunciado:
      '🧑‍🏫 Professor:\n' +
      '“Um aluno quis mostrar a ORDEM em que os métodos são chamados usando o diagrama de objetos.”\n\n' +
      'Por que isso não é o ideal?',
    correta: 'Porque o diagrama de objetos é estático (um instantâneo); ordem/tempo é papel do diagrama de sequência.',
    distratores: [
      'Porque o diagrama de objetos não pode ter mais de dois objetos.',
      'Porque ordem de chamadas vai no diagrama de classes.',
      'Porque objetos não possuem métodos.',
      'Porque isso é tarefa do diagrama de casos de uso.'
    ],
    explicacao:
      'Objetos = foto parada. Para mostrar a ORDEM das mensagens no tempo, use o diagrama de ' +
      'sequência (comportamental).'
  },

  /* ------------------------ DIAGRAMA DE SEQUÊNCIA (25–30) ------------------ */

  {
    enunciado:
      '👩‍🏫 Professora:\n' +
      '“No diagrama de sequência, o que significa a posição VERTICAL de uma mensagem?”\n\n' +
      'Assinale a correta.',
    correta: 'O tempo: mensagens mais acima acontecem antes das mais abaixo.',
    distratores: [
      'A importância: quanto mais acima, mais crítica.',
      'A visibilidade: acima é público, abaixo é privado.',
      'Nada; a ordem vertical é apenas estética.',
      'O número de vezes que a mensagem se repete.'
    ],
    explicacao:
      'No diagrama de sequência o eixo vertical é o TEMPO (de cima para baixo). O horizontal são ' +
      'os participantes.'
  },

  {
    enunciado:
      '🧑‍🏫 Sênior → júnior (fluxo “Assinar Premium”):\n' +
      '“Preciso mostrar os dois caminhos que se excluem: saldo suficiente E saldo insuficiente.”\n\n' +
      'Qual fragmento usar?',
    correta: 'alt (alternativa — equivale a if/else).',
    distratores: [
      'loop (repetição).',
      'opt (opcional — if sem else).',
      'par (execução paralela).',
      'ref (referência a outro diagrama).'
    ],
    explicacao:
      'alt mostra caminhos MUTUAMENTE EXCLUSIVOS (if/else). opt seria para algo que só às vezes ' +
      'acontece, sem o “senão”.'
  },

  {
    enunciado:
      '🧑‍💼 Júnior → sênior:\n' +
      '“Ao reproduzir uma faixa, preciso creditar royalty para CADA artista dela.”\n\n' +
      'Como represento isso no diagrama de sequência?',
    correta: 'Com um fragmento loop (para cada artista).',
    distratores: [
      'Com alt (if/else).',
      'Com break (saída antecipada).',
      'Com uma única auto-mensagem.',
      'Com um ator de tempo.'
    ],
    explicacao:
      'loop representa repetição — o equivalente ao for/while (ex.: for (Artista a : artistas) ' +
      'creditarRoyalty(...)).'
  },

  {
    enunciado:
      '👥 Aluno → aluno:\n' +
      '“Qual a diferença entre a seta cheia (->>) e a seta aberta (-)) no diagrama de sequência?”\n\n' +
      'Assinale a correta.',
    correta: 'A cheia é síncrona (quem chama espera a resposta); a aberta é assíncrona (dispara e segue).',
    distratores: [
      'A cheia é pública; a aberta, privada.',
      'A cheia cria um objeto; a aberta o destrói.',
      'Não há diferença, é só estilo visual.',
      'A cheia é o retorno; a aberta é a chamada.'
    ],
    explicacao:
      'Síncrona = chama e ESPERA (a maioria dos métodos Java). Assíncrona = “dispara e segue” ' +
      '(filas, eventos, webhooks).'
  },

  {
    enunciado:
      '🧑‍🏫 Sênior → júnior:\n' +
      '“No reproduzir(), se a música não está no catálogo, o método devolve o erro na hora e não executa mais nada.”\n\n' +
      'Que fragmento modela essa saída antecipada?',
    correta: 'break.',
    distratores: [
      'opt.',
      'par.',
      'loop.',
      'alt com dois ramos idênticos.'
    ],
    explicacao:
      'break é a “saída de emergência”: a condição bate, o cenário encerra ali — como um return/throw ' +
      'no começo de um método.'
  },

  {
    enunciado:
      '💼 Gerente técnico → time:\n' +
      '“O sub-fluxo de ‘Autenticar’ aparece em 5 diagramas diferentes. Não quero redesenhá-lo toda vez.”\n\n' +
      'Qual recurso do diagrama de sequência resolve isso?',
    correta: 'ref — uma moldura que referencia outro diagrama (sub-fluxo) já desenhado.',
    distratores: [
      'loop, repetindo o login a cada vez.',
      'par, para autenticar em paralelo.',
      'Copiar e colar as mesmas setas em todos os diagramas.',
      'break, para pular a etapa de login.'
    ],
    explicacao:
      'ref aponta para um sub-fluxo desenhado UMA vez em outro diagrama — reúso sem cópia, no ' +
      'mesmo espírito do «include» dos casos de uso.'
  }

];
