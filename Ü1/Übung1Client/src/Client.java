import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;



public class Client{
	
	private int zahl1;
	private int zahl2;
	private String Rechenzeichen;
	
	public Client(int zahl1, int zahl2, String Rechenzeichen){
		this.setZahl1(zahl1);
		this.setZahl2(zahl2);
		this.setRechenzeichen(Rechenzeichen);
	}
	
    public void client() throws UnknownHostException, IOException{
    			String serverAddress = JOptionPane.showInputDialog("Geben Sie die IP- Adresse des Servers ein, welcher auf Port 9000 l�uft:");  
    	        Socket s = new Socket(serverAddress, 9000); //Ein Socket mit oben angegebener IP-Addresse und Port 9000 wird erstellt
    	        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream()); //Ein Object outputstream wird erstellt, um die Zahlen zu versenden
    	        out.writeObject(this.getZahl1()+";"+this.getZahl2()+";"+this.getRechenzeichen());//Zahlen werden versendet
    	        out.flush();
    	        
    	        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	        String answer = input.readLine();
    	        JOptionPane.showMessageDialog(null, answer);   	    
    	        s.close();
    }
    


	

	public int getZahl1() { //Getter Und Setter f�r Zahlen, welche �bergeben wurden.
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
    
}
