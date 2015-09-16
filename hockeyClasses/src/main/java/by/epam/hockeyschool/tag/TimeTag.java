package by.epam.hockeyschool.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Dmitry Olifer on 10.07.2015.
 *
 * The {@code TimeTag} class represents tag date-time tag
 * without body and attributes.
 */
public class TimeTag extends TagSupport {

    /**
     * Defines the code which performs during the request when
     * the starting tag was found.
     * @return  an integer constant value {@code SKIP_BODY} that ignores
     *          all the information between starting and ending tag.
     * @throws  JspException if Java Server Pages' error occurs.
     */
    @Override
    public int doStartTag() throws JspException {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
        String localeStr = (String) pageContext.getSession().getAttribute("locale");
        Locale locale;
        if (localeStr != null) {
            locale = new Locale(localeStr.split("_")[0], localeStr.split("_")[1]);
        }
        else {
            locale = Locale.getDefault();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM, HH:mm", locale);
        String currTime = simpleDateFormat.format(new Date()) + "<br>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(currTime);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    /**
     * Calls right after {@code doStartTag()} method because of integer
     * constant value {@code SKIP_BODY} that returns by {@code doStartTag()}.
     * @return  an integer constant value {@code EVAL_PAGE} that allows to
     *          carry out the following page content.
     */
    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}