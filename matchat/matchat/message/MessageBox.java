package matchat.message;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MessageBox implements Serializable , Iterable<Message> {
	/**
	 * Development serial version UID in use - 1L 
	 */
	private static final long serialVersionUID = 1L;
	//The server database may be concerned with these fields
	private long ID ;
	private String fromUsername ;
	private String toUsername ;
	//The receiver of messages will be concerned with these fields
	private int quantity = 0; 
	private Date sent ;
	private Message[] messages;
	
	public MessageBox(Date sent , String from , String to , long ID) throws IllegalArgumentException{
		if (sent==null || from == null || to==null) {
			throw new IllegalArgumentException("Null value detected in Date or user fields");
		}
		this.sent = sent;
		this.fromUsername = from;
		this.toUsername = to;
		this.ID = ID;
		messages = new Message[10];
	}
	
	public MessageBox(Date sent , long ID) throws IllegalArgumentException{
		if (sent==null ) {
			throw new IllegalArgumentException("Date object cannot be null!");
		}
		this.sent = sent;
		this.ID = ID;
		messages = new Message[10];
	}
	
	public MessageBox(Date sent , long ID , String from) throws IllegalArgumentException{
		if (sent==null ) {
			throw new IllegalArgumentException("Date object cannot be null!");
		}
		this.sent = sent;
		this.ID = ID;
		this.fromUsername = from;
		messages = new Message[10];
	}
	
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
		if (messages.length >= quantity+1) {
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
	}
	public Message getItem(int index) {
		/**
		 * Return the message object stored at the given index.
		 * Throws IllegalArgumentException when bad input is given
		 */
		if (index+1>quantity) {
			throw new IllegalArgumentException("No message with index "+index+" exists on this mailbox");
		}
		if (index<0) {
			throw new IllegalArgumentException("Non-positive integer encountered for message index");
		}
		return messages[index];
	}
	public Message changeItem(int index , Message message) {
		/**
		 * Return the message object stored at the given index, whilst changing it to the new value given
		 * Throws IllegalArgumentException when bad input is given
		 */
		if (index+1>quantity) {
			throw new IllegalArgumentException("Error modifying MessageBox index. No message with index "+index+" exists on this mailbox");
		}
		if (index<0) {
			throw new IllegalArgumentException("Error modifying MessageBox index. Non-positive integer encountered for message index");
		}
		Message temp = messages[index];
		messages[index] = message;
		return temp;
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
		return quantity;
	}
	/**
	 * Modify field of recipient
	 * @param to
	 */
	public void setTo(String to) {
		if (to==null) throw new IllegalArgumentException("Recipient username cannot be null");
		this.toUsername = to;
	}
	/**
	 * Change the recipient  
	 * @param from username of recipient
	 */
	public void setfrom(String from) {
		if (from==null) throw new IllegalArgumentException("Sender username cannot be null");
		this.fromUsername = from;
	}
	/**
	 * Change the sender  
	 * @param from username of sender
	 */
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
	}
	
	public boolean hasNext() {
		return messageIndex<quantity ;
	}
	
	public E next() {
		// TODO Auto-generated method stub
		if (hasNext()) { 
			return messages[messageIndex++] ;
		}else {
			throw new UnsupportedOperationException () ;
		}
		
	}
	
	public void remove() {
		throw new UnsupportedOperationException() ;
	}
}
