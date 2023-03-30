package etu1779.framework;
import inc.*;
import java.io.*;
import java.net.http.HttpRequest;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etu1779.framework.Mapping;
public class FrontServlet extends HttpServlet{
    HashMap <String,Mapping> MappingUrls;
    protected void ProcessRequest(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        res.setContentType("text/html");
        Utilitaire u = new Utilitaire();
        // Use a PrintWriter object to send text data to the client who has requested the servlet
        PrintWriter out = res.getWriter();

        // Write the response message, in an HTML document
        out.println("<html><body>");
        out.println("<head><title>Front Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>"+ u.getServletUrl(req) +"</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        ProcessRequest(req, res);
    }
    
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        ProcessRequest(req, res);
    }
}