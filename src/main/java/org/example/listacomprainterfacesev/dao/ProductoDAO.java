package org.example.listacomprainterfacesev.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.listacomprainterfacesev.model.Producto;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar el almacenamiento de la lista de productos
 * en un archivo JSON usando la libreria Gson
 */
public class ProductoDAO {
    // Ruta del archivo donde se guardara la lista
    private static final String ARCHIVO_JSON = "lista_compra.json";
    private Gson gson;

    public ProductoDAO() {
        this.gson = new Gson();
    }

    // Guarda la lista actual de productos en el archivo JSON.
    public void guardarProductos(List<Producto> productos) {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            // Convierte la lista en formato JSON y la escribe en el archivo
            gson.toJson(productos, writer);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar la lista de productos.");
        }
    }

    // Carga y devuelve la lista de productos desde el archivo JSON
    public List<Producto> cargarProductos() {
        File file = new File(ARCHIVO_JSON);
        
        // Si el archivo no existe, simplemente devolvemos una lista nueva y vacia
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            // Usamos TypeToken para indicarle a Gson que tipo de lista es (List<Producto>)
            Type listType = new TypeToken<ArrayList<Producto>>() {}.getType();
            List<Producto> productos = gson.fromJson(reader, listType);
            
            // Si el json estaba vacio, puede devolver null
            if (productos == null) {
                return new ArrayList<>();
            }
            return productos;
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la lista de productos.");
            return new ArrayList<>();
        }
    }
}