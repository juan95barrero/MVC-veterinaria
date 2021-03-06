package View_;

import Access_.propietarioDAO;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*  *Esta ventana presenta labels y cuadros de texto para manejar los propietarios
    *de la base de datos.
*/

public class windowManagePropietario implements ActionListener {

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
    private JLabel lblPropUsuario = new JLabel("Usuario a actualizar o eliminar");
    private JLabel lblPropTelefono = new JLabel("Telefono a actualizar");
    private JButton btnUpdate = new JButton("Actualizar");
    private JButton btnDelete = new JButton("Eliminar");
    private JButton btnCreate = new JButton("Crear");
    private JTextField txtUsuario = new JTextField();
    private JTextField txtTelefono = new JTextField();
    

    public windowManagePropietario() {

        initComponents();
    }

    private void initComponents() {
        this.frame = new JFrame();
        configFrame();
        centerFrame();
        this.frame.setVisible(true);
    }
    
    /*
        En el metodo configFrame se setea el icono de la aplicacion, el tama??o de la ventana,
        se desabilita la posibilidad de ajustar su tama??o, se setea el titulo y se define un GridLayout
        para la ventana lo cual la separa en 2 filas y 1 columna con una separacion horizontal de 20 y una vertical de 30
    
    */

    private void configFrame() {
        
        
        
        ImageIcon image = new ImageIcon("paw.png"); // create an image icon
        this.frame.setIconImage(image.getImage()); // set the image as the app image
        
        this.frame.setSize(400, 300);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("Propietario Management");
        this.frame.setLayout(new GridLayout(2, 1, 20, 30));
        this.frame.getContentPane().setBackground(Color.WHITE);
        
        /*
            En este punto se adiciona a cada boton la posibilidad de ser escuchados
            cada que se de click sobre ellos
        */
        this.btnUpdate.addActionListener(this);
        this.btnDelete.addActionListener(this);
        this.btnCreate.addActionListener(this);

        
        /*
            las configuraciones del panel donde se van a situar los labels y los textbox
            se configuran en este punto donde cada txtbox toma un tama??o para la interfaz
            y se adicionan los elementos mencionados anteriormente al ManagePanel, finalmente
            se agrega el ManagePanel a la ventana principal haciendolo visible
        */
        this.ManagementPanel.setLayout(new FlowLayout());
        this.ManagementPanel.setBackground(Color.WHITE);
        this.txtUsuario.setPreferredSize(new Dimension(90, 20));
        this.txtTelefono.setPreferredSize(new Dimension(90, 20));
        this.ManagementPanel.add(lblPropUsuario);
        this.ManagementPanel.add(txtUsuario);
        this.ManagementPanel.add(lblPropTelefono);
        this.ManagementPanel.add(txtTelefono);

        this.frame.add(ManagementPanel);

        /*
            Al igual que en el punto anterior se adicionan los botones, pero esta vez al panel
            de botones y finalmente esste panel se adiciona a la ventana principal
        
        */
        this.BtnPanel.setBackground(Color.white);
        this.BtnPanel.add(btnUpdate);
        this.BtnPanel.add(btnDelete);
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
        Este metodo permite escuchar a los botones cuando se de click sobre ellos
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        /*
            Este condicional escucha al boton encargado de la actualizacion de datos de 
            propietarios, en donde se usan los datos Usuario y Telefono, el usuario
            para buscar el registro a actualizar y el telefono seria el unico registro
            disponible para cambiar
        
        */
        if (ae.getSource() == this.btnUpdate) {
            propietarioDAO propDAO = new propietarioDAO();
            try {
                String propUsuario = (String) this.txtUsuario.getText();
                String propTelefono = (String) this.txtTelefono.getText();

                propDAO.actualizarPropietario(propUsuario, propTelefono);

            } catch (Error e) {
                JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
            }
        }
        /*
           este condicional escucha al boton de borrar registros de usuario, por lo
            que solo es necesario el usuario que se va a borrar para acceder a la base de datos
            y eliminar las filas
        */
        if (ae.getSource() == this.btnDelete) {
            propietarioDAO propDAO = new propietarioDAO();

            String propUsuario = (String) this.txtUsuario.getText();

            propDAO.eliminarPropietario(propUsuario);
        }
        /*
            Este ultimo condicional escucha al boton crear, lo cual genera una ventana diferente
            con campos diferentes disponibles para la creacion de un usuario
        */
        if(ae.getSource() == this.btnCreate){
            windowCreateUsuario createUsuario = new windowCreateUsuario();
        }
    }
}
