import java.util.regex.*;

public class EmailValidator {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
    "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"
  );

  public boolean validEmail(String email) {
    Matcher match = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    return match.matches();
  }
}
