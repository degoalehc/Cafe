package uam.mx.bl.dto;

public class CafeDto {

    private String nombre;
    private String locacion;
    private float cantidad;
    private int fecha;
    private int importe;
    private int id;
    private String tipo;

    public CafeDto(){

    }

    public CafeDto(String nombre,String locacion, float cantidad, int fecha, int id, int importe,  String tipo) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.id = id;
        this.importe = importe;
        this.nombre = nombre;
        this.tipo = tipo;
    }

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

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    @Override

    public String toString(){
        return "Cafe: "+nombre+
               ",Cantidad: "+cantidad+
               "Fecha"+fecha+
               "Importe"+importe+
               "Id"+"Tipo"+tipo;
    }

    

}