package matchat.message;

public class Notifier {
	/**
	 * Helper class that can add notification messages to a notification object/
	 */
	private static final String replyOff = "REPLY_OFF" ;
	public static void replyOff (Notification n) {
		n.addContent(replyOff) ;
	}
	
}
