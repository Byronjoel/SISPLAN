/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.modelos;

import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.accesodatos.Parametro;
import ec.edu.espoch.sisplan.clases.CClase;
import ec.edu.espoch.sisplan.clases.CFechasInvalidas;
import ec.edu.espoch.sisplan.clases.CHorario;
import ec.edu.espoch.sisplan.clases.CMateria;
import ec.edu.espoch.sisplan.clases.CProgreso;
import ec.edu.espoch.sisplan.clases.CUnidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class MClase {

    public static boolean insertarClases(CClase objClase) throws Exception {
        boolean respuesta = false;
        boolean verifica = true;
        try {
            ArrayList<Parametro> lstParamClase = new ArrayList<>();
            java.sql.Date sqlDate1 = new java.sql.Date(objClase.getTemafecha().getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(objClase.fechaSistema().getTime());

            //comparar fechas
            String sql1 = "SELECT *FROM sisplan.fn_select_fechasinvalidasverifica(?)";
            ArrayList<Parametro> lstParamDatosUnidad = new ArrayList<>();

            lstParamDatosUnidad.add(new Parametro(1, sqlDate1));

            ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1, lstParamDatosUnidad);

            // ConjuntoResultado rs1 = AccesoDatos.ejecutaQuery(sql1);
            while (rs1.next()) {
                CFechasInvalidas fecha = new CFechasInvalidas();
                fecha.setTfechasinvalidascodigo(rs1.getInt(0));
                fecha.setTfechasinvalidasfecha(rs1.getDate(1));
                fecha.setTfechasinvalidasdescripcion(rs1.getString(2));
                verifica = false;
            }

            if (verifica) {
                String sql = "SELECT sisplan.fn_insert_clase(?,?,?,?,?,?,?)";

                lstParamClase.add(new Parametro(1, objClase.getTematema()));
                lstParamClase.add(new Parametro(2, sqlDate1));
                lstParamClase.add(new Parametro(3, objClase.getObjHorario().getIdthorario()));
                lstParamClase.add(new Parametro(4, objClase.getObjProgreso().getIdtprogreso()));
                lstParamClase.add(new Parametro(5, objClase.getTemaobservaciones()));
                lstParamClase.add(new Parametro(6, sqlDate2));
                lstParamClase.add(new Parametro(7, objClase.getObjUnidad().getUnidadcodigo()));

                ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamClase);
                while (rs.next()) {
                    if (rs.getBoolean(0)) {
                        respuesta = true;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }
    //clases cargar

    public static List<CClase> cargarClas(CClase objClase) throws Exception {
        List<CClase> lstReserva = new ArrayList<>();
        try {
            String sql = "select * from sisplan.fn_select_clase(?)";

            ArrayList<Parametro> lstParamDatosUnidad = new ArrayList<>();

            lstParamDatosUnidad.add(new Parametro(1, objClase.getObjMateria().getIdcarrera()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUnidad);
            // java.sql.Date sqlDate = new java.sql.Date(reserva.getFechaReserva().getTime());
            //java.sql.Date sqlDate1 = new java.sql.Date(reserv.getFechaRetiro().getTime());
            //ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while (rs.next()) {
                CClase reserva = new CClase();

                //codigo cclase
                reserva.setMateriacodigo(0);
                //descripcion unidad
                CUnidad objU = new CUnidad();
                objU.setUnidaddescripcion(rs.getString(1));
                reserva.setObjUnidad(objU);
                //descripcion tema                      
                reserva.setTematema(rs.getString(2));//setCodigo(rs.getLong(0));
                //fecha de clas
                reserva.setTemafecha(rs.getDate(3));;
                //descripcion horario
                CHorario objH = new CHorario();
                objH.setThorariohora(rs.getString(4));
                reserva.setObjHorario(objH);
                //observaicioens
                reserva.setTemaobservaciones(rs.getString(5));
                //fecha actualizacion
                reserva.setFechaactualizacion(rs.getDate(6));
                //progreso descripcion  

                CProgreso objP = new CProgreso();
                objP.setTprogresodescripcion(rs.getString(7));
                reserva.setObjProgreso(objP);

                lstReserva.add(reserva);
            }
            rs = null;
        } catch (Exception e) {
            lstReserva.clear();
            throw e;
        }
        return lstReserva;
    }

    public static List<CClase> cargarClaseByMateria(int idMateria) throws Exception {
        List<CClase> lstReserva = new ArrayList<>();
        try {
            String sql = "select * from sisplan.fn_select_tema_by_materia(?)";

            ArrayList<Parametro> lstParamDatosUnidad = new ArrayList<>();

            lstParamDatosUnidad.add(new Parametro(1, idMateria));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamDatosUnidad);
            while (rs.next()) {
                CClase reserva = new CClase();
                reserva.setIdTema(rs.getInt(0));
                reserva.setTematema(rs.getString(1));
                lstReserva.add(reserva);
            }
            rs = null;
        } catch (Exception e) {
            lstReserva.clear();
            throw e;
        }
        return lstReserva;
    }

}
