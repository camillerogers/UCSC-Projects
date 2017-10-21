//GCD.java
//Camille Rogers
//carogers
//pa3
//calculates the greates common divisor of two numbers, which the user inputs

import java.util.Scanner;

class GCD{
    
    public static void main(String[] args){
       
        int int1, int2;
	int remainder;
	int divident; 
        int divisor;
        int largerNum;
        int gcd;
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        
        while(true){
            while(!keyboard.hasNextInt()){
                keyboard.next();
                System.out.print("Please enter a positive integer: ");
            }
            int1 = keyboard.nextInt();
	    if(int1>0) break; 
            System.out.print("Please enter a positive integer: ");
        }
        System.out.print("Enter another positive integer: ");
        
        while(true){
            while(!keyboard.hasNextInt()){
                keyboard.next();
                System.out.print("Please enter a positive integer: ");
            }
            int2 = keyboard.nextInt();
            if(int2>0) break;
            System.out.print("Please enter a positive integer: ");
        }

       /* if(int1<int2){
            divident = int2;
            divisor = int1;
            System.out.println(divident + " > "+ divisor);
        }
        if(int2<int1){
            divident = int1;
            divisor = int2;
            System.out.println(divident + " > " + divisor);
        }
        //System.out.print(divident + " " + divisor);
       */
        //divident = largerNum; 
        divident =  Math.max(int1,int2); 
        divisor =  Math.min(int1,int2);  
        remainder = divident%divisor;
        while(divident>0){
            //remainder=divident%divisor;
            //divident=divisor;
            //divisor=remainder;
            remainder=divident;
            divident=divisor%divident;
            divisor=remainder;
            }

        //while(divisor>0);
        
        /*for(int divisor = 0;divisor <= largerNum; divisor++){
            if(int1%divisor==0 && int2%divisor==0){
                gcd = divisor;
            }
            
        }*/
        
        gcd = remainder;
        System.out.println("The GCD of " + int1 + " and " + int2 + " is " + gcd);
        
    }
}
