package View_;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class principalView {

    JFrame frame = new JFrame();

    public principalView() {

        initComponents();
    }

    private void initComponents() {
        this.frame = new JFrame();
        configFrame();
        setPanel();
        this.frame.setVisible(true);

    }
    /*
        Este metodo crea un objeto results panel que es el encargado de crear la tabla
        los controles y botones para mostrar la base de datos segun criterio de busqueda
        y lo adiciona como un controlpanel dentro del frame
    */
    private void setPanel() {
        ResultsPanel resultsPanel = new ResultsPanel();
        this.frame.setContentPane(resultsPanel);
        this.frame.add(new ControlsPanel(resultsPanel));

    }

    private void configFrame() {
        /* configuraciones de la ventana, primero el tamaño, luego que hacer con el
            boton de cerrado, si es ajustable en su tamaño, el titulo de la ventana
            no configurar ningun layout y finalmente el color de la ventana de blanco
         */

        ImageIcon image = new ImageIcon("paw.png"); // create an image icon
        this.frame.setIconImage(image.getImage()); // set the image as the app image

        this.frame.setSize(600, 800);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("Welcome to Vet-TIC 2021");
        this.frame.setLayout(new GridLayout(2, 2));
        this.frame.getContentPane().setBackground(Color.WHITE);

        centerFrame();
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

}
