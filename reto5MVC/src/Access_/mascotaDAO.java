package Access_;

import Model_.mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.myConnectionDB;


/*
    El DAO de mascota es el encargado de hacer el puente entre la programacion, la vista y 
    la base de datos.

*/

public class mascotaDAO {
    
    /*Este metodo devuelve todas las mascotas que se encuentren en la base de datos,
      pero no solo traen las mascotas, ya que en modelo esta clase sera la encargada de traer toda la informacion
      trae toda la informacion de todas las tablas
    
    */

    public ArrayList<mascota> obtenerMascotas() {
        Connection conn = null;
        ArrayList<mascota> mascotas = new ArrayList<>();

        try {

            conn = myConnectionDB.getConnection();
            String sql = "select mascotaId,  mascotaNombre, propUsuario, propNombre, propApellido, propTelefono, citaFecha, citaDescripcion from propietario natural join mascota natural join cita;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                mascota masc = new mascota(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8));
                mascotas.add(masc);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }

        return mascotas;
    }
    
    /*
        Para obtener solo los datos filtrados segun el id de la  mascota, la fecha seleccionada y 
        el usuario escrito en la vista principal se usa este metodo. primero se evalua si el id es 
        diferente a -1 lo cual adiciona un string a la consulta para hacer el debido filtro,
        igual con la fecha seleccionada para adicionar a la consulta el filtro de fecha y finalmente
        segun los cambios en la variable bandera de filter se introducen en el statement los valores 
        de los parametros del metodo para realizar la consulta
    
    */

    public ArrayList<mascota> obtenerMascotasFiltradas(int id, String fecha, String usuario) {
        Connection conn = null;
        ArrayList<mascota> mascotas = new ArrayList<>();
        int filter = -1;

        try {
            conn = myConnectionDB.getConnection();
            String sql = "select mascotaId, mascotaNombre, propUsuario, propNombre, propApellido,  "
                    + "propTelefono, citaFecha, citaDescripcion from "
                    + "propietario natural join mascota natural join cita where propUsuario like ?";

            if (id != -1) {
                filter = 0;
                sql += " AND mascotaId=?";
            }

            if (fecha != "Todas las citas") {
                filter = 1;
                sql += " AND citaFecha=?";
            }

            sql += ";";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, "%" + usuario + "%");
            if (filter >= 0) {
                statement.setInt(2, id);
            }

            if (filter >= 1) {
                statement.setString(3, fecha);
            }

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                mascotas.add(new mascota(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5),
                        result.getString(6), result.getString(7), result.getString(8)));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }

        return mascotas;
    }
    
    /*
        Este metodo recibe un objeto mascota, luego extrae los valores de los parametros del objeto y mediante una
        consulta inserta en la tabla mascota ese objeto pasado como parametro
    */

    public void insertarMascota(mascota masc) {
        Connection conn = null;

        try {

            conn = myConnectionDB.getConnection();
            String sql = "INSERT INTO mascota VALUES (?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, masc.getMascotaId());
            statement.setString(2, masc.getMascotaNombre());
            statement.setString(3, masc.getPropUsuario());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        }
    }
    
    /*
        La ultima consulta posible es cuando una mascota deja de ser cliente de la veterinaria
        y por ende se desea eliminar, por esto se elimina mediante el identificador unico de la mascota para asegurar un borrado seguro
    */

    public void eliminarMascota(int id) {
        Connection conn = null;

        try {
            conn = myConnectionDB.getConnection();
            String sql = "delete from mascota where mascotaId=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue borrado exitosamente !");
            }
        } catch (SQLException ex) {

            if (ex.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : Existen citas asociadas a esta mascota, elimine esta"
                        + "\n             dependencia para poder eliminar la mascota ");
            } else {
                JOptionPane.showMessageDialog(null, "Codigo : " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
    }
}
