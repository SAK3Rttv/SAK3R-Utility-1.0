/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sak3r;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
/**
 *
 * @author SAKER
 */
public class mediaPlayer1 extends BorderPane {
    Media media;
    MediaPlayer mediaPlayer;
    MediaView mediaView;
    Pane pane;
    Bar bar;
    
    public mediaPlayer1(){}
    
    public mediaPlayer1(String str) {
        media = new Media(str);
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        
        pane = new Pane();
        pane.getChildren().add(mediaView);
        setCenter(pane);
        
        bar = new Bar(mediaPlayer);
        setBottom(bar);
        
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            bar = new Bar(mediaPlayer);
        });
    }
    
    public MediaView os () {
        return mediaView;
    }
    
    public void oss() {
        if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            mediaPlayer.pause();
        }    
    }
}
