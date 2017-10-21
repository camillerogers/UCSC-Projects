//Roots.java
//Camille Rogers
//carogers
//prog4
//gives the real roots of a polynomial that lies within a specified range.

import java.util.Scanner;
class Roots{
    
    public static void main( String[] args ){
        
        int degree;
        double leftEnd, rightEnd;
        double[] poly;
        double resolution = Math.pow(10,-2);
        double threshold = Math.pow(10,-3);
        double tolerance = 0.0000001;
        double a, b;
        boolean roots = false; 
        
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter the degree: ");
        degree = keyboard.nextInt();
    
        int coef = degree + 1;
        poly = new double[coef];
        System.out.print("Enter " + coef + " coefficients: ");
        
        for(int i = 0; i<poly.length; i++){
            poly[i]=keyboard.nextDouble();
        }

        System.out.print("Enter the left and right endpoints: ");
        leftEnd = keyboard.nextDouble();
        rightEnd = keyboard.nextDouble();
        System.out.println();
        
        double[] test = diff(poly);
        a = leftEnd;
        b = a + resolution;
        while(a<=rightEnd){
            if(poly(poly,a)*poly(poly,b)<0){
                System.out.printf("Root found at %.5f%n", findRoot(poly,a,b,tolerance));
                roots = true;
            }
            if(poly(test,a)*poly(test,b)<0){
                double r = findRoot(test,a,b,tolerance);
                if(Math.abs(poly(poly,r))<threshold){
                    System.out.printf("Root found at %.5f%n", r);
                    roots = true;	
        		}
        	}
        a = b;
        b = a + resolution; 
        }
        
        if(!roots){
            System.out.println("No roots were found in the specified range.");
        }
        
        }
        static double poly(double[] C, double x){
            double sum = 0;
            for(int i = 0; i < C.length; i++){
                sum += C[i] * Math.pow(x, i);
            }
            return sum;
        }
        
       static double[] diff(double[] C){
           double[] derv = new double[C.length-1];
           for(int i = 1; i <= derv.length; i++){
               double d = C[i]*i;
               derv[i-1]=d; 
           }
           return derv;
        }
        
        static double findRoot(double[] C, double a, double b, double tolerance){
            double width = Math.abs(b-a);
            double mid=0.0;
            while ( width>tolerance ){
                mid = (a+b)/2;
                if( poly(C,a)*poly(C,mid)<=0 ){  
                    b = mid;           
                } else {               
                    a = mid;            
                } 
                width = b-a;
           }
           return mid;
    }
}
