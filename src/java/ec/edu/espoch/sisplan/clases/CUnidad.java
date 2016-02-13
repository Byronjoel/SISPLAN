/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
/*
 tunidadcodigo serial NOT NULL,
 tunidaddescripcion character varying(200) NOT NULL,
 tmateria_tmateriaid serial NOT NULL,

 */
public class CUnidad {

    private int unidadcodigo;
    private String unidaddescripcion;
    private String materiadescripcion;
    private CMateria objCMateria;
    private List<CClase> lstClase;

    public CUnidad() {
        objCMateria=new CMateria();
        lstClase=new ArrayList<>();
    }

    
    public CMateria getObjCMateria() {
        return objCMateria;
    }

    public void setObjCMateria(CMateria objCMateria) {
        this.objCMateria = objCMateria;
    }

    public List<CClase> getLstClase() {
        return lstClase;
    }

    public void setLstClase(List<CClase> lstClase) {
        this.lstClase = lstClase;
    }
    
    public String getMateriadescripcion() {
        return materiadescripcion;
    }

    public void setMateriadescripcion(String materiadescripcion) {
        this.materiadescripcion = materiadescripcion;
    }
    private int materiaid;

    public int getUnidadcodigo() {
        return unidadcodigo;
    }

    public void setUnidadcodigo(int unidadcodigo) {
        this.unidadcodigo = unidadcodigo;
    }

    public String getUnidaddescripcion() {
        return unidaddescripcion;
    }

    public void setUnidaddescripcion(String unidaddescripcion) {
        this.unidaddescripcion = unidaddescripcion;
    }

    public int getMateriaid() {
        return materiaid;
    }

    public void setMateriaid(int materiaid) {
        this.materiaid = materiaid;
    }

}
