package matchat.message;

import java.util.ArrayList;
import java.util.List;

public class Notification extends Message {
	/**
	 * Development UID
	 */
	private static final long serialVersionUID = 1L;
	String to ;
	String from ;
	private long ID;
	List<String> content;
	private String type;
	
	public Notification(long version , long ID , String to , String from) {
		super(version);
		this.ID = ID ;
		this.to = to ;
		this.from = from ;
		this.content = new ArrayList<String>();
		this.type = "DEFAULT" ;
	}
	public Notification (Message m) {
		super(m.ID()) ;
		this.ID = m.ID() ;
		this.to = m.toUserName();
		this.from = m.fromUserName();
		
		this.content = new ArrayList<String>();
		this.type = "DEFAULT" ;
	}
	
	public Notification (MessageBox m) {
		super(m.ID()) ;
		this.ID = m.ID() ;
		this.to = "root" ;
		this.from = m.from();
		
		this.content = new ArrayList<String>();
		this.type = "DEFAULT" ;
	}
	
	@Override
	public long ID() {
		return ID ;
	}

	@Override
	public String type() {
		return type ; 
	}

	@Override
	public String toUserName() {
		return to ; 
	}

	@Override
	public String fromUserName() {
		return from;
	}
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		if (to == null) throw new IllegalArgumentException("`To` address cannot be NULL") ;
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		if (from == null) throw new IllegalArgumentException("`From` address cannot be NULL") ;
		this.from = from;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		if (content == null) throw new IllegalArgumentException ("Cannot set content list to NULL") ;
		this.content = content;
	}
	public void addContent(String cont) {
		if (cont == null) throw new IllegalArgumentException ("Cannot add NULL to content list") ;
		content.add(cont) ;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type == null) throw new IllegalArgumentException("`type` cannot be NULL") ;
		this.type = type;
	}

}
