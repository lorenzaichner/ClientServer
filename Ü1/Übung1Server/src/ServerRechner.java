import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class ServerRechner {
	
public void server() throws IOException{
	ServerSocket listener = new ServerSocket(9000);
	
		try {
			boolean weiter = true;
			double x = 0;
			Socket socket = listener.accept();
			while (weiter) { //Solange er nichts von Client bekommt, macht er weiter
    			 ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    			 String tocalc = (String)in.readObject(); //Vom Vlient gesendete Daten werden in tocalc gespechert   	
               	try {
            		PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // out wird benötigt, um nach Client zu senden
            		if(x != 0){ // wenn etwas ausgerechnet wurde, schaltet sich der Server aus
            			weiter = false;
            		}
            		x = this.berechnen(tocalc); // Ergebniss wird berechnet
            		System.out.println(x);
            		out.println("Ihr Ergebniss lautet: "+ x); //Ergebniss wird zurückgeschickt
                   	} finally {
                	socket.close();
            	}
        	}
			socket.close();
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
    		listener.close();
    	}
	}

	public double berechnen(String toCalc){ // Ergebniss wird berechnet- Funktion
	String[] arrayCalc = new String[3];
	
	double a = 0;
	
	arrayCalc = toCalc.split(";"); //Zahlen werden nach ; gesplittet und in Array gespeichert

	double zahl1 = Double.parseDouble(arrayCalc[0]);//Zahlen werden geparst, nach double
	double zahl2 = Double.parseDouble(arrayCalc[1]);

	if(arrayCalc[2].equals("+")){
		a = zahl1 + zahl2;
	}
	if(arrayCalc[2].equals("-")){
		a = zahl1 - zahl2;
	}
	if(arrayCalc[2].equals("*")){
		a = zahl1 * zahl2;
	}
	if(arrayCalc[2].equals("/")){
		a = zahl1 / zahl2;
	}
	return a;
	}
}
	


