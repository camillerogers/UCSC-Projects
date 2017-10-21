//DictionaryTest.java
//Camille Rogers
//carogers
//pa3, 12B
//test client for the Dictionary ADT while it is under construction. 

public class DictionaryTest{

   public static void main(String[] args){
      Dictionary A = new Dictionary();
      
      /*A.insert("1","cat"); //This tests my insert() method in Dictionary.java 
      A.insert("2","dog");
      A.insert("3","camille");
      A.insert("4","unicorn");
      A.insert("5","elephant");
      A.insert("6","frog");
      A.insert("7","goldfish");
      System.out.println(A.toString());*/
      
      /*String v;
      v = A.lookup("1");
      System.out.println("key=1 "+(v==null?"not found":("value="+v))); //This tests my lookup() method in Dictionary.java 
      v = A.lookup("4");
      System.out.println("key=4 "+(v==null?"not found":("value="+v)));
      v = A.lookup("7");
      System.out.println("key=7 "+(v==null?"not found":("value="+v)));
      v = A.lookup("6");
      System.out.println("key=6 "+(v==null?"not found":("value="+v)));
      System.out.println();*/

     /*A.delete("1"); //test my delete() in Dictionary.java 
      A.delete("3");
      A.delete("7");
      System.out.println(A.toString());*/
      
      System.out.println(A.isEmpty()); //checks if isEmpty() returns correct boolean and numItems
      System.out.println(A.size());
      A.makeEmpty();
      System.out.println(A.isEmpty());
      System.out.println(A);
   
   }
}