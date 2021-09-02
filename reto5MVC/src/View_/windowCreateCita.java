package View_;

import Access_.citaDAO;
import Model_.cita;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*  *Esta ventana presenta labels y cuadros de texto para manejar crear los propietarios
    *en la base de datos.
 */
public class windowCreateCita implements ActionListener {

    JFrame frame = new JFrame();

    /*
        crea un JFrame y la misma clase implementa la interfaz ActionListener para
        interactuar con los botones presentes
    
        Se crean dos paneles, uno para manejar los cuadros de texto y otro para manejar
        los botones separandolos asi dentro de la interfaz.
    
        Se crean los label requeridos para actualizar y eliminar registros de la tabla propietarios
        una vez se intenta insertar un dato de propietario se desplegara otra ventana para esto exclusivamente
     */
    private JPanel ManagementPanel = new JPanel();
    private JPanel BtnPanel = new JPanel();
    private JLabel lblCitaId = new JLabel("ID");
    private JLabel lblCitaFecha = new JLabel("Fecha (YYYY-MM-DD HH:MM:SS)");
    private JLabel lblCitaDescripcion = new JLabel("Descripcion");
    private JLabel lblMascotaId = new JLabel("Id de mascota");
    private JButton btnCreate = new JButton("Crear");
    private JTextField txtID = new JTextField();
    private JTextField txtFecha = new JTextField();
    private JTextField txtDescripcion = new JTextField();
    private JTextField txtMascotaId = new JTextField();

    public windowCreateCita() {
        initComponents();
    }

    private void initComponents() {
        this.frame = new JFrame();
        configFrame();
        centerFrame();
        this.frame.setVisible(true);
    }

    private void configFrame() {

        /*
        En el metodo configFrame se setea el icono de la aplicacion, el tamaño de la ventana,
        se desabilita la posibilidad de ajustar su tamaño, se setea el titulo y se define un GridLayout
        para la ventana lo cual la separa en 2 filas y 1 columna con una separacion horizontal de 20 y una vertical de 30
    
         */
        ImageIcon image = new ImageIcon("paw.png"); // create an image icon
        this.frame.setIconImage(image.getImage()); // set the image as the app image

        this.frame.setSize(450, 300);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("Cita Creation");
        this.frame.setLayout(new GridLayout(2, 1, 20, 30));
        this.frame.getContentPane().setBackground(Color.WHITE);

        /*
            En este punto se adiciona a cada boton la posibilidad de ser escuchados
            cada que se de click sobre ellos
         */
        this.btnCreate.addActionListener(this);

        /*
            las configuraciones del panel donde se van a situar los labels y los textbox
            se configuran en este punto donde cada txtbox toma un tamaño para la interfaz
            y se adicionan los elementos mencionados anteriormente al ManagePanel, finalmente
            se agrega el ManagePanel a la ventana principal haciendolo visible
         */
        this.ManagementPanel.setLayout(new FlowLayout());
        this.ManagementPanel.setBackground(Color.WHITE);
        this.txtID.setPreferredSize(new Dimension(90, 20));
        this.txtFecha.setPreferredSize(new Dimension(90, 20));
        this.txtDescripcion.setPreferredSize(new Dimension(90, 20));
        this.txtMascotaId.setPreferredSize(new Dimension(90, 20));

        this.ManagementPanel.add(lblCitaId);
        this.ManagementPanel.add(txtID);
        this.ManagementPanel.add(lblCitaFecha);
        this.ManagementPanel.add(txtFecha);
        this.ManagementPanel.add(lblCitaDescripcion);
        this.ManagementPanel.add(txtDescripcion);
        this.ManagementPanel.add(lblMascotaId);
        this.ManagementPanel.add(txtMascotaId);

        this.frame.add(ManagementPanel);

        /*
            Al igual que en el punto anterior se adicionan los botones, pero esta vez al panel
            de botones y finalmente esste panel se adiciona a la ventana principal
        
         */
        this.BtnPanel.setBackground(Color.white);
        this.BtnPanel.add(btnCreate);

        this.frame.add(BtnPanel);
    }

    /*
        Este metodo adquiere las propiedades de la ventana del terminal en el que se este corriendo el programa
        y hace que la ventana se situe en la mitad de la pantalla del dispositivo en vez de en la esquina superior
     */
    private void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();

        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
    
    /*
        Este metodo sirve para escuchar el evento click en los botones, en este caso
        unicamente el boton de crear que utiliza los datos de ID, la fecha de la cita, 
        la descripcion de la cita y la amscotaId correspondiente para crear la cita en la base de datos
    
    */

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.btnCreate) {
            citaDAO citDAO = new citaDAO();

            int citaId = Integer.valueOf(this.txtID.getText());
            String citaFecha = (String) this.txtFecha.getText();
            String citaDescripcion = (String) this.txtDescripcion.getText();
            int mascotaId = Integer.valueOf(this.txtMascotaId.getText());

            cita cit = new cita(citaId, citaFecha, citaDescripcion, mascotaId);

            citDAO.insertarCita(cit);
        }
    }

}
