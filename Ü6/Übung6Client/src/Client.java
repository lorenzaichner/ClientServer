import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class Client {

    public static void main(String[] args) throws IOException {
    	
    	String serverAddress = JOptionPane.showInputDialog("Geben Sie die IP- Adresse des Servers ein, welcher auf Port 9000 läuft:");  
        Socket s = new Socket(serverAddress, 9000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ToSend toSend = new ToSend();
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());

             	String inputBuff = JOptionPane.showInputDialog("Geben Sie 0 ein, wenn Sie ein Randomwert wollen.\n"
        													 + "Geben Sie 1 ein, wenn Sie ein die Zeit wollen. ");                 
        int input = Integer.parseInt(inputBuff);        
                try {
                    toSend.setInfo(input);      
                    toSend.send(out);
                    toSend = (ToSend)Message.fromStream(in);

                    if(toSend.getInfo2()==1){
                        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/hh:mm:ss");
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(toSend.getInfo2());
                        JOptionPane.showMessageDialog(null, "Ihre Rückgabe: " + formatter.format(cal.getTime())); 
           
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Ihre Rückgabe: " +toSend.getInfo()); 
                    }
                } catch (Exception e) {              
                    e.printStackTrace();
                }       
        System.out.println("Programm beendet");
    }
}
