package learnspring.springbootdemo.TODO.exception;

public class IncorrectTaskDetailsException extends Exception{
    public IncorrectTaskDetailsException() {
    }

    public IncorrectTaskDetailsException(String message) {
        super(message);
    }

    public IncorrectTaskDetailsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectTaskDetailsException(Throwable cause) {
        super(cause);
    }

    public IncorrectTaskDetailsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
