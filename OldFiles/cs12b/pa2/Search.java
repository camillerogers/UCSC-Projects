//Search.java
//Camille Rogers
//carogers, 12B
//17 April, 2016
//implement the Binary Search and Merge Sort algorithms to operate on 
//String Arrays rather than int arrays. 

import java.io.*;
import java.util.Scanner;

public class Search {
   
   // binarySearch()
   public static int binarySearch(String[] A, int[] lineNumber, int p, int r,  String target){
      int q;
      if(p > r) {
         return -1;
      }else{
         q = (p+r)/2;
         if(target.compareTo(A[q])==0){ //checks to see if target is found
            return q;
         }else if(target.compareTo(A[q])<0){ //if not found recursive back
            return binarySearch(A, lineNumber, p, q-1, target);
         }else{ //if not found recursive back
            return binarySearch(A, lineNumber, q+1, r, target);
         }
      }
   }    

   // mergeSort()
   static void mergeSort(String[] word, int[] lineNumber, int p, int r){//int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2; //sorts array, finds midpoint
         mergeSort(word,lineNumber, p, q);
         mergeSort(word,lineNumber, q+1, r);
         merge(word,lineNumber, p, q, r);
      }
   }
   
   static void merge(String[] word, int[] lineNumber, int p, int q, int r){
      int s1 = q-p+1;
      int s2 = r-q;
      String[] L = new String[s1];
      String[] R = new String[s2];
      int[] left = new int[s1]; //creates new int arrays for lineNumber
      int[] right = new int[s2];
      int i, j, k;

      for(i=0; i<s1; i++) L[i] = word[p+i];
      for(j=0; j<s2; j++) R[j] = word[q+j+1];
      for(i=0; i<s1; i++) left[i] = lineNumber[p+i];
      for(j=0; j<s2; j++) right[j] = lineNumber[q+j+1];
      
      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<s1 && j<s2 ){ //merges sorted array
            if(L[i].compareTo(R[j])<0){
               word[k] = L[i];
               lineNumber[k] = left[i];
               i++;
            }else{
               word[k] = R[j];
               lineNumber[k] = right[j];
               j++;
            }
         }else if( i<s1 ){
            word[k] = L[i];
            lineNumber[k] = left[i];
            i++;
         }else{ 
            word[k] = R[j];
            lineNumber[k] = right[j];
            j++;
         }
      }
   }
   
   public static void main(String[] args) throws IOException {
      if(args.length < 2){ //checks length of args array
         System.err.println("Usage: Search file target1 [target2 ..]");
         System.exit(1);
      }
      
      Scanner in = new Scanner(new File(args[0])); //initializes Scanner
      String[] target = new String[args.length-1]; //creates array target
      
      for(int i=1; i< args.length; i++){
         target[i-1] = args[i];
      }
      
      in.useDelimiter("\\Z"); // matches the end of file character
      String s = in.next();  // read in whole file as a single String
      in.close();
      String[] B = s.split("\n");  // split s into individual lines
      int lineCount = B.length;  // extract length of the resulting array
      
      int [] lineNumber = new int[B.length];
      int k=0;
      for(int i=1; i<B.length+1; i++){
         lineNumber[k]=i;
         k++;
      }
      
      mergeSort(B, lineNumber, 0, B.length-1);
      for(int p=0; p< target.length; p++){
         if((binarySearch(B, lineNumber, 0, B.length-1, target[p])==-1)){
            System.out.println(target[p] + " not found"); //prints target not found
         }
         else{//prints word and line number it was found in original array
            System.out.println(target[p] + " found on line " + lineNumber[(binarySearch(B, lineNumber, 0, B.length-1, target[p]))]);
         }
      }
   }
}