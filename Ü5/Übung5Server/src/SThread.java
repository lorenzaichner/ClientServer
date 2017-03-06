import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SThread extends Thread {
    private Socket socket;
    private Server server;
    BufferedReader in = null;
    PrintWriter out;

    public SThread(Socket socket, Server server) throws IOException {          

        this.socket = socket;
        this.server = server;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run(){
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            try {
                ToSendGet buffpath = (ToSendGet)Message.fromStream(in);
                Path path = (Paths.get(buffpath.getPfad()));
                
                OutputStream out = socket.getOutputStream();
                InputStream toSend = new FileInputStream(path.toFile());
                byte[] buffer = new byte[1024];
                while (toSend.available() > 0) {
                    out.write(buffer, 0, toSend.read(buffer));
                    System.out.println("Gesendet");  
                }
                toSend.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
        }finally {
            try{
            	System.out.println("Gesendet");  
            	socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
} 