/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.controladores;

import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.clases.CProgreso;
import ec.edu.espoch.sisplan.utilidades.Util;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class ProgresoBean {

    /**
     * Creates a new instance of ProgresoBean
     */
    /*
    private CHorario objHorario;
    private CHorario selObjHorario;
    private List<CHorario> lstHorario;
    */
    private CProgreso objProgreso;
    private CProgreso selObjProgreso;
    private List<CProgreso> lstProgreso;

    public CProgreso getObjProgreso() {
        return objProgreso;
    }

    public void setObjProgreso(CProgreso objProgreso) {
        this.objProgreso = objProgreso;
    }

    public CProgreso getSelObjProgreso() {
        return selObjProgreso;
    }

    public void setSelObjProgreso(CProgreso selObjProgreso) {
        this.selObjProgreso = selObjProgreso;
    }

    public List<CProgreso> getLstProgreso() {
        return lstProgreso;
    }

    public void setLstProgreso(List<CProgreso> lstProgreso) {
        this.lstProgreso = lstProgreso;
    }
    
    public ProgresoBean() {
    }
    private List<SelectItem> selectOneItemsP;
     public List<SelectItem> getSelectItemsProgreso() {
        try {
            this.selectOneItemsP = new ArrayList<>();
            String sql1 = "SELECT *FROM sisplan.fn_select_progreso()";
            ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1);
            while (rs1.next()) {
                CProgreso objHor = new CProgreso();
                objHor.setIdtprogreso(rs1.getInt(0));//.setUnidadcodigo(rs1.getInt(0));
                objHor.setTprogresodescripcion(rs1.getString(1));//setUnidaddescripcion(rs1.getString(1));                
                SelectItem selectItem2 = new SelectItem(objHor.getIdtprogreso(), objHor.getTprogresodescripcion());
                this.selectOneItemsP.add(selectItem2);
            }//cargar desde bd//
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        return this.selectOneItemsP;
    }

    
}
