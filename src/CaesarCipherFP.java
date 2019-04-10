import java.util.Scanner;

public class CaesarCipherFP {
  public static void main(String[] args) {

    // devlares variables and scanner
    int var;
    Scanner scan = new Scanner(System.in);

    // asks user for string input

    System.out.println("Enter a string to encrypt: ");
    String code = scan.nextLine();

    //asks how many letters to change string too

    System.out.println("On the interval 1-25, please select an amount to change: ");
    var = scan.nextInt();

    // case where a variable out of bounds is corrected

    while (var < 1 || var > 25) {
      System.out.println("On the interval 1-25, please select an amount to change: ");
      var = scan.nextInt();
    }

    char[] arr = code.toCharArray();
    String input;

    // verify encryption

    System.out.println("Would you like to encrypt this message? (y/n): ");
    input = scan.next();
    //meassage verification

    if (input.equals("y") == true || input.equals("Y") == true) {
      System.out.println("\nEncrypting your message...");
      encrypt(arr, code, var);
    } else {
      System.out.println("Thanks for using the program!");
    }

    System.out.println("\n" + "\nWould you like to decrypt this message? (y/n): ");
    input = scan.next();
    //input to decrypt

    if (input.equals("y") == true || input.equals("Y") == true) {
      System.out.println("\nDecrypting your message...");
      decrypt(var);
    } else {
      System.out.println("Thanks for using the program!");
    }
    // calls the decrypt method
  }

  static String code2;
  static int var2;
  static char b;
  static char[] arr2;

  // variables to do methods

  	static void encrypt(char[] arr, String code, int var) {
  		code2 = code;
  		var2 = var;
  		arr2 = arr;

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
  			System.out.print(arr2[o]);
  		}
  	}

  	// encrypt storing new message in array

  	static void decrypt(int var) {
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
  			System.out.print(arr2[p]);
  		}
  		System.out.println();
  	}

  	// decrypts the message in an arrray into another array
  }
