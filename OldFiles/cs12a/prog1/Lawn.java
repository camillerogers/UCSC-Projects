//Lawn.java
//Camille Rogers
//1467517
//pa1
//calculates the lawn area from the length and width of the lot and the mowing time
import java.util.Scanner;

class Lawn{
   
   public static void main( String[] args){
  
      Scanner keyboard = new Scanner(System.in);

      String plural;
     
      double lengthLot, widthLot, lengthHouse, widthHouse, area, mowingRate, mowingTime, time, seconds; 
        
      int hours, minutes; 
         
      System.out.print("Enter the length and width of the lot, in feet: ");
      lengthLot = keyboard.nextDouble();
      widthLot = keyboard.nextDouble();
      
      System.out.print("Enter the length and width of the house, in feet: ");
      lengthHouse = keyboard.nextDouble();
      widthHouse = keyboard.nextDouble();
     
      area = (lengthLot * widthLot) - (lengthHouse * widthHouse);
      System.out.println("The lawn area is " + area + " square feet.");
     
      System.out.print("Enter the mowing rate, in square feet per second: ");
      mowingRate = keyboard.nextDouble();
     
      mowingTime = (area/mowingRate); 
      hours = (int)(mowingTime/3600); 
      minutes = (int)((mowingTime/60)%60);
      seconds = (double)(mowingTime%60);
     
      System.out.println("The mowing time is " + hours + " hour" + (hours==1? " ":"s"+" ") + minutes +" minute" + (minutes==1?" ":"s"+" ") + Math.round(seconds) + " second" + (seconds==1?".":"s.")); 

   }
}     
