package ru.aleksandrov.Util;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RunnerServlet", urlPatterns = "/RunnerServlet", loadOnStartup = 0)
public class RunnerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void init(){
        RunSqlScript run = new RunSqlScript();
        ServletContext sc = getServletContext();
        Settings settings = Settings.getSettings();
        String fullPath = sc.getRealPath("/WEB-INF/classes/" + settings.value("createFile"));
        run.scriptRunning(fullPath);
    }
}
