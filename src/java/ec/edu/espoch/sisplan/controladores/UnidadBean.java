/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.controladores;

import WSGeneral.Periodo;
import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.accesodatos.Parametro;
import ec.edu.espoch.sisplan.clases.CMateria;
import ec.edu.espoch.sisplan.clases.CUnidad;
import ec.edu.espoch.sisplan.modelos.MUnidad;
import ec.edu.espoch.sisplan.utilidades.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class UnidadBean implements Serializable {

    /**
     * Creates a new instance of UnidadBean
     */
    private CUnidad objUnidad;
    private CUnidad selObjUnidad;
    private List<CUnidad> lstUnidad;

    public CUnidad getSelObjUnidad() {
        return selObjUnidad;
    }

    public void setSelObjUnidad(CUnidad selObjUnidad) {
        this.selObjUnidad = selObjUnidad;
    }

    public CUnidad getObjUnidad() {
        return objUnidad;
    }

    public void setObjUnidad(CUnidad objUnidad) {
        this.objUnidad = objUnidad;
    }

    public List<CUnidad> getLstUnidad() {
        return lstUnidad;
    }

    public void setLstUnidad(List<CUnidad> lstUnidad) {
        this.lstUnidad = lstUnidad;
    }

    public UnidadBean() {
        this.objUnidad = new CUnidad();
        this.selObjUnidad = new CUnidad();
        this.lstUnidad = new ArrayList<>();

    }
    //sel

    public void insertarUnidad() {
        try {
            if (MUnidad.insertarUnidades(this.objUnidad)) {
                //cargarCategorias();

                Util.addSuccessMessage("Datos insertados!");
            } else {
                Util.addErrorMessage("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al insertar datos: " + e.getMessage());
        }
    }

    //select item
   /* public List<SelectItem> getSelectItemsMenu() {
     try {
     //cargar el periodo actual
     Periodo p = getPeriodoActual();
     this.selectOneItems = new ArrayList<>();
         
     String sql = "SELECT *FROM sisplan.fn_select_matComboBox(?)";
     ArrayList<Parametro> lstParamDatosUsuario = new ArrayList<>();
     lstParamDatosUsuario.add(new Parametro(1, objUsuario.getCedula()));

     ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUsuario);
     while (rs.next()) {
     CMateria objMat = new CMateria();
     objMat.setIdcarrera(rs.getInt(0));
     objMat.setCodigomateria(rs.getString(1));
     objMat.setNombremateria(rs.getString(2));
     //cambiar
     SelectItem selectItem2 = new SelectItem(objMat.getIdcarrera(),objMat.getNombremateria());
     //SelectItem selectItem2 = new SelectItem(objMat.getNombremateria()+ objMat.getIdcarrera());
     this.selectOneItems.add(selectItem2);

     }//cargar desde bd
     //
     } catch (Exception e) {
     Util.addErrorMessage(e.getMessage());
     }
     return this.selectOneItems;
     }*/
}
