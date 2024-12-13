/**
 * The {@code ApplicantNotFoundException} class represents an exception that is thrown when
 * an applicant is not found in the hiring table.
 * 
 * @author Manas Singh
 *    e-mail: anshumaan.singh@stonybrook.edu
 *    Stony Brook ID: 115426564
 *    Recitation : R02
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

