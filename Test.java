public class Test{
  public static void main(String args[]){
    HugeUnsignedInteger one = new HugeUnsignedInteger("1000009999999999999339393993");
    System.out.println(one.getDigit());
    one.printValue();
  } 
}