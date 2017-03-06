import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    ServerSocket serverSocket;
    Socket connection = null;

        public void start() {

        	
                try {
                    ServerSocket listener = new ServerSocket(9000);
                    ExecutorService executor = Executors.newCachedThreadPool();             
                    boolean weiter = true;
                  
                        Socket s = listener.accept();                                                  
                        executor.execute(new SThread(s, this));
                    listener.close();
                } catch (Exception e) {
                    e.printStackTrace();                  
            }
                System.out.println("Server beendet");
        }
}
