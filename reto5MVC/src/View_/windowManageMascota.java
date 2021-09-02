package View_;

import Access_.mascotaDAO;
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

public class windowManageMascota implements ActionListener {

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
    private JLabel lblNombreMascota = new JLabel("ID de Mascota a eliminar");
    private JButton btnDelete = new JButton("Eliminar");
    private JButton btnCreate = new JButton("Crear");
    private JTextField txtMascota = new JTextField();

    public windowManageMascota() {

        initComponents();
    }

    private void initComponents() {
        this.frame = new JFrame();
        configFrame();
        centerFrame();
        this.frame.setVisible(true);
    }

    /*
        En el metodo configFrame se setea el icono de la aplicacion, el tamaño de la ventana,
        se desabilita la posibilidad de ajustar su tamaño, se setea el titulo y se define un GridLayout
        para la ventana lo cual la separa en 2 filas y 1 columna con una separacion horizontal de 20 y una vertical de 30
    
     */
    private void configFrame() {

        ImageIcon image = new ImageIcon("paw.png"); // create an image icon
        this.frame.setIconImage(image.getImage()); // set the image as the app image

        this.frame.setSize(400, 300);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("Mascota Management");
        this.frame.setLayout(new GridLayout(2, 1, 20, 30));
        this.frame.getContentPane().setBackground(Color.WHITE);

        /*
            En este punto se adiciona a cada boton la posibilidad de ser escuchados
            cada que se de click sobre ellos
         */
        this.btnDelete.addActionListener(this);
        this.btnCreate.addActionListener(this);

        /*
            las configuraciones del panel donde se van a situar los labels y los textbox
            se configuran en este punto donde cada txtbox toma un tamaño para la interfaz
            y se adicionan los elementos mencionados anteriormente al ManagePanel, finalmente
            se agrega el ManagePanel a la ventana principal haciendolo visible
         */
        this.ManagementPanel.setLayout(new FlowLayout());
        this.ManagementPanel.setBackground(Color.WHITE);
        this.txtMascota.setPreferredSize(new Dimension(90, 20));
        this.ManagementPanel.add(lblNombreMascota);
        this.ManagementPanel.add(txtMascota);

        this.frame.add(ManagementPanel);

        /*
            Al igual que en el punto anterior se adicionan los botones, pero esta vez al panel
            de botones y finalmente esste panel se adiciona a la ventana principal
        
         */
        this.BtnPanel.setBackground(Color.white);
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
        Este metodo permite escuchar a los botones cuando se de click sobre ellos, en este caso 
        las mascotas en si no son actualizables ya que no cambian de nombre, no cambian de dueño
        el id es unico y no se debe cambiar
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        /*
            Este condicional escucha al boton encargado de la eliminacion de datos de 
            mascota, en donde se usa el dato de identificador unico de la mascota, para
            asi asegurar la eliminacion de la mascota deseada
        
         */
        if (ae.getSource() == this.btnDelete) {
            mascotaDAO mascDAO = new mascotaDAO();

            int id = Integer.valueOf(this.txtMascota.getText());

            mascDAO.eliminarMascota(id);
        }
        /*
            Este ultimo condicional escucha al boton crear, lo cual genera una ventana diferente
            con campos diferentes disponibles para la creacion de una mascota
         */
        if (ae.getSource() == this.btnCreate) {
            windowCreateMascota createMascota = new windowCreateMascota();
        }
    }

}
