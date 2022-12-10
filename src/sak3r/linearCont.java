package sak3r;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static sak3r.RecoveryController.username;

public class linearCont implements Initializable {
    @FXML
    private TextField txtField;
    
    @FXML
    private TextArea txtArea;
    
    @FXML
    private AnchorPane anchorPane;
    
    private File g;
    @FXML
    private Button up;
    
    @FXML
    private Button close;
    
    @FXML
    private TextField tField;
    
    @FXML
    private Text foundtxt;
    
    private String stringI;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtArea.setEditable(false);
    }
    public void start(Stage s) throws Exception {
        s.setOnCloseRequest(e1 -> {
            try {
                s.close();
            } catch (Exception ex) {
                Logger.getLogger(hexCont.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    public void back(ActionEvent event) throws Exception {
        String st =  username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\projFXML.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("File Operations");
        stage.setScene(scene);
        stage.show();
        new projCont().start(stage);
    }
    public void back() throws Exception {
        String sn = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\projFXML.fxml";
        File f = new File(sn);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = new Stage();
        stage.setTitle("File Operations");
        stage.setScene(scene);
        stage.show();
        new encCont().start(stage);
    }
    public void choose() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file;
        g = fileChooser.showOpenDialog(null);
        Scanner scan = new Scanner(g);
        StringBuilder strB = new StringBuilder();
        while(scan.hasNextLine()) {
            strB.append(scan.nextLine());
        }
        txtArea.setText(strB.toString());
        stringI = strB.toString();
        txtField.setText(g.toString());
    }
    public void txtFieldAction() throws FileNotFoundException {
        choose();
    }
    public void upAction() {
        String strs2 = stringI;
        String[] spliter = strs2.split(" ");
        String key = tField.getText();
        int LastIndexOfWords = 0;
        int count = 0;
        ArrayList<Integer> lista = new ArrayList<>();
        for(int i = 0; i < spliter.length; i++) {
            System.out.println("a");
            if(key.equals(spliter[i])) {
                LastIndexOfWords = i;
                lista.add(i);
                System.out.println("Found " +"(\""+key.toUpperCase()+"\")"+" in word number : " + lista);
                foundtxt.setText("Found " +"(\""+key+"\")"+" in word number : " + lista + " , "+lista.size()+" TIMES");
                count++;
            }
        }
    }
    public void searching() {
        
        
        int offset = txtArea.getText().indexOf(tField.getText());
        System.out.println("offest : " + offset);
        int length = tField.getText().length();
        System.out.println("len : " + length);
  
        while ( offset != -1) {
            try {
                txtArea.selectRange(offset, offset + length);
                offset = txtArea.getText().indexOf(tField.getText(), offset+1);
            } catch (Exception ex) {}
            
        }
    }
    public void closeAction() {
        up.setVisible(false);
        close.setVisible(false);
        tField.setVisible(false);
    }
    public void onKey() {
        anchorPane.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.F,KeyCombination.CONTROL_DOWN);
            @Override
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    ke.consume();
                    if(up.isVisible()) {
                        up.setVisible(false);
                        close.setVisible(false);
                        tField.setVisible(false);
                    } else {
                        up.setVisible(true);
                        close.setVisible(true);
                        tField.setVisible(true);
                    }
                }
            }
        });
    }
}
