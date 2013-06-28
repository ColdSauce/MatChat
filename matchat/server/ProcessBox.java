package matchat.server;

import java.util.Iterator;

import matchat.message.Message;
import matchat.message.MessageBox;

public class ProcessBox {
	MessageBox messageBox ;
	SimpleDataStore data ;
	public void ProcessBox(MessageBox mb , SimpleDataStore data) {
		assert mb != null ;
		assert data != null ;
		this.messageBox = mb ;
		this.data = data ;
	}
	public MessageBox process() {
		
		Iterator<Message> it = (Iterator<Message>) messageBox.iterator();
		while (it.hasNext()) {
			//have the SimpleDataStore take all required messages 
			Message current = it.next() ;
			data.addMessage(current) ;
		}
		return messageBox;
	}
}
