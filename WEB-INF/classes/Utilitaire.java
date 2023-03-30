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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import javax.servlet.http.HttpServletRequest;
import annotation.Url;
import etu1779.framework.Mapping;

public class Utilitaire{
    public Utilitaire(){}

    public String getServletUrl(HttpServletRequest req) {
        String url = req.getRequestURL().toString();
        String[] allpartUrl = url.split("//");
        String[] parts2 = allpartUrl[1].split("/");
        // System.out.println(allpartUrl[4]);
        return parts2[2];
    }

    public String getGeneralPath() {
        Class<?> cls = this.getClass();
        URL url = cls.getResource("");
        if (url == null) {
            return null;
        }
        String path = url.getPath();
        String searchString = "/classes/";
        int endIndex = path.indexOf(searchString);
        String extractedString = path.substring(0, endIndex);
        String resp = extractedString.substring(1); 
        return extractedString;
    }

    public ArrayList<String> findAllFilesInFolder(String path) {
        if (path == null || path == "") {
            path = getGeneralPath();
        }
        File folder = new File(path);
        ArrayList<String> list = new ArrayList<>();
    
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                String filen = file.getName();
                if(filen.endsWith(".class")){
                    list.add(filen);                                        
                }
            } else {
                list.add(file.getName());
                ArrayList<String> sublist = findAllFilesInFolder(file.getAbsolutePath()); // appel récursif avec le chemin absolu du sous-dossier
                list.addAll(sublist);
            }
        }
        return list;
    }

    public ArrayList<String> RecursiveAction(String folderPath) {
        if (folderPath == null || folderPath == "") {
            folderPath = getGeneralPath();
        }
        ArrayList<String> value = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    ArrayList<String> subvalue = RecursiveAction(file.getAbsolutePath());
                    value.addAll(subvalue);
                } else if (file.getName().endsWith(".class")) {
                    value.add(file.getParent()+"."+file.getName());
                }
            }
        }
        return value;
    }

    public ArrayList<String> getclassName(ArrayList<String> list){
        if (list == null) {
            list = RecursiveAction("");            
        }
        ArrayList<String> value = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String pathwithout = list.get(i).substring(list.get(i).indexOf("\\classes\\") + 9);
            String mety = pathwithout.replace("\\", ".");
            value.add(mety);
        }
        return value;
    }

    public ArrayList<Class<?>> getAllClass(ArrayList<String> list)throws Exception{
        if (list==null) {
            list = getclassName(null);
        }
        ArrayList<Class<?>> xlass = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String[] ind0 = list.get(i).split(".class");
            xlass.add(Class.forName(ind0[0]));
        }
        return xlass;
    }

    public Mapping getClassMethod(String annotation) throws Exception{
        ArrayList<Class<?>> list = getAllClass(null);
        Mapping map = new Mapping();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getMethods().length; j++) {
                if (list.get(i).getMethods()[j].isAnnotationPresent(Url.class)) {
                    if (annotation.compareToIgnoreCase(list.get(i).getMethods()[j].getAnnotation(Url.class).value())==0) {
                        map.setClassName(list.get(i).getSimpleName());
                        map.setMethod(list.get(i).getMethods()[j].getName());
                    }
                }
            }
        }
        return map;
    }
    
    public HashMap<String,Mapping> getUrlAnnotationByAllClass(ArrayList<Class<?>> list)throws Exception{
        if (list==null) {
            list = getAllClass(null);
        }
        HashMap<String,Mapping> hashmap = new HashMap<>();
        Mapping map = new Mapping();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getMethods().length; j++) {
                if (list.get(i).getMethods()[j].isAnnotationPresent(Url.class)) {
                    map.setClassName(list.get(i).getName());
                    map.setMethod(list.get(i).getMethods()[j].getName());
                    hashmap.put(list.get(i).getMethods()[j].getAnnotation(Url.class).value(), map);
                }
            }
        }
        return hashmap;
    }
}
