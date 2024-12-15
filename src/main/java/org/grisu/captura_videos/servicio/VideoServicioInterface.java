package org.grisu.captura_videos.servicio;

import org.grisu.captura_videos.modelo.Video;

import java.util.List;

public interface VideoServicioInterface {
    List<Video> listarVideos();
    void guardarVideo(Video video);
    void borrarVideo(Long id);
    Video obtenerVideo(Long id);

}
