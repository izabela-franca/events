import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP e buscar o Top 10 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //Filtrar apenas os dados que interessam (título, poster e nota)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Criando diretório
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        //Exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            String nomeArquivo = "figurinhas/" + titulo + ".png";

            InputStream inputStream = new URL(urlImagem).openStream();

            var gerador = new GeradoraDeFigurinhas();
            gerador.criar(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}