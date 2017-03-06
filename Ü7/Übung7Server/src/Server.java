import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    ExecutorService executor = Executors.newCachedThreadPool();
    boolean weiter = true;
    Socket socket;
   

    	public void start() {
           
                    try {
                        ServerSocket listener = new ServerSocket(9000);
                        //ExecutorService executor = Executors.newCachedThreadPool();                  
                        while (weiter) {
                             socket = listener.accept();                                                            
                            executor.execute(new SThread(socket, this));
                        }
                    } catch (Exception e) {
                    	
                        e.printStackTrace();
                    
            }
                    System.out.println("Server beendet");
            }
    
    public void Shutdown(){
    	
        try {
        	setWeiter(false);
            executor.shutdown();
            socket.close();
            System.out.println("Server beendet");
        }catch (Exception e) {
            System.out.println("Fehler");
        }
    }
    
    public void setWeiter(boolean weiter){
    	this.weiter = weiter;
    }



}
