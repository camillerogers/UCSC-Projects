//KeyNotFoundException.java
//Camille Rogers
//carogers
//pa3, 12B
//defines a type of exception class to be thrown.

public class KeyNotFoundException extends RuntimeException{
   public KeyNotFoundException(String s){
      super(s);
   }
}