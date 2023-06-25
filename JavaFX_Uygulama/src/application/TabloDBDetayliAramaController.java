package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.IsteMYSQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.sql.*;

public class TabloDBDetayliAramaController {
	public TabloDBDetayliAramaController() {
		baglanti=VeritabaniUtil.Baglan();
	}


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_sayfa;

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
    private TableColumn<Kayitlar_arama, String> col_yikama;

    @FXML
    private TableView<Kayitlar_arama> tableview_Aramalar;

    @FXML
    private TextField txt_arama;

    @FXML
    private TextField txt_fiyat;

    @FXML
    private TextField txt_plaka;

    @FXML
    private TextField txt_tur;

    @FXML
    private TextField txt_yikama;
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    public void DegerGetir(TableView tablo,String sql) {  //Müşteri bilgilerinin tableview columnları üzerine getiren fonksiyon
    	if (sql==null) {
			 sql="select * from islemler";	
		}
    	ObservableList<Kayitlar_arama>kayitlarListe=FXCollections.observableArrayList();
    	
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		while(getirilen.next()) {
    			kayitlarListe.add(new Kayitlar_arama(
    					getirilen.getInt("id"),
    					getirilen.getString("aracSahip"),
    					getirilen.getString("aracPlaka"),
    					getirilen.getString("aracTur"),
    					getirilen.getString("yikamaTur"),
    					getirilen.getInt("fiyat")
    					));		
    		}
    		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		col_sahip.setCellValueFactory(new PropertyValueFactory<>("aracSahip"));
    		col_plaka.setCellValueFactory(new PropertyValueFactory<>("aracPlaka"));
    		col_tur.setCellValueFactory(new PropertyValueFactory<>("aracTur"));
    		col_yikama.setCellValueFactory(new PropertyValueFactory<>("yikamaTur"));
    		col_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
    		tableview_Aramalar.setItems(kayitlarListe);
    		
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    	
    }

   

    @FXML
    void btn_sayfa_Click(ActionEvent event) {

    }

    @FXML
    void tableview_Aramalar_MouseClick(MouseEvent event) {

    }

    @FXML
    void txt_aramaAction(ActionEvent event) {  //Üzerine yazıldığı araç sahibi adlarını getiren textfield kodu

    	
    }

    @FXML
    void txt_arama_KeyPressed(KeyEvent event) { //Üzerine yazıldığı araç sahibi adlarını getiren textfield kodu
    	if (txt_arama.getText().equals("")) {
    		
    		sql="select * from islemler";
		}else {
			sql="select * from islemler where aracSahip like '%"+txt_arama.getText()+"%'";
		}
    	DegerGetir(tableview_Aramalar, sql);
    	
    }

    @FXML
    void txt_fiyat_Action(ActionEvent event) {

    }
    
    @FXML
    void txt_fiyat_KeyPressed(KeyEvent event) {  //Üzerine yazıldığı araç fiyat bilgisini getiren textfield kodu
    	if (txt_fiyat.getText().equals("")) {
    		
    		sql="select * from islemler";
		}else {
			sql="select * from islemler where fiyat like '%"+txt_fiyat.getText()+"%'";
		}
    	DegerGetir(tableview_Aramalar, sql);
    }

    @FXML
    void txt_plaka_Action(ActionEvent event) {

    }

    @FXML
    void txt_plaka_KeyPressed(KeyEvent event) {  //Üzerine yazıldığı araç plaka adlarını getiren textfield kodu
    	if (txt_plaka.getText().equals("")) {
    		
    		sql="select * from islemler";
		}else {
			sql="select * from islemler where aracPlaka like '%"+txt_plaka.getText()+"%'";
		}
    	DegerGetir(tableview_Aramalar, sql);
    }

    @FXML
    void txt_tur_Action(ActionEvent event) {
    	
    }

    @FXML
    void txt_tur_KeyPressed(KeyEvent event) {  //Üzerine yazıldığı araç türü bilgilerini getiren textfield kodu
    	if (txt_tur.getText().equals("")) {
    		
    		sql="select * from islemler";
		}
    	else {
			sql="select * from islemler where aracTur like '%"+txt_tur.getText()+"%'" ;
		}
    	DegerGetir(tableview_Aramalar, sql);
    }

    @FXML
    void txt_yikama_Action(ActionEvent event) {

    }

    @FXML
    void txt_yikama_KeyPressed(KeyEvent event) {  //Üzerine yazıldığı araçyıkama tür bilgisini getiren textfield kodu
    	if (txt_yikama.getText().equals("")) {
    		
    		sql="select * from islemler";
		}else {
			sql="select * from islemler where yikamaTur like '%"+txt_yikama.getText()+"%'";
		}
    	DegerGetir(tableview_Aramalar, sql);	
    }

    @FXML
    void initialize() {
    	sql="select * from islemler";
    	DegerGetir(tableview_Aramalar, sql);
        
    }

}
