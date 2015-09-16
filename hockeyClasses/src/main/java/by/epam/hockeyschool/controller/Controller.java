package by.epam.hockeyschool.controller;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.exception.DAOException;
import by.epam.hockeyschool.exception.TechnicalException;
import by.epam.hockeyschool.navigation.CommandFactory;
import by.epam.hockeyschool.navigation.commands.Command;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dmitry Olifer on 28.06.2015.
 * <p>
 * The {@code Controller} class represents single servlet used as
 * controller. It involves standard servlet's life-cycle methods
 * such as {@code init()}, {@code doGet()}, {@code doPost()},
 * {@code destroy()}.
 */
@WebServlet(name = "Controller",
        urlPatterns = "/Controller",
        initParams = {@WebInitParam(name = "log4j-pass", value = "/WEB-INF/log4j.properties")})
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    /**
     * Servlet initialization method which performs by server when the servlet's
     * object creates. Used to perform some initializing settings.
     *
     * @param config represents an instance to work with server.
     * @throws ServletException if can not perform inherit {@code init} method.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String log4j = config.getInitParameter("log4j-pass");
        String path = getServletContext().getRealPath(log4j);
        PropertyConfigurator.configure(path);
    }

    /**
     * Carries out http-servlet's request in the case of {@code post} request.
     *
     * @param request  is the http-servlet's request.
     * @param response is the http-sevlet's response.
     * @throws ServletException    if servlet error occurs.
     * @throws java.io.IOException if input or output error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    /**
     * Carries out http-servlet's request in the case of {@code get} request.
     *
     * @param request  is the http-servlet's request.
     * @param response is the http-sevlet's response.
     * @throws ServletException    if servlet error occurs.
     * @throws java.io.IOException if input or output error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    /**
     * Performs an action which follows after user's request.
     *
     * @param request  is the http-servlet's request.
     * @param response is the http-servlet's response.
     * @throws ServletException    if servlet error occurs.
     * @throws java.io.IOException if input or output error occurs.
     */
    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageCom = request.getParameter("command");
        if (pageCom == null) {
            pageCom = (String) request.getSession().getAttribute("command");
        }
        Command command = null;
        String nextPage = null;
        try {
            command = new CommandFactory().getCommand(pageCom);
        } catch (TechnicalException e) {
            LOG.error(e);
            response.sendError(500);

        }
        nextPage = command.execute(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
        requestDispatcher.forward(request, response);
    }

    /**
     * It is performed when servlet stops its existence.
     * It used to carry out finalizing actions.
     */
    public void destroy() {
        super.destroy();
        try {
            ConnectionPool.getInstance().cleanUp();
        } catch (DAOException e) {
            LOG.error(e);
        }
    }

    /*private void uploadFile(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();// Создаём класс фабрику
        factory.setSizeThreshold(1024 * 1024);    // Максимальный буфера данных в байтах, при превышении - запись во временную дир-ю
        File tempDir = (File) servletContext.getAttribute("javax.servlet.context.tempdir"); // устанавливаем временную директорию
        factory.setRepository(tempDir);
        ServletFileUpload upload = new ServletFileUpload(factory);//Создаём сам загрузчик
        upload.setSizeMax(1024 * 1024 * 10);    //максимальный размер данных который разрешено загружать в байтах,по умолчанию 1, без ограничений. Устанавливаем 10 мегабайт.
        try {
            List<FileItem> items = upload.parseRequest(request);
            HttpSession session = request.getSession();
            for (FileItem item : items) {
                String requestParamName = item.getFieldName();
                String requestParamValue = item.getString();
                session.setAttribute(requestParamName, requestParamValue);
            }
            String fileName = items.get(10).getName();
            String filePath = servletContext.getRealPath("/upload/" + new Random().nextInt() + fileName);
            session.setAttribute("filePath", filePath);
            File resultFile = new File(filePath);
            resultFile.createNewFile();
            items.get(10).write(resultFile);
        } catch (Exception e) {
            response.sendError(500);
        }
        new QuestToBaseCommand().execute(request);
    }*/
}
