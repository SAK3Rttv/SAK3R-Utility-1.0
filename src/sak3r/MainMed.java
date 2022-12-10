/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sak3r;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 *
 * @author SAKER
 */
public class MainMed extends FXMLDocumentController {
    FileChooser file1;
    mediaPlayer1 play1;
    
    MainMed() {}
    
    public void start(Stage s) throws Exception {

        Menu ma = new Menu("File");
        Menu mb = new Menu("Help");
        
        MenuItem ma1 = new MenuItem("Open File");
        MenuItem ma2 = new MenuItem("Exit");
        MenuItem mb1 = new MenuItem("About");
        
        ma.getItems().add(ma1);
        ma.getItems().add(ma2);
        mb.getItems().add(mb1);
        
        MenuBar ms = new MenuBar();
        ms.getMenus().add(ma);
        ms.getMenus().add(mb);

        FileChooser op = new FileChooser();
        op.setTitle("Open File");
        File pn = op.showOpenDialog(s); 
        play1 = new mediaPlayer1(pn.toURI().toURL().toExternalForm());
        play1.setTop(ms);
        
        file1 = new FileChooser();
        
        
        
        
        
        ma1.setOnAction(e -> {
            File g = file1.showOpenDialog(s);
            if(g != null) { 
                try {
                    play1.mediaPlayer.pause();
                    mediaPlayer1 play5 = new mediaPlayer1(g.toURI().toURL().toExternalForm());
                    Scene scene = new Scene(play5, 500, 300);
                    MediaView sd = play5.os();
                    sd.fitWidthProperty().bind(scene.widthProperty());
                    scene.setOnMouseClicked((MouseEvent click) -> {
                        if (click.getClickCount() == 2) {
                            s.setFullScreen(true);
                        }
                    });
                    s.setScene(scene);
                    s.setOnCloseRequest(e1 -> play5.oss());
                } catch (MalformedURLException ex) {
                    System.out.println("catch exception 1");
                }
            }
        });

        ma2.setOnAction(e -> {
            s.close();
            play1.oss();
        });
        
        Scene scene = new Scene(play1, 500, 300);
        MediaView sd = play1.os();
        sd.fitWidthProperty().bind(scene.widthProperty());
        scene.setOnMouseClicked((MouseEvent click) -> {
            if (click.getClickCount() == 2) {
                s.setFullScreen(true);
            }
        });

        s.setTitle("Media Player");
        s.setScene(scene);
        s.getIcons().add(new javafx.scene.image.Image("C:\\image5\\movie.png"));
        s.show();
        s.setOnCloseRequest(e1 -> play1.oss());
    }
}