package matchat.message;

public interface Snap {

	public abstract byte[] getImageData();

	public abstract void setFormat(String format);

	public abstract String type();

	public abstract String getCaption();

}