/*Sphere.c
 * Camille Rogers
 * carogers
 * lab7
 * prints out the volume and surface area of the sphere, whose radius is the value entered.
 */
#include<math.h>  
#include<stdio.h>
#include<stdlib.h>

int main(void){
   const double pi = 3.141592654;
   double volume, surArea, radius;

   printf("Enter the radius of the sphere: ");
   scanf( "%lf" , &radius);
   volume = ((4.0/3.0)*pi*pow(radius,3));
   surArea = (4.0*pi*pow(radius,2));
   printf("The volume is %lf", volume);
   printf(" and the surface area is %lf\n",surArea);
   return 0;
}

