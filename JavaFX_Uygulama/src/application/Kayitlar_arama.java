package application;

public class Kayitlar_arama {
	private int id;
	private String aracSahip;
	private String aracPlaka;
	private String aracTur;
	private String yikamaTur;
	private int fiyat;
	
	  Kayitlar_arama() {
		// TODO Auto-generated constructor stub
	}

	public Kayitlar_arama(int id, String aracSahip, String aracPlaka, String aracTur, String yikamaTur, int fiyat) {
		super();
		this.id = id;
		this.aracSahip = aracSahip;
		this.aracPlaka = aracPlaka;
		this.aracTur = aracTur;
		this.yikamaTur = yikamaTur;
		this.fiyat = fiyat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAracSahip() {
		return aracSahip;
	}

	public void setAracSahip(String aracSahip) {
		this.aracSahip = aracSahip;
	}

	public String getAracPlaka() {
		return aracPlaka;
	}

	public void setAracPlaka(String aracPlaka) {
		this.aracPlaka = aracPlaka;
	}

	public String getAracTur() {
		return aracTur;
	}

	public void setAracTur(String aracTur) {
		this.aracTur = aracTur;
	}

	public String getYikamaTur() {
		return yikamaTur;
	}

	public void setYikamaTur(String yikamaTur) {
		this.yikamaTur = yikamaTur;
	}

	public int getFiyat() {
		return fiyat;
	}

	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}
	  
			
		

}
