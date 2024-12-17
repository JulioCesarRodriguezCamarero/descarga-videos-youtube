package org.grisu.captura_videos.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.grisu.captura_videos.controladores.bd.TabalaEditaBD;
import org.grisu.captura_videos.controladores.web.FormularioWeb;
import org.grisu.captura_videos.controladores.web.TablaEditaWeb;
import org.grisu.captura_videos.controladores.web.VideoWeb;
import org.grisu.captura_videos.modelo.Video;
import org.grisu.captura_videos.servicio.VideoServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class IndexControlador implements Initializable {

    @FXML
    private AnchorPane anchorEdicion;

    @FXML
    private Button botonActualizaBD;

    @FXML
    private Button botonDescargaWeb;

    @FXML
    private Button botonEditaWeb;

    @FXML
    private Button botonEditarBD;

    @FXML
    private Button botonEliminaBD;

    @FXML
    private Button botonGuardaBD;

    @FXML
    private Button botonListarBD;
    @Autowired
    private VideoServicioImpl videoServicio;

    // Añadimos una referencia a la tabla para reusarla
    private TablaEditaWeb tablaEditaWeb;

    // Añadimos una referencia a la tabla BBDD para reusarla
    private TabalaEditaBD tablaEditaBD;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Dejar anchorEdicion limpio por defecto.
        anchorEdicion.getChildren().clear();
    }

    @FXML
    private void handleBotonDescargaWeb(ActionEvent event) {
        // Limpia el contenido previo de anchorEdicion
        anchorEdicion.getChildren().clear();

        // Crear y configurar formulario
        FormularioWeb formularioWeb = new FormularioWeb(anchorEdicion);
        formularioWeb.crearFormularioDescarga();
    }

    @FXML
    private void handleBotonEditaWeb(ActionEvent event) {
        // Dejar anchorEdicion limpio por defecto.
        anchorEdicion.getChildren().clear();

        // Crear y configurar tabla editaWeb
        this.tablaEditaWeb = new TablaEditaWeb(anchorEdicion);
        this.tablaEditaWeb.crearTabla();




    }

    @FXML
    private void handleBotonGuardaBD(ActionEvent event) {
        if (tablaEditaWeb == null) {
            mostrarMensaje("Error", "No se encontraron datos para guardar. Por favor, abra primero la tabla de edición.");
            return;
        }

        // Obtener los datos de la tabla
        TableView<VideoWeb> tablaVideos = tablaEditaWeb.getTablaVideos();
        if (tablaVideos == null || tablaVideos.getItems().isEmpty()) {
            mostrarMensaje("Error", "No hay datos en la tabla para guardar en la base de datos.");
            return;
        }

        try {
            Video video;
            // Iterar sobre los elementos de la tabla y guardarlos en la base de datos
            for (VideoWeb videoWeb : tablaVideos.getItems()) {
                video = new Video();
                video.setTitulo(videoWeb.getTitulo());
                video.setCapacidad(videoWeb.getTamano());
                video.setDuracion(videoWeb.getDuracion());
                video.setUrlOrigen(videoWeb.getUrl());
                video.setUrlDestino(videoWeb.getUbicacion());

                videoServicio.guardarVideo(video); // Asumimos que el método existe en la implementación del servicio
            }
            mostrarMensaje("Éxito", "Los datos se han guardado correctamente en la base de datos.");
        } catch (Exception e) {

            mostrarMensaje("Error", "Se produjo un error al guardar los datos en la base de datos.");
        }
    }

    @FXML
    private void handleBotonListarBD(ActionEvent event) {
        anchorEdicion.getChildren().clear();
        // Crear y configurar tabla tablaEditaBD
        this.tablaEditaBD = new TabalaEditaBD(anchorEdicion,videoServicio);
        this.tablaEditaBD.creaTablaBD();


    }

    @FXML
    private void handlebotonEditarBD(ActionEvent event) {

        this.tablaEditaBD.editarFila();
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}


