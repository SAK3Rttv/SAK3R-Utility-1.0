package sak3r;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import static sak3r.RecoveryController.on;
/**
 *
 * @author SAKER
 */
public class dones implements Initializable {
    
    @FXML
    private Text fg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fg.setText("Scanned " + on + " files successfully");
    }
}
