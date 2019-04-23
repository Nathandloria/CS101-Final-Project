import java.util.Scanner;

public class AlgorithmTwo {

  private String code2;
  private int var2;
  private char b;
  private char[] arr2;

  public String encrypt(String code, int var) {
    code2 = code;
    var2 = var;
    arr2 = code2.toCharArray();

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
    code2 = new String(arr2);
    return code2;
  }

  public String decrypt(String code, int var) {
    code2 = code;
    var2 = var;
    arr2 = code2.toCharArray();

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
    code2 =  new String(arr2);
    return code2;
  }
}
