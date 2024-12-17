package org.grisu.captura_videos.controladores.bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.grisu.captura_videos.modelo.Video;
import org.grisu.captura_videos.servicio.VideoServicioImpl;

import java.util.Collections;


public class TabalaEditaBD {
    private final AnchorPane anchorPane;
    private final VideoServicioImpl videoServicio;



    public TabalaEditaBD(AnchorPane anchorPane, VideoServicioImpl videoServicio) {
        this.anchorPane = anchorPane;
        this.videoServicio = videoServicio;
    }

    public void creaTablaBD() {
        // Limpiar el AnchorPane
        anchorPane.getChildren().clear();

        // Configuración de la tabla
        TableView<Video> tableView = new TableView<>();
        tableView.setLayoutX(20);
        tableView.setLayoutY(70);
        tableView.setPrefWidth(1100); // Ampliamos el ancho para incluir todas las columnas
        tableView.setPrefHeight(400);

        // Crear columnas
        TableColumn<Video, Long> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaId.setPrefWidth(100); // Ajustamos el ancho

        TableColumn<Video, String> columnaTitulo = new TableColumn<>("Título");
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columnaTitulo.setPrefWidth(200); // Ancho más intuitivo

        TableColumn<Video, String> columnaUrlOrigen = new TableColumn<>("URL Origen");
        columnaUrlOrigen.setCellValueFactory(new PropertyValueFactory<>("urlOrigen"));
        columnaUrlOrigen.setPrefWidth(250); // Ajustamos el ancho para URLs

        TableColumn<Video, String> columnaUrlDestino = new TableColumn<>("URL Destino");
        columnaUrlDestino.setCellValueFactory(new PropertyValueFactory<>("urlDestino"));
        columnaUrlDestino.setPrefWidth(250);

        TableColumn<Video, String> columnaDescripcion = new TableColumn<>("Descripción");
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnaDescripcion.setPrefWidth(150); // Más ancho para mejor legibilidad

        TableColumn<Video, String> columnaDuracion = new TableColumn<>("Duración");
        columnaDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        columnaDuracion.setPrefWidth(100);

        TableColumn<Video, String> columnaIdioma = new TableColumn<>("Idioma Original");
        columnaIdioma.setCellValueFactory(new PropertyValueFactory<>("idiomaOriginal"));
        columnaIdioma.setPrefWidth(120); // Más espacio para el idioma

        TableColumn<Video, String> columnaTraduccion = new TableColumn<>("Traducción");
        columnaTraduccion.setCellValueFactory(new PropertyValueFactory<>("idiomaTraduccion"));
        columnaTraduccion.setPrefWidth(120);

        TableColumn<Video, String> columnaCapacidad = new TableColumn<>("Tamaño");
        columnaCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        columnaCapacidad.setPrefWidth(100);

// Evita el warning con Collections.addAll
        Collections.addAll(
                tableView.getColumns(),
                columnaId,
                columnaTitulo,
                columnaUrlOrigen,
                columnaUrlDestino,
                columnaDescripcion,
                columnaDuracion,
                columnaIdioma,
                columnaTraduccion,
                columnaCapacidad
        );
        // Recuperar y establecer los datos en la tabla
        ObservableList<Video> listaVideos = FXCollections.observableArrayList(videoServicio.listarVideos());
        tableView.setItems(listaVideos);

        // Agregar la tabla al AnchorPane
        anchorPane.getChildren().add(tableView);

    }
}
