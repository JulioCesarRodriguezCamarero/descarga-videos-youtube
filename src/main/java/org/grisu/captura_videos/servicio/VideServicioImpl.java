package org.grisu.captura_videos.servicio;

import lombok.Getter;
import org.grisu.captura_videos.modelo.Video;
import org.grisu.captura_videos.repositorio.VideoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class VideServicioImpl implements VideoServicioInterface {

    private final VideoRepositorio videoRepositorio;

    public VideServicioImpl(VideoRepositorio videoRepositorio) {
        this.videoRepositorio = videoRepositorio;
    }

    @Override
    public List<Video> listarVideos() {
        return getVideoRepositorio().findAll();
    }

    @Override
    public void guardarVideo(Video video) {
        getVideoRepositorio().save(video);
    }


    @Override
    public void borrarVideo(Long id) {
        getVideoRepositorio().deleteById(id);
    }

    @Override
    public Video obtenerVideo(Long id) {

        return getVideoRepositorio().findById(id).orElse(null);
    }

}
