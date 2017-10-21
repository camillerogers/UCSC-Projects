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
   int a, b, int1, int2, remainder, divident, divisor, largerNum, gcd;
   char str[100]; 

   printf("Enter a positive integer: ");
   
   while( 1 ){ 
      a=scanf(" %d", &int1); 
      
      while( a!=1 ){ 
         scanf("%s", str); 
         printf("Please enter a positive integer: "); 
         a=scanf(" %d", &int1); 
      } 
   
   if( int1>0 ) break; 
      printf("Please enter a positive integer: "); 
   }
   printf("Enter another positive integer: "); 
   while( 1 ){ 
      b=scanf(" %d", &int2); 
      while( b!=1 ){ 
         scanf("%s", &str); 
         printf("Please enter a positive integer: "); 
         b=scanf(" %d", &int2); 
      } 
      if( int2>0 ) break; 
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
   printf("The GCD of %d and %d is %d\n", int1,int2,gcd);

   return 0;    
}
