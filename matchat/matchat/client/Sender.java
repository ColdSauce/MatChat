package matchat.client;

import matchat.message.MessageBox;

public interface Sender {
	public void setMessageBox(MessageBox box);
	public void send();
	public void changeHost(String host);
	public void changePort(int port);
	public MessageBox getReceipt();
}
