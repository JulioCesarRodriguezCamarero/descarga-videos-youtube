package org.grisu.captura_videos.servicio;

import org.grisu.captura_videos.modelo.Video;

import java.util.List;
import java.util.Optional;

public interface VideoServicioInterface {
    List<Video> listarVideos();
    void guardarVideo(Video video);
    void borrarVideo(Long id);
    Optional<Video> obtenerVideo(Long id);

}
