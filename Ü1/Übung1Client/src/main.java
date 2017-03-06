import java.io.IOException;

import javax.swing.JOptionPane;

public class main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int zahl1 = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die erste Zahl ein"));
		int zahl2 = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die zweite Zahl ein"));
		String zeichen = JOptionPane.showInputDialog("Geben Sie Ein REchenzeichen ein"); 
		Client c = new Client(zahl1, zahl2, zeichen); // Diese Zahlen mit Rechenzeichen werden übergeben
		try {
			c.client();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
