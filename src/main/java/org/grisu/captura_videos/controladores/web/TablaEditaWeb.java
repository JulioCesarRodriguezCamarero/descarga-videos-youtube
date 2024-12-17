package org.grisu.captura_videos.controladores.web;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class TablaEditaWeb {
    private final AnchorPane anchorEdicion;
    private TableView<VideoWeb> tablaVideos;

    public TablaEditaWeb(AnchorPane anchorEdicion) {
        this.anchorEdicion = anchorEdicion;

    }

    public void crearTabla() {
        // Limpiar el AnchorPane
        anchorEdicion.getChildren().clear();

        // Campo para ingresar el directorio
        TextField textFieldUrl = new TextField();
        textFieldUrl.setPromptText("Introduce la ruta del directorio...");
        textFieldUrl.setLayoutX(20);
        textFieldUrl.setLayoutY(20);
        textFieldUrl.setPrefWidth(400);

        // Botón para buscar videos
        Button botonBuscar = new Button("Buscar");
        botonBuscar.setLayoutX(450);
        botonBuscar.setLayoutY(20);
        botonBuscar.setStyle("-fx-background-color: #103229; -fx-text-fill: #dbe9c2;");

        // Configuración de la tabla
        tablaVideos = new TableView<>();
        tablaVideos.setLayoutX(20);
        tablaVideos.setLayoutY(70);
        tablaVideos.setPrefWidth(1100); // Ampliamos el ancho para incluir todas las columnas
        tablaVideos.setPrefHeight(400);

        // Crear columnas
        TableColumn<VideoWeb, String> columnaTitulo = new TableColumn<>("Título");
        columnaTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        columnaTitulo.setPrefWidth(200);

        TableColumn<VideoWeb, String> columnaUrl = new TableColumn<>("URL");
        columnaUrl.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
        columnaUrl.setPrefWidth(300);

        TableColumn<VideoWeb, String> columnaTipo = new TableColumn<>("Tipo");
        columnaTipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        columnaTipo.setPrefWidth(80);

        TableColumn<VideoWeb, String> columnaTamano = new TableColumn<>("Tamaño");
        columnaTamano.setCellValueFactory(cellData -> cellData.getValue().tamanoProperty());
        columnaTamano.setPrefWidth(80);

        TableColumn<VideoWeb, String> columnaUbicacion = new TableColumn<>("Ubicación");
        columnaUbicacion.setCellValueFactory(cellData -> cellData.getValue().ubicacionProperty());
        columnaUbicacion.setPrefWidth(50);

        TableColumn<VideoWeb, String> columnaFecha = new TableColumn<>("Fecha");
        columnaFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        columnaFecha.setPrefWidth(100);

        TableColumn<VideoWeb, String> columnaDuracion = new TableColumn<>("Duración");
        columnaDuracion.setCellValueFactory(cellData -> cellData.getValue().duracionProperty());
        columnaDuracion.setPrefWidth(100);

        // Agregar todas las columnas a la tabla
        tablaVideos.getColumns().addAll(columnaTitulo, columnaUrl, columnaTipo, columnaTamano, columnaUbicacion, columnaFecha, columnaDuracion);

        // Acción del botón "Buscar"
        botonBuscar.setOnAction(event -> {
            String rutaDirectorio = textFieldUrl.getText();
            if (rutaDirectorio.isEmpty()) {
                mostrarMensajeAlerta("Error de Entrada", "Introduce una ruta de directorio válida.");
                return;
            }
            List<VideoWeb> videos = VideoWeb.obtenerVideosDesdeDirectorio(rutaDirectorio);
            ObservableList<VideoWeb> datosVideos = FXCollections.observableArrayList(videos);
            tablaVideos.setItems(datosVideos);
        });

        // Agregar componentes al AnchorPane
        anchorEdicion.getChildren().addAll(textFieldUrl, botonBuscar, tablaVideos);
    }

    private void mostrarMensajeAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public TableView<VideoWeb> getTablaVideos() {
        return tablaVideos;
    }
}