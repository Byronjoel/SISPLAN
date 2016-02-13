/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.sisplan.clases;

/**
 *
 * @author Usuario
 *//*
idthorario serial NOT NULL,
  thorariohora character varying(45),
*/
public class CHorario {
    
    private int idthorario;
    private String thorariohora;

    public int getIdthorario() {
        return idthorario;
    }

    public void setIdthorario(int idthorario) {
        this.idthorario = idthorario;
    }

    public String getThorariohora() {
        return thorariohora;
    }

    public void setThorariohora(String thorariohora) {
        this.thorariohora = thorariohora;
    }
    
}
