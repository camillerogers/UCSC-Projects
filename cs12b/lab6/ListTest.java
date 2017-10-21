//ListTest.java
//Camille Rogers
//carogers
//12B, lab6
//Test the List ADT

public class ListTest{

   public static void main(String[] args){
      List<String> test = new List<String>();
      int i, j, k;
      
      test.add(1, "cat"); //test add() 
      test.add(2, "dog");
      test.add(3, "fish");
   
      System.out.println("test: "+test);
      System.out.println("test.equals(test) is "+test.equals(test)); //test equals()
      System.out.println("test.size() is "+test.size()); //test size()
      
      test.remove(1); //test remove()
      
      System.out.println("test.size() is "+test.size()); // prints size
      
      try{
         System.out.println(test.get(400));
      }catch(ListIndexOutOfBoundsException e){ //tests the ListIndexOutOfBoundsException 
         System.out.println("Caught Exception: ");
         System.out.println(e);
         System.out.println("Continuing without interuption");
      }
      System.out.println();
      System.out.println("test.get(2) is "+test.get(2)); //tests get()
   
   }
}