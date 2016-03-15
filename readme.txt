Tan Le (tle51) & Janki Patel (jpate53)
CS 342 - Project 3: RSA Encryption/Decryption
Readme

We got the algorithm to check for prime number from the following website:
http://crunchify.com/in-java-how-to-find-if-number-is-prime-or-not-best-way-to-generate-prime-number-in-java/

We used the following website to get the algorithm for GCD:
http://introcs.cs.princeton.edu/java/23recursion/Euclid.java.html

How to Use This Program:
- If a user is prompt to enter any file names, they can enter it with or without the file extension. 
  (e.g. “id_rsa.pub” or id_rsa.pub.txt”.
- There are 2 text fields provided to enter prime numbers. 
- If you don’t enter the prime numbers, and select “Create Key” option from the RSA dropdown menu, 
  then the computer will generate two prime numbers from the text file called “primeNumbers.rsc”.
  The path of this file is TODO.
	- Then the user is prompt to enter file names of public and private key. 
- One the key has been created, you can make a block file.
	- First, the user is prompt to enter a file name that contains a message that needs to be blocked.
	- Then, the user is prompt to enter a file name where the block will be saved.
	- Finally, the user is asked to enter a block size. Since the block size is deepened on the length 	  of the prime number, we have given some suggestions when the user is prompted for the block size.
- When the “Encrypt” menu is selected, the user is asked to enter a file name of the public key.
  	- The encrypted message gets stored in a file called “encrypt”.
- To decrypt a file, enter a file of of the private key. 
- The decryption class uses the “encrypt” file to decrypt the message. The decrypted message gets stored
  into a file called “decrypt.txt”.
- To unblock the decryption file, enter “decrypt.txt”. 
