
package Model_;


/*
    Esta clase se encarga de modelar el objeto citas que tiene como atributos
    el id, la fecha, la descripcion y el id unico de la mascota para poderlo 
    relacionar con otras tablas

*/

public class cita {

    private int citaId;
    private String citaFecha;
    private String citaDescripcion;
    private int mascotaId;

    public cita(int citaId, String citaFecha, String citaDescripcion, int mascotaId) {
        this.citaId = citaId;
        this.citaFecha = citaFecha;
        this.citaDescripcion = citaDescripcion;
        this.mascotaId = mascotaId;
    }
    

    public int getCitaId() {
        return citaId;
    }

    public void setCitaId(int citaId) {
        this.citaId = citaId;
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

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    /*
        Ya que el cbx de citas se va a poblar con objetos cita y no con la fecha unicamente
        es necesario sobrecargar este metodo para asi poder obtener la vista de la fecha y no del espacio en memoria de cada
        objeto cita
    
    */
    @Override
    public String toString() {
        return this.citaFecha; 
    }
    
    public Object[] toArray(){
    
        Object[] data = {citaId, citaFecha, citaDescripcion, mascotaId};
        return data;
    }
    
    

}
