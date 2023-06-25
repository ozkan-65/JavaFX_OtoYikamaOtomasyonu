package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.IsteMYSQL.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.*;


public class SampleController {
	public SampleController() {
		baglanti=VeritabaniUtil.Baglan();
		
	}
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_sayfa;

    @FXML
    private Button btn_sil;

    @FXML
    private Label lbl_sonuc;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;

   

    @FXML
    void btn_login_Click(ActionEvent event) { //Kullanıcı bilgilerinin kontrol edildiği login butonu
    	sql="select * from login where kul_ad=? and sifre=?";
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txt_kul.getText().trim());
			sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifreleme( txt_sifre.getText().trim()));
			ResultSet getirilen=sorguIfadesi.executeQuery();
			if(!getirilen.next()) {
				Alert alert=new Alert(AlertType.ERROR); // Giriş başarışız olunca ekrana gelecek alert error penceresi kodu
				alert.setTitle("Oto Yıkama Otomasyonu");
				alert.setHeaderText("Giriş işlemi başarısız, kullanıcı adı ya da şifre hatalı!!!");
				alert.setContentText("Bu bir hata mesajıdır...");
				alert.showAndWait();
			}
			else {
				
				Alert alert=new Alert(AlertType.INFORMATION); // Giriş başarılı olunca ekrana gelecek alert information penceresi kodu
				alert.setTitle("Oto Yıkama Otomasyonu");
				alert.setHeaderText("Giriş işlemi başarılı olmuştur");
				alert.setContentText("Bu bir bilgi mesajıdır...");
				alert.showAndWait();
				try {
	    	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TabloDB.fxml"));
	    	        Parent root = fxmlLoader.load();

	    	       
	    	        Stage stage = new Stage();
	    	        stage.setScene(new Scene(root));
	    	        stage.show();
	    	        Stage stagenew=(Stage) btn_login.getScene().getWindow();
	    	       
	    	        stagenew.close();
	    	        
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }
			}
			
    }catch (Exception e) {
		// TODO: handle exception
    	lbl_sonuc.setText(e.getMessage().toString());
	}
    }

  

    @FXML
    void btn_sayfa_Click(ActionEvent event) {

    }


    @FXML
    void initialize() {
      
    }

}
