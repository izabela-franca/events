import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP e buscar o Top 10 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        var http = new ClienteHttp();
        String json = http.buscarDados(url);

        //Criando diretório
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        //Exibir e manipular os dados
        ExtratorConteudoImdb extrator = new ExtratorConteudoImdb();
        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (Conteudo conteudo : conteudos) {

            String nomeArquivo = "figurinhas/" + conteudo.getTitulo() + ".png";

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            var gerador = new GeradoraDeFigurinhas();
            gerador.criar(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}