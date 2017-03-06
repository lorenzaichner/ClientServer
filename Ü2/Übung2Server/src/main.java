import java.io.IOException;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerRechner s = new ServerRechner();
		try {
			s.server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
