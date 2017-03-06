import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) {        

                try {
               	ServerSocket listener = new ServerSocket(9000);          	                        
                        Socket socket = listener.accept();                                                                      
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());               
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 
                        try {
                                                    
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
                            System.out.println("Fehler!");
                            e.printStackTrace();
                        }
                } catch (Exception e) {
                    System.out.println("Fehler!");
                    e.printStackTrace();
                }
            System.out.println("Server beendet");


        }


}