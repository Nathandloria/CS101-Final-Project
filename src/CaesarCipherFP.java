import java.util.Scanner;

public class CaesarCipherFP {

  private String code2;
  private int var2;
  private char b;
  private char[] arr2;

  	public String encrypt(String code, int var) {
  		code2 = code;
  		var2 = var;
  		arr2 = new char[code2.length];

  		System.out.println("\nNew Message: ");
  		for (int o = 0; o < arr2.length; o++) {
  			if (Character.isLetter(code2.charAt(o)) == false) {
  				b = (char)((int)code2.charAt(o));
  			} else {
  				if (Character.isUpperCase(code2.charAt(o))){
  					b = (char)(((int)code2.charAt(o) + var2 - 65) % 26 + 65);
  					arr2[o] = b;
  				} else {
  					b = (char)(((int)code2.charAt(o) + var2 - 97) % 26 + 97);
  					arr2[o] = b;
  				}
  			}

  		}
      return code2 = String(arr2);
  	}

  	// encrypt storing new message in array

  	public String decrypt(int var) {
  		code2 = String.copyValueOf(arr2);
  		var2 = var;

  		System.out.println("\nOriginal Message: ");
  		for (int p = 0; p < arr2.length; p++) {
  			if (Character.isLetter(code2.charAt(p)) == false) {
  				b = (char)((int)code2.charAt(p));
  			} else {
  				if (Character.isUpperCase(code2.charAt(p))){
  					b = (char)(((int)code2.charAt(p) - var2 + 65) % 26 + 65);
  					arr2[p] = b;
  				} else {
  					b = (char)(((int)code2.charAt(p) - var2 + 111) % 26 + 97);
  					arr2[p] = b;
  				}
  			}

  		}
      return code2 = String(arr2);
  	}


  	// decrypts the message in an arrray into another array
  }
