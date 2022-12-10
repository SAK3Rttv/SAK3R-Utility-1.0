package sak3r;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 *
 * @author SAKER
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    protected Button ButtonRec1;
    
    public static String username = System.getProperty("user.home");

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    public static void starts (Stage s) throws Exception {
        s.getIcons().add(new javafx.scene.image.Image("C:\\image5\\sak3r.png"));
        s.setOnCloseRequest(e1 -> { 
            try {   
                String st = "C:\\image5";
                File gs = new File(st);
                File[] list = gs.listFiles();
                if (list != null) {  
                    for (File file : list) {
                        file.delete();
                    }
                }
            } catch (Exception ex) {
            }
        });
    }

    public void handleButtonRecAction(ActionEvent event) throws Exception {
        String st = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\Recovery.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Recovery File");
        stage.setScene(scene);
        
        stage.show();
        new RecoveryController().start(stage);
    }
    
    public void handleButtonHexAction(ActionEvent event) throws Exception {
        String st = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\hex.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Hex Viewer");
        stage.setScene(scene);
        stage.show();
        new hexCont().start(stage);
    }
    public void fileAction(ActionEvent event) throws Exception {
        String st = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\projFXML.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("File Operations");
        stage.setScene(scene);
        
        stage.show();
        new RecoveryController().start(stage);
    }
    
    public void handleButtonMeAction(ActionEvent event) throws Exception {
        Stage s = new Stage();
        new MainMed().start(s);
    }
}