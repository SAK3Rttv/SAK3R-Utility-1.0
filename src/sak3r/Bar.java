package sak3r;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/**
 *
 * @author SAKER
 */
public class Bar extends HBox {
    MediaPlayer play;
    Slider timeLine = new Slider();
    Slider volume = new Slider();
    Button sg = new Button(".");
    Button plays = new Button("▶");
    Button pause = new Button("⏸");
    Label vol = new Label("Volume:");
    
    public Bar(MediaPlayer playv) {
        plays.setStyle("-fx-font-size:10");
        pause.setStyle("-fx-font-size:10");

        play = playv;
        HBox.setMargin(plays,new Insets(5,5,5,5));
        HBox.setMargin(pause,new Insets(5,5,5,5));
        HBox.setMargin(timeLine,new Insets(5,5,5,5));
        HBox.setMargin(vol,new Insets(5,5,5,5));
        HBox.setMargin(volume,new Insets(5,5,5,5));
        HBox.setHgrow(timeLine, Priority.ALWAYS);
        
        setStyle("-fx-background-color:#f3f4f5");
        
        volume.setPrefWidth(70);
        volume.setMinWidth(30);
        volume.setValue(100);
        
        getChildren().addAll(pause,timeLine,vol,volume);
        
        play.play();
        
        pause.setOnAction(e -> {
            MediaPlayer.Status status = play.getStatus();
            if (status == MediaPlayer.Status.PLAYING) {
                getChildren().clear();
                getChildren().addAll(plays,timeLine,vol,volume);
                play.pause();
            }
        });
        
        plays.setOnAction(e -> {
            MediaPlayer.Status status = play.getStatus();
            if (status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
                getChildren().clear();
                getChildren().addAll(pause,timeLine,vol,volume);
                play.play();
            }
        });
        
        plays.setPrefWidth(60);
        pause.setPrefWidth(60);
        
        volume.setValue(play.getVolume());
        volume.setMax(1);
        
        volume.valueProperty().addListener((javafx.beans.Observable observable) -> {
            play.setVolume(volume.getValue());
        });
        
        play.currentTimeProperty().addListener((l, o, n) -> {
            timeLine.setMax(play.getTotalDuration().toSeconds());
            timeLine.setValue(n.toSeconds());
        });
        
        timeLine.setOnMousePressed(mouseEvent -> {
        play.seek(Duration.seconds(timeLine.getValue()));
        });
        
        timeLine.setOnMouseDragged(mouseEvent -> {
        play.seek(Duration.seconds(timeLine.getValue()));
        });
    }
}