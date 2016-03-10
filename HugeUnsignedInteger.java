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
  //Division
  private String tempResult;
  private HugeUnsignedInteger tempArr2;
  
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
          int tempAdd2 = tempArr[j] + tempAdd;
          if(tempAdd2 < 10){
            tempArr[j] = tempAdd2;
          }
          else{
            tempArr[j] = tempArr[j] + (tempAdd2 - 10);
            tempArr[j+1] = tempArr[j+1] + 1;
          }
        }
        else{
          tempArr[j] = tempArr[j] + (tempAdd - 10);
          tempArr[j+1] = tempArr[j+1] + 1;
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
  public String subtraction(HugeUnsignedInteger value2) throws SubtractionException{
    String result = "";
    int tempSize;
    int tempCount;
    //Check for the larger array of the two operands
    if(numDigit > value2.numDigit){
      tempSize = numDigit;
    }
    else{
      tempSize = value2.numDigit;
    }
    //Copy array1 to tempArr
    int[] tempArr = new int[tempSize+1];
    for(i=0; i<tempSize+1; i++){
      if(i < numDigit){
        tempArr[i] = arr[i];
      }
      //Set remaining spaces of tempArr to 0
      else{
        tempArr[i] = 0;
      }
    }
    //Copy array2 to subArr
    int[] subArr = new int[tempSize+1];
    for(i=0; i<tempSize+1; i++){
      if(i < value2.numDigit){
        subArr[i] = value2.arr[i];
      }
      else{
        subArr[i] = 0;
      }
    }
    //Subtract two large int array together
    j=0;
    while(j < tempSize){
      int tempSub = tempArr[j] - subArr[j];
      //Normal subtraction
      if(tempSub >= 0){
        tempArr[j] = tempSub;
      }
      //Need to borrow
      else{
        tempArr[j] = (tempArr[j] + 10) - subArr[j];
        //Check if next number is a 0
        if(tempArr[j+1] < 0){
          //Borrow from next number and set to 9
          tempArr[j+1] = 9;
          tempArr[j+2] = tempArr[j+2] - 1;
        }
        else{
          //Normal borrowing
          tempArr[j+1] = tempArr[j+1] - 1;
        }
      }
      j++;
    }
    //Check if negative number
    if(tempArr[tempSize] < 0){
      throw new SubtractionException("Number is negative");
    }
    else{
      //If have leading zero
      if(tempArr[numDigit-1] == 0 && numDigit-1 != 0){
        tempCount = numDigit-1;
      }
      else{
        tempCount = numDigit;
      }
      for(i=tempCount-1; i>=0; i--){
        result = result + Integer.toString(tempArr[i]);
      }
    }
    //Check if array is all zero
    int zeroCount = 0;
    for(i=0; i<tempCount; i++){
      if(tempArr[i] == 0){
        zeroCount++;
      }
    }
    //If all number in array is zero -> set to zero
    if(zeroCount == tempCount){
      result = "0";
    }
    return result;
  }
  
  //Multiplication Operation
  public String multiplication(HugeUnsignedInteger value2){
    String result = "";
    int tempSize;  //Size of product1
    int tempCount;  //Size for result string
    int productSize;  //Size of product2
    int[] tempArr;
    int[] productArr;
    //Determine the larger digit number
    if(numDigit >= value2.numDigit){
      tempSize = numDigit;
      //Copy array1 to tempArr
      tempArr = new int[tempSize];
      for(i=0; i<tempSize; i++){
        tempArr[i] = arr[i];
      }
      //Copy array2 to productArr
      productSize = value2.numDigit;
      productArr = new int[value2.numDigit];
      for(i=0; i< value2.numDigit; i++){
        productArr[i] = value2.arr[i];
      }
    }
    else{
      tempSize = value2.numDigit;
      //Copy array2 to tempArr
      tempArr = new int[tempSize];
      for(i=0; i<tempSize; i++){
        tempArr[i] = value2.arr[i];
      }
      //Copy array1 to productArr
      productSize = numDigit;
      productArr = new int[numDigit];
      for(i=0; i<numDigit; i++){
        productArr[i] = arr[i];
      }
    }
    //Initialize resultArr
    int[] resultArr = new int[tempSize+productSize];
    for(i=0; i<tempSize+productSize; i++){
      resultArr[i] = 0;
    }
    //Multiply two array (tempArr x productArr)
    for(i=0; i<productSize; i++){  //Go thru productArr
      for(j=0; j<tempSize; j++){  //Go thru tempArr
        int tempProduct = productArr[i] * tempArr[j];
        if(tempProduct < 10){
          int tempAddition = resultArr[i+j] + tempProduct;
          if(tempAddition < 10){
            resultArr[i+j] = tempAddition;
          }
          else{
            resultArr[i+j] = tempAddition - 10;
            resultArr[i+j+1] = resultArr[i+j+1] + 1;
          }
        }
        else{
          //Check if need to carry
          int tempAdd = resultArr[i+j] + (tempProduct % 10);
          if(tempAdd < 10){
            resultArr[i+j] = tempAdd;
          }
          else{
            resultArr[i+j] = tempAdd - 10;
            resultArr[i+j+1] = resultArr[i+j+1] + 1;
          }
          int tempAdd2 = resultArr[i+j+1] + (tempProduct / 10 % 10);
          if(tempAdd2 < 10){
            resultArr[i+j+1] = tempAdd2;
          }
          else{
            resultArr[i+j+1] = tempAdd2 - 10;
            resultArr[i+j+2] =  resultArr[i+j+2] + 1;
          }
          
//          for(int x = 0; x<tempSize+productSize; x++){
//            if(resultArr[x] > 10){
//              resultArr[x] = resultArr[x] - 10;
//              resultArr[x+1] = resultArr[x+1] + 1;
//            }
//          }
//          //Check if need to carry
//          int tempAdd = resultArr[i+j] + (tempProduct % 10);
//          int tempAdd2 = resultArr[i+j+1] + (tempProduct / 10 % 10);
//          resultArr[i+j] = tempAdd;
//          resultArr[i+j+1] = tempAdd2;
//          
//          for(int x = 0; x<tempSize+productSize; x++){
//            if(resultArr[x] > 10){
//              resultArr[x] = resultArr[x] - 10;
//              resultArr[x+1] = resultArr[x+1] + 1;
//            }
//          }
          
        }
      }
    }
    //Remove leading zero
    if(resultArr[tempSize+productSize-1] == 0){
      tempCount = tempSize+productSize-1;
    }
    else{
      tempCount = tempSize+productSize;
    }
    //Convert int array to string
    for(i=tempCount-1; i>=0; i--){
      result = result + Integer.toString(resultArr[i]);
    }
    //Multiplying by 0
    if((tempArr[tempSize-1] == 0 && tempSize == 1) || (productArr[productSize-1] == 0 && productSize == 1)){
      result = "0";
    }
    
    return result;
  }
  
//  //Division Operation
//  public String division(HugeUnsignedInteger value2){
//    String result = "";
//    int tempSize = value2.numDigit;
//    int dividendNum;  //Number of digit in the dividend
//    int[] tempArr;
//    int[] quotientArr;  //Answer array
//    int[] dividendArr;  //Current number being subtracted (part of the dividend)
//    int x, t, z;
//    int stringCount;
//    
//    HugeUnsignedInteger dividendInt;
//    //HugeUnsignedInteger tempInt = new HugeUnsignedInteger(value);  //copy of value1;
//    //Check if divisor is bigger than dividend
//    if(numDigit < value2.numDigit){
//      result = "0";
//      return result;
//    }
//    //Same number of digit
//    else if(numDigit == value2.numDigit){      
//      for(i=numDigit-1; i>=0; i--){
//        if(arr[i] > value2.arr[i]){
//          break;
//        }
//        else if(arr[i] < value2.arr[i]){
//          result = "0";
//          return result;
//        }
//      }
//    }
//    //Initialize quotient array
//    quotientArr = new int[numDigit];
//    for(i=0; i<numDigit; i++){
//      quotientArr[i] = 0;
//    }
//    //Initialize dividend array
//    dividendNum = value2.numDigit;
//    dividendArr = new int[dividendNum];
//    for(i=0; i<dividendNum; i++){
//      dividendArr[i] = -1;  //-1 marked empty cell
//    }
//    //Dividing two huge unsigned integer
//    t = numDigit-1;
//    while(t >= 0){
//      //Count for valid number in the dividend array (anything except -1)
//      int validCount = 0;  //Count for valid number (not -1)
//      for(j=0; j<dividendNum; j++){
//        if(dividendArr[j] != -1){
//          validCount++;
//        }
//      }
//      //Making sure that the dividend array have same number as divisor
//      if(validCount < value2.numDigit){
//        //Place in the next avaliable cell
//        for(x=dividendNum-1; x>=0; i--){
//          if(dividendArr[x] == -1){
//            dividendArr[x] = arr[i];
//            break;
//          }
//        }
//      }
//      //Same length - check if divisor is bigger than dividend
//      else if(validCount == value2.numDigit){
//        for(i=value2.numDigit-1; i>=0; i--){
//          if(dividendArr[i] > value2.arr[i]){
//            tempSize = dividendNum;
//            break;
//          }
//          else if(dividendArr[i] < value2.arr[i]){
//            //Allocate a bigger array and bring another number down
//            tempSize = dividendNum + 1;
//            break;
//          }
//        }
//        //Decide to increase new array size or not
//        if(tempSize > dividendNum){
//          tempArr = new int[tempSize];
//          //Copy dividend content to temp array
//          for(z=dividendNum-1; z>=0; z--){
//            tempArr[z+1] = dividendArr[z];
//          }
//          //Bring down another number from array1
//          tempArr[0] = arr[t--];  
//        }
//        else{
//          tempArr = new int[tempSize];
//        }
//        //Convert array to string
//        String dividendString = "";
//        for(stringCount = tempSize-1; stringCount >= 0; stringCount--){
//          dividendString = dividendString + Integer.toString(tempArr[stringCount]);
//        }
//        //Loop subtraction
//        int looping = 1;
//        while(looping == 1){
//          try{
//            dividendInt = new HugeUnsignedInteger(dividendString);
//            tempResult = dividendInt.subtraction(value2);
//            dividendInt = new HugeUnsignedInteger(tempResult);  //result of subtracting
//            quotientArr[t] = quotientArr[t] + 1;  //Increment amound of time subtracted
//            //Check if dividend is smaller than divisor
//            if(dividendInt.numDigit < value2.numDigit){
//              tempArr2 = new HugeUnsignedInteger(tempResult);
//              break;
//            }
//            else if(dividendInt.numDigit == value2.numDigit){
//              for(int d3 = dividendInt.numDigit-1; d3>=0; d3--){
//                if(dividendInt.arr[d3] > value2.arr[d3]){
//                  break;
//                }
//                else if(dividendInt.arr[d3] < value2.arr[d3]){
//                  looping = 0;
//                  break;
//                }
//              }
//            }
//            else if(dividendInt.arr[dividendInt.numDigit-1] == 0 && dividendInt.numDigit == 1){
//              break;
//            }
//            
//          }
//          catch(SubtractionException e){
//            //Restore to original
//            //tempResult = dividendInt.addition(value2);
//            //tempArr2 = new HugeUnsignedInteger(tempResult);
//            break;
//          }
//        }
//        //Clear dividend array
//        int dd;
//        for(dd =0; dd<dividendNum; dd++){
//          dividendArr[dd] = -1;
//        }
//        //Put the leftover of subtraction to dividend array
//        tempArr2 = new HugeUnsignedInteger(tempResult);
//        int d1,d2;
//        //Single 0
//        if(!(tempArr2.arr[tempArr2.numDigit-1] == 0 && tempArr2.numDigit == 1)){
//          for(d1 = tempArr2.numDigit-1; d1>=0; d1--){
//            for(d2 = dividendNum-1; d2 >= 0; d2--){
//              if(dividendArr[d2] == -1){
//                dividendArr[d2] = tempArr2.arr[d1];
//                break;
//              }
//            }
//          }
//        }
//      }
//      
//      if(t >= 0){
//        t--;  //?
//      }
//    }
//    
//    return result;
//  }
  
  public String division(HugeUnsignedInteger value2){
    String result = "";
    int tempSize;
    int answerCount = 0;  //Keep track of the quotient
    int t = 1;  //Infinite loop
    HugeUnsignedInteger tempInt = new HugeUnsignedInteger(value);  //copy of value1;
    //HugeUnsignedInteger valueCopy = new HugeUnsignedInteger(value);  //copy of value1
    if(tempInt.numDigit < value2.numDigit){
      result = "0";
      return result;
    }
    else if(tempInt.numDigit == value2.numDigit){      
      for(i=tempInt.numDigit-1; i>=0; i--){
        if(tempInt.arr[i] > value2.arr[i]){
          //Set flag to jump to next statement
          //t = 1;
          break;
        }
        else if(tempInt.arr[i] < value2.arr[i]){
          result = "0";
          return result;
        }
        //t = 1;
      }
    
    }
    //if(t== 1){
      while(t == 1){
        try{
          //Subtract array2(divisor) from array1(dividend)
          String tempResult = tempInt.subtraction(value2);
          tempInt = new HugeUnsignedInteger(tempResult);
          answerCount++;
          //Check if dividend is smaller than divisor
          if(tempInt.numDigit < value2.numDigit){
            result = Integer.toString(answerCount);
            //System.out.println("FIRST");
            return result;
          }
          else if(tempInt.numDigit == value2.numDigit){
//            for(i=tempInt.numDigit-1; i>=0; i--){
//              if(tempInt.arr[i] < value2.arr[i]){
//                result = Integer.toString(answerCount);
//                System.out.println("SECOND");
//                return result;
//              }
//            }
            for(i=tempInt.numDigit-1; i>=0; i--){
              if(tempInt.arr[i] > value2.arr[i]){
                break;
              }
              else if(tempInt.arr[i] < value2.arr[i]){
                result = Integer.toString(answerCount);
                //System.out.println("SECOND");
                return result;
              }
            }
            
          }
          else if(tempInt.arr[tempInt.numDigit-1] == 0 && tempInt.numDigit == 1){
            result = Integer.toString(answerCount);
            //System.out.println("THIRD");
            return result;
          }
        }
        catch(SubtractionException e){
          //If negative number
          answerCount++;
          result = Integer.toString(answerCount);
          //System.out.println("FOURTH");
          return result;
        }
      }
    //}
    //result = "1";
    //System.out.println("LAST");
    return result;
  }
  
  //Modulus Operation
  public String modulus(HugeUnsignedInteger value2){
    String tempDivide = division(value2);
    HugeUnsignedInteger x1 = new HugeUnsignedInteger(tempDivide);
    String result = "";
    String tempMultiply = value2.multiplication(x1);
    x1 = new HugeUnsignedInteger(tempMultiply);
    try{
      result = subtraction(x1);
    }
    catch(SubtractionException e){
      System.out.print("Negative subtraction");
    }
    return result;
  }
  
  //Relational Operation - Equal To
  public int equalTo(HugeUnsignedInteger value2){
    if(numDigit == value2.numDigit){
      for(i=numDigit-1; i>=0; i--){
        if(arr[i] != value2.arr[i]){
          return 0;
        }
      }
      return 1;  //Equal
    }
    return 0;  //Not equal
  }
  
  //Relational Operation - Greater Than
  public int greaterThan(HugeUnsignedInteger value2){
    if(numDigit > value2.numDigit){
      return 1;
    }
    else if(numDigit == value2.numDigit){
      for(i=numDigit-1; i>=0; i--){
        if(arr[i] > value2.arr[i]){
          return 1;
        }
      }
    }
    else{
      return 0;
    }
    return 0;
  }
  
  //Relational Operation - Less than
  public int lessThan(HugeUnsignedInteger value2){
    if(numDigit < value2.numDigit){
      return 1;
    }
    else if(numDigit == value2.numDigit){
      for(i=numDigit-1; i>=0; i--){
        if(arr[i] < value2.arr[i]){
          return 1;
        }
      }
    }
    else{
      return 0;
    }
    return 0;
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

 //Subtraction Exception
  class SubtractionException extends Exception{
    public SubtractionException(String msg){
      super(msg);
    }
  }