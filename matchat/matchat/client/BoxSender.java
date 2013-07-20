package matchat.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import matchat.message.MessageBox;

public final class BoxSender {
	
	private static final int DEFAULT_PORT = 4343; //default values set
	private static final String DEFAULT_HOST = "localhost"; //default values set
	
	private final int port; //default values set
	private final String host; //default values set
	
	private final MessageBox toBeSent;
	
	
	private static int maxSendAttempts = 5;
	
	public BoxSender(MessageBox toBeSent, String host, int port) {
		nullCheck(toBeSent, host);
		portCheck(port);
		
		this.toBeSent = toBeSent;
		this.host = host;
		this.port = port;
	}
	public BoxSender(MessageBox toBeSent) {
		nullCheck(toBeSent);
		
		this.toBeSent = toBeSent;
		this.port = DEFAULT_PORT ;
		this.host = DEFAULT_HOST;
	}
	
	public void send() throws IOException {
		int attempts = 0;
		while (attempts < maxSendAttempts) {
			Socket s = null;
			OutputStream os = null;
			ObjectOutputStream oos = null;
			try {
				s = new Socket(host, port);
				os = s.getOutputStream();
				oos = new ObjectOutputStream(os);
				oos.writeObject(toBeSent);
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.out.println("Could not connect to unknown host");
			} finally {
				try {
					if (oos != null) oos.close();
					if (os != null) os.close();
					if (s != null) s.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}
	
	private void nullCheck(Object ... o) {
		if (o == null) throw new NullPointerException();
	}
	
	private void portCheck(int port) {
		if (port < 0 || port >= 65536)
			throw new IllegalArgumentException("Invalid port specified: " + port);
	}

}
