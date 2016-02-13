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
    
    //select items
    

}
