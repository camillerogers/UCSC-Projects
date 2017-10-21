//Recursion.java
//Camille Rogers
//carogers
//12B
//April 7, 2016
//Program reverses a given array in three different situations, 
//and prints the max and min values in the array 

class Recursion {
   
   // reverseArray1()
   // Places the leftmost n elements of X[] into the rightmost n positions in
   // Y[] in reverse order
   static void reverseArray1(int[] X, int n, int[] Y){
      if(n > 0) {
         Y[X.length-n]= X[n-1];
         reverseArray1(X, n-1, Y);     
      }   
   }
   

   // reverseArray2()
   // Places the rightmost n elements of X[] into the leftmost n positions in
   // Y[] in reverse order.
   static void reverseArray2(int[] X, int n, int[] Y){
      if(n > 0){
         Y[n-1]= X[Y.length-n];
         reverseArray2(X, n-1, Y);     
      }
   }

   // reverseArray3()
   // Reverses the subarray X[i...j].
   static void reverseArray3(int[] X, int i, int j){
      if(i < j){
      int temp = X[i];
      X[i] = X[j];
      X[j] = temp;
      reverseArray3( X, i+1, j-1);
      }
   }
   
   // maxArrayIndex()
   // returns the index of the largest value in int array X
   static int maxArrayIndex(int[] X, int p, int r){
     int q, x, y;
     if(p < r) {
        q = (p+r)/2; //finds midpoint in the array
        x = maxArrayIndex(X, p, q);
        y = maxArrayIndex(X, q+1, r);
        if (X[x]>X[y]){ //compares two spots in array for larger num  
           return x;
        }
        else
           return y;
        }
     return p;
   }
   
   // minArrayIndex()
   // returns the index of the smallest value in int array X
   static int minArrayIndex(int[] X, int p, int r){
      int q, x, y;
         if(p < r) {
            q = (p+r)/2; //finds midpoint in the array
            x = minArrayIndex(X, p, q);
            y = minArrayIndex(X, q+1, r);
            if (X[x]<X[y]){ //compares two spots in array for smaller num 
           	   return x;
            }
            else
           	   return y;
        }
        return p;
   }
   
   public static void main(String[] args){
      
      int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
      int[] B = new int[A.length];
      int[] C = new int[A.length];
      int minIndex = minArrayIndex(A, 0, A.length-1);
      int maxIndex = maxArrayIndex(A, 0, A.length-1);
      
      for(int x: A) System.out.print(x+" ");
      System.out.println(); 
      
      System.out.println( "minIndex = " + minIndex );  
      System.out.println( "maxIndex = " + maxIndex );  

      reverseArray1(A, A.length, B);
      for(int x: B) System.out.print(x+" ");
      System.out.println();
      
      reverseArray2(A, A.length, C);
      for(int x: C) System.out.print(x+" ");
      System.out.println(); 
      
      reverseArray3(A, 0, A.length-1);
      for(int x: A) System.out.print(x+" ");
      System.out.println(); 
      
   }
   
}
