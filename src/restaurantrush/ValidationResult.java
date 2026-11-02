package restaurantrush;

/** Owner: P4. Says whether a chosen staff task is allowed, and why not. */
public final class ValidationResult {
    private final boolean valid;
    private final String message;

    private ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public static ValidationResult ok() { return new ValidationResult(true, ""); }
    public static ValidationResult fail(String msg) { return new ValidationResult(false, msg); }

    public boolean isValid() { return valid; }
    public String getMessage() { return message; }
}
