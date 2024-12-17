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
    private final TableView<Video> tableView ;


    public TabalaEditaBD(AnchorPane anchorPane, VideoServicioImpl videoServicio) {
        this.anchorPane = anchorPane;
        this.videoServicio = videoServicio;
        this.tableView = new TableView<>();
    }

    public void creaTablaBD() {
        // Limpiar el AnchorPane
        anchorPane.getChildren().clear();

        // Configuración de la tabla

        tableView.setLayoutX(20);
        tableView.setLayoutY(70);
        tableView.setPrefWidth(1100); // Ampliamos el ancho para incluir todas las columnas
        tableView.setPrefHeight(400);

        // Crear columnas
        TableColumn<Video, Long> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaId.setPrefWidth(30); // Ajustamos el ancho

        TableColumn<Video, String> columnaTitulo = new TableColumn<>("Título");
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columnaTitulo.setPrefWidth(200); // Ancho más intuitivo

        TableColumn<Video, String> columnaUrlOrigen = new TableColumn<>("URL Origen");
        columnaUrlOrigen.setCellValueFactory(new PropertyValueFactory<>("urlOrigen"));
        columnaUrlOrigen.setPrefWidth(200); // Ajustamos el ancho para URLs

        TableColumn<Video, String> columnaUrlDestino = new TableColumn<>("URL Destino");
        columnaUrlDestino.setCellValueFactory(new PropertyValueFactory<>("urlDestino"));
        columnaUrlDestino.setPrefWidth(50);

        TableColumn<Video, String> columnaDescripcion = new TableColumn<>("Descripción");
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnaDescripcion.setPrefWidth(150); // Más ancho para mejor legibilidad

        TableColumn<Video, String> columnaDuracion = new TableColumn<>("Duración");
        columnaDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        columnaDuracion.setPrefWidth(50);

        TableColumn<Video, String> columnaIdioma = new TableColumn<>("Idioma Original");
        columnaIdioma.setCellValueFactory(new PropertyValueFactory<>("idiomaOriginal"));
        columnaIdioma.setPrefWidth(120); // Más espacio para el idioma

        TableColumn<Video, String> columnaTraduccion = new TableColumn<>("Traducción");
        columnaTraduccion.setCellValueFactory(new PropertyValueFactory<>("idiomaTraduccion"));
        columnaTraduccion.setPrefWidth(120);

        TableColumn<Video, String> columnaCapacidad = new TableColumn<>("Tamaño");
        columnaCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        columnaCapacidad.setPrefWidth(50);

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

    public void editarFila() {
        // Verificar si hay una fila seleccionada
        Video videoSeleccionado = tableView.getSelectionModel().getSelectedItem();

        if (videoSeleccionado == null) {
            System.out.println("No se ha seleccionado ninguna fila para editar.");
            return;
        }

        System.out.println("Editando el video: " + videoSeleccionado);

        // Hacer las columnas editables (si aún no lo son)
        hacerColumnasEditables();

        // Opcionalmente, puedes implementar lógica adicional para guardar los datos editados.
    }

    private void hacerColumnasEditables() {
        // Configurar la tabla como editable
        tableView.setEditable(true);

   /*     // Columna Título
        columnaTitulo.setCellFactory(TextFieldTableCell.forTableColumn());
        columnaTitulo.setOnEditCommit(event -> {
            Video video = event.getRowValue(); // Obtiene la fila actual
            video.setTitulo(event.getNewValue()); // Actualiza el valor del atributo
            System.out.println("Título actualizado: " + video.getTitulo());
            // Persistir los cambios en la base de datos
            videoServicio.actualizarVideo(video);
        });

        // Columna Descripción
        columnaDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        columnaDescripcion.setOnEditCommit(event -> {
            Video video = event.getRowValue();
            video.setDescripcion(event.getNewValue());
            System.out.println("Descripción actualizada: " + video.getDescripcion());
            videoServicio.actualizarVideo(video);
        });

        // Columna Idioma Original
        columnaIdioma.setCellFactory(TextFieldTableCell.forTableColumn());
        columnaIdioma.setOnEditCommit(event -> {
            Video video = event.getRowValue();
            video.setIdiomaOriginal(event.getNewValue());
            System.out.println("Idioma Original actualizado: " + video.getIdiomaOriginal());
            videoServicio.actualizarVideo(video);
        });

        // Columna Traducción
        columnaTraduccion.setCellFactory(TextFieldTableCell.forTableColumn());
        columnaTraduccion.setOnEditCommit(event -> {
            Video video = event.getRowValue();
            video.setIdiomaTraduccion(event.getNewValue());
            System.out.println("Traducción actualizada: " + video.getIdiomaTraduccion());
            videoServicio.actualizarVideo(video);
        });*/


    }
    }

