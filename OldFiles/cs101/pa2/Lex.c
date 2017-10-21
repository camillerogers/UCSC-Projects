//Lex.c
//Camille Rogers
//Pa1
//CMPS 101
//carogers
//Sorts array indirectly using List.c with a List of indices of array.

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"

#define MAX_LEN 160

int main(int argc, char * argv[]){
	int n, count=0;
   	FILE *in, *out;
   	char line[MAX_LEN];
   	char* array[MAX_LEN];		//create new String array
   	List indices = newList();	//create new List indices

   // check command line for correct number of arguments
   if( argc != 3 ){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
      	exit(1);
   }

   // open files for reading and writing 
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   if( in==NULL ){
    	printf("Unable to open file %s for reading\n", argv[1]);
      	exit(1);
   }
   if( out==NULL ){
    	printf("Unable to open file %s for writing\n", argv[2]);
      	exit(1);
   }
   
	fprintf(out, "Array: \n");
	
    //get length of array 
    while( fgets(line, MAX_LEN, in) != NULL)  {
    	count++;				
    }
    
    //set aside memory for array
    for(int i = 0; i<=count;i++){
    	array[i] = calloc(1, sizeof(char* [MAX_LEN]) );
    }
	
	n=0;

	rewind(in); 	//sets file position to the beginning
   
   //puts lines into array
   	while( fgets(line, MAX_LEN, in) != NULL){
    	strcpy(array[n], line );
		n++;
 	}
 	
 	//print out array
 	for(int g=0; g<count; g++){
 		fprintf(out, "%s", array[g]);
 	}
	
	//put 0 into List indices 
   	append(indices,0);	

	//Insertion Sort for List indices
	for(int k=1; k<count; k++){
    	int j=k-1;						//sets J to element before k
     	moveBack(indices);				//start at back element and move forward
     	
        while(j >= 0 && strcmp(array[k],array[j])<0){
			movePrev(indices); 		//if current comes before array[j] move previous
			j--;
	    }
    	if(index(indices)!=-1){ 		
    		insertAfter(indices,k);		//if index is not null, insert k after cursor
        }
        else{
    		prepend(indices, k);			//else insert k at the end of List
       	}
	}
		
	fprintf(out, "\n");
	fprintf(out, "\n");
	fprintf(out, "List of indices: ");				
	fprintf(out, "\n");
	
	moveFront(indices);					//move to beginning of List indices
	for(int p=0; p<count; p++){			
    	fprintf(out, "%d ", get(indices)); //print out List indices
      	moveNext(indices);
		fprintf(out, "\n");
  	}

	fprintf(out, "\n");
	fprintf(out, "Sorted Array: \n");
	moveFront(indices);					//move to beginning of List indices
	for(int i = 0; i< count; i++){	
		fprintf(out,"%s ", array[get(indices)]); //print array element at the place of cursor
		moveNext(indices);					//in List, then move cursor next
		fprintf(out, "\n");
	}
	
	fclose(in);
   	fclose(out);
	
	int f=0;
	while(f<=count){	//free array
		free(array[f]);
		f++;
	}
	freeList(&indices);						//free List indices

    
}

