package projecta07.exception;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}