/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.clases;

/**
 *
 * @author Usuario
 */
/*
idtprogreso serial NOT NULL,
  tprogresodescripcion 
*/
public class CProgreso {
    private int idtprogreso;
    private String tprogresodescripcion;

    public int getIdtprogreso() {
        return idtprogreso;
    }

    public void setIdtprogreso(int idtprogreso) {
        this.idtprogreso = idtprogreso;
    }

    public String getTprogresodescripcion() {
        return tprogresodescripcion;
    }

    public void setTprogresodescripcion(String tprogresodescripcion) {
        this.tprogresodescripcion = tprogresodescripcion;
    }
    
    
    
}
