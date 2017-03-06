import java.io.*;
import java.net.Socket;
import java.util.Date;


public class SThread extends Thread {
    private Socket socket;
    private Server server;

    public SThread(Socket socket, Server server) throws IOException {          

        this.socket = socket;
        this.server = server;
        
    }

    @Override
    public void run() {
        try {

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
  
            ToSend toSend = (ToSend)Message.fromStream(in);
            
            if (toSend.getInfo() == 0) {
                double re = Math.random()*99*(Math.random())*100;
                Thread.sleep(1000);
                toSend.setInfo2(0);
                toSend.setInfo(re);
            } else {
                long re = System.currentTimeMillis();
                Thread.sleep(1000);
                toSend.setInfo(1);
                toSend.setInfo2(re);
            }                    

            toSend.send(out);
           


        } catch (Exception e) {

        
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                

            } catch (Exception e) {

                System.out.println("Fehler Server");
                e.printStackTrace();

            }
        }
    }


}





