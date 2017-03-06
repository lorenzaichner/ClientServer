import java.io.*;

import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;


public class Server {



        public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9000);      
            try {
                System.out.println("Server gestartet");
                
                    Socket s = listener.accept();                              
               
                    try {

                        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                        try {
                        	
                            Url url = (Url) Message.fromStream(in);
                            String link = "http://"+url.getMessage();
                       
                            WebDownloader downloader = new WebDownloader();

                            try {
                            	downloader.saveTo(new URL(link),"ServerToSend.html");                

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }




                    OutputStream out = s.getOutputStream();
                            InputStream fileToSend = new FileInputStream("ServerToSend.html");

                            byte[] buffer = new byte[1024];
                            while (fileToSend.available() > 0) {
                                out.write(buffer, 0, fileToSend.read(buffer));
                                System.out.println("...");
                            }

                            fileToSend.close();




                        } catch (Exception e) {

                            System.out.println("Fehler!!");

                        }


                    } finally {
                        s.close();
                        System.out.println("Socket beendet");
         
                    }
                
            }
            finally {
                listener.close();
                System.out.println("Server beendet");
            }
        }





}
