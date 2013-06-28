package matchat.tests;

import matchat.message.Message;

public class OneLiner extends Message {
	/**
	 * Development serial UID is used here - which is 1L 
	 */
	private static final long serialVersionUID = 1L;
	String line;
	public OneLiner(String line , long version) {
		super(version);
		this.line = line;
	}
	@Override
	public long ID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String type() {
		// TODO Auto-generated method stub
		return null;
	}
}
