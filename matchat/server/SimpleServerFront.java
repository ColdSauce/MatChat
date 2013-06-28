package matchat.server;
import java.io.IOException;
import java.net.*;

public class SimpleServerFront {
	private SimpleDataStore data = new SimpleDataStore() ;
	private final static int port = 3434;
	
	SimpleServerListener listener ;
	
	public SimpleServerFront() throws IOException {
		listener = new SimpleServerListener (port) ;
	}
	
	public static void main(String argv[]) {
		SimpleServerFront front = null;
		try {
			front = new SimpleServerFront();
		} catch (IOException e) {
			System.out.println("Could not begin listening on port "+port) ;
			e.printStackTrace();
		}
		front.activate() ;
	}
	public void activate() {
		try {
			listener = new SimpleServerListener(port) ;
		} catch (IOException e) {
			System.err.println("Could not establish server listener.");
			e.printStackTrace() ;
		}
	}
}
