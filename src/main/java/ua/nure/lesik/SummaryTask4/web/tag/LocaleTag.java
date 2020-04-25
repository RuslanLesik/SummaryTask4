package ua.nure.lesik.SummaryTask4.web.tag;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom tag for support i18n
 *
 * @author Ruslan Lesik
 */
public class LocaleTag extends TagSupport {

    private static final Logger LOG = Logger.getLogger(LocaleTag.class);

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        String language = String.valueOf(session.getAttribute("language"));
        if (value != null) {
            Locale locale = new Locale(language);
            ResourceBundle rb = ResourceBundle.getBundle("resources", locale);
            JspWriter out = pageContext.getOut();
            try {
                out.println(rb.getString(value));
            } catch (IOException e) {
                LOG.error("No value found for such a key - " + value);
            }
        }
        return SKIP_BODY;
    }
}