/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.controladores;

import ec.edu.espoch.sisplan.clases.CClase;
import ec.edu.espoch.sisplan.clases.CHorario;
import ec.edu.espoch.sisplan.clases.CMateria;
import ec.edu.espoch.sisplan.clases.CProgreso;
import ec.edu.espoch.sisplan.clases.CUnidad;
import ec.edu.espoch.sisplan.modelos.MClase;
import ec.edu.espoch.sisplan.modelos.MUnidad;
import ec.edu.espoch.sisplan.utilidades.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.DefaultRequestContext;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class ClaseBean implements Serializable {

    /**
     * Creates a new instance of ClaseBean
     */
    /*  private CUnidad objUnidad;
     private CUnidad selObjUnidad;
     private List<CUnidad> lstUnidad;*/
    private CClase objClase;
    private CClase selObjClase;
    private ArrayList<CClase> lstClase;
    private ArrayList<CClase> lstClaseByMateria;

    public ArrayList<CClase> getLstClaseByMateria() {
        return lstClaseByMateria;
    }

    public void setLstClaseByMateria(ArrayList<CClase> lstClaseByMateria) {
        this.lstClaseByMateria = lstClaseByMateria;
    }

    public ArrayList<CClase> getLstClase() {
        return lstClase;
    }

    public void setLstClase(ArrayList<CClase> lstClase) {
        this.lstClase = lstClase;
    }

    public CClase getObjClase() {
        return objClase;
    }

    public void setObjClase(CClase objClase) {
        this.objClase = objClase;
    }

    public CClase getSelObjClase() {
        return selObjClase;
    }

    public void setSelObjClase(CClase selObjClase) {
        this.selObjClase = selObjClase;
    }

    public ClaseBean() {
        this.objClase = new CClase();
        this.selObjClase = new CClase();
        this.lstClase = new ArrayList<>();
        this.lstClaseByMateria = new ArrayList<>();

    }

    @PostConstruct
    public void reinit() {
        CMateria objMateria = new CMateria();
        CUnidad objUnidad = new CUnidad();
        CHorario objHorario = new CHorario();
        CProgreso objProgreso = new CProgreso();
        this.objClase.setObjMateria(objMateria);
        this.objClase.setObjUnidad(objUnidad);
        this.objClase.setObjHorario(objHorario);
        this.objClase.setObjProgreso(objProgreso);

        this.selObjClase.setObjMateria(objMateria);
        this.selObjClase.setObjUnidad(objUnidad);
        this.selObjClase.setObjHorario(objHorario);
        this.selObjClase.setObjProgreso(objProgreso);

        cargarClase();
        /*
       
         cargarReservaEstudiante();
         cargarReserva();

         }
        
         */
    }

    public void insertarClase() {
        try {
            if (MClase.insertarClases(this.objClase)) {
                //cargarCategorias();
                Util.addSuccessMessage("Datos insertados!");
            } else {
                Util.addErrorMessage("Datos no insertados!");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al insertar datos: " + e.getMessage());
        }
    }

    public void cargarClase() {

        try {
            this.lstClase = (ArrayList<CClase>) MClase.cargarClas(this.objClase);
            // this.lstReservas = (ArrayList<CReservaBodega>) MViewReservaBodDocente.cargarReservaDocentesSolicitado();
        } catch (Exception e) {
            Util.addErrorMessage("Error al cargar datos: " + e.getMessage());
        }
        //DefaultRequestContext.getCurrentInstance().execute("$('#myDataTables').dataTable();");
        //titulo = "solicitados";

    }

    public void cargarClaseInterfaz() {

        try {
            this.lstClase = (ArrayList<CClase>) MClase.cargarClas(this.objClase);
            System.out.println("tam " + lstClase.size());
            if (lstClase.isEmpty()) {
                Util.addErrorMessage("No existen datos de esta materia.");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al cargar datos: " + e.getMessage());
        }
        //DefaultRequestContext.getCurrentInstance().execute("$('#myDataTables').dataTable();");
        //titulo = "solicitados";

    }

    public void materiaSele() {
        System.out.println("materia seleccionada: " + objClase.getObjMateria().getIdcarrera());
        Util.addErrorMessage("materia seleccionada: " + objClase.getObjMateria().getIdcarrera());
    }

    public void cargarClaseByMateria() {
        try {
            this.lstClaseByMateria = (ArrayList<CClase>) MClase.cargarClaseByMateria(this.objClase.getMateriacodigo());
            System.out.println("tam " + lstClaseByMateria.size());
            if (lstClaseByMateria.isEmpty()) {
                Util.addErrorMessage("No existen datos de esta materia por nombre.");
            }
        } catch (Exception e) {
            Util.addErrorMessage("Error al cargar datos: " + e.getMessage());
        }
    }

}
