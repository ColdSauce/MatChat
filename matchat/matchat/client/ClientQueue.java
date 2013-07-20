package matchat.client;

import java.io.IOException;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import matchat.message.Message;
import matchat.message.MessageBox;


public class ClientQueue extends AbstractQueue<Message> {
	
	private List<Message> messages;
	
	public ClientQueue() {
		messages = new ArrayList<Message>();
	}
	
	public static void sendAll(ClientQueue toSend) {
		MessageBox messageBox = new MessageBox(0);
		
		Message m = toSend.poll();
		
		while (m != null) {
			messageBox.addMessage(m);
			m = toSend.poll();
		}
		
		try {
			new BoxSender(messageBox).send();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean offer(Message e) {
		if (e == null) return false;
		else return super.add(e);
	}

	@Override
	public Message poll() {
		if (size() == 0) return null;
		
		return messages.remove(0);
	}

	@Override
	public Message peek() {
		if (size() == 0) return null;
		
		return messages.get(0);
	}

	@Override
	public Iterator<Message> iterator() {
		return messages.iterator();
	}

	@Override
	public int size() {
		return messages.size();
	}

}
