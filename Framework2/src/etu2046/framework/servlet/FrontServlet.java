package etu2046.framework.servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import annotation.Annotation;
import annotation.Scop;
import etu2046.framework.Mapping;
import etu2046.framework.Modelview;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jessy
 */
@WebServlet(urlPatterns = {"/FrontServlet"})
public class FrontServlet extends HttpServlet {

    HashMap<String, Mapping> mappingUrls;
    HashMap<String, Object> singleton;

    public HashMap<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }
    public HashMap<String, Object> getSingleton() {
        return singleton;
    }

    public void setSingleton(HashMap<String, Object> singleton) {
        this.singleton = singleton;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        mappingUrls = new HashMap<>();
        singleton = new HashMap<>();
        String rootPackage = config.getInitParameter("rootPackage");
        File folder = new File(rootPackage);
        File[] files = folder.listFiles();
        for (File file : files) {
            String fileName = file.getName().split(".java")[0];
            Class<?> classTemp = null;
            try {
                classTemp = Class.forName("test." + fileName);
                this.checkSingleton(classTemp);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Object obj = null;
            try {
                obj = classTemp.newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Annotation.class)) {
                    String url = method.getAnnotation(Annotation.class).url();
                    String className = obj.getClass().getName();
                    String methodName = method.getName();
                    mappingUrls.put(url, new Mapping(className, methodName));
                }
            }
        }
    }
    public void checkSingleton(Class check){
        if(check.isAnnotationPresent(Scop.class)){
            
            String className = check.getName();
            this.getSingleton().put(className, null);
        }
    }
    public void reset(Object objet) throws IllegalAccessException, InvocationTargetException{
        Field[] fields = objet.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = upperFirst(field.getName());
            Method methodSet = null;
            try {
                methodSet = objet.getClass().getMethod("set"+fieldName, field.getType());
            } catch (Exception e) {
                continue;
            }
            if(field.getType().equals(int.class)){
                methodSet.invoke(objet, 0);
            }
            if(field.getType().equals(double.class)){
                methodSet.invoke(objet, 0);
            }
            if(field.getType().equals(float.class)){
                methodSet.invoke(objet, 0);
            }
            if(field.getType().equals(Object.class)){
                methodSet.invoke(objet, (Object) null);
            }
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] parts = request.getServletPath().split("/");
        String url = parts[parts.length - 1];
        Modelview mv = null;
        PrintWriter out = response.getWriter();
        Enumeration<String> parametres = request.getParameterNames();
        response.setContentType("text/html;charset=UTF-8");

        out.print(url);
        Modelview vue;
        try {
            Enumeration<String> parameters = request.getParameterNames();
            String[] attributs = new String[0];
            String[] values = new String[0];
            while (parameters.hasMoreElements()) {
                String parametre = parameters.nextElement();
                String value = request.getParameter(parametre);
                attributs = Arrays.copyOf(attributs, attributs.length + 1);
                values = Arrays.copyOf(values, values.length + 1);
                attributs[attributs.length - 1] = parametre;
                values[values.length - 1] = value;
            }
            if (getModelview(url, attributs, values) != null) {
                vue = this.getModelview(url, attributs, values);
                String page = vue.getView();
                for (Map.Entry m : vue.getData().entrySet()) {
                    request.setAttribute((String) m.getKey(), m.getValue());
                }
                RequestDispatcher dispatch = request.getRequestDispatcher(page);
                dispatch.forward(request, response);
            }
            int x = 8;
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    public Method getMethod(String method, Object obj) {
        Method[] listeMethod = obj.getClass().getDeclaredMethods();
        Method valiny = null;
        for (Method method1 : listeMethod) {
            if (method1.getName().equals(method)) {
                valiny = method1;
            }
        }
        return valiny;
    }
    public Object getInClassInstance(String className,Class<?> classe) throws IllegalAccessException, InvocationTargetException, InstantiationException{
        Object objet = null;
        if(this.getSingleton().containsKey(className)){
            Object obj = this.getSingleton().get(className);
            if(obj == null){
                obj = classe.newInstance();
                objet = obj;
                this.getSingleton().put(className, objet);
            }else{
                reset(obj);
                objet = obj;
            }
        }
        else{
            objet = classe.newInstance();
        }
        return objet;
    }

    public Modelview getModelview(String url, String[] params, String[] values) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Modelview valiny = null;
        if (getMappingUrls().get(url) instanceof Mapping) {
            Mapping util = getMappingUrls().get(url);
            Class classname = Class.forName(util.getClassName());
            Object test = this.getInClassInstance(classname.getName(), classname);
            Method m = this.getMethod(util.getMethod(), test);
            Field[] fields = classname.getDeclaredFields();
            Parameter[] parametres = m.getParameters();
            Object[] tableau = new Object[parametres.length];
            String[] allparm = new String[0];
            for (Field field : fields) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i].equals(field.getName())) {
                        Method setobject = classname.getMethod("set" + this.upperFirst(params[i]), field.getType());
                        Object type = null;
                        if (field.getType() == String.class) {
                            type = values[i];
                        } else if (field.getType() == int.class || field.getType() == Integer.class) {
                            type = Integer.valueOf(values[i]);
                        } else if (field.getType() == double.class || field.getType() == Double.class) {
                            type = Double.valueOf(values[i]);
                        } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                            type = Boolean.valueOf(values[i]);
                        }
                        allparm = Arrays.copyOf(allparm, allparm.length + 1);
                        if(type != null)
                            allparm[allparm.length - 1] = type.getClass().getName();
                        setobject.invoke(test, type);
                    }
                }
            }
            for (int j = 0; j < parametres.length; j++) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i].equals(parametres[j].getName())) {
                        Object type = null;
                        if (parametres[j].getType() == String.class) {
                            type = values[i];
                        } else if (parametres[j].getType() == int.class || parametres[j].getType() == Integer.class) {
                            type = Integer.valueOf(values[i]);
                        } else if (parametres[j].getType() == double.class || parametres[j].getType() == Double.class) {
                            type = Double.valueOf(values[i]);
                        } else if (parametres[j].getType() == boolean.class || parametres[j].getType() == Boolean.class) {
                            type = Boolean.valueOf(values[i]);
                        }
                        tableau[j] = type;
                        int f = 0;
                    }
                }
            }
            valiny = (Modelview) m.invoke(test, tableau);
        }
        return valiny;
    }

    public Modelview getView(String url) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Modelview valiny = null;
        if (getMappingUrls().get(url) instanceof Mapping) {
            Mapping util = getMappingUrls().get(url);
            Class classname = Class.forName(util.getClassName());
            Object test = classname.newInstance();
            Method method = test.getClass().getMethod(util.getMethod());
            Object page = method.invoke(test);
            valiny = (Modelview) page;
        }
        return valiny;
    }

    public Modelview save(String url, String[] params, String[] values) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        String classname = this.getMappingUrls().get(url).getClassName();
        String methode = this.getMappingUrls().get(url).getMethod();
        Class<?> classe = Class.forName(classname);
        Object objet = classe.newInstance();
        Field[] fields = classe.getDeclaredFields();
        String[] allparm = new String[0];
        for (Field field : fields) {
            for (int i = 0; i < params.length; i++) {
                if (params[i].equals(field.getName())) {
                    Method setobject = classe.getMethod("set" + this.upperFirst(params[i]), field.getType());
                    Object type = null;
                    if (field.getType() == String.class) {
                        type = values[i];
                    } else if (field.getType() == int.class || field.getType() == Integer.class) {
                        type = Integer.valueOf(values[i]);
                    } else if (field.getType() == double.class || field.getType() == Double.class) {
                        type = Double.valueOf(values[i]);
                    } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                        type = Boolean.valueOf(values[i]);
                    }
                    allparm = Arrays.copyOf(allparm, allparm.length + 1);
                    if(type != null)
                        allparm[allparm.length - 1] = type.getClass().getName();
                    setobject.invoke(objet, type);
                }
            }
        }
        Method method = classe.getDeclaredMethod(methode);
        Modelview mv = (Modelview) method.invoke(objet);
        return mv;
    }

    public String upperFirst(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
