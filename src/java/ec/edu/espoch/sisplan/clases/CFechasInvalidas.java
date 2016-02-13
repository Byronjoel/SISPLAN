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
/*
tfechasinvalidascodigo serial NOT NULL,
  tfechasinvalidasfecha date NOT NULL,
  tfechasinvalidasdescripcion 
*/
public class CFechasInvalidas {
    private int tfechasinvalidascodigo;

    public int getTfechasinvalidascodigo() {
        return tfechasinvalidascodigo;
    }

    public void setTfechasinvalidascodigo(int tfechasinvalidascodigo) {
        this.tfechasinvalidascodigo = tfechasinvalidascodigo;
    }

    public Date getTfechasinvalidasfecha() {
        return tfechasinvalidasfecha;
    }

    public void setTfechasinvalidasfecha(Date tfechasinvalidasfecha) {
        this.tfechasinvalidasfecha = tfechasinvalidasfecha;
    }

    public String getTfechasinvalidasdescripcion() {
        return tfechasinvalidasdescripcion;
    }

    public void setTfechasinvalidasdescripcion(String tfechasinvalidasdescripcion) {
        this.tfechasinvalidasdescripcion = tfechasinvalidasdescripcion;
    }
    private Date tfechasinvalidasfecha;
    private String tfechasinvalidasdescripcion;
    
    
}
