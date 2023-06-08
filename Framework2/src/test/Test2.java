/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import annotation.Annotation;
import etu2046.framework.Modelview;
import java.util.ArrayList;

/**
 *
 * @author jessy
 */
public class Test2 {
   String nom;
   String prenom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Test2(String nom, String prenom) {
        this.setNom(nom);
        this.setPrenom(prenom);
    }
    public Test2(){
    }
    
   
   
   
    @Annotation(url="/get_all")
    public Modelview get_all_test(){
        Modelview model=new Modelview("test_view.jsp");
        model.addItem("liste", allemp());
        return model;
    }
    
    
    ArrayList<Test2> allemp(){
        ArrayList<Test2> list=new ArrayList<>();
        list.add(new Test2("jean","bernard"));
        list.add(new Test2("huhu","betrand"));
        list.add(new Test2("haha","bert"));
        list.add(new Test2("hehe","bertold"));
        return list;
    }
    @Annotation(url="/save")
    public Modelview save(){
        Modelview model=new Modelview("test_view.jsp");
        ArrayList<Test2> list=new ArrayList<>();
        list.add(this);
        model.addItem("liste", list);
        return model;
        
    }
    
}
