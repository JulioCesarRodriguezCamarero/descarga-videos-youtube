package org.grisu.captura_videos.presentacion;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.grisu.captura_videos.CapturaVideosApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SistemaVideosFx extends Application {

   private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        this.context = new SpringApplicationBuilder(CapturaVideosApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader= new FXMLLoader(CapturaVideosApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(context::getBean);
        try {
            primaryStage.setScene(new javafx.scene.Scene(loader.load()));
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}
