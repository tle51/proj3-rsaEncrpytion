/** Tan Le (tle51) & Janki Patel (jpate53)
  * CS 342 - Project 3: RSA Encryption/Decryption
  * Huge-Unsigned-Integer Class - Hold very large unsigned integer values and include operations
  */
import java.io.*;
import java.lang.*;

public class HugeUnsignedInteger{
  private String value;
  private int[] arr;
  private int numDigit;
  private int i, j;
  
  //Constructor
  public HugeUnsignedInteger(String input){
    value = input;
    toArray();
  }
  
  //Covert value to array
  public void toArray(){
    numDigit = value.length();  //Count number of digits
    arr = new int[numDigit];  //Dynamically allocate array space
    
    //Store value to array
    StringReader strRead = new StringReader(value);
    try{
      for(i=numDigit-1; i>=0; i--){
        char c = (char) strRead.read();
        arr[i] = Character.getNumericValue(c);
      }
    }
    catch(IOException e){
      System.err.println(e);
    }
  }
  
  //Addition Operation
  public String addition(HugeUnsignedInteger value2){
    String result = "";
    int tempSize;
    int tempCount;
    //Check for the larger array size of the two operands
    if(numDigit > value2.numDigit){
      tempSize = numDigit;
    }
    else{
      tempSize = value2.numDigit;
    }
    //Initialize temp array to zero
    int[] tempArr = new int[tempSize+1];
    for(i=0; i<tempSize+1; i++){
      tempArr[i] = 0;
    }
    //Add two large int array together
    j=0;
    while(j < value2.numDigit || j < numDigit){
      //If end of array1 -> copy rest of array2 contents
      if(j >= numDigit){
        tempArr[j] = tempArr[j] + value2.arr[j];
      }
      //If end of array2 -> copy rest of array1 contents
      else if(j >= value2.numDigit){
        tempArr[j] = tempArr[j] + arr[j];
      }
      else{
        int tempAdd = arr[j] + value2.arr[j];
        //Addition result is less than 10
        if(tempAdd < 10){
          tempArr[j] = tempAdd;
        }
        else{
          tempArr[j] = tempAdd - 10;
          tempArr[j+1] = 1;
        }
      }
      j++;
    }
    //If there is an extra leading zero in the tempArr
    if(tempArr[tempSize] == 0){
      //Remove the extra space
      tempCount = tempSize;
    }
    else{
      tempCount = tempSize+1;
    }
    //Convert int array to string
    for(i=tempCount-1; i>=0; i--){
      result = result + Integer.toString(tempArr[i]);
    }
    
    return result;
  }
  
  //Subtraction Operation
  public String subtraction(HugeUnsignedInteger value2){
    
  }
  
  //------------Test----------------
  public int getDigit(){
    return numDigit;
  }
  public void printValue(){
    System.out.println(value);
    for(i=0; i<numDigit; i++){
      System.out.print(arr[i]);
    }
  }
  //--------------------------------

}  //End of class