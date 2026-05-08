package org.example.listacomprainterfacesev.model;

/**
 * Clase que representa un Producto en la lista de la compra.
 */
public class Producto {
    // Nombre del producto
    private String nombre;
    // Estado de la compra (true = comprado, false = por comprar)
    private boolean comprado;


    // Constructor por defecto, necesario para librerias como Gson.
    public Producto() {
    }


     // Constructor con el nombre del producto. Por defecto se añade como no comprado.
    public Producto(String nombre) {
        this.nombre = nombre;
        this.comprado = false; // Al crearlo, por defecto no esta comprado
    }


   // Constructor completo
    public Producto(String nombre, boolean comprado) {
        this.nombre = nombre;
        this.comprado = comprado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }


    // Como se muestra el producto en forma de texto.
    // Muestra el nombre y si está comprado
    @Override
    public String toString() {
        return nombre + (comprado ? " (Comprado)" : " (Pendiente)");
    }
}