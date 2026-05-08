package org.example.listacomprainterfacesev.controller;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.example.listacomprainterfacesev.model.Producto;

import java.net.URL;

/**
 * Controla la representación visual de cada elemento en el ListView.
 * Mapea los datos del modelo Producto a componentes gráficos.
 */
public class ProductoCell extends ListCell<Producto> {

    private final HBox container;
    private final CheckBox checkBox;
    private final Label lblNombre;
    private final TextField txtEditNombre;
    
    private final MainController mainController;

    public ProductoCell(MainController controller) {
        super();
        this.mainController = controller;

        // Inicialización de componentes UI
        checkBox = new CheckBox();
        lblNombre = new Label();
        txtEditNombre = new TextField();
        
        Button btnEditar = new Button();
        Button btnEliminar = new Button();
        
        // Carga de recursos gráficos
        configurarIcono(btnEditar, "/assets/editar.png", "✏");
        configurarIcono(btnEliminar, "/assets/eliminar.png", "🗑");

        // Vinculación con los estilos CSS
        lblNombre.getStyleClass().add("product-label");
        txtEditNombre.getStyleClass().add("edit-text-field");
        btnEditar.getStyleClass().addAll("button-icon", "button-edit");
        btnEliminar.getStyleClass().addAll("button-icon", "button-delete");
        checkBox.getStyleClass().add("checkbox");
        
        // Estado inicial de los componentes de edición (ocultos por defecto)
        txtEditNombre.setVisible(false);
        txtEditNombre.setManaged(false);

        // Layout de la celda
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        container = new HBox(10, checkBox, lblNombre, txtEditNombre, spacer, btnEditar, btnEliminar);
        container.setAlignment(Pos.CENTER_LEFT);
        container.getStyleClass().add("cell-container");

        // --- Definición de eventos de la UI ---

        btnEditar.setOnAction(e -> iniciarEdicion());

        btnEliminar.setOnAction(e -> {
            if (getItem() != null) mainController.eliminarProductoDirecto(getItem());
        });

        // Eventos para la confirmación de la edición
        txtEditNombre.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) confirmarEdicion();
            else if (e.getCode() == KeyCode.ESCAPE) cancelarEdicion();
        });

        txtEditNombre.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) confirmarEdicion(); 
        });
    }
    

     // Carga de icono desde resources, con fallback a emoji.
    private void configurarIcono(Button boton, String rutaImagen, String emojiRespaldo) {
        URL url = getClass().getResource(rutaImagen);
        if (url != null) {
            Image image = new Image(url.toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(16);
            imageView.setFitHeight(16);
            boton.setGraphic(imageView);
        } else {
            boton.setText(emojiRespaldo);
        }
    }

    private void iniciarEdicion() {
        if (getItem() == null) return;
        
        lblNombre.setVisible(false);
        lblNombre.setManaged(false);
        
        txtEditNombre.setText(getItem().getNombre());
        txtEditNombre.setVisible(true);
        txtEditNombre.setManaged(true);
        
        txtEditNombre.requestFocus();
        txtEditNombre.selectAll();
    }

    private void confirmarEdicion() {
        if (getItem() != null && txtEditNombre.isVisible()) {
            String nuevoNombre = txtEditNombre.getText().trim();
            if (!nuevoNombre.isEmpty()) {
                getItem().setNombre(nuevoNombre);
                lblNombre.setText(nuevoNombre);
                mainController.guardarCambios();
            }
            cancelarEdicion(); 
        }
    }

    private void cancelarEdicion() {
        txtEditNombre.setVisible(false);
        txtEditNombre.setManaged(false);
        lblNombre.setVisible(true);
        lblNombre.setManaged(true);
    }

     // Actualizamos l la celda cuando cambian los datos.

    @Override
    protected void updateItem(Producto producto, boolean empty) {
        super.updateItem(producto, empty);

        if (empty || producto == null) {
            setGraphic(null);
            setText(null);
        } else {
            lblNombre.setText(producto.getNombre());
            cancelarEdicion(); 
            
            // Desenlazar el listener antes de modificar el estado visual
            checkBox.setOnAction(null); 
            checkBox.setSelected(producto.isComprado());
            actualizarEstiloTexto(producto);
            
            // Volver a enlazar el listener con los datos actualizados
            checkBox.setOnAction(e -> {
                producto.setComprado(checkBox.isSelected());
                actualizarEstiloTexto(producto);
                mainController.guardarCambios();
            });

            setGraphic(container);
            setText(null);
        }
    }

    private void actualizarEstiloTexto(Producto producto) {
        if (producto.isComprado()) {
            lblNombre.getStyleClass().add("product-label-crossed");
        } else {
            lblNombre.getStyleClass().remove("product-label-crossed");
        }
    }
}