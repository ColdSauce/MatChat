package matchat.clientservertests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import matchat.message.Message;
import matchat.message.MessageBox;
import matchat.server.SimpleServerFront;

import org.junit.Test;

public class LocalTest {
	static SimpleServerFront front;
	private static int count  = 0;
	public  void LocalTest_init () {
		try {
			if (front != null) return ;
			front = new SimpleServerFront() ;
			front.activate();
			count ++;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Server running on port "+front.port());
		System.out.println(count + "instances of tester");
	}
	
	@Test
	public void test_1() {
		//Send a dummy message box, test whether it works without failure
		MessageBox box = new MessageBox(new Date(), 1) ;
		System.out.println("Sending box") ;
		sendMessagebox(box) ;
	}
	@Test
	public void test_2() {
		//Send a dummy message box, test whether it comes back with 
		//
		MessageBox box = new MessageBox(new Date(), 1) ;
		MessageBox back = sendMessagebox_withWriteback(box) ;
		assertEquals (box.from() , back.from()) ;
	}
	
	private void sendMessagebox (MessageBox m) {
		LocalTest_init() ;
		try {
			Socket s = new Socket("localhost" , front.port()) ;
			OutputStream os = s.getOutputStream() ;
			ObjectOutputStream ois = new ObjectOutputStream(os) ;
			ois.writeObject(m) ;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			fail("Test premise failed");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Test premise failed");
		}
		//front.deactivate();
	}
	private MessageBox sendMessagebox_withWriteback (MessageBox m) {
		LocalTest_init() ;
		try {
			Socket s = new Socket("localhost" , front.port()) ;
			OutputStream os = s.getOutputStream() ;
			ObjectOutputStream oos = new ObjectOutputStream(os) ;
			oos.writeObject(m) ;
			//now handle the messagebox sent back
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Object o = ois.readObject();
			if (o instanceof MessageBox) {
				return (MessageBox) o ;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			fail("Test premise failed");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Test premise failed");
		}
		//front.deactivate();
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	public void finalize () {
		if (front != null) {
			front.deactivate();
		}
	}
}

class DumMessage extends Message {

	public DumMessage() {
		super(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long ID() {
		return 0;
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "DUMMY";
	}

	@Override
	public String toUserName() {
		return "test"; 
	}

	@Override
	public String fromUserName() {
		return "test" ;
	}
	
}
