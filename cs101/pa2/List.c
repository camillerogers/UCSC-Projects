//List.c
//Camille Rogers
//carogers
//pa2
//carogers
//List ADT, functions in order to insert into List and sort List
// structs --------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include "List.h"

//private NodeObj type
typedef struct NodeObj{				
   int data;
   struct NodeObj* next;		//create Node object next, previous
   struct NodeObj* previous;
} NodeObj;

//private Node type
typedef NodeObj* Node;

//private ListObj type
typedef struct ListObj{
   Node front;
   Node back;
   Node cursor;
   int length;
   int idx;
} ListObj;

//returns reference to new Node object. Initializes next and data fields.
//private.
Node newNode(int data){
   Node N = malloc(sizeof(NodeObj));
   N->data = data;
   N->next = NULL;
   N->previous = NULL;
   return(N);
}

//frees heap memory pointed to by *pN, sets *pN to NULL.
//private.
void freeNode(Node* pN){
   if(pN!=NULL && *pN!=NULL){
      free(*pN);
      *pN = NULL;
   }
}

// Constructors-Destructors ---------------------------------------------------

//returns reference to new empty List object.
List newList(void){
	List L;
	L = malloc(sizeof(ListObj));
	L->front = L->back = L->cursor = NULL;
	L->length = 0;
	L->idx = 0;
	return(L);
}

//frees all heap memory associated with Queue *pQ, and sets *pQ to NULL.S
void freeList(List* pL){
	if(pL!=NULL && *pL!=NULL) { 
    	while( !isEmpty(*pL) ) { 
        	deleteFront(*pL); 
      	}
    free(*pL);
    *pL = NULL;
	}
}

// Access functions -----------------------------------------------------------

//returns true (1) if Q is empty, otherwise returns false (0)
int isEmpty(List L){
   if( L==NULL ){
      printf("List Error: calling isEmpty() on NULL List reference\n");
      exit(1);
   }
   return(L->length==0);
}

//returns the length of List
int length(List L){
	if( L==NULL ){
    	printf("List Error: calling length() on NULL List reference\n");
    	exit(1);
   	}
   	return(L->length);
}

//returns the index of List
int index(List L){
	if(L->cursor!=NULL){
		L->idx = L->cursor->data;
		return(L->idx);
	}
	else{
		return -1;
	}
}

//returns the value at the front of L. Pre: !isEmpty(L)
int front(List L){
	if(L==NULL){
    	printf("List Error: calling front() on NULL List reference\n");
      	exit(1);
   	}
   	if(isEmpty(L)){
      	printf("List Error: calling front() on an empty List\n");
      	exit(1);
   	}
   	return(L->front->data);
}

//returns the value at the back of L. Pre: !isEmpty(L)
int back(List L){
	if(L==NULL){
    	printf("List Error: calling back() on NULL List reference\n");
      	exit(1);
   	}
   	if(isEmpty(L)){
      	printf("List Error: calling back() on an empty List\n");
      	exit(1);
   	}
   	return(L->back->data);
}

//returns cursor element. Pre: length()>0, index()>=0
int get(List L){
	if(L==NULL){
		printf("List Error: calling get() on NULL List reference\n");
		exit(1);
	}
	if(isEmpty(L)){
      	printf("List Error: calling get() on an empty List\n");
      	exit(1);
    }
    if(L->idx==-1){
    	printf("List Error: calling get() on NULL cursor\n");
    	exit(1);
    }
    return(L->cursor->data);
}

//returns true (1) if A is identical to B, false (0) otherwise
int equals(List A, List B){
	int eq = 0; 
   	Node N = NULL;
   	Node M = NULL;
   	if(A==NULL || B==NULL){
    	printf("List Error: calling equals() on NULL List reference\n");
      	exit(1);
   	}
   	eq = (A->length == B->length);
   	N = A->front;
   	M = B->front;
   	while(eq && N!=NULL){
    	eq = (N->data==M->data);
      	N = N->next;
      	M = M->next;
   	}
   	return eq;
}

// Manipulation procedures ----------------------------------------------------

//clears List
void clear(List L){
	L->front = NULL;
	L->back = NULL;
	L->cursor = NULL;
	L->length = 0;
	L->idx = -1;
}

//if List is non-empty, places the cursor under the front element
void moveFront(List L){
	if(L==NULL){
		printf("List Error: calling moveFront() on NULL List reference\n");
		exit(1);
	}
	if(isEmpty(L)){
      	printf("List Error: calling get() on an empty List\n");
      	exit(1);
    }
	L->cursor = L->front;
}

//if List is non-empty, places the cursor under the back element
void moveBack(List L){
	if(L==NULL){
		printf("List Error: calling moveFront() on NULL List reference\n");
		exit(1);
	}
	if(isEmpty(L)){
      	printf("List Error: calling get() on an empty List\n");
      	exit(1);
    }
	L->cursor = L->back;
}

//if cursor is defined and not at front, moves cursor one step toward front of this 
//List, if cursor is defined and at front, cursor becomes undefined, if cursor is 
//undefined does nothing.
void movePrev(List L){
	if(L->cursor!=NULL){	
		L->cursor = L->cursor->previous;
	}
	if(L->cursor==NULL){
		return;
	}
}

//if cursor is defined and not at back, moves cursor one step toward back of 
//this List, if cursor is defined and at back, cursor becomes undefined, if
//cursor is undefined does nothing
void moveNext(List L){
	if(L->cursor!=NULL){	
		L->cursor = L->cursor->next;
	}
	if(L->cursor==NULL){
		return;
	}
}

//insert new element into this List. If List is non-empty, insertion takes place
//before front element
void prepend(List L, int data){
	Node N = newNode(data);
   	if(L==NULL){
    	printf("List Error: calling prepend() on NULL List reference\n");
      	exit(1);
   	}
   	if(isEmpty(L)){ 		//if List is empty, set front and back to newNode
    	L->front = L->back = N; 
    	L->length++;
    	L->idx++;
    }
    else{
    	L->front->previous = N;		
    	N->next = L->front;
    	L->front = N;
    	L->length++;
    	L->idx++;
    }
}

//insert new element into this List. If List is non-empty, insertion
//takes place after back element. 
void append(List L, int data){
	Node N = newNode(data);
	if(L==NULL){
		printf("List error: calling append() on NULL List reference\n");
		exit(1);
	}
	if(isEmpty(L)){
		L->front = L->back = N;
		L->back->next = NULL;
		L->length++;
		//freeNode(&L->back->next);
	}
	else{
		L->back->next = N;
		N->previous = L->back;
		L->back = L->back->next;
		L->length++;
	}
}

//insert new element before cursor. Pre: length()>0, index()>=0
void insertBefore(List L, int data){
	Node N = newNode(data);
	if(L==NULL){
		printf("List error: calling insertBefore() on NULL List reference\n");
		exit(1);
	}
	if(isEmpty(L)){
		printf("List Error: calling insertBefore() on an empty List\n");
      	exit(1);
	}
	if(L->idx==-1){
		printf("List Error: calling insertBefore() on an empty index\n");
      	exit(1);
	}
	else{
		N->previous = L->cursor->previous;
		N->next = L->cursor;
		L->cursor->previous->next = N;
		L->cursor->previous = N;
		L->length++;
		L->idx++;
	}
}

//inserts new element after cursor, Pre: length()>0, index()>=0
void insertAfter(List L, int data){
	Node N = newNode(data);
	if(isEmpty(L) && L->idx<0){	//if List is empty and index is undefined throw error
		printf("List Error: insertAfter() called empty List");
	}
	if( L->idx == L->length - 1){	//if index of cursor is back, then append() data 
		append(L, data);
	}
	else{
		N->next = L->cursor->next;
		N->previous = L->cursor;
		L->cursor->next->previous = N;
		L->cursor->next = N;
		L->length++;
	}
}

//deletes the front element, Pre: length()>0
void deleteFront(List L){
	Node N = NULL;
	if(L==NULL){
    	printf("List Error: calling deleteFront() on NULL List reference\n");
      	exit(1);
   	}
   	if(isEmpty(L)){
    	printf("List Error: calling deleteFront on an empty List\n");
      	exit(1);
   	}
  	N = L->front;
   	if(length(L)>1){ 
    	L->front = L->front->next; 
   	}else{ 
    	L->front = L->back = NULL; 
    	//freeNode(&L->back);
    	//freeNode(&L->front);
   	}
   	L->length--;
   	freeNode(&N);
}


//Deletes the back element. Pre length()>0
void deleteBack(List L){
	Node N = NULL;
	if(L==NULL){
    	printf("List Error: calling deleteBack() on NULL List reference\n");
      	exit(1);
   	}
   	if(isEmpty(L)){
    	printf("List Error: calling deleteBack() on an empty List\n");
      	exit(1);
   	}
  	N = L->back;
   	if(length(L)>1){ 
    	L->back = L->back->previous; 
   	}else{ 
    	L->back = L->front = NULL; 
    	//freeNode(&L->back);
    	//freeNode(&L->front);
   	}
   	L->length--;
   	freeNode(&N);
}
	
//Deletes cursor element, making cursor undefined. Pre: length()>0, index()>=0
void delete(List L){
	if(L==NULL){
		printf("List error: calling delete() on NULL List reference\n");
		exit(1);
	}
	if(isEmpty(L)){
		printf("List Error: calling delete() on an empty List\n");
      	exit(1);
	}
	if(L->idx==0){
		printf("List Error: calling delete() on an empty index\n");
      	exit(1);
	}
	else{
		L->cursor = NULL;		 
		L->idx= -1;			
		L->length--;
	}
	
	//freeNode(&L->cursor);
}

// Other operations -----------------------------------------------------------

//prints data elements in L on a single line to stdout.
void printList(FILE* out, List L){
	Node N = NULL;
	if(L==NULL){
      printf("List Error: calling printList() on NULL List reference\n");
      exit(1);
   }
   for(N = L->front; N != NULL; N = N->next){
      printf("%d ", N->data);
   }
   printf("\n");
}

//copies List L 
List copyList(List L){
	List l = newList();
	Node N = L->front;
	while(N != NULL){
		append(l, N->data);
		N = N->next;
	}
	return l;
}