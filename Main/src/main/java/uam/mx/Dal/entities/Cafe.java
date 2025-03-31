package uam.mx.dal.entities;

public class Cafe {
    private int id;
    private String nombre;
    private String locacion;
    private float cantidad;

    //Constructor vacío
    public Cafe(){

    }

    //Constructor

    public Cafe(String nombre,String location, float cantidad) {
        this.cantidad = cantidad;
        this.locacion=location;
        this.nombre = nombre;
    }

    public Cafe(int id, String nombre,String location, float cantidad) {
        this.id=id;
        this.cantidad = cantidad;
        this.locacion=location;
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

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override

    public String toString(){
        return "Cafe{ "+"Nombre"+nombre+
                "Id"+id+
               "Locacion"+locacion+
               "Cantidad: "+cantidad+"}";
    }

    

    


}
