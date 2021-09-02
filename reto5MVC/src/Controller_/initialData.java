package Controller_;

import Access_.citaDAO;
import Access_.mascotaDAO;
import Access_.propietarioDAO;
import Model_.cita;
import Model_.mascota;
import Model_.propietario;
import java.util.ArrayList;

/*
    Esta clase es la encargada de traer los datos de cada tabla por separado a un arraylist
    por medio de las clases modeladas en el paquete Model_. A traves de los DAO´s se obtienen
    todos los datos correspondientes a propietarios, mascotas y citas cada uno por separado y metodos   
    que permitan acceder  estas listas desde cualquier lugar del proyecto

*/

public class initialData {

    private ArrayList<propietario> propietarios = null;
    private ArrayList<mascota> mascotas = null;
    private ArrayList<cita> citas = null;

    public initialData() {
        
        propietarioDAO propDAO = new propietarioDAO();
        propietarios = propDAO.obtenerPropietarios();
        propietarios.add(new propietario("Todos", "", "",""));
        
        mascotaDAO mascDAO = new mascotaDAO();
        mascotas = mascDAO.obtenerMascotas();
        mascotas.add(new mascota(-1, "Todos", ""));
        
        citaDAO citDAO = new citaDAO();
        citas = citDAO.obtenerCitas();
    }
    
    public ArrayList<propietario> getPropietarios(){
        return propietarios;
    }
    
    public ArrayList<mascota> getMascotas(){
        return mascotas;
    }
    public ArrayList<cita> getCitas(){
        return citas;
    }
    
    
    
}
