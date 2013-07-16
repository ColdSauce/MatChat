package matchat.server;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import matchat.message.Message;
import matchat.message.MessageBox;
import matchat.message.Notification;
import matchat.server.store.SimpleDataStore;

public class ProcessBox {
	MessageBox messageBox ;
	SimpleDataStore data ;
	Map< String , MessageBox > MessageMap = new HashMap<String , MessageBox > () ; 
	boolean replyon = true ;
	/**
	 * Construct a processbox with the MessageBox it is responsible for processing and the SimpleDataStore it
	 * uses for the operation
	 */
	public ProcessBox(MessageBox mb , SimpleDataStore data) {
		assert mb != null ;
		assert data != null ;
		this.messageBox = mb ;
		this.data = data ;
	}
	/**
	 * Execute the process operation. Currently the ProcessBox extracts the Message objects out of the 
	 * MessageBox , then writes them to `data`. A MessageBox with remaining messages for the calling 
	 * user is generated. 
	 */
	public MessageBox process() {
		
		Iterator<Message> it = messageBox.iterator();
		String username = messageBox.from();
		//if (username == null) System.out.println ("Poop!") ;
		while (it.hasNext()) {
			//have the SimpleDataStore take all required messages 
			Message current = it.next() ;
			
			data.addMessage(current) ;
		}
		MessageBox out =  generateMessage(username) ;
		return out ;
	}
	/**
	 * Helper method responsible for generating the messagebox of remaining messages 
	 * intended for `user`.
	 * @param user
	 * @return
	 */
	private void processNotification (Notification message) {
		
	}
	private MessageBox generateMessage(String user) {
		MessageBox box = new MessageBox (new Date() , messageBox.ID()+1);
		boolean next = true ;
		while (next) {
			try {
				Message m = data.nextMessage(user) ;
				box.addMessage(m);
			} catch (NoSuchElementException e) {
				next = false ;
			}
		}
		return box ;
	}
}
