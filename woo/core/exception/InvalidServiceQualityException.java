package woo.core.exception;

/** Exception for invalid service level. */
public class InvalidServiceQualityException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192337L;
  String _string;

  public InvalidServiceQualityException(String s) {
    _string = s;
  }

  public String getInvalidString() {
  	return _string;
  }
}