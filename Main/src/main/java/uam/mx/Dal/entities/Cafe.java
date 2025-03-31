package uam.mx.Dal.entities;

public class Cafe {
    private String nombre;
    private float cantidad;

    //Constructor vacío
    public Cafe(){

    }

    //Constructor

    public Cafe(float cantidad, String nombre) {
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    //GET Y SET
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @Override

    public String toString(){
        return "Cafe: "+nombre+
               "Cantidad: "+cantidad;
    }


}
