
public class BenutzerAnmeldung extends Message{
	
	private String benutzer;
	private String fixerBenutzer = "Lorenz";
	private boolean weiter;
	
	public BenutzerAnmeldung(String benutzer){
		setBenutzer(benutzer);
	}

	
	
	public boolean check(){
		if(benutzer.equals(fixerBenutzer)){
			this.setWeiter(true);
			
			return true;
			
			
			
		}else{
			this.setWeiter(false);
			return false;
		}
	}

	public String getFixerBenutzer() {
		return fixerBenutzer;
	}

	public void setFixerBenutzer(String fixerBenutzer) {
		this.fixerBenutzer = fixerBenutzer;
	}

	public boolean isWeiter() {
		return weiter;
	}

	public void setWeiter(boolean weiter) {
		this.weiter = weiter;
	}
	
	public String getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(String benutzer) {
		this.benutzer = benutzer;
	}
	
	
	

}
