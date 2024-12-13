/**
* The <code>Applicant</code> class implements a fully-documented class named Applicant for a 
* job application which contains the Company Name (String[]), Applicant Name (String), GPA (double), 
* College (String), and Skills (String[]).
*/

public class Applicant implements Cloneable{
    private String[] companyName = new String[HiringTable.MAX_COMPANIES];
    private String applicantName;
    private double applicantGPA;
    private String applicantCollege;
    private String[] applicantSkills = new String[HiringTable.MAX_SKILLS];


    /**
     * This is an empty constructor used to create an empty Applicant
     */
    public Applicant(){
    }

    /**
     * This constructor used to create a Applicant with predefined company name, applicant name, applicant GPA, applicant college and applicant skills.
     * 
     * @param companyName
     * An array of the name of the companies the applicant work at.
     * @param applicantName
     * Name of the applicant.
     * @param applicantGPA
     * GPA of the applicant.
     * @param applicantCollege
     * College of the applicant.
     * @param applicantSkills
     * An array of the skills the applicant has.
     */
    public Applicant(String[] companyName, String applicantName, double applicantGPA, String applicantCollege, String[] applicantSkills) {
        this.companyName = companyName;
        this.applicantName = applicantName;
        this.applicantGPA = applicantGPA;
        this.applicantCollege = applicantCollege;
        this.applicantSkills = applicantSkills;
    }

    // Getters
    public String[] getCompanyName() {
        return companyName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public double getApplicantGPA() {
        return applicantGPA;
    }

    public String getApplicantCollege() {
        return applicantCollege;
    }

    public String[] getApplicantSkills() {
        return applicantSkills;
    }


    /**
     * A special getter that returns the skills applicant has as a well-formated string.
     * @return
     * The string that contains all the skills applicant has.
     */
    public String getApplicantSkillsAsString() {
        String temp_skills = applicantSkills[0];
        for (int i = 1; i<applicantSkills.length; i++){
            if (applicantSkills[i] == ""){break;}
            temp_skills += ", " + applicantSkills[i];
        }
        return temp_skills;
    }

    /**
     * A special getter that returns the companies applicant worked at as well-formated string.
     * @return
     * The string that contains all the companies applicant worked at.
     */
    public String getCompanyNameAsString() {
        String temp_comps = companyName[0];
        for (int i = 1; i<companyName.length; i++){
            if (companyName[i] == ""){break;}
            temp_comps += ", " + companyName[i];
        }
        return temp_comps;
    }


    // Setters
    public void setCompanyName(String[] companyName) {
        this.companyName = companyName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     * Setter for applicant GPA.
     * @param applicantGPA
     * Applicant GPA as a double.
     * @throws IllegalArgumentException
     * Throws an exception if GPA is less than 0.
     */
    public void setApplicantGPA(double applicantGPA) {
        if (applicantGPA < 0){
            this.applicantGPA = applicantGPA;
        } else {
            throw new IllegalArgumentException("GPA cannot be negative");
        }
    }

    // More setters
    public void setApplicantCollege(String applicantCollege) {
        this.applicantCollege = applicantCollege;
    }

    public void setApplicantSkills(String[] applicantSkills) {
        this.applicantSkills = applicantSkills;
    }


    /**
     * Creates a deep-copy clone of Applicant object.
     * @return
     * Returns the cloned object.
     */
    public Object clone() {
        String[] companyNameNew = new String[HiringTable.MAX_COMPANIES];
        String[] applicantSkillsNew = new String[HiringTable.MAX_SKILLS];

        for (int i = 0; i<HiringTable.MAX_COMPANIES; i++){
            companyNameNew[i] = this.companyName[i];
        }

        for (int i = 0; i<HiringTable.MAX_SKILLS; i++){
            applicantSkillsNew[i] = this.applicantSkills[i];
        }

        return new Applicant(companyNameNew, applicantName, applicantGPA, applicantCollege, applicantSkillsNew);  
    }
    

    /**
     * A method that returns true if the object passed to it is the same as the current object.
     * @param obj
     * An object that the current object is being compare to.
     * @return
     * Returns true if the object is equal, false if it is not.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Applicant){
            boolean a = areArraysEqual(this.companyName, ((Applicant) obj).companyName);
            boolean b = this.applicantName.equalsIgnoreCase(((Applicant) obj).applicantName);
            boolean c = this.applicantGPA == ((Applicant) obj).applicantGPA;
            boolean d = this.applicantCollege.equalsIgnoreCase(((Applicant) obj).applicantCollege);
            boolean e = areArraysEqual(this.applicantSkills , ((Applicant) obj).applicantSkills);
            return a && b && c && d && e;
        } else return false;
    }

    /**
     * A method that checks if the string[] are the same.
     * @param arr1
     * THe first string[] to compare
     * @param arr2
     * THe first string[] to compare
     * @return
     * Returns true if they are equal
     */
    public static boolean areArraysEqual(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns a string representation of the {@code Applicant} object.
     *
     * @return 
     * A string containing the applicant's information.
     */
    public String toString() {
        String temp_skills = applicantSkills[0];
        for (int i = 1; i<applicantSkills.length; i++){
            if (applicantSkills[i] == null){break;}
            temp_skills += ", " + applicantSkills[i];
        }
        String temp_comps = companyName[0];
        for (int i = 1; i<companyName.length; i++){
            if (companyName[i] == null){break;}
            temp_comps += ", " + companyName[i];
        }

        return "Companies: " + temp_comps + "\n" + "Applicant Name" + applicantName + "\n" + "Applicant GPA: " + applicantGPA + "\n" + "Applicant College: " + applicantCollege + "\n" + "Applicant Skills: " + temp_skills;
    }

    
    /**
     * Prints a string representation of the {@code Applicant} object and compliant of the CodeGrade format.
     */
    public void ApplicantPrinter(){
        String temp_skills = applicantSkills[0];
        for (int i = 1; i<applicantSkills.length; i++){
            if (applicantSkills[i] == ""){break;}
            temp_skills += ", " + applicantSkills[i];
        }
        String temp_comps = companyName[0];
        for (int i = 1; i<companyName.length; i++){
            if (companyName[i] == ""){break;}
            temp_comps += ", " + companyName[i];
        }
       
        System.out.printf("%-30s %-15s %-10s %-20s %-30s\n",
                        temp_comps,
                        applicantName,
                        String.format("%.2f", applicantGPA),
                        applicantCollege,
                        temp_skills);
    }
}
