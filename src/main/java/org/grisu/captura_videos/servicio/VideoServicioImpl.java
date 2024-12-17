package org.grisu.captura_videos.servicio;

import org.grisu.captura_videos.modelo.Video;
import org.grisu.captura_videos.repositorio.VideoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServicioImpl implements VideoServicioInterface {

    private final VideoRepositorio videoRepositorio;


    @Autowired
    public VideoServicioImpl(VideoRepositorio videoRepositorio) {
        this.videoRepositorio = videoRepositorio;
    }

    @Override
    public List<Video> listarVideos() {
        return videoRepositorio.findAll(); // No es necesario usar getVideoRepositorio()
    }

    @Override
    public void guardarVideo(Video video) {
        videoRepositorio.save(video);
    }

    @Override
    public void borrarVideo(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        videoRepositorio.deleteById(id);
    }

    @Override
    public Optional<Video> obtenerVideo(Long id) {
        return Optional.of(videoRepositorio.findById(id).orElseThrow(()-> new IllegalArgumentException("El ID no existe.")));
    }
}