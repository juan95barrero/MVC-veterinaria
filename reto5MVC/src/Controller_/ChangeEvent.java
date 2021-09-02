
package Controller_;

import Access_.citaDAO;
import Model_.cita;
import Model_.mascota;
import View_.ControlsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*

    Esta clase implementa la interfaz actionlistener para manejar los eventos de cambio sobre los cbx
    presentes en controlsPanel que recibe como parametro
 */
public class ChangeEvent implements ActionListener {

    private ControlsPanel controlsPanel;

    public ChangeEvent(ControlsPanel controlsPanel) {
        this.controlsPanel = controlsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        
        /*
            Lo primero que hace es si en el cbx de mascota se selecciona algo diferente a todos
            que tiene asignado el identificador -1 asigna a la lista citas por medio de citDao
            todas las citas dentro de la base de datos, si por el contrario se selecciono alguna mascota
            a la lista citas le asigna solo las citas filtradas por mascotay finalmente se setea en el cbx
            de las citas la lista segun sea el caso
        */
        if (actionEvent.getSource() == this.controlsPanel.getCbxMascotaList()) {
            citaDAO citDAO = new citaDAO();
            ArrayList<cita> citas = null;
            int mascota = ((mascota) this.controlsPanel.getCbxMascotaList().getSelectedItem()).getMascotaId();
            if (mascota == -1) {
                citas = citDAO.obtenerCitas();
                citas.add(0, new cita(-1, "Todas las citas", "", -1));
            } else {
                citas = citDAO.obtenerCitasFiltradas(mascota);
                citas.add(0, new cita(-1, "Todas las citas", "", -1));
            }
            this.controlsPanel.setCbxCitasList(citas);
        }
    }

}
