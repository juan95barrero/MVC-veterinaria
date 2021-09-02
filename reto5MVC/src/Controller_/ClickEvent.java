/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_;

import Access_.mascotaDAO;
import Model_.cita;
import Model_.mascota;
import View_.ControlsPanel;
import View_.ControlsPanel;
import View_.windowManageCita;
import View_.windowManageMascota;
import View_.windowManagePropietario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
    Esta clase implementa la interfaz ActionListener, lo cual permite crear el comportamiento
    de cada boton ubicado en controlspanel, es por esto que lo recibe en el constructor como un
    parametro
 */
public class ClickEvent implements ActionListener {

    private ControlsPanel controlsPanel;

    public ClickEvent(ControlsPanel controlsPanel) {
        this.controlsPanel = controlsPanel;
    }

    /*
        En este punto se da el comportamiento de cada uno de los botones presentes en el controlpanel    
    */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        
        /*
            el primer condicional tiene como objetivo el boton de buscar, primero se trae el 
            identificador de la mascota seleccionada en el cbx de mascotas, luego la fecha seleccionada
            en el cbx de fechas y por ultimo el usuario ingresado en el cuadro de texto y por medio de
            mascotaDAO se gestionan los resultados dado que esta clase mascota fue elegida para modelar
            tanto a la mascota como al dato completo que va a representarse en la tabla
        
        */
        if (actionEvent.getSource() == this.controlsPanel.getBtnSearch()) {
            int idMascota = ((mascota) this.controlsPanel.getCbxMascotaList().getSelectedItem()).getMascotaId();
            String dateRepresentation = ((cita) this.controlsPanel.getCbxCitasList().getSelectedItem()).getCitaFecha();
            String userName = (String) this.controlsPanel.getTxtName().getText();

            mascotaDAO mascDAO = new mascotaDAO();
            ArrayList<mascota> mascotas = mascDAO.obtenerMascotasFiltradas(idMascota, dateRepresentation, userName);

            this.controlsPanel.setTblResults(mascotas);
        }
        
        /*
            Cada uno de los botones de gestion de aca en adelante despliega las ventanas
            para realizar la gestion sobre la base de datos
        
        */

        if (actionEvent.getSource() == this.controlsPanel.getBtnManagePropietario()) {

            windowManagePropietario newWindowPropietario = new windowManagePropietario();
        }
        if (actionEvent.getSource() == this.controlsPanel.getBtnManageMascota()) {

            windowManageMascota newWindowMascota = new windowManageMascota();
            
        }
        if (actionEvent.getSource() == this.controlsPanel.getBtnManageCita()) {

            windowManageCita newWindowCita = new windowManageCita();

        }
    }

}
