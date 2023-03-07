package inc;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class Utilitaire{
    public Utilitaire(){}

    public String getServletUrl(HttpServletRequest req) {
        String url = req.getRequestURL().toString();
        String[] allpartUrl = url.split("//");
        String[] parts2 = allpartUrl[1].split("/");
        // System.out.println(allpartUrl[4]);
        return parts2[2];
    }
}
