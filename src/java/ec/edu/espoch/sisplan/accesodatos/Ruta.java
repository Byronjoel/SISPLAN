/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.accesodatos;
import java.net.URL;
/**
 *
 * @author root
 */
public class Ruta {
    //OJO AQUI VA EL PATH RELATIVO DEL ARCHIVO PROPERTIES
    private final String dbOracle="/ec/edu/espoch/sisplan/propiedades/dboracle.properties";
    private final String dbMysql="/ec/edu/espoch/sisplan/propiedades/dbmysql.properties";
    private final String dbPostgres="/ec/edu/espoch/sisplan/propiedades/dbpostgres.properties";
    private final String dbSqlServer="/ec/edu/espoch/sisplan/propiedades/dbsqlserver.properties";
    
    public URL getFileDbOracle(){
        return getClass().getResource(dbOracle);
    }
    
    public URL getFileDbMysql(){
        return getClass().getResource(dbMysql);
    }
    
    public URL getFileDbPostgres(){
        return getClass().getResource(dbPostgres);
    }
    
    public URL getFileDbSqlServer(){
        return getClass().getResource(dbSqlServer);
    }
}
