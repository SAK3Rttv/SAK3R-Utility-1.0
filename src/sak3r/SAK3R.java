package sak3r;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
/**
 *
 * @author SAKER
 */
public class SAK3R extends Application {
    
    public static String username = System.getProperty("user.home");
    protected static boolean ros;

    @Override
    public void start(Stage stage) throws Exception {
        
        boolean fs = new File("C:\\image5").mkdir();
        String m = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\image1";
        System.out.println(m);
        File fss = new File(m);
        File[] lis = fss.listFiles();
        int j = lis.length;

        for (int i = 0; i < j; i++) {
            
            Path source = Paths.get(username + "/Documents/NetBeansProjects/SAK3R/src/sak3r/image1/" + lis[i].getName());
            Path target = Paths.get("C:/image5/" + lis[i].getName());
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
        
        String st = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\FXMLDocument.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        stage.setTitle("SAK3R Programme");
        stage.setScene(scene);        
        stage.show(); 
        FXMLDocumentController.starts(stage);
    }
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
