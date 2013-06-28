package matchat.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import matchat.message.MessageBox;

public class BoxSender implements Sender {
	private static final int DEFAULT_PORT = 4343; //default values set
	private static final String DEFAULT_HOST = "localhost"; //default values set
	private int port = 4343; //default values set
	private String host = "localhost"; //default values set
	private MessageBox toSend;
	private static int maxSendAttempts = 5;
	
	public BoxSender(String host , int port) {
		assert host != null : "Host supplied to BoxSender cannot be null!" ;
		//makes a term of contract with object creating BoxSender that 
		//host is nonnull
		assert port < 65535 && port > 0 ;
		// TCP/UPD port invariant must hold
		this.host = host;
		this.port = port;
	}
	public BoxSender() {
		/*
		 * First the default invariants are checked. Then we set the instance
		 * values to the default
		 */
		assert DEFAULT_HOST != null : "Host supplied to BoxSender cannot be null! Static 'host' field must be set" ;
		//makes a term of contract with object creating BoxSender that 
		//host is nonnull
		assert DEFAULT_PORT < 65535 && DEFAULT_PORT > 0 : "Port supplied must be valud. Static 'port' field must be set";
		// TCP/UPD port invariant must hold
		this.port = DEFAULT_PORT ;
		this.host = DEFAULT_HOST;
	}
	@Override
	public void setMessageBox(MessageBox box) throws IllegalArgumentException{ //checked exception requires client to supply good arguments!
		if (box==null) throw new IllegalArgumentException("Sender cannot have a null box");
		toSend = box;
	}
	@Override
	public void send() {
		boolean finished = false;
		int attempts = 0;
		while (!finished && attempts<maxSendAttempts) {
			Socket s = null;
			OutputStream o = null;
			ObjectOutputStream boxObOut = null;
			try {
				s = new Socket(host , port);
				o = s.getOutputStream();
				boxObOut = new ObjectOutputStream(o);
				boxObOut.writeObject(boxObOut);
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.out.println("Could not connect to unknown host");
			} catch (IOException e) {
				System.out.println("An IO exception occurred");
				e.printStackTrace();
			} 
			try { 
				if (boxObOut!=null) boxObOut.close();
				if (o!=null) o.close();
				if (s!=null) s.close();
			} catch (IOException e) {
				System.out.println("Could not close sockets");
			}
		}
	}

	@Override
	public void changeHost(String host) throws IllegalArgumentException {
		if (host == null) throw new IllegalArgumentException("Sender cannot have a null host");
		this.host = host ;
	}

	@Override
	public void changePort(int port) throws IllegalArgumentException{
		if (port < 0 || port >= 65536 ) throw new IllegalArgumentException ("Invalid port specified: "+port) ;
		this.port = port;
	}

	@Override
	public MessageBox getReceipt() {
		// TODO Auto-generated method stub
		return null;
	}

}
