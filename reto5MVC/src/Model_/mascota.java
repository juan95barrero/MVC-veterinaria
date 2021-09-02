package Model_;

/*
    Esta clase mascota tiene la peculiaridad de que describe tanto a la mascota en si
    como al objeto con el que se va a poblar toda la tabla, es por esto que tiene las propiedades
    unicamente de mascota y las del resto de tablas para poder almacenar todos los datos
*/

public class mascota {

    private int     mascotaId;
    private String  mascotaNombre;
    private String  propUsuario;
    private String  propNombre;
    private String  propApellido;
    private String  propTelefono;
    private String  citaFecha;
    private String  citaDescripcion;

    public mascota(int mascotaId, String mascotaNombre, String propUsuario, String propNombre, String propApellido, String propTelefono, String citaFecha, String citaDescripcion) {
        this.mascotaId = mascotaId;
        this.mascotaNombre = mascotaNombre;
        this.propUsuario = propUsuario;
        this.propNombre = propNombre;
        this.propApellido = propApellido;
        this.propTelefono = propTelefono;
        this.citaFecha = citaFecha;
        this.citaDescripcion = citaDescripcion;
    }

    public mascota(int mascotaId, String mascotaNombre, String propUsuario) {
        this.mascotaId = mascotaId;
        this.mascotaNombre = mascotaNombre;
        this.propUsuario = propUsuario;
    }
    
    
    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getMascotaNombre() {
        return mascotaNombre;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }

    public String getPropUsuario() {
        return propUsuario;
    }

    public void setPropUsuario(String propUsuario) {
        this.propUsuario = propUsuario;
    }

    public String getPropNombre() {
        return propNombre;
    }

    public void setPropNombre(String propNombre) {
        this.propNombre = propNombre;
    }

    public String getPropApellido() {
        return propApellido;
    }

    public void setPropApellido(String propApellido) {
        this.propApellido = propApellido;
    }

    public String getPropTelefono() {
        return propTelefono;
    }

    public void setPropTelefono(String propTelefono) {
        this.propTelefono = propTelefono;
    }

    public String getCitaFecha() {
        return citaFecha;
    }

    public void setCitaFecha(String citaFecha) {
        this.citaFecha = citaFecha;
    }

    public String getCitaDescripcion() {
        return citaDescripcion;
    }

    public void setCitaDescripcion(String citaDescripcion) {
        this.citaDescripcion = citaDescripcion;
    }
    /*
        Ya que la tabla se pobla con datos en arrays, se crea este metodo para facilitar la extraccion de los datos
        en el momento de la poblacion de la tabla
    */
    public Object[] toArray(){
    
        Object[] data = {mascotaId, mascotaNombre, propUsuario, propNombre,propApellido,propTelefono, citaFecha, citaDescripcion};
        return data;
    }
    
    /*
        Al igual que con cita, una vez en el cbx se van a almacenar objetos de tipo mascotas pero para no observar
        el espacio en memoria, se sobrecarga el toString para unicamente ver el nombre de la mascota junto con el 
        usuario del propietario para distinguir en caso de nombres iguales
    */

    @Override
    public String toString() {
        return this.mascotaNombre +"-"+ this.propUsuario;
    }

}
