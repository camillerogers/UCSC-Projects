//QueueTest.java
//Camille Rogers
//carogers
//12B
//Test Client for the Queue class

public class QueueTest {

   public static void main(String[] args){
      Queue A = new Queue();
      A.enqueue("cat"); 
      A.enqueue(" dog"); 
      A.enqueue(" coffee"); 
      A.enqueue(" trees"); 
      A.enqueue(" sugar");
      System.out.println("Queue: " + A + " ");
      System.out.println();
      System.out.println("Peek: " + A.peek());
      System.out.println("Length: " + A.length());
      
      System.out.println();
      A.dequeue(); 
      A.dequeue(); 
      A.dequeue();
      System.out.println("Peek: " + A.peek());
      System.out.println("Queue: " + A);
      
      System.out.println("Empty: " + A.isEmpty());

      System.out.println("Length: " + A.length());
      
      A.enqueue(" birds ");
      System.out.println("Queue: " + A);
      
      A.dequeueAll();
      System.out.println("Queue: " + A);
      System.out.println("Empty: " + A.isEmpty());
      
      A.enqueue(new Job(1,1));
      A.enqueue(new Job(5,5));
      A.enqueue(new Job(4,4));
      A.enqueue(new Job(6,6));
      System.out.println("toString: " + A.toString());
   }
}