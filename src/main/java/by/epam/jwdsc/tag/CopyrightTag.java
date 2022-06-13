package by.epam.jwdsc.tag;


import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

/**
 * The type Copyright tag.
 */
public class CopyrightTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        String copyrightSign = "<span>&#169; Copyright</span>";
        try {
            JspWriter out = pageContext.getOut();
            out.print(copyrightSign);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
