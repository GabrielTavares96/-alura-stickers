import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem
//        InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
//        InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria uma nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova imagem(em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // esrever uma frase na nova imagem
        String texto = "TOPZERA";
        graphics.drawString(texto,
                (novaImagem.getWidth() / 2) - (graphics.getFontMetrics().stringWidth(texto) / 2),
                novaImagem.getHeight() - 100);


        // Cria o dir se nao existir
        File fileNewToWrite = new File(nomeArquivo);
        fileNewToWrite.mkdirs();

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", fileNewToWrite);

    }

}
