public class GcdTest{
  private String e = "2"; 
  private String phi = "1050809315572461349368";
  private String prime1 = "178956971";
  
  public GcdTest(){
    checkGCD();
  }

  //Check if GCD
  public void checkGCD(){
    HugeUnsignedInteger n1 = new HugeUnsignedInteger(e);  //e
    HugeUnsignedInteger n2 = new HugeUnsignedInteger(phi);  //phi
    HugeUnsignedInteger one = new HugeUnsignedInteger("1");
   
    while(!findGCD(n1.value, n2.value).equals("1")){
      n1 = new HugeUnsignedInteger(n1.addition(one));
    }
    System.out.println(findGCD(n1.value, n2.value));
    e = n1.value;
  }
  
  //Find GCD
  public String findGCD(String int1, String int2){
    HugeUnsignedInteger x1 = new HugeUnsignedInteger(int1);
    HugeUnsignedInteger x2 = new HugeUnsignedInteger(int2);
    //base case
    if(x2.value.equals("0")){
      //System.out.println(x1.value);
      return x1.value;
    }
    return findGCD(x2.value, x1.modulus(x2));
  }
  
  //Get e
  public String getE(){
    return e;
  }
  
  //-------------PRIME-----------
  public boolean isPrime(){
    HugeUnsignedInteger p1 = new HugeUnsignedInteger(prime1);  //n
    HugeUnsignedInteger two = new HugeUnsignedInteger("2");
    HugeUnsignedInteger i = new HugeUnsignedInteger("3");
    if(p1.modulus(two).equals("0")){
      return false;
    }
    HugeUnsignedInteger iSquare = new HugeUnsignedInteger(i.multiplication(i));
    while(iSquare.lessThan(p1) == 1 || iSquare.equalTo(p1) == 1){
      if(p1.modulus(i).equals("0")){
        return false;
      }
      i = new HugeUnsignedInteger(i.addition(two));
      iSquare = new HugeUnsignedInteger(i.multiplication(i));
    }
    return true;
  }
  
  //main
  public static void main(String[] args){
    GcdTest tt = new GcdTest();
    System.out.println("E:"+ tt.getE());
    if(tt.isPrime()){
      System.out.println(tt.prime1 + " is a prime number");
    }
    else{
      System.out.println("Not a prime number");
    }
  }
}