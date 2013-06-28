package matchat.client;
import matchat.message.*;

public interface ClientQueue {
	public void enqueueMessage(Message m) throws IllegalArgumentException;
	public boolean removeMessage(Message m) ;
	public Message removeMessage(long ID) ;
	public Message dequeueMessage() throws EmptyQueueException ;
	public int size () ;
	public boolean isEmpty() ;
 }
