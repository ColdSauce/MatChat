package matchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import matchat.server.store.SimpleDataStore;

public class SimpleServerListener implements Runnable{
	ServerSocket socket ;
	private static int maxClients = 10 ;
	//List<Thread> threads = new ArrayList<Thread> () ;
	SimpleDataStore data = new SimpleDataStore() ;
	private int port;
	//only one reference to SimpleDataStore used by all other associated classes
	public SimpleServerListener(int port) throws IOException {
		this.port = port ;
		System.out.println("Instance of listener created");
	}
	public void startListening() {
		Thread lastThread = null ;
		try {
			socket = new ServerSocket(port) ;
		} catch (IOException e1) {
			System.out.println("Failed to start server socket on listener. \nCould not listen on port"+port) ;
			e1.printStackTrace();
			return ;
		}
		while (true) {
			Socket connection = null ;
			if (lastThread == null || Thread.activeCount() < maxClients) {
				try {
					 connection = socket.accept() ;
					 System.out.println("Oh yeah! Got a socket");
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
	@Override
	public void run() {
		this.startListening();
	}
	public void kill() {
		if (socket != null ) {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Failed to close socket cleanly:\n"+e.getMessage()) ;
			}
		}
	}
}
