/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
    Este codigo genera la pagina de bienvenida a la veterinaria
 */
public class welcomePage implements ActionListener {

    /*
        Se crea el JFrame, el boton y los label correspondientes, necesarios
        para el momento de ajustarlo todo en una ventana
     */
    JFrame frame;
    JButton myButton = new JButton("Start");
    JLabel labelWelcome = new JLabel("Welcome to Vet-TIC 2021");
    JLabel labelImage = new JLabel();

    public welcomePage() {

        initComponents();
    }

    public void initComponents() {

        this.frame = new JFrame();
        configFrame();
        configComponents();
        addComponents();
        this.frame.setVisible(true);
    }

    public void configFrame() {
        /* configuraciones de la ventana, primero el tamaño, luego que hacer con el
            boton de cerrado, si es ajustable en su tamaño, el titulo de la ventana
            no configurar ningun layout y finalmente el color de la ventana de blanco
         */
        this.frame.setSize(400, 600);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("Welcome to Vet-TIC");
        this.frame.setLayout(null);
        this.frame.getContentPane().setBackground(Color.WHITE);
        centerFrame();

    }

    public void configComponents() {
        /* Se configura las propiedades del boton, como posicion con setBounds
            el setFocusable quita un recuadro sobre el texto del boton y se adiciona
            el actionlistener para entrar al sistema una vez se pulsa el boton
         */
        myButton.setBounds(120, 500, 150, 50);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        /* Se crea y configura el logo de la aplicacion, el .png esta dentro de 
            la misma carpeta del proyecto
         */
        ImageIcon image = new ImageIcon("paw.png"); // create an image icon
        this.frame.setIconImage(image.getImage()); // set the image as the app image

        /*Se crea un label para encabezar la pagina de bienvenida con un alineamiento
            horizontal centrado, una letra estilo "MV Boli" en negrita y tamaño 20
            y la posicion en el frame especificada en .setBounds
        
         */
        labelWelcome.setHorizontalAlignment(JLabel.CENTER);
        labelWelcome.setFont(new Font("MV Boli", Font.BOLD, 20));
        labelWelcome.setForeground(Color.BLACK);
        labelWelcome.setBounds(50, 0, 300, 100);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("paw.png").getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));
        labelImage.setIcon(imageIcon);
        labelImage.setBounds(0, 80, 400, 400);
        labelImage.setBackground(new Color(123, 50, 250));

    }
    
        /*
        Este metodo adquiere las propiedades de la ventana del terminal en el que se este corriendo el programa
        y hace que la ventana se situe en la mitad de la pantalla del dispositivo en vez de en la esquina superior
     */

    public void centerFrame() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();

        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

    }

    public void addComponents() {
        frame.add(myButton);
        frame.add(labelWelcome);
        frame.add(labelImage);
    }
    
    /*
        este metodo esta encargado de crear la ventana principal una ve se da clic sobre la ventada de bienvenida
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == myButton) {
            frame.dispose();
            principalView mainWindow = new principalView();
        }
    }
}