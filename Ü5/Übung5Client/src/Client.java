import java.io.*;
import java.net.Socket;

import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class Client {


        public static void main(String[] args) throws IOException {
            ToSendGet sd = new ToSendGet();          
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String serverAddress = JOptionPane.showInputDialog("Geben Sie die IP- Adresse des Servers ein, welcher auf Port 9000 läuft:");        
        
            	boolean exit= false;
            while(!exit) {


            	String pfad = JOptionPane.showInputDialog("Bitte geben sie den Pfad der Date an, welche Sie transferieren wollen.");      
              
            		if (pfad != null) {

            		sd.setPfad(pfad);
                    Socket s = new Socket(serverAddress, 9000);                                        

                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

                    sd.send(out);

                    String file = Paths.get(sd.getPfad()).toString();

                    String extension = "";

                    int i = file.lastIndexOf('.');
                    if (i > 0) {
                        extension = file.substring(i+1);
                    }

                    InputStream in = s.getInputStream();
                    FileOutputStream fileOut = new FileOutputStream("Client-downloadet."+extension);

                    byte[] buffer = new byte[1024];                                 //File wird gespeichert
                    while (s.isConnected()) {
                        int bytesRead = in.read(buffer);
                        if (bytesRead == -1) break;
                        fileOut.write(buffer, 0, bytesRead);
                    }

                    fileOut.close();
                    exit=true;
                }
            }

            System.out.println("Programm beendet");


        }


}
