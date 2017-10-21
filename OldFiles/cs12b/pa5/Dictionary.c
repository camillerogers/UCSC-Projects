/*
*Dictionary.c
*Camille Rogers
*carogers
*lab5, 12B
*recreate the Dictionary ADT again in C but now based on a hash
table instead of a linked list.  
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize=101; //length of the hash table array.

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

//create List Object
typedef struct ListObj{ 
   Node head;
   int size;
   } ListObj;
   
typedef struct ListObj* List;

//create Dictionary Object
typedef struct DictionaryObj{
   int numItems;
   List* table;
   } DictionaryObj;
    
// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
   return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
   unsigned int result = 0xBAE86554;
   while (*input) {
   result ^= *input++;
   result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

//creates new node
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

//creates a new List
List newList(void){
   List L = malloc(sizeof(ListObj)); //size of the List Object
   assert(L!=NULL);
   L->head = NULL;
   L->size = 0; 
   return L;
}

void freeList(List* pL){//frees List from memory leaks
   if( pL!=NULL && *pL!=NULL ){
      free(*pL);
      *pL = NULL;
   }
}

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary S = malloc(sizeof(DictionaryObj));
   assert(S!=NULL);
   S->table = calloc(tableSize, sizeof(ListObj)); //creates new list of dictionary 
   S->numItems = 0;
   for(int i=0; i<tableSize; i++){//iterates through size of hash table array
      S->table[i] = newList(); //fills new list  
   }
   return S;
}

//helper function that removes table array
void removeTable(Dictionary D){
   for(int i=0; i<tableSize; i++){
      freeList(&D->table[i]); 
   }  
   free(D->table);
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pS){
   if( pS!=NULL && *pS!=NULL ){
      if( !isEmpty(*pS) ){
         makeEmpty(*pS);
      }
      removeTable(*pS);
      free(*pS);
      *pS = NULL;
   }
}

//finds key in list   
Node findKey(Dictionary D, char* k){
   Node N = D->table[hash(k)]->head;
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
   Node H = D->table[hash(k)]->head;
   Node P = newNode(k,v);
   if(D->table[hash(k)]->head==NULL){//if it does equal null it will add items to Node
      P->next = D->table[hash(k)]->head;
      D->table[hash(k)]->head = P;
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
   N = D->table[hash(k)]->head;
   if(lookup(D,k)==NULL){
      fprintf(stderr, "KeyNotFoundException: cannot delete non-existent key\n");
      exit(EXIT_FAILURE);
   }
   
   if(findKey(D,k)!=NULL){  
      if(N==findKey(D,k)){ 
         D->table[hash(k)]->head = D->table[hash(k)]->head->next;
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
    for(int i=0; i<tableSize; i++){
       deleteNodes(D->table[i]->head);
    }
    D->numItems = 0;
    
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling printStack() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   for(int i=0; i<tableSize; i++){ //iterates through lists
      Node N = D->table[i]->head;
      while(N!=NULL){//checks through Node until the end
         fprintf(out, "%s %s\n", N->key, N->value);
         N = N->next; 
      }
   }
}