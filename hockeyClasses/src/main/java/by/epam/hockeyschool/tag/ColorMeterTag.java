package by.epam.hockeyschool.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Dmitry Olifer on 29.07.2015.
 */
public class ColorMeterTag extends TagSupport {

    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<b style=\"font-size: x-small\">" + value + "</b>&nbsp;");
            out.write("<meter min=\"0\" low=\"50\" high=\"100\" max=\"200\" optimum=\"200\" value=\"" + value + "\"></meter>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}