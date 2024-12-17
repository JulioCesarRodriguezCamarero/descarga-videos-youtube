package org.grisu.captura_videos.videos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ObtenVideo {

    /**
     * Clase para almacenar información del video.
     */
  public  static class InfoVideo {
        private String titulo;
        private String descripcion;
        private String duracion;
        private String tamano;

        public InfoVideo(String titulo, String descripcion, String duracion, String tamano) {
            this.titulo = titulo.trim();
            this.descripcion = descripcion.trim();
            this.duracion = duracion.trim();
            this.tamano = tamano.trim();
        }

        @Override
        public String toString() {
            return String.format(
                    "Título: %s%nDescripción: %s%nDuración: %s%nTamaño: %s",
                    titulo, descripcion, duracion, tamano
            );
        }
    }

    /**
     * Método para descargar un video de YouTube y obtener su información usando yt-dlp.
     *
     * @param urlVideo    La URL del video de YouTube que se descargará.
     * @param rutaDestino La ruta de la carpeta donde se guardará el archivo descargado.
     * @throws IOException Si ocurre algún error durante la ejecución del comando.
     */
    public InfoVideo descargarVideoYExtraerInfo(String urlVideo, String rutaDestino) throws IOException {
        InfoVideo info = null;

        // 1. Comando para obtener información del video
        String comandoInfo = String.format(
                "yt-dlp --print \"%%(title)s\\n%%(description)s\\n%%(duration_string)s\\n%%(filesize_approx)s\" \"%s\"",
                urlVideo
        );

        // Obtener información del video
        try {
            Process procesoInfo = Runtime.getRuntime().exec(comandoInfo);

            // Leer la salida estándar con codificación UTF-8
            try (BufferedReader lector = new BufferedReader(new InputStreamReader(procesoInfo.getInputStream(), StandardCharsets.UTF_8))) {
                String titulo = lector.readLine();      // Leer el título
                String descripcion = lector.readLine();// Leer la descripción
                String duracion = lector.readLine();   // Leer la duración
                String tamano = lector.readLine();     // Leer el tamaño aproximado

                // Validar datos nulos o vacíos
                if (titulo == null) titulo = "Desconocido";
                if (descripcion == null) descripcion = "Desconocida";
                if (duracion == null) duracion = "Desconocida";
                if (tamano == null || tamano.isBlank()) tamano = "Desconocido";

                // Crear un objeto con la información del video
                info = new InfoVideo(titulo, descripcion, duracion, tamano + " MB");
                System.out.println("Información extraída del video:");
                System.out.println(info); // Imprimir información en consola
            }
        } catch (Exception e) {
            throw new IOException("Error al obtener información del video: " + e.getMessage(), e);
        }

        // 2. Comando para descargar el video
        String comandoDescarga = String.format(
                "yt-dlp -o \"%s\\%%(title)s.%%(ext)s\" \"%s\"",
                rutaDestino, urlVideo
        );
        try {
            Process procesoDescarga = Runtime.getRuntime().exec(comandoDescarga);

            // Leer salida estándar y errores
            try (BufferedReader lector = new BufferedReader(new InputStreamReader(procesoDescarga.getInputStream()));
                 BufferedReader errorLector = new BufferedReader(new InputStreamReader(procesoDescarga.getErrorStream()))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea); // Progreso de la descarga
                }
                while ((linea = errorLector.readLine()) != null) {
                    System.err.println(linea); // Leer errores si ocurren
                }
            }

            // Comprobar el código de salida para confirmar éxito
            int exitCode = procesoDescarga.waitFor();
            if (exitCode == 0) {
                System.out.println("El video se descargó correctamente en la ruta: " + rutaDestino);
            } else {
                System.err.println("Ocurrió un error al intentar descargar el video. Código de salida: " + exitCode);
            }
        } catch (Exception e) {
            throw new IOException("Error al descargar el video: " + e.getMessage(), e);
        }

        return info;
    }

    /**
     * Método principal para probar la descarga y extracción de información de un video de YouTube.
     *
     * @param args Argumentos del programa.
     */
    public static void main(String[] args) {
        ObtenVideo obtenVideo = new ObtenVideo();

        // Variables con la información
        String urlVideo = "https://www.youtube.com/watch?v=PQ9zStqu8LY"; // URL de ejemplo (reemplazar con la deseada)
        String rutaDestino = "G:"; // Carpeta destino

        try {
            // Descargar el video y extraer información
            InfoVideo info = obtenVideo.descargarVideoYExtraerInfo(urlVideo, rutaDestino);
            System.out.println("Información del video descargado: " + info);
        } catch (IOException e) {
            System.err.println("Hubo un error: " + e.getMessage());
        }
    }
}