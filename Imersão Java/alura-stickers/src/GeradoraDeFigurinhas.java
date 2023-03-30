import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    public void criar(InputStream inputStream, String nomeArquivo) throws IOException {

        //Leitura da imagem

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //Criar nova imagem (em memória) com transparência
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();

        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //Copiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //Configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.orange);
        graphics.setFont(fonte);

        //Escrever uma frase na nova imagem
        String textoFigurinha = "Bora assitir!";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo =  fontMetrics.getStringBounds(textoFigurinha, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;
        int posicaoTextoY = novaAltura - 100;
        graphics.drawString(textoFigurinha, posicaoTextoX, posicaoTextoY);

        //Outline da frase
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(textoFigurinha, fonte, fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform =  graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        //Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }
}
