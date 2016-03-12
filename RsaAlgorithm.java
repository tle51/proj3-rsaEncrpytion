/** Tan Le (tle51) & Janki Patel (jpate53)
 * CS 342 - Project 3: RSA Encryption/Decryption
 * Rsa Algorithm Class - Implement RSA Algorithm
 */
import java.io.*;
import java.lang.*;

public class RsaAlgorithm
{
    private String e, d, n, p, q, phi;
    private HugeUnsignedInteger hui1;
    private HugeUnsignedInteger hui2;
    private HugeUnsignedInteger one;
    
    public RsaAlgorithm(String prime1, String prime2)
    {
        p = prime1;
        q = prime2;
        
        System.out.println(p);
        System.out.println(q);
        
        generatePhi();
        generateN();
        generateE();
    }
    
    //phi = (p-1) * (q-1)
    public void generatePhi()
    {
        hui1 = new HugeUnsignedInteger(p);
        hui2 = new HugeUnsignedInteger(q);
        
        //HugeInt object of value 1
        one = new HugeUnsignedInteger("1");
        
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
        System.out.println("phi is" + phi);
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
        hui2 = new HugeUnsignedInteger(p);
        
        n = hui1.multiplication(hui2);
        //n = hui1.modulus(hui2);
        System.out.println("Value of n: " + n);
    }
    
    //get the value of n
    public String getN()
    {
        return n;
    }
    
    //Check if the number is GCD using Euclidean Algorithm
    public int isGcd(String s)
    {
        System.out.println("in isGCD");
        //		hui1 = new HugeUnsignedInteger(s);
        //		HugeUnsignedInteger phiHui = new HugeUnsignedInteger(phi);
        String temp = "0";
        String phiTemp = phi; //set the value of phi to a temp variable
        System.out.println(phiTemp);
        
        while(!phi.equals("0"))
        {
            System.out.println("in gcd while" + phi);
            //objects for s and phi
            hui1 = new HugeUnsignedInteger(s);
            HugeUnsignedInteger phiHui = new HugeUnsignedInteger(phi);
            temp = phi;
            phi = hui1.modulus(phiHui);   //e%phi
            System.out.println("after mod call in gcd " + phi);
            s = temp; //update s
            
            System.out.println("s is" + s + "temp is" + temp);
        }
        System.out.println("end of isGCD" + temp);
        
        return Integer.parseInt(s);
    }
    
    //e is less than n and relative prime to phi
    public void generateE()
    {
        //Assign e to 3 because it is guaranteed to be less than n
        e = "3";
        hui1 = new HugeUnsignedInteger(n);
        one = new HugeUnsignedInteger("1");
        int temp = Integer.parseInt(e);
        
        if(isGcd(e) == 1)
        {
            System.out.println("if gcd2 = 1");
            e = Integer.toString(temp);
            System.out.println("if gcd2 = 1" + e);
        }
        else
        {
            int i = 3;
            while(isGcd(e) != 1)
            {
                temp = (Integer.parseInt(e)) + 1;
                System.out.println("temp is " + temp);
                e = Integer.toString(temp);
                System.out.println("e in whiel  " + e);
                i++;
            }
        }
        
        System.out.println("e val is " + e);
    }
    
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
    
    //get the value of d
    public String getD()
    {
        return d;
    }
}//End of class