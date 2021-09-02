/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_;

import Controller_.ClickEvent;
import Controller_.ChangeEvent;
import Controller_.initialData;
import Model_.cita;
import Model_.mascota;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
    Esta clase hereda el comportamiento de un Jpanel por lo que en el se adicionaran ciertos componentes fraficos
    como lo son lblMascota, lblCitas y sus cbx Correspondientes junto con el boton que desencadena el filtro a aplicar a la
    tabla
    
    En un segundo panel los 3 botones correspondientes al manejo de la base de datos los cuales son manejar propietarios
    mascotas y citas
*/
public class ControlsPanel extends JPanel {
    
    private JPanel ManagementPanel;
    private JPanel SearchPanel;
    private JLabel lblMascota;
    private JComboBox<mascota> cbxMascotaList;
    private JLabel lblCitas;
    private JComboBox<cita> cbxCitasList;
    private JLabel lblName;
    private JTextField txtName;
    private JButton btnSearch;
    private JButton btnManagePropietario;
    private JButton btnManageMascota;
    private JButton btnManageCita;
    private JTable tblResults;
    
    /*
        El constructor recibe la tabla creada como parametro de ingreso.
    */

    public ControlsPanel(ResultsPanel resultsPanel) {
        this.tblResults = resultsPanel.getTblResults();
        initComponents();
    }
    
    /*
        Se crea un GridLayout de 3 filas y 1 columna distribuidos como tabla de resultados,
        botones de manejo de filtros y botones de manejo de base de datos
    
    */

    private void initComponents() {
        setLayout(new GridLayout(3, 1,0, 150));
        initialData initialData = new initialData();

        this.setTblResults(initialData.getMascotas());
        
        this.SearchPanel = new JPanel();
        this.SearchPanel.setLayout(new GridLayout(2, 2,0,15));

        this.lblMascota = new JLabel("Mascotas");
        this.SearchPanel.add(this.getLlblMascotas());
        
        
        /*
            El cbx de mascotas recibe toda la data inicial de mascotas en la base de datos
            sin embargo en la clase initialData se hacen unos joins para no solo mostrar mascotas
            tambien mostrar las tablas de citas y propietarios para asi acceder a todos los datos
            existentes 
        
        */

        this.cbxMascotaList = new JComboBox();
        this.cbxMascotaList.setModel(new DefaultComboBoxModel<>(initialData.getMascotas().toArray(new mascota[initialData.getMascotas().size()])));
        this.cbxMascotaList.setSelectedIndex(0);
        this.SearchPanel.add(this.getCbxMascotaList());

        ChangeEvent changeEvent = new ChangeEvent(this);
        this.getCbxMascotaList().addActionListener(changeEvent);

        // Exhibition selection
        this.lblCitas = new JLabel("Citas");
        this.SearchPanel.add(this.getLblCitas());
        
        
        /*
            inicialmente el cbx de citas tiene todas las citas iniciales, sin embargo en la linea 91
            al seleccionar una mascota se hacen filtros para mostrar solo las citas corespondientes a esa mascota
        
        */

        this.cbxCitasList = new JComboBox();
        this.setCbxCitasList(initialData.getCitas());
        this.SearchPanel.add(this.getCbxCitasList());

        this.lblName = new JLabel("Nombre del propietario");
        this.SearchPanel.add(this.getLblName());

        this.txtName = new JTextField();
        this.SearchPanel.add(this.getTxtName());

        // Search button
        this.btnSearch = new JButton("Buscar");
        this.SearchPanel.add(this.btnSearch);
        
        
        add(this.SearchPanel);
        
        
        /* De aqui en adelante se manejan los botones de gestion de la base de datos
            modificar propietarios, modificar mascotas o modificar citas
        
        */
        
        this.ManagementPanel = new JPanel();
        this.ManagementPanel.setLayout(new FlowLayout());
        
        this.btnManagePropietario = new JButton("Modificar Propietarios");
        this.btnManageMascota = new JButton("Modificar Mascotas");
        this.btnManageCita = new JButton("Modificar Citas");
        
        this.ManagementPanel.add(this.btnManagePropietario);
        this.ManagementPanel.add(this.btnManageMascota);
        this.ManagementPanel.add(this.btnManageCita);
        

        add(this.ManagementPanel);
        
        /*
            en este punto se adiciona la opcion de escuchar a los botones de la vista principal
            para setear su comportamiento en la clase clickEvent
        */

        ClickEvent clickEvent = new ClickEvent(this);
        this.btnSearch.addActionListener(clickEvent);
        
   
        this.btnManagePropietario.addActionListener(clickEvent);
        this.btnManageMascota.addActionListener(clickEvent);
        this.btnManageCita.addActionListener(clickEvent);

    }

    public JButton getBtnManagePropietario() {
        return btnManagePropietario;
    }

    public JButton getBtnManageMascota() {
        return btnManageMascota;
    }

    public JButton getBtnManageCita() {
        return btnManageCita;
    }

    public JLabel getLblMascota() {
        return lblMascota;
    }

    public JComboBox getCbxMascotaList() {
        return cbxMascotaList;
    }

    public JLabel getLblCitas() {
        return lblCitas;
    }

    public JComboBox getCbxCitasList() {
        return cbxCitasList;
    }

    public JLabel getLblName() {
        return lblName;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JTable getTblResults() {
        return tblResults;
    }
    /*
        Al momento de hacer el filtro de mascota nombre, este metodo se hace util ya que en ve de poblar
        el cbx de citas con todas las citas de la base de datos, se va a poblar unicamente con las correspondientes a la mascota seleccionada
    */
    public void setCbxCitasList(ArrayList<cita> citas) {
        this.cbxCitasList.setModel(new DefaultComboBoxModel<>(citas.toArray(new cita[citas.size()])));
        this.getCbxCitasList().setSelectedIndex(0);
    }


    public void setBtnManagepropietario(JButton btnAddpropietario) {
        this.btnManagePropietario = btnAddpropietario;
    }
    
    /*
        Para poblar la tabla se utiliza este metodo el cual primero crea un array con los header de la tabla en el orden en que se van a mostrar
        definidos por el metodo toArray de la clase mascota, luego se limpia la tabla y se procede a llenar linea a linea
        con los datos de mascota pasados como parametros
    
    */

    public void setTblResults(ArrayList<mascota> mascotas) {
        String[] headers = {"ID", "Nombre", "Usuario", "Nombre Propietario", "Apellido propietario", "Telefono propietario", "Fecha cita", "Descripcion cita"};
        this.tblResults.removeAll();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(headers);
        this.tblResults.setModel(tableModel);
        for (int i = 0; i < mascotas.size(); i++) {
            tableModel.addRow(mascotas.get(i).toArray());
        }
    }

    private JLabel getLlblMascotas() {
        return lblMascota;
    }
}
