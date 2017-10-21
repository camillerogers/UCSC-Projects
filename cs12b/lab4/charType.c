/*charType.c
*Camille Rogers 
*carogers
*2 May, 2016
*12B
*takes two command line arguments giving the input and output file
*names respectively, then classifies the characters on each line of the input file into the following categories:
*alphabetic characters (upper or lower case), numeric characters (digits 0-9), punctuation, and white space
*(space, tab, or newline). 
*/

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>


#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w){/*line, alpha, num, punc, white_space*/
   int i=0, j=0, k=0, m=0, f=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
      if( isalpha( (int)s[i]) ){
         a[j++] = s[i];
      }
      if( isdigit( (int)s[i]) ){
         d[k++] = s[i];
      }   
      if( ispunct( (int)s[i]) ){
         p[m++] = s[i];
      }
      if( isspace( (int)s[i]) ){
         w[f++] = s[i];
      }
      i++;
   }
   a[j] = '\0';
   d[k] = '\0';
   p[m] = '\0';
   w[f] = '\0';
}

/* function main which takes command line arguments*/ 
int main(int argc, char* argv[]){
   FILE* in;        /* handle for input file*/                  
   FILE* out;       /* handle for output file */                 
   char* line;      /* string holding input line  */             
   char* alpha;     /* string holding all alpha chars*/ 
   char* num;       /* string holding all num chars*/ 
   char* punc;      /* string holding all pu1nc chars*/ 
   char* white;   /* string holding all white_space chars*/ 
   

   /* check command line for correct number of arguments*/ 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   /* open input file for reading*/ 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   /* open output file for writing*/ 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   /* allocate strings line and alpha_num on the heap */
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   num = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punc = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   white = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL && alpha!=NULL && num!=NULL && punc!=NULL && white!=NULL );

   /* read each line in input file, extract alpha-numeric characters*/
   int lines=1;
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      extract_chars(line, alpha, num, punc, white);
      int a = strlen(alpha);
      int b = strlen(num);
      int c = strlen(punc);
      int d = strlen(white);
      
      
      fprintf(out, "line %d contains: \n", lines);
          
      if(a==1){
         fprintf(out,"%d alphabetic character: %s\n", a, alpha);
       }
      else{
         fprintf(out,"%d alphabetic characters: %s\n", a, alpha);
      }
      if(b==1){   
         fprintf(out,"%d numeric character: %s\n", b, num);
      }
      else{
         fprintf(out,"%d numeric characters: %s\n", b, num);
      }
      if(c==1){
         fprintf(out,"%d punctuation character: %s\n", c, punc);
      }   
      else{
         fprintf(out,"%d punctuation characters: %s\n", c, punc);
      }
      if(d==1){
         fprintf(out,"%d whitespace character: %s\n", d, white);
      }
      else{
         fprintf(out,"%d whitespace characters: %s\n", d, white);
      }
      lines++;
   }

   /* free heap memory*/ 
   free(line);
   free(alpha);
   free(num);
   free(punc);
   free(white);

   /* close input and output files*/ 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}


