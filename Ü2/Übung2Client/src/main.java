import java.io.IOException;

import javax.swing.JOptionPane;

public class main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String serverAddress = JOptionPane.showInputDialog(	//IP-Adresse von Server wird gefordert (localhost)
	            "Enter IP Address of a machine that is\n" +
	            "running the date service on port 9000:");
		
		
	
		
		String authentifizierung =JOptionPane.showInputDialog("Geben Sie bitte ihr Benutzername ein!"); 
		int zahl1 = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die erste Zahl ein"));
		int zahl2 = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die zweite Zahl ein"));
		String zeichen = JOptionPane.showInputDialog("Geben Sie Ein REchenzeichen ein"); 
		Client c = new Client(zahl1, zahl2, zeichen, authentifizierung, serverAddress); // Diese Zahlen mit Rechenzeichen werden übergeben
		try {
			c.client();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
