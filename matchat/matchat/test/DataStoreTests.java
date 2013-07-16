package matchat.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import matchat.message.Message;
import matchat.message.MessageBox;
import matchat.server.store.DataStore;

import org.junit.Test;

public class DataStoreTests {
	DataStore ds ;
	private String userfrom ;
	private String userto ;
	public DataStoreTests() {
		//ds = getSomeNewDataStore()
	}
	@Test
	public void test_addGetSingleMessage() {
		Message m = new SomeMessage(userfrom , userto) ;
		long ID = 100 ;
		//MessageBox b = new MessageBox(new Date(), ID, "test1") ;
		ds.addMessage(m);
		Message mb = ds.getMessageById(ID);
		assertEquals( m , mb ) ;
	}
	@Test
	public void test_addGetMultipleMessages() {
		List<Message> mlist = new ArrayList<Message>() ;
		for (long l = 100 ; l < 200 ; l++) {
			Message them = new SomeMessage(userfrom , userto , l) ;
			ds.addMessage(them) ;
			
			mlist.add (
					them
					);
		}
		int index = 0;
		for (long l = 100 ; l < 200 ; l++) {
			assertEquals (mlist.get(index) , ds.getMessageById(l)) ;
		}
	}
	@Test
	public void test_addGetUnsentMessages() {
		List<Message> expectedlist = new ArrayList<Message>() ;
		for (long l = 100 ; l < 200 ; l++) {
			Message message = new SomeMessage(userfrom , userto) ;
			ds.addMessage(message);
			expectedlist.add(message);
		}
		List<Message> actual = ds.awaitingMessagesForUser(userto) ;
		for (Message m : actual) {
			if (expectedlist.contains(m)) {
				//good
			}else {
				fail("Expected message `"+"` but it was not in expected message array") ;
			}
		}
	}	
}
