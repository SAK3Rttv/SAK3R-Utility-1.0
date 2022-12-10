package sak3r;

import java.awt.Desktop;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.*;
import javafx.stage.*;
/**
 *
 * @author SAKER
 */

public class RecoveryController implements Initializable {
    
    @FXML
    private CheckBox pdf;

    @FXML
    private CheckBox png;

    @FXML
    private CheckBox jpg;

    @FXML
    private CheckBox selectAll;

    @FXML
    private TextField txt;

    @FXML
    private ListView<String> lista ;

    @FXML
    private Button fsfsfs;
    
    @FXML
    private Button scan1;

    File g;
    
    private boolean don;
    protected static int on = 0;
    protected static int on1 = 0;
    boolean b1;
    boolean b2;
    boolean b3;
    boolean b4;
    ArrayList <String> lp = new ArrayList <> ();
    public static String username = System.getProperty("user.home");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista.setCellFactory(CheckBoxListCell.forListView((String item) -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    lp.add(item);
                    on1++;
                } else {
                    lp.remove(item);
                    on1--;
                }
            });
            return observable;
        }));
    }
    
    public void start(Stage s) throws Exception {
        s.setOnCloseRequest(e1 -> {
            try {
                String st = username + "\\AppData\\Local\\Temp\\RecoverdFile\\";
                File gs = new File(st);
                File[] list = gs.listFiles();
                if (list != null) { 
                    for (File file : list) {     
                        file.delete();
                    }
                }
                back();
            } catch (Exception ex) {
            }
        });
        
    }
    
    public void back() throws Exception {
        String st =  username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\FXMLDocument.fxml";
        File f = new File(st);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = new Stage();
        stage.setTitle("SAK3R Programme");
        stage.setScene(scene);
        stage.show();
        FXMLDocumentController.starts(stage);
    }
    
    public void recover() throws IOException {
        
        File gss = new File("C:\\RecoverdFile");
        File[] lis = gss.listFiles();

        String out = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss").format(new Date());      
        boolean fs = new File("C:\\RecoverdFile\\" + out).mkdir();
        int j = lis.length;
        
        String o = "C:/RecoverdFile/" + out + "/";
        String st = username + "/AppData/Local/Temp/RecoverdFile/";
        
        for (int i = 0; i < lp.size(); i++) {
            Path source = Paths.get(st + lp.get(i));
            Path target = Paths.get(o + lp.get(i));
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
        
        String st1 = username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\Reco.fxml";
        File f = new File(st1);
        String st2 = "file:///C:/image5/ss.mp3";
        
        Media media = new Media(st2);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = new Stage();
        stage.setTitle("Recovered");
        stage.setScene(scene);
        stage.show();
    }

    public void mks() {
        Desktop desktop = Desktop.getDesktop();
        lista.setOnMouseClicked((MouseEvent click) -> {
            if (click.getClickCount() == 2) {
                String selectedItem =   lista.getSelectionModel().getSelectedItem();
                int index = lista.getSelectionModel().getSelectedIndex();
                File dirToOpen;
                try {
                    String st =  username + "\\AppData\\Local\\Temp\\RecoverdFile\\" + selectedItem;
                    dirToOpen = new File(st);
                    desktop.open(dirToOpen);
                } catch (IllegalArgumentException iae) {
                    System.out.println("File Not Found");
                } catch (IOException ex) {
                    Logger.getLogger(RecoveryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void choose() {
        FileChooser file = new FileChooser();
        file.setTitle("Open File");
        g = file.showOpenDialog(null);
        txt.setText(g.toString());
    }
    
    public void back(ActionEvent event) throws Exception {
        
        String stt =  username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\FXMLDocument.fxml";
        
        File f = new File(stt);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("SAK3R Programme");
        stage.setScene(scene);
        stage.show();
        FXMLDocumentController.starts(stage);
        
        try {
            String st = username + "\\AppData\\Local\\Temp\\RecoverdFile\\";
            File gs = new File(st);
            File[] list = gs.listFiles();
            if (list != null) { 
                for (File file : list) {
                    file.delete();
                }
            }
        } catch (Exception e ) {}
    }
    
    public void clear() throws Exception {
        lista.getItems().clear();
    }
    
    public void selectAllAction() {
        
        b4 = selectAll.isSelected();
        
        if (b4) {
            pdf.setSelected(true);
            jpg.setSelected(true);
            png.setSelected(true);
        } else {
            pdf.setSelected(false);
            jpg.setSelected(false);
            png.setSelected(false);
        }
    }
    
    public void scan() throws Exception {
        
        b1 = pdf.isSelected();
        b2 = jpg.isSelected();
        b3 = png.isSelected();
        
        BufferedOutputStream fos = null;
        
        byte lastByte1 = 0;
        byte lastByte2 = 0;
        byte lastByte3 = 0;
        byte lastByte4 = 0;
        byte lastByte5 = 0;
        byte lastByte6 = 0;
        byte lastByte7 = 0;
        byte lastByte8 = 0;
        
        BufferedInputStream fis = new BufferedInputStream (new FileInputStream(g));
        
        int r = 0;
        boolean pns1 = false;
        boolean pns2 = false;
        boolean pns3 = false;
        
        while (fis.available() > 0) {
            int currentByte = fis.read();
            lastByte1 = lastByte2;
            lastByte2 = lastByte3;
            lastByte3 = lastByte4;
            lastByte4 = lastByte5;
            lastByte5 = lastByte6;
            lastByte6 = lastByte7;
            lastByte7 = lastByte8;
            lastByte8 = (byte) currentByte;
            if(b1) {
                try {
                    if (fos != null && pns1 && pns2 == false && pns2 == false ) {
                        fos.write(currentByte);
                        if ((lastByte1 == 0x25 && lastByte2 == 0x25 && lastByte3 == 0x45 && lastByte4 == 0x4F) ||
                            (lastByte1 == 0x25 && lastByte2 == 0x45 && lastByte3 == 0x4F && lastByte4 == 0x46) ||
                            (lastByte1 == 0x45 && lastByte2 == 0x4F && lastByte3 == 0x46 && lastByte4 == 0x0A) ||
                            (lastByte1 == 0x4F && lastByte2 == 0x64 && lastByte3 == 0x0D && lastByte4 == 0x0A) ||
                            (lastByte1 == 0x45 && lastByte2 == 0x4F && lastByte3 == 0x46 && lastByte4 == 0x0D)) {
                            fos.close();
                            txt.setText(null);
                            fos = null;
                            pns1 = false;
                        }
                    } else if (lastByte1 == 0x25 && lastByte2 == 0x50 && lastByte3 == 0x44 && lastByte4 == 0x46) {
                        
                        String sn =  username + "\\AppData\\Local\\Temp\\RecoverdFile\\";
                        boolean fs = new File(sn).mkdir();
                        String sn1 =  username + "\\AppData\\Local\\Temp\\RecoverdFile\\" + r + ".pdf";
                        
                        fos = new BufferedOutputStream (new FileOutputStream(sn1));
                        
                        pns1 = true;
                        don = true;
                        
                        lista.getItems().add(r + ".pdf");
                        lista.refresh();
                        
                        r++;
                        on++;
                        
                        fos.write(lastByte1);
                        fos.write(lastByte2);
                        fos.write(lastByte3);
                        fos.write(lastByte4);
                        fos.write(lastByte5);
                        fos.write(lastByte6);
                        fos.write(lastByte7);
                        fos.write(lastByte8);
                    }
                } catch (IOException e) {  
                    e.getStackTrace();
                }
            }
            if(b2) {
                try {
                    if (fos != null && pns2 && pns1 == false && pns3 == false ) {
                        fos.write(currentByte);
                        if (lastByte1 == 0xFF && lastByte2 == 0xD9) {
                            fos.close();
                            txt.setText(null);
                            fos = null;
                            pns2 = false;
                        }
                    } else if (lastByte1 == -1 && lastByte2 == -40 && lastByte3 == -1 && lastByte4 == -32 ) {
                        
                        String sn =  username + "\\AppData\\Local\\Temp\\RecoverdFile\\";
                        boolean fs = new File(sn).mkdir();
                        String sn1 =  username + "\\AppData\\Local\\Temp\\RecoverdFile\\" + r + ".jpg";
                        
                        fos = new BufferedOutputStream(new FileOutputStream(sn1));
                        
                        pns2 = true;
                        lista.getItems().add(r + ".jpg");
                        lista.refresh();
                        don = true;
                        
                        r++;
                        on++;
                        
                        fos.write(lastByte1);
                        fos.write(lastByte2);
                        fos.write(lastByte3);
                        fos.write(lastByte4);
                        fos.write(lastByte5);
                        fos.write(lastByte6);
                        fos.write(lastByte7);
                        fos.write(lastByte8);
                    }
                } catch (IOException e) {  
                    e.getStackTrace(); 
                }
            }
            if(b3) {
                try {
                    if (fos != null && pns3 && pns1 == false && pns2 == false) {
                        fos.write(currentByte);
                        if (lastByte1 == 0x49 && lastByte2 == 0x45 && lastByte3 == 0x4E && lastByte4 == 0x44  && lastByte5 == -82 && lastByte6 == 0x42 && lastByte7 == 0x60 && lastByte8 == -126) {
                            fos.close();
                            txt.setText(null);
                            fos = null;
                            pns3 = false;
                        }
                    } else if (lastByte1 == -119 && lastByte2 == 0x50 && lastByte3 == 0x4E && lastByte4 == 0x47  && lastByte5 == 0x0D && lastByte6 == 0x0A && lastByte7 == 0x1A && lastByte8 == 0x0A) {
                        String sn =  username + "\\AppData\\Local\\Temp\\RecoverdFile\\";
                        boolean fs = new File(sn).mkdir();
                        String sn1 =  username + "\\AppData\\Local\\Temp\\RecoverdFile\\" + r + ".png";
                        
                        fos = new BufferedOutputStream (new FileOutputStream(sn1));
                        
                        pns3 = true;
                        don = true;
                        lista.getItems().add(r + ".png");
                        lista.refresh();
                        
                        r++;
                        on++;
                        
                        fos.write(lastByte1);
                        fos.write(lastByte2);
                        fos.write(lastByte3);
                        fos.write(lastByte4);
                        fos.write(lastByte5);
                        fos.write(lastByte6);
                        fos.write(lastByte7);
                        fos.write(lastByte8);
                    }
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
        File f;

        if (don) {
            String sn =  username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\done.fxml";
            f = new File(sn);
            String sn1 = "file:///C:/image5/ss.mp3";
            Media media = new Media(sn1);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            don = false;
        } else {
            
            String sn =  username + "\\Documents\\NetBeansProjects\\SAK3R\\src\\sak3r\\fail.fxml";
            f = new File(sn);
            String sn2 = "file:///C:/image5/ss.mp3";
            Media media = new Media(sn2);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
        }
        
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        Scene scene = new Scene(fxmlParent);
        Stage stage = new Stage();
        stage.setTitle("Recovered");
        stage.setScene(scene);
        stage.show();
        on = 0;
        g = null;
    }
    
    public void openFileLocation() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        File dirToOpen;
        try {
            boolean f = new File("C:\\RecoverdFile").mkdir();
            dirToOpen = new File("C:\\RecoverdFile");
            desktop.open(dirToOpen);
        } catch (IllegalArgumentException iae) {
            System.out.println("File Not Found");
        }
    }
}