//List.java
//Camille Rogers
//carogers
//Pa1
//List ADT: uses a cursor as a pointer and for iteration, two end nodes back and front 
//a set of “mathematical structures” consists of all finite sequences of integers
//in which at most one element is underscored

public class List{

	private class Node{
      // Fields
      int data;
      Node next;
      Node previous;
      
      // Constructor
      Node(int data) { this.data = data; this.next = this.previous = null; }
      
      // toString():  overrides Object's toString() method
      public String toString() { 
         return String.valueOf(data); 
      }
      
      // equals(): overrides Object's equals() method
      public boolean equals(Object x){
         boolean eq = false;
         Node that;
         if(x instanceof Node){
            that = (Node) x;
            eq = (this.data==that.data);
         }
         return eq;
      }
   }

   // Fields
   private Node front;
   private Node back;
   private Node cursor;
   private int length;
   private int index;
   
	//Constructor 
	List(){
		front = back = cursor = null;
		length = 0;
		index = 0;
	}
////////////////////////////////////////////////////////////////////////////////////
	
	//ACCESS FUNCTIONS
	
	//returns true if List is empty, false if otherwise
	boolean isEmpty(){
		if(length == 0){
			return true;
		}
		else{
			return false;
			}
	}

	//returns the number of elements in List
	int length(){
		return length;
	}
	
	//If cursor is defined, returns the index of the cursor element, otherwise returns -1
	int index(){
		if(cursor != null){
			index = cursor.data;	//set index to cursor element
			return index;
		}
		else{
			return -1;
		}
	}
	
	//returns front element. Pre: length()>0
	int front(){
		if(this.isEmpty()){
			throw new RuntimeException("List Error: front() called on empty List");
			//throw error if List is empty
		}
		else{
			return front.data;
		}
	}
	
	//returns back element. Pre: length()>0
	int back(){
		if(this.isEmpty()){
			throw new RuntimeException("List Error: back() called on empty List");
			//throw error if List is empty
		}
		else{
			return back.data;
		}
	}
	
	//returns cursor element. Pre: length()>0, index()>=0
	int get(){
		if(this.isEmpty() && this.index()<0){
			throw new RuntimeException("List Error: get() called on empty List & undefined index");
			//throw error if List is empty and index is undefined
		}
		else{
			return cursor.data;
		}
	}
	
	//returns true if and only if this List and L are the same integer sequence.
	//The states of the cursors in the two Lists are not used in determining equality
	//front() & back()
	boolean equals(List L){
		boolean eq = false;
      	List S;
      	Node N, M;

      	if(L instanceof List){
        	S = (List)L;
         	eq = (this.length==S.length);	//if List & L have same length then remains true
         	N = this.front;
         	M = S.front;
         	while( eq && N!=null ){		//while lengths are the same and front is not null
            	eq = N.equals(M);	//if List front = L front remains true
            	N = N.next;
            	M = M.next;
         	}
      	}
      	return eq;
   	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//MANIPULATION PROCEDURES
	
	//resets this list to its original empty state.
	void clear(){
		front= null;	//empty Front, null, cursor, & index
		back = null;
		cursor = null;
		length = 0;
		index = 0;
		
	}
	
	//if List is non-empty, places the cursor under the front element, otherwise does nothing
	void moveFront(){
		if(this.isEmpty()==true){	//if List is empty return nothing
			return;
		}
		else{						//else cursor = front element
			cursor = front;
		}
		
	}	
	
	//if List is non-empty, places the cursor under the back element, otherwise does nothing
	void moveBack(){
		if(this.isEmpty()==true){	//if List is empty return nothing
			return;
		}
		else{						//else cursor = back element
			cursor = back;
		}
	}
	
	//if cursor is defined and not at front, moves cursor one step toward front of this 
	//List, if cursor is defined and at front, cursor becomes undefined, if cursor is 
	//undefined does nothing. 
	void movePrev(){
		if(cursor!=null){	//if cursor does not equal null, then move cursor toward front
			cursor = cursor.previous;
		}
		if(cursor==null ){	//if cursor is undefined return nothing
			return;
		}
	}
	
	//if cursor is defined and not at back, moves cursor one step toward back of 
	//this List, if cursor is defined and at back, cursor becomes undefined, if
	//cursor is undefined does nothing
	void moveNext(){
		if(cursor!=null){	//if cursor doess not equal null, then more cursor toward back
			cursor = cursor.next;
			index++;
		}
		if(cursor==null){	//if cursor is undefined return nothing
			return;
		}
	}
	
	//insert new element into this List. If List is non-empty, insertion takes place
	//before front element
	void prepend(int data){	
		Node N = new Node(data);	//create new Node
		if(this.isEmpty()==true){	//if List is empty, front & back = new node N
			front = N;				//increment length & index
			back = N;
			length++;
			index++;
		}
		else{						//else add new Node N before front element 
			front.previous = N;		
			N.next = front;
			front = N;
			index++;				//increment index & length
			length++;
		}
	}
	
	//insert new element into this List. If List is non-empty, insertion
	//takes place after back element. 
	void append(int data){
		Node N = new Node(data);	//create new Node
		if(this.isEmpty()==true){	//if List is empty, set front & back = new Node N 
			front = N;
			back = N;
			back.next = null;		//set back.next to null to set new boundary
			length++;				//increment length & index
			index++;
		}
		else{
			back.next = N;			//else insert N after back element
			N.previous = back;
			back = back.next;
			length++;				//increment index & length
			index++;
		}
	}
	
	//insert new element before cursor. Pre: length()>0, index()>=0
	void insertBefore(int data){
		//Node C = cursor;	//create new node = cursor
		Node N = new Node(data);	//create new node = new element
		if(this.isEmpty() && this.index()<0){	//if List is empty and index is undefined throw error
			throw new RuntimeException("List Error: insertBefore() called empty List");
		}
		else{
			if( cursor == null){	//if cursor is undefined, insert new element using append() 
				prepend(data);
			}
			else{
				N.previous = cursor.previous;		//new element previous = cursor previous
				N.next = cursor;					//new element next is cursor
				cursor.previous.next = N;			//cursor previous & next is now new element 
				cursor.previous = N;				//cursor previous is new element
				length++;							//increment length & index
				index++;
			}
		}
	}
	
	//Inserts new element after cursor, Pre: length()>0, index()>=0
	void insertAfter(int data){
		Node N = new Node(data);			//create new node = new element
		if(this.isEmpty() && this.index()<0){	//if List is empty and index is undefined throw error
			throw new RuntimeException("List Error: insertAfter() called empty List");
		}
		else{
			if( index == length - 1){	//if index of cursor is back, then append() data 
					append(data);
				}
				else{
					N.next = cursor.next;			//new element next is cursor next	
					N.previous = cursor;			//new element previous is cursor
					cursor.next.previous = N;		//cursor next & previous is new element
					cursor.next = N;				//cursor next is new element 
					length++;						//increment length & index
					index++;
				}
			}
	}
	
	//Deletes the front element, Pre: length()>0
	void deleteFront(){
		if(this.isEmpty()){		//if List is empty throw error
			throw new RuntimeException("List Error: deleteFront() called on empty List");
		}
		else{					//else delete front 
			front = null;
			length--;			//decrement length & index
			index--;
		}
	}
	
	//Deletes the back element. Pre length()>0
	void deleteBack(){
		if(this.isEmpty()){		//if List is empty throw error
			throw new RuntimeException("List Error: deleteBack() called on empty List");
		}	
		else{					//else make cursor back, delete back
			cursor = back; 
			back = back.previous;
			length--;
			index--;
		}
	}
	
	//Deletes cursor element, making cursor undefined. Pre: length()>0, index()>=0 
	void delete(){
		if(this.isEmpty() ){	//if List is empty throw error
			throw new RuntimeException("List Error: delete() called on Empty List");
		}	
		else{
			cursor = null;		//cursor is null 
			index--;			//decrement index & length
			length--;
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////

	//OTHER METHODS
		
	//Overrides Object's toString method. Returns a String representation of this List 
	//consisting of a space separated sequence of integers, with front on left. 
	public String toString(){
		StringBuffer sb = new StringBuffer();
		Node N = front;
		while( N!=null){
			sb.append(" ");
			sb.append(N.toString());
			N = N.next;
		}
		return new String(sb);
	}
	
	//Returns a new List representing the same integer sequence as this List. 
	//The cursor in the new list is undefined, regardless of the
	// state of the cursor in this List. This List is unchanged.
	List copy(){
		List L = new List();
		Node N = this.front;
		while(N != null){
			L.append(N.data);
			N = N.next;
		}
		return L;
	}
	
}
