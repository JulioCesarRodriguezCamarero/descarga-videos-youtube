package org.grisu.captura_videos.controladores.web;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VideoWeb {
    // Propiedades del video
    private final StringProperty titulo;
    private final StringProperty url;
    private final StringProperty tipo;
    private final StringProperty tamano;
    private final StringProperty ubicacion;
    private final StringProperty fecha;
    private final StringProperty duracion;

    public VideoWeb(String titulo, String url, String tipo, String tamano, String ubicacion, String fecha, String duracion) {
        this.titulo = new SimpleStringProperty(titulo);
        this.url = new SimpleStringProperty(url);
        this.tipo = new SimpleStringProperty(tipo);
        this.tamano = new SimpleStringProperty(tamano);
        this.ubicacion = new SimpleStringProperty(ubicacion);
        this.fecha = new SimpleStringProperty(fecha);
        this.duracion = new SimpleStringProperty(duracion);
    }

    // Getters y Setters con propiedades para JavaFX
    public String getTitulo() {
        return titulo.get();
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public String getTipo() {
        return tipo.get();
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public String getTamano() {
        return tamano.get();
    }

    public StringProperty tamanoProperty() {
        return tamano;
    }

    public String getUbicacion() {
        return ubicacion.get();
    }

    public StringProperty ubicacionProperty() {
        return ubicacion;
    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public String getDuracion() {
        return duracion.get();
    }

    public StringProperty duracionProperty() {
        return duracion;
    }

    // Método estático para recuperar videos del directorio
    public static List<VideoWeb> obtenerVideosDesdeDirectorio(String directorioRuta) {
        List<VideoWeb> listaVideos = new ArrayList<>();
        File directorio = new File(directorioRuta);

        // Verificar que sea un directorio válido
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.err.println("La ruta no es un directorio válido: " + directorioRuta);
            return listaVideos;
        }

        // Ampliar el filtro de extensiones para incluir más formatos comunes de video
        File[] archivos = directorio.listFiles((dir, name) -> {
            String nombreMinusculas = name.toLowerCase();
            return nombreMinusculas.endsWith(".mp4")
                    || nombreMinusculas.endsWith(".mkv")
                    || nombreMinusculas.endsWith(".avi")
                    || nombreMinusculas.endsWith(".mov")
                    || nombreMinusculas.endsWith(".flv")
                    || nombreMinusculas.endsWith(".wmv")
                    || nombreMinusculas.endsWith(".webm")
                    || nombreMinusculas.endsWith(".mpeg");
        });

        if (archivos != null) {
            for (File archivo : archivos) {
                try {
                    // Obtener información del archivo
                    String titulo = archivo.getName();
                    String url = archivo.getAbsolutePath();
                    String tipo = obtenerTipoArchivo(archivo);
                    String tamano = formatoTamanoArchivo(archivo.length());
                    String ubicacion = archivo.getParent();
                    String fecha = obtenerFechaArchivo(archivo);
                    String duracion = obtenerDuracionArchivo(archivo);

                    // Crear objeto Video y agregar a la lista
                    VideoWeb video = new VideoWeb(titulo, url, tipo, tamano, ubicacion, fecha, duracion);
                    listaVideos.add(video);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return listaVideos;
    }

    // Métodos auxiliares
    private static String obtenerTipoArchivo(File archivo) {
        String nombreArchivo = archivo.getName();
        int index = nombreArchivo.lastIndexOf(".");
        return (index > 0) ? nombreArchivo.substring(index).toLowerCase() : "Desconocido";
    }

    private static String formatoTamanoArchivo(long tamanoBytes) {
        double tamanoKB = tamanoBytes / 1024.0;
        double tamanoMB = tamanoKB / 1024.0;
        double tamanoGB = tamanoMB / 1024.0;

        if (tamanoGB > 1) {
            return String.format("%.2f GB", tamanoGB);
        } else if (tamanoMB > 1) {
            return String.format("%.2f MB", tamanoMB);
        } else {
            return String.format("%.2f KB", tamanoKB);
        }
    }

    private static String obtenerFechaArchivo(File archivo) throws Exception {
        BasicFileAttributes attr = Files.readAttributes(archivo.toPath(), BasicFileAttributes.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(attr.lastModifiedTime().toMillis());
    }

    private static String obtenerDuracionArchivo(File archivo) {
        try {
            // Ruta al ejecutable ffmpeg y ffprobe (si no están en PATH, usa su ubicación completa)
            String ffmpegPath = "ffmpeg"; // Si el binario está en el PATH
            String ffprobePath = "ffprobe"; // Si el binario está en el PATH

            // Inicializar el wrapper de FFmpeg
            FFprobe ffprobe = new FFprobe(ffprobePath);

            // Usar FFprobe para analizar el archivo
            FFmpegProbeResult result = ffprobe.probe(archivo.getAbsolutePath());

            // Obtener la duración en segundos
            double durationInSeconds = result.getFormat().duration;
            if (durationInSeconds > 0) {
                // Formatear duración a un formato legible (HH:mm:ss)
                int hours = (int) durationInSeconds / 3600;
                int minutes = (int) (durationInSeconds % 3600) / 60;
                int seconds = (int) durationInSeconds % 60;
                return String.format("%02d:%02d:%02d", hours, minutes, seconds);
            }

            return "Duración no disponible"; // Si no se puede obtener la duración
        } catch (Exception e) {
            e.printStackTrace();
            return "Duración no disponible"; // Manejo de errores
        }
    }
}