package sak3r;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class hexCont implements Initializable {

    @FXML
    private TextField txt;

    @FXML
    private TextArea sd;

    private File g;
    public static String username = System.getProperty("user.home");

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    public void start(Stage s) throws Exception {
        s.setOnCloseRequest(e1 -> {
            try {
                back();
            } catch (Exception ex) {
                Logger.getLogger(hexCont.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void choose() throws Exception {
        
       FileChooser file = new FileChooser();
       file.setTitle("Open File");
       
       g = file.showOpenDialog(null);
       txt.setText(g.toString());
       
       StringBuilder sbuf = new StringBuilder();
       
       try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(g))) {
           int i = 0;
           int count = 0;
           
           while ((i = fis.read()) != -1) {
               String s = String.format("%02X", i);
               sbuf.append(s).append("    ");
               count++;
               if (count == 20) {
                   sbuf.append("\n\n");
                   count = 0;
               }
           }
       }
       sd.setText(sbuf.toString());
   }

    public void back() throws Exception {
        
        String sn = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\FXMLDocument.fxml";
        File f = new File(sn);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = new Stage();
        stage.setTitle("File Operations");
        stage.setScene(scene);
        stage.show();
        FXMLDocumentController.starts(stage);
   }

    public void back(ActionEvent event) throws Exception {
        
       String sn =  username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\FXMLDocument.fxml";
       
       File f = new File(sn);
       FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
       Parent fxmlParent = fxmlLoader.load();
       Scene scene = new Scene(fxmlParent);
       Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
       stage.setTitle("File Operations");
       stage.setScene(scene);
       stage.show();
       FXMLDocumentController.starts(stage);
    }
}