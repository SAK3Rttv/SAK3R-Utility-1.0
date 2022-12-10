package sak3r;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static sak3r.FXMLDocumentController.username;

public class projCont implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void start(Stage s) throws Exception {
        s.setOnCloseRequest(e1 -> {
            try {
                back();
            } catch (Exception ex) {
                Logger.getLogger(hexCont.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void encButtonAction(ActionEvent event) throws Exception {
        String st = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\enccFXML.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Encrypt & Decrypt Files");
        stage.setScene(scene);
        stage.show();
        new encCont().start(stage);
    }
    public void compButtonAction(ActionEvent event) throws Exception {
        String st = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\compFXML.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Compress & Decompress Files");
        stage.setScene(scene);
        stage.show();
        new compCont().start(stage);
    }
    public void linearAction(ActionEvent event) throws Exception {
        String st = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\linearFXML.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Linear Search");
        stage.setScene(scene);
        stage.show();
        new linearCont().start(stage);
    }

    private void back() throws MalformedURLException, IOException, Exception {
        String sn = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\FXMLDocument.fxml";
        File f = new File(sn);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = new Stage();
        stage.setTitle("SAK3R Programme");
        stage.setScene(scene);
        stage.show();
        FXMLDocumentController.starts(stage);
    }
    
    
}
