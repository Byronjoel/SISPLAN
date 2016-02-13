/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.controladores;

import ec.edu.espoch.sisplan.clases.CRol;
import ec.edu.espoch.sisplan.modelos.MRol;
import ec.edu.espoch.sisplan.utilidades.JsfUtil;
import ec.edu.espoch.sisplan.utilidades.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ©foqc
 */
@ManagedBean
@ViewScoped
public class RolBean implements Serializable{

    private CRol objRol;
    private CRol selObjRol;
    private List<CRol> lstRoles;

    /**
     * Creates a new instance of RolBean
     */
    public RolBean() {
        this.objRol = new CRol();
        this.selObjRol = new CRol();
        this.lstRoles = new ArrayList<>();        
    }

    public CRol getObjRol() {
        return objRol;
    }

    public void setObjRol(CRol objRol) {
        this.objRol = objRol;
    }

    public CRol getSelObjRol() {
        return selObjRol;
    }

    public void setSelObjRol(CRol selObjRol) {
        this.selObjRol = selObjRol;
    }

    public List<CRol> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<CRol> lstRoles) {
        this.lstRoles = lstRoles;
    }

    @PostConstruct
    public void reinit() {
        cargarRoles();
    }

    public void cargarRoles() {
        try {
            this.lstRoles = (ArrayList<CRol>) MRol.cargarRoles();
        } catch (Exception e) {
            Util.addErrorMessage("Error al cargar datos: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Insertar Rol">
    public void insertarRol() {
        try {
            if (MRol.insertarRol(this.objRol)) {
                cargarRoles();
                Util.addSuccessMessage("Datos insertados!");
            } else {
                Util.addErrorMessage("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al insertar datos: " + e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Actualizar Rol">
    public void actualizarRol() {
        try {
            if (MRol.actualizarRol(this.selObjRol)) {
                cargarRoles();
                Util.addSuccessMessage("Datos actualizados!");
            } else {
                Util.addErrorMessage("Datos no actualizados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al actualizar datos: " + e.getMessage());
        }
        if (JsfUtil.isValidationFailed()) {
            cargarRoles();
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Eliminar Rol">
    public void eliminarRol() {
        try {
            if (MRol.eliminarRol(this.selObjRol.getCodigo())) {
                recursos.Util1.addSuccessMessage("Datos eliminados!");
                cargarRoles();
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al eliminar datos: " +e.getMessage());
        }
    }
//</editor-fold>
}
