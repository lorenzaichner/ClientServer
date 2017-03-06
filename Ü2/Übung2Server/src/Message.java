import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public abstract class Message implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 312853181604924426L;
		
		public void send(ObjectOutputStream writer) {
			try {
				writer.writeObject(this);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static Message fromStream(ObjectInputStream reader) {
			try {
				return (Message) reader.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}

	
	

