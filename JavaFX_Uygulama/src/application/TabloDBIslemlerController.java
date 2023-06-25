package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.IsteMYSQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.*;
public class TabloDBIslemlerController {
	public TabloDBIslemlerController() {
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
    private Button btn_refresh;

    @FXML
    private Button btn_sayfa;

    @FXML
    private Button btn_sil;
    
    @FXML
    private Button btn_temizle;

    @FXML
    private TableColumn<Kayitlar_arama, Integer> col_fiyat;

    @FXML
    private TableColumn<Kayitlar_arama, Integer> col_id;

    @FXML
    private TableColumn<Kayitlar_arama, String> col_plaka;

    @FXML
    private TableColumn<Kayitlar_arama, String> col_sahip;

    @FXML
    private TableColumn<Kayitlar_arama, String> col_tur;

    @FXML
    private TableColumn<Kayitlar_arama, String> col_yikamaTur;

    @FXML
    private Label lbl_id;

    @FXML
    private TableView<Kayitlar_arama> tableview_islemler;

    @FXML
    private TextField txt_fiyat;

    @FXML
    private TextField txt_plaka;

    @FXML
    private TextField txt_sahip;

    @FXML
    private TextField txt_tur;

    @FXML
    private TextField txt_yikamaTur;
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    public void DegerGetir(TableView tablo) {  //Müşteri bilgilerinin tableview columnları üzerine getiren fonksiyon
    	sql="select * from islemler";
    	ObservableList<Kayitlar_arama>kayitlarListe=FXCollections.observableArrayList();
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		while(getirilen.next()) {
    			kayitlarListe.add(new Kayitlar_arama(getirilen.getInt("id"),getirilen.getString("aracSahip"), getirilen.getString("aracPlaka"), getirilen.getString("aracTur"), getirilen.getString("yikamaTur"), getirilen.getInt("fiyat")));		
    		}
    		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		col_sahip.setCellValueFactory(new PropertyValueFactory<>("aracSahip"));
    		col_plaka.setCellValueFactory(new PropertyValueFactory<>("aracPlaka"));
    		col_tur.setCellValueFactory(new PropertyValueFactory<>("aracTur"));
    		col_yikamaTur.setCellValueFactory(new PropertyValueFactory<>("yikamaTur"));
    		col_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
    		tableview_islemler.setItems(kayitlarListe);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void btn_ekle_Click(ActionEvent event) {  //Tableviewe müşteri bilgileri ekleyen buton kodu
    	sql="insert into islemler(aracSahip, aracPlaka, aracTur, yikamaTur, fiyat) values(?,?,?,?,?)";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txt_sahip.getText().trim());
    		sorguIfadesi.setString(2, txt_plaka.getText().trim());
    		sorguIfadesi.setString(3, txt_tur.getText().trim());
    		sorguIfadesi.setString(4, txt_yikamaTur.getText().trim());
    		sorguIfadesi.setString(5, txt_fiyat.getText().trim());
    		
    		sorguIfadesi.executeUpdate();
    		Alert alert=new Alert(AlertType.INFORMATION);   // Ekleme işlemi başarılı olunca ekrana gelecek alert information penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Ekleme işlemi başarılı olmuştur");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert=new Alert(AlertType.ERROR);  // Ekleme işlemi başarışız olunca ekrana gelecek alert error penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Ekleme işlemi başarısız!!!");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		}

    }

    @FXML
    void btn_guncelle_Click(ActionEvent event) {  //Tableviewdaki müşteri bilgilerini güncelleyen buton kodu
    	sql="update islemler set aracSahip=? where aracPlaka=? and aracTur=? and yikamaTur=? and fiyat=? ";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txt_sahip.getText().trim());
    		sorguIfadesi.setString(2, txt_plaka.getText().trim());
    		sorguIfadesi.setString(3, txt_tur.getText().trim());
    		sorguIfadesi.setString(4, txt_yikamaTur.getText().trim());
    		sorguIfadesi.setString(5, txt_fiyat.getText().trim());
    		
    		sorguIfadesi.executeUpdate();
    		Alert alert=new Alert(AlertType.INFORMATION);  // Güncelleme işlemi başarılı olunca ekrana gelecek alert information penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Güncelleme işlemi başarılı olmuştur");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert=new Alert(AlertType.ERROR);  // Güncelleme işlemi başarısız olunca ekrana gelecek alert error penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Güncelleme işlemi başarısız!!!");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		}
    }

    @FXML
    void btn_refresh_Click(ActionEvent event) {  //Tableview üzerindeki bilgileri yenileyen buton kodu
    	DegerGetir(tableview_islemler);
    }

    @FXML
    void btn_sayfa_Click(ActionEvent event) {

    }
    
    @FXML
    void btn_temizle_Click(ActionEvent event) {  //Textfieldlardaki ve labelın içindeki bilgilerini temizleyen buton kodu
    	txt_sahip.setText("");
    	txt_plaka.setText("");
    	txt_tur.setText("");
    	txt_yikamaTur.setText("");
    	txt_fiyat.setText("");
    	lbl_id.setText("");

    
    }

    @FXML
    void btn_sil_Click(ActionEvent event) {  //Tableviewdaki müşteri bilgilerini silen buton kodu
    	sql="delete from islemler where aracSahip=? and aracPlaka=? and aracTur=? and yikamaTur=? and fiyat=? ";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txt_sahip.getText().trim());
    		sorguIfadesi.setString(2, txt_plaka.getText().trim());
    		sorguIfadesi.setString(3, txt_tur.getText().trim());
    		sorguIfadesi.setString(4, txt_yikamaTur.getText().trim());
    		sorguIfadesi.setString(5, txt_fiyat.getText().trim());
    		
    		sorguIfadesi.executeUpdate();
    		Alert alert=new Alert(AlertType.INFORMATION);  // Silme işlemi başarılı olunca ekrana gelecek alert information penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Silme işlemi başarılı olmuştur");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert=new Alert(AlertType.ERROR); // Silme işlemi başarısız olunca ekrana gelecek alert error penceresi kodu
			alert.setTitle("Oto Yıkama Otomasyonu");
			alert.setHeaderText("Silme işlemi başarısız!!!");
			alert.setContentText("Bu bir bilgi mesajıdır...");
			alert.showAndWait();
		}

    }

    @FXML
    void tableview_islemler_Click(MouseEvent event) {  //Tableviewde satırdaki değerleri textfieldların ve labelin içine gönderen buton kodu
    	Kayitlar_arama kayit=new Kayitlar_arama();
    	kayit=(Kayitlar_arama) tableview_islemler.getItems().get(tableview_islemler.getSelectionModel().getSelectedIndex());
    	txt_sahip.setText(kayit.getAracSahip());
    	txt_plaka.setText(kayit.getAracPlaka());
    	txt_tur.setText(kayit.getAracTur());
    	txt_yikamaTur.setText(kayit.getYikamaTur());
    	txt_fiyat.setText(String.valueOf(kayit.getFiyat()));
    	lbl_id.setText(String.valueOf(kayit.getId()));

    }

    @FXML
    void initialize() {
    	DegerGetir(tableview_islemler);
    }

}