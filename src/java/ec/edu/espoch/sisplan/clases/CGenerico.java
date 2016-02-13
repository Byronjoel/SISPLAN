/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.clases;

/**
 *
 * @author 
 * @param <T>
 */
public class CGenerico<T> {

    T obj;

    public CGenerico(T o) {
        obj = o;
    }

    public CGenerico() {
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public void classType() {
        System.out.println("El tipo de T es " + obj.getClass().getName());
    }
    
    public String classType1() {
        return("El tipo de T es " + obj.getClass().getName());
    }

}
