import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

//        String url = "https://imdb-api.com/en/API/Top250Movies/k_12345678";
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

//        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);


        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);


            System.out.println();
            System.out.println("\u001b[38;2;255;209;0m" + conteudo.getTitulo() + "\u001b[0m");
            System.out.println("-----------------------------------------------------------------");
        }

    }
}
