/** Tan Le (tle51) & Janki Patel (jpate53)
 * CS 342 - Project 3: RSA Encryption/Decryption
 * Rsa Algorithm Class - Implement RSA Algorithm
 */
import java.io.*;
import java.lang.*;

public class RsaAlgorithm
{
    private int e;
    private int d;
    private String n;
    private String p;
    private String q;
    private String phi;
    private HugeUnsignedInteger hui1;
    private HugeUnsignedInteger hui2;
    
    public RsaAlgorithm(String prime1, String prime2)
    {
        p = prime1;
        q = prime2;
        
        System.out.println(p);
        System.out.println(q);
        
        generatePhi();
        generateN();
    }
    
    //phi = (p-1) * (q-1)
    public void generatePhi()
    {
        hui1 = new HugeUnsignedInteger(p);
        hui2 = new HugeUnsignedInteger(q);
        
        //HugeInt object of value 1
        HugeUnsignedInteger one = new HugeUnsignedInteger("1");
        
        String x = "";
        String y = "";
        try{
            x = hui1.subtraction(one); //p-1
            y = hui2.subtraction(one); //q-1
        }
        catch(SubtractionException e){
            System.err.println("Subtraction result is a negative number");
        }
        
        //HUI for p-1 and q-1 value
        HugeUnsignedInteger p_1 = new HugeUnsignedInteger(x);
        HugeUnsignedInteger p_2 = new HugeUnsignedInteger(y);
        
        //phi = (p-1) * (q-1)
        phi = p_1.multiplication(p_2);
    }
    
    //get the value of phi
    public String getPhi()
    {
        return phi;
    }
    
    // n = p * q
    public void generateN()
    {
        hui1 = new HugeUnsignedInteger(p);
        hui2 = new HugeUnsignedInteger(q);
        
        n = hui1.multiplication(hui2);
        System.out.println(n);
    }
    
    //get the value of n
    public String getN()
    {
        return n;
    }
    
    public void generateE()
    {
        
    }
    
    //Inverse of e mod phi i.e. (1+k*phi)/e
    public void generateD()
    {
        //Inverse of e mod phi i.e. (1+k*phi)/e
        //Let k = 2
        public void generateD()
        {
            hui1 = new HugeUnsignedInteger(phi);
            hui2 = new HugeUnsignedInteger("2");
            
            String temp = hui1.multiplication(hui2); //Stores k * phi
            
            //HUI obj for 1 and k*phi value
            one = new HugeUnsignedInteger("1");
            HugeUnsignedInteger inverseHUI = new HugeUnsignedInteger(temp);
            
            //compute 1+*k*phi)
            String inverse = inverseHUI.addition(one);
            
            //Make numerator and denominator HUI
            HugeUnsignedInteger numerator = new HugeUnsignedInteger(inverse);
            HugeUnsignedInteger denominator = new HugeUnsignedInteger(e);
            
            d = numerator.division(denominator); 
        }
    }
    
}//End of class
