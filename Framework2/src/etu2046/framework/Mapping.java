/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2046.framework;

import annotation.Annotation;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jessy
 */
public class Mapping {
    String className;
    String method;

    public String getClassName() {
        return className;
    }

    public String getMethod() {
        return method;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Mapping(String className, String method) {
        this.className = className;
        this.method = method;
    }
    
    
     public static String[] getClassList(String directoryPath)
    {
        File file=new File(directoryPath);
//        ty zany afaka simplifier-na otran'zao
//        de filtrena fotsiny le .java avy eo
        File[] f=file.listFiles();
        FilenameFilter textFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
//        String lowercaseName = name.toLowerCase();
                if (name.endsWith(".java")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String [] listefile=file.list(textFilefilter);
        for (int i = 0; i < listefile.length; i++) {
            listefile[i]=listefile[i].split(".java")[0];
        }
        return listefile;
    }


public static HashMap<String, Mapping> getMethodsHashMapFromPackage(String packageDirectory,String ObjectPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        HashMap<String,Mapping> mapping=new HashMap<>();

        String[] classes=getClassList(packageDirectory);

        for (String classe : classes) {
            System.out.println("name: " + classe);
            Class<?> tempClass = Class.forName(ObjectPackage + classe);
            Object obj=tempClass.newInstance();
            Method [] methods;
            methods = obj.getClass().getDeclaredMethods();
            for (Method method1 : methods) {
                if (method1.isAnnotationPresent(Annotation.class)) {
                    String url = method1.getAnnotation(Annotation.class).url();
                    String className = classe;
                    String methodName = method1.getName();
                    mapping.put(url,new Mapping(className,methodName));
                }
            }
        }
        return mapping;
    }
    
}
    
    
