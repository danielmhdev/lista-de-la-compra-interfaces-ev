package org.example.listacomprainterfacesev.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.listacomprainterfacesev.dao.ProductoDAO;
import org.example.listacomprainterfacesev.model.Producto;

import java.util.List;

/*
 Controlador principal de la vista de la Lista de la Compra.
 Gestiona los eventos de la interfaz y la comunicación con la capa de datos.
 */
public class MainController {

    // Componentes FXML
    @FXML
    private TextField txtProducto;
    @FXML
    private ListView<Producto> ListViewProductos;
    @FXML
    private Label lblMensaje;
    @FXML
    private Label lblTotalProductos;

    // DAO para la gestión y persistencia de los datos en JSON
    private ProductoDAO productoDAO;

    // Lista observable para que los cambios se reflejen automáticamente en la vista
    private ObservableList<Producto> listaProductos;

    // Mé_todo de inicialización se ejecuta automáticamente al cargar el FXML.
    @FXML
    public void initialize() {
        productoDAO = new ProductoDAO();
        listaProductos = FXCollections.observableArrayList();

        // Cargar datos al iniciar
        List<Producto> productosGuardados = productoDAO.cargarProductos();
        listaProductos.addAll(productosGuardados);

        ListViewProductos.setItems(listaProductos);

        // Usamos una celda personalizada para poder tener botones en cada fila
        ListViewProductos.setCellFactory(param -> new ProductoCell(this));

        actualizarTotal();
    }

    // Añade un nuevo producto a la lista cuando se pulsa el botón "Añadir" o "Enter".

    @FXML
    void onAgregarProducto(ActionEvent event) {
        String nombre = txtProducto.getText().trim();

        // Validación: Evitar productos vacíos
        if (nombre.isEmpty()) {
            lblMensaje.setText("El producto no puede estar vacío.");
            return;
        }

        // Comprobar si ya existe ignorando mayúsculas/minúsculas
        boolean existe = listaProductos.stream()
                .anyMatch(p -> p.getNombre().equalsIgnoreCase(nombre));

        if (existe) {
            lblMensaje.setText("El producto ya está en la lista.");
            return;
        }

        // Añadir a la lista y limpiar el input
        Producto nuevoProducto = new Producto(nombre);
        listaProductos.add(nuevoProducto);

        txtProducto.clear();
        lblMensaje.setText("");
        guardarCambios();
    }

     // Elimina el producto de la lista (usado desde la celda
    public void eliminarProductoDirecto(Producto producto) {
        if (producto != null) {
            listaProductos.remove(producto);
            lblMensaje.setText("");
            guardarCambios();
        }
    }

    // Elimina todos los productos de la lista.
    @FXML
    void onVaciarLista(ActionEvent event) {
        listaProductos.clear();
        lblMensaje.setText("");
        guardarCambios();
    }

     // Guardamos los datos y actualiza la UI
    public void guardarCambios() {
        productoDAO.guardarProductos(listaProductos);
        actualizarTotal();
    }

     // Actualiza el texto de la etiqueta que muestra el total de productos y cuántos se han comprado.
    private void actualizarTotal() {
        long comprados = listaProductos.stream().filter(Producto::isComprado).count();
        int total = listaProductos.size();
        lblTotalProductos.setText("Comprados: " + comprados + " de " + total);
    }
}