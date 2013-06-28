package matchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimpleServerListener {
	ServerSocket socket ;
	private static int maxClients = 10 ;
	//List<Thread> threads = new ArrayList<Thread> () ;
	SimpleDataStore data = new SimpleDataStore() ;
	//only one reference to SimpleDataStore used by all other associated classes
	public SimpleServerListener(int port) throws IOException {
		socket = new ServerSocket(port) ;
	}
	public void startListening() {
		Thread lastThread = null ;
		while (true) {
			Socket connection = null ;
			if (lastThread == null || Thread.activeCount() < maxClients) {
				try {
					 connection = socket.accept() ;
				} catch (IOException e) {
					System.out.println("Error whilst accepting socket connection");
					e.printStackTrace();
				} 
				//connection accepted! now create a new ServeJob to deal with it.
				assert connection != null ;
				ServeJob job = new ServeJob(connection , data) ;
				Thread thread = new Thread(job) ;
				thread.start() ;
				lastThread = thread ;
			}
			
		}
	}
	public int maxClients () {
		return maxClients ;
	}
	public void setMaxClients(int clients) {
		this.maxClients = clients ;
	}
}
