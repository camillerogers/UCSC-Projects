//Queue.java
//Camille Rogers
//carogers
//12B
//simulate a set of jobs performed by a set of processors, where there are more jobs than processors

public class Queue implements QueueInterface{

   private class Node{
      Object item;
      Node next;

      Node(Object item){
         this.item = item;
         next = null;
      }
      Node(Object job, Node N){
         if(item instanceof Job){
            item = job;
         }
         next = N; 
     }         
   }

   private int numItems;      // number of items in this Queue
   private Node front;         // index of front element
   private Node back; 
   
   public Queue(){
      front = null;
      back = null;
      numItems = 0;
      
   }
   
   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // length()
   // pre: none
   // post: returns the length of this Queue.
   public int length(){
      return numItems;
   }

   // enqueue()
   // adds newItem to back of this Queue
   // pre: none
   // post: !isEmpty()
   public void enqueue(Object newItem){
      if(front==null){
      	 front = new Node(newItem);
         numItems++;
      }
      else{
         Node N = front; 
         while(N.next != null){
            N = N.next;  
         }
         N.next = new Node(newItem);
         back = N.next;
         numItems++; 
      }
    }  

   // dequeue()
   // deletes and returns item from front of this Queue
   // pre: !isEmpty()
   // post: this Queue will have one fewer element
   public Object dequeue() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot dequeue() empty queue");
      }
      else {
         Node N = front;
         front = N.next;
         numItems--;
         return N.item;
      }
   }

   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public Object peek() throws QueueEmptyException{
      if(numItems==0){
         throw new QueueEmptyException("cannot peek() empty queue");
      }
      else{
      	return front.item;
      }
   }

   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException{
      if(numItems==0){
         throw new QueueEmptyException("cannot dequeueAll() empty queue");
      }
      numItems = 0;
      front = null;
      back = null;
   }

   // toString()
   // overrides Object's toString() method
   public String toString(){
      String s = "";
      Node N = front;
      while(N!=null){
         s+= N.item; //+ " \n";
         N= N.next;  
      }
      return s;
   }   
}

