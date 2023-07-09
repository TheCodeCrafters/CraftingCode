package io.github.thecodecrafters.craftingcode.langapi;

/**
 * An exception/error that happened in the language
 */
public class WrappedException extends VmException {
    private final String traceback;

    public WrappedException(Throwable cause) {
        super(cause);
        this.traceback = null;
    }

    public WrappedException(String message, String traceback) {
        super(message);
        this.traceback = traceback;
    }

    public WrappedException(String message, String traceback, Throwable cause) {
        super(message, cause);
        this.traceback = traceback;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (traceback != null)
            if (message != null)
                return message + '\n' + traceback;
            else
                return traceback;
        else
            return message;

    }
}
