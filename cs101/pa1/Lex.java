//Lex.java
//Camille Rogers
//carogers
//Pa1
//checks there are two command line arguments, reads lines from file and puts into 
//string array, creates a List using elements from List.java to sort elements in string
//array

import java.io.*;
import java.util.Scanner;
   
class Lex{
	
   public static void main(String[] args) throws IOException{
   		Scanner in = null;			
      	PrintWriter out = null;
      	String line = null;
      	String[] array = null;		//create String array
      	int lineNumber = 0;			
      	List indices = new List();	//create List indices
      	int l = 0;
      	String current = null;
	  	String back = null;
	  	String front = null;

      	if(args.length < 2){		//check if there are two command line arguments
        	System.err.println("Usage: Lex infile outfile");
         	System.exit(1);
      	}
      
      	in = new Scanner(new File(args[0]));	//take in argument
      
      	while(in.hasNextLine()){				//while in has next line, add elements for array #
      		lineNumber++;
      		line = in.nextLine() + " ";    // add extra space so split works right
      	}
      	
      	in.close();							//close scanner
      		
      	array = new String[lineNumber];		//create String array, size of lineNumber 
      
      	in = new Scanner(new File(args[0]));			//open in/out scanner
      	out = new PrintWriter(new FileWriter(args[1]));
      
      	out.print("Array: " );	
      	
      	while(in.hasNextLine()){
      		line = in.nextLine() + " ";    	//add extra space so split works right
      		array[l] = line;				//add elements into array
      		out.print(array[l]);			//print String array
      		l++;
      	}
      	
     	out.println();						//close scanner
	 	in.close();
      
    	indices.append(0);					//put 0 into List indices 
     
     	//insertionSort indices
     	for(int k=1; k<array.length; k++){	
     		indices.moveBack();				//start at back element and move forward 
        	current = array[k]; 			//through array	
        
	    	for(int j=k-1; j >= 0 && current.compareTo(array[j])<0;j--){
				indices.movePrev(); 		//if current comes before array[j] move previous
	    	}
	    
	    	if(indices.index()!=-1){ 		
				indices.insertAfter(k);		//if index is not null, insert k after cursor
        	}
        
       		else{
        		indices.prepend(k);			//else insert k at the end of List
       		}
   
		}
		
		out.print("List of indices: ");
		out.println(indices);					//print List indices
		out.print("Sorted Array: ");
		indices.moveFront();					//move cursor to front of the List
		for(int i = 0; i< array.length; i++){	
			out.print(array[indices.get()]);	//print array element at the place of cursor
			indices.moveNext();					//in List, then move cursor next
	
		}
		
		out.close();							//close scanner
	}
}

   
