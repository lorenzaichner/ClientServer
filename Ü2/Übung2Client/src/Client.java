import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;



public class Client{
	
	private int zahl1;
	private int zahl2;
	private String Rechenzeichen;
	private String auth;
	private String serverAddress;
	

	public Client(int zahl1, int zahl2, String Rechenzeichen, String authentifizierung, String serverAddress){
		this.setZahl1(zahl1);
		this.setZahl2(zahl2);
		this.setRechenzeichen(Rechenzeichen);
		this.setServerAddress(serverAddress);
		this.setAuth(authentifizierung);
	}
	
    public void client() throws UnknownHostException, IOException{
    		
    	  
    	        Socket s = new Socket(this.getServerAddress(), 9000); //Ein Socket mit oben angegebener IP-Addresse und Port 9000 wird erstellt
    	        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream()); //Ein Object outputstream wird erstellt, um die Zahlen zu versenden
    	        ObjectInputStream in = new ObjectInputStream(s.getInputStream());  	       
    	        Authentifizierung auth = new Authentifizierung(getAuth()); 	       
    	        auth.send(out);
    	       
    	        BenutzerAnmeldung ben = (BenutzerAnmeldung) Message.fromStream(in);
    	        
    	        if(ben.isWeiter()){
    	        
    	        out.writeObject(this.getZahl1()+";"+this.getZahl2()+";"+this.getRechenzeichen());//Zahlen werden versendet
    	        out.flush();
    	        
    	        BufferedReader input =
    	            new BufferedReader(new InputStreamReader(s.getInputStream()));
    	        String answer = input.readLine();
    	        JOptionPane.showMessageDialog(null, answer);   	 
    	        }else if(ben.isWeiter() == false){
    	        	JOptionPane.showMessageDialog(null, "Ihr Benutzername ist falsch!");  
    	        }
    	        s.close();
    	        
    }
    


	

	public int getZahl1() { //Getter Und Setter für Zahlen, welche übergeben wurden.
		return zahl1;
	}

	public void setZahl1(int zahl1) {
		this.zahl1 = zahl1;
	}

	public int getZahl2() {
		return zahl2;
	}

	public void setZahl2(int zahl2) {
		this.zahl2 = zahl2;
	}

	public String getRechenzeichen() {
		return Rechenzeichen;
	}

	public void setRechenzeichen(String rechenzeichen) {
		Rechenzeichen = rechenzeichen;
	}
	
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	
    
}
