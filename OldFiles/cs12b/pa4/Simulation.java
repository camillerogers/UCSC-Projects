//Simulation.java 
//Camille Rogers
//carogers
//12B
// Your program will take one command line argument giving the
//name of an input file, and will write to two output files whose names are the name of the input file followed by
//the suffixes .rpt and .trc respectively

import java.io.*;
import java.util.Scanner;

public class Simulation{

   public static Job getJob(Scanner in) {
      
      String[] s = in.nextLine().split(" "); //calls Job
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }
   
   public static void main(String[] args) throws IOException{
      
      Scanner in = null; //initializing variables
      PrintWriter items = null;
      PrintWriter nums = null;
      Queue q = new Queue();
      int s, st, t;
      
       in = new Scanner(new File(args[0]));//input and output files
      
      if (args.length < 1){ //throws exception if there is less than one argument
         System.err.println("Usage: Simulation fileIn fileOut");
         System.exit(1);
      }

      items = new PrintWriter( new FileWriter(args[1]+".rpt")); // prints to one of output files
      items.println(q);
      
      nums = new PrintWriter( new FileWriter(args[2]+ ".trc"));
      nums.println(q);
    }
}   

