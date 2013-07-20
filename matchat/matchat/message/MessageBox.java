package matchat.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageBox implements Serializable , Iterable<Message> {
	/**
	 * Development serial version UID in use - 1L 
	 */
	private static final long serialVersionUID = 1L;
	
	//The server database may be concerned with these fields
	private final long ID ;
	
	
	private String fromUsername ;
	private String toUsername ;
	
	private List<Message> messages;
	
	public MessageBox(String from, String to, long ID) {
		nullCheck(from, to);
		this.fromUsername = from;
		this.toUsername = to;
		this.ID = ID;
		
		messages = new ArrayList<Message>();
	}
	
	public MessageBox(long ID) {
		this.ID = ID;
		
		messages = new ArrayList<Message>();
	}
	
	public boolean addMessage(Message message) {
		return messages.add(message);
	}
	
<<<<<<< HEAD
	public void addMessage(Message m) {
		/**
		 * Add a message to the tail of Message array
		 */
		if (m==null) {
			throw new IllegalArgumentException("Error while adding message. Cannot add NULL message to MessageBox");
		}
		ensurecapacity();
		//set final message to parameter message
		messages[quantity] = m;
		quantity++;
	}
	public void removeMessage(int index) {
		/**
		 * Add a message to the tail of Message array
		 */
		if (index+1>quantity) {
			throw new IllegalArgumentException("No message with index "+index+" exists on this mailbox");
		}
		if (index<0) {
			throw new IllegalArgumentException("Non-positive integer encountered for message index");
		}
		//shift array upwards
		for (int i=index ; i<quantity-1 ; i++) { //need to protect against over-reach
			messages[i] = messages[i+1];
		}
		messages [quantity-1] = null;
		quantity--;
	}
	private void ensurecapacity() {
		if (messages.length == quantity) {
			//double array
			Message[] temp = new Message[quantity*2 + 1];
			//copy it
			for (int i=0 ; i<quantity ; i++) {
				temp[i] = messages[i];
			}
			this.messages = temp;
		}
		else if (messages.length == quantity/2 - 1) {
			Message[] temp = new Message[quantity/2];
			for (int i=0 ; i<quantity ; i++) {
				temp[i] = messages[i];
			}
			this.messages = temp;
		}
=======
	public Message removeMessage(int index) {
		indexCheck(index);
		
		return messages.remove(index);
>>>>>>> d87aa39cbe68309071eb7361ae18d6b947eaf21a
	}
	
	public Message getMessage(int index) {
		indexCheck(index);
		
		return messages.get(index);
	}
	
	public Message changeItem(int index , Message message) {
		indexCheck(index);
		
		//point to old message
		Message oldMessage = getMessage(index);
		
		//swap new message and old message
		messages.set(index, message);
		
		return oldMessage;
	}
	
	//check for index boundary violations
	private void indexCheck(int index) {
		if (index < 0 || index >= messages.size()) 
			throw new IllegalArgumentException("Invalid index: " + index);
	}
	
	/**
	 * Get username message box is addressed to
	 * @return Username of recipient
	 */
	public String to() {
		return toUsername;
	}
	
	/**
	 * Get username of user who sent message box
	 * @return Sender's username
	 */
	public String from() {
		return fromUsername;
	}
	
	/**
	 * Unique identifying number for this specific message box. The scope of this ID spans the entire 
	 * MatChat deployment, such that no ID is shared by any two users of the system
	 * @return
	 */
	public long ID() {
		return ID;
	}
	
	/**
	 * Returns the number of messages associated with and being delivered by the mailbox
	 * @return Message count 
	 */
	public int quantity() {
		return messages.size();
	}
	
	/**
	 * Modify field of recipient
	 * @param to
	 */
	public void setTo(String to) {
		nullCheck(to);
		this.toUsername = to;
	}
	
	/**
	 * Change the sender  
	 * @param from username of sender
	 */
<<<<<<< HEAD
	public void setID(long ID) {
		this.ID = ID;
	}
	/**
	 * Change the ID of the messagebox
	 * @param ID the unique ID of the message box
	 */
	@Override
	public Iterator<Message> iterator() {
		return (Iterator<Message>) new MessageIterator<Message>(messages , quantity) ;
	}
	
	public List<Notification> notifications() {
		return null ;
	}
}
class MessageIterator<E extends Message> implements Iterator<E> , Serializable{
	/**
	 * Development UID
	 */
	private static final long serialVersionUID = 1L;
	private E[] messages;
	private int messageIndex;
	private int quantity ;
	public MessageIterator(Message[] messagesSrc , int quantitySrc) {
		messages = (E[]) new Message[quantitySrc] ;
		System.arraycopy(messagesSrc , 0  , this.messages , 0 , quantitySrc);
		this.quantity = quantitySrc ;
=======
	public void setFrom(String from) {
		nullCheck(from);
		this.fromUsername = from;
>>>>>>> d87aa39cbe68309071eb7361ae18d6b947eaf21a
	}

	private void nullCheck(Object ... o) {
		if (o == null) 
			throw new NullPointerException("Null argument: " + o);
	}
	
	@Override
	public Iterator<Message> iterator() {
		return messages.iterator();
	}
	
}
