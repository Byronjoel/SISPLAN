/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.modelos;

import ec.edu.espoch.sisplan.accesodatos.AccesoDatos;
import ec.edu.espoch.sisplan.accesodatos.ConjuntoResultado;
import ec.edu.espoch.sisplan.clases.CUnidad;
import java.util.ArrayList;
import ec.edu.espoch.sisplan.accesodatos.Parametro;

/**
 * import ec.edu.espoch.softlab.accesodatos.Parametro;
 *
 * @author Usuario
 */
public class MUnidad {

    public static boolean insertarUnidades(CUnidad objUnidad) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lstParamCategoriaBodega = new ArrayList<>();
            String sql = "SELECT sisplan.fn_insert_unidad(?,?)";
            lstParamCategoriaBodega.add(new Parametro(1, objUnidad.getUnidaddescripcion()));
            lstParamCategoriaBodega.add(new Parametro(2, objUnidad.getMateriaid()));

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstParamCategoriaBodega);
            while (rs.next()) {
                if (rs.getBoolean(0)) {
                    respuesta = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }
    
    
    public static boolean eliminarUnidad(int cod) throws Exception {
        boolean respuesta = false;

        try {
            
            String sql = "select * from sisplan.fn_delete_unidad(?)";
            //transfroma 
            ArrayList<Parametro> lstUnidad = new ArrayList<>();
            lstUnidad.add(new Parametro(1, cod));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstUnidad);
            while (rs.next()) {
                if (rs.getBoolean(0) == true)
                {
                    respuesta = true;
                }
            }
            rs = null;
            lstUnidad = null;

        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }
    
     
      public static ArrayList<CUnidad> obtenerUnidad() throws Exception {
        ArrayList<CUnidad> lstUnidad = new ArrayList<>();
        try {
            //inicializamos la sentncia ssql
            String sql = "Select * from sisplan.fn_select_unidad1()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            while(rs.next()){
                CUnidad objA=new CUnidad();
                objA.setUnidadcodigo(rs.getInt(0));
                objA.setUnidaddescripcion(rs.getString(1));
                
                CMateria objM= new CMateria();
                objM.setNombremateria(rs.getString(2));
                objA.setObjCMateria(objM);
                lstUnidad.add(objA);
            }
         rs=null;
        } catch (Exception e) {
            throw e;
        }
        return lstUnidad;
    }

     
    
       public static boolean modificarUnidad(CUnidad dato) throws Exception {
        boolean respuesta = false;
        try {
            ArrayList<Parametro> lst = new ArrayList<>();
            String sql = "SELECT sisplan.fn_update_unidad(?,?);"; 
            lst.add(new Parametro(1, dato.getUnidadcodigo()));
            lst.add(new Parametro(2, dato.getUnidaddescripcion()));
           // lst.add(new Parametro(3, dato.getMateriaid()));
         
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lst);
            while (rs.next()) {
                if (rs.getBoolean(0) == true) {
                    respuesta = true;
                }
            }
            rs = null;
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return respuesta;

    }
    
   
    //select items
    

}
