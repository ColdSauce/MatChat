package matchat.client;

public class EmptyQueueException extends Exception {
	public EmptyQueueException() {
		super() ;
	}
	public EmptyQueueException (String m) {
		super(m) ;
	}
}
