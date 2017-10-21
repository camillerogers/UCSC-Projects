//-----------------------------------------------------------------------------
// List.h
// Header file for List ADT
//-----------------------------------------------------------------------------

#ifndef _LIST_H_INCLUDE_
#define _LIST_H_INCLUDE_

//exported type
typedef struct ListObj* List;

// Constructors-Destructors ---------------------------------------------------

//returns reference to new empty List object.
List newList(void);

//frees all heap memory associated with List *pL, and sets *pL to NULL
void freeList(List* pL);

// Access functions -----------------------------------------------------------

//returns true (1) if L is empty, otherwise returns false (0)
int isEmpty(List L);

//returns the length of List
int length(List L);

//returns the index of List
int index(List L);

//returns the value at the front of L. Pre: !isEmpty(L)
int front(List L);

//returns the value of the back of L: Pre: !isEmpty(L)
int back(List L);

//returns cursor element. Pre: length()>0, index()>=0
int get(List L);

//returns true (1) if A is identical to B, false (0) otherwise
int equals(List A, List B);

// Manipulation procedures ----------------------------------------------------

//clears List
void clear(List L);

//if List is non-empty, places the cursor under the front element
void moveFront(List L);

//if List is non-empty, places the cursor under the back element
void moveBack(List L);

//if cursor is defined and not at front, moves cursor one step toward front of this 
//List, if cursor is defined and at front, cursor becomes undefined, if cursor is 
//undefined does nothing.
void movePrev(List L);

//if cursor is defined and not at back, moves cursor one step toward back of 
//this List, if cursor is defined and at back, cursor becomes undefined, if
//cursor is undefined does nothing
void moveNext(List L);

//insert new element into this List. If List is non-empty, insertion takes place
//before front element
void prepend(List L, int data);

//insert new element into this List. If List is non-empty, insertion
//takes place after back element. 
void append(List L, int data);

//insert new element before cursor. Pre: length()>0, index()>=0
void insertBefore(List L, int data);

//Inserts new element after cursor, Pre: length()>0, index()>=0
void insertAfter(List L, int data);

//Deletes the front element, Pre: length()>0
void deleteFront(List L);

//Deletes the back element. Pre length()>0
void deleteBack(List L);

//Deletes cursor element, making cursor undefined. Pre: length()>0, index()>=0 
void delete(List L);

// Other operations -----------------------------------------------------------

//prints data elements in L on a single line to stdout.
void printList(FILE* out, List L);

//copies List L 
List copyList(List L);

#endif
