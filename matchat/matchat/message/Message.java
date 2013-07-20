package matchat.message;

import java.io.Serializable;

public abstract class Message implements Serializable {
	/**
	 * Development serial UID is used here - which is 1L
	 */
	private static final long serialVersionUID = 1L;
	
	private final long senderID;
	private long recipientID;
	
	public Message(long senderID, long recipientID) {
		this.senderID = senderID;
		this.recipientID = recipientID;
	}

	public long getSenderID() {
		return this.senderID;
	}
	
	public long getRecipientID() {
		return this.recipientID;
	}
	
	public abstract String getType();
}
