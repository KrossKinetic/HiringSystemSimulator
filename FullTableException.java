/**
 * The {@code FullTableException} class represents an exception that is thrown when
 * the hiring table is full and a new applicant cannot be added.
 * 
 * @author Manas Singh
 *    e-mail: anshumaan.singh@stonybrook.edu
 *    Stony Brook ID: 115426564
 *    Recitation : R02
 */
public class FullTableException extends Exception {
    /**
     * Constructs a {@code FullTableException} with no detail message.
     */
    public FullTableException(){
    }

    /**
     * Constructs a {@code FullTableException} with the specified detail message.
     * @param message
     * The detail message.
     */
    public FullTableException(String message){
        super(message);
    }
}
