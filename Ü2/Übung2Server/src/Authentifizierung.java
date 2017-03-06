import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Authentifizierung extends Message{
	
	private String auth; 
		 

	public Authentifizierung(String name){
		this.setAuth(auth);
	}
	
	public void getBenutzer(){
		
	}
	

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	
}
