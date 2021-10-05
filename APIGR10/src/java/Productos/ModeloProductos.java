package Productos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ModeloProductos {
    private int id_producto;
    private String descripcion;
    private float precio;
    
    public ModeloProductos(){
    }

    public ModeloProductos(int id_producto,
            String descripcion,
            float precio) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
}
