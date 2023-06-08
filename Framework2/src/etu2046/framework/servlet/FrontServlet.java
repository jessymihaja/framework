package etu2046.framework.servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import etu2046.framework.Mapping;
import etu2046.framework.Modelview;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
            throws ServletException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FrontServlet at huhu " + request.getContextPath() + "</h1>");
            out.println("<h2> from " + request.getServletPath() + "</h2>");
            String path=request.getServletPath();
            /*try{
                if(getMappingUrls().get(path) instanceof Mapping){
                out.println("mande");
                out.println(getMappingUrls().size());
                }
                else{
                    out.println("tsy mandee");
                }
            }catch(Exception e){
                out.println(e.getMessage());
            } for (Map.Entry m: getMappingUrls().entrySet()) {
                        out.println((String) m.getKey()+" "+ m.getValue());
                    }*/
                if(getMappingUrls().containsKey("/save")){
                    Enumeration<String> parameters=request.getParameterNames();
                    ArrayList<String> attributs=new ArrayList<>();
                    ArrayList<String> valeurs=new ArrayList<>();
                    String parameter="";
                    String value="";
                    while(parameters.hasMoreElements()){
                        
                        parameter=parameters.nextElement();
                        value=request.getParameter(parameter);
                        out.println(parameter);
                        out.println(value);
                        attributs.add(parameter);
                        valeurs.add(value);
                    }
                    
                    Modelview newvue=this.save_model(attributs, valeurs, path);
                    
                    for (Map.Entry m: newvue.getData().entrySet()) {
                        
                        request.setAttribute((String) m.getKey(), m.getValue());
                    }
                    
                    RequestDispatcher rd=request.getRequestDispatcher(newvue.getView());
                    rd.forward(request, response);
                }
                else if(getMappingUrls().containsKey(path)){
                   
                    String clas=getMappingUrls().get(request.getServletPath()).getClassName();
                    String met=getMappingUrls().get(request.getServletPath()).getMethod();
                    out.println(clas);
                    try {
                        Class<?> thisClass=Class.forName("test."+clas);
                
                    Method thisMethod=thisClass.getDeclaredMethod(met);
                    
                    Object thisInstance= thisClass.newInstance();
                    
                    Modelview resultat=(Modelview)thisMethod.invoke(thisInstance);
                    for (Map.Entry m: resultat.getData().entrySet()) {
                        request.setAttribute((String) m.getKey(), m.getValue());
                    }
                    RequestDispatcher rd=request.getRequestDispatcher(resultat.getView());
                    rd.forward(request, response);
                    
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    
                }   
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e){
            throw new RuntimeException(e);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    HashMap<String, Mapping> MappingUrls;

    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> MappingUrls) {
        this.MappingUrls = MappingUrls;
    }
    public Modelview save_model(ArrayList<String> attributs,ArrayList<String> valeurs,String path) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
        System.out.println("mande_model1");
        String clas=getMappingUrls().get(path).getClassName();
        String met=getMappingUrls().get(path).getMethod();
        Class<?> thisClass=Class.forName("test."+clas);
        Object thisInstance= thisClass.getConstructor().newInstance();
        Method thisMethod=thisClass.getDeclaredMethod(met);
        Field[] field_tab=thisClass.getDeclaredFields();
        
        int compteur=0;
       
        for (Field field : field_tab) {
            
            for (String attribut : attributs) {
                
                if(attribut.equals(field.getName())){
                    
                    Method setter=thisClass.getMethod("set"+this.Maj(attribut),field.getType());
                    //System.out.println(valeurs.get(compteur) +" "+ compteur);
                    
                    
                    String fieldName=field.getName();
                    if (field.getType().equals(String.class)) {
                        setter.invoke(thisInstance,String.valueOf(valeurs.get(compteur)));
                    } else if (field.getType().equals(int.class)) {
                        setter.invoke(thisInstance,Integer.valueOf(valeurs.get(compteur)));
                    } else if (field.getType().equals(double.class)) {
                        setter.invoke(thisInstance,Double.valueOf(valeurs.get(compteur)));
                    }
                    

                }
                
            }
            compteur++;
        }
        
        Modelview view=(Modelview)thisMethod.invoke(thisInstance);
        
        return view;
    }
    public String Maj(String string){
        String maj=string.substring(0,1).toUpperCase()+string.substring(1);
        return maj;
    }

    @Override
    public void init() throws ServletException {
        String packageDirectory="/home/jessy/NetBeansProjects/Framework_test/src/java/test/";
        String chemin="test.";
        try {
            HashMap<String,Mapping> mapp=new HashMap();
            mapp=Mapping.getMethodsHashMapFromPackage(packageDirectory, chemin);
            this.setMappingUrls(mapp);
            
        } catch (Exception e) {
            System.out.println("non trouve");
        }
    }

   
    
    
    
    
    

}