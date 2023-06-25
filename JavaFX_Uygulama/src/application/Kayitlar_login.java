package application;

public class Kayitlar_login {
	private int id;
	private String sutun_kulad;
	private String sifre;
	
	 Kayitlar_login() {
		
	}
	 Kayitlar_login(int id, String sutun_kulad, String sifre) {
		super();
		this.id = id;
		this.sutun_kulad = sutun_kulad;
		this.sifre = sifre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSutun_kulad() {
		return sutun_kulad;
	}
	public void setSutun_kulad(String sutun_kulad) {
		this.sutun_kulad = sutun_kulad;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	 
	
}
