
package Model_;

/*
    Esta clase esta encargada de modelar al propietario por ende cuenta con los 
    atributos necesarios para cumplir con ese proposito
*/

public class propietario {
    private String propUsuario;
    private String propApellido;
    private String propNombre;
    private String propTelefono;

    public propietario(String propUsuario, String propApellido, String propNombre, String propTelefono) {
        this.propUsuario = propUsuario;
        this.propApellido = propApellido;
        this.propNombre = propNombre;
        this.propTelefono = propTelefono;
    }

    public String getPropUsuario() {
        return propUsuario;
    }

    public void setPropUsuario(String propUsuario) {
        this.propUsuario = propUsuario;
    }

    public String getPropApellido() {
        return propApellido;
    }

    public void setPropApellido(String propApellido) {
        this.propApellido = propApellido;
    }

    public String getPropNombre() {
        return propNombre;
    }

    public void setPropNombre(String propNombre) {
        this.propNombre = propNombre;
    }

    public String getPropTelefono() {
        return propTelefono;
    }

    public void setPropTelefono(String propTelefono) {
        this.propTelefono = propTelefono;
    }

    @Override
    public String toString() {
        return this.propUsuario;
    }
    
    public Object[] toArray(){
    
        Object[] data = {propUsuario, propApellido, propNombre, propTelefono};
        return data;
    }
    
    
    
}
