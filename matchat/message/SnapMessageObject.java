package matchat.message;


public class SnapMessageObject extends Message implements SnapMessageInterface {
	
	/**
	 * This class is a very simple class that serves merely as a container.
	 * A byte 
	 */
	/**
	 * Just like the parent class, Message, the development serial version ID is
	 * the default given.  
	 */
	private static final long serialVersionUID = 1L;
	byte [] imageData ;
	String caption ;
	int offset ;
	String format ;
	private String type = "SNAP" ;
	private long ID ;
	private String fromUsername ;
	private String toUsername ;
	/**
	 * For a working snap, the version of the software using the snap is supplied, along with the array of image data, a
	 * caption 
	 * @param version
	 * @param imageData
	 * @param caption
	 * @param captionOffset
	 * @param format
	 */
	public SnapMessageObject(long version , byte [] imageData , String caption , int captionOffset, String format) {
		super(version);
		if (imageData==null || caption == null || format == null ) {
			//This could be the most complicated expression in the code. But really
			//it is just a few simple expressions that will make debugging easier! 
			throw new IllegalArgumentException(
					"The following arguments were null: " + ((imageData == null)? " imageData ":"") +  ((caption == null)? " caption ":"") + ((format == null)? " format ":"")   
					) ;
		}
		this.offset = captionOffset ;
		this.format = format ;
		this.caption = caption ;
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#getImageData()
	 */
	@Override
	public byte[] getImageData () {
		return imageData ;
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#setFormat(java.lang.String)
	 */
	@Override
	public void setFormat(String format) {
		if (format != null) this.format = format ;
		else throw new IllegalArgumentException("setFormat called with null string.") ;
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#ID()
	 */
	@Override
	public long ID() {
		// TODO Auto-generated method stub
		return ID ;
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#setFrom(java.lang.String)
	 */
	@Override
	public void setFrom(String user) {
		assert user != null ;
		this.fromUsername = user ;
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#setTo(java.lang.String)
	 */
	@Override
	public void setTo(String user) {
		assert user != null ;
		toUsername = user ;
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#getFrom()
	 */
	@Override
	public String getFrom() {
		return fromUsername ;
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#getTo()
	 */
	@Override
	public String getTo() {
		return toUsername ;
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#type()
	 */
	@Override
	public String type() {
		return type ; 
	}
	/* (non-Javadoc)
	 * @see matchat.message.SnapMessageInterface#getCaption()
	 */
	@Override
	public String getCaption() {
		return caption ;
	}
	@Override
	public String toUserName() {
		return toUserName() ;
	}
}
