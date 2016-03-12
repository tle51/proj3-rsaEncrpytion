/** Tan Le (tle51) & Janki Patel (jpate53)
  * CS 342 - Project 3: RSA Encryption/Decryption
  * Encryption Class - Encrypt a block file using a public key
  */

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Decryption{
  private HugeUnsignedInteger d;
  private HugeUnsignedInteger n;
  private int i;
  private String privateName;  
  private File privateFile;  //Private key file
  private String eName = "encrypt.txt";
  private String dName = "decrypt.txt";
  private File eFile = new File(eName);  //Encrypted File
  private File dFile = new File(dName);  //Decrypted File
  
  //Constructor
  public Decryption(String filePath){
    privateName = filePath;
    privateFile = new File(privateName);
    readXML();
    decrypt();
  }
  
  //Read XML File
  public void readXML(){
    try{
      DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = docBuilder.newDocumentBuilder();
      Document doc = builder.parse(privateFile);
      NodeList nList = doc.getElementsByTagName("rsakey");
      String tempD, tempN;
      for(i=0; i<nList.getLength(); i++){
        Node node = nList.item(i);
        if(node.getNodeType() == Node.ELEMENT_NODE){
          Element elementValue = (Element) node;
          //Get e value
          tempD = elementValue.getElementsByTagName("dvalue").item(0).getTextContent();
          d = new HugeUnsignedInteger(tempD);
          //Get n value
          tempN = elementValue.getElementsByTagName("nvalue").item(0).getTextContent();
          n = new HugeUnsignedInteger(tempN);

          System.out.println("d: " + d.value);
          System.out.println("n: " + n.value);
        }
      }
    }
    catch(Exception e){
      System.err.println(e);
    }
  }
  
  //Decrpyt the file
  public void decrypt(){
    String tempString = "";
    HugeUnsignedInteger inputNumber;
    HugeUnsignedInteger outputNumber;
    HugeUnsignedInteger tempNumber;
    String resultString;
    int intD = Integer.parseInt(d.value);
    //Read each block
    try{
      BufferedReader fRead = new BufferedReader(new FileReader(eFile));
      
      //Output File
      if(dFile.exists()){
        dFile.delete();
      }
      dFile.createNewFile();
      BufferedWriter fWrite = new BufferedWriter(new FileWriter(dFile, true));
      
      int c;
      while((tempString = fRead.readLine()) != null){
        //Convert to HugeUnsignedInteger
        inputNumber = new HugeUnsignedInteger(tempString);
        //C=M^d mod n
        outputNumber = new HugeUnsignedInteger("1");  //C = 1
        for(i=0; i<intD; i++){
          resultString = outputNumber.multiplication(inputNumber);
          System.out.println("1: "+resultString);
          tempNumber =  new HugeUnsignedInteger(resultString);
          resultString = tempNumber.modulus(n);
          System.out.println("2: "+resultString);
          outputNumber =  new HugeUnsignedInteger(resultString);
        }
        //Write to file
        System.out.println(outputNumber.value);
        fWrite.write(outputNumber.value);
        fWrite.newLine();
      }
      fWrite.close();
    }
    catch(IOException e){
      System.err.println(e);
    }
  }
  
  //-----Test------
  public static void main(String[] args){
    Decryption dd = new Decryption("rsakey2.txt");
  }
  
}