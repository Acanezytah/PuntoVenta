/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.puntoventa;

/**
 *
 * @author Akane
 */
public class Producto {

    //----- Clase entidad para el objeto de producto donde se almacenan los valores del producto
    //que agregue el usuario.
    
    //----- También se calculan automaticamente los subtotales y se agregan a una función get para
    //Obtenerlos facilmente.
    
    public Producto(String nombre, double precio, int cantidad) {
    this.nombre = nombre;
    this.precio = precio;
    this.cantidad = cantidad;
}
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    //--------- RF3 – Calcular total ------------
    // ****** RN6: El subtotal debe calcularse automáticamente ******
    public double getSubtotal() {
    return precio * cantidad;
    }
    
    private String nombre;
    private double precio;
    private int cantidad;
}
