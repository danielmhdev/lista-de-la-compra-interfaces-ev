# Lista de la Compra - JavaFX

Aplicacion de escritorio desarrollada con JavaFX para la gestion de una lista de la compra. Este proyecto forma parte de la asignatura de Desarrollo de Interfaces del modulo de 2º de DAM.

## Funcionalidades

* Añadir productos mediante un campo de texto.
* Editar el nombre de los productos introducidos.
* Marcar productos como comprados (modifica su aspecto tachando el texto).
* Eliminar productos de forma individual.
* Vaciar la lista por completo en un solo clic.
* Visualizar el contador total de productos y cuantos estan comprados.
* Persistencia de datos en formato JSON, manteniendo la lista guardada al cerrar la aplicacion.
* Interfaz grafica estilizada con CSS y componentes interactivos.

## Tecnologias utilizadas

* Java 17
* JavaFX (Scene Builder, FXML)
* Maven (Gestor de dependencias)
* Gson (Libreria de Google para trabajar con JSON)
* CSS

## Estructura del proyecto

El codigo esta organizado siguiendo un modelo MVC (Modelo-Vista-Controlador) adaptado:

* `model`: Contiene la entidad de datos principal (`Producto.java`).
* `controller`: Maneja la logica de la interfaz grafica (`MainController.java`) y la renderizacion personalizada de la lista (`ProductoCell.java`).
* `dao`: Se encarga del acceso a datos y guardado en archivo (`ProductoDAO.java`).
* `main`: Contiene el punto de entrada de la aplicacion (`Launcher.java`).
* `resources`: Almacena la vista principal en FXML, la hoja de estilos CSS y los iconos necesarios.

## Como ejecutar el proyecto

Para probar o ejecutar la aplicacion, sigue estos pasos:

1. Clona o descarga este repositorio en tu maquina local.
2. Abre el proyecto en tu IDE preferido (se recomienda IntelliJ IDEA o Eclipse) y espera a que Maven descargue las dependencias.
3. Ejecuta la clase principal que se encuentra en la ruta:
   `src/main/java/org/example/listacomprainterfacesev/main/Launcher.java`

Alternativamente, si tienes Maven configurado en tu terminal, puedes ejecutar el siguiente comando en la raiz del proyecto:

```bash
mvn clean javafx:run
```