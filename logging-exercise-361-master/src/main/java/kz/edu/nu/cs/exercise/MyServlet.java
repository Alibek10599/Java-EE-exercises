package kz.edu.nu.cs.exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(urlPatterns = { "/myservlet" })
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       PrintWriter p = response.getWriter();
       HttpSession session = request.getSession();

           ArrayList <String> container = new ArrayList<String>();
           
           container.add("Time " + new Date(session.getLastAccessedTime()));
           container.add(" Host " + request.getRemoteHost());
           container.add(" Path " + request.getContextPath());

           p.println(container);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
