//DictionaryTest.c
//Camille Rogers
//carogers
//12B pa5
//Test client for the Dictionary ADT

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary(); //creates new dictionary 
   char* k;
   char* v;
   char* test1[] = {"1","2","3","4","5"};
   char* test2[] = {"cat","dog","pig","frog","turtle"};
   int i;

   for(i=0; i<5; i++){
      insert(A, test1[i], test2[i]); //test insert()
   }

   printDictionary(stdout, A); //test print

   for(i=0; i<5; i++){
      k = test1[i];
      v = lookup(A, k); //test lookup
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   // insert(A, "five", "rat"); // error: duplicate keys

   delete(A, "2"); //tests delete()
   delete(A, "4");

   printDictionary(stdout, A); //test print 

   for(i=0; i<5; i++){
      k = test1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   // delete(A, "1");  // error: key not found

   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));
   makeEmpty(A); //tests makeEmpty()
   printf("%s\n", (isEmpty(A)?"true":"false"));

   freeDictionary(&A);

   return(EXIT_SUCCESS);
}