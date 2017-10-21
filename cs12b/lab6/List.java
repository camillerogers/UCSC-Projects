//ListInterface.java
//Camille Rogers 
//carogers
//12M, lab6
//List ADT, uses Generics 

@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T> {
   
   // private inner Node class
   private class Node<T> {
      T item;
      Node<T> next;

      Node(T x){
         item = x;
         next = null;
      }
   }

   // Fields for the List class
   private Node<T> head;   // reference to first Node in List
   private int numItems;  // number of items in this List

   // List()
   // constructor for the List class
   public List(){
      head = null;
      numItems = 0;
   }

   // find()
   // returns a reference to the Node at position index in this List
   private Node<T> find(int index){
      Node<T> N = head;
      for(int i=1; i<index; i++){
         N = N.next;
      }
      return N;
   }

   // isEmpty
   // pre: none
   // post: returns true if this List is empty, false otherwise
   public boolean isEmpty(){
      return( numItems == 0);
   }
   
   // size
   // pre: none
   // post: returns the number of elements in this List
   public int size(){
      return numItems;
   }
   
   // get
   // pre: 1 <= index <= size()
   // post: returns item at position index
   public T get(int index) throws ListIndexOutOfBoundsException{
      if( index<1 ||  index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "List Error: get(): invalid index: " + index);
      }
      Node<T> N = find(index);
      return N.item;
   }

   // add
   // inserts newItem in this List at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void add(int index, T newItem) throws ListIndexOutOfBoundsException{
      if( index<1 || index>(numItems+1) ){
         throw new ListIndexOutOfBoundsException(
            "List Error: add(): invalid index: " + index);
      }
      if(index==1){
         Node<T> N = new Node<T>(newItem);
         N.next = head;
         head = N;
      }else{
         Node<T> P = find(index-1); // at this point index >= 2
         Node<T> C = P.next;
         P.next = new Node<T>(newItem);
         P = P.next;
         P.next = C;
      }
      numItems++;
   }

   // remove
   // deletes item from position index
   // pre: 1 <= index <= size()
   // post: items to the right of deleted item are renumbered
   public void remove(int index) throws ListIndexOutOfBoundsException{
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "List Error: remove(): invalid index: " + index);
      }
      if(index==1){
         Node<T> N = head;
         head = head.next;
         N.next = null;
      }else{
         Node<T> P = find(index-1);
         Node<T> N = P.next;
         P.next = N.next;
         N.next = null;
      }
      numItems--;
   }

   // removeAll
   // pre: none
   // post: isEmpty()
   public void removeAll(){
      head = null;
      numItems = 0;
   }
   
   
   //toString() that uses recursion.
   private String myString(Node<T> H){
      if( H==null ){
         return "";
      }else{
         return (H.item + " " + myString(H.next));
      }
   }
   
   public String toString(){
      return myString(head);
   }
   
   // equals()
   // pre: none
   // post: returns true if this IntegerList matches rhs, false otherwise
   // Overrides Object's equals() method
   @SuppressWarnings("unchecked")
   public boolean equals(Object rhs){
      boolean eq = false;
      List<T> R = null;
      Node<T> N = null;
      Node<T> M = null;

      if(rhs instanceof List){
         R = (List<T>) rhs;
         eq = ( this.numItems == R.numItems );
         N = this.head;
         M = R.head;
         while(eq && N!=null){
            eq = (N.item == M.item);
            N = N.next;
            M = M.next;
         }
      }
      return eq;
   }
}