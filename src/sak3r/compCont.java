package sak3r;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static sak3r.RecoveryController.username;

public class compCont implements Initializable {
    @FXML
    private TextField txtField;
    
    @FXML
    private Text txt;
    
    @FXML
    private ProgressBar progs;
    
    @FXML
    private Button compId;
    
    @FXML
    private Button decompId;
    private Thread t;
    private Thread t1;
    private String saveingComp;
    private boolean finish = false;
    private File g;
    private boolean isComp = false;
    private boolean isDec = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
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
    public void comp() throws FileNotFoundException, IOException, Exception {
        tt();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file;
        g = fileChooser.showOpenDialog(null);
        t.start();
        Scanner scan = new Scanner(g);
        StringBuilder strB = new StringBuilder();
        while(scan.hasNextLine()) {
            strB.append(scan.nextLine());
        }
        List<Integer> lista = compress(strB.toString());
        StringBuilder strr = new StringBuilder();
        for(int i = 0; i < lista.size();i++) {
            strr.append(lista.get(i)).append('\n');
        }
        saveingComp = strr.toString();
        isComp = true;
    }
    public void tt () throws Exception {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 0;
                for(double i = 0; i < 1; i+=0.01) {
                    try {
                        if (n>12) n = 0;
                        progs.setProgress(i);
                        if(n==3) txt.setText("Compressing");
                        if(n==6) txt.setText("Compressing.");
                        if(n==9) txt.setText("Compressing..");
                        if(n==12) txt.setText("Compressing...");
                        n++;
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {}
                }
                txt.setText("Compressed");
            }
        });
    }
    public void tt2() throws Exception {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 0;
                for(double i = 0; i < 1; i+=0.01) {
                    try {
                        if (n>12) n = 0;
                        progs.setProgress(i);
                        if(n==3) txt.setText("Decompressing");
                        if(n==6) txt.setText("Decompressing.");
                        if(n==9) txt.setText("Decompressing..");
                        if(n==12) txt.setText("Decompressing...");
                        n++;
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {}
                }
                txt.setText("Decompressed");
            }
        });
    }
    public void saveAction() throws IOException, InterruptedException {
        FileChooser file = new FileChooser();
        file.setTitle("Save File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Documents (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("Zip File (*.zip)", "*.zip");
        if(isDec)
            file.getExtensionFilters().add(extFilter);
        if(isComp)
            file.getExtensionFilters().add(extFilter1);
        File ff = file.showSaveDialog(null);
        ff.createNewFile();
        Path path = Paths.get(ff.getAbsolutePath()); 
        try {
            Files.writeString(path, saveingComp, StandardCharsets.UTF_8);
        } catch (IOException ex) {}
        isComp = isDec = false;
    }
    public void decomp() throws FileNotFoundException, IOException, Exception {
        tt2();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file;
        g = fileChooser.showOpenDialog(null);
        Scanner scan = new Scanner(g);
        StringBuilder strB = new StringBuilder();
        List<Integer> lista = new ArrayList<>();
        while(scan.hasNextLine()) {
            int n = Integer.valueOf(scan.nextLine());
            lista.add(n);
        }
        t.start();
        String tr = decompres(lista);
        saveingComp = tr;
        isDec = true;
    }
    public static List<Integer> compress(String message) {
        int dictionarySize = 256;
        Map<String, Integer> dictionary = new HashMap<>();
        for(int i = 0; i < dictionarySize; i++) {
            char ch = (char)i;
            String str = String.valueOf(ch);
            dictionary.put(str, i);
        }
        String savedLastChar = "";
        List<Integer> result = new ArrayList<>();
        for (char character : message.toCharArray()) {
            String sumChar = savedLastChar + character;
            if(dictionary.containsKey(sumChar)) {
                savedLastChar = sumChar;
            } else {
                result.add(dictionary.get(savedLastChar));
                dictionary.put(sumChar, dictionarySize++);
                savedLastChar = String.valueOf(character);
            }
        }
        if(!savedLastChar.isEmpty()) {
            result.add(dictionary.get(savedLastChar));
        }
        return result;
    }
    public static String decompres(List<Integer> encodedMessage) {
        int dictionarySize = 256;
        Map<Integer,String> dictionary = new HashMap<>();
        for (int i = 0;i < dictionarySize; i++) {
            dictionary.put(i, String.valueOf((char)i));
        }
        int intValue = encodedMessage.remove(0);
        char ch = (char) intValue;
        String characters = String.valueOf(ch);
        
        StringBuilder result = new StringBuilder(characters);
        for(int value : encodedMessage) {
            String sumChar;
            if(dictionary.containsKey(value))
                sumChar = dictionary.get(value);
            else
                sumChar = characters + characters.charAt(0);
            result.append(sumChar);
            dictionary.put(dictionarySize++, characters + sumChar.charAt(0));
            characters = sumChar;
        }
        return result.toString();
    }
    
}
