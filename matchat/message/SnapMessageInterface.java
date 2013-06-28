package matchat.message;

public interface SnapMessageInterface {

	public abstract byte[] getImageData();

	public abstract void setFormat(String format);

	public abstract long ID();

	public abstract void setFrom(String user);

	public abstract void setTo(String user);

	public abstract String getFrom();

	public abstract String getTo();

	public abstract String type();

	public abstract String getCaption();

}