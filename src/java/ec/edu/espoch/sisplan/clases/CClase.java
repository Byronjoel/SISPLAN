/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.clases;

import java.util.Date;

/**
 *
 * @author Usuario
 */
            //tema
public class CClase {

    private int materiacodigo;
    private int unidadcodigo;
    private int idTema;
    private String tematema;
    private Date temafecha;
    private int temahorario;
    private int temaprogreso;
    private String temaobservaciones;
    private Date fechaactualizacion;
    //protected CCategoria objCategoria;

    protected CMateria objMateria;
    protected CUnidad objUnidad;
    protected CHorario objHorario;
    protected CProgreso objProgreso;

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public CMateria getObjMateria() {
        return objMateria;
    }

    public void setObjMateria(CMateria objMateria) {
        this.objMateria = objMateria;
    }

    public CUnidad getObjUnidad() {
        return objUnidad;
    }

    public void setObjUnidad(CUnidad objUnidad) {
        this.objUnidad = objUnidad;
    }

    public CHorario getObjHorario() {
        return objHorario;
    }

    public void setObjHorario(CHorario objHorario) {
        this.objHorario = objHorario;
    }

    public CProgreso getObjProgreso() {
        return objProgreso;
    }

    public void setObjProgreso(CProgreso objProgreso) {
        this.objProgreso = objProgreso;
    }

    public String getTematema() {
        return tematema;
    }

    public void setTematema(String tematema) {
        this.tematema = tematema;
    }

    public Date getTemafecha() {
        return temafecha;
    }

    public void setTemafecha(Date temafecha) {
        this.temafecha = temafecha;
    }

    public int getMateriacodigo() {
        return materiacodigo;
    }

    public void setMateriacodigo(int materiacodigo) {
        this.materiacodigo = materiacodigo;
    }

    public int getTemahorario() {
        return temahorario;
    }

    public void setTemahorario(int temahorario) {
        this.temahorario = temahorario;
    }

    public int getTemaprogreso() {
        return temaprogreso;
    }

    public void setTemaprogreso(int temaprogreso) {
        this.temaprogreso = temaprogreso;
    }

    public String getTemaobservaciones() {
        return temaobservaciones;
    }

    public void setTemaobservaciones(String temaobservaciones) {
        this.temaobservaciones = temaobservaciones;
    }

    public Date getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Date fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public int getUnidadcodigo() {
        return unidadcodigo;
    }

    public void setUnidadcodigo(int unidadcodigo) {
        this.unidadcodigo = unidadcodigo;
    }

    public Date fechaSistema() {
        return this.fechaactualizacion = new Date();
    }

}
