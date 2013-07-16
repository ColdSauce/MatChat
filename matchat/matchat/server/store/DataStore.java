package matchat.server.store;

import java.util.List;
import java.util.NoSuchElementException;

import matchat.message.Message;

public interface DataStore {
	public Message getMessageById (long ID) throws NoSuchElementException ;
	public void addMessage(Message m) throws IllegalArgumentException;
	public List<Message> unsentMessagesForUser(String user) ;
	public List<Message> awaitingMessagesForUser(String user) ;
	public boolean markAsSent(long ID , String user) throws NoSuchElementException ;
	// public boolean markAsUnsent(long ID , String user) throws NoSuchElementException ;
}
