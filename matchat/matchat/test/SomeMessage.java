package matchat.test;

import matchat.message.Message;

class SomeMessage extends Message {
	String userfrom ;
	String userto ; 
	
	public SomeMessage(String userfrom , String userto ) {
		super (0) ;
		this.userfrom = userfrom ;
		this.userto = userto ;
	}
	public SomeMessage(String userfrom , String userto, long id ) {
		super (0) ;
		this.userfrom = userfrom ;
		this.userto = userto ;
		this.ID = id;
	}
	public SomeMessage() {
		super ( 0 ) ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SomeMessage other = (SomeMessage) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long ID ;
	public SomeMessage(int ID) {
		super(0L);
		this.ID = (int) ID ;
	}

	@Override
	public long ID() {
		return ID ;
	}

	@Override
	public String type() {
		return "SOME" ;
	}
	@Override 
	public String toString() {
		String append = "" + ((userfrom==null)? "":userfrom ) + ((userto==null)? "":userto ) ;  
		return "SOME Message with ID : " + Long.toString(ID) + append ;
	}

	@Override
	public String toUserName() {
		return userto ;
	}

	@Override
	public String fromUserName() {
		return userfrom ;
	}
	
}