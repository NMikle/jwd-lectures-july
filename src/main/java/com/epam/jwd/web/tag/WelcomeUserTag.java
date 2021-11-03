package com.epam.jwd.web.tag;

import com.epam.jwd.web.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

public class WelcomeUserTag extends TagSupport {

    private static final long serialVersionUID = -5020029415387643046L;

    private static final Logger LOGGER = LogManager.getLogger(WelcomeUserTag.class);

    private static final String PARAGRAPH_TAGS = "<p>%s, %s</p>";
    private static final String USER_SESSION_PARAM_NAME = "user";

    private String text;

    @Override
    public int doStartTag() {
        extractUserNameFromSession()
                .ifPresent(name -> printTextToOut(format(PARAGRAPH_TAGS, text, name)));
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private Optional<String> extractUserNameFromSession() {
        return Optional.ofNullable(pageContext.getSession())
                .map(session -> (User) session.getAttribute(USER_SESSION_PARAM_NAME))
                .map(User::getFirstName);
    }

    private void printTextToOut(String text) {
        final JspWriter out = pageContext.getOut();
        try {
            out.write(text);
        } catch (IOException e) {
            LOGGER.error("error occurred writing text to jsp. text: {}, tagId: {}", text, id);
            LOGGER.error(e);
        }
    }

    public void setText(String text) {
        this.text = text;
    }
}
