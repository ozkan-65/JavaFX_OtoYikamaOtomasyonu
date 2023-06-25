package application;
import java.util.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.IsteMYSQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;
import javafx.scene.control.MenuItem;
public class TabloDBController {
	public TabloDBController() {
		baglanti=VeritabaniUtil.Baglan();
		}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private MenuItem MENU_CUSTOMER;

    @FXML
    private MenuItem MENU_PROCESS;
    
    @FXML
    private MenuItem MENU_FİYAT;


    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_refresh;

    @FXML
    private Button btn_sayfa;

    @FXML
    private Button btn_sil;
    
    @FXML
    private Button btn_temizle;

    @FXML
    private TableColumn<Kayitlar_login, Integer> col_id;

    @FXML
    private TableColumn<Kayitlar_login, String> col_kul;

    @FXML
    private TableColumn<Kayitlar_login, String> col_sifre;

    @FXML
    private Label lbl_id;
    
    @FXML
    private TextField txt_id;
    
    @FXML
    private Label lbl_yeni;

    @FXML
    private TableView<Kayitlar_login> tableview_kayitlar;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    public void DegerGetir(TableView tablo) { //Admin bilgilerinin tableview columnları üzerine getiren fonksiyon
    	sql="select * from login";
    	ObservableList<Kayitlar_login>kayitlarListe=FXCollections.observableArrayList();
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		while(getirilen.next()) {
    			kayitlarListe.add(new Kayitlar_login(getirilen.getInt("kID"), getirilen.getString("kul_ad"), getirilen.getString("sifre")));		
    		}
    		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		col_kul.setCellValueFactory(new PropertyValueFactory<>("sutun_kulad"));
    		col_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
    		tableview_kayitlar.setItems(kayitlarListe);
		} catch (Exception e) {

			System.out.println(e.getMessage().toString());
		}
    }
    
    @FXML
    void MENU_CUSTOMER_CLIK(ActionEvent event) { // Müşteri işlemleri menu itemine tıklandığı zaman TabloDBIslemler sayfasını açan kod bloğu
    	 try {
    	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TabloDBIslemler.fxml"));
    	        Parent root = fxmlLoader.load();

    	       
    	        Stage stage = new Stage();
    	        stage.setScene(new Scene(root));
    	        stage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	
    }

    @FXML
    void MENU_PROCESS_CLIK(ActionEvent event) {  // İşlem detaylı arama menu itemine tıklandığı zaman TabloDBDetayliArama sayfasını açan kod bloğu
    	 try {
 	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TabloDBDetayliArama.fxml"));
 	        Parent root = fxmlLoader.load();

 	       
 	        Stage stage = new Stage();
 	        stage.setScene(new Scene(root));
 	        stage.show();
 	    } catch (IOException e) {
 	        e.printStackTrace();
 	    }
    }
    
    @FXML
    void MENU_FİYAT_CLIK(ActionEvent event) {   // Fiyat bilgisi arama menu itemine tıklandığı zaman FiyatTablo sayfasını açan kod bloğu
    	 try {
  	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FiyatTablo.fxml"));
  	        Parent root = fxmlLoader.load();

  	       
  	        Stage stage = new Stage();
  	        stage.setScene(new Scene(root));
  	        stage.show();
  	    } catch (IOException e) {
  	        e.printStackTrace();
  	    }
    }


    @FXML
    void btn_ekle_Click(ActionEvent event) { //Tableviewe admin bilgileri ekleme buton kodu
    	sql="insert into login(kul_ad, sifre) values(?,?)";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txt_kul.getText().trim());
			sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifreleme( txt_sifre.getText().trim()));
			sorguIfadesi.executeUpdate();
			Alert alert=new Alert(AlertType.INFORMATION);  // Ekleme işlemi başarılı olunca ekrana gelecek alert information penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Ekleme işlemi başarılı olmuştur");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert=new Alert(AlertType.ERROR);  // Ekleme işlemi başarışız olunca ekrana gelecek alert error penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Ekleme işlemi başarısız!!!");
			alert.setContentText("Bu bir hata mesajıdır...");
			alert.showAndWait();
		}
    }

    @FXML
    void btn_guncelle_Click(ActionEvent event) {  //Tableviewde admin bilgilerini güncelleyen buton kodu
    	sql="update login set sifre=? where kul_ad=?";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(2, txt_kul.getText().trim());
			sorguIfadesi.setString(1,VeritabaniUtil.MD5Sifreleme( txt_sifre.getText().trim()));
			sorguIfadesi.executeUpdate();
			Alert alert=new Alert(AlertType.INFORMATION);  // Güncelleme işlemi başarılı olunca ekrana gelecek alert information penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Güncelleme işlemi başarılı olmuştur");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert=new Alert(AlertType.ERROR); // Güncelleme işlemi başarısız olunca ekrana gelecek alert error penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Güncelleme işlemi başarısız!!!");
			alert.setContentText("Bu bir hata mesajıdır...");
			alert.showAndWait();
		}
    }

    @FXML
    void btn_refresh_Click(ActionEvent event) { //Tableview üzerindeki bilgileri yenileyen buton kodu
    	 DegerGetir(tableview_kayitlar);
    }

    @FXML
    void btn_sayfa_Click(ActionEvent event) {

    }
    
    @FXML
    void btn_temizle_Click(ActionEvent event) { //Textfieldlardaki ve labelın içindeki bilgilerini temizleyen buton kodu
    	txt_kul.setText("");
    	txt_sifre.setText("");
    	lbl_yeni.setText("");
    	
    }

    @FXML
    void btn_sil_Click(ActionEvent event) {  //Tableviewde admin bilgilerini silen buton kodu
    	sql="delete from login where kul_ad=? and sifre=?";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txt_kul.getText().trim());
			sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifreleme( txt_sifre.getText().trim()));
			sorguIfadesi.executeUpdate();
			Alert alert=new Alert(AlertType.INFORMATION); // Silme işlemi başarılı olunca ekrana gelecek alert information penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Silme işlemi başarılı olmuştur");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert=new Alert(AlertType.ERROR);  // Silme işlemi başarısız olunca ekrana gelecek alert error penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Silme işlemi başarısız!!!");
			alert.setContentText("Bu bir hata mesajıdır...");
			alert.showAndWait();
		}
    }

    @FXML
    void tableview_kayitlar_Click(MouseEvent event) { //Tableviewde satırdaki değerleri textfieldların ve labelin içine gönderen buton kodu
    	Kayitlar_login kayit=new Kayitlar_login();
    	kayit=(Kayitlar_login) tableview_kayitlar.getItems().get(tableview_kayitlar.getSelectionModel().getSelectedIndex());
    	txt_kul.setText(kayit.getSutun_kulad());
    	txt_sifre.setText(kayit.getSifre());
    	lbl_yeni.setText(String.valueOf(kayit.getId()));

    }

    @FXML
    void initialize() {
    	 DegerGetir(tableview_kayitlar);
    }

}