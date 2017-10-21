//Complex.java
//Camille Rogers
//pa6
//carogers
//Fill in the function definitions below. See notes from 3-2-16 for the 
//definitions of the complex arithmetic operations.

public class Complex{

   private double re;
   private double im;
   
  
   public static final Complex ONE = Complex.valueOf(1,0);
   public static final Complex ZERO = Complex.valueOf(0,0);
   public static final Complex I = Complex.valueOf(0,1);

  
   Complex(double a, double b){
      this.re = a;
      this.im = b;
   }

   Complex(double a){
      this.re = a;
      this.im = 0;
   }

   Complex(String s){
      
      double[] Q = parseComplex(s);
      re = Q[0];
      im = Q[1];
      }
 
   Complex copy(){
      Complex a = new Complex(this.re, this.im);
      return a;
   }
   

   Complex add(Complex z){
      double c = this.im + z.im;
      double b = this.re + z.re;
      Complex a = new Complex(b, c);
      return a;	

   }

   Complex negate(){
      double b = this.re*-1;
      double c = this.im*-1;
      Complex a = new Complex(b, c);
      return a;	
   }

   Complex sub(Complex z){
      // Fill in
      double c = this.im - z.im;
      double b = this.re - z.re;
      Complex a = new Complex(b, c);
      return a;	
   }

   Complex mult(Complex z){
      // Fill in
      double b = (this.re*z.re - this.im*z.im); 
      double c = (this.re*z.im + this.im*z.re);
      Complex a = new Complex(b, c);
      return a;	
   }


   Complex recip(){
      double b = this.re/(Math.pow(this.re,2)+Math.pow(this.im,2));
      double c = -1*this.im/(Math.pow(this.re,2)+Math.pow(this.im,2));
      Complex a = new Complex(b, c);
      return a;	
   }

   Complex div(Complex z){

      if(z.equals(Complex.ZERO)){
         throw new ArithmeticException("Cannot divide by zero!!!");
      }
      Complex a = this.mult(z.recip());
      return a;	
   }

 
   Complex conj(){
      // Fill in
      double c = this.im*-1;
      double b = this.re;
      Complex a = new Complex(b, c);
      return a;	
   }
   
  
   double Re(){
      return re;
   }

   double Im(){
      return im;
   }


   double abs(){
      double b = Math.sqrt(Math.pow(this.re,2)+Math.pow(this.im,2));
      return b;	
   }

   double arg(){
      return Math.atan2(im, re);
   }


   public String toString(){
      String str = "";
      if(re!=0){
          str += String.valueOf(re);
          }
      if(im>0 && re!=0){
         str += "+";
         }
      if(im!=0){
       str += String.valueOf(im)+"i";
      } 
      return str;
   
   }

   public boolean equals(Object obj){
      boolean eq = false;
      Complex r; 
      
      if( obj instanceof Complex ){
         r = (Complex) obj;
         eq = ( this.re==r.re && this.im==r.im );
      }
      return eq;
   }

   static Complex valueOf(double a, double b){
      return new Complex(a,b);
   }

   static Complex valueOf(double a){

      return new Complex(a);
    }

   static Complex valueOf(String s){
      return new Complex(s);
   }
   
   static double[] parseComplex(String str){
      double[] part = new double[2];
      String s = str.trim();
      String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
      String SGN = "[+-]?";
      String OP =  "\\s*[+-]\\s*";
      String I =   "i";
      String OR =  "|";
      String REAL = SGN+NUM;
      String IMAG = SGN+NUM+"?"+I;
      String COMP = REAL+OR+
                    IMAG+OR+
                    REAL+OP+NUM+"?"+I;
      
      if( !s.matches(COMP) ){
         throw new NumberFormatException(
                   "Cannot parse input string \""+s+"\" as Complex");
      }
      s = s.replaceAll("\\s","");     
      if( s.matches(REAL) ){
         part[0] = Double.parseDouble(s);
         part[1] = 0;
      }else if( s.matches(SGN+I) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I, "1.0" ) );
      }else if( s.matches(IMAG) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I , "" ) );
      }else if( s.matches(REAL+OP+I) ){
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
      }else{   //  s.matches(REAL+OP+NUM+I) 
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
      }
      return part;
   }

}