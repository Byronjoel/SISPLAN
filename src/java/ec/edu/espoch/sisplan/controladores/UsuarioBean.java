/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.controladores;

import ec.edu.espoch.sisplan.clases.CRol;
import ec.edu.espoch.sisplan.modelos.MUsuario;
import ec.edu.espoch.sisplan.clases.CUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import ec.edu.espoch.sisplan.utilidades.Util;
import ec.edu.espoch.sisplan.utilidades.JsfUtil;

/**
 *
 * @author @foqc
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private CUsuario objUsuario;
    private CUsuario selObjUsuario;
    private ArrayList<CUsuario> lstUsuarios;
    private ArrayList<CUsuario> objUsuarioSesion;

    public UsuarioBean() {
        this.objUsuario = new CUsuario();
        this.lstUsuarios = new ArrayList<>();
        this.objUsuarioSesion = new ArrayList<>();
        this.selObjUsuario = new CUsuario();
    }

    public CUsuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(CUsuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public CUsuario getSelObjUsuario() {
        return selObjUsuario;
    }

    public void setSelObjUsuario(CUsuario selObjUsuario) {
        this.selObjUsuario = selObjUsuario;
    }

    public ArrayList<CUsuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(ArrayList<CUsuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public ArrayList<CUsuario> getObjUsuarioSesion() {
        return objUsuarioSesion;
    }

    public void setObjUsuarioSesion(ArrayList<CUsuario> objUsuarioSesion) {
        this.objUsuarioSesion = objUsuarioSesion;
    }

    /*
     postonstructor se ejecuta luego del constructor
     */
    @PostConstruct
    public void reinit() {
        CRol objTipo = new CRol();
        this.objUsuario.setObjRol(objTipo);
        this.selObjUsuario.setObjRol(objTipo);
        cargarUsuario();
        //cargarUsuarioPorSesion();

    }
    /*
     Metodo que permite cargar todos los registros de la base de datos
     */

    public void cargarUsuario() {
        try {
            this.lstUsuarios = (ArrayList<CUsuario>) MUsuario.cargarUsuarios();
        } catch (Exception e) {
            Util.addErrorMessage("Error al cargar datos: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Insertar Usuario">
    public void insertarUsuario() {
        try {
            if (MUsuario.insertarUsuario(objUsuario)) {
                cargarUsuario();
                Util.addSuccessMessageAndDetail("Aviso","Datos insertados!");
            } else {
                Util.addErrorMessageAndDetail("Error al insertar","Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>
  
    //<editor-fold defaultstate="collapsed" desc="Actualizar Usuario">
    public void actualizarPersona() {
        try {
            if (MUsuario.actualizarUsuario(selObjUsuario)) {
                Util.addSuccessMessageAndDetail("Aviso","Datos actualizados!");
            } else {
                Util.addErrorMessageAndDetail("Error al actualizar","Datos no actualizados!");
            }
        } catch (Exception e) {
            Util.addErrorMessageAndDetail("Error al actualizar","actualizar ***" + e.getMessage());
        }
        if (JsfUtil.isValidationFailed()) {
            cargarUsuario();
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Eliminar Usuario">
    public void eliminarPersona() {
        try {
            if (MUsuario.eliminarUsuario(selObjUsuario.getCodigo())) {
                Util.addSuccessMessageAndDetail("Aviso","Datos eliminados!");
                cargarUsuario();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>
}
