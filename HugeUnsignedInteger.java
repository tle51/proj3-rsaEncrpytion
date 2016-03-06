/** Tan Le (tle51) & Janki Patel (jpate53)
  * CS 342 - Project 3: RSA Encryption/Decryption
  * Huge-Unsigned-Integer Class - Hold very large unsigned integer values and include operations
  */
import java.io.*;

public class HugeUnsignedInteger{
  private String value;
  private int[] arr;
  private int numDigit;
  
  public HugeUnsignedInteger(String input){
    value = input;
    toArray();
  }
  
  //Covert value to array
  public void toArray(){
    numDigit = value.length();  //Count number of digits
    
    
  }
  
  //------------Test----------------
  public int getDigit(){
    return numDigit;
  }
  public void printValue(){
    System.out.println(value);
  }
  //--------------------------------

}  //End of class