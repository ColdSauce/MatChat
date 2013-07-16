package matchat.server.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import matchat.message.Message;

public class SimpleDataStore {
	private Map<String , List<Message>> messageMap ;
	//not the best data structure to use due to load factor ??
	public SimpleDataStore () {
		messageMap = new HashMap<String , List<Message>>() ;
	}
	public synchronized void addMessage(Message message) {
		//assert message != null : "Cannot add NULL message" ;
		if (message == null) throw new IllegalArgumentException("Cannot process NULL message") ;
		System.out.println("Processing...");
		
		String username = message.toUserName() ;
		
		if (messageMap.containsKey(username)) {
			//retrieve the set of messages addressed to the user
			messageMap.get(username).add(message) ;
			
		}else {
			List<Message> newMessageList = new ArrayList<Message>();
			newMessageList.add(message);
			messageMap.put(username, (List<Message>)newMessageList );
		}
	}
	public synchronized Message nextMessage (String user) throws NoSuchElementException{
		if (messageMap.containsKey(user)) {
			//retrieve the set of messages addressed to the user
			List<Message> messageList = messageMap.get(user) ;
			if (messageList.size() != 0) return messageList.remove(0);
			else throw new NoSuchElementException("No messages for user: "+user);
			
		}else {
			throw new NoSuchElementException("No messages for user: "+user);
		}
	}
	public synchronized List<Message> userMessageList(String user) throws NoSuchElementException{
		if (messageMap.containsKey(user)) {
			//retrieve the set of messages addressed to the user
			List<Message> messageList = messageMap.get(user) ;
			return messageList ;
			
		}else {
			throw new NoSuchElementException("No messages for user: "+user);
		}
	}
}
