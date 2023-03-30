package inc;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
