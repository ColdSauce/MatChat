package matchat.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.Iterator;

import matchat.message.*;

public class ServeJob implements Runnable{
	/**
	 * This class models a single unit related to processing incoming MessageBoxes
	 * 
	 */
	private MessageBox messageBox ;
	private Socket socket ;
	private int status = 0;
	private SimpleDataStore data;
	/**
	 * Provisional statuses:
	 * -1 - Failure
	 * 0 - Job completed
	 * 1 - Reading MessageBox object.
	 * 2 - Processing MessageBox object
	 */
	public ServeJob(Socket socket , SimpleDataStore data) {
		assert socket != null && data != null ;
		this.socket = socket ;
		this.data = data ;
	}
	@Override
	public void run() {
		/*
		 * First thing that is expected to be read is a messageBox object
		 */
		System.out.println("Serve job started");
		ObjectInputStream objectInstream ;
		Object nextObject = null ;
		try {
			objectInstream = new ObjectInputStream(socket.getInputStream()) ;
			nextObject = objectInstream.readObject();
		} catch (IOException e) {
			status = -1 ;
		} catch (ClassNotFoundException e) {
			status = -1 ; 
		} finally {
			if (status < 0) return ;
		}
		if (nextObject instanceof MessageBox) {
			//success , a MessageBox has been received
			MessageBox outgoing = processMessageBox((MessageBox) nextObject) ;
			//write response to message back
			writeBack(outgoing) ;
			//operation completed successfully. 
			return ;
		}else {
			status = -1 ;
			return ;
		}
	}
	private void writeBack (MessageBox box) {
		try {
			ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream()) ;
			outstream.writeObject(box); 
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				//meh!
			}
			if (status < 0) return ;
		}
		
	}
	private MessageBox processMessageBox(MessageBox box) {
		assert box != null ;
		ProcessBox processor = new ProcessBox( box , data);
		MessageBox outgoing = processor.process();
		return outgoing ;
	}
	public int status() {
		return status;
	}
	
}
