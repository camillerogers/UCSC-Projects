//Dictionary.java
//Camille Rogers
//carogers
//pa3, 12B
//implement a Dictionary ADT based on the linked list data structure. 

public class Dictionary implements DictionaryInterface{

   //private inner Node class
   private class Node {
      String key;
      String value;
      Node next;

      Node(String x, String y){
         key = x;
         value = y;
         next = null;
      }
   }

   // Fields for the Dictionary class
   private Node head;     // reference to first Node in List
   private int numItems;  // number of items in Dictionary 

   // Dictionary()
   // constructor for the Dictionary class
   public Dictionary(){
      head = null;
      numItems = 0;
   }

   // find()
   // returns a reference to the Node at position index in this IntegerList
   private Node findKey(String key){
      Node N = head;
      while(N!=null){
         if(N.key.equals(key)){
            return N;
         }
         N = N.next;
      }
      return null;
   }    
   
   // isEmpty()
   // pre: none
   // returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
      return(numItems==0);
   }

   // size()
   // pre: none
   // returns the number of entries in this Dictionary
   public int size(){
      return numItems;
   }

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key){
      Node N = findKey(key);
      if(N!=null){
         return N.value;
      }else{
         return null; 
      }
   }

   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException{
      if(findKey(key)!=null){
         throw new DuplicateKeyException("cannot insert duplicate keys");//exception if findkey(key) does not equal null
      }
      else if(findKey(key)==null){ // if it does equal null it will add items to Node
         Node P = new Node(key,value);
         P.next = head;
         head = P; 
         numItems++; 
      }
   }

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException{
      Node N = head;
      //Node P = N;
      if(findKey(key)==null){
         throw new KeyNotFoundException("cannot delete non-existent key"); // exception if findkey(key) is null
      } 
      
      else{
         if(N==findKey(key)){ // if it does not equal null, it searches for key in Node to delete or null items.
            head =head.next;
            N.next = null;
          }else{
             while(N!=null && N.next!=null){
                if(N.next == findKey(key)){
                   Node P = N.next;
                   if(P.next==null){
                      N.next=null;
                   }else{
                   N.next = P.next;
                   P.next = null;
                   }
                }
                N = N.next;
             }
          }
          numItems--;
    }
  }
   
   // makeEmpty()
   // pre: none
   public void makeEmpty(){
      head = null;
      numItems = 0;
   }

   private String myString(Node H){ // returns print to DictionaryClient
      if( H==null ){
         return "";
      }else{
         return (myString(H.next) + " " + H.key + " " + H.value + "\n"); // recursively prints Node
      }
   }
   
   public String toString(){
      return myString(head);
   }
}