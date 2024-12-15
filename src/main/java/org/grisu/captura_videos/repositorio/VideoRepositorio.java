package org.grisu.captura_videos.repositorio;

import org.grisu.captura_videos.modelo.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepositorio  extends JpaRepository<Video,Long> {
}
