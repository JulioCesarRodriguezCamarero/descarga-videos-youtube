package org.grisu.captura_videos;

import javafx.application.Application;
import org.grisu.captura_videos.presentacion.SistemaVideosFx;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapturaVideosApplication {

    public static void main(String[] args) {
        Application.launch(SistemaVideosFx.class, args);
    }

}
