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
import java.util.Random;
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
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static sak3r.RecoveryController.username;

public class encCont implements Initializable {
    File g;
    @FXML
    private TextArea txtArea;
    
    @FXML
    private TextArea txtAreaEnc;
    
    @FXML
    private Button arrow1;
    
    @FXML
    private Button arrow2;
    
    private int key;
    
    private byte[] beforeEncBytes;
    
    //private byte[] ss;
    
    private String afterEnc;
    
    private String beforeEnc;
    
    //private int numOfKey = 0;
    //private String Dec;
    
    private byte[] afterEncBytes;
    
    private String DecryptSaved;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arrow2.setVisible(false);
        txtAreaEnc.setEditable(false);
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
    public void encAction(ActionEvent event) throws Exception {
        txtAreaEnc.setText(encryptFile());
        txtArea.setText(beforeEnc);
    }
    public void saveEncAction(ActionEvent event) throws Exception {
        FileChooser file = new FileChooser();
        file.setTitle("Save File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Documents (*.txt)", "*.txt");
        file.getExtensionFilters().add(extFilter);
        File ff = file.showSaveDialog(null);
        ff.createNewFile();
        Path path = Paths.get(ff.getAbsolutePath());    
        
        File fg;
        fg = new File(ff.getParent() + "\\key"+ff.getName());
        fg.createNewFile();
        Path path1 = Paths.get(fg.getAbsolutePath());
        
        File w;
        w = new File(ff.getParent() + "\\bytes"+ff.getName());
        Path path3 = Paths.get(w.getAbsolutePath());
        
        StringBuilder stringBr = new StringBuilder();
        for(int i = 0; i < afterEncBytes.length;i++) {
            Byte byteA = afterEncBytes[i];
            stringBr.append(byteA.intValue()).append("\n");
        }
        byte[] bytesFile = stringBr.toString().getBytes();
        String bytesFileString = new String(bytesFile, StandardCharsets.UTF_8);

        try {
            Files.writeString(path, afterEnc, StandardCharsets.UTF_8);
            
            Files.writeString(path1, String.valueOf(key), StandardCharsets.UTF_8);
            Files.setAttribute(path1, "dos:hidden", true);
            
            Files.writeString(path3, bytesFileString, StandardCharsets.UTF_8);
            Files.setAttribute(path3, "dos:hidden", true);
        } catch (IOException ex) {}
     
    }
    public void DecAction(ActionEvent event) throws Exception {
        arrow1.setVisible(false);
        arrow2.setVisible(true);
        decryptFile();
    }
    public void saveDecAction(ActionEvent event) throws Exception {
        FileChooser file = new FileChooser();
        file.setTitle("Save File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Documents (*.txt)", "*.txt");
        file.getExtensionFilters().add(extFilter);
        File ff = file.showSaveDialog(null);
        ff.createNewFile();
        Path path = Paths.get(ff.getAbsolutePath()); 
        try {
            Files.writeString(path, DecryptSaved, StandardCharsets.UTF_8);
        } catch (IOException ex) {}
    }
    public void back(ActionEvent event) throws Exception {
        String st =  username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\projFXML.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("SAK3R Programme");
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
        stage.setTitle("SAK3R Programme");
        stage.setScene(scene);
        stage.show();
        new projCont().start(stage);
   }
    public void clear () {
        arrow1.setVisible(true);
        arrow2.setVisible(false);
        txtArea.clear();
        txtAreaEnc.clear();
    }
    public int getKey() {
        Random rn = new Random();
        int random = rn.nextInt(100);
        return random;
    }
    public String encryptFile() throws FileNotFoundException, IOException {
        FileChooser file = new FileChooser();
        file.setTitle("Open File");
        g = file.showOpenDialog(null);
        Scanner scan = new Scanner(g);
        StringBuilder strB = new StringBuilder();
        while(scan.hasNextLine()) {
            strB.append(scan.nextLine());
        }
        beforeEnc = strB.toString();
        beforeEncBytes = strB.toString().getBytes();
        key = getKey();
        afterEncBytes = new byte[beforeEncBytes.length];
        for(int i = 0; i < beforeEncBytes.length; i++) {
            beforeEncBytes[i] = (byte) ((beforeEncBytes[i] + key)%256);
            afterEncBytes[i] = beforeEncBytes[i];
        }
        afterEnc = new String(beforeEncBytes, StandardCharsets.UTF_8);
        return afterEnc;
    }
    public String encryptFile(String strv) throws FileNotFoundException, IOException {
        beforeEnc = strv;
        beforeEncBytes = strv.getBytes();
        key = getKey();
        afterEncBytes = new byte[beforeEncBytes.length];
        for(int i = 0; i < beforeEncBytes.length; i++) {
            beforeEncBytes[i] = (byte) ((beforeEncBytes[i] + key)%256);
            afterEncBytes[i] = beforeEncBytes[i];
        }
        afterEnc = new String(beforeEncBytes, StandardCharsets.UTF_8);
        DecryptSaved = afterEnc;
        return afterEnc;
    }
    public void decryptFile() throws FileNotFoundException, IOException {
        FileChooser file = new FileChooser();
        file.setTitle("Open File");
        g = file.showOpenDialog(null);
        File ns = new File(g.getParent() + "\\bytes"+g.getName());        
        Scanner scan = new Scanner(ns);

        ArrayList<Integer> arrayList = new ArrayList<>();
        
        File fg;
        fg = new File(g.getParent() + "\\key"+g.getName());
        Path path1 = Paths.get(fg.getAbsolutePath());
        String val = Files.readString(path1);
        int getKey = Integer.valueOf(val);
        
        while(scan.hasNextLine()) {
            String str = scan.nextLine();
            int n = Integer.valueOf(str);
            arrayList.add(n);
        }
        
        byte[] bytesBefore = new byte[arrayList.size()];
        byte[] bytesAfter = new byte[arrayList.size()];
        for(int i = 0;i < arrayList.size();i++) {
            bytesBefore[i] = (byte) ((arrayList.get(i) - 1)+1);
            bytesAfter[i] = (byte) ((arrayList.get(i) - getKey)%256);
        }
        
        String beforeDec = new String(bytesBefore, StandardCharsets.UTF_8);
        String afterDec = new String(bytesAfter, StandardCharsets.UTF_8);
        
        txtAreaEnc.setText(beforeDec);
        txtArea.setText(afterDec);
        DecryptSaved = afterDec;
    }
    public void arrow1Action () throws IOException {
        txtAreaEnc.setText(encryptFile(txtArea.getText()));
        
    }
}