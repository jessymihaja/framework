/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2046.framework;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jessy
 */
public class Modelview {
    String view;
    HashMap <String ,Object> data;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String,Object> getData() {
        return data;
    }

    public void setData(HashMap <String,Object> data) {
        this.data = data;
    }
    
    public Modelview(String view) {
        this.setView(view);
        this.setData(new HashMap<>());
    }

    public void addItem(String key,Object item){
        this.getData().put(key,item);
    }
}