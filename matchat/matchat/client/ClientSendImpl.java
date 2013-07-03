package matchat.client;

import java.util.Date;

import matchat.message.Message;
import matchat.message.MessageBox;

public class ClientSendImpl implements ClientSend {
	Sender sender;
	
	private int status; 
	public static String localUserName ;
	
	public static long lastID ;
	
	public ClientSendImpl() {
		sender = new BoxSender() ;
	}
	@Override
	public void sendAll(ClientQueue queue) {
		// TODO Auto-generated method stub
		MessageBox box = new MessageBox(new Date() , ++lastID) ;
		while (!queue.isEmpty()) {
			try {
				box.addMessage(queue.dequeueMessage()) ;
			} catch (EmptyQueueException e) {
				status = -1 ;
				throw new IllegalStateException("Queue dequeued while isEmpty returned false. These invariants are inconsistent") ;
			}
			sender.setMessageBox(box);
			sender.send();
			status = 0;
		}
	}

	@Override
	public int status() {
		return status ;
	}
	
	@Override
	public void clearAll() {
		status = 0 ;
	}

	
	
}
