import javax.swing.JOptionPane;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.net.Socket;

public class Client {


        public static void main(String[] args) throws IOException {
        	
        	String serverAddress = JOptionPane.showInputDialog("Geben Sie die IP- Adresse des Servers ein, welcher auf Port 9000 läuft:");          
        	
            Url m = new Url(); //Objekt wird erzeugt, das gesendet wird
            Socket s = new Socket(serverAddress, 9000); 
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

               
                       
            boolean exit= false;
       
            String url = JOptionPane.showInputDialog("Bitte geben sie ihre URL mit http:// ein!");
               if (url != null) {

                    m.setMessage(url); //Verbindung wird aufgebaut
                    m.send(out);
             
                    InputStream in = s.getInputStream();
                    FileOutputStream fileOut = new FileOutputStream("Download.html");

                    byte[] buffer = new byte[1024];
                    while (s.isConnected()) {
                        int bytesRead = in.read(buffer);
                        if (bytesRead == -1) break;
                        fileOut.write(buffer, 0, bytesRead);
                    }

                    fileOut.close();
                    exit=true;


                }
            System.out.println("Programm beendet");
        }


}
