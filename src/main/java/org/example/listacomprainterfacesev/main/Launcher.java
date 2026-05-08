package org.example.listacomprainterfacesev.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal que lanza la aplicación JavaFX.
 * Se encarga de cargar el archivo MainView.fxml y mostrar la ventana.
 */
public class Launcher extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/fxml/MainView.fxml"));
        
        // Crea la escena con la vista cargada
        Scene scene = new Scene(fxmlLoader.load(), 450, 500);
        
        // Configura el título y asocia la escena a la ventana principal
        stage.setTitle("Lista de la Compra - Interfaces");
        stage.setScene(scene);

        // Muestra la ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}