/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.controladores;

import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.clases.CHorario;
import ec.edu.espoch.sisplan.utilidades.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class HorarioBean implements Serializable{

    /**
     * Creates a new instance of HorarioBean
     */
    /*
      private CClase objClase;
    private CClase selObjClase;
    private List<CClase> lstClase;
    */
    private CHorario objHorario;
    private CHorario selObjHorario;
    private List<CHorario> lstHorario;

    public CHorario getObjHorario() {
        return objHorario;
    }

    public void setObjHorario(CHorario objHorario) {
        this.objHorario = objHorario;
    }

    public CHorario getSelObjHorario() {
        return selObjHorario;
    }

    public void setSelObjHorario(CHorario selObjHorario) {
        this.selObjHorario = selObjHorario;
    }

    public List<CHorario> getLstHorario() {
        return lstHorario;
    }

    public void setLstHorario(List<CHorario> lstHorario) {
        this.lstHorario = lstHorario;
    }
    
    public HorarioBean() {
    }
    private List<SelectItem> selectOneItemsH;
     public List<SelectItem> getSelectItemsHorario() {
        try {
            this.selectOneItemsH = new ArrayList<>();
            String sql1 = "SELECT *FROM sisplan.fn_select_horario()";
            ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1);
            while (rs1.next()) {
                CHorario objHor = new CHorario();
                objHor.setIdthorario(rs1.getInt(0));//.setUnidadcodigo(rs1.getInt(0));
                objHor.setThorariohora(rs1.getString(1));//setUnidaddescripcion(rs1.getString(1));                
                SelectItem selectItem2 = new SelectItem(objHor.getIdthorario(), objHor.getThorariohora());
                this.selectOneItemsH.add(selectItem2);
            }//cargar desde bd//
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        return this.selectOneItemsH;
    }
    
}
