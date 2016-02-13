/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.controladores;

import WSCarrera.Persona;
import WSGeneral.ArrayOfFacultad;
import WSGeneral.Facultad;
import WSGeneral.InfoGeneral;
import WSSeguridad.ArrayOfRolCarrera;
import WSSeguridad.RolCarrera;
import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.accesodatos.Parametro;
import ec.edu.espoch.sisplan.clases.CUsuario;
import ec.edu.espoch.sisplan.modelos.MLogin;
import ec.edu.espoch.sisplan.modelos.Mfacultad;
import ec.edu.espoch.sisplan.utilidades.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.xml.ws.WebServiceRef;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class facultadBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/academico.espoch.edu.ec/OAS_Interop/InfoGeneral.asmx.wsdl")
    private InfoGeneral service;

    /**
     * Creates a new instance of facultadBean
     */
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public facultadBean() {
    }

    public void insertarFacultad() {
        try {
            if (Mfacultad.insertarFacultades()) {
                Util.addSuccessMessage("Datos insertados!");
            } else {
                Util.addErrorMessage("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al insertar datos: " + e.getMessage());
        }
    }

    public void insertarEscuel() {
        try {
            if (Mfacultad.insertarEscuela()) {
// if (MProveedor.insertarProveedor(this.objProveedor)) {
                // cargarProveedores();
                Util.addSuccessMessage("Datos escuela insertados!");
            } else {
                Util.addErrorMessage("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addwarningMessage(e.getMessage());//.addErrorMessage(e.getMessage());
            //Util.addErrorMessage("Error al insertar datos: " + e.getMessage());
        }
    }

    public void insertarCarrer() {
        try {
            if (Mfacultad.insertarCarrera()) {
// if (MProveedor.insertarProveedor(this.objProveedor)) {
                // cargarProveedores();
                Util.addSuccessMessage("Datos Carreras insertados!");
            } else {
                Util.addErrorMessage("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al insertar datos: " + e.getMessage());
        }
    }

    public void insertarMateria() {
        try {
            if (Mfacultad.insertarMaterias()) {
// if (MProveedor.insertarProveedor(this.objProveedor)) {
                // cargarProveedores();
                Util.addSuccessMessage("Datos Materias insertados!");
            } else {
                Util.addErrorMessage("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al insertar datos: " + e.getMessage());
        }
    }

    /* public static List<CMaterialBodega> cargarMaterial() throws Exception {
     List<CMaterialBodega> lstMaterial = new ArrayList<>();
     try {
     String sql = "select *from softlab.fn_select_tmaterialbodega()";
     ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
     while (rs.next()) {
     CMaterialBodega material = new CMaterialBodega();
     material.setCodigo(rs.getLong(0));
     material.setNombre(rs.getString(1));
     material.setDescripcion(rs.getString(2));
     material.setEstado(rs.getString(3));
     material.setFechaIngreso(rs.getDate(4));
     material.setCantidad(rs.getLong(5));
     material.setResponsable(rs.getString(7));
     material.setContenido(rs.getInt(8));
     CProveedor objProveedor = MProveedor.cargarProveedorPorCodigo(rs.getInt(10));
     material.setObjProveedor(objProveedor);
     CCategoria objCategoria = MCategoriaBodega.cargarCategoriaBodegaPorCodigo(rs.getInt(11));
     material.setObjCategoria(objCategoria);
     lstMaterial.add(material);
     }
     rs = null;
     } catch (Exception e) {
     lstMaterial.clear();
     throw e;
     }
     return lstMaterial;
     }
     */
    private ArrayOfFacultad getFacultadesTotales() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        WSGeneral.InfoGeneralSoap port = service.getInfoGeneralSoap();
        return port.getFacultadesTotales();
    }
}
