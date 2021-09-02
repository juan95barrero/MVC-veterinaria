package Access_;

import Model_.cita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.myConnectionDB;


/*
    El DAO de cita es el encargado de hacer el puente entre la programacion, la vista y 
    la base de datos.

*/
public class citaDAO {
    
    /*
        Este metodo retorna una lista con objetos cita por lo cual hace la consulta almacenada
        en la variable sql y se seleccionan 3 campos de la tabla cita. el while es el encargado de crear
        cada objeto cita para luego almacenarlo en la lista citas
    */

    public ArrayList<cita> obtenerCitas() {
        Connection conn = null;
        ArrayList<cita> citas = new ArrayList<>();

        try {

            conn = myConnectionDB.getConnection();
            String sql = "select citaId, citaFecha, citaDescripcion, mascotaId from cita";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                cita cit = new cita(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
                citas.add(cit);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }
        return citas;
    }
    
    /*
        al igual que el metodo anterior por medio de una consulta se actualiza un campo de la base de datos
        el metodo recibe 3 parametros, el nombre de la mascota, el usuario del propietario, y la descripcion
        que se va a modificar junto con la fecha para no modificar todas las citas de una mascota, si no especificamente
        la de un dia en especial
    
    */

    public void actualizarCita(String mascotaNombre, String propUsuario, String fecha, String descripcionUpdate) {
        Connection conn = null;

        try {

            conn = myConnectionDB.getConnection();
            String sql = "update cita natural join mascota set citaDescripcion = ? where "
                    + "mascotaNombre=? and propUsuario =? and citaFecha = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, descripcionUpdate);
            statement.setString(2, mascotaNombre);
            statement.setString(3, propUsuario);
            statement.setString(4, fecha);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue actualizado exitosamente");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }
    }
    
    /*
        Para este metodo es necesario el nombre de la mascota que se puede repetir y el usuario del propietario
        el cual sera el diferenciador para eliminar las citas de una mascota en especifico
    */
    public void eliminarCita(String nombreMascota, String propUsuario) {
        Connection conn = null;

        try {
            conn = myConnectionDB.getConnection();
            String sql = "delete cita from cita natural join mascota where mascotaNombre=? and propUsuario =?;";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, nombreMascota);
            statement.setString(2, propUsuario);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue borrado exitosamente !");
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }
    }
    /*
        Para insertar una cita se recibe un objeto de tipo cita y se corre la consulta almacenada en sql,
        finalmente se obtienen cada una de las propiedades del objeto y se reemplazan en los interrogantes 
    */
    public void insertarCita(cita cit) {
        Connection conn = null;

        try {
            conn = myConnectionDB.getConnection();
            String sql = "insert into cita values(?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, cit.getCitaId());
            statement.setString(2, cit.getCitaFecha());
            statement.setString(3, cit.getCitaDescripcion());
            statement.setInt(4, cit.getMascotaId());

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }
    }
    
    /*
        Este metodo devuelve una lista de todas las citas filtradas segun el id de la mascota que se pase como parametro
        esto con el fin de revisar el historial de una sola mascota
    */
    
     public ArrayList<cita> obtenerCitasFiltradas(int id) {
        Connection conn = null;
        ArrayList<cita> citas = new ArrayList<>();


        try {
            conn = myConnectionDB.getConnection();
            String sql = "select citaId,  citaFecha, citaDescripcion, mascotaId from cita where mascotaId=?";


            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id );
           
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                citas.add(new cita(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4)));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }

        return citas;
    }
}

