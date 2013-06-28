package matchat.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import matchat.message.Message;

public class SimpleDataStore {
	private Map<String , ? extends List<Message>> messageMap ;
	//not the best data structure to use due to load factor ??
	public SimpleDataStore () {
		messageMap = new HashMap<String , List<Message>>() ;
	}
	public synchronized void addMessage(Message message) {
		String username = message.toUserName() ;
		
		if (messageMap.containsKey(username)) {
			//retrieve the set of messages addressed to the user
			messageMap.get(username).add(message) ;
			
		}
	}
	/*public synchronized Set<Message> messageSet (String user) throws NoSuchElementException{
		if (messageMap.containsKey(user)) {
			Set<Message> m = messageMap.get(user) ;
			return m ;
		}else {
			throw new NoSuchElementException("No messages exist for user: "+user);
		}
	}*/
	public synchronized Message nextMessage (String user) throws NoSuchElementException{
		if (messageMap.containsKey(user)) {
			//retrieve the set of messages addressed to the user
			List<Message> messageList = messageMap.get(user) ;
			return messageList.remove(0);
			
		}else {
			throw new NoSuchElementException("No messages for user: "+user);
		}
	}
}
