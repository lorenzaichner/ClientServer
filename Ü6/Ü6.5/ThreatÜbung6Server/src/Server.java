import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

        public void start() {
       
                try {
                    ServerSocket listener = new ServerSocket(9000);
                    boolean p = true;
                    ExecutorService executor = Executors.newCachedThreadPool();                  
                    while (p) {
                        Socket socket = listener.accept();                                                            
                        executor.execute(new SThread(socket, this));
                    }
                } catch (Exception e) {
                	
                    e.printStackTrace();
                
        }
                System.out.println("Server beendet");
        }
}
