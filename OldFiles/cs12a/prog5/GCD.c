/*
* GCD.c
* Camille Rogers
* carogers
* lab8
* calculates the greatest common divisor of two numbers, which the user inputs in the language c
*/

#include<stdio.h>
#include<stdlib.h>

int main(){
   int i, n;
   int x;
   char str[100]; 

   printf("Enter a positive integer: ");
   
   while( 1 ){ 
      n = scanf(" %d", &x); 
      
      while( n!=1 ){ 
         scanf("%s", str); 
         printf("Please enter a positive double: "); 
         n = scanf(" %d", &x); 
      } 
   
   if( x>0 ) break; 
      printf("Please enter a positive double: "); 
   }

   while( 1 ){ 
      n = scanf(" %d", &x); 
      while( n!=1 ){ 
         scanf("%s", str); 
         printf("Please enter another positive integer: "); 
         n = scanf(" %d", &x); 
      } 
      if( x>0 ) break; 
         printf("Please enter a positive integer: "); 
      }
   
   if(int1>int2){
      divident = int1;
      divisor = int2;
   }
   if(int1<int2){
      divident = int2;
      divisor =  int1; 
   }
   
   remainder = divident%divisor;
        
   while(divident>0){
     remainder=divident;
     divident=divisor%divident;
     divisor=remainder;
   }
            
   gcd = remainder;
   System.out.println("The GCD of " + int1 + " and " + int2 + " is " + gcd);

   return 0;    
}

