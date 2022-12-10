package sak3r;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import static sak3r.RecoveryController.on1;
/**
 *
 * @author SAKER
 */
public class recoCon implements Initializable {
    
    @FXML
    private Text fg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fg.setText("Recovered " + on1 + " files successfully");
    }
}
