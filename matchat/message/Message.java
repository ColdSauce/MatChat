package matchat.message;

import java.io.Serializable;

public abstract class Message implements Serializable {
	/**
	 * Development serial UID is used here - which is 1L
	 */
	private static final long serialVersionUID = 1L;
	private long version;
	
	public Message(long version) {
		this.version = version;
	}
	/**
	 * Return the version contained within this message.
	 * @return
	 */
	public long getVersion() {
		return version ;
	}
	public abstract long ID() ;
	public abstract String type() ;
	
	public abstract String toUserName() ;
	
}
