package uam.mx.dal.entities;

public class Ventas {
    private int fecha;
    private int importe;
    private int id;
    private String tipo;

        public Ventas(){
            
        }
    
        public Ventas(int fecha, int id, int importe, String tipo) {
            this.fecha = fecha;
            this.id = id;
            this.importe = importe;
            this.tipo = tipo;
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
    }