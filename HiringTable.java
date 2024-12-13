/**
* The {@code HiringTable} class represents a table of job applicants.
* It stores an array of {@code Applicant} objects and provides methods for
* adding, removing, retrieving, and searching for applicants.
* @author Manas Singh 
*    e-mail: anshumaan.singh@stonybrook.edu
*    Stony Brook ID: 115426564
*    Recitation : R02
**/

public class HiringTable implements Cloneable{
    public static final int MAX_SKILLS = 3;
    public static final int MAX_COMPANIES = 3;
    public static final int MAX_APPLICANTS = 50;

    private int cursor_applicant_index = 0;
    private Applicant[] data = new Applicant[MAX_APPLICANTS];

    /**
     * Constructs a {@code HiringTable} object with the specified array of applicants.
     *
     * @param applicants
     * An array of {@code Applicant} objects.
     */
    public HiringTable(Applicant[] applicants){
        this.data = applicants;
    }

    /**
     * Constructs an empty {@code HiringTable} object.
     * @custom.postcondition
     * The HiringTable has been initialized to an empty list of Applicants.
     */
    public HiringTable(){
    }

    /**
     * Returns the number of applicants in the hiring table.
     * @return 
     * The number of applicants.
     * @custom.preconditions
     * The HiringTable has been instantiated.
     */
    public int size(){
        return cursor_applicant_index;
    }

    /**
     * Adds an applicant to the hiring table.
     *
     * @param newApplicant 
     * The {@code Applicant} object to add.
     * @throws FullTableException 
     * if the table is full, throw a FullTableException.
     * @custom.preconditions
     * This Applicant object has been instantiated and the number of applicants in the HiringTable is less than MAX_APPLICANTS.
     * @custom.postconditions
     * The new Applicant is now inserted at the end of the list.
     */
    public void addApplicant(Applicant newApplicant) throws FullTableException{
        if (data.length <= MAX_APPLICANTS){
            data[cursor_applicant_index] = newApplicant;
            cursor_applicant_index++;
        } else throw new FullTableException("Table is full");
    }

    /**
     * Removes an applicant from the hiring table.
     *
     * @param applicantName 
     * The name of the applicant to remove.
     * @throws ApplicantNotFoundException 
     * if the applicant is not found, throw a custom ApplicantNotFoundException.
     * @custom.preconditions
     * The HiringTable has been instantiated.
     * @custom.postconditions
     * The Applicant with the name given has been removed from the list. Any Applicant that was in a spot after the removed Applicant is shifted upwards one spot. To put simply, make sure that there
     * are no gaps in the array when removing an applicant. For example:
     * 1) [Bob][Steve][Dave][Carmen]
     * 2) Remove Steve
     * 3) Any objects after Steve should be moved up like so: [Bob][Dave][Carmen]
     */
    public void removeApplicant(String applicantName) throws ApplicantNotFoundException{
        int found_app_ind = LinearSearchIterative(applicantName);
        if (found_app_ind != -1){
            for (int i = found_app_ind; i < cursor_applicant_index - 1; i++) {
                data[i] = data[i + 1];
            }
            data[cursor_applicant_index - 1] = null; 
            cursor_applicant_index--;
        } else {
            throw new ApplicantNotFoundException("Applicant Not Found");
        }
    }

    /**
     * Retrieves an applicant from the hiring table.
     *
     * @param applicantName 
     * The name of the applicant to retrieve.
     * @return 
     * The {@code Applicant} object if found.
     * @throws ApplicantNotFoundException 
     * if the applicant is not found, throws a custom ApplicantNotFoundException.
     * @custom.precondition
     * The HiringTable object has been instantiated
     * 
     */
    public Applicant getApplicant(String applicantName) throws ApplicantNotFoundException{
        int found_app_ind = LinearSearchIterative(applicantName);
        if (found_app_ind != -1){
            return data[found_app_ind];
        }
        throw new ApplicantNotFoundException("Applicant Not Found");
    }

    /**
     * Performs a linear search for an applicant by name.
     *
     * @param name 
     * The name of the applicant to search for.
     * @return 
     * The index of the applicant in the array, or -1 if not found.
     */
    public int LinearSearchIterative(String name) {
        for (int i = 0; i < cursor_applicant_index; i++){
            if (data[i].getApplicantName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Performs a refined search for applicants based on the specified criteria.
     *
     * @param table      
     * The {@code HiringTable} object to search.
     * @param company   
     * The company to filter by (optional).
     * @param skill     
     * The skill to filter by (optional).
     * @param college  
     * The college to filter by (optional).
     * @param gpa       
     * The minimum GPA to filter by (optional).
     * @custom.precondition
     * The HiringTable object has been instantiated
     * @custom.postconditon
     * Displays a neatly formatted table of each Applicant filtered from the HiringTable.
     */
    public static void refineSearch(HiringTable table, String company, String skill, String college, double gpa){
        Applicant[] found_Applicants = new Applicant[MAX_APPLICANTS];
        int applicant_index = table.cursor_applicant_index;
        Applicant[] applicants = table.data;
        int found_app_ind = 0;
        boolean applicant_add_gpa;
        boolean applicant_add_skill;
        boolean applicant_add_company;
        boolean applicant_add_college;
        for (int i = 0; i < applicant_index; i++){
            applicant_add_college = false;
            applicant_add_skill = false;
            applicant_add_company = false;
            applicant_add_gpa = false;

            String[] skills = applicants[i].getApplicantSkills();
            String[] companies = applicants[i].getCompanyName();
            String college_app = applicants[i].getApplicantCollege();
            double gpa_app = applicants[i].getApplicantGPA();

            applicant_add_gpa = (gpa_app>=gpa);
            applicant_add_college = (college == null || college_app.equalsIgnoreCase(college));

            for (String a: skills){
                if (a == null){
                    applicant_add_skill = false;
                    break;
                }
                if ( skill == null || a.equalsIgnoreCase(skill)){
                    applicant_add_skill = true;
                    break;
                } else {
                    applicant_add_skill = false;
                }
            }


            for (String b: companies){
                if (b == null){
                    applicant_add_company = false;
                    break;
                }
                if ( company == null || b.equalsIgnoreCase(company)){
                    applicant_add_company = true;
                    break;
                } else {
                    applicant_add_company = false;
                }
            }

            if (applicant_add_gpa && applicant_add_skill && applicant_add_company && applicant_add_college){
                found_Applicants[found_app_ind] = applicants[i];
                found_app_ind++;
            }
        }

        System.out.printf("%-30s %-15s %-10s %-20s %-30s\n", "Company Name",
        "Applicant", "GPA", "College", "Skills");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Applicant a: found_Applicants){
            if (a != null) {
                a.ApplicantPrinter();
            } 
        }
    }

    /**
     * Creates and returns a deep copy of this {@code HiringTable} object.
     *
     * @return 
     * A clone of this hiring table.
     * @custom.precondition
     * This HiringTable has been instantiated.
     */
    public Object clone() {
        Applicant[] applicants = new Applicant[MAX_APPLICANTS];
        for (int i = 0; i<MAX_APPLICANTS; i++){
            if (this.data[i] != null){
                applicants[i] = (Applicant) this.data[i].clone();
            }
        }
        return new HiringTable(applicants);
    }

    /**
     * Prints the applicant table to the console.
     * @customs.precondition
     * The HiringTable has been instantiated.
     * @customs.postcondition
     * Displays a neatly formatted table of each Applicant from the HiringTable.
     */
    public void printApplicantTable() {
        System.out.printf("%-30s %-15s %-10s %-20s %-30s\n", "Company Name",
        "Applicant", "GPA", "College", "Skills");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Applicant a: data){
            if (a != null) {
                a.ApplicantPrinter();
            } 
        }
    }

    /**
     * Indicates whether some other object is "equal to" the current one.
     *
     * @param obj 
     * The object to compare with.
     * @return 
     * {@code true} if this object is the same as the {@code obj} argument.
     * {@code false} otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof HiringTable){
            if (cursor_applicant_index == ((HiringTable) obj).cursor_applicant_index){
                for (int i = 0; i < cursor_applicant_index; i++){
                    if (!this.data[i].equals(((HiringTable) obj).data[i])){
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else return false;
    }

    public static boolean areArraysEqual(String[] arr1, String[] arr2) {
        // Check if arrays have the same length
        if (arr1.length != arr2.length) {
            return false;
        }
        // Check if elements are the same and in the same order
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        // Arrays are equal
        return true;
    }
}