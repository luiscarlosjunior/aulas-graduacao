import album.Album;
import album.Playlist;
import album.Historico;
import conteudo.Audiobook;
import conteudo.Musica;
import conteudo.Podcast;
import pagamento.Assinatura;
import pagamento.Pagamento;
import recomendacao.Avaliacao;
import recomendacao.Comentario;
import recomendacao.Genero;
import recomendacao.Recomendacao;
import usuario.Apresentador;
import usuario.Artista;
import usuario.Narrador;
import usuario.UsuarioFamilia;
import usuario.UsuarioGratuito;
import usuario.UsuarioPremium;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando aplicativo de streaming de áudio");

        // Criando usuários
        UsuarioGratuito usuarioGratuito = new UsuarioGratuito(1, "João", "joao@email.com", true);
        UsuarioPremium usuarioPremium = new UsuarioPremium(2, "Maria", "maria@email.com", 19.90);
        UsuarioFamilia usuarioFamilia = new UsuarioFamilia(3, "Família Silva", "familia@email.com", 4);

        // Criando artistas, narradores e apresentadores
        Artista artista = new Artista(4, "Roberto Carlos", "roberto@email.com", "MPB");
        Narrador narrador = new Narrador(5, "Carlos Alberto", "carlos@email.com", "Dramático");
        Apresentador apresentador = new Apresentador(6, "Ana Paula", "ana@email.com", "Tech Talk");

        // Exibindo informações dos usuários
        System.out.println("\n--- Informações dos Usuários ---");
        usuarioGratuito.exibirInfo();
        usuarioPremium.exibirInfo();
        usuarioFamilia.exibirInfo();
        artista.exibirInfo();
        narrador.exibirInfo();
        apresentador.exibirInfo();

        // Criando álbuns
        Album album1 = new Album("Grandes Sucessos", 2022);
        Album album2 = new Album("Novidades", 2023);

        // Criando conteúdos
        Musica musica = new Musica(1, "Emoções", 4.5, artista, album1);
        Podcast podcast = new Podcast(2, "Tecnologia Hoje", 45.0, apresentador, 10);
        Audiobook audiobook = new Audiobook(3, "O Poder do Hábito", 360.0, narrador, 12);

        // Adicionando músicas ao álbum2
        album2.addMusica("Lançamento 1");
        album2.addMusica("Lançamento 2");

        // Manipulando conteúdos
        System.out.println("\n--- Manipulação de Conteúdos ---");
        musica.tocar();
        podcast.ouvir();
        audiobook.ouvir();

        // Criando e manipulando playlists
        System.out.println("\n--- Playlists e Histórico ---");
        Playlist playlist = new Playlist(apresentador, 5);
        playlist.reproduzir();

        Historico historico = new Historico("Músicas ouvidas recentemente");
        historico.exibir();

        // Adicionando músicas ao álbum
        album1.addMusica("Detalhes");
        album1.addMusica("Como é grande o meu amor por você");

        // Exibindo informações dos álbuns
        System.out.println("\n--- Informações dos Álbuns ---");
        album1.exibirInfo();
        album2.exibirInfo();

        // Processando pagamentos e assinaturas
        System.out.println("\n--- Pagamentos e Assinaturas ---");
        Pagamento pagamento = new Pagamento("Cartão de Crédito", 19.90);
        pagamento.processar();

        Assinatura assinatura = new Assinatura();
        assinatura.setTipo("Premium");
        assinatura.setPreco(19.90);
        assinatura.exibir();

        // Avaliações e comentários
        System.out.println("\n--- Avaliações e Comentários ---");
        Avaliacao avaliacao = new Avaliacao("Ótima música!", 5);
        avaliacao.avaliar();

        Comentario comentario = new Comentario("Adorei esse podcast!", usuarioPremium);
        System.out.println("Comentário de " + comentario.getUsuario().getNome() + ": " + comentario.getTexto());

        // Gêneros e recomendações
        Genero genero = new Genero("Rock");
        System.out.println("Gênero criado: " + genero.getNome());
        Recomendacao recomendacao = new Recomendacao("Baseado nos seus gostos musicais");
        recomendacao.sugerir();

        // Demonstração de funcionalidades específicas
        System.out.println("\n--- Funcionalidades Específicas ---");
        usuarioPremium.baixar("Música: Emoções");
        usuarioGratuito.ouvirAnuncio();
        usuarioFamilia.adicionarMembro();
        artista.publicarConteudo();
        narrador.narrar();
        apresentador.apresentar();

        System.out.println("\nAplicativo encerrado com sucesso!");
    }
}