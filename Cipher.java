public class Cipher {

  private String pass;
  private String encryptedPass;

  public String encryptString(String someString) {
    String ret = new String();
    ret = "";
    for (int i = 0; i < someString.length(); i++) {
      if (someString.charAt(i) >= 'a' && someString.charAt(i) <= 'z') {
        char curChar = (char) ((((someString.charAt(i) - 'a') + 3) % 26) + 'a');
        ret += curChar;
      } else if (someString.charAt(i) >= 'A' && someString.charAt(i) <= 'Z') {
        char curChar = (char) ((((someString.charAt(i) - 'A') + 3) % 26) + 'A');
        ret += curChar;
      } else {
        ret += (char) (((int) someString.charAt(i) - '0' + 3) % 10 + '0');
      }
    }
    return ret;
  }

  public String decryptString(String someString) {
    String ret = new String();
    ret = "";

    for (int i = 0; i < someString.length(); i++) {
      if (someString.charAt(i) >= 'a' && someString.charAt(i) <= 'z') {
        char curChar = (char) (
          (((someString.charAt(i) - 'a') + 23) % 26) + 'a'
        );
        ret += curChar;
      } else if (someString.charAt(i) >= 'A' && someString.charAt(i) <= 'Z') {
        char curChar = (char) (
          (((someString.charAt(i) - 'A') + 23) % 26) + 'A'
        );
        ret += curChar;
      } else {
        ret += (char) (((int) someString.charAt(i) - '0' + 6) % 10 + '0');
      }
    }
    return ret;
  }
}
