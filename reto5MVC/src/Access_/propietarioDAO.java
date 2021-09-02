package Access_;

import Model_.propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.myConnectionDB;


/*
    El DAO de propietario es el encargado de hacer el puente entre la programacion, la vista y 
    la base de datos.

*/

public class propietarioDAO {
    
    /*
        Este metodo es el encargado de traer todos los propietarios existentes, por esto devuelve una lista
        de propietarios, todos traidos mediante la consulta de sql
    */

    public ArrayList<propietario> obtenerPropietarios() {

        Connection conn = null;

        ArrayList<propietario> propietarios = new ArrayList<>();

        try {

            conn = myConnectionDB.getConnection();
            String sql = "select propUsuario, propApellido, propNombre, propTelefono from propietario";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                propietario prop = new propietario(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
                propietarios.add(prop);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }

        return propietarios;
    }
    
    /*
        Si se desea traer un unico propietario, solo se debe implementar este metodo, el cual retornara un objeto de tipo
        propietario
    */

    public propietario obtenerPropietario(String user) {

        Connection conn = null;
        propietario prop = null;

        try {

            conn = myConnectionDB.getConnection();
            String sql = "select propUsuario, propApellido, propNombre, propTelefono from propietario where propUsuario = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user);

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                prop = new propietario(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }

        return prop;
    }
    
    /*
        Ya que el propietario no va a cambiar de Usuario ya que es unico, ni de nombre o apellido, el unico dato que se puede actualizar
        es el de telefono. por esot mediante la consulta almacenada en sql se selecciona el usuario correspondiente en la tabla propietario y
        se reemplaza su numero de telefono
    */

    public void actualizarPropietario(String propUsuario, String propTelefono) {
        Connection conn = null;

        try {

            conn = myConnectionDB.getConnection();
            String sql = "UPDATE propietario SET propTelefono=? WHERE propUsuario=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, propTelefono);
            statement.setString(2, propUsuario);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue actualizado exitosamente");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }
    }
    
    /*
        para eliminar un propietario solo hace falta su usuario unico que lo identifica y por medio de la consulta
        se hace la eliminacion
    */

    public void eliminarPropietario(String user) {
        Connection conn = null;

        try {

            conn = myConnectionDB.getConnection();
            String sql = "delete from propietario where propUsuario=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue borrado exitosamente !");
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : Existen mascotas asociadas a este usuario, elimine esta"
                        + "\n             dependencia para poder eliminar el usuario ");
            } else {
                JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
    }
    
    /*  
        Para insertar un propietario es necesario contar ocn todos los campos de propietario
        por esto se recibe un objeto de tipo propietario, se adquieren todos los valores y se corre la consulta
        una vez se conecta con la base de datos
    
    */

    public void insertarPropietario(propietario prop) {

        Connection conn = null;

        try {

            conn = myConnectionDB.getConnection();
            String sql = "INSERT INTO propietario VALUES (?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, prop.getPropUsuario());
            statement.setString(2, prop.getPropApellido());
            statement.setString(3, prop.getPropNombre());
            statement.setString(4, prop.getPropTelefono());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }
    }
}
