/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import annotation.Annotation;
import annotation.Scop;
import etu2046.framework.Modelview;
import java.util.ArrayList;

/**
 *
 * @author jessy
 */
@Scop(isSingleton=true)
public class Test2 {

    int id=0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Test2(int id, String nom, String prenom) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    public Test2() {
    }

    @Annotation(url = "get_all")
    public Modelview get_all_test() {
        Modelview model = new Modelview("test_view.jsp");
        model.addItem("liste", allemp());
        return model;
    }

    @Annotation(url = "get-detail")
    public Modelview getDetail(Integer id) {
        Modelview valiny = new Modelview("newjspdetail.jsp");

            ArrayList<Test2> ls = new ArrayList<>();
            ls.add(new Test2(1, "Jean", "Marc"));
            ls.add(new Test2(2, "Jean", "Yves"));
            ls.add(new Test2(3, "Jean", "Charles"));
            ls.add(new Test2(4, "Bob", "Alice"));
            valiny.addItem("liste", ls.get(id));
            return valiny;

    }

    ArrayList<Test2> allemp() {
        ArrayList<Test2> list = new ArrayList<>();
        list.add(new Test2(1, "jean", "bernard"));
        list.add(new Test2(2, "huhu", "betrand"));
        list.add(new Test2(3, "haha", "bert"));
        list.add(new Test2(4, "hehe", "bertold"));
        return list;
    }

    @Annotation(url = "save")
    public Modelview save() {
        Modelview model = new Modelview("test_view.jsp");

        ArrayList<Test2> list = new ArrayList<>();
        list.add(this);
        model.addItem("liste", list);
        return model;

    }
    @Annotation(url="singleton")
    public Modelview singleton(){
        Modelview valiny = new Modelview("newjspdetail.jsp");
        ArrayList<Test2> ls = new ArrayList<>();
        this.setId(this.getId()+1);
        ls.add(this);
        valiny.addItem("listeid",ls);
        return valiny;
    }

}
