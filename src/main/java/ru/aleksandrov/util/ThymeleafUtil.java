package ru.aleksandrov.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;

public class ThymeleafUtil {
    private static TemplateEngine templateEngine = null;

    private ThymeleafUtil(ServletContext context) {
        ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver(context);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setCacheable(true);
        resolver.setCacheTTLMs(600000L);
        resolver.setCharacterEncoding("UTF-8");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
    }

    public static TemplateEngine getTemplateEngine(ServletContext context) {
        if (templateEngine == null) {
            new ThymeleafUtil(context);
        }
        return templateEngine;
    }

}
