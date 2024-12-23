/**
 * The {@code ApplicantNotFoundException} class represents an exception that is thrown when
 * an applicant is not found in the hiring table.
 */
public class ApplicantNotFoundException extends Exception {
    /**
     * Constructs an {@code ApplicantNotFoundException} with no detail message.
     */
    public ApplicantNotFoundException(){
    }

    /**
     * Constructs an {@code ApplicantNotFoundException} with the specified detail message.
     * @param message
     * The detail message.
     */
    public ApplicantNotFoundException(String message){
        super(message);
    }
}

