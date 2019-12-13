package edu.clarkson.ee408project;

public class MyException extends RuntimeException
{
    String message;
    Throwable cause;

    public MyException() {
        super();
    }

    public MyException(String message, Throwable cause)
    {
        super(message, cause);

        this.cause = cause;
        this.message = message;
    }
}
