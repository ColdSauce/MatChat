package matchat.server;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import matchat.message.Message;
import matchat.server.store.SimpleDataStore;


public class SimpleServerFront {
	private SimpleDataStore data = new SimpleDataStore() ;
	private final static int port = 3434;
	
	SimpleServerListener listener ;
	
	public SimpleServerFront() throws IOException {
		
	}
	
	public static void main(String argv[]) {
		/*SimpleServerFront front = null;
		try {
			front = new SimpleServerFront();
		} catch (IOException e) {
			System.out.println("Could not begin listening on port "+port) ;
			e.printStackTrace();
		}
		if (front != null) front.activate() ;
	}*/}
	public void activate() {
		try {
			listener = new SimpleServerListener (port) ;
			Thread mainThread = new Thread (listener) ;
			mainThread.start();
		} catch (IOException e) {
			System.out.println("Could not establish server. Exiting.");
			e.printStackTrace();
		} 
		//listener.startListening();
	}
	public void deactivate() {
		listener.kill() ;
	}
	public int port () {
		return port ;
	}
	
}
