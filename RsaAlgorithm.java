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
	private int n;
	private String p;
	private String q;
	private int phi;
	private HugeUnsignedInteger hui;
	
	public RsaAlgorithm(String prime1, String prime2)
	{
		p = prime1;
		q = prime2;
	}
	
	/* (e,n) will compose the public-key */
	
	/* (d,n) will compose the private-key */
	
	//phi = (p-1) * (q-1)
	public void generatePhi()
	{
		hui = new HugeUnsignedInteger(p);
		
	}
	
	// n = p * q
	public void generateN()
	{
		
	}
	
	
	public void generateE()
	{
		
	}
	
	//Inverse of e mod phi i.e. (1+k*phi)/e
	public void generateD()
	{
		
	}
	
}//End of class
