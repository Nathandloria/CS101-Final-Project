import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

/** This is a simple encrption method to encript a string by shifting it 100
*   characters by the acii chart.
*   @author Adam Cook
*/

public class AlgorithmOne{

  public String encript(String message){
    char[] a = message.toCharArray();
      int key = 150;  // Shift key for CaesarCipher
      // Encripts by shifting position of each char by key
      for (int i=0; i<message.length(); i++) {
        char temp = a[i];
        int numTemp = temp;
        int encriptedNumTemp = (numTemp + key);
        a[i] = (char)encriptedNumTemp;
      }
      //System.out.println(Arrays.toString(a)); //Displays encripted message
      return Arrays.toString(a);
    }

    public String unencript(String message) {
      int key = 150;  // Shift key for CaesarCipher
      // Encripts by shifting position of each char by key
      for (int i=0; i<message.length(); i++) {
        char temp = a[i];
        int numTemp = temp;
        int unencriptedNumTemp = (numTemp - key);
        a[i] = (char)unencriptedNumTemp;
      }
      //System.out.println(Arrays.toString(a)); //Displays encripted message
      return Arrays.toString(a);
    }
    }

  }
