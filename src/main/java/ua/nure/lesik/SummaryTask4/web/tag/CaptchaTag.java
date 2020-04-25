package ua.nure.lesik.SummaryTask4.web.tag;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Custom tag for generation a new captcha image
 *
 * @author Ruslan Lesik
 */
public class CaptchaTag extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(CaptchaTag.class);

    @Override
    public int doStartTag() throws JspException {

        int width = 140;
        int height = 50;

        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("serif", Font.ITALIC, 22);
        g2d.setFont(font);

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        GradientPaint gp = new GradientPaint(0, 0,
                Color.black, 0, height / 2, Color.red, true);
//                Color.pink, 0, height / 2, Color.GREEN, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(new Color(255, 255, 0));
//        g2d.setColor(new Color(123, 104, 238));

        Random r = new Random();
        char[] data = getCaptcha();
        String captcha = String.copyValueOf(data);
        pageContext.getSession().setAttribute("captcha", captcha);
        int x = 0;
        int y;
        for (int i = 0; i < data.length; i++) {
            x += 10 + (Math.abs(r.nextInt()) % 15);
            y = 20 + Math.abs(r.nextInt()) % 20;
            g2d.drawChars(data, i, 1, x, y);
        }
        g2d.dispose();
        pageContext.getResponse().setContentType("image/png");
        OutputStream os;
        try {
            os = pageContext.getResponse().getOutputStream();
            ImageIO.write(bufferedImage, "png", os);
            os.close();
        } catch (IOException e) {
            LOGGER.warn("Captcha failed!");
        }
        return super.doStartTag();
    }

    private char[] getCaptcha() {
        char data[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9'};
        char index[] = new char[5];
        Random r = new Random();
        for (int i = 0; i < index.length; i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }
        return index;
    }
}