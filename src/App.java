import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

//        String url = "https://imdb-api.com/en/API/Top250Movies/k_12345678";
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println();
            System.out.println("\u001b[38;2;255;209;0m" + filme.get("title") + "\u001b[0m");
            System.out.println("\u001b[38;2;42;122;228m" + filme.get("image") + "\u001b[0m");
            System.out.print("\u001b[95;1m" + filme.get("imDbRating") + "\u001b[0m ");

            Long estrelas = Math.round(Double.parseDouble(filme.get("imDbRating")));
            for (int i = 0; i < estrelas; i++) {
                System.out.printf("\u2b50");
            }
            System.out.println("\n");
            System.out.println("-----------------------------------------------------------------");
        }

    }
}
