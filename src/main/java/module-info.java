module org.example.listacomprainterfacesev {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson; // Necesario para usar Gson

    // Exportar el paquete model a gson para la correcta serializacion/deserializacion
    opens org.example.listacomprainterfacesev.model to com.google.gson;

    exports org.example.listacomprainterfacesev.controller;
    opens org.example.listacomprainterfacesev.controller to javafx.fxml;
    exports org.example.listacomprainterfacesev.main;
    opens org.example.listacomprainterfacesev.main to javafx.fxml;
}