package org.grisu.captura_videos.controladores.web;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.grisu.captura_videos.videos.ObtenVideo;

import java.io.IOException;

public class FormularioWeb {
    // Definición de campos dinámicos
    private TextField textFieldOrigen;
    private TextField textFieldDestino;
    private final AnchorPane anchorEdicion;

    public FormularioWeb(AnchorPane anchorEdicion) {
        this.anchorEdicion = anchorEdicion;
    }

    public void crearFormularioDescarga() {
        // Crear Label para "URL Origen"
        Label labelOrigen = new Label("URL Origen");
        labelOrigen.setLayoutX(20);
        labelOrigen.setLayoutY(20);
        labelOrigen.setStyle("-fx-font-size: 14px; -fx-font-family: 'Baskerville Old Face';");

        // Crear TextField para "URL Origen"
        textFieldOrigen = new TextField();
        textFieldOrigen.setPromptText("Introduce la URL de origen");
        textFieldOrigen.setLayoutX(150);
        textFieldOrigen.setLayoutY(20);
        textFieldOrigen.setPrefWidth(400);

        // Crear Label para "URL Destino"
        Label labelDestino = new Label("URL Destino");
        labelDestino.setLayoutX(20);
        labelDestino.setLayoutY(70);
        labelDestino.setStyle("-fx-font-size: 14px; -fx-font-family: 'Baskerville Old Face';");

        // Crear TextField para "URL Destino"
        textFieldDestino = new TextField();
        textFieldDestino.setPromptText("Introduce la URL de destino");
        textFieldDestino.setLayoutX(150);
        textFieldDestino.setLayoutY(70);
        textFieldDestino.setPrefWidth(400);

        // Crear botón "Ejecuta"
        Button botonEjecuta = new Button("Ejecuta");
        botonEjecuta.setLayoutX(150);
        botonEjecuta.setLayoutY(120);
        botonEjecuta.setPrefWidth(100);
        botonEjecuta.setStyle("-fx-background-color: #103229; -fx-text-fill: #dbe9c2; -fx-font-size: 14px;");
        botonEjecuta.setOnAction(this::procesarFormulario); // Evento para manejar el clic

        // Añadir todos los elementos al AnchorPane
        anchorEdicion.getChildren().addAll(labelOrigen, textFieldOrigen, labelDestino, textFieldDestino, botonEjecuta);
    }

    private void procesarFormulario(ActionEvent event) {
        // Obtener los valores de los campos
        String urlOrigen = textFieldOrigen.getText();
        String urlDestino = textFieldDestino.getText();

        // Validar que los campos no estén vacíos
        if (urlOrigen.isEmpty() || urlDestino.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos Vacíos");
            alerta.setHeaderText("Error: Campos Vacíos");
            alerta.setContentText("Debes llenar ambos campos antes de continuar.");
            alerta.show();
            return;
        }

        // Crear un ProgressIndicator (Spinner)
        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setLayoutX(500);  // Ajustar la posición donde deseas colocarlo
        spinner.setLayoutY(200);
        anchorEdicion.getChildren().add(spinner); // Agregarlo al AnchorPane para que sea visible

        // Crear tarea en segundo plano para descargar el video
        Task<Void> tareaDescarga = new Task<>() {
            @Override
            protected Void call() throws Exception {
                ObtenVideo obtenVideo = new ObtenVideo();
                try {
                    // Descargar el video y extraer información
                    updateMessage("Descargando video..."); // Actualizar el mensaje (opcional)
                    ObtenVideo.InfoVideo info = obtenVideo.descargarVideoYExtraerInfo(urlOrigen, urlDestino);
                    System.out.println("Información del video descargado: " + info);

                } catch (IOException e) {
                    throw new IOException("Hubo un problema al descargar el video: " + e.getMessage());
                }
                return null;
            }
        };

        // Acción al finalizar con éxito la tarea
        tareaDescarga.setOnSucceeded(workerStateEvent -> {
            anchorEdicion.getChildren().remove(spinner); // Quitar el Spinner
            mostrarMensaje("Finalizado con éxito", "El video se descargó correctamente.");
        });

        // Acción al fallar la tarea
        tareaDescarga.setOnFailed(workerStateEvent -> {
            anchorEdicion.getChildren().remove(spinner); // Quitar el Spinner
            Throwable exception = tareaDescarga.getException();
            mostrarMensaje("Error", "Hubo un problema: " + exception.getMessage());
        });

        // Ejecutar la tarea en un hilo separado
        Thread hilo = new Thread(tareaDescarga);
        hilo.setDaemon(true); // Hacer que el hilo sea de fondo
        hilo.start();
    }

    // Método para mostrar un mensaje en formato de alerta
    private void mostrarMensaje(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null); // Sin cabecera
        alerta.setContentText(contenido);
        alerta.showAndWait(); // Mostrar y esperar cierre del usuario
    }
}
