import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			boolean anmelden = true;
			double x = 0;
			Socket socket = listener.accept();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    
			while(anmelden){
				 Authentifizierung a= (Authentifizierung) Message.fromStream(in);
				 System.out.println(a.getAuth());
				 BenutzerAnmeldung anm = new BenutzerAnmeldung(a.getAuth());
				 
				 if(anm.check()){
					 anmelden = false; 
					 anm.send(out);
				 }else if(anm.check() == false){
					 System.out.println("test");
					 anm.send(out);
					 weiter = false;
					 anmelden = false;
				 }
			}
			
			
			while (weiter) { //Solange er nichts von Client bekommt, macht er weiter
    			 
    			 
    			 
    			 String tocalc = (String)in.readObject(); //Vom Client gesendete Daten werden in tocalc gespechert   	
               
    			 try {
            		PrintWriter outRechnung= new PrintWriter(socket.getOutputStream(), true); // out wird benötigt, um nach Client zu senden
            		if(x != 0){ // wenn etwas ausgerechnet wurde, schaltet sich der Server aus
            			weiter = false;
            		}
            		x = this.berechnen(tocalc); // Ergebniss wird berechnet
            		System.out.println(x);
            		outRechnung.println("Ihr Ergebniss lautet: "+ x); //Ergebniss wird zurückgeschickt
                   	} finally {
                	socket.close();
            	}
        	}
			socket.close();
			listener.close();
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
	


