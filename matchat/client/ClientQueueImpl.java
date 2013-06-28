package matchat.client;

import java.util.NoSuchElementException;

import matchat.message.Message;

public class ClientQueueImpl implements ClientQueue {
	Node<Message> root ;
	/**
	 *   |BACK| <------previous-------|NODE| ------next------> |FRONT|
	 */
	private Node<Message> front ;
	private Node<Message> back ;
	
	private int size = 0;
	
	public ClientQueueImpl() {
		
	}
	
	@Override
	public void enqueueMessage(Message m) {
		/*
		 * Message size is always incremented 
		 */
		// TODO Auto-generated method stub
		if (m == null) throw new IllegalArgumentException("Argument cannot be null!") ;
		size ++ ; 
		if (first(m)) return ;
		
		Node<Message> newBack = new Node<Message> (m , back) ;
		//set the new back of queue to a node with the enqueing message, with the former back as next element
		back.setPrevious(newBack);
		//previous element of old back is the newly generated back
		back = newBack ;
		
	}

	@Override
	public boolean removeMessage(Message m) {
		
		
		Node<Message> node = front ;
		boolean hasNext = true ;
		
		while (hasNext) {
			if (node.value().equals(m)) {
				//node is garanteed not to be null
				//awful remove message code
				Node<Message> lookingForward = node.next();
				Node<Message> lookingBackward = node.previous();
				
				
				if (lookingForward != null) lookingForward.setNext(lookingBackward);
				if (lookingBackward != null) lookingBackward.setNext(lookingForward);
				
				if (front == node) {
					//if the front node was identified , then the new front is node behind the front
					front = node.previous();
				}
				if (back == node) {
					//if the back node was identified , then the new back is node in front of the back
					back = node.next() ;
				}
				size -- ;
				return true ;
			}
			node = node.previous();
			//must only look at previous node
			hasNext = (node!=null) ;
		}
		return false ;
		//loop did not find something, so method returns false
	}

	@Override
	public Message removeMessage(long ID) throws NoSuchElementException{
		Node<Message> node = front ;
		boolean hasNext = true ;
		
		while (hasNext) {
			if (node.value().ID() == ID) {
				//node is garanteed not to be null
				//awful remove message code
				Node<Message> lookingForward = node.next();
				Node<Message> lookingBackward = node.previous();
				
				
				if (lookingForward != null) lookingForward.setNext(lookingBackward);
				if (lookingBackward != null) lookingBackward.setNext(lookingForward);
				
				if (front == node) {
					//if the front node was identified , then the new front is node behind the front
					front = node.previous();
				}
				if (back == node) {
					//if the back node was identified , then the new back is node in front of the back
					back = node.next() ;
				}
				
				//return true ;
				size -- ;
				return node.value() ;
			}
			node = node.previous();
			//must only look at previous node
			hasNext = (node!=null) ;
		}
		//return false ;
		return null ;
		//loop did not find something, so method returns false
	}

	@Override
	public Message dequeueMessage() throws EmptyQueueException {
		
		if (front == null) throw new EmptyQueueException() ;
		
		Node<Message> out = front ;
		front = front.previous();
		size -- ;
		return out.value(); 
	}
	private boolean first(Message m) {
		if (root == null) {
			root = new Node<Message> (m , null) ; 
			front = root ;
			back = root ;
			return true ;
		}else {
			return false ;
		}
	}
	private class Node<E extends Message> {
		/**
		 * Simple private class with basic doubly-link structure 
		 */
		private E data ;
		private Node<E> next ;
		
		private Node<E> previous ;
		private int position ;
		
		public Node(E message , Node<E> next) {
			/**
			 * `next` refers to the forward-looking element of queue. 
			 */
			this.next = next ;
			this.data = message ;
		}
		public Node(E message , Node<E> next , Node<E> previous) {
			this.next = next ;
			this.data = message ;
		}
		public E value () {
			return data ;
		}
		public Node<E> next() {
			return next ;
		}
		public Node<E> previous () {
			return previous ;
		}
		public void setValue(E v) {
			this.data = v ;
		}
		public void setNext(Node<E> next ) {
			this.next = next ;
		}
		public void setPrevious(Node<E> previous ) {
			this.previous = previous ;
		}
	}
	@Override
	public int size() {
		return size ;
	}
	@Override 
	public boolean isEmpty() {
		return size == 0 ;
	}
}
