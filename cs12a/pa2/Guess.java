//Guess.java
//Camille Rogers
//carogers
//pa2
//will allow user to guess a number the computer will randomly produce from 1 to 10.
import java.util.Scanner;

class Guess{

    public static void main( String[] args){
        
        Scanner keyboard = new Scanner(System.in);
        String high  = "Your guess is too high. ";
        String low = "Your guess is too low.";
        int min = 1;
        int max = 10;
        int answer = (int)(min +Math.random() * max);
        int guess;
        int numberGuess = 1;
        
        System.out.println("I'm thinking of an integer in the range 1 to 10. You have three guesses.");
        System.out.print("\nEnter your first guess: ");
        guess = keyboard.nextInt();
        
        while(numberGuess <=3 && guess != answer){

            if(guess > answer){
                System.out.println(high);
                System.out.print("\n");
                numberGuess++;
             }
         
            if(guess < answer){
                System.out.println(low);
                System.out.print("\n");
                numberGuess++; 
            }
            
            if(numberGuess==2){
                System.out.print("Enter your second guess: ");   
                guess = keyboard.nextInt();
            }
            
            if(numberGuess==3){
                System.out.print("Enter your third guess: ");
                guess = keyboard.nextInt();
            }
       } 
      
       if(numberGuess > 3){
           System.out.println("You lose. The number was " + answer + ".");
       }
      
       if(guess == answer){
           System.out.println("You win!");
        }
  
  }
}
