package matchat.test;

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
import matchat.message.Notification;
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
		//correct from address
		MessageBox box = new MessageBox(new Date(), 1) ;
		MessageBox back = sendMessagebox_withWriteback(box) ;
		assertEquals (box.from() , back.from()) ;
	}
	@Test
	public void test_3() {
		//Send a dummy message box, test whether it comes back with 
		//correct from address , and correct quantity
		MessageBox box = new MessageBox(new Date(), 1) ;
		box.addMessage(new SomeMessage(0)) ;
		MessageBox back = sendMessagebox_withWriteback(box) ;
		assertEquals (box.from() , back.from()) ;
		assertEquals(box.quantity() , back.quantity()) ;
	}
	@Test
	public void test_4() {
		//A more vigorous test with 2 different receivers
		Message m1 = new SomeMessage("test1" , "test2") ;
		
		Message m2 = new SomeMessage("test2" , "test1") ;
		
		MessageBox box1 = new MessageBox(new Date(), 1 , "test1") ;
		MessageBox box2 = new MessageBox(new Date(), 2 , "test2") ;
		
		box1.addMessage(m1) ;
		box2.addMessage(m2) ;
		//correspondence is between the number of receiver
		//and expected message number
		
		MessageBox back1 = sendMessagebox_withWriteback(box1) ;
		MessageBox back2 = sendMessagebox_withWriteback(box2) ;
		//back2 = sendMessagebox_withWriteback(box2) ;
		//assertTrue (box1.from() != null) ;
		
		assertEquals (back1.from() , back2.to());
		assertEquals (back2.from() , back1.to()) ;
		
		//assertEquals( 1 , back1.quantity() ) ;
		assertEquals( 1 , back2.quantity() ) ;
		
		//assertEquals( m2 ,  back1.getItem(0)) ; //
		assertEquals( m1 , back2.getItem(0) ) ;
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
	private void sendMessagebox_replyOff (MessageBox m , Notification n) {
		LocalTest_init() ;
		m.addMessage(new Notification(m)) ; //add the required notification
		try {
			Socket s = new Socket("localhost" , front.port()) ;
			OutputStream os = s.getOutputStream() ;
			ObjectOutputStream oos = new ObjectOutputStream(os) ;
			oos.writeObject(m) ;
			//now handle the messagebox sent back
		} catch (UnknownHostException e) {
			e.printStackTrace();
			fail("Test premise failed");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Test premise failed");
		}
	}
	public void finalize () {
		if (front != null) {
			front.deactivate();
		}
	}
}

class DumMessage extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String userfrom ;
	String userto ; 
	public DumMessage() {
		super(0);
		// TODO Auto-generated constructor stub
	}
	public DumMessage(String userfrom , String userto ) {
		super (0) ;
		this.userfrom = userfrom ;
		this.userto = userto ;
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
//		return "test";
		if (userfrom == null) return "test" ;
		else return userto ;
	}

	@Override
	public String fromUserName() {
//		return "test" ;
		if (userfrom == null) return "test" ;
		else return userfrom ;
	}
	
}