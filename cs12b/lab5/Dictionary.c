/*
*Dictionary.c
*Camille Rogers
*carogers
*lab5, 12B
*implement a Dictionary ADT based on the linked list data structure in C. 
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// Dictionary
// Exported reference type
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
   } NodeObj;
   
// Node
typedef NodeObj* Node;
Node head;

typedef struct DictionaryObj{
   Node head;
   int numItems;
   } DictionaryObj;

// newNode()
// constructor for the Node type
Node newNode(char* x, char* y) {
   Node D = malloc(sizeof(NodeObj));
   assert(D!=NULL);
   D->key = x;
   D->value = y; 
   D->next = NULL;
   return(D);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}


// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary S = malloc(sizeof(DictionaryObj));
   assert(S!=NULL);
   S->head = NULL;
   S->numItems = 0;
   return S;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pS){
   if( pS!=NULL && *pS!=NULL ){
      if( !isEmpty(*pS) ) makeEmpty(*pS);
      free(*pS);
      *pS = NULL;
   }
}

   
Node findKey(Dictionary D, char* k){
   Node N = D->head;
   while(N!=NULL){
      if(strcmp(N->key,k)==0){
         return N;
      }
      N = N->next;
   }
   return NULL;
}     

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if(D==NULL){
      fprintf(stderr, "Stack Error: calling isEmpty() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N = findKey(D,k);
   if(N!=NULL){
      return N->value;
   }else{
      return NULL; 
   }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   if( D==NULL ){
      fprintf(stderr, "Stack Error: calling push() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   if(findKey(D,k)!=NULL){
      fprintf(stderr, "Stack Error:cannot insert duplicate keys\n");
      exit(EXIT_FAILURE);
   }
   Node H = D->head;
   Node P = newNode(k,v);
   if(D->head == NULL){ // if it does equal null it will add items to Node
      P->next = D->head;
      D->head = P;
   }
   else{ 
      while(H->next != NULL){
         H = H->next;
      }
      H->next = P;
   }
   D->numItems++; 
}


// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   Node N;
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling push() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   N = D->head;
   
   //throw new KeyNotFoundException("cannot delete non-existent key"); // exception if findkey(key) is null
   if(lookup(D,k)==NULL){
      fprintf(stderr, "KeyNotFoundException: cannot delete non-existent key\n");
      exit(EXIT_FAILURE);
   }
   
   if(findKey(D,k)!=NULL){  
      if(N==findKey(D,k)){ 
         D->head = D->head->next;
         N->next = NULL;
         freeNode(&N);
      }else{
         while(N!=NULL && N->next!=NULL){
            if(N->next == findKey(D,k)){
               Node P = N->next;
               if(P->next==NULL){
                  N->next=NULL;
                  freeNode(&P);
               }else{
                  N->next = P->next;
                  P->next = NULL;
                  freeNode(&P);
               }
            }
            N = N->next;
         }
      }
      D->numItems--;
   }
}

//Function to free all nodes
void deleteNodes(Node N){
   if(N!=NULL){
      deleteNodes(N->next);
      freeNode(&N); 
   }
}
  
// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling popAll() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   if(D->numItems==0){
      fprintf(stderr, "Dictionary Error:calling makeEmpty() on empty dictionary"); 
   }
    Node P = D->head;//D->head = NULL;
    deleteNodes(P);
    D->numItems = 0;
    
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling printStack() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->head; N!=NULL; N=N->next) 
   fprintf(out, "%s %s\n", N->key, N->value);
}
