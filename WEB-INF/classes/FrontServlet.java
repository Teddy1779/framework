package etu1779.framework;
import inc.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etu1779.framework.Mapping;
public class FrontServlet extends HttpServlet{  
    HashMap<String,Mapping> MappingUrls ;
    Utilitaire u = new Utilitaire();
    @Override
    public void init() throws ServletException{ 
        try {
            this.MappingUrls = this.u.getUrlAnnotationByAllClass(null);
            for (Map.Entry<String,Mapping> ex : this.MappingUrls.entrySet()) {
                System.out.println(ex.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    protected void ProcessRequest(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{   
        res.setContentType("text/html");
        Utilitaire u = new Utilitaire();
        // Use a PrintWriter object to send text data to the client who has requested the servlet
        PrintWriter out = res.getWriter();

        // Write the response message, in an HTML document
        out.println("<html><body>");
        out.println("<head><title>Front Servlet</title></head>");
        out.println("<body>");
        for (Map.Entry<String,Mapping> ex : this.MappingUrls.entrySet()) {
            out.println(ex.getKey());
        }
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