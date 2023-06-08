/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

//import annotation.Annotation;

import annotation.Annotation;


/**
 *
 * @author jessy
 */
public class Test {
    double x1;
    double x2;

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }
   @Annotation(url="/sommer")
    public double getSomme(){
        return (this.getX1() + this.getX2());
    }
    
    
}
