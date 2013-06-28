package matchat.client.tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import matchat.client.ClientQueueImpl;
import matchat.client.ClientQueue;
import matchat.client.EmptyQueueException;

import matchat.message.Message;

import org.junit.Test;

public class QueueTests extends ClientQueueImpl{

	@Test
	public void testAddSize_1() {
		ClientQueue clq = new ClientQueueImpl() ;
		int testSize = 20 ;
		int permTestSize = testSize ;
		while (testSize > 0) {
			System.out.println("About to add "+testSize+" with current size "+clq.size()) ;
			clq.enqueueMessage(new SomeMessage(testSize)) ; 
			testSize -- ;
		}
		assertEquals (clq.size() , permTestSize) ;
	}
	
	@Test
	public void testZeroSize_1()  {
		ClientQueue clq = new ClientQueueImpl() ;
		int testSize = 20 ;
		int testSize2 = testSize ;
		while (testSize > 0) {
			System.out.println("About to add "+testSize+" with current size "+clq.size()) ;
			clq.enqueueMessage(new SomeMessage(testSize)) ; 
			testSize -- ;
		}
		while (testSize <= testSize2 ) {
			System.out.println("About to remove "+testSize+" with current size "+clq.size()) ;
			try { 
				clq.removeMessage(testSize) ; 
			}catch (NoSuchElementException e) {
				fail("Message "+testSize+" was not removed when it should have been present.");
			}
			testSize ++ ;
		} 
		assertEquals(0 , clq.size()); 
	}
	@Test
	public void testZeroSize_2 () {
		Message[] messages = new Message[20] ;
		ClientQueue clq = new ClientQueueImpl () ;
		int testlength = 20; 
		for (int i = 0 ; i < testlength ; i++) {
			Message m = new SomeMessage(i) ;
			messages[i] = m ;
			clq.enqueueMessage(m);
		}
		
		for (Message m : messages) {
			if (!clq.removeMessage(m)) fail("Message `"+"` should have been\nremovable" +
					"but was not");
		}
		assertEquals( 0 , clq.size()) ;
	}
	@Test
	public void testElementOrder_1 () {
		Message[] messages = new Message[20] ;
		ClientQueue clq = new ClientQueueImpl () ;
		int testlength = 20; 
		for (int i = 0 ; i < testlength ; i++) {
			Message m = new SomeMessage(i) ;
			messages[i] = m ;
			clq.enqueueMessage(m);
		}
		int index = 0 ;
		while (!clq.isEmpty()) {
			try {
				assertEquals (
						messages[index] ,
						clq.dequeueMessage()
						) ;
			} catch (EmptyQueueException e) {
				fail("nonempty ClientQueue expected") ;
			}
			index++ ;
		}
	}
	@Test
	public void testNullElement_1 () {
		ClientQueue clq = new ClientQueueImpl() ;
		int testSize = 20 ;
		int permTestSize = testSize ;
		boolean failed = true ;
		try {
			clq.enqueueMessage(null) ;
		} catch (IllegalArgumentException e) {
			failed = false ;
		} finally {
			if (failed) fail("Illegal null argument not caught") ;
		}
		
	}
	//@Test
	//public void test
}
class SomeMessage extends Message {
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
		return "SOME Message with ID : " + Long.toString(ID) ;
	}
	
}
